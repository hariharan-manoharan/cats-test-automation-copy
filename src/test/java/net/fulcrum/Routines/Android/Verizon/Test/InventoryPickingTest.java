package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestRailListener.class)
public class InventoryPickingTest extends VerizonTest {

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
    private final String invalidMR = "DEVICE: Materials Request # INVALID MR is not a valid selection.";
    private final String invalidGeneratePickList = "DEVICE: Generate Pick List? INVALID is not a valid selection.";
    private final String invalidAssetCode = "ASSET CODE ENTERED NOT FOUND FOR A VALID PART ON THE MATERIALS REQUEST #.";
    private final String invalidBarcode = "DEVICE: Barcode INVALID BARCODE is not a valid selection.";
    private final String qtyToPickInvalid = "DEVICE: Quantity To Pick is required.";


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
    public void inventoryPick0002() {
        lineNumber = "3";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode3, partCodeSO, 5);
    }

    //Pick SO Item
    @TestRailCase(automationId = "2001")
    @Test(priority = 53, enabled = true)
    public void inventoryPick0003() {
        lineNumber = "8";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode4, partCodeSO, 2);
    }

    //Pick LN Item
    @TestRailCase(automationId = "2002")
    @Test(priority = 103, enabled = true)
    public void inventoryPick0004 () {
        lineNumber = "5";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode, partCodeLN, 10);
    }

    //Pick SU Item
    @TestRailCase(automationId = "2003")
    @Test(priority = 104, enabled = true)
    public void inventoryPick0005 () {
        lineNumber = "4";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode, partCodeSU, 5);
    }


    //Regression Inventory Picking

    @TestRailCase(automationId = "2004")
    @Test(priority = 112, enabled = true)
    public void verifyRoutineTitleHeader() {
        inventoryPicking.reset();
        inventoryPicking.verifyRoutineHeader();
        inventoryPicking.verifyRoutineTitle("Inventory Picking");
    }

    @TestRailCase(automationId = "2005")
    @Test(priority = 113, enabled = true)
    public void verifyMaterialsRequestRequired() {
        inventoryPicking.testRequiredField("Materials Request #");
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(automationId = "2006")
    @Test(priority = 114, enabled = true)
    public void verifyMaterialsRequestInvalid() {
        inventoryPicking.materialsRequest(true, "INVALID MR");
        inventoryPicking.nextField();
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(automationId = "2007")
    @Test(priority = 115, enabled = true)
    public void enterMR() {
        inventoryPicking.materialsRequest(true, mrCode);
        inventoryPicking.nextField();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(automationId = "2008")
    @Test(priority = 116, enabled = true)
    public void verifyGeneratePickListRequired() {
        inventoryPicking.generatePickList(true, " ");
        inventoryPicking.testRequiredField("Generate Pick List?");
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(automationId = "2009")
    @Test(priority = 117, enabled = true)
    public void verifyGeneratePickListInvalid() {
        inventoryPicking.generatePickList(true, "INVALID");
        inventoryPicking.nextField();
        inventoryPicking.verifyMessage(invalidGeneratePickList);
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(automationId = "2010")
    @Test(priority = 118, enabled = true)
    public void enterGeneratePickList2() {
        inventoryPicking.generatePickList(true, "N");
        inventoryPicking.nextField();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Validate To LDC Required
    @TestRailCase(selfReporting = true, automationId = "2011")
    @Test(priority = 119, enabled = true)
    public void verifyToLDCRequired() {
        inventoryPicking.testRequiredField("To LDC");
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Validate Invalid LDC
    @TestRailCase(selfReporting = true, automationId = "2012")
    @Test(priority = 120, enabled = true)
    public void verifyToLDCInvalid() {
        inventoryPicking.toLDC(true, "INVALID LDC");
        inventoryPicking.nextField();
        inventoryPicking.verifyMessage(invalidToLDC);
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Enter valid LDC
    @TestRailCase(selfReporting = true, automationId = "2013")
    @Test(priority = 121, enabled = true)
    public void enterToLDC() {
        inventoryPicking.toLDC(true, stagingLDC);
        inventoryPicking.clickValidationFile();
        //inventoryPicking.enterValidationFileSearch(toLDC);
        inventoryPicking.clickLookupValue(stagingLDC);
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Verify Location Description is not clickable
    @TestRailCase(selfReporting = true, automationId = "2014")
    @Test(priority = 122, enabled = true, dependsOnMethods = "enterToLDC")
    public void locationDescriptionNonEditable() {
        inventoryPicking.clickPreviousRoutineDetail("Location Description");
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Validate Location Description
    @TestRailCase(selfReporting = true, automationId = "2015")
    @Test(priority = 123, enabled = true, dependsOnMethods = "locationDescriptionNonEditable")
    public void verifyLocationDescription() {;
        String locationDescription = oracleDriver.getLDCDescription(stagingLDC);
        inventoryPicking.verifyRoutineValue(locationDescription);
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Validate Populated BU
    @TestRailCase(selfReporting = true, automationId = "2016")
    @Test(priority = 124, enabled = true, dependsOnMethods = "verifyLocationDescription")
    public void validatePopulatedBu() {
        toLocationBU = oracleDriver.getLocationBU(stagingLDC);
        inventoryPicking.verifyCurrentRoutineValue(toLocationBU);
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Validate To Location BU Required
    @TestRailCase(selfReporting = true, automationId = "2017")
    @Test(priority = 125, enabled = true, dependsOnMethods = "validatePopulatedBu")
    public void verifyToLocationBURequired() {
        mobilityDriver.clearField(1);
        inventoryPicking.testRequiredField("To Location BU");
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Enter invalid To Location BU
    @TestRailCase(selfReporting = true, automationId = "2018")
    @Test(priority = 126, enabled = true, dependsOnMethods = "verifyToLocationBURequired")
    public void verifyToLocationBUInvalid() {
        inventoryPicking.toLocationBU(true, "INVALID BU");
        inventoryPicking.nextField();
        inventoryPicking.verifyMessage(invalidToBU);
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Enter valid BU
    @TestRailCase(selfReporting = true, automationId = "2019")
    @Test(priority = 127, enabled = true, dependsOnMethods = "verifyToLocationBUInvalid")
    public void enterBu() {
        inventoryPicking.toLocationBU(true, toLocationBU);
        inventoryPicking.clickValidationFile();
        inventoryPicking.clickLookupValue(toLocationBU);
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Enter container label
    @TestRailCase(selfReporting = true, automationId = "2048")
    @Test(priority = 128, enabled = true, dependsOnMethods = "enterBu")
    public void enterPickToContainerLabel() {
        inventoryPicking.pickToContainerLabel(containerCode);
        inventoryPicking.nextField();
        inventoryPicking.nextField();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Validate Barcode Required
    @TestRailCase(selfReporting = true, automationId = "2020")
    @Test(priority = 129, enabled = true, dependsOnMethods = "enterPickToContainerLabel")
    public void validateBarcodeRequired() {
        inventoryPicking.testRequiredField("Barcode");
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }


    @TestRailCase(selfReporting = true, automationId = "2021")
    @Test(priority = 130, enabled = true, dependsOnMethods = "validateBarcodeRequired")
    public void validateBarcodeInvalid() {
        inventoryPicking.barcode(true, "INVALID BARCODE");
        inventoryPicking.nextField();
        inventoryPicking.verifyMessage(invalidBarcode);
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2022")
    @Test(priority = 131, enabled = true, dependsOnMethods = "validateBarcodeInvalid")
    public void enterBarcodeSerialized() {
        inventoryPicking.barcode(true, partCodeSerialized);
        inventoryPicking.nextField();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Validate required Asset Code
    @TestRailCase(selfReporting = true, automationId = "2023")
    @Test(priority = 132, enabled = true, dependsOnMethods = "enterBarcodeSerialized")
    public void verifyAssetCodeRequired() {
        inventoryPicking.testRequiredField("Asset Code");
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Enter invalid Asset Code
    @TestRailCase(selfReporting = true, automationId = "2024")
    @Test(priority = 133, enabled = true, dependsOnMethods = "verifyAssetCodeRequired")
    public void verifyAssetCodeInvalid() {
        assetCode = "INVALID ASSETCODE";
        inventoryPicking.assetCode(true, assetCode);
        inventoryPicking.nextField();
        inventoryPicking.verifyMessage(invalidAssetCode);
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Enter valid Asset Code
    @TestRailCase(selfReporting = true, automationId = "2025")
    @Test(priority = 134, enabled = true, dependsOnMethods = "verifyAssetCodeInvalid")
    public void enterAssetCode() {
        inventoryPicking.assetCode(true, assetCode3);
        inventoryPicking.nextField();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2026")
    @Test(priority = 135, enabled = true, dependsOnMethods = "enterAssetCode")
    public void enterGeneratePackingSlip() {
        inventoryPicking.generatePackingSlip("N");
        inventoryPicking.nextField();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Validate enter Notes
    @TestRailCase(selfReporting = true, automationId = "2027")
    @Test(priority = 136, enabled = true, dependsOnMethods = "enterGeneratePackingSlip")
    public void enterNotes() {
        inventoryPicking.notes(false, "ENTER NOTES");
        inventoryPicking.nextField();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Verify loop field for Asset
    @TestRailCase(selfReporting = true, automationId = "2028")
    @Test(priority = 137, enabled = true, dependsOnMethods = "enterNotes")
    public void verifyLoopfieldAssets() {
        inventoryPicking.verifyLoopfield(true, "Barcode");
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2029")
    @Test(priority = 138, enabled = true, dependsOnMethods = "verifyLoopfieldAssets")
    public void enterBarcodeNonSerialized() {
        inventoryPicking.barcode(true, partCodeSO);
        inventoryPicking.nextField();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2030")
    @Test(priority = 139, enabled = true, dependsOnMethods = "enterBarcodeNonSerialized")
    public void verifyLineNumberRequired() {
        inventoryPicking.testRequiredField("Line Number");
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2031")
    @Test(priority = 140, enabled = false, dependsOnMethods = "verifyLineNumberRequired")
    public void verifyLineNumberInvalid() {
        inventoryPicking.lineNumber(true, "INVALID LINENUMBER");
        inventoryPicking.nextField();
        inventoryPicking.verifyMessage(qtyToPickInvalid);
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2032")
    @Test(priority = 141, enabled = true, dependsOnMethods = "verifyLineNumberRequired")
    public void enterLineNumber() {
        lineNumber = "8";
        inventoryPicking.lineNumber(true, lineNumber);
        inventoryPicking.nextField();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2033")
    @Test(priority = 142, enabled = true, dependsOnMethods = "enterLineNumber")
    public void verifyQuantityToPickRequired() {
        mobilityDriver.clearField(1);
        inventoryPicking.testRequiredField("Quantity To Pick");
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2034")
    @Test(priority = 143, enabled = true, dependsOnMethods = "verifyQuantityToPickRequired")
    public void verifyQuantityToPickInvalid() {
        inventoryPicking.quantityToPickLabel(0);
        inventoryPicking.nextField();
        inventoryPicking.nextField();
        inventoryPicking.fromLDC(true, receiveLDC);
        inventoryPicking.nextField();
        inventoryPicking.notes(false, "Notes");
        inventoryPicking.nextField();
        inventoryPicking.verifyMessage(invalidQuantity);
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2035")
    @Test(priority = 144, enabled = true, dependsOnMethods = "verifyQuantityToPickRequired")
    public void enterQuantityToPick() {
        inventoryPicking.clickPreviousRoutineDetail("Quantity To Pick");
        inventoryPicking.quantityToPickLabel(3);
        inventoryPicking.nextField();
        inventoryPicking.nextField();
    }

    //Verify From LDC required
    @TestRailCase(automationId = "2047")
    @Test(priority = 145, enabled = true, dependsOnMethods = "enterQuantityToPick")
    public void verifyFromLDCRequired() {
        mobilityDriver.clearField(1);
        inventoryPicking.testRequiredField("From LDC");
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Verify Invalid From LDC
    @TestRailCase(automationId = "2036")
    @Test(priority = 146, enabled = true, dependsOnMethods = "verifyFromLDCRequired")
    public void verifyFromLDCInvalid() {
        inventoryPicking.fromLDC(true, "INVALID LDC");
        inventoryPicking.nextField();
        inventoryPicking.verifyMessage(invalidFromLDC);
        inventoryPicking.clickOk();
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Enter valid From LDC
    @TestRailCase(automationId = "2037")
    @Test(priority = 147, enabled = true, dependsOnMethods = "verifyFromLDCInvalid")
    public void enterFromLDC() {
        inventoryPicking.fromLDC(true, "");
        inventoryPicking.clickValidationFile();
        inventoryPicking.enterValidationFileSearch(receiveLDC);
        inventoryPicking.clickLookupValue(receiveLDC);
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Validate Populated BU
    @TestRailCase(selfReporting = true, automationId = "2040")
    @Test(priority = 148, enabled = true, dependsOnMethods = "enterFromLDC")
    public void validatePopulatedFromBU() {
        fromLocationBU = oracleDriver.getLocationBU(receiveLDC);
        inventoryPicking.verifyRoutineValue(fromLocationBU);
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "2045")
    @Test(priority = 149, enabled = true, dependsOnMethods = "validatePopulatedFromBU")
    public void enterEmptyNotes() {
        inventoryPicking.nextField();
        inventoryPicking.verifyTransaction(false, "INVENTORY_PICK");
        Assert.assertEquals(inventoryPicking.getResult(), true);
    }

    //Verify loop field for Parts
    @TestRailCase(selfReporting = true, automationId = "2046")
    @Test(priority = 156, enabled = true, dependsOnMethods = "enterEmptyNotes")
    public void verifyLoopfieldParts() {
        inventoryPicking.verifyLoopfield(true, "Barcode");
    }

}