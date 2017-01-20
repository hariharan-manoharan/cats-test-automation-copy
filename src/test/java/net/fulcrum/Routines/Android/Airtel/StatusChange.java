package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class StatusChange extends AirtelRoutine {
    private final String routine = "Status Change";

    private boolean serialized = false;
    private boolean isInitial = true;

    public StatusChange (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String partCode, String assetCode, String toStatus) {
        executeRoutine(location, partCode, assetCode, null, 0, null, toStatus);
    }

    public void execute (String location, String partCode, String lot, int qty, String fromStatus, String toStatus) {
        executeRoutine(location, partCode, null, lot, qty, fromStatus, toStatus);
    }

    public void executeRoutine (String location, String partCode, String assetCode, String lot, int qty, String fromStatus, String toStatus) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (qty == 0) {
            serialized = true;
        } else {
            serialized = false;
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            location(true, location);
            nextField();
        }

        barcode(true, partCode, true);
        nextField();

        if (!serialized) {
            lotNumber(true, lot);
            nextField();
            fromStatus(true, fromStatus, true);
            nextField();
            qty(true, qty);
        } else {
            barcode2(false, assetCode);
        }

        nextField();
        toStatus(true, toStatus);
        nextField();

        if (!serialized) {
           swipeDown();
        }

        //notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("BARCODE", 30, true);
    }

}

