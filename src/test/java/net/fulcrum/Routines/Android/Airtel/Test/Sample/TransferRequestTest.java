package net.fulcrum.Routines.Android.Airtel.Test.Sample;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.Airtel.Sample.InternalReceipt;
import net.fulcrum.Routines.Android.Airtel.Sample.Pack;
import net.fulcrum.Routines.Android.Airtel.Sample.Pick;
import net.fulcrum.Routines.Android.Airtel.Sample.Ship;
import net.fulcrum.testrail.TestRailListener;


@Listeners(TestRailListener.class)
public class TransferRequestTest extends MainTest{
	
	private Pick pick;
	private Pack pack;
	private Ship ship;
	private InternalReceipt internalReceipt;
	
	private String fromLocation;
	private String toLocation;
	//private String transferRequest = String.valueOf(System.getProperty("transferRequest"));
	private String transferOrder ;
	private String partCode;
	private String assetCode;
	private String lot;
	private int qty;
	private String shipment;
	private String shipmentMethod;
	private String trackingNumber;
	private String healthCheckup;
	private String toStatus;
	
	
	@BeforeClass(alwaysRun = true)
	public void StageData(){
		pick = new Pick(mobilityDriver, oracleDriver);
		pack = new Pack(mobilityDriver, oracleDriver);		
		ship = new Ship(mobilityDriver, oracleDriver);
		internalReceipt = new InternalReceipt(mobilityDriver, oracleDriver);
		
		fromLocation = "BAL-MUNDKA-MDEL";
		toLocation = "111--1434002";
		transferOrder = "T000000007";
		partCode = "NSP0001";
		assetCode = "TESTASSET";
		lot = "LOT-A1";
		qty = 5;
		shipment = "SHIPMENT1";
		shipmentMethod = "ROADWAYS";
		trackingNumber = "TRACK1";
		healthCheckup = "NO";
		toStatus = "RECEIVED";
	}
	
	@Test(priority = 1, enabled = true)
	public void pick(){
		routines.selectRoutine("Pick");
		pick.setInitial();
		pick.execute(fromLocation, transferOrder, partCode, lot, qty);
		pick.execute(fromLocation, transferOrder, partCode, assetCode);
		pick.routineComplete(transferOrder);
		Assert.assertEquals(pick.getResult(), true);
	}
	
	@Test(priority = 2, enabled = true)
	public void pack(){
		routines.selectRoutine("Pack");
		pick.setInitial();
		pack.execute(fromLocation, transferOrder, shipment, partCode, qty);
		pack.execute(fromLocation, transferOrder, shipment, partCode, assetCode);
		pack.routineComplete();
		Assert.assertEquals(pack.getResult(), true);
	}	
	
	@Test(priority = 3, enabled = true)
	public void ship(){
		routines.selectRoutine("Ship");
		ship.setInitial();
		ship.executeRoutine(fromLocation, transferOrder, shipmentMethod, trackingNumber,shipment );
		ship.routineComplete();
		Assert.assertEquals(ship.getResult(), true);
	}	
	
	@Test(priority = 4, enabled = false)
	public void internalReceipt(){
		routines.selectRoutine("Internal Receipt");
		internalReceipt.setInitial();
		internalReceipt.execute(toLocation, transferOrder, shipment, partCode, qty, healthCheckup, toStatus);
		internalReceipt.execute(toLocation, transferOrder, shipment, partCode, assetCode, healthCheckup, toStatus);
		internalReceipt.routineComplete();
		Assert.assertEquals(internalReceipt.getResult(), true);
	}
	
}
