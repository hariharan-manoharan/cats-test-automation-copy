package net.fulcrum.Routines.Android.Airtel.Sample;


import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.Airtel.AirtelRoutine;

public class InternalReceipt extends AirtelRoutine{
	
	private final String routine = "Internal Receipt";

	private boolean serialized = false;
	
	private boolean isInitial = true;
	
	
	public InternalReceipt(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
		this.mobilityDriver = mobilityDriver;
		this.oracleDriver = oracleDriver;
		notes = "AIRTEL CTA Android: " + routine;
	}
	
	public void reset(){
		mobilityDriver.clickByName("Location");
	}
	
	public void setInitial(){
		isInitial = true;
	}
	
	//Non-Serialized
    public void execute (String location, String transferOrder, String shipment, String partCode, int qty, String healthCheckup, String toStatus) {
        executeRoutine(location, transferOrder, shipment, partCode, null, qty, healthCheckup , toStatus);
    }
    
    // Serialized
    public void execute (String location, String transferOrder, String shipment, String partCode, String assetCode, String healthCheckup, String toStatus) {
        executeRoutine(location, transferOrder, shipment, partCode, assetCode, 0, healthCheckup , toStatus);
    }
	
	  
	public void executeRoutine(String location, String transferOrder, String shipment, String partCode, String assetCode, int qty, String healthCheckup, String toStatus){
		
		last = true;
		
		if(!isInitial && !result){
			reset();
		}
		
        if (qty == 0) {
            serialized = true;
        }
        
        if (isInitial) {
            isInitial = false;
            Location(true, location);
            nextField();
            ShipmentNumber(true,"Shipment #", shipment);
            nextField();            
            receivingLocation(true, location);
            nextField(); 
        }        
        
        if(serialized){
        barcode(true, assetCode);
        }else{
        barcode(true, partCode);	
        }
        
        nextField(); 
        
        if(!serialized){
        quantity(true, qty);
        nextField();
        } 
        
        healthCheckup(true, healthCheckup);
        nextField();
        ToStatus(true, toStatus);
        nextField();        
        notes(false, notes);
        nextField(); 
        
        //routineComplete();
        
	}

	public void routineComplete() {
		confirmMessage("");
        serialized = false;
        
        //If transaction completed message is displayed move back to Routines page
        if(last){
        	clickOk();
        	clickBackRoutines();
        }
	}

}
