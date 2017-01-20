package net.fulcrum.Routines.Android.XO.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.XO.*;
import org.testng.Assert;
import org.testng.annotations.*;
import net.fulcrum.testrail.annotations.*;

public class BVTTest2 extends MainTest {

    private StockEquipment stockEquipment;

    private AssetToRepair assetToRepair;
    private AssetFromRepair assetFromRepair;
    private IncomingInquiry incomingInquiry;
    private AssetInquiry assetInquiry;
    private InventoryLookup inventoryLookup;
    private UnfulfilledTransfers unfulfilledTransfers;
    private RMAReceive rmaReceive;
    private RMAReturn rmaReturn;
    private POReceive poReceive;
    private POCorrect poCorrect;
    private POReceiveASN poReceiveASN;
    private POReturnToVendor poReturnToVendor;
    private AdvanceShippingNotice advanceShippingNotice;

    private final String poCode = "PO12071";
    private final String remedy = "XO0000002957927";
    private final String rma = "00102204";

    private String status;
    private String partCodeS = "00038002";
    private String barcodeType = "XO ITEM NUMBER";
    private String partCodeNS = "00031484";
    private String stockFromLocation = "ABE-WAREHOUSE";
    private String fromLocation = "PDX-WAREHOUSE";
    private String bin = "ENG";
    private String incomingTransfer = "XFER:MAN0002719";
    private String tranferLine = "1";
    private String transferLocation = "SEA-WAREHOUSE";
    private String unfulfilledTransfer = "PLAN-1085-T4637";
    private int qty;
    private String poAsset;
    private String poAsset2;
    private String poAsset3;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        stockEquipment = new StockEquipment(mobilityDriver, oracleDriver, routines);
        assetToRepair = new AssetToRepair(mobilityDriver, oracleDriver, routines);
        assetFromRepair = new AssetFromRepair(mobilityDriver, oracleDriver, routines);
        incomingInquiry = new IncomingInquiry(mobilityDriver, oracleDriver, routines);
        assetInquiry = new AssetInquiry(mobilityDriver, oracleDriver, routines);
        unfulfilledTransfers = new UnfulfilledTransfers(mobilityDriver, oracleDriver, routines);
        inventoryLookup = new InventoryLookup(mobilityDriver, oracleDriver, routines);
        rmaReceive = new RMAReceive(mobilityDriver, oracleDriver, routines);
        rmaReturn = new RMAReturn(mobilityDriver, oracleDriver, routines);
        poReceive = new POReceive(mobilityDriver, oracleDriver, routines);
        poCorrect = new POCorrect(mobilityDriver, oracleDriver, routines);
        poReceiveASN = new POReceiveASN(mobilityDriver, oracleDriver, routines);
        poReturnToVendor = new POReturnToVendor(mobilityDriver, oracleDriver, routines);
        advanceShippingNotice = new AdvanceShippingNotice(mobilityDriver, oracleDriver, routines);

        qty = mobilityDriver.getRandomInt(100);
        routines.selectRoutine("Stock Equipment");

    }

    @Test(priority = 1, enabled = true)
    public void stockEquipment0001() {
        status = "ON HAND";
        assetCode = mobilityDriver.getNewAssetCode();
        stockEquipment.execute(stockFromLocation, status, bin, partCodeS, barcodeType, assetCode);
        Assert.assertEquals(true, stockEquipment.getResult());
    }

    @TestRailCase(automationId = "8032")
    @Test(priority = 8, enabled = true)
    public void assetToRepair0001() {
        assetToRepair.reset();
        String repairLocation = "CISCO";
        String rmaAsset = "RMA" + mobilityDriver.getRandomInt(100000);
        assetToRepair.execute(repairLocation, assetCode, rmaAsset);
        Assert.assertEquals(true, assetToRepair.getResult());
    }

    @TestRailCase(automationId = "8033")
    @Test(priority = 9, enabled = true)
    public void assetFromRepair0001() {
        assetFromRepair.reset();
        status = "On Hand";
        assetFromRepair.execute(transferLocation, assetCode, status, bin);
        Assert.assertEquals(true, assetFromRepair.getResult());
    }

    @TestRailCase(automationId = "8034")
    @Test(priority = 10, enabled = true)
    public void incomingInquiry() {
        incomingInquiry.reset();
        incomingInquiry.execute(transferLocation, null, incomingTransfer, tranferLine);
        Assert.assertEquals(true, incomingInquiry.getResult());
    }

    @TestRailCase(automationId = "8035")
    @Test(priority = 11, enabled = true)
    public void assetInquiry() {
        assetInquiry.reset();
        assetInquiry.executeRoutine(assetCode, "XO CS", "GOOD", "CATSADM CATSADM", "S" + assetCode, "TS1007202");
        Assert.assertEquals(true, assetInquiry.getResult());
    }

    @TestRailCase(automationId = "8036")
    @Test(priority = 12, enabled = true)
    public void inventoryLookup() {
        inventoryLookup.reset();
        inventoryLookup.executeRoutine(partCodeS, "CENTRAL PA", "NDO-DISTRIBUTION CENTER");
        Assert.assertEquals(true, inventoryLookup.getResult());
    }

    @TestRailCase(automationId = "8037")
    @Test(priority = 13, enabled = true)
    public void unfulfilledTransfers() {
        unfulfilledTransfers.reset();
        unfulfilledTransfers.executeRoutine(fromLocation, transferLocation, null, unfulfilledTransfer, tranferLine);
        Assert.assertEquals(true, unfulfilledTransfers.getResult());
    }

    @TestRailCase(automationId = "8038")
    @Test(priority = 15, enabled = true)
    public void rmaReceiveAsset() {
        assetCode2 = mobilityDriver.getNewAssetCode();
        rmaReceive.reset();
        rmaReceive.executeRoutine(fromLocation, assetCode2, partCodeS, rma, remedy);
        Assert.assertEquals(true, rmaReceive.getResult());
    }

    @TestRailCase(automationId = "8039")
    @Test(priority = 16, enabled = true)
    public void rmaReturnAsset() {
        rmaReturn.reset();
        rmaReturn.executeRoutine(assetCode2, rma);
        Assert.assertEquals(true, rmaReturn.getResult());
    }

    @TestRailCase(automationId = "8040")
    @Test(priority = 17, enabled = true)
    public void poReceiveAsset() {
        poAsset = mobilityDriver.getNewAssetCode();
        poReceive.reset();
        poReceive.execute(poCode, partCodeS, "1", transferLocation, bin, poAsset);
        Assert.assertEquals(true, poReceive.getResult());
    }

    @Test(priority = 18, enabled = true)
    public void poReceiveAsset2() {
        poAsset2 = mobilityDriver.getNewAssetCode();
        poReceive.execute(poCode, partCodeS, "1", transferLocation, bin, poAsset2);
        Assert.assertEquals(true, poReceive.getResult());
    }

    @TestRailCase(automationId = "8042")
    @Test(priority = 19, enabled = true)
    public void poReceivePart() {
        poReceive.execute(poCode, partCodeNS, "2", transferLocation, bin, 10);
        Assert.assertEquals(true, poReceive.getResult());
    }

    @TestRailCase(automationId = "8043")
    @Test(priority = 20, enabled = true)
    public void poCorrectAsset() {
        poCorrect.reset();
        poCorrect.execute(poAsset, poCode, "1");
        Assert.assertEquals(true, poCorrect.getResult());
    }

    @TestRailCase(automationId = "8044")
    @Test(priority = 21, enabled = true)
    public void poCorrectPart() {
        status = "On Hand";
        poCorrect.execute(partCodeNS, poCode, "2", 5, transferLocation, status, bin);
        Assert.assertEquals(true, poCorrect.getResult());
    }

    @TestRailCase(automationId = "8045")
    @Test(priority = 22, enabled = true)
    public void poReturnAsset() {
        poReturnToVendor.reset();
        poReturnToVendor.execute("INVENTORY", poAsset2, poCode, "1", rma);
        Assert.assertEquals(true, poReturnToVendor.getResult());
    }

    @TestRailCase(automationId = "8046")
    @Test(priority = 23, enabled = true)
    public void poReturnPart() {
        poReturnToVendor.execute("INVENTORY", partCodeNS, poCode , "2", rma, 5, transferLocation, status, bin);
        Assert.assertEquals(true, poReturnToVendor.getResult());
    }

    @TestRailCase(automationId = "8047")
    @Test(priority = 24, enabled = true)
    public void advanceShippingNoticeAsset() {
        advanceShippingNotice.reset();
        poAsset3 = mobilityDriver.getNewAssetCode();
        advanceShippingNotice.execute(poCode, "1", poAsset3);
        Assert.assertEquals(true, advanceShippingNotice.getResult());
    }

    @TestRailCase(automationId = "8048")
    @Test(priority = 25, enabled = true)
    public void advanceShippingNoticePart() {
        advanceShippingNotice.reset();
        advanceShippingNotice.execute(poCode, "2", 8);
        Assert.assertEquals(true, advanceShippingNotice.getResult());
    }

    @TestRailCase(automationId = "8049")
    @Test(priority = 26, enabled = true)
    public void poReceiveASNAsset() {
        poReceiveASN.reset();
        poReceiveASN.execute(poCode, transferLocation, bin, poAsset3);
        Assert.assertEquals(true, poReceiveASN.getResult());
    }

    @TestRailCase(automationId = "8050")
    @Test(priority = 27, enabled = true)
    public void poReceiveASNPart() {
        poReceiveASN.execute(poCode, transferLocation, bin, partCodeNS, 8);
        Assert.assertEquals(true, poReceiveASN.getResult());
    }

}
