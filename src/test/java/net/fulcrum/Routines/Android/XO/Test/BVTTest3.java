package net.fulcrum.Routines.Android.XO.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.XO.*;
import org.testng.Assert;
import org.testng.annotations.*;
import net.fulcrum.testrail.annotations.*;

public class BVTTest3 extends MainTest {

    private StockEquipment stockEquipment;
    private Pack pack;
    private Pick pick;
    private PickShip pickShip;
    private Ship ship;
    private Unpack unpack;
    private Unpick unpick;
    private Unship unship;
    private InstallEquipment installEquipment;
    private ReturnEquipment returnEquipment;
    private InternalReceive internalReceive;

    private String status;
    private String partCodeS = "00038002";
    private String barcodeType = "XO ITEM NUMBER";
    private String partCodeNS = "00031484";
    private String stockFromLocation = "ABE-WAREHOUSE";
    private String installLocation = "PDX-WAREHOUSE";
    private String bin = "ENG";
    private String transferLocation = "SEA-WAREHOUSE";
    private String returnLocation = "SEA-WAREHOUSE";
    private int qty;
    private String transfer = "MAN0002757";
    private String job = "11144406";
    private String assetCode3;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        stockEquipment = new StockEquipment(mobilityDriver, oracleDriver, routines);
        installEquipment = new InstallEquipment(mobilityDriver, oracleDriver, routines);
        returnEquipment = new ReturnEquipment(mobilityDriver, oracleDriver, routines);
        internalReceive = new InternalReceive(mobilityDriver, oracleDriver, routines);
        pack = new Pack(mobilityDriver, oracleDriver, routines);
        pick = new Pick(mobilityDriver, oracleDriver, routines);
        pickShip = new PickShip(mobilityDriver, oracleDriver, routines);
        ship = new Ship(mobilityDriver, oracleDriver, routines);
        unpack = new Unpack(mobilityDriver, oracleDriver, routines);
        unpick = new Unpick(mobilityDriver, oracleDriver, routines);
        unship = new Unship(mobilityDriver, oracleDriver, routines);
        qty = mobilityDriver.getRandomInt(100);
        profiles.selectProfile("ADMIN");
        routines.selectRoutine("Stock Equipment");
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

    @TestRailCase(automationId = "8061")
    @Test(priority = 2, enabled = true)
    public void pickPart() {
        pick.reset();
        pick.execute(transfer, "1", bin, 10);
        Assert.assertEquals(true, pick.getResult());
    }

    @TestRailCase(automationId = "8062")
    @Test(priority = 3, enabled = true)
    public void pickAsset() {
        pick.execute(transfer, "2", assetCode);
        pick.execute(transfer, "2", assetCode2);

        Assert.assertEquals(true, pick.getResult());
    }

    @TestRailCase(automationId = "8063")
    @Test(priority = 4, enabled = true)
    public void packPart() {
        pack.reset();
        pack.execute(transfer, containerCode, partCodeNS, 10);
        Assert.assertEquals(true, pack.getResult());
    }

    @TestRailCase(automationId = "8064")
    @Test(priority = 5, enabled = true)
    public void packAsset() {
        pack.execute(transfer, containerCode, assetCode);
        pack.reset();
        pack.execute(transfer, containerCode2, assetCode2);
        Assert.assertEquals(true, pack.getResult());
    }

    @TestRailCase(automationId = "8065")
    @Test(priority = 6, enabled = true)
    public void ship() {
        ship.reset();
        ship.execute(transfer, containerCode);
        ship.execute(transfer, containerCode2);
        Assert.assertEquals(true, ship.getResult());
    }

    @TestRailCase(automationId = "8066")
    @Test(priority = 7, enabled = true)
    public void unship() {
        unship.reset();
        unship.execute(containerCode);
        Assert.assertEquals(true, unship.getResult());
    }

    @TestRailCase(automationId = "8067")
    @Test(priority = 8, enabled = true)
    public void unpackPart() {
        unpack.reset();
        unpack.execute(containerCode, partCodeNS, 10);
        Assert.assertEquals(true, unpack.getResult());
    }

    @TestRailCase(automationId = "8068")
    @Test(priority = 9, enabled = true)
    public void unpackAsset() {
        unpack.execute(containerCode, assetCode);
        Assert.assertEquals(true, unpack.getResult());
    }

    @TestRailCase(automationId = "8069")
    @Test(priority = 10, enabled = true)
    public void unpickPart() {
        unpick.reset();
        unpick.execute(transfer, partCodeNS, bin, 10);
        Assert.assertEquals(true, unpick.getResult());
    }

    @TestRailCase(automationId = "8070")
    @Test(priority = 11, enabled = true)
    public void unpickAsset() {
        unpick.execute(transfer, assetCode, bin);
        Assert.assertEquals(true, unpick.getResult());
    }

    @TestRailCase(automationId = "8071")
    @Test(priority = 12, enabled = true)
    public void pickShipPart() {
        pickShip.reset();
        pickShip.execute(transfer, "1", bin, qty);
        Assert.assertEquals(true, pickShip.getResult());
    }

    @TestRailCase(automationId = "8072")
    @Test(priority = 13, enabled = true)
    public void pickShipAsset() {
        pickShip.execute(transfer, "2", assetCode);
        Assert.assertEquals(true, pickShip.getResult());
    }

    @TestRailCase(automationId = "8073")
    @Test(priority = 14, enabled = true)
    public void internalReceive() {
        internalReceive.reset();
        internalReceive.execute(transfer, containerCode2, "Y", bin);
        Assert.assertEquals(true, internalReceive.getResult());
    }

    @TestRailCase(automationId = "8074")
    @Test(priority = 15, enabled = true)
    public void installEquipmentAsset() {
        installEquipment.reset();
        installEquipment.execute(job, assetCode2, installLocation);
        Assert.assertEquals(true, installEquipment.getResult());
    }

    @TestRailCase(automationId = "8075")
    @Test(priority = 16, enabled = true)
    public void installEquipmentParts() {
        status = "ON HAND";
        installEquipment.execute(job, partCodeNS, 1, installLocation, status);
        Assert.assertEquals(true, installEquipment.getResult());
    }

    @TestRailCase(automationId = "8077")
    @Test(priority = 17, enabled = true)
    public void returnEquipmentParts() {
        status = "ON HAND";
        returnEquipment.reset();
        returnEquipment.execute(job, partCodeNS, 1, returnLocation, "DEFECTIVE");
        Assert.assertEquals(true, returnEquipment.getResult());
    }

    @TestRailCase(automationId = "8076")
    @Test(priority = 18, enabled = true)
    public void returnEquipmentAsset() {
        System.out.println("ASSET IS: " + assetCode2);
        //returnEquipment.execute(job, assetCode2, returnLocation, "DEFECTIVE");
        Assert.assertEquals(true, returnEquipment.getResult());
    }
}
