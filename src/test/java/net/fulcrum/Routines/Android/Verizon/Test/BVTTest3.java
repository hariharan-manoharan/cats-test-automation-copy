package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class BVTTest3 extends VerizonTest {

    //Routine Page Objects

    private InventoryShipping inventoryShipping;
    private InventoryPicking inventoryPicking;
    private InventoryUnpick inventoryUnpick;

    private String mrCode;
    private String assetCode2;
    private String stagingLDC;
    private String receiveLDC;
    private String shipLDC;

    private String containerCode;

    private String fromLDC = "CNS236520";



    @BeforeClass(alwaysRun = true)
    public void StageData() {

        inventoryPicking = new InventoryPicking(mobilityDriver, oracleDriver, routines);
        inventoryShipping = new InventoryShipping(mobilityDriver, oracleDriver, routines);
        inventoryUnpick = new InventoryUnpick(mobilityDriver, oracleDriver, routines);

        mrCode = oracleDriver.stageMaterialsRequest("103383");

        containerCode = oracleDriver.generateContainerCode();
        assetCode2 = mrCode + "A2";
        oracleDriver.createPart("CNS236520", 10);
        stagingLDC = "GNS103383";
        receiveLDC = "CNS103383";
        shipLDC = "TN0100091";
        toLDC = "SNS236520";
        partCodeLN = "0-U6-16DS1-HSB";
        partCodeSU = "01-61084003";
        partCodeSO = "#18/2";
        projectCode = createNewProjectCode();
        poCode = createNewPOCode();
        routines.selectRoutine("Inventory Picking");
    }

    //Pick LS Item
    @TestRailCase(automationId = "5026")
    @Test(priority = 1108, enabled = true)
    public void inventoryPick0001() {
        inventoryPicking.reset();
        inventoryPicking.execute(false, mrCode, stagingLDC, containerCode, "00-4822-1", assetCode2);
    }

    //Pick SO Item
    @TestRailCase(automationId = "5027")
    @Test(priority = 1109, enabled = true)
    public void inventoryPick0002() {
        lineNumber = "3";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode2, partCodeSO, 5);
    }

    //Pick SU Item
    @TestRailCase(automationId = "5029")
    @Test(priority = 1110, enabled = true)
    public void inventoryPick0005 () {
        lineNumber = "4";
        inventoryPicking.execute(false, mrCode, lineNumber, stagingLDC, receiveLDC, containerCode, partCodeSU, 5);
    }

    //UnPick LS Item
    @TestRailCase(automationId = "5030")
    @Test(priority = 1111, enabled = true)
    public void inventoryUnpick0001() {
        inventoryUnpick.reset();
        inventoryUnpick.execute(false, mrCode, receiveLDC, containerCode, assetCode2);
    }

    //Unpick SO Item
    @TestRailCase(automationId = "5031")
    @Test(priority = 1112, enabled = true)
    public void inventoryUnpick0002() {
        inventoryUnpick.execute(false, mrCode, "3.0", receiveLDC, containerCode, partCodeSO, 5);
    }

    //Ship SO Item
    @TestRailCase(automationId = "5032")
    @Test(priority = 1113, enabled = true)
    public void inventoryShip0002() {
        inventoryShipping.reset();
        inventoryShipping.execute(false, mrCode, shipLDC, containerCode);
    }


}
