package net.fulcrum.Routines.iOS.TimeWarnerCable;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class PlaceAssetInService extends IOSRoutine {

    private final String routine = "Place Asset In Service";

    private boolean isInitial = true;

    public PlaceAssetInService(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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
            nextField();
        }

        assetCode(assetCode);
        nextField();

    }

}
