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

@Listeners(TestRailListener.class)
public class AdvanceRepairTest extends MainTest{
		
//create objects to reuse Routine classes
    private AdvancedRepairReceive advancedRepairReceive = new AdvancedRepairReceive(mobilityDriver, oracleDriver);
    private AdvancedRepairToRepair advancedRepairToRepair = new AdvancedRepairToRepair(mobilityDriver, oracleDriver);

    @TestRailCase(selfReporting = true, automationId="10")
	@Test(priority = 1)
    public void advancedRepair001(){
        advancedRepairReceive.executeRoutine("109091595");
       // Assert.assertEquals(result.GetSuccess(), true);

    }

    //@TestRailCase(automationId = "11")
   /* @Test(dependsOnMethods = "SelectProfile", alwaysRun= true)
    public void AdvRepRecv002(){
    	result.SetResult("AdvRepRecv002", "AdvancedRepairReceive");
        result = routines.selectRoutine(driver, "Advanced Repair Receive");
        result= advreprecv.ExecuteRoutine(driver,"107893304");
       // Assert.assertEquals(result.GetSuccess(), true);
        result.OutputResult();   
    }*/
    //@TestRailCase(automationId = "12")
  /* @Test(dependsOnMethods = "AdvRepRecv001", alwaysRun= true)
    public void AdvRepRep001(){
    	result.SetResult("AdvRepRep001", "AdvancedRepairToRepair");
        result = routines.selectRoutine(driver, "Advanced Repair To Repair");
        result= advreprep.ExecuteRoutine(driver,"109091595");
       // Assert.assertEquals(result.GetSuccess(), true);
        result.OutputResult();   
    }*/
    //@TestRailCase(automationId = "13")
	/* @Test(dependsOnMethods = "AdvRepRecv002", alwaysRun= true)
    public void AdvRepRep002(){
    	result.SetResult("AdvRepRep002", "AdvancedRepairToRepair");
        result = routines.selectRoutine(driver, "Advanced Repair To Repair");
        result= advreprep.ExecuteRoutine(driver,"107893304");
       // Assert.assertEquals(result.GetSuccess(), true);
        result.OutputResult();   
    }*/
}
