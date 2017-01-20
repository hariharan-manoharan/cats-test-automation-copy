package net.fulcrum.Routines.Android.Core;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

public class Length extends AndroidRoutine {

    private final String currentRoutine = "Length Test";

    public Length(AndroidMobilityDriver mobilityDriver) {
        this.mobilityDriver = mobilityDriver;
    }

    public void executeRoutine() {
        routineDetail("Value", "AB" ,false);
        nextField();
        confirmMessage("Value less than 5 characters");
        clickOk();
        routineDetail("Value", "ABCDE", false);
        nextField();
        confirmMessage("Value is 5 characters");
        clickOk();
        routineDetail("Value", "ABCDEF", false);
        nextField();
        confirmMessage("Value more than 5 characters");
        clickOk();
    }
}
