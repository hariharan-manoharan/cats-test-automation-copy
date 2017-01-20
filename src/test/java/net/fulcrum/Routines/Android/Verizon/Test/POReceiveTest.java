package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Routines.Android.Verizon.*;
import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;

import org.testng.annotations.*;
import org.testng.Assert;

@Listeners(TestRailListener.class)
public class POReceiveTest extends MainTest {

    private String poCode;
    private String assetCode;

    private POReceive poReceive;
    private CancelReceive cancelReceive;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        poCode = oracleDriver.stagePOData();
        poReceive = new POReceive(mobilityDriver, oracleDriver, routines);
        cancelReceive = new CancelReceive(mobilityDriver, oracleDriver);
        assetCode = "A1-" + poCode;
    }

	@TestRailCase(automationId = "5017")
	@Test (priority = 100, enabled = true)
	public void POR_0001(){
        routines.selectRoutine("PO Receive");
		poReceive.execute(poCode, "40.1", assetCode);
		Assert.assertEquals(poReceive.getResult(), true);
	}

    //Receive LN Item
    @TestRailCase(automationId = "5018")
    @Test (priority = 101, enabled = true)
    public void POR_0002(){
        poReceive.execute(poCode, "10.1", 1);
        Assert.assertEquals(poReceive.getResult(), true);
    }

    //Receive SO Item
    @TestRailCase(automationId = "5019")
    @Test (priority = 102, enabled = true)
    public void POR_0003(){
        poReceive.execute(poCode, "21.1", 10);
        Assert.assertEquals(poReceive.getResult(), true);
    }

    //Receive SU Item
    @TestRailCase(automationId = "5020")
    @Test (priority = 103, enabled = true)
    public void POR_0004(){
        poReceive.execute(poCode, "19.1", 1);
        Assert.assertEquals(poReceive.getResult(), true);
    }

    //Cancel LS Item
    @TestRailCase(automationId = "5021")
    @Test (priority = 105, enabled = true)
    public void POC_0001(){
        cancelReceive.clickBackRoutines();
        routines.selectRoutine("Cancel Receive");
        cancelReceive.execute(false, poCode, "40.1", assetCode);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }

    //Cancel LN Item
    @TestRailCase(automationId = "5022")
    @Test (priority = 106, enabled = true)
    public void POC_0002(){
        cancelReceive.execute(false, poCode, "10.1", 1);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }

    //Cancel SO Item
    @TestRailCase(automationId = "5023")
    @Test (priority = 107, enabled = true)
    public void POC_0003(){
        cancelReceive.execute(false, poCode, "21.1", 1);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }

    //Cancel SU Item
    @TestRailCase(automationId = "5024")
    @Test (priority = 108, enabled = true)
    public void POC_0004(){
        cancelReceive.execute(false, poCode, "19.1", 1);
        Assert.assertEquals(cancelReceive.getResult(), true);
    }


}
