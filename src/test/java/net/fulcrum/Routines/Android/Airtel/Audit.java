package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class Audit extends AirtelRoutine {

    private final String routine = "Audit";

    private boolean isInitial = true;
    private boolean serialized = false;

    public Audit (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String barcode) {
        executeRoutine(location, barcode, 0);
    }

    public void execute (String location, String barcode, int qty) {
        executeRoutine(location, barcode, qty);
    }

    public void executeRoutine (String location, String barcode, int qty) {
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
            auditLocation(location);
            nextField();
        }

        scanBarcode(true, barcode);
        nextField();
        nextField();

        if (!serialized) {
            qty(true, qty);
            nextField();
        }

        notes(false, notes);
        nextField();
    }

    private void auditLocation(String location) {
        routineDetail("AUDIT LOCATION", location, true);
    }

    private void verifyContainer(String verify) {
        routineDetail("VERIFY CONTAINER", verify, true);
    }


    public void routineComplete () {
        result = verifyRoutineDetail("BARCODE", 30, true);
        serialized = false;
    }
}

