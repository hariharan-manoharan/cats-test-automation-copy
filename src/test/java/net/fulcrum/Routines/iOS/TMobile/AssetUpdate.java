package net.fulcrum.Routines.iOS.TMobile;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class AssetUpdate extends IOSRoutine {

    private final String routine = "Asset Update";

    private boolean isInitial = true;

    public AssetUpdate(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String oldAsset, String newAsset) {
        executeRoutine(oldAsset, newAsset);
    }

    public void executeRoutine (String assetCode, String serial) {
        last = true;
        assetCode(true, assetCode);
        nextField();
        nextField();
        nextField();
        nextField();
        serialNumber(false, serial);
        nextField();
        nextField();
        nextField();
        populateNotes(false, "CTA: iOS " + routine);
        nextField();
    }

}
