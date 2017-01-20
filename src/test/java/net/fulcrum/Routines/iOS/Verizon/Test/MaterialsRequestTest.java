package net.fulcrum.Routines.iOS.Verizon.Test;

import net.fulcrum.Routines.iOS.Verizon.InventoryPicking;
import net.fulcrum.Routines.iOS.Verizon.InventoryShipping;
import net.fulcrum.Routines.iOS.Verizon.InventoryUnpick;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.iOS.Test.MainTest;

import org.openqa.selenium.By;
import org.testng.annotations.*;



@Listeners(TestRailListener.class)
public class MaterialsRequestTest extends MainTest {

    private InventoryShipping inventoryShipping;
    private InventoryUnpick inventoryUnpick;
    private InventoryPicking inventoryPicking;

    private String barcode;
    private String lineNumber;
    private String mrCode;
    private String containerCode;
    private int qty;
    private String toLDC;
    private String fromLDC;
    private String location = "103383";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        inventoryPicking = new InventoryPicking(mobilityDriver, oracleDriver);
        inventoryShipping = new InventoryShipping(mobilityDriver, oracleDriver);
        inventoryUnpick = new InventoryUnpick(mobilityDriver, oracleDriver);
        mrCode = "MR494522";
      //  mrCode = oracleDriver.stageMaterialsRequest(location);

    }

    /*
    //Pick LS Item
    @TestRailCase(automationId = "7005")
    @Test(priority = 101, enabled = true)
    public void inventoryPick0001 () {
        element = iosDriver.findElement(By.name("Inventory Picking"));
        element.click();
        fromLDC = "CNS103383";
        toLDC = "GNS103383";
        barcode = mrCode + "A1";
        containerCode = "VZC-" + mrCode + "C1";
        inventoryPicking.execute(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, 1);
    }


    //Pick SO Item
    @TestRailCase(automationId = "7008")
    @Test(priority = 102, enabled = true)
    public void inventoryPick0002 () {
        fromLDC = "CNS103383";
        toLDC = "GNS103383";
        barcode = "#18/2";
        containerCode = "VZC-" + mrCode + "C1";
        lineNumber = "3";
        inventoryPicking.execute(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, 1);
    }



    //Pick LN Item
    @TestRailCase(automationId = "7006")
    @Test(priority = 103, enabled = true)
    public void inventoryPick0003 () {
        fromLDC = "CNS103383";
        toLDC = "GNS103383";
        barcode = "0-U6-16DS1-HSB";
        containerCode = "VZC-" + mrCode + "C1";
        lineNumber = "5";
        inventoryPicking.execute(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, 10);
    }

    //Pick SU Item
    @TestRailCase(automationId = "7007")
    @Test(priority = 104, enabled = true)
    public void inventoryPick0004 () {
        fromLDC = "CNS103383";
        toLDC = "GNS103383";
        barcode = "01-61084003";
        containerCode = "VZC-" + mrCode + "C1";
        lineNumber = "4";
        inventoryPicking.execute(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, 5);
    }

    */

    //Unpick LS Item
    @TestRailCase(automationId = "7009")
    @Test(priority = 105, enabled = true)
    public void inventoryUnpick0001 () {
        //mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Inventory Unpick"));
        element.click();

        toLDC = "CNS103383";
        fromLDC = "GNS103383";
        barcode = mrCode + "A1";
        containerCode = "VZC-" + mrCode + "C1";
        inventoryUnpick.execute(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, 1);
    }

    //Unpick SO Item
    @TestRailCase(automationId = "7012")
    @Test(priority = 106, enabled = true)
    public void inventoryUnpick0002 () {
        toLDC = "CNS103383";
        fromLDC = "GNS103383";
        barcode = "#18/2";
        containerCode = "VZC-" + mrCode + "C1";
        lineNumber = "3";
        inventoryUnpick.execute(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, 1);
    }

    //Unpick SU Item
    @TestRailCase(automationId = "7010")
    @Test(priority = 107, enabled = true)
    public void inventoryUnpick0003 () {
        toLDC = "CNS103383";
        fromLDC = "GNS103383";
        barcode = "01-61084003";
        containerCode = "VZC-" + mrCode + "C1";
        lineNumber = "4";
        inventoryUnpick.execute(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, 1);
    }

    //Unpick LN Item
    @TestRailCase(automationId = "7011")
    @Test(priority = 108, enabled = true)
    public void inventoryUnpick0004 () {
        toLDC = "CNS103383";
        fromLDC = "GNS103383";
        barcode = "0-U6-16DS1-HSB";
        containerCode = "VZC-" + mrCode + "C1";
        lineNumber = "5";
        inventoryUnpick.execute(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, 2);
    }

    //Pick LS Item
    @Test(priority = 109, enabled = true)
    public void inventoryPick0005 () {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Inventory Picking"));
        element.click();
        inventoryPicking.setInitial();
        fromLDC = "CNS103383";
        toLDC = "GNS103383";
        barcode = mrCode + "A2";
        containerCode = "VZC-" + mrCode + "C2";
        inventoryPicking.execute(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, 1);
    }

    //Ship Container with one Asset
    @TestRailCase(automationId = "7013")
    @Test(priority = 110, enabled = true)
    public void inventoryShip0001 () {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Inventory Shipping"));
        element.click();
        toLDC = "TN0100091";
        containerCode = "VZC-" + mrCode + "C2";
        inventoryShipping.execute(mrCode, lineNumber, toLDC, containerCode);
    }

    //Ship Container with SO, SU, LN items
    @TestRailCase(automationId = "7014")
    @Test(priority = 111, enabled = true)
    public void inventoryShip0002 () {
        toLDC = "TN0100091";
        containerCode = "VZC-" + mrCode + "C1";
        inventoryShipping.execute(mrCode, lineNumber, toLDC, containerCode);
    }
}
