package net.fulcrum.Routines.iOS.TMobile;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class AssetRelabel extends IOSRoutine {

    private final String routine = "Asset Re-Label";

    private boolean isInitial = true;

    public AssetRelabel(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String oldAsset, String newAsset) {
        executeRoutine(oldAsset, newAsset);
    }

    public void executeRoutine (String oldAsset, String newAsset) {
        last = true;
        oldAssetCode(oldAsset);
        nextField();
        newAssetCode(newAsset);
        populateNotes(true, "CTA iOS: " + routine);
        nextField();
    }

}
