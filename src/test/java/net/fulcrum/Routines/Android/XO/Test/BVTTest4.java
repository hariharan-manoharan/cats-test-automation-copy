package net.fulcrum.Routines.Android.XO.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.XO.*;
import org.testng.Assert;
import org.testng.annotations.*;
import net.fulcrum.testrail.annotations.*;

public class BVTTest4 extends MainTest {

    private StockEquipment stockEquipment;
    private Pack pack;
    private Pick pick;
    private Ship ship;
    private InternalReceive internalReceive;
    private VendorReceiveRepair vendorReceiveRepair;
    private VendorShipRepair vendorShipRepair;
    private VendorStatusUpdate vendorStatusUpdate;
    private Audit audit;
    private AuditOpen auditOpen;
    private AuditClose auditClose;

    private String status;
    private String partCodeS = "00038002";
    private String barcodeType = "XO ITEM NUMBER";
    private String partCodeNS = "00031484";
    private String stockFromLocation = "ABE-WAREHOUSE";
    private String bin = "ENG";
    private String transferLocation = "SEA-WAREHOUSE";
    private int qty;
    private String transfer = "MAN0002758";
    private String job = "11144406";
    private String assetCode3;
    private String rma = "RMA12121";
    private String condition = "REPAIR";
    private String fault = "BAD/FAILED";
    private String auditLocation = "AUDIT";
    private String auditAsset = "AUDITASSET1";
    private String mfgPartNumberNS = "C02510364-010";
    private String mfgPartNumberS = "TS1007202";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        stockEquipment = new StockEquipment(mobilityDriver, oracleDriver, routines);
        internalReceive = new InternalReceive(mobilityDriver, oracleDriver, routines);
        pack = new Pack(mobilityDriver, oracleDriver, routines);
        pick = new Pick(mobilityDriver, oracleDriver, routines);
        ship = new Ship(mobilityDriver, oracleDriver, routines);
        vendorReceiveRepair = new VendorReceiveRepair(mobilityDriver, oracleDriver, routines);
        vendorShipRepair = new VendorShipRepair(mobilityDriver, oracleDriver, routines);
        vendorStatusUpdate = new VendorStatusUpdate(mobilityDriver, oracleDriver, routines);
        audit = new Audit(mobilityDriver, oracleDriver, routines);
        auditClose = new AuditClose(mobilityDriver, oracleDriver, routines);
        auditOpen = new AuditOpen(mobilityDriver, oracleDriver, routines);
        qty = mobilityDriver.getRandomInt(100);
        routines.selectRoutine("Stock Equipment");
        oracleDriver.createAssetXO(partCodeS, mobilityDriver.getNewAssetCode());
        oracleDriver.cleanAuditLocationXO("148348");
    }

    @Test(priority = 1, enabled = true)
    public void stockItems() {
        status = "ON HAND";
        qty = 20;
        assetCode = mobilityDriver.getNewAssetCode();
        stockEquipment.execute(transferLocation, status, bin, partCodeS, barcodeType, assetCode);
        assetCode2 = mobilityDriver.getNewAssetCode();
        stockEquipment.execute(transferLocation, status, bin, partCodeS, barcodeType, assetCode2);
        assetCode3 = mobilityDriver.getNewAssetCode();
        stockEquipment.execute(transferLocation, status, bin, partCodeS, barcodeType, assetCode3);
        stockEquipment.execute(stockFromLocation, status, bin, partCodeNS, null, qty);
        containerCode = "C" + mobilityDriver.getRandomInt(100000);
        containerCode2 = "C" + mobilityDriver.getRandomInt(100000);
        Assert.assertEquals(true, stockEquipment.getResult());
    }

    @Test(priority = 2, enabled = true)
    public void pickPart() {
        pick.reset();
        pick.execute(transfer, "1", bin, 10);
        Assert.assertEquals(true, pick.getResult());
    }

    @Test(priority = 3, enabled = true)
    public void pickAsset() {
        pick.execute(transfer, "2", assetCode);
        Assert.assertEquals(true, pick.getResult());
    }

    @Test(priority = 4, enabled = true)
    public void packPart() {
        pack.reset();
        pack.execute(transfer, containerCode, partCodeNS, 10);
        Assert.assertEquals(true, pack.getResult());
    }

    @Test(priority = 5, enabled = true)
    public void packAsset() {
        pack.execute(transfer, containerCode, assetCode);
        Assert.assertEquals(true, pack.getResult());
    }

    @Test(priority = 6, enabled = true)
    public void ship() {
        ship.reset();
        ship.execute(transfer, containerCode);
        Assert.assertEquals(true, ship.getResult());
    }


    @TestRailCase(automationId = "8078")
    @Test(priority = 19, enabled = true)
    public void vendorReceiveRepairAsset() {
        vendorReceiveRepair.reset();
        vendorReceiveRepair.execute(rma, containerCode, assetCode);
        Assert.assertEquals(true, vendorReceiveRepair.getResult());
    }

    @TestRailCase(automationId = "8079")
    @Test(priority = 20, enabled = true)
    public void vendorReceiveRepairPart() {
        status = "ON HAND";
        vendorReceiveRepair.execute(rma, containerCode, partCodeNS, 1);
        Assert.assertEquals(true, vendorReceiveRepair.getResult());
    }

    @TestRailCase(automationId = "8080")
    @Test(priority = 21, enabled = true)
    public void vendorStatusUpdateAsset() {
        vendorStatusUpdate.reset();
        vendorStatusUpdate.execute(rma, mfgPartNumberS, condition, fault);
        Assert.assertEquals(true, vendorReceiveRepair.getResult());
    }

    @TestRailCase(automationId = "8081")
    @Test(priority = 22, enabled = true)
    public void vendorStatusUpdatePart() {
        status = "ON HAND";
        vendorStatusUpdate.execute(rma, mfgPartNumberNS, 1, 1);
        Assert.assertEquals(true, vendorStatusUpdate.getResult());
    }

    @TestRailCase(automationId = "8082")
    @Test(priority = 23, enabled = true)
    public void vendorShipRepairAsset() {
        vendorShipRepair.reset();
        vendorShipRepair.execute(rma, mfgPartNumberS);
        Assert.assertEquals(true, vendorShipRepair.getResult());
    }

    @TestRailCase(automationId = "8083")
    @Test(priority = 24, enabled = true)
    public void vendorShipRepairPart() {
        vendorShipRepair.execute(rma, mfgPartNumberNS, 1);
        Assert.assertEquals(true, vendorShipRepair.getResult());
    }

    @TestRailCase(automationId = "8084")
    @Test(priority = 25, enabled = true)
    public void auditOpen() {
        auditOpen.reset();
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
