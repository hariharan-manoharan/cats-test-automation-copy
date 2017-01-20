package net.fulcrum.Routines.iOS.TMobile;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class TransferContainer extends IOSRoutine {

    private final String routine = "Transfer Container";

    private boolean isInitial = true;

    public TransferContainer(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLocation, String container) {
        executeRoutine(toLocation, container);
    }

    public void executeRoutine (String toLocation, String container) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLocation(true, toLocation);
            nextField();
        }

        containerCode(true, container);
        nextField();
        populateNotes(false, "CTA: iOS " + routine);
        nextField();
    }

}
