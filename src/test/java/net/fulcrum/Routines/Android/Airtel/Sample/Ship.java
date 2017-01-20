package net.fulcrum.Routines.Android.Airtel.Sample;


import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.Airtel.AirtelRoutine;

public class Ship extends AirtelRoutine{
	
	private final String routine = "Ship";

	
	public Ship(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
		this.mobilityDriver = mobilityDriver;
		this.oracleDriver = oracleDriver;
		notes = "AIRTEL CTA Android: " + routine;
		
	}
	
	public void reset(){
		mobilityDriver.clickByName("From Location");
	}
	
	public void setInitial(){
		isInitial = true;
	}
	
	  
	public void executeRoutine(String location, String transferOrder, String shipmentMethod, String trackingNumber, String shipment){
		
		last = true;
		
		if(!isInitial && !result){
			reset();
		}
        
        if (isInitial) {
            isInitial = false;
            FromLocation(true, location);
            nextField();
            transferRequest(true, transferOrder);
            nextField();
        }

        shipmentMethod(true,shipmentMethod);
        nextField();
        trackingNumber(false,trackingNumber);
        nextField();
        ShipmentNumber(true,"Shipment Number", shipment);
        nextField();
        nextField();
        notes(false, notes);
        nextField(); 
        
       // routineComplete();
        
	}

	public void routineComplete() {
		confirmMessage("All items have been shipped for this transfer."); 
        
        //If transaction completed message is displayed move back to Routines page
        if(last){
        	clickOk();
        	clickBackRoutines();
        }
	}

}
