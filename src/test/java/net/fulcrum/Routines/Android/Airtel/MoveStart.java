package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class MoveStart extends AirtelRoutine {
    private final String routine = "Move Start";

    private boolean serialized = false;
    private boolean isInitial = true;

    public MoveStart (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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
        executeRoutine(fromLocation, toLocation, barcode, assetCode, null, 0);
    }

    public void execute (String fromLocation, String toLocation, String barcode, String fromStatus, int qty) {
        executeRoutine(fromLocation, toLocation, barcode, null, fromStatus, qty);
    }

    public void executeRoutine (String fromLocation, String toLocation, String barcode, String barcode2,
                                String fromStatus, int qty) {
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
            fromLocation(true, fromLocation);
            nextField();
            toLocation(true, toLocation);
            nextField();
        }

        barcode(true, barcode, true);
        nextField();

        if (!serialized) {
            fromStatus(true, fromStatus, true);
            nextField();
            qty(true, qty);
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

}

