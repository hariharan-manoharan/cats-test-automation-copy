package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.Test.MainTest;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Pages.Android.Test.MainTest;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.Random;

@Listeners(TestRailListener.class)
public class RepairTest extends MainTest {

	private ToRepair toRepair;
	private FromRepair fromRepair;

	private String assetCode;
    private String partCodeS= "109091595";
	private String partCodeNS = "#18/2";
	private String fromLDC ="CNS236520";
    private String toLDC = "RNR110828";
	private String vendorRA = "9-3+=4";
	private String catsRA = "RA00419523";

	private Random rand;
	private int qty;

	@BeforeClass(alwaysRun = true)
	public void StageData() {
		toRepair = new ToRepair(mobilityDriver, oracleDriver, routines);
		fromRepair = new FromRepair(mobilityDriver, oracleDriver, routines);
		assetCode = oracleDriver.createAsset(fromLDC, partCodeS);
		rand = new Random();
		qty = rand.nextInt(100);
	}

	@TestRailCase(automationId = "5052")
	@Test(priority = 1)
	public void toRepair001(){
		routines.selectRoutine("To Repair");
		toRepair.execute(false, fromLDC, toLDC, vendorRA, catsRA, assetCode);
		Assert.assertEquals(toRepair.getResult(), true);
	}

	@TestRailCase(automationId = "5053")
	@Test(priority = 2)
	public void toRepair002(){
		toRepair.execute(false, fromLDC, toLDC, vendorRA, catsRA, partCodeNS, qty);
		Assert.assertEquals(toRepair.getResult(), true);
	}

	@TestRailCase(automationId = "5054")
	@Test(priority = 3)
	public void fromRepair001(){
		fromRepair.clickBackRoutines();
		routines.selectRoutine("From Repair");
		fromRepair.execute(false, toLDC, fromLDC, vendorRA, catsRA, assetCode);
		Assert.assertEquals(fromRepair.getResult(), true);
	}

	@TestRailCase(automationId = "5055")
	@Test(priority = 4)
	public void fromRepair002(){
		fromRepair.execute(false, toLDC, fromLDC, vendorRA, catsRA, partCodeNS, qty);
		Assert.assertEquals(fromRepair.getResult(), true);
	}
}
