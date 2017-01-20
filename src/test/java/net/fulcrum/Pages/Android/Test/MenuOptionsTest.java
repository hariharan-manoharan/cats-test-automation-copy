package net.fulcrum.Pages.Android.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.Drivers.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.TestRailReporter;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.Android.*;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.Assert;

import java.net.MalformedURLException;

@Listeners(TestRailListener.class)
public class MenuOptionsTest extends MainTest{
	
	//Create object to reuse PORecv routine
		protected MenuOptions menuop = new MenuOptions(mobilityDriver);
		
		/*@Test (dependsOnMethods = "SelectProfile" , alwaysRun=true)
		public void SelectLocateInventory()
		{
			menuop.SelectMenu(driver);
			menuop.SelectLocateInventory(driver);
		}*/

		/*@Test (dependsOnMethods = "SelectProfile" , alwaysRun=true)
		public void SelectRoutines()
		{
			menuop.SelectMenu(driver);
			menuop.SelectRoutines(driver);
		}
		/*@Test (dependsOnMethods = "SelectProfile" , alwaysRun=true)
		public void SelectProfiles()
		{
			menuop.SelectMenu(driver);
			menuop.SelectProfiles(driver);
		}
		@Test (dependsOnMethods = "SelectProfile" , alwaysRun=true)
		public void SelectValidationData()
		{
			menuop.SelectMenu(driver);
			menuop.SelectValidationData(driver);
		}
		@Test (dependsOnMethods = "SelectProfile" , alwaysRun=true)
		public void SwitchToBatchMode()
		{
			menuop.SelectMenu(driver);
			menuop.SelectBatchMode(driver);
		}
		@Test (dependsOnMethods = "SelectProfile" , alwaysRun=true)
		public void SwitchToNetworkMode()
		{
			menuop.SelectMenu(driver);
			menuop.SelectNetworkMode(driver);
		}*/

	/*
		@TestRailCase(selfReporting = true, automationId="23")
		@Test(dependsOnMethods = "SelectProfile" , alwaysRun = true)
		public void Menuoptions()
		{
			menuop.SelectMenu(driver);
			menuop.SelectRoutines(driver);
			menuop.SelectMenu(driver);
			menuop.SelectLocateInventory(driver);
			menuop.SelectMenu(driver);
			menuop.SelectValidationData(driver);
			//menuop.SelectMenu(driver);
			//menuop.SelectBatchMode(driver);
			//menuop.ClickOk(driver);
			//menuop.SelectMenu(driver);
			//menuop.SelectNetworkMode(driver);
			//menuop.SelectMenu(driver);
			//menuop.SignOut(driver);
			//TestRailReporter.getInstance().reportResult("6", TestRailReporter.ResultStatus.PASS, null);
		} */
}
