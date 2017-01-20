package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.TestRailReporter;
import net.fulcrum.testrail.annotations.TestRailCase;


import org.testng.annotations.*;
import org.testng.Assert;

import java.net.MalformedURLException;

@Listeners(TestRailListener.class)
public class RetirementTest extends MainTest {

    private Disposal disposal;
    private RequestReinstatement reinstatement;

    private String barcode;
    private String assetCode;
    private String toLDC;
    private String fromLDC;
    private String retirementBatch;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        disposal = new Disposal(mobilityDriver, oracleDriver);
        retirementBatch =  oracleDriver.stageRetirementBatch();
       // retirementBatch =  "RET000029345835";
        toLDC = "DNS103383";
        fromLDC = "ZNS103383";

    }

    //Add Asset to Container
    @TestRailCase(automationId = "5036")
    @Test(priority = 101, enabled = true)
    public void disposal001 () {
        routines.selectRoutine("Disposal");
        assetCode = retirementBatch + "A3";
        disposal.executeRoutine(toLDC, fromLDC, retirementBatch, assetCode);
    }


    //Dispose LN item
    @TestRailCase(automationId = "5037")
    @Test(priority = 102, enabled = true)
    public void disposal002 () {
        barcode = "108773938";
        disposal.executeRoutine(toLDC, fromLDC, retirementBatch, barcode);
    }

    //Dispose SU item
    @TestRailCase(automationId = "5041")
    @Test(priority = 103, enabled = true)
    public void disposal003 () {
        barcode = "220-3B";
        disposal.executeRoutine(toLDC, fromLDC, retirementBatch, barcode);
    }

    //Dispose entire batch
    @TestRailCase(automationId = "5042")
    @Test(priority = 104, enabled = true)
    public void disposal004 () {
        disposal.setInitial();
        menu.selectAdmin();
        routines.selectRoutine("Disposal");
        barcode = "108773938";
        disposal.executeRoutine(toLDC, fromLDC, retirementBatch, "Y");
    }
}
