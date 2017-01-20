package net.fulcrum.Routines.iOS.SoLinc.Test;

import net.fulcrum.Pages.iOS.Test.MainTest;
import net.fulcrum.Routines.iOS.SoLinc.*;


import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.*;

@Listeners(TestRailListener.class)
public class BVTTest extends MainTest {

    private StockAsset stockAsset;
    private TransferAsset transferAsset;
    private UpdateAsset updateAsset;
    private ChangeAssetLabel changeAssetLabel;
    private StockPart stockPart;
    private TransferPart transferPart;
    private IssuePart issuePart;
    private AssetToRepair assetToRepair;
    private AssetFromRepair assetFromRepair;

    private String stockLDC = "ACG9919";
    private String transferLDC = "SCG9102";

    private String assetCode;
    private String serializedPartCode =  "1400VA";
    private String nonSerializedPartCode= "PPTEST LTE";
    private String raCode = "RA07122";
    private String serialNumber;
    private String repairAsset;
    private int qty;
    protected Random rand;
    private int num;
    private String status;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        stockAsset = new StockAsset(mobilityDriver, oracleDriver);
        transferAsset = new TransferAsset(mobilityDriver, oracleDriver);
        updateAsset = new UpdateAsset(mobilityDriver, oracleDriver);
        changeAssetLabel = new ChangeAssetLabel(mobilityDriver, oracleDriver);
        stockPart = new StockPart(mobilityDriver, oracleDriver);
        transferPart = new TransferPart(mobilityDriver, oracleDriver);
        issuePart = new IssuePart(mobilityDriver, oracleDriver);
        assetToRepair = new AssetToRepair(mobilityDriver, oracleDriver);
        assetFromRepair = new AssetFromRepair(mobilityDriver, oracleDriver);
    }

    //Stock an Asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 100, enabled = true)
    public void stockAsset0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Stock Asset"));
        num = rand.nextInt(1000000) + 1;
        assetCode = "A" + num;
        serialNumber = "S" + num;
        stockAsset.execute(stockLDC, serializedPartCode, assetCode, serialNumber);
        Assert.assertEquals(stockAsset.getResult(), true);
    }

    //Stock a second Asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 101, enabled = true)
    public void stockAsset0002() {
        num = rand.nextInt(1000000) + 1;
        repairAsset = "A" + num;
        serialNumber = "S" + num;
        stockAsset.execute(stockLDC, serializedPartCode, assetCode, serialNumber);
        Assert.assertEquals(stockAsset.getResult(), true);
    }


    //Transfer Asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 102, enabled = true, dependsOnMethods = "stockAsset0001")
    public void transferAsset0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Transfer Asset"));
        transferAsset.execute(transferLDC, assetCode);
        Assert.assertEquals(transferAsset.getResult(), true);
    }

    //Update Asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 103, enabled = true, dependsOnMethods = "stockAsset0001")
    public void updateAsset0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Update Asset"));
        num = rand.nextInt(1000000) + 1;
        serialNumber = "S" + num;
        updateAsset.execute(null, null, assetCode, serialNumber);
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    //Change Asset Label
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 104, enabled = true, dependsOnMethods = "stockAsset0001")
    public void changeAssetLabel0001() {
        String newAssetCode;
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Change Asset Label"));
        num = rand.nextInt(1000000) + 1;
        newAssetCode = "NEW" + num;
        changeAssetLabel.execute(assetCode, newAssetCode);
        Assert.assertEquals(changeAssetLabel.getResult(), true);
    }

    //Stock Part
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 105, enabled = true)
    public void stockPart0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Stock Part"));
        qty = 10;
        stockPart.execute(stockLDC, nonSerializedPartCode, qty);
        Assert.assertEquals(stockPart.getResult(), true);
    }

    //Transfer Part
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 106, enabled = true, dependsOnMethods = "stockPart0001")
    public void transferPart0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Transfer Part"));
        qty = 5;
        transferPart.execute(stockLDC, transferLDC, nonSerializedPartCode, qty);
        Assert.assertEquals(transferPart.getResult(), true);
    }

    //Issue Part
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 107, enabled = true, dependsOnMethods = "stockPart0001")
    public void issuePart0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Issue Part"));
        qty = 5;
        issuePart.execute(stockLDC, nonSerializedPartCode, qty);
        Assert.assertEquals(issuePart.getResult(), true);
    }

    //Asset To Repair
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 108, enabled = true, dependsOnMethods = "stockAsset0002")
    public void assetToRepair0001() {
        String repairLDC = "UVRFS";
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Asset To Repair"));
        assetToRepair.execute(repairLDC, raCode, repairAsset, stockLDC);
        Assert.assertEquals(assetToRepair.getResult(), true);
    }

    //Asset From Repair
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 109, enabled = true, dependsOnMethods = "assetToRepair0001")
    public void assetFromRepair0001() {
        String repairLDC = "UVRFS";
        num = rand.nextInt(1000000) + 1;
        String fixAssetCode = "A" + num;
        serialNumber = "S" + num;
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Asset From Repair"));
        assetFromRepair.execute(repairLDC, raCode, repairAsset, fixAssetCode, serializedPartCode, serialNumber);
        Assert.assertEquals(assetFromRepair.getResult(), true);
    }


    public void reset() {
        //stock.setInitial();
    }

}