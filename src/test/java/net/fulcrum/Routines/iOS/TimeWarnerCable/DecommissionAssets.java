package net.fulcrum.Routines.iOS.TimeWarnerCable;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class DecommissionAssets extends IOSRoutine {

    private final String routine = "Decommission Assets";

    private boolean isInitial = true;

    public DecommissionAssets(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLDC,String assetCode) {
        executeRoutine(toLDC, assetCode);
    }

    public void executeRoutine (String toLDC, String assetCode) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            locationDetail(true, toLDC);
            nextField();
        }

        assetCode(assetCode);
        nextField();

    }

}
