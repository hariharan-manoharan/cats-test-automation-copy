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
public class UpdateAssetTest extends VerizonTest {

    private String ldc = "CNS236520";
    private String newLDC = "SNS236520";
    private String assetCode;
    private String newPartCode = "44WR87";
    private UpdateAsset updateAsset;

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
        updateAsset = new UpdateAsset(mobilityDriver, oracleDriver, routines);
        toLDC.add("GNS103383");
        toLDC.add("SNS103383");
        toLDC.add("GNS236520");
        toLDC.add("SNS236520");
        assetCode = oracleDriver.createAsset(ldc);
        rand = new Random();
    }
    
    @TestRailCase(automationId = "30001")
    @Test(priority = 101, enabled = true)
    public void updateAsset0001 () {
        routines.selectRoutine("Update Asset");
        num = rand.nextInt(4);
        updateAsset.executeRoutine(false, assetCode, toLDC.get(num));
    }

    //Regression Testing Update Asset
    @TestRailCase(selfReporting = true, automationId = "30010")
    @Test(priority = 112, enabled = true)
    public void barcodeRequired() {
        updateAsset.reset();
        updateAsset.barcode(true, " ");
        updateAsset.testRequiredField("Barcode");
        updateAsset.clickOk();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30011")
    @Test(priority = 113, enabled = true)
    public void invalidBarcode() {
        updateAsset.barcode(true, "INVALID");
        updateAsset.nextField();
        updateAsset.verifyMessage(invalidBarcode);
        updateAsset.clickOk();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30012")
    @Test(priority = 114, enabled = true)
    public void enterBarcode() {
        updateAsset.barcode(true, assetCode);
        updateAsset.nextField();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30013")
    @Test(priority = 115, enabled = true)
    public void ldcRequired() {
        updateAsset.ldc(true, " ");
        updateAsset.testRequiredField("LDC");
        updateAsset.clickOk();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30014")
    @Test(priority = 116, enabled = true)
    public void invalidLDC() {
        updateAsset.ldc(true, "INVALID LDC");
        updateAsset.nextField();
        updateAsset.verifyMessage(invalidLDC);
        updateAsset.clickOk();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30015")
    @Test(priority = 117, enabled = true)
    public void enterLDC() {
        updateAsset.ldc(true, newLDC);
        updateAsset.nextField();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    //Verify Location Description is not clickable
    @TestRailCase(selfReporting = true, automationId = "30016")
    @Test(priority = 118, enabled = true)
    public void readOnlyLocationDesc() {
        updateAsset.clickPreviousRoutineDetail("Location Description");
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    //Validate Location Description
    @TestRailCase(selfReporting = true, automationId = "30017")
    @Test(priority = 119, enabled = true)
    public void validateLocationDesc() {
        String locationDescription = oracleDriver.getLDCDescription(newLDC);
        updateAsset.verifyRoutineValue(locationDescription);
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    //Validate Populated BU
    @TestRailCase(selfReporting = true, automationId = "30018")
    @Test(priority = 120, enabled = true)
    public void validatePopulatedBU() {
        toLocationBU = oracleDriver.getLocationBU(newLDC);
        updateAsset.verifyCurrentRoutineValue(toLocationBU);
        Assert.assertEquals(updateAsset.getResult(), true);
    }


    //Enter invalid To Location BU
    @TestRailCase(selfReporting = true, automationId = "30019")
    @Test(priority = 121, enabled = false)
    public void enterInvalidBU() {
        updateAsset.locationBU(false, " ");
        updateAsset.locationBU(false, "INVALID BU");
        updateAsset.verifyMessage(invalidBU);
        updateAsset.clickOk();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    //Enter valid BU
    @TestRailCase(selfReporting = true, automationId = "30020")
    @Test(priority = 123, enabled = true)
    public void enterBU() {
        updateAsset.locationBU(false, toLocationBU);
        updateAsset.clickValidationFile();
        updateAsset.clickLookupValue(toLocationBU);
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    //Validate Part Code Required
    @TestRailCase(selfReporting = true, automationId = "30021")
    @Test(priority = 124, enabled = true)
    public void partCodeRequired() {
        updateAsset.partCode(true, " ");
        updateAsset.testRequiredField("Part Code");
        updateAsset.clickOk();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    //Validate invalid Part Code
    @TestRailCase(selfReporting = true, automationId = "30022")
    @Test(priority = 125, enabled = true)
    public void enterInvalidPartCode() {
        updateAsset.partCode(true, "INVALID PART CODE");
        updateAsset.nextField();
        updateAsset.verifyMessage(invalidPartCodeUpdateAssetMsg);
        updateAsset.clickOk();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    //Validate Part Code look up
    @TestRailCase(selfReporting = true, automationId = "30023")
    @Test(priority = 126, enabled = true)
    public void enterPartCode() {
        updateAsset.partCode(true, newPartCode);
        updateAsset.clickValidationFile();
        updateAsset.clickLookupValue(newPartCode);
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30024")
    @Test(priority = 127, enabled = true)
    public void verifyNewPartCodeMsg() {
        updateAsset.verifyMessage(newPartCodeMsg);
        updateAsset.clickOk();
    }

    @TestRailCase(selfReporting = true, automationId = "30025")
    @Test(priority = 128, enabled = true)
    public void newPartCodeNo() {
        updateAsset.confirmPartCodeChange("N");
        updateAsset.nextField();
        updateAsset.verifyRoutineDetail("Part Code", true);
        updateAsset.partCode(true, newPartCode);
        updateAsset.nextField();
    }

    @TestRailCase(selfReporting = true, automationId = "30026")
    @Test(priority = 129, enabled = true)
    public void newPartCodeYes() {
        updateAsset.verifyMessage(newPartCodeMsg);
        updateAsset.clickOk();
        updateAsset.confirmPartCodeChange("Y");
        updateAsset.nextField();
    }

    @TestRailCase(selfReporting = true, automationId = "30027")
    @Test(priority = 130, enabled = true)
    public void newSerial() {
        updateAsset.serialNumber("NewSerial");
        updateAsset.nextField();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    //Validate Part Code look up
    @TestRailCase(selfReporting = true, automationId = "30028")
    @Test(priority = 131, enabled = true)
    public void enterRevision() {
        updateAsset.revision("NewRevision");
        updateAsset.nextField();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30029")
    @Test(priority = 132, enabled = true)
    public void enterNotes() {
        updateAsset.notes(false, "NewNotes");
        updateAsset.nextField();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30030")
    @Test(priority = 133, enabled = true)
    public void enterEmptyLocationBU() {
        updateAsset.barcode(true, assetCode);
        updateAsset.nextField();
        updateAsset.nextField();
        updateAsset.locationBU(false, "");
        updateAsset.nextField();
        Assert.assertEquals(updateAsset.getResult(), true);

    }

    @TestRailCase(selfReporting = true, automationId = "30031")
    @Test(priority = 134, enabled = true)
    public void enterEmptySerial() {
        updateAsset.nextField();
        updateAsset.serialNumber("");
        updateAsset.nextField();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30032")
    @Test(priority = 135, enabled = true)
    public void enterEmptyRevision() {
        updateAsset.revision("");
        updateAsset.nextField();
        Assert.assertEquals(updateAsset.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "30033")
    @Test(priority = 136, enabled = true)
    public void enterEmptyNotes() {
        updateAsset.notes(false, "");
        updateAsset.nextField();
        Assert.assertEquals(updateAsset.getResult(), true);
    }
}
