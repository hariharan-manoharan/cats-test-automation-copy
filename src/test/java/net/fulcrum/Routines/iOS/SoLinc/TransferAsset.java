package net.fulcrum.Routines.iOS.SoLinc;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class TransferAsset extends IOSRoutine {

    private final String routine = "Transfer Asset";

    private boolean isInitial = true;

    public TransferAsset(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLDC, String assetCode) {
        executeRoutine(toLDC, assetCode);
    }

    public void executeRoutine (String toLDC, String assetCode) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLocationDetailCode(true, toLDC);
            nextField();
        }

        assetCode(true, assetCode);
        nextField();
        populateNotes(true, "CTA iOS: " + routine);
        nextField();
    }

}
