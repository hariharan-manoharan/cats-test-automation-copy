package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.Stock;


import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.*;

@Listeners(TestRailListener.class)
public class StockTest extends MainTest {

    private Stock stock;

    private String location = "WH1";
    private String assetCode;
    private String serializedPartCode =  "ABC000011";
    private String nonSerializedPartCode= "E3LSN0014";
    private String mfgPart;
    private String serialNumber;
    private String packageTag;
    private String container;
    private int qty;
    protected Random rand;
    private int num;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        folders.selectFolder("Inventory");
        routines.selectRoutine("Stock");
        stock = new Stock(mobilityDriver, oracleDriver);
        rand =  new Random();
    }

    //Stock an Asset without Container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 100, enabled = true)
    public void stock0001() {
        num = rand.nextInt(1000000) + 1;
        assetCode = "ASSET" + num;
        serialNumber = "SERIAL" + num;
        packageTag = "PACKAGE" + num;
        mfgPart = "NCD90126/11";
        stock.execute(location, null, serializedPartCode, mfgPart, serialNumber, assetCode, packageTag);
        Assert.assertEquals(stock.getResult(), true);
    }

    //Stock an Asset with Container
    @TestRailCase(selfReporting = true, automationId = "1001")
    @Test(priority = 101, enabled = true)
    public void stock0002() {
        reset();
        num = rand.nextInt(1000000) + 1;
        container = "CON" + num;
        assetCode = "ASSET" + num;
        serialNumber = "SERIAL" + num;
        packageTag = "PACKAGE" + num;
        mfgPart = "NCD90126/11";
        stock.execute(location, container, serializedPartCode, mfgPart, serialNumber, assetCode, packageTag);
        Assert.assertEquals(stock.getResult(), true);
    }

    //Stock an Asset with Container that has item(s) in it
    @TestRailCase(selfReporting = true, automationId = "1001")
    @Test(priority = 101, enabled = false)
    public void stock0003() {
        container = "CON" + num;
        assetCode = "ASSET" + num;
        serialNumber = "SERIAL" + num;
        packageTag = "PACKAGE" + num;
        mfgPart = "NCD90126/11";
        stock.execute(location, container, serializedPartCode, mfgPart, serialNumber, assetCode, packageTag);
        Assert.assertEquals(stock.getResult(), true);
    }

    //Stock a part without Container
    @TestRailCase(selfReporting = true, automationId = "1002")
    @Test(priority = 102, enabled = true)
    public void stock0004() {
        reset();
        qty = 10;
        mfgPart = "NA";
        stock.execute(location, null, nonSerializedPartCode, mfgPart, qty);
        Assert.assertEquals(stock.getResult(), true);
    }

    //Stock a part with Container
    @TestRailCase(selfReporting = true, automationId = "1003")
    @Test(priority = 103, enabled = true)
    public void stock0005() {
        reset();
        qty = 5;
        mfgPart = "NA";
        num = rand.nextInt(1000000) + 1;
        container = "CON" + num;
        stock.execute(location, container, nonSerializedPartCode, mfgPart, qty);
        Assert.assertEquals(stock.getResult(), true);
    }

    //Stock a part with Container that has item(s) in it
    @TestRailCase(selfReporting = true, automationId = "1005")
    @Test(priority = 103, enabled = false)
    public void stock0006() {
        reset();
        qty = 5;
        mfgPart = "NA";
        container = "CON" + num;
        stock.execute(location, container, nonSerializedPartCode, mfgPart, qty);
        Assert.assertEquals(stock.getResult(), true);
    }

    public void reset() {
        stock.clickBackRoutines();
        routines.selectRoutine("Stock");
        stock.setInitial();
    }

}