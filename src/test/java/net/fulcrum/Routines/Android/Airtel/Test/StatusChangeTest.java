package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.*;
import net.fulcrum.Pages.Android.*;


import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

@Listeners(TestRailListener.class)
public class StatusChangeTest extends MainTest {

    private StatusChange statusChange;
    private String location = "WH1";
    private String assetCode;
    private String nonSerializedPartCode;
    private String status;
    private String lot;
    private String partCode = "ABC000002";
    private int qty;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        folders.selectFolder("Inventory");
        routines.selectRoutine("Status Change");
        statusChange = new StatusChange(mobilityDriver, oracleDriver);
    }

    @TestRailCase(selfReporting = true, automationId = "1015")
    @Test(priority = 100, enabled = true)
    public void statusChange0001() {
        assetCode = "ASSET831251";
        status = "SPARE";
        statusChange.execute(location, partCode, assetCode, status);
        Assert.assertEquals(statusChange.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "1016")
    @Test(priority = 100, enabled = true)
    public void statusChange0002() {
        status = "SPARE";
        nonSerializedPartCode =  "010110033";
        lot = "LOT1";
        qty = 1;
        statusChange.execute(location, nonSerializedPartCode, lot, qty, status, status);
        Assert.assertEquals(statusChange.getResult(), true);
    }
}