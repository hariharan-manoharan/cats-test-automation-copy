package net.fulcrum.Routines.iOS.Verizon.Test;

import net.fulcrum.Routines.iOS.Verizon.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.iOS.Test.MainTest;

import org.openqa.selenium.By;
import org.testng.annotations.*;


@Listeners(TestRailListener.class)
public class TransferTest extends MainTest {

    private Transfer transfer;

    private int qty;
    private String fromLDC = "CNS103383";
    private String toLDC = "SNS103383";
    private String assetCode;
    private String partCode;
    private String containerCode;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        transfer = new Transfer(mobilityDriver, oracleDriver);

    }

    //Transfer an Asset
    @TestRailCase(automationId = "7022")
    @Test(priority = 100, enabled = true)
    public void transfer001() {
        element = iosDriver.findElement(By.name("Transfer"));
        element.click();
        assetCode = oracleDriver.createAsset("CNS103383");
        transfer.executeRoutine(assetCode, fromLDC, toLDC, 1);
    }

    //Transfer a LN item
    @TestRailCase(automationId = "7023")
    @Test(priority = 101, enabled = true)
    public void transfer002() {
        partCode = "1101-959";
        qty = 8;
        oracleDriver.createPart(fromLDC, partCode, qty);
        transfer.executeRoutine(partCode, fromLDC, toLDC, qty);
    }

    //Transfer a SO item
    @TestRailCase(automationId = "7024")
    @Test(priority = 102, enabled = true)
    public void transfer003() {
        partCode = "1184007L1";
        qty = 1;
        oracleDriver.createPart(fromLDC, partCode, qty);
        transfer.executeRoutine(partCode, fromLDC, toLDC, qty);
    }

    //Transfer a SU item
    @TestRailCase(automationId = "7025")
    @Test(priority = 103, enabled = true)
    public void transfer004() {
        partCode = "120-110-1001";
        qty = 1;
        oracleDriver.createPart(fromLDC, partCode, qty);
        transfer.executeRoutine(partCode, fromLDC, toLDC, qty);
    }

    //Transfer a Container
    @TestRailCase(automationId = "7026")
    @Test(priority = 104, enabled = true)
    public void transfer005() {
        containerCode = oracleDriver.createContainerAsset(fromLDC, toLDC);
        transfer.executeRoutine(containerCode, fromLDC, toLDC, 1);
    }
}
