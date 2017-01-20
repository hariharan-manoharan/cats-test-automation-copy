package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.*;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.Random;

@Listeners(TestRailListener.class)
public class FieldTest extends MainTest {

    private final static String folder = "Field";

    private MoveStart moveStart;
    private MoveFinish moveFinish;
    private Stock stock;
    private SiteReturn siteReturn;

    private String toLocation = "1";
    private String fromLocation = "1000";
    private String fromStatus = "ON HAND";
    private String uid;
    private String serialNumber;
    private String packageTag;
    private String serializedPartCode = "ABC000001";
    private String nonSerializedPartCode = "ABC000002";
    private String barcode2;
    private int qty;
    protected Random rand;

    private int num;

    @BeforeClass(alwaysRun = true)
    public void stageData() {
        stock = new Stock(mobilityDriver, oracleDriver);
        moveStart = new MoveStart(mobilityDriver, oracleDriver);
        moveFinish = new MoveFinish(mobilityDriver, oracleDriver);
        siteReturn = new SiteReturn(mobilityDriver, oracleDriver);
        rand =  new Random();
        num = rand.nextInt(1000000) + 1;
        serialNumber = "SERIAL" + num;
        uid = "ASSET" + num;
        packageTag = "PACKAGE" + num;
        folders.selectFolder("Inventory");
        routines.selectRoutine("Stock");
        stock.execute(fromLocation, null, serializedPartCode, "UNKNOWN", serialNumber, uid, packageTag);
        menu.selectAdmin();
    }

    //Move Start asset
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 100, enabled = true)
    public void moveStart0001() {
        navigateToFolder();
        routines.selectRoutine("Move Start");
        moveStart.execute(fromLocation, toLocation, serializedPartCode, uid);
        Assert.assertEquals(moveStart.getResult(), true);
    }

    //Move Start Part
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 101, enabled = true)
    public void moveStart0002() {
        moveStart.execute(fromLocation, toLocation, nonSerializedPartCode, "CWIP", 1);
        Assert.assertEquals(moveStart.getResult(), true);
    }

    //Move Finish asset
    @TestRailCase(selfReporting = true, automationId = "1008")
    @Test(priority = 102, enabled = true)
    public void moveFinish0001() {
        moveFinish.clickBackRoutines();
        routines.selectRoutine("Move Finish");
        moveFinish.execute(fromLocation, toLocation, serializedPartCode, uid);
        Assert.assertEquals(moveFinish.getResult(), true);
    }

    //Move Finish part
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 103, enabled = true)
    public void moveFinish0002() {
        moveFinish.execute(fromLocation, toLocation, nonSerializedPartCode, barcode2);
        Assert.assertEquals(moveStart.getResult(), true);
    }

    //Site Return Asset
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 104, enabled = true)
    public void siteReturn0001() {
        siteReturn.clickBackRoutines();
        routines.selectRoutine("Site Return");
        siteReturn.execute(toLocation, serializedPartCode, uid);
        Assert.assertEquals(moveStart.getResult(), true);
    }


    //Site Return Part
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 105, enabled = true)
    public void siteReturn0002() {
        siteReturn.execute(toLocation, nonSerializedPartCode, barcode2);
        Assert.assertEquals(moveStart.getResult(), true);
    }


    private void navigateToFolder() {
        folders.selectFolder(folder);
    }
}