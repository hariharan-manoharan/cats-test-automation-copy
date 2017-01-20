package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.*;


import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.*;

@Listeners(TestRailListener.class)
public class ReceiveTest extends MainTest {

    private final static String folder = "Receiving";

    private Stock stock;
    private WarehouseReceive warehouseReceive;
    private MRRReceive mrrReceive;
    private SiteReceive siteReceive;

    private String location = "WH1";
    private String parentUID = "A070731";
    private String serial;
    private String packageTag;
    private String uid;
    private String nonSerializedPartCode= "E3LHI0043";
    private String serializedPartCode = "E3HHN0468";
    private String transferNumber = "MOA2";
    private String shipment = "UPS";
    private String mrrNumberNS = "53285.28979";
    private String mrrNumber = "53281.28217";
    private String choosePart = "UNKNOWN";
    private String lot;
    private String assetCode;
    private String barcode;
    private int qty;
    protected Random rand;
    private int num;

    @BeforeClass(alwaysRun = true)
    public void stageData() {
        warehouseReceive = new WarehouseReceive(mobilityDriver, oracleDriver);
        mrrReceive = new MRRReceive(mobilityDriver, oracleDriver);
        siteReceive = new SiteReceive(mobilityDriver, oracleDriver);
        rand =  new Random();
    }

    //Perform Warehouse Receive
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 100, enabled = true)
    public void warehouseReceive0001() {
        navigateToFolder();
        routines.selectRoutine("Warehouse Receive");
        num = rand.nextInt(1000000) + 1;
        transferNumber = "MOA";
        warehouseReceive.execute(location, transferNumber, shipment, serializedPartCode, uid);
        Assert.assertEquals(warehouseReceive.getResult(), true);
    }

    /*
    //Perform MRR Receive asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 102, enabled = true)
    public void mrrReceive0001() {
        mrrReceive.clickBackRoutines();
        routines.selectRoutine("MRR Receive");
        num = rand.nextInt(1000000) + 1;
        serial = "SERIAL" + num;
        uid = "ASSET" + num;
        packageTag = "PACKAGE" + num;
        mrrReceive.execute(location, mrrNumber, serializedPartCode, choosePart, serial, uid, packageTag);
        Assert.assertEquals(mrrReceive.getResult(), true);
    }

    //Perform MRR Receive part
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 103, enabled = true)
    public void mrrReceive0002() {
        mrrReceive.clickBackRoutines();
        routines.selectRoutine("MRR Receive");
        mrrReceive.setInitial();
        qty = 1;
        mrrReceive.execute(location, mrrNumberNS, nonSerializedPartCode, choosePart, qty);
        Assert.assertEquals(mrrReceive.getResult(), true);
    }
/*
    //Perform Site Receive
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 104, enabled = true)
    public void siteReceive0001() {
        siteReceive.clickBackRoutines();
        routines.selectRoutine("Site Receive");
        siteReceive.execute(location, transferNumber, shipment, barcode, assetCode);
        Assert.assertEquals(mrrReceive.getResult(), true);
    }
*/
    private void navigateToFolder() {
        folders.selectFolder(folder);
    }

}