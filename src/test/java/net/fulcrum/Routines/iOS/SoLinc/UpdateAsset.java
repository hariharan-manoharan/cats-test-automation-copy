package net.fulcrum.Routines.iOS.SoLinc;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class UpdateAsset extends IOSRoutine {

    private final String routine = "Update Asset";

    private boolean isInitial = true;

    public UpdateAsset(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLDC, String partCode, String assetCode, String serial) {
        executeRoutine(toLDC, partCode, assetCode, serial);
    }

    public void executeRoutine (String toLDC, String partCode, String assetCode, String serial) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        assetCode(true, assetCode);
        nextField();

        if (toLDC != null) {
            toLocationDetailCode(true, toLDC);
        }

        nextField();

        if (partCode != null) {
            partCode(true, partCode);
        }

        nextField();

        if (serial != null) {
            serial(false, serial);
        }

        nextField();
        populateNotes(true, "CTA iOS: " + routine);
        nextField();
    }

}
