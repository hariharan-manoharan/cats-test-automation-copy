package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class PutAwayStock extends AirtelRoutine {

    private final String routine = "Put Away Stock";

    private String lot = "LOT1";
    private String packageTag = "TAG1";
    private boolean serialized = false;
    private boolean container = false;
    private boolean isInitial = true;

    public PutAwayStock (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        notes = "AIRTEL CTA Android: " + routine;
    }

    public void reset() {
        mobilityDriver.clickTextView("LOCATION");
        serialized = false;
    }

    public void setInitial() {
        isInitial = true;
    }

    public void execute (String location, String container, String toStatus) {
        executeRoutine(location, container, null, null, toStatus, -1);
    }

    public void execute (String location, String partCode, String assetCode, String toStatus) {
        executeRoutine(location, partCode, assetCode, null, toStatus, 0);
    }

    public void execute (String location, String partCode, String fromStatus, String toStatus, int qty) {
        executeRoutine(location, partCode, null, fromStatus, toStatus, qty);
    }

    public void executeRoutine (String location, String barCode, String assetCode, String fromStatus,
                                String toStatus, int qty) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            location(true, location);
        } else {
            serialized = false;
        }

        if (qty == 0) {
            serialized = true;
        } else if (qty == -1) {
            container = true;
        }

        nextField();
        barcode(true, barCode, true);
        nextField();

        if (serialized) {
            barcode2(false, assetCode);
            nextField();
        } else if (!container && !serialized){
            qty(true, qty);
            nextField();
            lotNumber(lot);
            nextField();
            fromStatus(true, fromStatus, true);
            nextField();
        }

        toStatus(true, toStatus, true);
        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete() {
        result = verifyRoutineDetail("LOCATION", 60, true);
    }

    private void lotNumber(String lot) {
        routineDetail("Enter LOT # (*) :", lot);
    }

}

