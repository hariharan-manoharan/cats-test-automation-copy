package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.*;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

@Listeners(TestRailListener.class)
public class RepairTest extends MainTest {

    private final static String folder = "Repair";

    private ToRepair toRepair;
    private FromRepair fromRepair;

    private String location = "WH1";
    private String jobNumber = "JOB123";
    private String rmaNumber = "RMA123";
    private String failBarcode = "ABC000011";
    private String failBarcode2 = "ASSET08203";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        toRepair = new ToRepair(mobilityDriver, oracleDriver);
        fromRepair = new FromRepair(mobilityDriver, oracleDriver);
    }

    //Send asset to repair
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 100, enabled = true)
    public void toRepair() {
        navigateToFolder();
        routines.selectRoutine("To Repair");
        toRepair.execute(location, jobNumber, failBarcode, failBarcode2);
        Assert.assertEquals(toRepair.getResult(), true);
    }

    //Asset from repair
    @TestRailCase(selfReporting = true, automationId = "1008")
    @Test(priority = 103, enabled = true)
    public void fromRepair0001() {
        fromRepair.clickBackRoutines();
        routines.selectRoutine("From Repair");
        fromRepair.execute(location, jobNumber, rmaNumber, failBarcode, failBarcode2);
        Assert.assertEquals(fromRepair.getResult(), true);
    }

    private void navigateToFolder() {
        folders.selectFolder(folder);
    }
}