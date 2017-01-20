package net.fulcrum.Routines.iOS.Verizon.Test;

import net.fulcrum.Routines.iOS.Verizon.ExpressStock;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.iOS.Test.MainTest;

import org.openqa.selenium.By;
import org.testng.annotations.*;


@Listeners(TestRailListener.class)
public class ExpressStockTest extends MainTest {

    private ExpressStock expressStock;
    private String assetCode;

    @Test (priority = 100, enabled = true)
    public void StageData() {
        element = iosDriver.findElement(By.name("Express Stock"));
        element.click();
        expressStock = new ExpressStock(mobilityDriver, oracleDriver);
    }


    //Express stock a LS part
    @TestRailCase(selfReporting = true, automationId="7001")
    @Test (priority = 101, enabled = false)
    public void expressStock0001 (){
        assetCode = oracleDriver.generateAssetCode();
        expressStock.execute("CNS103383", "EV106-H5-H5-100", assetCode, 1);
        //Assert.assertEquals(result.GetSuccess(), true);
    }

    //Express stock a LS part
    @TestRailCase(selfReporting = true, automationId="7001")
    @Test (priority = 102, enabled = false)
    public void expressStock0002 (){
        assetCode = oracleDriver.generateAssetCode();
        expressStock.execute("CNS236520", "44WR8", assetCode, 1);
        //Assert.assertEquals(result.GetSuccess(), true);
    }

    //Express stock a LN part
    @TestRailCase(selfReporting = true, automationId="7002")
    @Test (priority = 103, enabled = true)
    public void expressStock0003 (){
        expressStock.execute("CNS103383", "179-530090-0101", 100);
    }

    //Express stock a SO part
    @TestRailCase(selfReporting = true, automationId="7003")
    @Test (priority = 104, enabled = true)
    public void expressStock0004 (){
        expressStock.execute("CNS103383", "LOC-VAS335055-0013", 1);
    }

    //Express stock a SU part
    @TestRailCase(selfReporting = true, automationId="7004")
    @Test (priority = 105, enabled = true)
    public void expressStock0005 (){
        expressStock.execute("CNS103383", "120-110-1001", 1);
    }
}
