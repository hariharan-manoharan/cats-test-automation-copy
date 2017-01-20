package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class AuditOpen extends AirtelRoutine {

    private final String routine = "Audit Open";

    private boolean isInitial = true;

    public AuditOpen (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String ldc, String type) {
        executeRoutine(ldc, type);
    }

    public void executeRoutine (String ldc, String type) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        locationDetailCode(true, ldc);
        nextField();
        type(true, type);
        nextField();
        clickYes();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("LOCATION DETAIL CODE", 30, true);
    }
}

