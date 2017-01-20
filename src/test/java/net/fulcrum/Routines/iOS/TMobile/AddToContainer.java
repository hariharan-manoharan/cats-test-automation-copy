package net.fulcrum.Routines.iOS.TMobile;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class AddToContainer extends IOSRoutine {

    private final String routine = "Add To Container";

    private boolean isInitial = true;

    public AddToContainer(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLocation, String container, String assetCode) {
        executeRoutine(toLocation, container, assetCode);
    }

    public void executeRoutine (String toLocation, String container, String assetCode) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLocation(true, toLocation);
            nextField();
            containerCode(true, container);
            nextField();
        }

        nextField();
        assetCode(true, assetCode);
        nextField();
        populateNotes(false, "CTA: iOS " + routine);
        nextField();
    }

}
