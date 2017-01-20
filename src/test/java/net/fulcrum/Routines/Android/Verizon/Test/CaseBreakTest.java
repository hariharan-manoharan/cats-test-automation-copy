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
public class CaseBreakTest extends VerizonTest {

    private AddToContainer addToContainer;
    private CaseBreak caseBreak;
    private DestroyContainer destroyContainer;
    private ContainerInquiry containerInquiry;

    private final String containerSize = "BOX";
    private final String invalidContainer = "VZC-INVALID";
    private String containercode4;
    private int qtySO;

    //Error Messages
    private final String invalidContainerPrefix = "CONTAINER LABEL MUST BEGIN WITH VZC, V00, V01, OR U00.";
    private final String invalidBarcode = "BARCODE ENTERED DOES NOT EXIST AS A VALID ASSET CODE OR A VALID PART CODE" +
            "OR A VALID CONTAINER CODE.";
    private final String invalidContainerLabel = "DEVICE: Container Label " + invalidContainer + " is not a valid selection.";
    private final String invalidContainerInquiryLabel = "THE CONTAINER LABEL ENTERED IS NOT VALID";
    private final String itemNotInContainerMsg = "CONTAINER ITEM ENTERED IS NOT A VALID ITEM IN THE CONTAINER LABEL SPECIFIED.";
    private final String destroyContainerMsg = "DO YOU WANT TO DESTROY THIS CONTAINER? Y/N";
    protected final String invalidQuantityMsg = "DEVICE: Quantity is a required field.  The value must be greater than 0.";


    @BeforeClass(alwaysRun = true)
    public void StageData() {

        addToContainer = new AddToContainer(mobilityDriver, oracleDriver, routines);
        caseBreak = new CaseBreak(mobilityDriver, oracleDriver, routines);
        destroyContainer = new DestroyContainer(mobilityDriver, oracleDriver, routines);
        containerInquiry = new ContainerInquiry(mobilityDriver, oracleDriver, routines);
        fromLDC = "CNS236520";
        toLDC = "SNS236520";
        partCodeSO = "#18/2";
        projectCode = "PROJECT-CTA-EXP";
        poCode = "PO-CTA-EXP";
        lineNumber = "1.0";
        containerCode = oracleDriver.generateContainerCode();
        containerCode2 = oracleDriver.generateContainerCode();
        assetCode = oracleDriver.createAsset("CNS236520");
        assetCode2 = oracleDriver.createAsset("CNS236520");
        qty = getRandomNumber(1000) + 1;
        qtySO = 3;
        oracleDriver.createPart("CNS236520", partCodeLN, qty);
        oracleDriver.createPart("CNS236520", partCodeSU, qty);
        oracleDriver.createPart("CNS236520", partCodeSO, qty);

    }

    //Select Add to Container Routine
    @TestRailCase(automationId = "1004")
    @Test(priority = 1, enabled = true)
    public void selectAddToContainer() {
        addToContainer.selectRoutine();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Add Asset to Container
    @TestRailCase(automationId = "1000")
    @Test(priority = 2, enabled = true)
    public void addAsset2() {
        addToContainer.execute(false, toLDC, fromLDC, containerCode2, assetCode2);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Add Asset to Container
    @TestRailCase(automationId = "1000")
    @Test(priority = 3, enabled = true)
    public void addAsset() {
        addToContainer.reset();
        addToContainer.execute(false, toLDC, fromLDC, containerCode, assetCode);
        Assert.assertEquals(addToContainer.getResult(), true);
    }


    //Add LN Part to Container
       @TestRailCase(automationId = "1001")
       @Test(priority = 11, enabled = true)
       public void addLNPart() {
           addToContainer.execute(false, toLDC, fromLDC, containerCode, partCodeLN, qty);
           Assert.assertEquals(addToContainer.getResult(), true);
      }

       //Add SO Part to Container
       @TestRailCase(automationId = "1002")
       @Test(priority = 12, enabled = true)
       public void addSOPart() {
           addToContainer.execute(false, toLDC, fromLDC, containerCode, partCodeSO, qtySO, poCode, lineNumber, projectCode);
           Assert.assertEquals(addToContainer.getResult(), true);
       }

       //Add SU Part to Container
       @TestRailCase(automationId = "1003")
       @Test(priority = 13, enabled = true)
       public void addSUPart() {
           addToContainer.execute(false, toLDC, fromLDC, containerCode, partCodeSU, qty);
           Assert.assertEquals(addToContainer.getResult(), true);
       }


    //Add Container with an Asset to another Container
    @TestRailCase(automationId = "1004")
    @Test(priority = 14, enabled = true)
    public void addContainer() {
        addToContainer.execute(false, toLDC, fromLDC, containerCode, containerCode2);
        Assert.assertEquals(addToContainer.getResult(), true);
    }


    @TestRailCase(automationId = "2040")
    @Test(priority = 101, enabled = true)
    public void navigateRoutine() {
        caseBreak.reset();
    }

    @TestRailCase(automationId = "1006")
    @Test(priority = 102, enabled = true)
    public void verifyContainerLabelRequired() {
        addToContainer.testRequiredField("Container Code");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "1004")
    @Test(priority = 103, enabled = true)
    public void verifyContainerLabelInvalid() {
        addToContainer.containerCode(true, "INVALID CONTAINER");
        addToContainer.nextField();
        addToContainer.verifyValidationOverride("No");
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Enter Valid Container Label
    @TestRailCase(automationId = "1004")
    @Test(priority = 104, enabled = true)
    public void enterContainerLabel() {
        addToContainer.containerCode(true, containerCode);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "2041")
    @Test(priority = 105, enabled = true)
    public void readOnlyToLDC() {
        caseBreak.clickPreviousRoutineDetail("To LDC");
        caseBreak.verifyRoutineDetail("Container Item", true);
    }

    @TestRailCase(automationId = "2042")
    @Test(priority = 106, enabled = true)
    public void readOnlyLocationDecription() {
        caseBreak.clickPreviousRoutineDetail("Location Description");
        caseBreak.verifyRoutineDetail("Container Item", true);
    }

    @TestRailCase(automationId = "2043")
    @Test(priority = 107, enabled = true)
    public void readOnlyLocationBU() {
        caseBreak.clickPreviousRoutineDetail("To Location BU");
        caseBreak.verifyRoutineDetail("Container Item", true);
    }

    @TestRailCase(automationId = "2044")
    @Test(priority = 108, enabled = true)
    public void invalidItemRequired() {
        caseBreak.testRequiredField("Container Item");
        caseBreak.clickOk();
    }

    @TestRailCase(automationId = "2044")
    @Test(priority = 109, enabled = true)
    public void invalidItem() {
        caseBreak.containerItem(true, "Invalid");
        caseBreak.nextField();
        caseBreak.verifyMessage(itemNotInContainerMsg);
        caseBreak.clickOk();
    }

    @TestRailCase(automationId = "2045")
    @Test(priority = 110, enabled = true)
    public void enterItem() {
        caseBreak.containerItem(true, partCodeSO);
        caseBreak.nextField();
    }

    //Enter valid value for PO
    @TestRailCase(selfReporting = true, automationId = "5043")
    @Test(priority = 111, enabled = true)
    public void enterEmptyPO() {
        caseBreak.poCode(false, "");
        caseBreak.nextField();
        Assert.assertEquals(caseBreak.getResult(), true);
    }

    //Enter valid value for PO Line
    @TestRailCase(selfReporting = true, automationId = "5044")
    @Test(priority = 112, enabled = true)
    public void enterEmptyPOLine() {
        caseBreak.lineNumber(false, "");
        caseBreak.nextField();
        Assert.assertEquals(caseBreak.getResult(), true);
    }

    //Enter valid value for PO Line
    @TestRailCase(selfReporting = true, automationId = "5045")
    @Test(priority = 113, enabled = true)
    public void enterEmptyProject() {
        caseBreak.projectCode(false, "");
        caseBreak.nextField();
        Assert.assertEquals(caseBreak.getResult(), true);
    }

    @TestRailCase(automationId = "2046")
    @Test(priority = 114, enabled = true)
    public void enterOverQty() {
        caseBreak.quantity(true, qty + 10);
        caseBreak.nextField();
        caseBreak.nextField();
        caseBreak.verifyMessage("TRIGGER: CONTAINER PART QUANTITY " + qtySO + " IS LESS THAN ENTERED QUANTITY AND " +
                "CANNOT BE REMOVED.");
        caseBreak.clickOk();
    }

    @TestRailCase(automationId = "2046")
    @Test(priority = 115, enabled = true)
    public void enterNegativeQty() {
        caseBreak.clickPreviousRoutineDetail("Quantity");
        caseBreak.quantity(true, -1);
        caseBreak.nextField();
        caseBreak.verifyMessage(invalidQuantityMsg);
        caseBreak.clickOk();
    }

    @TestRailCase(automationId = "2046")
    @Test(priority = 116, enabled = true)
    public void enterQty() {
        caseBreak.quantity(true, 1);
        caseBreak.nextField();
    }

    @TestRailCase(automationId = "2047")
    @Test(priority = 117, enabled = true)
    public void enterNotes() {
        caseBreak.notes(false, "TEST");
        caseBreak.nextField();
    }

    //Case Break asset from Container
    @TestRailCase(automationId = "2048")
    @Test(priority = 118, enabled = true)
    public void caseBreakLS() {
        caseBreak.reset();
        caseBreak.executeRoutine(false, toLDC, containerCode, assetCode, 1);
        //Assert.assertEquals(result.GetSuccess(),true);
    }

    //Case Break a Part from Container
    @TestRailCase(automationId = "2049")
    @Test(priority = 119, enabled = true)
    public void caseBreakLN() {
        caseBreak.executeRoutine(false, toLDC, containerCode, partCodeLN, qty);
        //Assert.assertEquals(result.GetSuccess(),true);
    }

    //Case Break a Part from Container
    @TestRailCase(automationId = "2049")
    @Test(priority = 120, enabled = true)
    public void caseBreakSU() {
        caseBreak.executeRoutine(false, toLDC, containerCode, partCodeSU, qty);
        //Assert.assertEquals(result.GetSuccess(),true);
    }


    //Case Break a Container from Container
    @TestRailCase(automationId = "2050")
    @Test(priority = 121, enabled = true)
    public void caseBreakContainer() {
        caseBreak.executeRoutine(false, toLDC, containerCode, containerCode2, 1);
    }

    //Case Break a Part from Container
    @TestRailCase(automationId = "2049")
    @Test(priority = 122, enabled = true)
    public void enterInvalidPOCode() {
        caseBreak.containerItem(true, partCodeSO);
        caseBreak.nextField();
        caseBreak.poCode(false, "INVALID");
        caseBreak.nextField();
        caseBreak.verifyValidationOverride("No");
    }


    //Case Break a Part from Container
    @TestRailCase(automationId = "2049")
    @Test(priority = 123, enabled = true)
    public void enterValidPOCode() {
        caseBreak.poCode(false, poCode);
        caseBreak.nextField();
    }

    //Case Break a Part from Container
    @TestRailCase(automationId = "2049")
    @Test(priority = 124, enabled = true)
    public void enterInvalidPOLineNumber() {
        caseBreak.lineNumber(false, "INVALID");
        caseBreak.nextField();
        caseBreak.verifyValidationOverride("No");
    }


    //Case Break a Part from Container
    @TestRailCase(automationId = "2049")
    @Test(priority = 125, enabled = true)
    public void enterValidPOLineNumber() {
        caseBreak.lineNumber(false, lineNumber);
        caseBreak.nextField();
    }

    //Case Break a Part from Container
    @TestRailCase(automationId = "2049")
    @Test(priority = 126, enabled = true)
    public void enterInvalidProject() {
        caseBreak.projectCode(false, "INVALID");
        caseBreak.nextField();
        caseBreak.verifyValidationOverride("No");
    }


    //Case Break a Part from Container
    @TestRailCase(automationId = "2049")
    @Test(priority = 127, enabled = true)
    public void enterValidProject() {
        caseBreak.projectCode(false, projectCode);
        caseBreak.nextField();
    }

    @TestRailCase(automationId = "2049")
    @Test(priority = 128, enabled = true)
    public void enterPartialQty() {
        caseBreak.quantity(true, qtySO - 1);
        caseBreak.nextField();
    }


    @TestRailCase(automationId = "2049")
    @Test(priority = 129, enabled = true)
    public void verifyDestroyMsg() {
        caseBreak.verifyMessage(destroyContainerMsg);
        caseBreak.clickOk();
    }


    @TestRailCase(automationId = "2049")
    @Test(priority = 130, enabled = true)
    public void destroyYes() {
        caseBreak.clickValidationFile();
        caseBreak.clickLookupValue("Y");

    }

    @TestRailCase(automationId = "2049")
    @Test(priority = 131, enabled = true)
    public void enterEmptyNotes() {
        caseBreak.nextField();
    }

    //Case Break a Part from Container
    @TestRailCase(automationId = "2049")
    @Test(priority = 132, enabled = true)
    public void caseBreakSO() {
        Assert.assertEquals(caseBreak.getResult(), true);
    }

    @TestRailCase(automationId = "2049")
    @Test(priority = 133, enabled = true)
    public void destroyNo() {
        caseBreak.reset();
        caseBreak.containerCode(true, containerCode2);
        caseBreak.nextField();
        caseBreak.containerItem(true, assetCode2);
        caseBreak.nextField();
        caseBreak.verifyMessage(destroyContainerMsg);
        caseBreak.clickOk();
        caseBreak.clickValidationFile();
        caseBreak.clickLookupValue("N");
        caseBreak.nextField();
    }
}
