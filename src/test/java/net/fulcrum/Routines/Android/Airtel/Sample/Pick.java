package net.fulcrum.Routines.Android.Airtel.Sample;


import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.Airtel.AirtelRoutine;

public class Pick extends AirtelRoutine{
	
	private final String routine = "Pick";

	private boolean serialized = false;

	
	public Pick(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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
    public void execute (String location, String transferOrder, String partCode, String lot, int qty) {
        executeRoutine(location, transferOrder, partCode, null, lot, qty);
    }
    
    // Serialized
    public void execute (String location, String transferOrder, String partCode, String assetCode) {
        executeRoutine(location, transferOrder, partCode, assetCode, null, 0);
    }
	
	  
	public void executeRoutine(String location, String transferOrder, String partCode, String assetCode, String lot, int qty){
		
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
        }
        
        if(serialized){
        lineItem(true, assetCode);
        }else{
        lineItem(true, partCode);	
        }
        
        nextField();
        
        if(lot!=null){
        Lot(true, lot);
        nextField();
        }
        
        if(!serialized){
        quantity(true, qty);
        nextField();
        } 
        
        notes(false, notes);
        nextField(); 
        
        //routineComplete(transferOrder);
        
	}

	public void routineComplete(String transferOrder) {
		confirmMessage("Transfer "+transferOrder+" has been fully picked.");        
        serialized = false;
        
        //If transaction completed message is displayed move back to Routines page
        if(last){
        	clickOk();
        	clickBackRoutines();
        }
	}

}
