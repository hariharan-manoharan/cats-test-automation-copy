package net.fulcrum.Routines.iOS.TMobile;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class AssetTransfer extends IOSRoutine {

    private final String routine = "Asset Transfer";

    private boolean isInitial = true;

    public AssetTransfer(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLDC, String assetCode ) {
        executeRoutine(toLDC, assetCode);
    }

    public void executeRoutine (String toLocation, String assetCode) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLocation(true, toLocation);
            nextField();
        }

        assetCode(true, assetCode);
        nextField();
    }

}
