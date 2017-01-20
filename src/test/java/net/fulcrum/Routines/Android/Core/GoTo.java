package net.fulcrum.Routines.Android.Core;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Pages.Android.Routines;

public class GoTo extends AndroidRoutine {

    private final String currentRoutine = "GoTo Test";

    public GoTo(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines) {
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
        routine = currentRoutine;
    }

    public void executeRoutine() {
        nextField();
        verifyCurrentRoutineValue("IT WORKED");
    }
}
