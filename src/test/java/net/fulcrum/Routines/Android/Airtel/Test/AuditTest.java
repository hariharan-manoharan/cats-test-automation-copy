package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.*;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

@Listeners(TestRailListener.class)
public class AuditTest extends MainTest {

    private final static String folder = "Other";

    private Audit audit;
    private AuditOpen auditOpen;
    private AuditCancel auditCancel;
    private AuditClose auditClose;

    private String locationDetailCode;
    private String type;
    private String barcode;
    private int qty;

    @BeforeClass(alwaysRun = true)
    public void stageData() {
        audit = new Audit(mobilityDriver, oracleDriver);
        auditOpen = new AuditOpen(mobilityDriver, oracleDriver);
        auditCancel = new AuditCancel(mobilityDriver, oracleDriver);
        auditClose = new AuditClose(mobilityDriver, oracleDriver);
    }

    //Open Audit
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 100, enabled = true)
    public void auditOpen0001() {
        navigateToFolder();
        routines.selectRoutine("Audit Open");
        auditOpen.execute(locationDetailCode, type);
        Assert.assertEquals(auditOpen.getResult(), true);
    }

    //Audit Cancel
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 101, enabled = true)
    public void auditCancel0001() {
        navigateToFolder();
        routines.selectRoutine("Audit Cancel");
        auditCancel.execute(locationDetailCode);
        Assert.assertEquals(auditCancel.getResult(), true);
    }

    //Open new Audit
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 102, enabled = true)
    public void auditOpen0002() {
        navigateToFolder();
        routines.selectRoutine("Audit Open");
        auditOpen.execute(locationDetailCode, type);
        Assert.assertEquals(auditOpen.getResult(), true);
    }

    //Audit Asset
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 103, enabled = true)
    public void audit0001() {
        navigateToFolder();
        routines.selectRoutine("Audit");
        audit.execute(locationDetailCode, barcode);
        Assert.assertEquals(audit.getResult(), true);
    }

    //Close Audit
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 104, enabled = true)
    public void auditClose0001() {
        navigateToFolder();
        routines.selectRoutine("Audit Close");
        auditCancel.execute(locationDetailCode);
        Assert.assertEquals(auditClose.getResult(), true);
    }

    private void navigateToFolder() {
        menu.selectRoutines();
        folders.selectFolder(folder);
    }
}