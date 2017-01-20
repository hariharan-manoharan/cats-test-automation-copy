package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.*;
import net.fulcrum.Pages.Android.*;


import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

@Listeners(TestRailListener.class)
public class ContainerTest extends MainTest {

    private final static String folder = "Container";

    private AddToContainer addToContainer;
    private RemoveFromContainer removeFromContainer;
    private OpenContainer openContainer;
    private CloseContainer closeContainer;

    private String location = "WH1";
    private String assetCode = "ASSET08202";
    private String serializedPartCode =  "ABC000011";
    private String nonSerializedPartCode= "E3LSN0014";
    private String container = "CON334893";
    private String childContainer = "CHILD06301";
    private String lot = "LOT1";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        addToContainer = new AddToContainer(mobilityDriver, oracleDriver);
        removeFromContainer = new RemoveFromContainer(mobilityDriver, oracleDriver);
        closeContainer = new CloseContainer(mobilityDriver, oracleDriver);
        openContainer = new OpenContainer(mobilityDriver, oracleDriver);
    }

    //Add Asset to an existing Container
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 100, enabled = true)
    public void addToContainer0001() {
        navigateToFolder();
        routines.selectRoutine("Add To Container");
        addToContainer.execute(location, container, serializedPartCode, assetCode);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Add Part to an existing Container
    @TestRailCase(selfReporting = true, automationId = "1007")
    @Test(priority = 101, enabled = true)
    public void addToContainer0002() {
        String fromStatus = "ON HAND";
        addToContainer.execute(location, fromStatus, container, nonSerializedPartCode, lot, 1);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Add Container to an existing Container
    @TestRailCase(selfReporting = true, automationId = "1007")
    @Test(priority = 102, enabled = true)
    public void addToContainer0003() {
        addToContainer.execute(location, container, childContainer);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Remove Asset from an existing Container
    @TestRailCase(selfReporting = true, automationId = "1008")
    @Test(priority = 103, enabled = true)
    public void removeFromContainer0001() {
        removeFromContainer.clickBackRoutines();
        routines.selectRoutine("Remove From Container");
        removeFromContainer.execute(location, container, serializedPartCode, assetCode);
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    //Remove Part from an existing Container
    @TestRailCase(selfReporting = true, automationId = "1009")
    @Test(priority = 104, enabled = true)
    public void removeFromContainer0002() {
        removeFromContainer.execute(location, container, nonSerializedPartCode, lot, 1);
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    //Remove Container from an existing Container
    @TestRailCase(selfReporting = true, automationId = "1010")
    @Test(priority = 105, enabled = true)
    public void removeFromContainer0003() {
        removeFromContainer.execute(location, container, childContainer);
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    //Close an existing Container
    @TestRailCase(selfReporting = true, automationId = "1011")
    @Test(priority = 106, enabled = true)
    public void closeContainer0001() {
        closeContainer.clickBackRoutines();
        routines.selectRoutine("Close Container");
        closeContainer.execute(location, container);
        Assert.assertEquals(closeContainer.getResult(), true);
    }

    //Open an existing Container
    @TestRailCase(selfReporting = true, automationId = "1012")
    @Test(priority = 106, enabled = true)
    public void openContainer0001() {
        openContainer.clickBackRoutines();
        routines.selectRoutine("Open Container");
        openContainer.execute(location, container);
        Assert.assertEquals(openContainer.getResult(), true);
    }

    private void navigateToFolder() {
        folders.selectFolder(folder);
    }
}