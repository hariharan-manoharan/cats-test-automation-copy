package net.fulcrum.Routines.Android.Verizon.Test;
import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.Drivers.*;
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
public class QuarantineTest extends MainTest {

	private MiscQuarantine miscQuarantine;
	private ReturnQuarantineToVendor returnQuarantineToVendor;


	private String partCodeNS = "00-4929-1";
    private String partCodeS = "44WA1";
	private String returnPartCodeNS = "00-4929-1";
    private String returnPartCodeS = "44WA1";
	private String quarantineLabel = "VZQ-PO-2896660";
    private String quarantineToLDC = "QNS236520";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        miscQuarantine = new MiscQuarantine(mobilityDriver, oracleDriver, routines);
        returnQuarantineToVendor= new ReturnQuarantineToVendor(mobilityDriver, oracleDriver, routines);
    }

	@TestRailCase(automationId = "5048")
	@Test (priority = 1)
	public void miscQuarantine001(){
        routines.selectRoutine("Misc Quarantine");
		miscQuarantine.executeRoutine(quarantineToLDC, quarantineLabel, partCodeNS, 10);
	}

    @TestRailCase(automationId = "5049")
    @Test (priority = 2)
    public void miscQuarantine002(){
        miscQuarantine.executeRoutine(quarantineToLDC, quarantineLabel, partCodeS, 1);
    }
    @TestRailCase(automationId = "5050")
	@Test (priority = 3, enabled = true)
	public void returnQuarantine001() {
        returnQuarantineToVendor.clickBackRoutines();
        routines.selectRoutine("Return Quarantine To Vendor");
		returnQuarantineToVendor.executeRoutine(false, quarantineLabel, returnPartCodeNS, 1);
	}

    @TestRailCase(automationId = "5051")
    @Test (priority = 4, enabled = true)
    public void returnQuarantine002() {
        returnQuarantineToVendor.executeRoutine(false, quarantineLabel, returnPartCodeS, 1);
    }

}
