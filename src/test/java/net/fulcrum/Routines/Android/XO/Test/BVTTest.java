package net.fulcrum.Routines.Android.XO.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.XO.*;
import org.testng.Assert;
import org.testng.annotations.*;
import net.fulcrum.testrail.annotations.*;

public class BVTTest extends MainTest {

    private StockEquipment stockEquipment;
    private ChangeAssetLabel changeAssetLabel;
    private UpdateAsset updateAsset;
    private AddToContainer addToContainer;
    private RemoveFromContainer removeFromContainer;
    private DestroyContainer destroyContainer;
    private TakeASpare takeASpare;
    private Transfer transfer;
    private PutawayStock putawayStock;
    private DCPutawayStock dcPutawayStock;
    private VehicleCheckIn vehicleCheckIn;
    private VehicleCheckOut vehicleCheckOut;
    private Triage triage;

    private final String partCodeS = "00038002";
    private final String spareJob = "XO0000002957931";
    private final String partCodeNS = "00031484";
    private final String stockFromLocation = "ABE-WAREHOUSE";
    private final String transferLocation = "PDX-WAREHOUSE";
    private final String distributionLocation = "NDO-DISTRIBUTION CENTER";
    private final String putawayBin = "C01-01-A-01";
    private final String vehicle = "1D3HV18P79S819768";

    private String barcodeType = "XO ITEM NUMBER";
    private String putawayType = "MOVE";
    private String bin = "ENG";
    private String triageAsset = "XO00046601";
    private String status;
    private String toStatus;
    private String fromStatus;
    private String newAssetCode;
    private int qty;
    private String assetCode;
    private String containerCode;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        stockEquipment = new StockEquipment(mobilityDriver, oracleDriver, routines);
        updateAsset = new UpdateAsset(mobilityDriver, oracleDriver, routines);
        changeAssetLabel = new ChangeAssetLabel(mobilityDriver, oracleDriver, routines);
        addToContainer = new AddToContainer(mobilityDriver, oracleDriver, routines);
        removeFromContainer = new RemoveFromContainer(mobilityDriver, oracleDriver, routines);
        destroyContainer = new DestroyContainer(mobilityDriver, oracleDriver, routines);
        takeASpare = new TakeASpare(mobilityDriver, oracleDriver, routines);
        transfer = new Transfer(mobilityDriver, oracleDriver, routines);
        putawayStock = new PutawayStock(mobilityDriver, oracleDriver, routines);
        dcPutawayStock = new DCPutawayStock(mobilityDriver, oracleDriver, routines);
        vehicleCheckIn = new VehicleCheckIn(mobilityDriver, oracleDriver, routines);
        vehicleCheckOut = new VehicleCheckOut(mobilityDriver, oracleDriver, routines);
        triage = new Triage(mobilityDriver, oracleDriver, routines);
        qty = mobilityDriver.getRandomInt(100);
        routines.selectRoutine("Stock Equipment");
    }

    @TestRailCase(automationId = "8000")
    @Test(priority = 1, enabled = true)
    public void stockEquipmentPart() {
        status = "CONFIGURATION";
        stockEquipment.execute(stockFromLocation, status, bin, partCodeNS, null, qty);
        Assert.assertEquals(true, stockEquipment.getResult());
    }

    @TestRailCase(automationId = "8001")
    @Test(priority = 2, enabled = true)
    public void stockEquipmentAsset() {
        assetCode = mobilityDriver.getNewAssetCode();
        stockEquipment.execute(stockFromLocation, status, bin, partCodeS, null, assetCode);
        Assert.assertEquals(true, stockEquipment.getResult());
    }

    @TestRailCase(automationId = "8002")
    @Test(priority = 3, enabled = true)
    public void changeAssetLabel0001() {
        changeAssetLabel.reset();
        newAssetCode = "NEW-" + assetCode;
        changeAssetLabel.execute(assetCode, newAssetCode);
        Assert.assertEquals(true, changeAssetLabel.getResult());
    }

    @TestRailCase(automationId = "8025")
    @Test(priority = 4, enabled = true)
    public void updateAsset() {
        updateAsset.reset();
        updateAsset.execute(newAssetCode, "S" + newAssetCode);
        Assert.assertEquals(true, updateAsset.getResult());
    }

    @TestRailCase(automationId = "8015")
    @Test(priority = 5, enabled = true)
    public void transferAsset() {
        transfer.reset();
        transfer.execute(spareJob, transferLocation, newAssetCode);
        Assert.assertEquals(true, transfer.getResult());
    }

    @TestRailCase(automationId = "8016")
    @Test(priority = 6, enabled = true)
    public void transferPart() {
        fromStatus = "CONFIGURATION";
        transfer.execute(spareJob, transferLocation, partCodeNS, qty, stockFromLocation, fromStatus, bin);
        Assert.assertEquals(true, transfer.getResult());
    }

    @TestRailCase(automationId = "8017")
    @Test(priority = 7, enabled = true)
    public void putAwayStockAsset() {
        putawayStock.reset();
        toStatus = "SPARE";
        putawayStock.execute(transferLocation, putawayType, toStatus, bin, newAssetCode);
        Assert.assertEquals(true, putawayStock.getResult());
    }

    @TestRailCase(automationId = "8018")
    @Test(priority = 8, enabled = true)
    public void putAwayStockPart() {
        fromStatus = "IN TRANSIT/SHIPPED";
        putawayStock.execute(transferLocation, putawayType, toStatus, bin, partCodeNS, qty, fromStatus);
        Assert.assertEquals(true, putawayStock.getResult());
    }

    @TestRailCase(automationId = "8011")
    @Test(priority = 9, enabled = true)
    public void takeASpareAsset() {
        takeASpare.reset();
        takeASpare.execute(spareJob, newAssetCode);
        Assert.assertEquals(true, takeASpare.getResult());
    }

    @TestRailCase(automationId = "8012")
    @Test(priority = 10, enabled = true)
    public void takeASparePart() {
        fromStatus = toStatus;
        takeASpare.execute(spareJob, partCodeNS, qty, transferLocation, fromStatus);
        Assert.assertEquals(true, takeASpare.getResult());
    }


    @TestRailCase(automationId = "8007")
    @Test(priority = 11, enabled = true)
    public void addToContainerAsset() {
        addToContainer.reset();
        status = "PENDING SWAP";
        containerCode = "CONTAINER" + mobilityDriver.getRandomInt(100000);
        addToContainer.execute(transferLocation, containerCode, status, newAssetCode);
        Assert.assertEquals(true, addToContainer.getResult());
    }

    @TestRailCase(automationId = "8008")
    @Test(priority = 12, enabled = true)
    public void addToContainerPart() {
        addToContainer.execute(transferLocation, containerCode, status, partCodeNS, qty);
        Assert.assertEquals(true, addToContainer.getResult());
    }

    @TestRailCase(automationId = "8009")
    @Test(priority = 13, enabled = true)
    public void removeFromContainerAsset() {
        removeFromContainer.reset();
        removeFromContainer.execute(transferLocation, containerCode, newAssetCode);
        Assert.assertEquals(true, removeFromContainer.getResult());
    }

    @TestRailCase(automationId = "8011")
    @Test(priority = 14, enabled = true)
    public void removeFromContainerPart() {
        removeFromContainer.execute(transferLocation, containerCode, partCodeNS, 1);
        Assert.assertEquals(true, removeFromContainer.getResult());
    }

    @TestRailCase(automationId = "8019")
    @Test(priority = 15, enabled = true)
    public void destroyContainer0001() {
        destroyContainer.reset();
        destroyContainer.execute(containerCode);
        Assert.assertEquals(true, removeFromContainer.getResult());
    }

    @Test(priority = 16, enabled = true)
    public void stockItems() {
        stockEquipment.reset();
        assetCode2 = mobilityDriver.getNewAssetCode();
        status = "ON HAND";
        stockEquipment.execute(distributionLocation, status, putawayBin, partCodeNS, null, qty);
        stockEquipment.execute(distributionLocation, status, putawayBin, partCodeS, null, assetCode2);
        Assert.assertEquals(true, stockEquipment.getResult());
    }

    @TestRailCase(automationId = "8020")
    @Test(priority = 17, enabled = true)
    public void dcPutawayStockAsset() {
        dcPutawayStock.reset();
        dcPutawayStock.execute(putawayBin, assetCode2);
        Assert.assertEquals(true, dcPutawayStock.getResult());
    }

    @TestRailCase(automationId = "8021")
    @Test(priority = 18, enabled = true)
    public void dcPutawayStockPart() {
        dcPutawayStock.execute(putawayBin, partCodeNS, qty, putawayBin);
        Assert.assertEquals(true, dcPutawayStock.getResult());
    }

    @TestRailCase(automationId = "8022")
    @Test(priority = 19, enabled = true)
    public void vehicleCheckOut() {
        vehicleCheckOut.reset();
        vehicleCheckOut.execute(vehicle);
        Assert.assertEquals(true, vehicleCheckOut.getResult());
    }

    @TestRailCase(automationId = "8023")
    @Test(priority = 20, enabled = true)
    public void vehicleCheckIn() {
        vehicleCheckIn.reset();
        vehicleCheckIn.execute(vehicle);
        Assert.assertEquals(true, vehicleCheckIn.getResult());
    }

    @TestRailCase(automationId = "8024")
    @Test(priority = 21, enabled = true)
    public void triage() {
        triage.reset();
        triage.execute(triageAsset, "No");
        Assert.assertEquals(true, triage.getResult());
    }
}
