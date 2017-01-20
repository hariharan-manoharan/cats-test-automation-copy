package net.fulcrum.Routines.Android.Airtel.Test;

import io.appium.java_client.remote.MobileBrowserType;
import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.AddToAssembly;
import net.fulcrum.Routines.Android.Airtel.AssemblyDetail;
import net.fulcrum.Routines.Android.Airtel.RemoveFromAssembly;
import net.fulcrum.Routines.Android.Airtel.Stock;


import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.*;

@Listeners(TestRailListener.class)
public class AssemblyTest extends MainTest {

    private final static String folder = "Assembly";

    private Stock stock;
    private AssemblyDetail assemblyDetail;
    private RemoveFromAssembly removeFromAssembly;
    private AddToAssembly addToAssembly;

    private String location = "WH1";
    private String parentUID;
    private String nonSerializedPartCode= "ABC000003";
    private String serializedPartCode = "WRANLJ026";
    private String childSerializedPartCode = "ABC000007";
    private String childSerializedPartCode2 = "EWCHILD02";
    private String childSerial;
    private String childUID;
    private String childSerial2;
    private String childUID2;
    private String serialNumber;
    private String packageTag;
    private String lot;
    private int qty;
    protected Random rand;
    private int num;

    @BeforeClass(alwaysRun = true)
    public void stageData() {
        stock = new Stock(mobilityDriver, oracleDriver);
        assemblyDetail = new AssemblyDetail(mobilityDriver, oracleDriver);
        removeFromAssembly = new RemoveFromAssembly(mobilityDriver, oracleDriver);
        addToAssembly = new AddToAssembly(mobilityDriver, oracleDriver);
        rand =  new Random();
        num = rand.nextInt(1000000) + 1;
        serialNumber = "SERIAL" + num;
        parentUID = "ASSET" + num;
        packageTag = "PACKAGE" + num;
        folders.selectFolder("Inventory");
        routines.selectRoutine("Stock");
        stock.execute(location, null, serializedPartCode, "UNKNOWN", serialNumber, parentUID, packageTag);
        menu.selectAdmin();
    }

    //Perform Assembly Detail
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 100, enabled = true)
    public void assembly0001() {
        navigateToFolder();
        routines.selectRoutine("Assembly Detail");
        num = rand.nextInt(1000000) + 1;
        childUID = "UID" + num;
        childSerial = "S" + num;
        assemblyDetail.execute(location, serializedPartCode, parentUID, childSerializedPartCode,
                childSerial, childUID);
        Assert.assertEquals(assemblyDetail.getResult(), true);
    }

    //Perform Assembly Detail
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 101, enabled = true)
    public void assembly0002() {
        num = rand.nextInt(1000000) + 1;
        childUID2 = "UID" + num;
        childSerial2 = "S" + num;
        assemblyDetail.execute(location, serializedPartCode, parentUID, childSerializedPartCode2,
                childSerial2, childUID2);
        Assert.assertEquals(assemblyDetail.getResult(), true);
        mobilityDriver.clickOk();
    }

    //Perform remove from Assembly
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 102, enabled = true)
    public void assembly0003() {
        removeFromAssembly.clickBackRoutines();
        routines.selectRoutine("Remove From Assembly");
        num = rand.nextInt(1000000) + 1;
        removeFromAssembly.execute(location, parentUID, childUID2);
        Assert.assertEquals(removeFromAssembly.getResult(), true);
    }

    //Perform Add to Assembly
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 103, enabled = true)
    public void assembly0004() {
        addToAssembly.clickBackRoutines();
        routines.selectRoutine("Add To Assembly");
        num = rand.nextInt(1000000) + 1;
        lot = "LOT1";
        qty = 5;
        childUID = "UID" + num;
        childSerial = "S" + num;
        addToAssembly.execute(location, parentUID, childUID2);
        Assert.assertEquals(addToAssembly.getResult(), true);
    }

    private void navigateToFolder() {
        folders.selectFolder(folder);
    }

}