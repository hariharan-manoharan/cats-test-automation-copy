package net.fulcrum.Routines.iOS.SoLinc;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class StockAsset extends IOSRoutine {

    private final String routine = "Stock Asset";

    private boolean isInitial = true;

    public StockAsset(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

        assetCode(true, assetCode);
        nextField();
        partCode(true, partCode);
        nextField();
        serial(false, serial);
        nextField();
        populateNotes(true, "CTA iOS: " + routine);
        nextField();
    }

}
