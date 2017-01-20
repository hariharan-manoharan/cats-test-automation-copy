package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.*;


public class BVTTest extends VerizonTest {

    //Routine Page Objects
    private AddToContainer addToContainer;
    private CaseBreak caseBreak;
    private DestroyContainer destroyContainer;
    private ContainerInquiry containerInquiry;
    private ExpressStock expressStock;
    private Transfer transfer;
    private ToRepair toRepair;
    private FromRepair fromRepair;
    private Disposal disposal;
    private RequestReinstatement reinstatement;
    private AssetInquiry assetInquiry;
    private UpdateAsset updateAsset;
    private ChangeAssetTag changeAssetTag;
    private UpdateAssetSysAdmin updateAssetSysAdmin;

    private Random rand;
    private int num;

    private String assetCode;
    private String assetCode2;
    private String assetCode3;
    private String containerCode;
    private String containerCode2;
    private String partCode;
    private String toLDC;
    private String fromLDC = "CNS236520";
    private String toRetirementLDC;
    private String fromRetirementLDC;
    private String projectCode;
    private String poCode;
    private String retirementBatch;
    private int qty;
    private String partCodeSerialized = "EV106-H5-H5-100";
    private String partCodeSerialized2 = "44WR8";
    private String partCodeLN = "179-530090-0101";
    private String toRepairLDC = "RNR110828";
    private String vendorRA = "9-3+=4";
    private String catsRA = "RA00419522";


    private ArrayList<String> locationDetailCodes = new ArrayList<String>();

    @BeforeClass(alwaysRun = true)
    public void StageData() {


        addToContainer = new AddToContainer(mobilityDriver, oracleDriver, routines);
        caseBreak = new CaseBreak(mobilityDriver, oracleDriver, routines);
        destroyContainer = new DestroyContainer(mobilityDriver, oracleDriver, routines);
        containerInquiry = new ContainerInquiry(mobilityDriver, oracleDriver, routines);
        expressStock = new ExpressStock(mobilityDriver, oracleDriver, routines);
        transfer = new Transfer(mobilityDriver, oracleDriver, routines);
        toRepair = new ToRepair(mobilityDriver, oracleDriver, routines);
        fromRepair = new FromRepair(mobilityDriver, oracleDriver, routines);
        disposal = new Disposal(mobilityDriver, oracleDriver);
        assetInquiry = new AssetInquiry(mobilityDriver, oracleDriver);
        updateAsset = new UpdateAsset(mobilityDriver, oracleDriver, routines);
        updateAssetSysAdmin = new UpdateAssetSysAdmin(mobilityDriver, oracleDriver, routines);
        changeAssetTag = new ChangeAssetTag(mobilityDriver, oracleDriver, routines);

        retirementBatch = oracleDriver.stageRetirementBatch();
        containerCode = oracleDriver.generateContainerCode();
        assetCode2 = oracleDriver.createAsset("CNS236520");
        assetCode3 = oracleDriver.createAsset("CNS236520");
        partCode = "107893304";
        oracleDriver.createPart("CNS236520", 10);
        containerCode2 = oracleDriver.createContainerAsset("CNS236520", "SNS236520");
        locationDetailCodes.add("CNS103383");
        locationDetailCodes.add("CNS113846");
        locationDetailCodes.add("CNS109677");
        locationDetailCodes.add("CNS236520");
        toLDC = locationDetailCodes.get(getRandomNumber(4));
        toLDC = "SNS236520";
        toRetirementLDC = "DNS103383";
        fromRetirementLDC = "ZNS103383";
        projectCode = createNewProjectCode();
        poCode = createNewPOCode();
        rand = new Random();
    }

    //Express stock a LS part
    @TestRailCase(selfReporting = true, automationId = "5000")
    @Test(priority = 100)
    public void expressStock0001() {
        assetCode = createNewAssetLabel();
        expressStock.selectRoutine();
        expressStock.execute(false, toLDC, partCodeSerialized, assetCode, projectCode, poCode, createNewPOLine());
        Assert.assertEquals(expressStock.getResult(), true);
    }


    //Express stock a LN part
    @TestRailCase(selfReporting = true, automationId = "5002")
    @Test(priority = 101, enabled = true)
    public void expressStock0003() {
        expressStock.execute(false, toLDC, partCodeLN, 100, projectCode, poCode, createNewPOLine());
        Assert.assertEquals(expressStock.getResult(), true);
    }

    //Add Asset to Container
    @TestRailCase(automationId = "5004")
    @Test(priority = 102, enabled = true)
    public void addToContainer001() {
        addToContainer.reset();
        addToContainer.execute(false, toLDC, fromLDC, containerCode, assetCode);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    //Add Part to Container
    @TestRailCase(automationId = "5005")
    @Test(priority = 103, enabled = true)
    public void addToContainer003() {
        addToContainer.execute(false, toLDC, fromLDC, containerCode, partCode, 10);
        Assert.assertEquals(addToContainer.getResult(), true);

    }

    //Container Inquiry
    @TestRailCase(automationId = "5043")
    @Test(priority = 104, enabled = true)
    public void containerInquiry001() {
        containerInquiry.reset();
        containerInquiry.executeRoutine(containerCode);
        Assert.assertEquals(containerInquiry.getResult(), true);
    }

    //Case Break a Part from Container
    @TestRailCase(automationId = "5009")
    @Test(priority = 105, enabled = true)
    public void caseBreak0002() {
        caseBreak.reset();
        caseBreak.executeRoutine(false, toLDC, containerCode, partCode, 5);
        Assert.assertEquals(caseBreak.getResult(), true);
    }

    //Case Break a Asset from Container
    @TestRailCase(automationId = "5008")
    @Test(priority = 106, enabled = true)
    public void caseBreak0001() {
        caseBreak.executeRoutine(false, toLDC, containerCode, assetCode, 1);
        Assert.assertEquals(caseBreak.getResult(), true);
    }

    //Destroy Container
    @TestRailCase(automationId = "5011")
    @Test(priority = 107, enabled = true)
    public void destroyContainer001() {
        destroyContainer.reset();
        destroyContainer.executeRoutine(false, containerCode, assetCode2);
        Assert.assertEquals(destroyContainer.getResult(), true);
    }

    //Transfer an Asset
    @TestRailCase(automationId = "5012")
    @Test(priority = 108, enabled = true)
    public void transfer001() {
        transfer.reset();
        assetCode3 = oracleDriver.createAsset("CNS236520");
        transfer.executeRoutine(false, assetCode, fromLDC, toLDC, 1);
        Assert.assertEquals(transfer.getResult(), true);
    }

    //Transfer a LN item
    @TestRailCase(automationId = "5013")
    @Test(priority = 109, enabled = true)
    public void transfer002() {
        qty = 8;
        oracleDriver.createPart(fromLDC, partCodeLN, qty);
        transfer.executeRoutine(false, partCodeLN, fromLDC, toLDC, qty);
        Assert.assertEquals(transfer.getResult(), true);
    }

    @TestRailCase(automationId = "5052")
    @Test(priority = 110)
    public void toRepair001() {
        toRepair.reset();
        toRepair.execute(false, fromLDC, toRepairLDC, vendorRA, catsRA, assetCode);
        Assert.assertEquals(toRepair.getResult(), true);
    }

    @TestRailCase(automationId = "5053")
    @Test(priority = 111)
    public void toRepair002() {
        qty = getRandomNumber(100);
        oracleDriver.createPart(fromLDC, partCodeLN, qty);
        toRepair.execute(false, fromLDC, toRepairLDC, vendorRA, catsRA, partCodeLN, qty);
        Assert.assertEquals(toRepair.getResult(), true);
    }

    @TestRailCase(automationId = "5054")
    @Test(priority = 112)
    public void fromRepair001() {
        fromRepair.reset();
        fromRepair.execute(false, toRepairLDC, fromLDC, vendorRA, catsRA, assetCode);
        Assert.assertEquals(fromRepair.getResult(), true);
    }

    @TestRailCase(automationId = "5055")
    @Test(priority = 113)
    public void fromRepair002() {
        fromRepair.execute(false, toRepairLDC, fromLDC, vendorRA, catsRA, partCodeLN, qty);
        Assert.assertEquals(fromRepair.getResult(), true);
    }

    @TestRailCase(automationId = "5036")
    @Test(priority = 114, enabled = true)
    public void disposal001() {
        disposal.reset();
        disposal.executeRoutine(toRetirementLDC, fromRetirementLDC, retirementBatch, retirementBatch + "A3");
    }

    //Dispose entire batch
    @TestRailCase(automationId = "5042")
    @Test(priority = 115, enabled = true)
    public void disposal004() {
        disposal.reset();
        disposal.executeRoutine(toRetirementLDC, fromRetirementLDC, retirementBatch, "Y");
    }

    @TestRailCase(automationId = "5047")
    @Test(priority = 116, enabled = true)
    public void assetInquiry0001() {
        routines.selectRoutine("Asset Inquiry");
        assetInquiry.executeRoutine(assetCode2);
    }

    @TestRailCase(automationId = "5045")
    @Test(priority = 117, enabled = true)
    public void updateAsset0001() {
        updateAsset.reset();
        num = rand.nextInt(4);
        updateAsset.executeRoutine(false, assetCode2, locationDetailCodes.get(num));
    }

    @TestRailCase(automationId = "5046")
    @Test(priority = 118, enabled = true)
    public void updateAssetSysAdmin0001() {
        updateAssetSysAdmin.reset();
        num = rand.nextInt(4);
        updateAssetSysAdmin.executeRoutine(false, assetCode2, locationDetailCodes.get(num));
    }

    @TestRailCase(automationId = "5025")
    @Test(priority = 119, enabled = true)
    public void changeAsset0001() {
        changeAssetTag.reset();
        String newAssetCode = "NEWASSET" + rand.nextInt(10000);
        changeAssetTag.executeRoutine(false, assetCode2, newAssetCode);
    }
}
