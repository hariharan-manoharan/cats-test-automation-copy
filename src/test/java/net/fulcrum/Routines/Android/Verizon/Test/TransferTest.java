package net.fulcrum.Routines.Android.Verizon.Test;
import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.Drivers.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.TestRailReporter;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Pages.Android.Test.MainTest;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Listeners(TestRailListener.class)
public class TransferTest extends VerizonTest {

	private Transfer transfer;

	private int qty;
	private String fromLDC;
	private String toLDC;
	private String toLDCReuse;
	private String toLDC2;
	private String assetCode;
	private String assetCode2;
	private String partCode;
	private String containerCode;
	private String reuseLocation;
	private String reuseLocationDescription;
	private String today;
	private String yesterday;
	private String tomorrow;
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private Date date = new Date();


	private final String invalidBarcode = "BARCODE ENTERED NOT FOUND. DO YOU WANT TO STOCK THIS ITEM AS A NEW " +
			"ASSETCODE? Y/N";
	private final String invalidDate = "REUSE DEPLOYMENT DATE MUST BE GREATER THAN OR EQUAL TO CURRENT DATE.";

	private final String interBUTransfer = "TO LOCATION BU DOES NOT MATCH THE FROM LOCATION BU FOR THE ITEM ENTERED. " +
			"PLEASE CONFIRM INTER-BU TRANSFER. Y/N";

	private final String invalidDateFormat = "DEVICE: Dates must be entered as MMDDYYYY";

	@BeforeClass(alwaysRun = true)
	public void StageData() {
		routines.selectRoutine("Transfer");
		transfer = new Transfer(mobilityDriver, oracleDriver, routines);
		partCodeSerialized = "44WR8";
		fromLDC = "CNS236520";
		toLDC = "SNS236520";
		toLDC2 = "SNS103383";
		toLDCReuse = "ERNS236520";
		reuseLocation = "236520";
		projectCode = createNewProjectCode();
		poCode = createNewPOCode();
		oracleDriver.createPart(fromLDC, qty, projectCode, poCode, 1);
		assetCode = oracleDriver.createAsset(fromLDC);
		assetCode2 = oracleDriver.createAsset(fromLDC);
		today = dateFormat.format(date);
		date = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
		yesterday = dateFormat.format(date);
		date = new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L);
		tomorrow = dateFormat.format(date);
	}

	//Transfer an Asset
	@TestRailCase(automationId = "5012")
	@Test(priority = 100, enabled = false)
	public void transfer001() {
		transfer.executeRoutine(false, assetCode, fromLDC, toLDC, 1);
	}

	//Transfer a LN item
	@TestRailCase(automationId = "5013")
	@Test(priority = 101, enabled = true)
	public void transfer002() {
		partCode = "1101-959";
		qty = 8;
		oracleDriver.createPart(fromLDC, partCode, qty);
		transfer.executeRoutine(false, partCode, fromLDC, toLDC, qty);
	}

	//Transfer a SO item
	@TestRailCase(automationId = "5014")
	@Test(priority = 102, enabled = true)
	public void transfer003() {
		partCode = "1184007L1";
		qty = 1;
		oracleDriver.createPart(fromLDC, partCode, qty);
		transfer.executeRoutine(false, partCode, fromLDC, toLDC, qty);
	}

	//Transfer a SU item
	@TestRailCase(automationId = "5015")
	@Test(priority = 103, enabled = true)
	public void transfer004() {
		partCode = "120-110-1001";
		qty = 1;
		oracleDriver.createPart(fromLDC, partCode, qty);
		transfer.executeRoutine(false, partCode, fromLDC, toLDC, qty);
	}

	//Transfer a Container
	@TestRailCase(automationId = "5016")
	@Test(priority = 104, enabled = true)
	public void transfer005() {
		containerCode = oracleDriver.createContainerAsset(fromLDC, toLDC);
		transfer.executeRoutine(false, containerCode, fromLDC, toLDC, 1);
	}

	@TestRailCase(automationId = "1005")
	@Test(priority = 110, enabled = true)
	public void verifyRoutineTitleHeader() {
		transfer.reset();
		transfer.verifyRoutineHeader();
		transfer.verifyRoutineTitle("Transfer");
	}

	@TestRailCase(automationId = "1006")
	@Test(priority = 111, enabled = true)
	public void verifyContainerLabelRequired() {
		transfer.testRequiredField("To LDC");
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Verify Invalid To LDC
	@TestRailCase(automationId = "1004")
	@Test(priority = 117, enabled = true)
	public void verifyToLDCInvalid() {
		System.out.println("IN 013");
		transfer.toLDC(true, "INVALID LDC");
		transfer.nextField();
		transfer.verifyMessage(invalidToLDC);
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter valid To LDC
	@TestRailCase(automationId = "1004")
	@Test(priority = 118, enabled = true, dependsOnMethods = "verifyToLDCInvalid")
	public void enterToLDC() {
		transfer.toLDC(true, toLDCReuse);
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Verify Location Description is not clickable
	@TestRailCase(selfReporting = true, automationId = "5008")
	@Test(priority = 119, enabled = true, dependsOnMethods = "enterToLDC")
	public void verifyLocationDescriptionNonEditable() {
		transfer.clickPreviousRoutineDetail("To Location Description");
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Validate Location Description
	@TestRailCase(selfReporting = true, automationId = "5010")
	@Test(priority = 120, enabled = true, dependsOnMethods = "enterToLDC")
	public void verifyToLocationDescription() {
		String locationDescription = oracleDriver.getLDCDescription(toLDCReuse);
		transfer.verifyRoutineValue(locationDescription);
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Validate Populated BU
	@TestRailCase(selfReporting = true, automationId = "5011")
	@Test(priority = 121, enabled = true, dependsOnMethods = "verifyToLocationDescription")
	public void validatePopulatedBU() {
		toLocationBU = oracleDriver.getLocationBU(toLDCReuse);
		transfer.verifyCurrentRoutineValue(toLocationBU);
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Validate To Location BU Required
	@TestRailCase(selfReporting = true, automationId = "5012")
	@Test(priority = 122, enabled = true, dependsOnMethods = "validatePopulatedBU")
	public void validateToLocationBURequired() {
		mobilityDriver.clearField();
		transfer.testRequiredField("To Location BU");
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter invalid To Location BU
	@TestRailCase(selfReporting = true, automationId = "5013")
	@Test(priority = 123, enabled = true, dependsOnMethods = "validateToLocationBURequired")
	public void validateToLocationBUInvalid() {
		transfer.toLocationBU(true, "INVALID BU");
		transfer.nextField();
		transfer.verifyMessage(invalidToBU);
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter valid BU
	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 124, enabled = true, dependsOnMethods = "validateToLocationBUInvalid")
	public void enterBu() {
		transfer.toLocationBU(true, toLocationBU);
		transfer.clickValidationFile();
		transfer.clickLookupValue(toLocationBU);
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Validate Barcode Required
	@TestRailCase(selfReporting = true, automationId = "5012")
	@Test(priority = 125, enabled = true, dependsOnMethods = "enterBu")
	public void validateBarcodeRequired() {
		transfer.testRequiredField("Barcode");
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 127, enabled = true, dependsOnMethods = "enterBu")
	public void enterBarcodeSerialized() {
		transfer.barcode(true, assetCode2);
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 128, enabled = true, dependsOnMethods = "enterBarcodeSerialized")
	public void enterToReuseProjectLocation() {
		transfer.toReuseProjectlocation(reuseLocation);
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 129, enabled = true, dependsOnMethods = "enterToReuseProjectLocation")
	public void verifyReuseLocationDescription() {
		reuseLocationDescription = oracleDriver.getLDCDescription(toLDCReuse);
		transfer.verifyRoutineValue(reuseLocationDescription);
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 130, enabled = true, dependsOnMethods = "verifyReuseLocationDescription")
	public void enterInvalidToReuseDate() {
		transfer.toReuseDate(yesterday);
		transfer.nextField();
		transfer.verifyMessage(invalidDate);
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 131, enabled = true, dependsOnMethods = "enterInvalidToReuseDate")
	public void enterInvalidToReuseDateFormat() {
		transfer.toReuseDate("103342016");
		transfer.nextField();
		transfer.verifyMessage(invalidDateFormat);
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 132, enabled = true, dependsOnMethods = "enterInvalidToReuseDateFormat")
	public void enterToReuseDateTomorrow() {
		mobilityDriver.clearField();
		transfer.toReuseDate(tomorrow);
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 133, enabled = true, dependsOnMethods = "enterToReuseDateTomorrow")
	public void enterToReuseDateToday() {
		transfer.clickPreviousRoutineDetail("To Reuse Date (MMDDYYYY)");
		transfer.toReuseDate(today);
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 134, enabled = true, dependsOnMethods = "enterToReuseDateToday")
	public void enterToReuseComment() {
		transfer.toReuseComment("Reuse");
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 135, enabled = true, dependsOnMethods = "enterToReuseComment")
	public void enterNotes() {
		transfer.notes(false, "Notes");
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 136, enabled = true, dependsOnMethods = "enterNotes")
	public void verifyLoopfield() {
		transfer.verifyRoutineDetail("Barcode", true);
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5014")
	@Test(priority = 137, enabled = true, dependsOnMethods = "verifyLoopfield")
	public void enterBarcodeNonSerialized() {
		transfer.barcode(true, "#18/2");
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Verify From LDC required
	@TestRailCase(automationId = "2047")
	@Test(priority = 138, enabled = true, dependsOnMethods = "enterBarcodeNonSerialized")
	public void verifyFromLDCRequired() {
		mobilityDriver.clearField(1);
		transfer.testRequiredField("From LDC");
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Verify Invalid From LDC
	@TestRailCase(automationId = "2036")
	@Test(priority = 139, enabled = true, dependsOnMethods = "verifyFromLDCRequired")
	public void verifyFromLDCInvalid() {
		transfer.fromLDC(true, "INVALID LDC");
		transfer.nextField();
		transfer.verifyMessage(invalidFromLDC);
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter valid From LDC
	@TestRailCase(automationId = "2037")
	@Test(priority = 140, enabled = true, dependsOnMethods = "verifyFromLDCInvalid")
	public void enterFromLDC() {
		transfer.fromLDC(true, "");
		transfer.clickValidationFile();
		transfer.enterValidationFileSearch(fromLDC);
		transfer.clickLookupValue(fromLDC);
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Validate Populated BU
	@TestRailCase(selfReporting = true, automationId = "2040")
	@Test(priority = 141, enabled = true, dependsOnMethods = "enterFromLDC")
	public void validatePopulatedFromBU() {
		fromLocationBU = oracleDriver.getLocationBU(fromLDC);
		transfer.verifyRoutineValue(fromLocationBU);
		transfer.fromLocationBU(true, fromLocationBU);
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter valid value for PO
	@TestRailCase(selfReporting = true, automationId = "5033")
	@Test(priority = 142, enabled = true, dependsOnMethods = "validatePopulatedFromBU")
	public void enterEmptyPO() {
		transfer.poCode(false, "");
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter valid value for PO Line
	@TestRailCase(selfReporting = true, automationId = "5034")
	@Test(priority = 143, enabled = true, dependsOnMethods = "enterEmptyPO")
	public void enterEmptyPOLine() {
		transfer.lineNumber(false, "");
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter valid value for PO Line
	@TestRailCase(selfReporting = true, automationId = "5034")
	@Test(priority = 144, enabled = true, dependsOnMethods = "enterEmptyPOLine")
	public void enterEmptyProject() {
		transfer.projectCode(false, "");
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter empty reuse project
	@TestRailCase(selfReporting = true, automationId = "5034")
	@Test(priority = 145, enabled = true, dependsOnMethods = "enterEmptyProject")
	public void enterEmptyReuseProject() {
		transfer.toReuseProjectlocation("");
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Validate required Quantity
	@TestRailCase(selfReporting = true, automationId = "5018")
	@Test(priority = 146, enabled = true, dependsOnMethods = "enterEmptyReuseProject")
	public void verifyRequiredQuantity() {
		transfer.testRequiredField("Quantity");
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter valid Quantity
	@TestRailCase(selfReporting = true, automationId = "5019")
	@Test(priority = 147, enabled = true, dependsOnMethods = "verifyRequiredQuantity")
	public void enterQuantity() {
		qty = 1;
		transfer.quantity(true, qty);
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter empty notes
	@TestRailCase(selfReporting = true, automationId = "5034")
	@Test(priority = 148, enabled = true, dependsOnMethods = "enterQuantity")
	public void enterEmptyNotes() {
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Enter new asset
	@TestRailCase(selfReporting = true, automationId = "5034")
	@Test(priority = 149, enabled = true, dependsOnMethods = "enterQuantity")
	public void enterNewAsset() {
		transfer.barcode(true, createNewAssetLabel());
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Verify new Asset message
	@TestRailCase(selfReporting = true, automationId = "5034")
	@Test(priority = 150, enabled = true, dependsOnMethods = "enterQuantity")
	public void verifyNewAssetMessage() {
		transfer.verifyMessage(invalidBarcode);
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "5034")
	@Test(priority = 151, enabled = true, dependsOnMethods = "enterQuantity")
	public void enterConfirmNewAsset() {
		transfer.confirmStockAsset("Y");
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Validate required Part Code
	@TestRailCase(selfReporting = true, automationId = "2023")
	@Test(priority = 152, enabled = true, dependsOnMethods = "enterConfirmNewAsset")
	public void verifyPartCodeRequired() {
		transfer.testRequiredField("Part Code");
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	//Validate required Part Code
	@TestRailCase(selfReporting = true, automationId = "2023")
	@Test(priority = 153, enabled = true, dependsOnMethods = "verifyPartCodeRequired")
	public void enterPartCode() {
		transfer.partCode(true, partCodeSerialized);
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "2023")
	@Test(priority = 154, enabled = true, dependsOnMethods = "enterPartCode")
	public void notesRequiredStock() {
		transfer.nextField();
		transfer.nextField();
		transfer.nextField();
		transfer.nextField();
		transfer.nextField();
		transfer.nextField();
		transfer.testRequiredField("Notes");
		transfer.clickOk();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "2023")
	@Test(priority = 155, enabled = true, dependsOnMethods = "notesRequiredStock")
	public void enterNotesRequired() {
		transfer.notes(true, "Notes Required for Stock");
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "2023")
	@Test(priority = 156, enabled = true, dependsOnMethods = "enterNotesRequired")
	public void completeStockTransaction() {
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "2023")
	@Test(priority = 157, enabled = true, dependsOnMethods = "completeStockTransaction")
	public void transferDifferentBUYes() {
		transfer.reset();
		qty = getRandomNumber(100);
		partCodeSO = "#18/2";
		oracleDriver.createPart(fromLDC, partCodeSO, qty);
		transfer.toLDC(true, toLDC2);
		transfer.nextField();
		transfer.toLocationBU(true, oracleDriver.getLocationBU(toLDC2));
		transfer.nextField();
		transfer.barcode(true, partCodeSO);
		transfer.nextField();
		transfer.fromLDC(true, fromLDC);
		transfer.nextField();
		transfer.fromLocationBU(true, oracleDriver.getLocationBU(fromLDC));
		transfer.nextField();
		transfer.verifyMessage(interBUTransfer);
		transfer.clickOk();
		transfer.confirmInterBU("Y");
		transfer.nextField();
		transfer.nextField();
		transfer.nextField();
		transfer.nextField();
		transfer.quantity(true, qty);
		transfer.nextField();
		transfer.nextField();
		Assert.assertEquals(transfer.getResult(), true);
	}

	@TestRailCase(selfReporting = true, automationId = "2023")
	@Test(priority = 158, enabled = true, dependsOnMethods = "transferDifferentBUYes")
	public void transferDifferentBUNo() {
		transfer.reset();
		qty = getRandomNumber(100);
		partCodeSO = "#18/2";
		oracleDriver.createPart(fromLDC, partCodeSO, qty);
		transfer.toLDC(true, toLDC2);
		transfer.nextField();
		transfer.toLocationBU(true, oracleDriver.getLocationBU(toLDC2));
		transfer.nextField();
		transfer.barcode(true, partCodeSO);
		transfer.nextField();
		transfer.fromLDC(true, fromLDC);
		transfer.nextField();
		transfer.fromLocationBU(true, oracleDriver.getLocationBU(fromLDC));
		transfer.nextField();
		transfer.verifyMessage(interBUTransfer);
		transfer.clickOk();
		transfer.confirmInterBU("N");
		transfer.nextField();
		transfer.verifyCurrentRoutineValue(fromLDC);
		Assert.assertEquals(transfer.getResult(), true);
	}
}
