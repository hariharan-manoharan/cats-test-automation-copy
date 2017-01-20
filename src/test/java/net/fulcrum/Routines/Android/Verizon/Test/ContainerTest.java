package net.fulcrum.Routines.Android.Verizon.Test;
import net.fulcrum.Routines.Android.Verizon.*;

import net.fulcrum.Routines.iOS.TimeWarnerCable.AddAsset;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.TestRailReporter;

import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.Android.Test.MainTest;
import org.testng.annotations.*;
import org.testng.*;

@Listeners(TestRailListener.class)
public class ContainerTest extends VerizonTest {

    private AddToContainer addToContainer;
    private CaseBreak caseBreak;
    private DestroyContainer destroyContainer;
    private ContainerInquiry containerInquiry;

    //Error Messages
    private final String invalidContainerLabel = "CONTAINER LABEL MUST BEGIN WITH VZC, V00, V01, OR U00.";
    private final String invalidBarcode = "BARCODE ENTERED DOES NOT EXIST AS A VALID ASSET CODE OR A VALID PART CODE" +
            "OR A VALID CONTAINER CODE.";

    @BeforeClass(alwaysRun = true)
    public void StageData() {

        addToContainer = new AddToContainer(mobilityDriver, oracleDriver, routines);
        caseBreak = new CaseBreak(mobilityDriver, oracleDriver, routines);
        destroyContainer = new DestroyContainer(mobilityDriver, oracleDriver, routines);
        containerInquiry = new ContainerInquiry(mobilityDriver, oracleDriver, routines);

        containerCode = oracleDriver.generateContainerCode();
        assetCode = oracleDriver.createAsset("CNS236520");
        assetCode2 = oracleDriver.createAsset("CNS236520");
        qty = getRandomNumber(1000) + 1;
        oracleDriver.createPart("CNS236520", partCodeLN, qty);
        oracleDriver.createPart("CNS236520", partCodeSO, qty);
        oracleDriver.createPart("CNS236520", partCodeSU, qty);
        containerCode2 = oracleDriver.createContainerAsset("CNS236520", "SNS236520");

        fromLDC = "CNS236520";
        toLDC = "SNS236520";

    }

    //Select Add to Container Routine
    @TestRailCase(automationId = "1004")
    @Test(priority = 100, enabled = true)
    public void selectAddToContainer() {
        addToContainer.selectRoutine();
        Assert.assertEquals(addToContainer.getResult(), true);
    }


    //Add Asset to Container
	@TestRailCase(automationId = "1000")
	@Test(priority = 101, enabled = true)
    public void addAsset() {
        addToContainer.execute(false, toLDC, fromLDC, containerCode, assetCode);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Add Asset to Container
    @TestRailCase(automationId = "1000")
    @Test(priority = 102, enabled = true)
    public void addAsset2() {
        addToContainer.execute(false, toLDC, fromLDC, containerCode, assetCode2);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Add LN Part to Container
    @TestRailCase(automationId = "1001")
    @Test(priority = 103, enabled = true)
    public void addLNPart() {
        addToContainer.execute(false, toLDC, fromLDC, containerCode, partCodeLN, qty);
        Assert.assertEquals(addToContainer.getResult(), true);
   }

    //Add SO Part to Container
    @TestRailCase(automationId = "1002")
    @Test(priority = 104, enabled = true)
    public void addSOPart() {
        addToContainer.execute(false, toLDC, fromLDC, containerCode, partCodeSO, qty);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Add SU Part to Container
    @TestRailCase(automationId = "1003")
    @Test(priority = 105, enabled = true)
    public void addSUPart() {
        addToContainer.execute(false, toLDC, fromLDC, containerCode, partCodeSU, qty);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Add Container with an Asset to another Container
	@TestRailCase(automationId = "1004")
    @Test(priority = 106, enabled = true)
    public void addContainer() {
        addToContainer.execute(false, toLDC, fromLDC, containerCode, containerCode2);
        Assert.assertEquals(addToContainer.getResult(), true);
   }
    /*

    @TestRailCase(automationId = "1005")
    @Test(priority = 110, enabled = true)
    public void verifyRoutineTitleHeader() {
        addToContainer.reset();
        addToContainer.verifyRoutineHeader();
        addToContainer.verifyRoutineTitle("Add To Container");
    }

    @TestRailCase(automationId = "1006")
    @Test(priority = 112, enabled = true)
    public void verifyContainerLabelRequired() {
        addToContainer.testRequiredField("Container Label");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "1004")
    @Test(priority = 113, enabled = true)
    public void verifyContainerLabelInvalid() {
        addToContainer.containerLabel(true, "INVALID CONTAINER");
        addToContainer.nextField();
        addToContainer.verifyMessage(invalidContainerLabel);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter Valid Container Label
    @TestRailCase(automationId = "1004")
    @Test(priority = 114, enabled = true)
    public void enterContainerLabel() {
        addToContainer.containerLabel(true, "VZC-" + getRandomNumber(1000000));
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Select Container Size
    @TestRailCase(automationId = "1004")
    @Test(priority = 115, enabled = true)
    public void selectContainerSize() {
        addToContainer.containerSize(false, "");
        addToContainer.clickValidationFile();
        addToContainer.enterValidationFileSearch("BOX");
        addToContainer.clickLookupValue("BOX");
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Verify TO LDC required
    @TestRailCase(automationId = "1004")
    @Test(priority = 116, enabled = true)
    public void verifyToLDCRequired() {
        addToContainer.testRequiredField("To LDC");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Verify Invalid To LDC
    @TestRailCase(automationId = "1004")
    @Test(priority = 117, enabled = true)
    public void verifyToLDCInvalid() {
        System.out.println("IN 013");
        addToContainer.toLDC(true, "INVALID LDC");
        addToContainer.nextField();
        addToContainer.verifyMessage(invalidToLDC);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter valid To LDC
    @TestRailCase(automationId = "1004")
    @Test(priority = 118, enabled = true)
    public void enterToLDC() {
        addToContainer.toLDC(true, "");
        addToContainer.clickValidationFile();
        addToContainer.enterValidationFileSearch(toLDC);
        addToContainer.clickLookupValue(toLDC);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Verify Location Description is not clickable
    @TestRailCase(selfReporting = true, automationId = "5008")
    @Test(priority = 119, enabled = true, dependsOnMethods = "enterToLDC")
    public void verifyLocationDescriptionNonEditable() {
        addToContainer.clickPreviousRoutineDetail("Location Description");
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate Location Description
    @TestRailCase(selfReporting = true, automationId = "5010")
    @Test(priority = 120, enabled = true, dependsOnMethods = "enterToLDC")
    public void verifyLocationDescription() {
        String locationDescription = oracleDriver.getLDCDescription(toLDC);
        addToContainer.verifyRoutineValue(locationDescription);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate Populated BU
    @TestRailCase(selfReporting = true, automationId = "5011")
    @Test(priority = 121, enabled = true, dependsOnMethods = "verifyLocationDescription")
    public void validatePopulatedBU() {
        toLocationBU = oracleDriver.getLocationBU(toLDC);
        addToContainer.verifyCurrentRoutineValue(toLocationBU);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate To Location BU Required
    @TestRailCase(selfReporting = true, automationId = "5012")
    @Test(priority = 122, enabled = true, dependsOnMethods = "validatePopulatedBU")
    public void validateToLocationBURequired() {
        mobilityDriver.clearField();
        addToContainer.testRequiredField("To Location BU");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter invalid To Location BU
    @TestRailCase(selfReporting = true, automationId = "5013")
    @Test(priority = 123, enabled = true, dependsOnMethods = "validateToLocationBURequired")
    public void validateToLocationBUInvalid() {
        addToContainer.toLocationBU(true, "INVALID BU");
        addToContainer.nextField();
        addToContainer.verifyMessage(invalidToBU);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter valid BU
    @TestRailCase(selfReporting = true, automationId = "5014")
    @Test(priority = 124, enabled = true, dependsOnMethods = "validateToLocationBUInvalid")
    public void enterBu() {
        addToContainer.toLocationBU(true, toLocationBU);
        addToContainer.clickValidationFile();
        addToContainer.clickLookupValue(toLocationBU);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate Barcode Required
    @TestRailCase(selfReporting = true, automationId = "5012")
    @Test(priority = 125, enabled = true, dependsOnMethods = "enterBu")
    public void validateBarcodeRequired() {
        addToContainer.testRequiredField("Barcode");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter invalid To Location BU
    @TestRailCase(selfReporting = true, automationId = "5013")
    @Test(priority = 126, enabled = true, dependsOnMethods = "enterBu")
    public void validateBarcodeInvalid() {
        addToContainer.barcode(true, "INVALID BARCODE");
        addToContainer.nextField();
        addToContainer.verifyMessage(invalidBarcode);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "5014")
    @Test(priority = 127, enabled = true, dependsOnMethods = "enterBu")
    public void enterBarcodeSerialized() {
        addToContainer.barcode(true, assetCode);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter notes
    @TestRailCase(selfReporting = true, automationId = "5014")
    @Test(priority = 128, enabled = true, dependsOnMethods = "enterBarcodeSerialized")
    public void enterNotes() {
        addToContainer.notes(false, "Notes");
        addToContainer.nextField();
        addToContainer.verifyTransaction();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "5014")
    @Test(priority = 129, enabled = true, dependsOnMethods = "enterBarcodeSerialized")
    public void verifyLoopfieldSerialized() {
        addToContainer.verifyLoopfield();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter non-serialized barcode
    @TestRailCase(selfReporting = true, automationId = "5014")
    @Test(priority = 130, enabled = true, dependsOnMethods = "verifyLoopfieldSerialized")
    public void enterBarcodeNonSerialized() {
        oracleDriver.createPart(fromLDC, partCodeLN, 10);
        addToContainer.barcode(true, partCodeLN);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Verify From LDC required
    @TestRailCase(automationId = "1004")
    @Test(priority = 131, enabled = true, dependsOnMethods = "enterBarcodeNonSerialized")
    public void verifyFromLDCRequired() {
        addToContainer.testRequiredField("From LDC");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Verify Invalid From LDC
    @TestRailCase(automationId = "1004")
    @Test(priority = 132, enabled = true, dependsOnMethods = "enterBarcodeNonSerialized")
    public void verifyFromLDCInvalid() {
        addToContainer.fromLDC(true, "INVALID LDC");
        addToContainer.nextField();
        addToContainer.verifyMessage(invalidFromLDC);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter valid From LDC
    @TestRailCase(automationId = "1004")
    @Test(priority = 133, enabled = true, dependsOnMethods = "enterBarcodeNonSerialized")
    public void enterFromLDC() {
        addToContainer.fromLDC(true, "");
        addToContainer.clickValidationFile();
        addToContainer.enterValidationFileSearch(fromLDC);
        addToContainer.clickLookupValue(fromLDC);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Verify From Location Description is not clickable
    @TestRailCase(selfReporting = true, automationId = "5008")
    @Test(priority = 134, enabled = true, dependsOnMethods = "enterFromLDC")
    public void verifyFromLocationDescriptionNonEditable() {
        addToContainer.clickPreviousRoutineDetail("Location Description");
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate Location Description
    @TestRailCase(selfReporting = true, automationId = "5010")
    @Test(priority = 135, enabled = true, dependsOnMethods = "enterFromLDC")
    public void verifyFromLocationDescription() {
        String locationDescription = oracleDriver.getLDCDescription(fromLDC);
        addToContainer.verifyRoutineValue(locationDescription);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate Populated BU
    @TestRailCase(selfReporting = true, automationId = "5011")
    @Test(priority = 136, enabled = true, dependsOnMethods = "verifyFromLocationDescription")
    public void validatePopulatedFromBU() {
        fromLocationBU = oracleDriver.getLocationBU(fromLDC);
        addToContainer.verifyCurrentRoutineValue(fromLocationBU);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate To Location BU Required
    @TestRailCase(selfReporting = true, automationId = "5012")
    @Test(priority = 137, enabled = true, dependsOnMethods = "validatePopulatedFromBU")
    public void validateFromLocationBURequired() {
        mobilityDriver.clearField();
        addToContainer.testRequiredField("From Location BU");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter invalid From Location BU
    @TestRailCase(selfReporting = true, automationId = "5013")
    @Test(priority = 138, enabled = true, dependsOnMethods = "validateFromLocationBURequired")
    public void validateFromLocationBUInvalid() {
        addToContainer.fromLocationBU(true, "INVALID BU");
        addToContainer.nextField();
        addToContainer.verifyMessage(invalidFromBU);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter valid From Location BU
    @TestRailCase(selfReporting = true, automationId = "5014")
    @Test(priority = 139, enabled = true, dependsOnMethods = "validateToLocationBUInvalid")
    public void enterFromBu() {
        addToContainer.fromLocationBU(true, fromLocationBU);
        addToContainer.clickValidationFile();
        addToContainer.clickLookupValue(fromLocationBU);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate empty PO
    @TestRailCase(selfReporting = true, automationId = "5021")
    @Test(priority = 140, enabled = true, dependsOnMethods = "enterFromBu")
    public void validateEmptyPO() {
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate empty PO Line
    @TestRailCase(selfReporting = true, automationId = "5022")
    @Test(priority = 141, enabled = true, dependsOnMethods = "enterFromBu")
    public void validateEmptyPOLine() {
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate empty Project
    @TestRailCase(selfReporting = true, automationId = "5022")
    @Test(priority = 142, enabled = true, dependsOnMethods = "enterFromBu")
    public void validateEmptyProject() {
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate required Quantity
    @TestRailCase(selfReporting = true, automationId = "5018")
    @Test(priority = 118, enabled = true, dependsOnMethods = "expressStock0018")
    public void expressStock0019() {
        addToContainer.testRequiredField("Quantity");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Validate invalid Quantity
    @TestRailCase(selfReporting = true, automationId = "5019")
    @Test(priority = 119, enabled = true, dependsOnMethods = "expressStock0019")
    public void expressStock0020() {
        addToContainer.quantity(true, 0);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }
*/
    //Container Inquiry
    @TestRailCase(automationId = "5043")
    @Test(priority = 104, enabled = false)
    public void containerInquiry001() {
        containerInquiry.reset();
        containerInquiry.executeRoutine(containerCode);
        // Assert.assertEquals(result.GetSuccess(), true);
    }

    //Case Break an Asset from Container
    @TestRailCase(automationId = "5008")
    @Test(priority = 105, enabled = true)
    public void caseBreak0001 () {
        caseBreak.reset();
        caseBreak.executeRoutine(false, toLDC, containerCode, assetCode, 1);
    }

    //Case Break a Part from Container
    @TestRailCase(automationId = "5009")
    @Test(priority = 106, enabled = true)
    public void caseBreak0002()
    {
        caseBreak.executeRoutine(false, toLDC, containerCode, partCodeLN, 10);
        //Assert.assertEquals(result.GetSuccess(),true);
    }

    //Case Break a Container from Container
    @TestRailCase(automationId = "5010")
    @Test(priority = 107, enabled = true)
    public void caseBreak0003()
    {
        caseBreak.executeRoutine(false, toLDC, containerCode, containerCode2, 1);
        //Assert.assertEquals(result.GetSuccess(),true);
    }

    //Destroy Container
    @TestRailCase(automationId = "5011")
    @Test(priority = 108, enabled = true)
    public void destroyContainer001() {
        destroyContainer.reset();
        destroyContainer.executeRoutine(false, containerCode, assetCode2);
        // Assert.assertEquals(result.GetSuccess(), true);
    }
}
