package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

public class Install extends AirtelRoutine {

    private final String routine = "Install";

    private boolean serialized = false;
    private boolean isInitial = true;

    public Install(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void select(Menu menu, Routines routines) {
        selectRoutine(menu, routines, routine);
    }

    public void execute (String location, String barcode, String barcode2, String newUID, String serialNumber,
                         String farID, String toStatus) {
        executeRoutine(location, barcode, barcode2, newUID, serialNumber, farID, null, 0, toStatus);
    }

    public void execute (String location, String barcode, String barcode2, String lot, int qty, String toStatus) {
        executeRoutine(location, barcode, null, null, null, null, lot, qty, toStatus);
    }

    public void executeRoutine (String location, String barcode, String barcode2, String newUID, String serialNumber,
                                String farID, String lot, int qty, String toStatus) {
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
        }

        barcode(true, barcode, true);
        nextField();

        if (serialized) {
            barcode2(false, barcode2);
            nextField();
            newUID(newUID);
            nextField();
            serialNumber(true, serialNumber);
            nextField();
            farID(farID);
        } else {
            lotNumber(true, lot);
            nextField();
            qty(true, qty);
        }

        nextField();
        toStatus(true, toStatus);
        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("BARCODE", 30, true);
        serialized = false;
    }

    private void newUID(String uid) {
        routineDetail("NEW UID", uid, true);
    }

    private void farID(String farID) {
        routineDetail("FAR ID", farID, true);
    }
}

