package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestRailListener.class)
public class InventoryShippingTest extends VerizonTest {

    private InventoryShipping inventoryShipping;
    private InventoryPicking inventoryPicking;

    private String mrCode;
    private String shipLDC;
    private String receiveLDC;
    private String stagingLDC;
    private String assetCode3;
    private String projectCode;
    private String projectCode2;
    private String poCode2;
    private String containerCode3;
    private String containerCode4;

    //Error Messages
    private final String invalidBarcode = "DEVICE: Barcode INVALID BARCODE is not a valid selection.";
    private final String invalidPackingSlip = "DEVICE: Generate Packing Slip? INVALID is not a valid selection.";
    private final String invalidShipLocationBu = "DEVICE: Ship To Location BU INVALID BU is not a valid selection.";
    private final String invalidShipToLDC = "DEVICE: Ship To LDC INVALID is not a valid selection.";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        stagingLDC = "GNS103383";
        receiveLDC = "CNS103383";
        shipLDC = "TN0100091";
        partCodeSerialized = "00-4822-1";
        partCodeSerialized2 = "004871";
        partCodeLN = "0-U6-16DS1-HSB";
        partCodeSU = "01-61084003";
        partCodeSO = "#18/2";

        inventoryPicking = new InventoryPicking(mobilityDriver, oracleDriver, routines);
        inventoryShipping = new InventoryShipping(mobilityDriver, oracleDriver, routines);

        mrCode = oracleDriver.stageMaterialsRequest("103383");

        containerCode = "VZC-" + mrCode + "C1";
        containerCode2 = "VZC-" + mrCode + "C2";
        containerCode3 = "VZC-" + mrCode + "C3";
        containerCode4 = "VZC-" + mrCode + "C4";
        assetCode = mrCode + "A1";
        assetCode2 = mrCode + "A2";
        assetCode3 = mrCode + "A3";
        projectCode = "PRO-" + mrCode + "-1";
        projectCode2 = "PRO-" + mrCode + "-2";
        poCode = "PO-" + mrCode + "-1";
        poCode2 = "PO-" + mrCode + "-2";
    }

    //Pick LS Item
    @TestRailCase(automationId = "2000")
    @Test(priority = 50, enabled = true)
    public void inventoryPick0001() {
        routines.selectRoutine("Inventory Picking");
        inventoryPicking.execute(false, mrCode, stagingLDC, containerCode, partCodeSerialized, assetCode);
    }

    //Pick SO Item
    @TestRailCase(automationId = "2001")
    @Test(priority = 52, enabled = true)
    public void inventoryPick0003() {
        lineNumber = "3";
        inventoryPicking.reset();
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode3, partCodeSO, 5);
    }

    //Pick LN Item
    @TestRailCase(automationId = "2002")
    @Test(priority = 54, enabled = true)
    public void inventoryPick0005() {
        lineNumber = "5";
        inventoryPicking.reset();
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode4, partCodeLN, 10);
    }


    //Pick LS Item
    @Test(priority = 60, enabled = true)
    public void inventoryPick0007() {
        inventoryPicking.reset();
        inventoryPicking.execute(false, mrCode, stagingLDC, containerCode2, partCodeSerialized, assetCode2);
    }


    //Ship Container with one Asset
    @TestRailCase(automationId = "9001")
    @Test(priority = 61, enabled = true)
    public void inventoryShip0001() {
        inventoryShipping.reset();
        inventoryShipping.execute(false, mrCode, shipLDC, containerCode2);
    }

    //Ship Container with SO, SU, LN items
    @TestRailCase(automationId = "9002")
    @Test(priority = 62, enabled = true)
    public void inventoryShip0002() {
        inventoryShipping.execute(false, mrCode, shipLDC, containerCode);
    }

    //Regression for Inventory Shipping
    @TestRailCase(automationId = "9003")
    @Test(priority = 300, enabled = true)
    public void verifyRoutineTitleHeaderShip() {
        inventoryShipping.reset();
        inventoryShipping.verifyRoutineHeader();
        inventoryShipping.verifyRoutineTitle("Inventory Shipping");
    }

    @TestRailCase(automationId = "9005")
    @Test(priority = 301, enabled = true, dependsOnMethods = "verifyRoutineTitleHeaderShip")
    public void verifyMaterialsRequestRequiredShip() {
        inventoryShipping.testRequiredField("Materials Request #");
        inventoryShipping.clickOk();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    @TestRailCase(automationId = "9006")
    @Test(priority = 302, enabled = true, dependsOnMethods = "verifyRoutineTitleHeaderShip")
    public void verifyMaterialsRequestInvalidShip() {
        inventoryShipping.materialsRequest(true, "INVALID MR");
        inventoryShipping.nextField();
        inventoryShipping.clickOk();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    @TestRailCase(automationId = "9007")
    @Test(priority = 303, enabled = true, dependsOnMethods = "verifyRoutineTitleHeaderShip")
    public void enterMRShip() {
        inventoryShipping.materialsRequest(true, mrCode);
        inventoryShipping.nextField();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }


    //Validate populated Ship To LDC
    @TestRailCase(selfReporting = true, automationId = "9011")
    @Test(priority = 304, enabled = true, dependsOnMethods = "enterMRShip")
    public void verifyShipToLDCPopulated() {
        inventoryShipping.verifyCurrentRoutineValue(shipLDC);
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Validate Ship To LDC Required
    @TestRailCase(selfReporting = true, automationId = "9013")
    @Test(priority = 305, enabled = true, dependsOnMethods = "enterMRShip")
    public void verifyShipToLDCRequired() {
        mobilityDriver.clearField(1);
        inventoryShipping.testRequiredField("Ship To LDC");
        inventoryShipping.clickOk();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Validate Invalid LDC
    @TestRailCase(selfReporting = true, automationId = "9012")
    @Test(priority = 306, enabled = true, dependsOnMethods = "enterMRShip")
    public void verifyShipToLDCInvalid() {
        inventoryShipping.shipToLDC("INVALID");
        inventoryShipping.nextField();
        inventoryShipping.verifyMessage(invalidShipToLDC);
        inventoryShipping.clickOk();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Enter valid LDC
    @TestRailCase(selfReporting = true, automationId = "9014")
    @Test(priority = 307, enabled = true, dependsOnMethods = "enterMRShip")
    public void enterShipToLDC() {
        inventoryShipping.shipToLDC(shipLDC);
        inventoryShipping.clickValidationFile();
        //inventoryShipping.enterValidationFileSearch(toLDC);
        inventoryShipping.clickLookupValue(shipLDC);
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Verify Location Description is not clickable
    @TestRailCase(selfReporting = true, automationId = "9016")
    @Test(priority = 308, enabled = true, dependsOnMethods = "enterShipToLDC")
    public void shipLocationDescriptionNonEditable() {
        inventoryShipping.clickPreviousRoutineDetail("Ship To Location Description");
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }


    //Validate Location Description
    @TestRailCase(selfReporting = true, automationId = "9015")
    @Test(priority = 309, enabled = true, dependsOnMethods = "shipLocationDescriptionNonEditable")
    public void verifyShipLocationDescription() {
        String locationDescription = oracleDriver.getLDCDescription(shipLDC);
        inventoryShipping.verifyRoutineValue(locationDescription);
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Validate Populated BU
    @TestRailCase(selfReporting = true, automationId = "9017")
    @Test(priority = 310, enabled = true, dependsOnMethods = "verifyShipLocationDescription")
    public void validateShipPopulatedBu() {
        toLocationBU = oracleDriver.getLocationBU(shipLDC);
        inventoryShipping.verifyCurrentRoutineValue(toLocationBU);
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Validate To Location BU Required
    @TestRailCase(selfReporting = true, automationId = "9018")
    @Test(priority = 311, enabled = true, dependsOnMethods = "validateShipPopulatedBu")
    public void verifyShipToLocationBURequired() {
        mobilityDriver.clearField(1);
        inventoryShipping.testRequiredField("Ship To Location BU");
        inventoryShipping.clickOk();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Enter invalid To Location BU
    @TestRailCase(selfReporting = true, automationId = "9019")
    @Test(priority = 312, enabled = true, dependsOnMethods = "verifyShipToLocationBURequired")
    public void verifyShipToLocationBUInvalid() {
        inventoryShipping.shipToLocationBU("INVALID BU");
        inventoryShipping.nextField();
        inventoryShipping.verifyMessage(invalidShipLocationBu);
        inventoryShipping.clickOk();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Enter valid BU
    @TestRailCase(selfReporting = true, automationId = "2019")
    @Test(priority = 313, enabled = true, dependsOnMethods = "verifyShipToLocationBUInvalid")
    public void enterShipBu() {
        inventoryShipping.shipToLocationBU(toLocationBU);
        inventoryShipping.nextField();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Enter blank carrier
    @TestRailCase(selfReporting = true, automationId = "9020")
    @Test(priority = 314, enabled = true, dependsOnMethods = "verifyShipToLocationBUInvalid")
    public void emptyCarrier() {
        inventoryShipping.nextField();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Enter blank carrier
    @TestRailCase(selfReporting = true, automationId = "9021")
    @Test(priority = 315, enabled = true, dependsOnMethods = "verifyShipToLocationBUInvalid")
    public void emptyTracking() {
        inventoryShipping.nextField();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    //Validate Barcode Required
    @TestRailCase(selfReporting = true, automationId = "9022")
    @Test(priority = 316, enabled = true, dependsOnMethods = "enterShipBu")
    public void validateBarcodeRequiredShip() {
        inventoryShipping.testRequiredField("Barcode");
        inventoryShipping.clickOk();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }


    @TestRailCase(selfReporting = true, automationId = "9023")
    @Test(priority = 317, enabled = true, dependsOnMethods = "enterShipBu")
    public void validateBarcodeInvalidShip() {
        inventoryShipping.barcode(true, "INVALID BARCODE");
        inventoryShipping.nextField();
        inventoryShipping.verifyMessage(invalidBarcode);
        inventoryShipping.clickOk();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "9024")
    @Test(priority = 318, enabled = true, dependsOnMethods = "enterShipBu")
    public void enterBarcode() {
        inventoryShipping.barcode(true, containerCode3);
        inventoryShipping.nextField();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }


    @TestRailCase(selfReporting = true, automationId = "9025")
    @Test(priority = 319, enabled = true, dependsOnMethods = "enterBarcode")
    public void enterGeneratePackingSlipInvalid() {
        inventoryShipping.generatePackingSlip("INVALID");
        inventoryShipping.nextField();
        inventoryShipping.verifyMessage(invalidPackingSlip);
        inventoryShipping.clickOk();
        inventoryShipping.generatePackingSlip("Y");
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "9026")
    @Test(priority = 320, enabled = true, dependsOnMethods = "enterBarcode")
    public void emptyNotes() {
        inventoryShipping.nextField();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "9027")
    @Test(priority = 321, enabled = true, dependsOnMethods = "emptyNotes")
    public void verifyLoopfield() {
        inventoryShipping.verifyLoopfield(true, "Barcode");
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }


    @TestRailCase(selfReporting = true, automationId = "9028")
    @Test(priority = 322, enabled = true, dependsOnMethods = "verifyLoopfield")
    public void enterCarrier(){
        inventoryShipping.clickPreviousRoutineDetail("Carrier/Consignee");
        inventoryShipping.carrier(false, "USPS");
        inventoryShipping.nextField();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "9029")
    @Test(priority = 323, enabled = true, dependsOnMethods = "enterCarrier")
    public void enterTracking(){
        inventoryShipping.trackingNumber(false, "12345");
        inventoryShipping.nextField();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "9030")
    @Test(priority = 324, enabled = true, dependsOnMethods = "enterTracking")
    public void enterNotesShip(){
        inventoryShipping.barcode(true, containerCode4);
        inventoryShipping.nextField();
        inventoryShipping.nextField();
        inventoryShipping.notes(false, "NOTES");
        inventoryShipping.nextField();
        Assert.assertEquals(inventoryShipping.getResult(), true);
    }

}