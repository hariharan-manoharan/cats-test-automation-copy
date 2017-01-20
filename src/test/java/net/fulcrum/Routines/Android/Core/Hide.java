package net.fulcrum.Routines.Android.Core;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

public class Hide extends AndroidRoutine {

    private final String currentRoutine = "Hide Test";

    public Hide(AndroidMobilityDriver mobilityDriver) {
        this.mobilityDriver = mobilityDriver;
    }

    public void executeRoutine() {
        verifyRoutineDetail("SHOULD SEE THIS FIELD", false);
    }
}
