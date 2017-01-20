package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class AuditCancel extends AirtelRoutine {

    private final String routine = "Audit Cancel";

    public AuditCancel (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        notes = "AIRTEL CTA Android: " + routine;
    }

    public void reset() {
        mobilityDriver.clickByName("LOCATION DETAIL CODE");
    }

    public void setInitial() {
        isInitial = true;
    }

    public void select(Menu menu, Routines routines) {
        selectRoutine(menu, routines, routine);
    }

    public void execute (String ldc) {
        executeRoutine(ldc);
    }

    public void executeRoutine (String ldc) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        locationDetailCode(true, ldc);
        nextField();
        clickYes();
        notes(false, notes);
        nextField();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("LOCATION DETAIL CODE", 30, true);
    }
}

