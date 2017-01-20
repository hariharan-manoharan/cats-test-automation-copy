package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class BVTTest2 extends VerizonTest {

    //Routine Page Objects

    private POReceive poReceive;
    private CancelReceive cancelReceive;
;
    private MiscQuarantine miscQuarantine;
    private ReturnQuarantineToVendor returnQuarantineToVendor;

    private String mrCode;
    private String assetCode;

    private String projectCode;
    private String poCode;
    private int qty;
    private String partCodeNS = "00-4929-1";
    private String partCodeS = "44WA1";
    private String returnPartCodeNS = "00-4929-1";
    private String returnPartCodeS = "44WA1";
    private String quarantineLabel = "VZQ-PO-2896660";
    private String quarantineToLDC = "QNS236520";

    private ArrayList<String> locationDetailCodes = new ArrayList<String>();

    @BeforeClass(alwaysRun = true)
    public void StageData() {

        poReceive = new POReceive(mobilityDriver, oracleDriver, routines);
        cancelReceive = new CancelReceive(mobilityDriver, oracleDriver);
        miscQuarantine = new MiscQuarantine(mobilityDriver, oracleDriver, routines);
        returnQuarantineToVendor= new ReturnQuarantineToVendor(mobilityDriver, oracleDriver, routines);

        poCode = oracleDriver.stagePOData();
        containerCode = oracleDriver.generateContainerCode();
        assetCode = "A1-" + poCode;
        assetCode2 = mrCode + "A2";
        oracleDriver.createPart("CNS236520", 10);
        toLDC = "SNS236520";
        partCodeLN = "0-U6-16DS1-HSB";
        partCodeSU = "01-61084003";
        partCodeSO = "#18/2";
        routines.selectRoutine("PO Receive");


    }

    @TestRailCase(automationId = "5017")
    @Test (priority = 1100, enabled = true)
    public void POR_0001(){
        poReceive.execute(poCode, "40.1", assetCode);
        Assert.assertEquals(poReceive.getResult(), true);
    }

    //Receive LN Item
    @TestRailCase(automationId = "5018")
    @Test (priority = 1101, enabled = true)
    public void POR_0002(){
        poReceive.execute(poCode, "10.1", 1);
        Assert.assertEquals(poReceive.getResult(), true);
    }

    //Cancel LS Item
    @TestRailCase(automationId = "5021")
    @Test (priority = 1105, enabled = true)
    public void POC_0001(){
        cancelReceive.clickBackRoutines();
        routines.selectRoutine("Cancel Receive");
        cancelReceive.execute(false, poCode, "40.1", assetCode);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }

    //Cancel LN Item
    @TestRailCase(automationId = "5022")
    @Test (priority = 1106, enabled = true)
    public void POC_0002(){
        cancelReceive.execute(false, poCode, "10.1", 1);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }

    @TestRailCase(automationId = "5048")
    @Test (priority = 1114)
    public void miscQuarantine001(){
        returnQuarantineToVendor.clickBackRoutines();
        routines.selectRoutine("Misc Quarantine");
        miscQuarantine.executeRoutine(quarantineToLDC, quarantineLabel, partCodeNS, 10);
    }

    @TestRailCase(automationId = "5049")
    @Test (priority = 1115)
    public void miscQuarantine002(){
        miscQuarantine.executeRoutine(quarantineToLDC, quarantineLabel, partCodeS, 1);
    }

    @TestRailCase(automationId = "5050")
    @Test (priority = 1116, enabled = true)
    public void returnQuarantine001() {
        returnQuarantineToVendor.clickBackRoutines();
        routines.selectRoutine("Return Quarantine To Vendor");
        returnQuarantineToVendor.executeRoutine(false, quarantineLabel, returnPartCodeNS, 1);
    }

    @TestRailCase(automationId = "5051")
    @Test (priority = 1117, enabled = true)
    public void returnQuarantine002() {
        returnQuarantineToVendor.executeRoutine(false, quarantineLabel, returnPartCodeS, 1);
    }

}
