package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Pages.Android.Menu;
import net.fulcrum.Pages.Android.Routines;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;

public class MoveFinish extends AirtelRoutine {
    private final String routine = "Move Finish";

    private boolean serialized = false;
    private boolean isInitial = true;

    public MoveFinish(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        notes = "AIRTEL CTA Android: " + routine;
    }

    public void reset() {
        mobilityDriver.clickByName("FROM LOCATION");
    }

    public void setInitial() {
        isInitial = true;
    }

    public void select(Menu menu, Routines routines) {
        selectRoutine(menu, routines, routine);
    }

    public void execute (String fromLocation, String toLocation, String barcode, String assetCode) {
        executeRoutine(fromLocation, toLocation, barcode, assetCode, 0);
    }

    public void execute (String fromLocation, String toLocation, String barcode, int qty) {
        executeRoutine(fromLocation, toLocation, barcode, null, qty);
    }

    public void executeRoutine (String fromLocation, String toLocation, String barcode, String barcode2,
                                int qty) {
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
            toLocation(true, toLocation);
            nextField();
        }

        barcode(true, barcode, true);
        nextField();

        if (!serialized) {
            qty(qty);
            fromLocation(true, fromLocation);
            nextField();
        } else {
            barcode2(false, barcode2);
        }
        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("BARCODE", 30, true);
        serialized = false;
    }

    private void qty(int qty) {
        routineDetail("Enter QTY*:", String.valueOf(qty));
    }
}

