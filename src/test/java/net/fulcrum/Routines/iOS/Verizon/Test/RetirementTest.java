package net.fulcrum.Routines.iOS.Verizon.Test;

import net.fulcrum.Routines.iOS.Verizon.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.iOS.Test.MainTest;

import org.openqa.selenium.By;
import org.testng.annotations.*;



@Listeners(TestRailListener.class)
public class RetirementTest extends MainTest {

    private Disposal disposal;
    //private RequestReinstatement reinstatement;

    private String barcode;
    private String assetCode;
    private String toLDC;
    private String fromLDC;
    private String retirementBatch;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        disposal = new Disposal(mobilityDriver, oracleDriver);
        retirementBatch =  oracleDriver.stageRetirementBatch();
        toLDC = "DNS103383";
        fromLDC = "ZNS103383";

    }

    //Add Asset to Container
    @TestRailCase(automationId = "7035")
    @Test(priority = 101, enabled = true)
    public void disposal001 () {
        element = iosDriver.findElement(By.name("Disposal"));
        element.click();
        assetCode = retirementBatch + "A3";
        disposal.executeRoutine(toLDC, fromLDC, retirementBatch, assetCode);
    }

    //Add Asset to Container
    @TestRailCase(automationId = "7035")
    @Test(priority = 102, enabled = true)
    public void disposal004 () {
        element.click();
        assetCode = retirementBatch + "A2";
        disposal.executeRoutine(toLDC, fromLDC, retirementBatch, assetCode);
    }

    //Dispose LN item
    @TestRailCase(automationId = "7036")
    @Test(priority = 103, enabled = false)
    public void disposal002 () {
        barcode = "108773938";
        disposal.executeRoutine(toLDC, fromLDC, retirementBatch, barcode);
    }

    //Dispose SU item
    @TestRailCase(automationId = "7037")
    @Test(priority = 104, enabled = false)
    public void disposal003 () {
        barcode = "220-3B";
        disposal.executeRoutine(toLDC, fromLDC, retirementBatch, barcode);
    }

    //Dispose entire batch
    @TestRailCase(automationId = "7038")
    @Test(priority = 105, enabled = true)
    public void disposal005 () {
        disposal.setInitial();
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Disposal"));
        element.click();
        disposal.executeRoutine(toLDC, fromLDC, retirementBatch, "Y");
    }

}
