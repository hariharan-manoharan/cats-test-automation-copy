package net.fulcrum.Routines.Android.XO.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.XO.*;
import org.testng.Assert;
import org.testng.annotations.*;
import net.fulcrum.testrail.annotations.*;

public class StockEquipmentTest extends MainTest {

    private StockEquipment stockEquipment;

    //Error Messages
    private final String invalidToLocationMsg = "DEVICE: To Location INVALID is not a valid selection";
    private final String invalidToStatusMsg = "DEVICE: To Status INVALID is not a valid selection";
    private final String invalidToBinMsg = "DEVICE: To Bin INVALID is not a valid selection";
    private final String invalidItemMfgPartMsg = "DEVICE: Item/Mfg. Part # INVALID is not a valid selection";
    private final String invalidAssetMsg = "DEVICE: Asset Code INVALID is not a valid selection";
    private final String invalidBarcodeTypeMsg = "DEVICE: Barcode Type INVALID is not a valid selection";
    private final String invalidSerialMsg = "DEVICE: Serial # INVALID is not a valid selection";
    private final String invalidConditionMsg = "DEVICE: Condition INVALID is not a valid selection";
    private final String invalidQuantityMsg = "DEVICE: Quantity -1 is not a valid selection";


    private final String partCodeS = "00038002";
    private final String spareJob = "XO0000002957931";
    private final String partCodeNS = "00031484";
    private final String stockFromLocation = "ABE-WAREHOUSE";
    private final String transferLocation = "PDX-WAREHOUSE";
    private String condition = "Good";

    private String barcodeType = "XO ITEM NUMBER";
    private String putawayType = "MOVE";
    private String bin = "ENG";
    private String triageAsset = "XO00046601";
    private String status = "CONFIGURATION";
    private String toStatus;
    private String fromStatus;
    private String newAssetCode;
    private int qty;
    private String assetCode;
    private String containerCode;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        stockEquipment = new StockEquipment(mobilityDriver, oracleDriver, routines);
        qty = mobilityDriver.getRandomInt(100);
        assetCode = mobilityDriver.getNewAssetCode();
    }

    @TestRailCase(automationId = "800000")
    @Test(priority = 1, enabled = true)
    public void toLocationRequired() {
        stockEquipment.selectRoutine();
        stockEquipment.testRequiredField("To Location");
        stockEquipment.clickOk();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800001")
    @Test(priority = 2, enabled = true)
    public void toLocationInvalid() {
        stockEquipment.toLocation(true, "INVALID");
        stockEquipment.verifyMessage(invalidToLocationMsg);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800002")
    @Test(priority = 3, enabled = true)
    public void toLocationValid() {
        stockEquipment.toLocation(true, stockFromLocation);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800003")
    @Test(priority = 4, enabled = true)
    public void toStatusRequired() {
        
        stockEquipment.testRequiredField("To Status");
        stockEquipment.clickOk();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800004")
    @Test(priority = 5, enabled = true)
    public void toStatusInvalid() {
        stockEquipment.toStatus(true, "INVALID");
        stockEquipment.verifyMessage(invalidToStatusMsg);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800005")
    @Test(priority = 6, enabled = true)
    public void toStatusValid() {
        stockEquipment.toStatus(true, toStatus);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800006")
    @Test(priority = 7, enabled = true)
    public void toBinRequired() {
        
        stockEquipment.testRequiredField("To Bin");
        stockEquipment.clickOk();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800007")
    @Test(priority = 8, enabled = true)
    public void toBinInvalid() {
        stockEquipment.toBin(true, "INVALID");
        stockEquipment.verifyMessage(invalidToBinMsg);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800008")
    @Test(priority = 9, enabled = true)
    public void toBinValid() {
        stockEquipment.toBin(true, bin);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800009")
    @Test(priority = 10, enabled = true)
    public void itemMfgPartRequired() {
        
        stockEquipment.testRequiredField("Item/Mfg. Part #");
        stockEquipment.clickOk();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800010")
    @Test(priority = 11, enabled = true)
    public void itemMfgPartInvalid() {
        stockEquipment.itemMfgPart(true, "INVALID");
        stockEquipment.verifyMessage(invalidItemMfgPartMsg);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800011")
    @Test(priority = 12, enabled = true)
    public void itemMfgPartValid() {
        stockEquipment.itemMfgPart(true, partCodeNS);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800012")
    @Test(priority = 13, enabled = true)
    public void barcodeTypeInvalid() {
        stockEquipment.barcodeType(false, "INVALID");
        stockEquipment.verifyMessage(invalidBarcodeTypeMsg);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800013")
    @Test(priority = 14, enabled = true)
    public void barcodeTypeValid() {
        stockEquipment.barcodeType(false, barcodeType);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800014")
    @Test(priority = 15, enabled = true)
    public void quantityRequired() {
        stockEquipment.testRequiredField("Quantity");
        stockEquipment.clickOk();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800016")
    @Test(priority = 16, enabled = true)
    public void quantityInvalid() {
        stockEquipment.quantity(true, -1);
        stockEquipment.verifyMessage(invalidQuantityMsg);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800017")
    @Test(priority = 17, enabled = true)
    public void quantityValid() {
        stockEquipment.quantity(true, 10);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800018")
    @Test(priority = 18, enabled = true)
    public void conditionRequired() {
        
        stockEquipment.testRequiredField("Condition");
        stockEquipment.clickOk();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800019")
    @Test(priority = 19, enabled = true)
    public void conditionInvalid() {
        stockEquipment.condition(true, "INVALID");
        stockEquipment.verifyMessage(invalidConditionMsg);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800020")
    @Test(priority = 20, enabled = true)
    public void conditionValid() {
        stockEquipment.condition(true, condition);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800021")
    @Test(priority = 21, enabled = true)
    public void notesRequired() {
        
        stockEquipment.testRequiredField("Notes");
        stockEquipment.clickOk();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800022")
    @Test(priority = 22, enabled = true)
    public void enterNotes() {
        stockEquipment.notes(true, "CTA: Stock Equipment");
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800023")
    @Test(priority = 23, enabled = true)
    public void verifyLoopfield() {
        stockEquipment.verifyLoopfield(true, "Item/Mfg. Part #");
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800024")
    @Test(priority = 24, enabled = true)
    public void assetRequired() {
        
        stockEquipment.testRequiredField("Item/Mfg. Part #");
        stockEquipment.clickOk();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800025")
    @Test(priority = 25, enabled = true)
    public void assetInvalid() {
        stockEquipment.assetCode(true, "INVALID");
        stockEquipment.verifyMessage(invalidAssetMsg);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800026")
    @Test(priority = 26, enabled = true)
    public void assetValid() {
        stockEquipment.assetCode(true, assetCode);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800027")
    @Test(priority = 27, enabled = true)
    public void serialRequired() {
        stockEquipment.testRequiredField("Item/Mfg. Part #");
        stockEquipment.clickOk();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800028")
    @Test(priority = 28, enabled = true)
    public void serialInvalid() {
        stockEquipment.serial(true, "INVALID");
        stockEquipment.verifyMessage(invalidSerialMsg);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800029")
    @Test(priority = 29, enabled = true)
    public void serialValid() {
        stockEquipment.serial(true, "S" + assetCode);
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }

    @TestRailCase(automationId = "800030")
    @Test(priority = 30, enabled = true)
    public void completeAssetTransaction() {
        stockEquipment.condition(true, condition);
        stockEquipment.nextField();
        stockEquipment.notes(true, "CTA: Stock Equipment");
        stockEquipment.nextField();
        Assert.assertEquals(stockEquipment.getResult(), true);
    }
}
