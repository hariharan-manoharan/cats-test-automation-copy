package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class ToRepair extends AirtelRoutine {

    private final String routine = "To Repair";

    private boolean isInitial = true;

    public ToRepair (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String jobNumber, String failBarcode, String failBarcode2) {
        executeRoutine(location, jobNumber, failBarcode, failBarcode2);
    }

    public void executeRoutine (String location, String jobNumber, String failBarcode, String failBarcode2) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            location(true, location);
            nextField();
        }

        jobNumber(true, jobNumber);
        nextField();
        failBarcode(true, failBarcode);
        nextField();
        failBarcode2(false, failBarcode2);
        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("FAIL BARCODE", 30, true);
    }
}

