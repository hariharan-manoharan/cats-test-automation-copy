package net.fulcrum.Routines.Android.Core;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Pages.Android.Routines;

public class Fields extends AndroidRoutine {

    private final String currentRoutine = "Clear Test";

    public Fields(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines) {
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
        routine = currentRoutine;
    }

    public void executeRoutine() {
        verifyRoutineDetail("Value 2", false);
        nextField();
        verifyRoutineDetail("Value 3", false);
        nextField();
        verifyRoutineDetail("Value 4", false);
        nextField();
        verifyRoutineDetail("Value 5", false);
        nextField();
        verifyRoutineDetail("Value 6", false);
        nextField();
        verifyRoutineDetail("Value 7", false);
        nextField();
        verifyRoutineDetail("Value 8", false);
    }
}
