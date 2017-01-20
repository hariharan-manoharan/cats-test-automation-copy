package net.fulcrum.Routines.Android.Verizon.Test;
import net.fulcrum.Routines.Android.Verizon.*;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.Android.Test.MainTest;

import java.util.*;

import org.testng.annotations.*;
import org.testng.Assert;

@Listeners(TestRailListener.class)
public class ExpressStockTest extends VerizonTest {

    private ExpressStock expressStock;

    //Error Messages
    private final String itemExists = "ITEM ALREADY EXISTS AND CANNOT BE STOCKED.";
    private final String badQuantity = "422: TRIGGER: PART TRANSACTION REQUIRES A QUANTITY.";

    @Test (priority = 100)
    public void StageData() {
        expressStock = new ExpressStock(mobilityDriver, oracleDriver, routines);
        locationDetailCodes.add("CNS103383");
        locationDetailCodes.add("CNS113846");
        locationDetailCodes.add("CNS109677");
        locationDetailCodes.add("CNS236520");
        toLDC = locationDetailCodes.get(getRandomNumber(4));
        toLocationBU = oracleDriver.getLocationBU(toLDC);
        projectCode = createNewProjectCode();
        poCode = createNewPOCode();
        expressStock.selectRoutine();
    }

    //Express stock a LS part
    @TestRailCase(selfReporting = true, automationId="5000")
    @Test (priority = 100)
    public void expressStock0001() {
        assetCode = createNewAssetLabel();
        expressStock.execute(false, toLDC, partCodeSerialized, assetCode, projectCode, poCode, createNewPOLine());
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Express stock a LS part
    @TestRailCase(selfReporting = true, automationId="5001")
    @Test (priority = 101)
    public void expressStock0002 (){
        assetCode = createNewAssetLabel();
        expressStock.execute(false, toLDC, partCodeSerialized2, assetCode, projectCode, poCode, createNewPOLine());
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Express stock a LN part
    @TestRailCase(selfReporting = true, automationId="5002")
    @Test (priority = 102, enabled = true)
    public void expressStock0003 (){
        expressStock.execute(false, toLDC, partCodeLN, 1, projectCode, poCode, createNewPOLine());
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Express stock a SO part
    @TestRailCase(selfReporting = true, automationId="5003")
    @Test (priority = 103, enabled = true)
    public void expressStock0004 (){
        expressStock.execute(false, toLDC, partCodeSO, 1, projectCode, poCode, createNewPOLine());
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Express stock a SU part
    @TestRailCase(selfReporting = true, automationId="5004")
    @Test (priority = 104, enabled = true)
    public void expressStock0005 (){
        expressStock.execute(false, toLDC, partCodeSU, 1, projectCode, poCode, createNewPOLine());
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Verify Routine Title and Header
    @TestRailCase(selfReporting = true, automationId = "5005")
    @Test(priority = 105, enabled = true)//, dependsOnMethods = "expressStock0005")
    public void expressStock0006() {
        expressStock.verifyRoutineHeader();
        expressStock.verifyRoutineTitle("Express Stock");
    }

    //Validate To LDC Required
    @TestRailCase(selfReporting = true, automationId = "5006")
    @Test(priority = 106, enabled = true)
    public void expressStock0007() {
        expressStock.reset();
        expressStock.testRequiredField("To LDC");
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate Invalid LDC
    @TestRailCase(selfReporting = true, automationId = "5007")
    @Test(priority = 107, enabled = true, dependsOnMethods = "expressStock0007")
    public void expressStock0008() {
        expressStock.toLDC(true, "INVALID LDC");
        expressStock.nextField();
        expressStock.verifyMessage(invalidToLDC);
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter valid LDC
    @TestRailCase(selfReporting = true, automationId = "5009")
    @Test(priority = 108, enabled = true, dependsOnMethods = "expressStock0007")
    public void expressStock0009() {
        expressStock.toLDC(true, "");
        expressStock.clickValidationFile();
        expressStock.enterValidationFileSearch(toLDC);
        expressStock.clickLookupValue(toLDC);
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Verify Location Description is not clickable
    @TestRailCase(selfReporting = true, automationId = "5008")
    @Test(priority = 109, enabled = true, dependsOnMethods = "expressStock0009")
    public void expressStock0010() {
        expressStock.clickPreviousRoutineDetail("Location Description");
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate Location Description
    @TestRailCase(selfReporting = true, automationId = "5010")
    @Test(priority = 110, enabled = true, dependsOnMethods = "expressStock0009")
    public void expressStock0011() {
        String locationDescription = oracleDriver.getLDCDescription(toLDC);
        expressStock.verifyRoutineValue(locationDescription);
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate Populated BU
    @TestRailCase(selfReporting = true, automationId = "5011")
    @Test(priority = 111, enabled = true, dependsOnMethods = "expressStock0009")
    public void expressStock0012() {
        expressStock.verifyCurrentRoutineValue(toLocationBU);
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate To Location BU Required
    @TestRailCase(selfReporting = true, automationId = "5012")
    @Test(priority = 112, enabled = true, dependsOnMethods = "expressStock0009")
    public void expressStock0013() {
        mobilityDriver.clearField();
        expressStock.testRequiredField("To Location BU");
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter invalid To Location BU
    @TestRailCase(selfReporting = true, automationId = "5013")
    @Test(priority = 113, enabled = true, dependsOnMethods = "expressStock0009")
    public void expressStock0014() {
        expressStock.toLocationBU(true, "INVALID BU");
        expressStock.nextField();
        expressStock.verifyMessage(invalidToBU);
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter valid BU
    @TestRailCase(selfReporting = true, automationId = "5014")
    @Test(priority = 114, enabled = true, dependsOnMethods = "expressStock0009")
    public void expressStock0015() {
        expressStock.toLocationBU(true, toLocationBU);
        expressStock.clickValidationFile();
        expressStock.clickLookupValue(toLocationBU);
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate Part Code Required
    @TestRailCase(selfReporting = true, automationId = "5015")
    @Test(priority = 115, enabled = true, dependsOnMethods = "expressStock0015")
    public void expressStock0016() {
        expressStock.testRequiredField("Part Code");
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate invalid Part Code
    @TestRailCase(selfReporting = true, automationId = "5016")
    @Test(priority = 116, enabled = true, dependsOnMethods = "expressStock0015")
    public void expressStock0017() {
        expressStock.partCode(true, "INVALID PART CODE");
        expressStock.nextField();
        expressStock.verifyMessage(invalidPartCode);
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate Part Code look up
    @TestRailCase(selfReporting = true, automationId = "5017")
    @Test(priority = 117, enabled = true, dependsOnMethods = "expressStock0017")
    public void expressStock0018() {
        expressStock.partCode(true, partCodeLN);
        expressStock.clickValidationFile();
        expressStock.clickLookupValue(partCodeLN);
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate required Quantity
    @TestRailCase(selfReporting = true, automationId = "5018")
    @Test(priority = 118, enabled = true, dependsOnMethods = "expressStock0018")
    public void expressStock0019() {
        expressStock.testRequiredField("Quantity");
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter valid Quantity
    @TestRailCase(selfReporting = true, automationId = "5019")
    @Test(priority = 119, enabled = true, dependsOnMethods = "expressStock0019")
    public void expressStock0020() {
        expressStock.quantity(true, 0);
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate empty Project
    @TestRailCase(selfReporting = true, automationId = "5020")
    @Test(priority = 120, enabled = true, dependsOnMethods = "expressStock0020")
    public void expressStock0021() {
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate empty PO
    @TestRailCase(selfReporting = true, automationId = "5021")
    @Test(priority = 121, enabled = true, dependsOnMethods = "expressStock0021")
    public void expressStock0022() {
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate empty PO Line
    @TestRailCase(selfReporting = true, automationId = "5022")
    @Test(priority = 122, enabled = true, dependsOnMethods = "expressStock0022")
    public void expressStock0023() {
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate required Notes
    @TestRailCase(selfReporting = true, automationId = "5023")
    @Test(priority = 123, enabled = true, dependsOnMethods = "expressStock0023")
    public void expressStock0024() {
        expressStock.testRequiredField("Notes");
        expressStock.clickOk();
    }

    //Validate enter Notes
    @TestRailCase(selfReporting = true, automationId = "5024")
    @Test(priority = 124, enabled = true, dependsOnMethods = "expressStock0024")
    public void expressStock0025() {
        expressStock.notes(true, "ENTER NOTES");
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate bad Quantity
    @TestRailCase(selfReporting = true, automationId = "5025")
    @Test(priority = 125, enabled = true, dependsOnMethods = "expressStock0025")
    public void expressStock0026() {
        expressStock.verifyMessage(badQuantity);
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter valid Quantity
    @TestRailCase(selfReporting = true, automationId = "5026")
    @Test(priority = 126, enabled = true, dependsOnMethods = "expressStock0026")
    public void expressStock0027() {
        expressStock.clickPreviousRoutineDetail("Quantity");
        expressStock.quantity(true, 10);
        expressStock.nextField();
        expressStock.nextField();
        expressStock.nextField();
        expressStock.nextField();
        expressStock.nextField();
    }

    //Verify loop field for Parts
    @TestRailCase(selfReporting = true, automationId = "5027")
    @Test(priority = 127, enabled = true, dependsOnMethods = "expressStock0027")
    public void expressStock0028() {
        expressStock.verifyLoopfield();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter a valid part
    @TestRailCase(selfReporting = true, automationId = "5028")
    @Test(priority = 128, enabled = true, dependsOnMethods = "expressStock0028")
    public void expressStock0029() {
        expressStock.partCode(true, partCodeSerialized);
        expressStock.nextField();
    }

    //Validate required Asset Code
    @TestRailCase(selfReporting = true, automationId = "5029")
    @Test(priority = 129, enabled = true, dependsOnMethods = "expressStock0029")
    public void expressStock0030() {
        expressStock.testRequiredField("Asset Code");
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter valid Asset Code
    @TestRailCase(selfReporting = true, automationId = "5030")
    @Test(priority = 130, enabled = true, dependsOnMethods = "expressStock0030")
    public void expressStock0031() {
        assetCode = createNewAssetLabel();
        expressStock.assetCode(true, assetCode);
        expressStock.nextField();
    }

    //Validate Serial Number not required
    @TestRailCase(selfReporting = true, automationId = "5031")
    @Test(priority = 131, enabled = true, dependsOnMethods = "expressStock0031")
    public void expressStock0032() {
        expressStock.serialNumber("");
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter valid value for Project
    @TestRailCase(selfReporting = true, automationId = "5032")
    @Test(priority = 132, enabled = true, dependsOnMethods = "expressStock0032")
    public void expressStock0033() {
        expressStock.projectCode(false, projectCode);
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter valid value for PO
    @TestRailCase(selfReporting = true, automationId = "5033")
    @Test(priority = 133, enabled = true, dependsOnMethods = "expressStock0033")
    public void expressStock0034() {
        expressStock.poCode(false, poCode);
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Enter valid value for PO Line
    @TestRailCase(selfReporting = true, automationId = "5034")
    @Test(priority = 134, enabled = true, dependsOnMethods = "expressStock0034")
    public void expressStock0035() {
        expressStock.poLine(false, getRandomNumber() + ".0");
        expressStock.nextField();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Verify loop field for Assets
    @TestRailCase(selfReporting = true, automationId = "5035")
    @Test(priority = 135, enabled = true, dependsOnMethods = "expressStock0035")
    public void expressStock0036() {
        expressStock.notes(true, "CTA: Express Stock");
        expressStock.nextField();
        expressStock.verifyLoopfield();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Validate cannot stock duplicate Asset Codes
    @TestRailCase(selfReporting = true, automationId = "5036")
    @Test(priority = 136, enabled = true, dependsOnMethods = "expressStock0036")
    public void expressStock0037() {
        expressStock.partCode(true, partCodeSerialized2);
        expressStock.nextField();
        expressStock.assetCode(true, assetCode);
        expressStock.nextField();
        expressStock.verifyMessage(itemExists);
        expressStock.clickOk();
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Invalid input for PO Line
    @TestRailCase(selfReporting = true, automationId = "5037")
    @Test(priority = 137, enabled = true, dependsOnMethods = "expressStock0037")
    public void expressStock0038() {
        assetCode = createNewAssetLabel();
        expressStock.assetCode(true, assetCode);
        expressStock.nextField();
        expressStock.nextField();
        expressStock.nextField();
        expressStock.nextField();
        expressStock.poLine(false, "INVALID PO LINE");
        expressStock.clickOk();
        expressStock.nextField();
        expressStock.verifyMessage(invalidPOLine);
        Assert.assertEquals(expressStock.getResult(), true);
    }
    //Perform express stock for Asset in batch mode
    @TestRailCase(selfReporting = true, automationId = "5038")
    @Test(priority = 138, enabled = true, dependsOnMethods = "expressStock0038")
    public void expressStock0039() {
        menu.toggleBatchMode();
        assetCode = createNewAssetLabel();
        expressStock.reset();
        expressStock.execute(true, toLDC, partCodeSerialized, assetCode, projectCode, poCode, createNewPOLine());
        menu.toggleBatchMode();
        menu.selectBatchRecords();
        batchRecords.verifyBatchRecord("Express Stock");
        batchRecords.clickSendTransactions();
        expressStock.verifyTransaction(true, toLDC, partCodeSerialized, assetCode, 0);
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Perform express stock for Part in batch mode
    @TestRailCase(selfReporting = true, automationId = "5039")
    @Test(priority = 139, enabled = true, dependsOnMethods = "expressStock0039")
    public void expressStock0040() {
        menu.selectMenu();
        menu.toggleBatchMode();
        expressStock.execute(true, toLDC, partCodeLN, 1, projectCode, poCode, createNewPOLine());
        menu.toggleBatchMode();
        menu.selectBatchRecords();
        batchRecords.verifyBatchRecord("Express Stock");
        batchRecords.clickSendTransactions();
        expressStock.verifyTransaction(true, toLDC, partCodeSerialized, null, 1);
        Assert.assertEquals(expressStock.getResult(), true);
    }
}
