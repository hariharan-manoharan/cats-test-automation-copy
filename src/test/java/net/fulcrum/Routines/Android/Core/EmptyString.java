package net.fulcrum.Routines.Android.Core;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

public class EmptyString extends AndroidRoutine {

    private final String currentRoutine = "Empty String Test";

    public EmptyString(AndroidMobilityDriver mobilityDriver) {
        this.mobilityDriver = mobilityDriver;
    }

    public void executeRoutine() {
        nextField();
        confirmMessage("EMPTY");
        clickOk();
        routineDetail("Value", "ABCDE", false);
        nextField();
        confirmMessage("NOT EMPTY");
        clickOk();
    }
}
