package net.fulcrum.Routines.iOS.TimeWarnerCable;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class AddAsset extends IOSRoutine {

    private final String routine = "Add Asset";

    private boolean isInitial = true;

    public AddAsset(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

        if (isInitial) {
            isInitial = false;
            toLocationDetailCode(true, toLDC);
            nextField();
        }

        locationDetail(true, toLDC);
        nextField();
        nextField();
        nextField();
        assetCode(assetCode);
        nextField();
        partCode(partCode);
        nextField();
        serialNumber(true, serial);
        nextField();
        nextField();
        populateNotes(false, "CTA: iOS " + routine);
    }

}
