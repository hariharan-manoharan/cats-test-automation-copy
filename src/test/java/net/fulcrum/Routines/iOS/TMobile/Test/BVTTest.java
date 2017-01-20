package net.fulcrum.Routines.iOS.TMobile.Test;

import net.fulcrum.Pages.iOS.Test.MainTest;
import net.fulcrum.Routines.iOS.TMobile.*;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.*;

@Listeners(TestRailListener.class)
public class BVTTest extends MainTest {

    private AssetRelabel assetRelabel;
    private AssetUpdate assetUpdate;
    private AssetTransfer assetTransfer;
    private PartRemove partRemove;
    private OpenContainer openContainer;
    private CloseContainer closeContainer;
    private AddToContainer addToContainer;
    private RemoveFromContainer removeFromContainer;
    private TransferContainer transferContainer;
    private DestroyContainer destroyContainer;

    private String availableLDC = "AC1AT1053";
    private String transferLDC = "TC1AT1053";
    private String assetCode = "A07181";
    private String assetCode2;
    private String serialNumber;
    private String container = "C07181";
    private String serializedPartCode = "7528";
    private String nonSerializedPartCode = "7591";

    protected Random rand;
    private int num;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        assetRelabel = new AssetRelabel(mobilityDriver, oracleDriver);
        assetUpdate = new AssetUpdate(mobilityDriver, oracleDriver);
        assetTransfer = new AssetTransfer(mobilityDriver, oracleDriver);
        partRemove = new PartRemove(mobilityDriver, oracleDriver);
        openContainer = new OpenContainer(mobilityDriver, oracleDriver);
        closeContainer = new CloseContainer(mobilityDriver, oracleDriver);
        addToContainer = new AddToContainer(mobilityDriver, oracleDriver);
        removeFromContainer = new RemoveFromContainer(mobilityDriver, oracleDriver);
        transferContainer = new TransferContainer(mobilityDriver, oracleDriver);
        destroyContainer = new DestroyContainer(mobilityDriver, oracleDriver);
        num = rand.nextInt(1000000) + 1;
        assetCode2 = "A" + num;
        serialNumber = "S" + num;
    }

    //Relabel Asset Code
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 100, enabled = true)
    public void assetRelabel0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Asset Re-Label"));
        assetRelabel.execute(assetCode, assetCode2);
        Assert.assertEquals(assetRelabel.getResult(), true);
    }

    //Asset Update
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 101, enabled = true)
    public void assetUpdate0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Asset Update"));
        assetUpdate.execute(assetCode2, serialNumber);
        Assert.assertEquals(assetUpdate.getResult(), true);
    }

    //Asset Transfer
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 102, enabled = true, dependsOnMethods = "assetRelabel0001")
    public void assetTransfer0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Asset Transfer"));
        assetTransfer.execute(transferLDC, assetCode2);
        Assert.assertEquals(assetTransfer.getResult(), true);
    }

    //Part Remove
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 103, enabled = true)
    public void partRemove0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Part Remove"));
        partRemove.execute(availableLDC, nonSerializedPartCode, 1);
        Assert.assertEquals(partRemove.getResult(), true);
    }

    //Open Container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 104, enabled = true)
    public void openContainer0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Open Container"));
        openContainer.execute(container);
        Assert.assertEquals(openContainer.getResult(), true);
    }

    //Add To Container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 105, enabled = true, dependsOnMethods = "openContainer0001")
    public void addToContainer0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Add To Container"));
        addToContainer.execute(availableLDC, container, assetCode2);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Remove From Container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 106, enabled = true, dependsOnMethods = "addToContainer0001")
    public void removeFromContainer0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Remove From Container"));
        removeFromContainer.execute(availableLDC, container, assetCode2);
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    //Close Container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 107, enabled = true)
    public void closeContainer0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Close Container"));
        closeContainer.execute(container);
        Assert.assertEquals(closeContainer.getResult(), true);
    }

    //Transfer Container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 108, enabled = true, dependsOnMethods =  "closeContainer0001")
    public void transferContainer0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Transfer Container"));
        transferContainer.execute(availableLDC, container);
        Assert.assertEquals(transferContainer.getResult(), true);
    }

    //Asset Update to original Asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 109, enabled = true, dependsOnMethods = "assetUpdate0001")
    public void assetUpdate0002() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Asset Update"));
        assetUpdate.execute(assetCode2, assetCode);
        Assert.assertEquals(assetUpdate.getResult(), true);
    }

    //Destroy Container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 110, enabled = false, dependsOnMethods = "closeContainer0001")
    public void destroyContainer0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Destroy Container"));
        destroyContainer.execute(container);
        Assert.assertEquals(destroyContainer.getResult(), true);
    }


    public void reset() {
        //stock.setInitial();
    }

}