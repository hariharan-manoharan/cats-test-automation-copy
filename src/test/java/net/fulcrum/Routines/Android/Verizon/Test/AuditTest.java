package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.Drivers.*;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Pages.Android.Test.MainTest;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.Assert;

import java.net.MalformedURLException;

@Listeners(TestRailListener.class)
public class AuditTest extends MainTest {


    private String toLDC;
    private String assetCode;
    private AuditOpen auditOpen;
    private AuditCancel auditCancel;
    private AuditCancel auditClose;

    @BeforeClass(alwaysRun = true)
    public void StageData() {

        auditOpen = new AuditOpen(mobilityDriver, oracleDriver, routines);
        auditCancel = new AuditCancel(mobilityDriver, oracleDriver);
        auditClose = new AuditCancel(mobilityDriver, oracleDriver);

    }


    @TestRailCase(automationId = "14")
    @Test(priority = 100, enabled = true)
    public void assetOpen() {

        routines.selectRoutine("Audit Open");
        auditOpen.executeRoutine("CNS103383");
    }

    @TestRailCase(automationId = "14")
    @Test(priority = 101, enabled = true)
    public void assetCancel() {
        menu.selectAdmin();
        routines.selectRoutine("Audit Cancel");
        auditCancel.executeRoutine("CNS103383");
    }

    @TestRailCase(automationId = "14")
    @Test(priority = 102, enabled = true)
    public void assetOpen2() {
        menu.selectAdmin();
        routines.selectRoutine("Audit Open");
        auditOpen.executeRoutine("CNS103383");
    }

    @TestRailCase(automationId = "14")
    @Test(priority = 103, enabled = true)
    public void assetClose() {
        menu.selectAdmin();
        routines.selectRoutine("Audit Close");
        auditClose.executeRoutine("CNS103383");
    }
}