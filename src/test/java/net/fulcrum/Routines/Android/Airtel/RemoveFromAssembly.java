package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;

public class RemoveFromAssembly extends AirtelRoutine {

    private final String routine = "Remove From Assembly";

    private String lot = "LOT1";
    private String packageTag = "TAG1";
    private boolean serialized = false;

    private boolean isInitial = true;

    public RemoveFromAssembly (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        notes = "AIRTEL CTA Android: " + routine;
    }

    public void reset() {
        mobilityDriver.clickByName("LOCATION");
    }

    public void setInitial() {
        isInitial = true;
    }

    public void execute (String location, String parentUID, String child) {
        executeRoutine(location, parentUID, child, null, 0);
    }
    public void execute (String location, String parentUID, String child, String lot, int qty) {
        executeRoutine(location, parentUID, child, lot, qty);
    }

    public void executeRoutine (String location, String parentUID, String childBarcode, String lot, int qty) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (qty == 0) {
            serialized = true;
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            location(true, location);
            nextField();
            parentAssemblyUID(true, parentUID);
            nextField();
        }

        childComponentBarcode(true, childBarcode);
        nextField();

        if (!serialized) {
            qty(true, qty);
            nextField();
            lotNumber(true, lot);
            nextField();
        }

        notes(false, notes, true);
        nextField();
        routineComplete();
    }


    public void routineComplete() {
        result = verifyRoutineDetail("CHILD COMPONENT BARCODE", 60, true);
    }

    public void qty(boolean required, int qty) {
        routineDetail("Enter QTY*:", String.valueOf(qty));
    }

}

