package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.Drivers.*;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.Android.Test.MainTest;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Random;

@Listeners(TestRailListener.class)
public class UpdateAssetSysAdminTest extends VerizonTest {

    private String ldc = "CNS236520";
    private String newLDC = "SNS236520";
    private String assetCode;
    private String newPartCode = "44WR87";
    private UpdateAssetSysAdmin updateAssetSysAdmin;

    private ArrayList<String> toLDC = new ArrayList<String>(4);
    private Random rand = new Random();
    private int num;

    private final String nonExistAsset = "ASSET CODE ENTERED DOES NOT EXIST.";
    private final String existAsset = "NEW ASSET TAG ENTERED EXISTS AND CANNOT BE USED.";
    private final String newPartCodeMsg = "NEW PART CODE ENTERED IS NOT A VALID SUBSTITUTE FOR CURRENT PART CODE. PLEASE " +
            "CONFIRM CHANGE. Y/N";
    private final String invalidPartCodeUpdateAssetMsg = "PART CODE ENTERED IS NOT A VALID PART FOR UPDATING AN ASSET.";
    
    @BeforeClass(alwaysRun = true)
    public void StageData() {
        updateAssetSysAdmin = new UpdateAssetSysAdmin(mobilityDriver, oracleDriver, routines);
        toLDC.add("GNS103383");
        toLDC.add("SNS103383");
        toLDC.add("GNS236520");
        toLDC.add("SNS236520");
        assetCode = oracleDriver.createAsset(ldc);
        rand = new Random();
    }

    @TestRailCase(automationId = "40001")
    @Test(priority = 101, enabled = true)
    public void updateAssetSysAdmin0001 () {
        routines.selectRoutine("Update Asset - Sys Admin");
        num = rand.nextInt(4);
        updateAssetSysAdmin.executeRoutine(false, assetCode, toLDC.get(num));
    }

    //Regression Testing Update Asset
    @TestRailCase(selfReporting = true, automationId = "40010")
    @Test(priority = 112, enabled = true)
    public void barcodeRequired() {
        updateAssetSysAdmin.reset();
        updateAssetSysAdmin.barcode(true, " ");
        updateAssetSysAdmin.testRequiredField("Barcode");
        updateAssetSysAdmin.clickOk();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40011")
    @Test(priority = 113, enabled = true)
    public void invalidBarcode() {
        updateAssetSysAdmin.barcode(true, "INVALID");
        updateAssetSysAdmin.nextField();
        updateAssetSysAdmin.verifyMessage(invalidBarcode);
        updateAssetSysAdmin.clickOk();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40012")
    @Test(priority = 114, enabled = true)
    public void enterBarcode() {
        updateAssetSysAdmin.barcode(true, assetCode);
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40013")
    @Test(priority = 115, enabled = true)
    public void ldcRequired() {
        updateAssetSysAdmin.ldc(true, " ");
        updateAssetSysAdmin.testRequiredField("LDC");
        updateAssetSysAdmin.clickOk();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40014")
    @Test(priority = 116, enabled = true)
    public void invalidLDC() {
        updateAssetSysAdmin.ldc(true, "INVALID LDC");
        updateAssetSysAdmin.nextField();
        updateAssetSysAdmin.verifyMessage(invalidLDC);
        updateAssetSysAdmin.clickOk();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40015")
    @Test(priority = 117, enabled = true)
    public void enterLDC() {
        updateAssetSysAdmin.ldc(true, newLDC);
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    //Verify Location Description is not clickable
    @TestRailCase(selfReporting = true, automationId = "40016")
    @Test(priority = 118, enabled = true)
    public void readOnlyLocationDesc() {
        updateAssetSysAdmin.clickPreviousRoutineDetail("Location Description");
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    //Validate Location Description
    @TestRailCase(selfReporting = true, automationId = "40017")
    @Test(priority = 119, enabled = true)
    public void validateLocationDesc() {
        String locationDescription = oracleDriver.getLDCDescription(newLDC);
        updateAssetSysAdmin.verifyRoutineValue(locationDescription);
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    //Validate Populated BU
    @TestRailCase(selfReporting = true, automationId = "40018")
    @Test(priority = 120, enabled = true)
    public void validatePopulatedBU() {
        toLocationBU = oracleDriver.getLocationBU(newLDC);
        updateAssetSysAdmin.verifyCurrentRoutineValue(toLocationBU);
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }


    //Enter invalid To Location BU
    @TestRailCase(selfReporting = true, automationId = "40019")
    @Test(priority = 121, enabled = false)
    public void enterInvalidBU() {
        updateAssetSysAdmin.locationBU(false, " ");
        updateAssetSysAdmin.locationBU(false, "INVALID BU");
        updateAssetSysAdmin.verifyMessage(invalidBU);
        updateAssetSysAdmin.clickOk();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    //Enter valid BU
    @TestRailCase(selfReporting = true, automationId = "40020")
    @Test(priority = 123, enabled = true)
    public void enterBU() {
        updateAssetSysAdmin.locationBU(false, toLocationBU);
        updateAssetSysAdmin.clickValidationFile();
        updateAssetSysAdmin.clickLookupValue(toLocationBU);
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    //Validate Part Code Required
    @TestRailCase(selfReporting = true, automationId = "40021")
    @Test(priority = 124, enabled = true)
    public void partCodeRequired() {
        updateAssetSysAdmin.partCode(true, " ");
        updateAssetSysAdmin.testRequiredField("Part Code");
        updateAssetSysAdmin.clickOk();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    //Validate invalid Part Code
    @TestRailCase(selfReporting = true, automationId = "40022")
    @Test(priority = 125, enabled = true)
    public void enterInvalidPartCode() {
        updateAssetSysAdmin.partCode(true, "INVALID PART CODE");
        updateAssetSysAdmin.nextField();
        updateAssetSysAdmin.verifyMessage(invalidPartCodeUpdateAssetMsg);
        updateAssetSysAdmin.clickOk();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    //Validate Part Code look up
    @TestRailCase(selfReporting = true, automationId = "40023")
    @Test(priority = 126, enabled = true)
    public void enterPartCode() {
        updateAssetSysAdmin.partCode(true, newPartCode);
        updateAssetSysAdmin.clickValidationFile();
        updateAssetSysAdmin.clickLookupValue(newPartCode);
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40024")
    @Test(priority = 127, enabled = true)
    public void verifyNewPartCodeMsg() {
        updateAssetSysAdmin.verifyMessage(newPartCodeMsg);
        updateAssetSysAdmin.clickOk();
    }

    @TestRailCase(selfReporting = true, automationId = "40025")
    @Test(priority = 128, enabled = true)
    public void newPartCodeNo() {
        updateAssetSysAdmin.confirmPartCodeChange("N");
        updateAssetSysAdmin.nextField();
        updateAssetSysAdmin.verifyRoutineDetail("Part Code", true);
        updateAssetSysAdmin.partCode(true, newPartCode);
        updateAssetSysAdmin.nextField();
    }

    @TestRailCase(selfReporting = true, automationId = "40026")
    @Test(priority = 129, enabled = true)
    public void newPartCodeYes() {
        updateAssetSysAdmin.verifyMessage(newPartCodeMsg);
        updateAssetSysAdmin.clickOk();
        updateAssetSysAdmin.confirmPartCodeChange("Y");
        updateAssetSysAdmin.nextField();
    }

    @TestRailCase(selfReporting = true, automationId = "40026")
    @Test(priority = 130, enabled = true)
    public void enterPOCode() {
        updateAssetSysAdmin.poCode(false, "NEW-PO");
        updateAssetSysAdmin.nextField();
    }

    @TestRailCase(selfReporting = true, automationId = "40026")
    @Test(priority = 131, enabled = true)
    public void enterLineNumber() {
        updateAssetSysAdmin.lineNumber(false, "1.0");
        updateAssetSysAdmin.nextField();
    }

    @TestRailCase(selfReporting = true, automationId = "40026")
    @Test(priority = 132, enabled = true)
    public void enterProject() {
        updateAssetSysAdmin.projectCode(false, "NEW-PROJECT");
        updateAssetSysAdmin.nextField();
    }

    @TestRailCase(selfReporting = true, automationId = "40027")
    @Test(priority = 133, enabled = true)
    public void newSerial() {
        updateAssetSysAdmin.serialNumber("NewSerial");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    //Validate Part Code look up
    @TestRailCase(selfReporting = true, automationId = "40028")
    @Test(priority = 134, enabled = true)
    public void enterRevision() {
        updateAssetSysAdmin.revision("NewRevision");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40029")
    @Test(priority = 135, enabled = true)
    public void enterNotes() {
        updateAssetSysAdmin.notes(false, "NewNotes");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40030")
    @Test(priority = 136, enabled = true)
    public void enterEmptyLocationBU() {
        updateAssetSysAdmin.barcode(true, assetCode);
        updateAssetSysAdmin.nextField();
        updateAssetSysAdmin.nextField();
        updateAssetSysAdmin.locationBU(false, "");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);

    }

    @TestRailCase(selfReporting = true, automationId = "40031")
    @Test(priority = 137, enabled = true)
    public void enterEmptyPOCode() {
        updateAssetSysAdmin.nextField();
        updateAssetSysAdmin.poCode(false, "");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40031")
    @Test(priority = 138, enabled = true)
    public void enterEmptyLineNumber() {
        updateAssetSysAdmin.lineNumber(false, "");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }


    @TestRailCase(selfReporting = true, automationId = "40031")
    @Test(priority = 139, enabled = true)
    public void enterEmptyProject() {
        updateAssetSysAdmin.projectCode(false, "");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40031")
    @Test(priority = 140, enabled = true)
    public void enterEmptySerial() {
        updateAssetSysAdmin.serialNumber("");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40032")
    @Test(priority = 141, enabled = true)
    public void enterEmptyRevision() {
        updateAssetSysAdmin.revision("");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "40033")
    @Test(priority = 142, enabled = true)
    public void enterEmptyNotes() {
        updateAssetSysAdmin.notes(false, "");
        updateAssetSysAdmin.nextField();
        Assert.assertEquals(updateAssetSysAdmin.getResult(), true);
    }
}
