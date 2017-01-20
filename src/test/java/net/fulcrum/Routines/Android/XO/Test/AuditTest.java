package net.fulcrum.Routines.Android.XO.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.XO.*;
import org.testng.Assert;
import org.testng.annotations.*;
import net.fulcrum.testrail.annotations.*;

public class AuditTest extends MainTest {

    private Audit audit;
    private AuditOpen auditOpen;
    private AuditClose auditClose;

    private String status;
    private String partCodeS = "00038002";
    private String partCodeNS = "00031484";
    private String auditLocation = "AUDIT";
    private String auditAsset = "AUDITASSET1";
    private String bin = "ENG";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        audit = new Audit(mobilityDriver, oracleDriver, routines);
        auditClose = new AuditClose(mobilityDriver, oracleDriver, routines);
        auditOpen = new AuditOpen(mobilityDriver, oracleDriver, routines);
        profiles.selectProfile("ADMIN");
        routines.selectRoutine("Audit Open");

    }

    @TestRailCase(automationId = "8084")
    @Test(priority = 25, enabled = true)
    public void auditOpen() {
        auditOpen.execute(auditLocation, "Audit", "Y");
        Assert.assertEquals(true, auditOpen.getResult());
    }

    @TestRailCase(automationId = "8085")
    @Test(priority = 26, enabled = true)
    public void auditAsset() {
        audit.reset();
        status = "On Hand";
        audit.execute(auditLocation, status, auditAsset);
        Assert.assertEquals(true, audit.getResult());
    }

    @TestRailCase(automationId = "8087")
    @Test(priority = 27, enabled = true)
    public void auditPart() {
        audit.execute(auditLocation, status, partCodeNS, 8);
        Assert.assertEquals(true, audit.getResult());
    }

    @TestRailCase(automationId = "8086")
    @Test(priority = 28, enabled = true)
    public void auditClose() {
        auditClose.reset();
        auditClose.execute(auditLocation, "Y");
        Assert.assertEquals(true, auditClose.getResult());
    }
}
