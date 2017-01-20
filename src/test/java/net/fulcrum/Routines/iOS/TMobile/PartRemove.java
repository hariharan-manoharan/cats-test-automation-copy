package net.fulcrum.Routines.iOS.TMobile;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class PartRemove extends IOSRoutine {

    private final String routine = "Part Remove";

    private boolean isInitial = true;

    public PartRemove(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String fromLocation, String partCode, int qty) {
        executeRoutine(fromLocation, partCode, qty);
    }

    public void executeRoutine (String fromLocation, String partCode, int qty) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            fromLocation(true, fromLocation);
            nextField();
        }

        partCode(true, partCode);
        nextField();
        quantity(true, qty);
        nextField();
        populateNotes(false, "CTA: iOS " + routine);
        nextField();
    }

}
