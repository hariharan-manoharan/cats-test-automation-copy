package net.fulcrum.Routines.Android.Airtel.Sample;


import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.Airtel.AirtelRoutine;

public class Pack extends AirtelRoutine{
	
	private final String routine = "Pack";

	private boolean serialized = false;
	
	private boolean isInitial = true;
	
	
	public Pack(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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
    public void execute (String location, String transferOrder, String shipment, String partCode, int qty) {
        executeRoutine(location, transferOrder, shipment, partCode, null, qty);
    }
    
    // Serialized
    public void execute (String location, String transferOrder, String shipment, String partCode, String assetCode) {
        executeRoutine(location, transferOrder, shipment, partCode, assetCode, 0);
    }
	
	  
	public void executeRoutine(String location, String transferOrder, String shipment, String partCode, String assetCode, int qty){
		
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
            transferRequest(true, transferOrder);
            nextField();            
            
            confirmMessage("Generate new shipment?");
            if(last){
            	mobilityDriver.confirmYes();
            }
            
            shipment(true,shipment);
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
        
        notes(false, notes);
        nextField(); 
        
        //routineComplete();
        
	}

	public void routineComplete() {
		confirmMessage("All items picked for this transfer have been packed.");
        serialized = false;
        
        //If transaction completed message is displayed move back to Routines page
        if(last){
        	clickOk();
        	clickBackRoutines();
        }
	}

}
