package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class SiteReturn extends AirtelRoutine {
    private final String routine = "Site Return";

    private boolean serialized = false;
    private boolean isInitial = true;

    public SiteReturn (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String barcode, String assetCode) {
        executeRoutine(location, barcode, assetCode, null, 0);
    }

    public void execute (String location, String barcode, String fromStatus, int qty) {
        executeRoutine(location, barcode, null, fromStatus, qty);
    }

    public void executeRoutine (String location, String barcode, String barcode2,
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
            location(true, location);
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

