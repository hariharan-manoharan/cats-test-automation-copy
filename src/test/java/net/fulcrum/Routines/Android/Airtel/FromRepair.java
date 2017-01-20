package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class FromRepair extends AirtelRoutine {

    private final String routine = "From Repair";

    private boolean isInitial = true;

    public FromRepair (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String jobNumber, String rmaNumber, String failBarcode, String failBarcode2) {
        executeRoutine(location, jobNumber, rmaNumber, failBarcode, failBarcode2);
    }

    public void executeRoutine (String location, String jobNumber, String rmaNumber, String failBarcode,
                                String failBarcode2) {
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
        mobilityDriver.confirmNo();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("FAIL BARCODE", 30, true);
    }
}

