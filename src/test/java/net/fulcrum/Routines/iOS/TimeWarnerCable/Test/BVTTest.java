package net.fulcrum.Routines.iOS.TimeWarnerCable.Test;

import net.fulcrum.Pages.iOS.Test.MainTest;
import net.fulcrum.Routines.iOS.TimeWarnerCable.*;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.*;

@Listeners(TestRailListener.class)
public class BVTTest extends MainTest {

    private AddAsset addAsset;
    private AssetInquiry assetInquiry;
    private AdvanceReplacement advanceReplacement;
    private AdvanceReplacementDefReturn advanceReplacementDefReturn;
    private DecommissionAssets decommissionAssets;
    private DefectiveReturnToMSDI defectiveReturn;
    private InServiceToDefective inServiceToDefective;
    private PlaceAssetInService placeAssetInService;
    private RetireAssets retireAssets;
    private SerialInquiry serialInquiry;
    private TransferAsset transferAsset;

    private String stockLDC = "SV1D7HA16K86J193294";
    private String transferLDC = "TV1FMCU02749KD03596";

    private String assetCode;
    private String assetCode2;
    private String assetCode3;
    private String repairAsset;
    private String repairSerial;
    private String serialNumber;
    private String serialNumber2;
    private String serialNumber3;
    private String serializedPartCode =  "002VA1";
    private String raCode = "RA07151";
    private String reason = "SIGNAL";;
    protected Random rand;
    private int num;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        addAsset = new AddAsset(mobilityDriver, oracleDriver);
        assetInquiry = new AssetInquiry(mobilityDriver, oracleDriver);
        advanceReplacement = new AdvanceReplacement(mobilityDriver, oracleDriver);
        advanceReplacementDefReturn = new AdvanceReplacementDefReturn(mobilityDriver, oracleDriver);
        decommissionAssets = new DecommissionAssets(mobilityDriver, oracleDriver);
        defectiveReturn = new DefectiveReturnToMSDI(mobilityDriver, oracleDriver);
        inServiceToDefective = new InServiceToDefective(mobilityDriver, oracleDriver);
        placeAssetInService = new PlaceAssetInService(mobilityDriver, oracleDriver);
        retireAssets = new RetireAssets(mobilityDriver, oracleDriver);
        serialInquiry = new SerialInquiry(mobilityDriver, oracleDriver);
        transferAsset = new TransferAsset(mobilityDriver, oracleDriver);
        num = rand.nextInt(1000000) + 1;
        repairAsset = "A" + num;
        repairSerial = "S" + num;
    }

    //Add an Asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 100, enabled = true)
    public void addAsset0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Add Asset"));
        num = rand.nextInt(1000000) + 1;
        assetCode = "A" + num;
        serialNumber = "S" + num;
        addAsset.execute(stockLDC, serializedPartCode, assetCode, serialNumber);
        Assert.assertEquals(addAsset.getResult(), true);
    }

    //Add an Asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 101, enabled = true)
    public void addAsset0002() {
        num = rand.nextInt(1000000) + 1;
        assetCode2 = "A" + num;
        serialNumber2 = "S" + num;
        addAsset.execute(stockLDC, serializedPartCode, assetCode, serialNumber2);
        Assert.assertEquals(addAsset.getResult(), true);
    }

    //Add an Asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 102, enabled = true)
    public void addAsset0003() {
        num = rand.nextInt(1000000) + 1;
        assetCode3 = "A" + num;
        serialNumber3 = "S" + num;
        addAsset.execute(stockLDC, serializedPartCode, assetCode, serialNumber3);
        Assert.assertEquals(addAsset.getResult(), true);
    }

    //Asset Inquiry
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 103, enabled = true)
    public void assetInquiry0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Asset Inquiry"));
        addAsset.execute(stockLDC, serializedPartCode, assetCode, serialNumber);
        Assert.assertEquals(addAsset.getResult(), true);
    }

    //Serial Inquiry
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 103, enabled = true)
    public void serialInquiry0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Serial Inquiry"));
        serialInquiry.execute(serialNumber);
        Assert.assertEquals(serialInquiry.getResult(), true);
    }

    //Place Asset in Service
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 105, enabled = true)
    public void placeAssetInService0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Place Asset In Service"));
        placeAssetInService.execute(stockLDC, assetCode);
        Assert.assertEquals(placeAssetInService.getResult(), true);
    }

    //InServiceToDefective
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 106, enabled = true)
    public void inServiceToDefective0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("In Service To Defective"));
        inServiceToDefective.execute(stockLDC, assetCode, reason);
        Assert.assertEquals(inServiceToDefective.getResult(), true);
    }

    //Transfer Asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 107, enabled = true)
    public void transferAsset0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Transfer Asset"));
        transferAsset.execute(transferLDC, assetCode);
        Assert.assertEquals(transferAsset.getResult(), true);
    }

    //Decommission Assets
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 108, enabled = true)
    public void decommissionAssets0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Decommission Asset"));
        decommissionAssets.execute(transferLDC, assetCode);
        Assert.assertEquals(decommissionAssets.getResult(), true);
    }

    //Retire Assets
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 108, enabled = true)
    public void retireAssets0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Retire Asset"));
        retireAssets.execute(assetCode2);
        Assert.assertEquals(retireAssets.getResult(), true);
    }

    //Advance Replacement
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 109, enabled = true)
    public void advanceReplacement0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Advance Replacement"));
        advanceReplacement.execute(transferLDC, raCode, repairAsset, serializedPartCode, repairSerial);
        Assert.assertEquals( advanceReplacement.getResult(), true);
    }

    //Advance Replacement Def Return
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 110, enabled = true)
    public void advanceReplacementDefReturn0001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Advance Replacement"));
        advanceReplacementDefReturn.execute(raCode, repairAsset, assetCode, reason);
        Assert.assertEquals(advanceReplacementDefReturn.getResult(), true);
    }

    public void reset() {
        //stock.setInitial();
    }

}