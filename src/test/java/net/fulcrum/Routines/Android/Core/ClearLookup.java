package net.fulcrum.Routines.Android.Core;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Pages.Android.Routines;

public class ClearLookup extends AndroidRoutine {

    private final String currentRoutine = "Clear Lookup Logic Test";

    public ClearLookup(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines) {
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
        routine = currentRoutine;
    }

    public void executeRoutine() {
        clickNo();
        verifyRoutineValue("CLEAR THIS VALUE");
        nextField();
        clickYes();
        confirmMessage("VALUE IS CLEARED");
        clickOk();
    }
}
