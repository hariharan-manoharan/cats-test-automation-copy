package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.Drivers.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;


@Listeners(TestRailListener.class)
public class ChangeAssetTagTest extends VerizonTest{

	private ChangeAssetTag changeAssetTag;
	private String assetCode;
	private String newAssetCode;

	private final String assetCodeInvalid = "ASSET CODE ENTERED DOES NOT EXIST.";
	private final String newAssetExists = "NEW ASSET TAG ENTERED EXISTS AND CANNOT BE USED.";

	@BeforeClass(alwaysRun = true)
	public void stageData() {
		changeAssetTag = new ChangeAssetTag(mobilityDriver, oracleDriver, routines);
		toLDC = "CNS103383";
        changeAssetTag.selectRoutine();
	}

	//Regression for Inventory Shipping
	@TestRailCase(automationId = "9003")
	@Test(priority = 300, enabled = true)
	public void verifyRoutineTitleHeader() {
        assetCode = oracleDriver.createAsset(toLDC);
        newAssetCode = "NEWASSET" + getRandomNumber(100000);
        changeAssetTag.verifyRoutineHeader();
		changeAssetTag.verifyRoutineTitle("Change Asset Tag");
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9005")
	@Test(priority = 302, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void verifyAssetCodeRequired() {
		changeAssetTag.testRequiredField("Asset Code");
		changeAssetTag.clickOk();
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9006")
	@Test(priority = 303, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void verifyAssetCodeInvalid() {
		changeAssetTag.assetCode(true, "INVALID");
		changeAssetTag.nextField();
		changeAssetTag.verifyMessage(assetCodeInvalid);
		changeAssetTag.clickOk();
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9007")
	@Test(priority = 304, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void enterAssetCode() {
		changeAssetTag.assetCode(true, assetCode);
		changeAssetTag.nextField();
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9008")
	@Test(priority = 305, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void verifyNewAssetTagRequired() {
		changeAssetTag.testRequiredField("New Asset Tag");
		changeAssetTag.clickOk();
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9009")
	@Test(priority = 306, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void verifyNewAssetCodeExists() {
		changeAssetTag.newAssetTag(true, assetCode);
		changeAssetTag.nextField();
		changeAssetTag.verifyMessage(newAssetExists);
		changeAssetTag.clickOk();
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9010")
	@Test(priority = 307, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void enterNewAssetCode() {
		changeAssetTag.newAssetTag(true, newAssetCode);
		changeAssetTag.nextField();
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9011")
	@Test(priority = 308, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void emptyNotes() {
		changeAssetTag.nextField();
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9012")
	@Test(priority = 309, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void verifyLoopfield() {
		changeAssetTag.verifyLoopfield(true, "Asset Code");
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9013")
	@Test(priority = 310, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void enterNotes() {
		changeAssetTag.assetCode(true, newAssetCode);
		changeAssetTag.nextField();
		changeAssetTag.newAssetTag(true, assetCode);
		changeAssetTag.nextField();
		changeAssetTag.notes(false, "NOTES");
		changeAssetTag.nextField();
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

	@TestRailCase(automationId = "9014")
	@Test(priority = 311, enabled = true, dependsOnMethods = "verifyRoutineTitleHeader")
	public void batchModeTransaction() {
		menu.toggleBatchMode();
		changeAssetTag.executeRoutine(true, assetCode, newAssetCode);
		menu.toggleBatchMode();
		menu.selectBatchRecords();
		batchRecords.verifyBatchRecord("Change Asset Tag");
		batchRecords.clickSendTransactions();
		changeAssetTag.verifyTransaction(true, "CHANGE_LABEL");
		Assert.assertEquals(changeAssetTag.getResult(), true);
	}

}
