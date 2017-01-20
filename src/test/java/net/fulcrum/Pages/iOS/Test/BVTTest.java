package net.fulcrum.Pages.iOS.Test;

import net.fulcrum.testrail.annotations.TestRailCase;

import org.testng.Assert;
import org.testng.annotations.*;


public class BVTTest extends MainTest {

    @TestRailCase(automationId = "6005")
    @Test(priority = 2, enabled = true)
    public void navigateToMoreInfo () {
        login.clickInfo();
    }

    @TestRailCase(automationId = "6018")
    @Test(priority = 3, enabled = false)
    public void validateCopyright () {
        boolean result = help.validateCopyright("© Copyright 2001-2016");
        Assert.assertEquals(result, true);
    }


    @TestRailCase(automationId = "6016")
    @Test(priority = 4, enabled = false)
    public void validateBuildDate () {
        boolean result = help.validateBuildInfo("Build Date: 20160218");
        Assert.assertEquals(result, true);
    }

    @TestRailCase(automationId = "6022")
    @Test(priority = 5, enabled = false)
    public void validateBuildTime () {
        boolean result = help.validateBuildInfo("Build Time: 11:08:19");
        Assert.assertEquals(result, true);
    }

    @TestRailCase(automationId = "6008")
    @Test(priority = 6, enabled = false)
    public void validateMobilityVersion () {
        boolean result = help.validateVersion("Mobility Version 2.6.0.6");
        Assert.assertEquals(result, true);
    }

    @TestRailCase(automationId = "6019")
    @Test(priority = 7, enabled = false)
    public void validateLineaSDK () {
        boolean result = help.validateSDK("Linea SDK: 1.97");
        Assert.assertEquals(result, true);
    }

    @TestRailCase(automationId = "6020")
    @Test(priority = 8, enabled = false)
    public void validateCaptuvoSDK () {
        boolean result = help.validateSDK("Captuvo SDK: 2.19.742");
        Assert.assertEquals(result, true);
    }

    @TestRailCase(automationId = "6021")
    @Test(priority = 9, enabled = false)
    public void validateCognexSDK () {
        boolean result = help.validateSDK("Cognex SDK: v1.1.0 (32)");
        Assert.assertEquals(result, true);
    }

    @TestRailCase(automationId = "6007")
    @Test(priority = 10, enabled = true)
    public void navigateToHelp () {
        help.clickHelp();
        help.clickDone();
    }

    @TestRailCase(automationId = "6015")
    @Test(priority = 12, enabled = true)
    public void verifyRememberMe() {
        profiles.logout();
        login.clickConnect();
    }

    @TestRailCase(automationId = "6008")
    @Test(priority = 14, enabled = true)
    public void downloadValidationList() {
        mainMenu.clickValidationsButton();
        result = validations.downloadFile(6);
        Assert.assertEquals(result, true);
        result = validations.clickValidationFile(6);
        Assert.assertEquals(result, true);
        //validations.clickBackButton();
        //validations.clickBackButton();
    }

    @TestRailCase(automationId = "6004")
    @Test(priority = 15,  enabled = false)
    public void searchByPartCode() {
        mainMenu.clickViewInventory();
        viewInventory.enterPartCode("#18/2");
        viewInventory.clickSearch();
        viewInventory.clickBackButton();
        viewInventory.clickBackButton();

    }

    @TestRailCase(automationId = "6009")
    @Test(priority = 16, enabled = false)
    public void searchByLocationName() {
        mainMenu.clickViewInventory();
        viewInventory.enterLocationName("236520");
        viewInventory.clickSearch();
        viewInventory.clickBackButton();
        viewInventory.clickBackButton();
    }

    @TestRailCase(automationId = "6010")
    @Test(priority = 17, enabled = false)
    public void viewBatchRecords() {
        mainMenu.clickBatchButton();
        mainMenu.clickViewBatch();
        mainMenu.clickBackButton();
    }

}
