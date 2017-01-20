package net.fulcrum.Routines.Android.Airtel.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.*;


import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.*;

@Listeners(TestRailListener.class)
public class AirtelBVTTest extends MainTest {

    private Stock stock;
    private ContainerInquiry containerInquiry;
    private StatusChange statusChange;
    private AddToContainer addToContainer;

    private String location = "WH1";
    private String assetCode;
    private String serializedPartCode =  "ABC000011";
    private String nonSerializedPartCode= "E3LSN0014";
    private String mfgPart;
    private String serialNumber;
    private String packageTag;
    private String container = "CON334893";
    private int qty;
    protected Random rand;
    private int num;
    private String status;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        stock = new Stock(mobilityDriver, oracleDriver);
        containerInquiry = new ContainerInquiry(mobilityDriver, oracleDriver);
        statusChange = new StatusChange(mobilityDriver, oracleDriver);
        addToContainer = new AddToContainer(mobilityDriver, oracleDriver);
        rand =  new Random();
    }

    //Stock an Asset without Container
    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 100, enabled = true)
    public void stock0001() {
        folders.selectFolder("Inventory");
        routines.selectRoutine("Stock");
        num = rand.nextInt(1000000) + 1;
        assetCode = "ASSET" + num;
        serialNumber = "SERIAL" + num;
        packageTag = "PACKAGE" + num;
        mfgPart = "NCD90126/11";
        stock.execute(location, null, serializedPartCode, mfgPart, serialNumber, assetCode, packageTag);
        Assert.assertEquals(stock.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "1000")
    @Test(priority = 104, enabled = true)
    public void containerInquiry0001() {
        menu.selectAdmin();
        folders.selectFolder("Inquiry");
        routines.selectRoutine("Container Inquiry");
        containerInquiry.executeRoutine(container, location, "OPEN", "ON HAND", "ASSET06295", nonSerializedPartCode);
        Assert.assertEquals(containerInquiry.getResult(), true);
    }

    @TestRailCase(selfReporting = true, automationId = "1015")
    @Test(priority = 103, enabled = true)
    public void statusChange0001() {
        menu.selectAdmin();
        routines.selectRoutine("Inventory");
        routines.selectRoutine("Status Change");
        status = "SPARE";
        statusChange.execute(location, serializedPartCode, assetCode, status);
        Assert.assertEquals(statusChange.getResult(), true);
    }

    //Add Asset to an existing Container
    @TestRailCase(selfReporting = true, automationId = "1006")
    @Test(priority = 101, enabled = true)
    public void addToContainer0001() {
        menu.selectAdmin();
        folders.selectFolder("Container");
        routines.selectRoutine("Add To Container");
        addToContainer.execute(location, container, serializedPartCode, assetCode);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    public void reset() {
        menu.selectAdmin();
        routines.selectRoutine("Stock");
        stock.setInitial();
    }

}