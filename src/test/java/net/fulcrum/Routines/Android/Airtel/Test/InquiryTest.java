package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.*;

import net.fulcrum.Routines.Android.Airtel.ContainerInquiry;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.*;

@Listeners(TestRailListener.class)
public class InquiryTest extends MainTest {

    private final static String folder = "Inquiry";

    private AssetInquiry assetInquiry;
    private ContainerInquiry containerInquiry;
    private LocationInquiry locationInquiry;
    private PartInquiry partInquiry;
    private TransferInquiry transferInquiry;

    private String assetCode = "ASSET06295";
    private String serialNumber = "SERIAL06295";
    private String serializedPartCode = "ABC000011";
    private String nonSerializedPartCode= "E3LSN0014";
    private String partDescription = "HWAC HSDPA CODES STEP SIZE 5 - 3G";
    private String mfgPartNumber= "MULTIPLE";
    private String lot = "LOT1";
    private String locationStatus = "ON HAND";
    private String container = "CON334893";
    private String siteID = "WH1";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        assetInquiry = new AssetInquiry(mobilityDriver, oracleDriver);
        containerInquiry = new ContainerInquiry(mobilityDriver, oracleDriver);
        locationInquiry = new LocationInquiry(mobilityDriver, oracleDriver);
        partInquiry = new PartInquiry(mobilityDriver, oracleDriver);
        transferInquiry = new TransferInquiry(mobilityDriver, oracleDriver);
    }

    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 100, enabled = true)
    public void assetInquiry0001() {
        navigateToFolder();
        routines.selectRoutine("Asset Inquiry");
        assetInquiry.executeRoutine(siteID, locationStatus, lot, assetCode, serializedPartCode, serialNumber, mfgPartNumber);
        Assert.assertEquals(assetInquiry.getResult(), true);
    }


    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 101, enabled = true)
    public void containerInquiry0001() {
        String openClosed = "OPEN";
        containerInquiry.clickBackRoutines();
        routines.selectRoutine("Container Inquiry");
        containerInquiry.executeRoutine(container, siteID, openClosed, locationStatus, assetCode, nonSerializedPartCode);
        Assert.assertEquals(containerInquiry.getResult(), true);
    }


    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 102, enabled = true)
    public void locationInquiry0001() {
        String name = "MUNDKA";
        String locatorCode = "179167";
        String address = "OM LOGISTICS PLOT NO 110/20& 21 NEAR VARDHAMAN " +
                "DHARAM KANTA MUNDKA 110041 NEW DELHI";
        locationInquiry.clickBackRoutines();
        routines.selectRoutine("Location Inquiry");
        locationInquiry.executeRoutine(siteID, name, locatorCode, address);
        Assert.assertEquals(locationInquiry.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 103, enabled = true)
    public void partInquiry0001() {
        partInquiry.clickBackRoutines();
        routines.selectRoutine("Part Inquiry");
        partInquiry.executeRoutine(nonSerializedPartCode, partDescription, "N", "MULTIPLE", mfgPartNumber, "STANDALONE");
        Assert.assertEquals(partInquiry.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 103, enabled = true)
    public void transferInquiry0001() {
        String transferNumber = "MOA1";
        String type = "MO";
        String toLocation = "10003";
        String toStatus = "CWIP";
        transferInquiry.clickBackRoutines();
        routines.selectRoutine("Transfer Inquiry");
        transferInquiry.executeRoutine(transferNumber, type, siteID, toLocation, toStatus, serializedPartCode);
        Assert.assertEquals(transferInquiry.getResult(), true);
    }

    private void navigateToFolder() {
        folders.selectFolder(folder);
    }
}