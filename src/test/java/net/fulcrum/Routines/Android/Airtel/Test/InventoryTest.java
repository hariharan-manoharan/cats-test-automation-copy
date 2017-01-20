package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.AssetUpdate;

import net.fulcrum.Routines.Android.Airtel.PutAwayStock;
import net.fulcrum.Routines.Android.Airtel.StatusChange;
import net.fulcrum.Routines.Android.Airtel.Stock;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.*;

@Listeners(TestRailListener.class)
public class InventoryTest extends MainTest {

    private final static String folder = "Inventory";

    private AssetUpdate assetUpdate;
    private PutAwayStock putAwayStock;
    private Stock stock;
    private StatusChange statusChange;

    private String assetCode = "ASSET08201";
    private String serializedPartCode =  "ABC000011";
    private String nonSerializedPartCode= "E3LSN0014";
    private String mfgPart;
    private String container = "CON334893";
    private String location = "WH1";
    private String toStatus;
    private String fromStatus;
    private String status;
    private ArrayList<String> toStatuses = new ArrayList<String>(4);
    private Random rand;
    private String serialNumber;
    private String packageTag;
    private String lot;
    private int qty;
    private int num;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        toStatuses.add("Consign");
        toStatuses.add("Spare");
        toStatuses.add("On Hand");
        toStatuses.add("Scrap");
        rand = new Random();
        assetUpdate = new AssetUpdate(mobilityDriver, oracleDriver);
        putAwayStock = new PutAwayStock(mobilityDriver, oracleDriver);
        stock = new Stock(mobilityDriver, oracleDriver);
        statusChange = new StatusChange(mobilityDriver, oracleDriver);

    }

    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 100, enabled = true)
    public void assetUpdate0001() {
        navigateToFolder();
        routines.selectRoutine("Asset Update");
        assetUpdate.execute(serializedPartCode, assetCode);
        Assert.assertEquals(assetUpdate.getResult(), true);
    }

    //Put away stock asset
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 101, enabled = true)
    public void putAwayStock0001() {
        putAwayStock.clickBackRoutines();
        routines.selectRoutine("Put Away Stock");
        location = "WH1";
        toStatus = getRandomStatus();
        putAwayStock.execute(location, serializedPartCode, assetCode, toStatus);
        Assert.assertEquals(putAwayStock.getResult(), true);
    }

    //Put away stock part
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 102, enabled = true)
    public void putAwayStock0002() {
        fromStatus = "ON HAND";
        toStatus = getRandomStatus();
        putAwayStock.execute(location, nonSerializedPartCode, fromStatus, toStatus, 1);
        Assert.assertEquals(putAwayStock.getResult(), true);
    }

    //Put away stock container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 103, enabled = true)
    public void putAwayStock0003() {
        toStatus = getRandomStatus();
        putAwayStock.execute(location, container, toStatus);
        Assert.assertEquals(putAwayStock.getResult(), true);
    }

    //Stock an Asset without Container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 104, enabled = true)
    public void stock0001() {
        stock.clickBackRoutines();
        routines.selectRoutine("Stock");
        num = rand.nextInt(1000000) + 1;
        assetCode = "ASSET" + num;
        serialNumber = "SERIAL" + num;
        packageTag = "PACKAGE" + num;
        mfgPart = "NCD90126/11";
        stock.execute(location, null, serializedPartCode, mfgPart, serialNumber, assetCode, packageTag);
        Assert.assertEquals(stock.getResult(), true);
    }

    //Stock a part without Container
    @TestRailCase(selfReporting = true, automationId = "1002")
    @Test(priority = 105, enabled = true)
    public void stock0002() {
        stock.clickBackRoutines();
        routines.selectRoutine("Stock");
        mfgPart = "NA";
        stock.clickBackRoutines();
        routines.selectRoutine("Stock");
        qty = 10;
        stock.setInitial();
        stock.execute(location, null, nonSerializedPartCode, mfgPart, qty);
        Assert.assertEquals(stock.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "1015")
    @Test(priority = 106, enabled = true)
    public void statusChange0001() {
        statusChange.clickBackRoutines();
        routines.selectRoutine("Status Change");
        status = "SPARE";
        statusChange.execute(location, serializedPartCode, assetCode, status);
        Assert.assertEquals(statusChange.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "1016")
    @Test(priority = 107, enabled = true)
    public void statusChange0002() {
        fromStatus = "ON HAND";
        toStatus = "SPARE";
        lot = "LOT1";
        qty = 1;
        statusChange.execute(location, nonSerializedPartCode, lot, qty, fromStatus, toStatus);
        Assert.assertEquals(statusChange.getResult(), true);
    }

    private String getRandomStatus(){
        return toStatuses.get(rand.nextInt(3) + 1);
    }

    private void navigateToFolder() {
        folders.selectFolder(folder);
    }
}