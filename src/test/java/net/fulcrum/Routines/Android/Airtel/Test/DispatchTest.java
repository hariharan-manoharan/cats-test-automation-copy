package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.*;

import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

@Listeners(TestRailListener.class)
public class DispatchTest extends MainTest {

    private final static String folder = "Dispatch";

    private AddToShipment addToShipment;
    private RemoveFromShipment removeFromShipment;
    private UnshipShipment unshipShipment;
    private ShipShipment shipShipment;

    private String location = "WH1";
    private String transferNumber = "MOA2";
    private String assetCode = "ASSET08201";
    private String serializedPartCode =  "ABC000011";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        addToShipment = new AddToShipment(mobilityDriver, oracleDriver);
        removeFromShipment = new RemoveFromShipment(mobilityDriver, oracleDriver);
        unshipShipment = new UnshipShipment(mobilityDriver, oracleDriver);
        shipShipment = new ShipShipment(mobilityDriver, oracleDriver);
    }

    //Add Asset to a shipment
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 100, enabled = true)
    public void addToShipment0001() {
        navigateToFolder();
        routines.selectRoutine("Add To Shipment");
        addToShipment.execute(location, transferNumber, serializedPartCode, assetCode);
        Assert.assertEquals(addToShipment.getResult(), true);
        //mobilityDriver.clickOutOfFocus();
    }

    //Remove Asset from an existing shipment
    @TestRailCase(selfReporting = true, automationId = "1008")
    @Test(priority = 103, enabled = true)
    public void removeFromShipment0001() {
        removeFromShipment.clickBackRoutines();
        routines.selectRoutine("Remove From Shipment");
        removeFromShipment.execute(location, transferNumber, serializedPartCode, assetCode);
        Assert.assertEquals(removeFromShipment.getResult(), true);
    }

    //Ship a shipment
    @TestRailCase(selfReporting = true, automationId = "1011")
    @Test(priority = 106, enabled = true)
    public void shipShipment0001() {
        shipShipment.clickBackRoutines();
        routines.selectRoutine("Ship Shipment");
        shipShipment.execute(location, transferNumber);
        Assert.assertEquals(shipShipment.getResult(), true);
    }

    //Unship shipment
    @TestRailCase(selfReporting = true, automationId = "1011")
    @Test(priority = 106, enabled = true)
    public void unshipShipment0001() {
        unshipShipment.clickBackRoutines();
        routines.selectRoutine("Unship Shipment");
        unshipShipment.execute(location, transferNumber);
        Assert.assertEquals(unshipShipment.getResult(), true);
    }

    private void navigateToFolder() {
        folders.selectFolder(folder);
    }
}