package net.fulcrum.Routines.Android.Core;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Pages.Android.Routines;

public class AssignValue extends AndroidRoutine {

    private final String currentRoutine = "Assign Value Test";

    public AssignValue(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines) {
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
        routine = currentRoutine;
    }

    public void executeRoutine() {
        confirmMessage("PASS");     //Validate Assignment of ABC
        clickOk();
        confirmMessage("PASS");     //Validate Assignment of 123456789
        clickOk();
        confirmMessage("PASS");     //Validate Assignment of FULCRUM IS AWESOME
        clickOk();
        verifyCurrentRoutineValue("FULCRUM IS AWESOME!");
    }
}
