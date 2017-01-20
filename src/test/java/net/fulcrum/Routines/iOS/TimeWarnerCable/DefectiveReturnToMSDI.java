package net.fulcrum.Routines.iOS.TimeWarnerCable;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class DefectiveReturnToMSDI extends IOSRoutine {

    private final String routine = "Defective Return to MSDI";

    private boolean isInitial = true;

    public DefectiveReturnToMSDI (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String assetCode) {
        executeRoutine(assetCode);
    }

    public void executeRoutine (String assetCode) {

        last = true;

        assetCode(assetCode);
        nextField();

    }

}
