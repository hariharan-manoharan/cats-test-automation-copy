package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestRailListener.class)
public class InventoryUnpickTest extends VerizonTest {

    private InventoryShipping inventoryShipping;
    private InventoryUnpick inventoryUnpick;
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
    private final String invalidUnpickItem = "DEVICE: Select Unpick Item INVALID PART is not a valid selection.";
    private final String invalidUnpickMRItem = "DEVICE: Select Unpick Item 44WR8 is not a valid selection.";
    private final String invalidQty = "422: TRIGGER: PART TRANSACTION REQUIRES A QUANTITY.";
    protected final String invalidToLDCUnpick = "DEVICE: To LDC TNS103383 is not a valid selection.";
    private final String invalidLineNumber = "DEVICE: Line Number INVALID is not a valid selection.";



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
        inventoryUnpick = new InventoryUnpick(mobilityDriver, oracleDriver, routines);

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
        //inventoryPicking.execute(false, mrCode, stagingLDC, containerCode, partCodeSerialized, assetCode);
    }

    //Pick LS Item
    @TestRailCase(automationId = "2000")
    @Test(priority = 51, enabled = true)
    public void inventoryPick0002() {
        inventoryPicking.execute(false, mrCode, stagingLDC, containerCode3, partCodeSerialized, assetCode3);
    }

    //Pick SO Item
    @TestRailCase(automationId = "2001")
    @Test(priority = 52, enabled = true)
    public void inventoryPick0003() {
        lineNumber = "3";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode3, partCodeSO, 5);
    }

    //Pick SO Item
    @TestRailCase(automationId = "2001")
    @Test(priority = 53, enabled = true)
    public void inventoryPick0004() {
        lineNumber = "8";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode4, partCodeSO, 2);
    }

    //Pick LN Item
    @TestRailCase(automationId = "2002")
    @Test(priority = 54, enabled = true)
    public void inventoryPick0005() {
        lineNumber = "5";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode, partCodeLN, 10);
    }

    //Pick SU Item
    @TestRailCase(automationId = "2003")
    @Test(priority = 55, enabled = true)
    public void inventoryPick0006() {
        lineNumber = "4";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode, partCodeSU, 5);
    }


    //Pick LS Item
    @Test(priority = 60, enabled = true)
    public void inventoryPick0007() {
        inventoryPicking.reset();
        inventoryPicking.execute(false, mrCode, stagingLDC, containerCode2, partCodeSerialized, assetCode2);
    }

    //Regression Inventory Unpick
    @TestRailCase(automationId = "8000")
    @Test(priority = 200, enabled = true)
    public void verifyRoutineTitleHeaderUnpick() {
        inventoryUnpick.reset();
        inventoryUnpick.verifyRoutineHeader();
        inventoryUnpick.verifyRoutineTitle("Inventory Unpick");
    }

    @TestRailCase(automationId = "8001")
    @Test(priority = 201, enabled = true)
    public void verifyMaterialsRequestRequiredUnpick() {
        inventoryUnpick.testRequiredField("Materials Request #");
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8002")
    @Test(priority = 202, enabled = true)
    public void verifyMaterialsRequestInvalidUnpick() {
        inventoryUnpick.materialsRequest(true, "INVALID MR");
        inventoryUnpick.nextField();
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8003")
    @Test(priority = 203, enabled = true)
    public void enterMRUnpick() {
        inventoryUnpick.materialsRequest(true, mrCode);
        inventoryUnpick.nextField();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8004")
    @Test(priority = 204, enabled = true)
    public void verifyUnpickItemRequired() {
        inventoryUnpick.testRequiredField("Select Unpick Item");
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8005")
    @Test(priority = 205, enabled = true)
    public void verifyUnpickItemInvalidPart() {
        inventoryUnpick.unpickItem("Invalid Part");
        inventoryUnpick.nextField();
        inventoryUnpick.verifyMessage(invalidUnpickItem);
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8006")
    @Test(priority = 206, enabled = true)
    public void verifyUnpickItemInvalidMRPart() {
        inventoryUnpick.unpickItem("44WR8");
        inventoryUnpick.nextField();
        inventoryUnpick.verifyMessage(invalidUnpickMRItem);
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8007")
    @Test(priority = 207, enabled = true)
    public void enterUnpickItemSerialized() {
        inventoryUnpick.unpickItem(assetCode3);
        inventoryUnpick.nextField();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8008")
    @Test(priority = 208, enabled = false)
    public void verifyUnpickContainerSerialized() {
        inventoryUnpick.verifyRoutineValue(containerCode3);
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    //Validate To LDC Required
    @TestRailCase(selfReporting = true, automationId = "8009")
    @Test(priority = 209, enabled = true)
    public void verifyToLDCRequiredUnpick() {
        inventoryUnpick.testRequiredField("To LDC");
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    //Validate Invalid LDC
    @TestRailCase(selfReporting = true, automationId = "8010")
    @Test(priority = 210, enabled = true)
    public void verifyToLDCInvalidUnpick() {
        inventoryUnpick.toLDC(true, "TNS103383");
        inventoryUnpick.nextField();
        inventoryUnpick.verifyMessage(invalidToLDCUnpick);
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    //Enter valid LDC
    @TestRailCase(selfReporting = true, automationId = "8011")
    @Test(priority = 211, enabled = true)
    public void enterToLDCUnpick() {
        inventoryUnpick.toLDC(true, receiveLDC);
        inventoryUnpick.clickValidationFile();
        //inventoryUnpick.enterValidationFileSearch(toLDC);
        inventoryUnpick.clickLookupValue(receiveLDC);
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "8012")
    @Test(priority = 212, enabled = true, dependsOnMethods = "enterToLDCUnpick")
    public void validatePopulatedToLDCDescription() {
        String description = oracleDriver.getLDCDescription(receiveLDC);
        inventoryUnpick.verifyRoutineValue(description);
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    //Validate Populated BU
    @TestRailCase(selfReporting = true, automationId = "8013")
    @Test(priority = 213, enabled = true, dependsOnMethods = "validatePopulatedToLDCDescription")
    public void validatePopulatedToBU() {
        toLocationBU = oracleDriver.getLocationBU(receiveLDC);
        inventoryUnpick.verifyRoutineValue(toLocationBU);
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "8014")
    @Test(priority = 214, enabled = true, dependsOnMethods = "validatePopulatedToBU")
    public void enterEmptyNotesUnpick() {
        inventoryUnpick.nextField();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "8014")
    @Test(priority = 215, enabled = true, dependsOnMethods = "enterEmptyNotesUnpick")
    public void verifyLoopfieldAsset() {
        inventoryUnpick.verifyLoopfield(true, "Select Unpick Item");
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8015")
    @Test(priority = 216, enabled = true, dependsOnMethods = "verifyLoopfieldAsset")
    public void enterUnpickItemNonSerialized() {
        inventoryUnpick.unpickItem(partCodeSO);
        inventoryUnpick.nextField();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8016")
    @Test(priority = 217, enabled = true, dependsOnMethods = "enterUnpickItemNonSerialized")
    public void verifyUnpickContainerNonSerialized() {
      //  inventoryUnpick.unpickFromContainerLabel(containerCode4);
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }


    @TestRailCase(selfReporting = true, automationId = "8017")
    @Test(priority = 218, enabled = true, dependsOnMethods = "verifyUnpickContainerNonSerialized")
    public void verifyLineNumberRequiredUnpick() {
        inventoryUnpick.testRequiredField("Line Number");
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "8018")
    @Test(priority = 219, enabled = true, dependsOnMethods = "verifyLineNumberRequiredUnpick")
    public void verifyLineNumberInvalidUnpick() {
        inventoryUnpick.lineNumber(true, "INVALID");
        inventoryUnpick.nextField();
        inventoryUnpick.verifyMessage(invalidLineNumber);
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8019")
    @Test(priority = 220, enabled = true, dependsOnMethods = "verifyLineNumberInvalidUnpick")
    public void enterLineNumberNonSerialized() {
        inventoryUnpick.lineNumber(true, "8");
        inventoryUnpick.nextField();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8020")
    @Test(priority = 221, enabled = true, dependsOnMethods = "enterLineNumberNonSerialized")
    public void verifyFromLDCNonSerialized() {
        inventoryUnpick.verifyRoutineValue(stagingLDC);
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8021")
    @Test(priority = 222, enabled = true, dependsOnMethods = "verifyFromLDCNonSerialized")
    public void verifyFromLDCBUNonSerialized() {
        fromLocationBU = oracleDriver.getLocationBU(receiveLDC);
        inventoryUnpick.verifyRoutineValue(fromLocationBU);
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8022")
    @Test(priority = 223, enabled = true, dependsOnMethods = "verifyFromLDCBUNonSerialized")
    public void verifyProjectNonSerialized() {
        inventoryUnpick.verifyRoutineValue(projectCode2);
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8023")
    @Test(priority = 224, enabled = true, dependsOnMethods = "verifyProjectNonSerialized")
    public void verifyPONonSerialized() {
        inventoryUnpick.verifyRoutineValue(poCode2);
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8024")
    @Test(priority = 225, enabled = true, dependsOnMethods = "verifyPONonSerialized")
    public void verifyPOLineNumberNonSerialized() {
        inventoryUnpick.verifyRoutineValue("3.0");
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8025")
    @Test(priority = 226, enabled = true, dependsOnMethods = "verifyPOLineNumberNonSerialized")
    public void verifyQuantityToUnpickRequired() {
        inventoryUnpick.toLDC(true, receiveLDC);
        inventoryUnpick.clickValidationFile();
        inventoryUnpick.clickLookupValue(receiveLDC);
        mobilityDriver.clearField(2);
        inventoryUnpick.testRequiredField("Quantity To Unpick");
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8026")
    @Test(priority = 227, enabled = true, dependsOnMethods = "verifyQuantityToUnpickRequired")
    public void verifyQuantityToUnpickOver() {
        inventoryUnpick.unpickQty(100);
        inventoryUnpick.nextField();
        inventoryUnpick.nextField();
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8027")
    @Test(priority = 228, enabled = true, dependsOnMethods = "verifyQuantityToUnpickOver")
    public void verifyQuantityToUnpickZero() {
        inventoryUnpick.clickPreviousRoutineDetail("Quantity To Unpick");
        inventoryUnpick.unpickQty(0);
        inventoryUnpick.nextField();
        inventoryUnpick.nextField();
        inventoryUnpick.verifyMessage(invalidQty);
        inventoryUnpick.clickOk();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8028")
    @Test(priority = 229, enabled = true, dependsOnMethods = "verifyQuantityToUnpickZero")
    public void enterQuantityToUnpick() {
        inventoryUnpick.clickPreviousRoutineDetail("Quantity To Unpick");
        inventoryUnpick.unpickQty(2);
        inventoryUnpick.nextField();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8029")
    @Test(priority = 230, enabled = true, dependsOnMethods = "enterQuantityToUnpick")
    public void enterNotesUnpick() {
        inventoryUnpick.notes(false, "NOTES");
        inventoryUnpick.nextField();
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }

    @TestRailCase(automationId = "8030")
    @Test(priority = 230, enabled = true, dependsOnMethods = "enterNotesUnpick")
    public void verifyLoopfieldPartsUnpick() {
        inventoryUnpick.verifyLoopfield(true, "Select Unpick Item");
        Assert.assertEquals(inventoryUnpick.getResult(), true);
    }
}