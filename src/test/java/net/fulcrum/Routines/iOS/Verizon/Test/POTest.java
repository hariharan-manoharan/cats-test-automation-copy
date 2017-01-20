package net.fulcrum.Routines.iOS.Verizon.Test;

import net.fulcrum.Routines.iOS.Verizon.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.iOS.Test.MainTest;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.Assert;



@Listeners(TestRailListener.class)
public class POTest extends MainTest {

    private String poCode;
    private String assetCode;

    private POReceive poReceive;
    private CancelReceive cancelReceive;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        poCode = oracleDriver.stagePOData();
        //poCode = "PO-2510749";
        poReceive = new POReceive(mobilityDriver, oracleDriver);
        cancelReceive = new CancelReceive(mobilityDriver, oracleDriver);
        assetCode = "A1-" + poCode;
    }


    @TestRailCase(automationId = "7027")
    @Test (priority = 100, enabled = true)
    public void POR_0001(){
        element = iosDriver.findElement(By.name("PO Receive"));
        element.click();
        poReceive.execute(poCode, "40.1", assetCode);
        Assert.assertEquals(poReceive.getResult(), true);
    }


    //Receive LN Item
    @TestRailCase(automationId = "7028")
    @Test (priority = 101, enabled = true)
    public void POR_0002(){
        poReceive.execute(poCode, "10.1", 1);
        Assert.assertEquals(poReceive.getResult(), true);
    }

    //Receive SO Item
    @TestRailCase(automationId = "7029")
    @Test (priority = 102, enabled = true)
    public void POR_0003(){
        poReceive.execute(poCode, "21.1", 10);
        Assert.assertEquals(poReceive.getResult(), true);
    }

    //Receive SU Item
    @TestRailCase(automationId = "7030")
    @Test (priority = 103, enabled = true)
    public void POR_0004(){
        poReceive.execute(poCode, "19.1", 1);
        Assert.assertEquals(poReceive.getResult(), true);
    }

    //Cancel LS Item
    @TestRailCase(automationId = "7031")
    @Test (priority = 105, enabled = true)
    public void POC_0001(){
       mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Cancel Receive"));
        element.click();
        cancelReceive.execute(poCode, "40.1", assetCode);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }

    //Cancel LN Item
    @TestRailCase(automationId = "7032")
    @Test (priority = 106, enabled = true)
    public void POC_0002(){
        cancelReceive.execute(poCode, "10.1", 1);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }

    //Cancel SO Item
    @TestRailCase(automationId = "7033")
    @Test (priority = 107, enabled = true)
    public void POC_0003(){
        cancelReceive.execute(poCode, "21.1", 1);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }

    //Cancel SU Item
    @TestRailCase(automationId = "7034")
    @Test (priority = 108, enabled = true)
    public void POC_0004(){
        cancelReceive.execute(poCode, "19.1", 1);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }

}
