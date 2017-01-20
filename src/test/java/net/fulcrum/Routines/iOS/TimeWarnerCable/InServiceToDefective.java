package net.fulcrum.Routines.iOS.TimeWarnerCable;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class InServiceToDefective extends IOSRoutine {
private final String routine = "In Service To Defective";

private boolean isInitial = true;

    public InServiceToDefective(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLDC, String assetCode, String reason) {
        executeRoutine(toLDC, assetCode, reason);
    }

    public void executeRoutine (String toLDC, String assetCode, String reason) {

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
        failureReason(reason);
        nextField();

    }

    private void failureReason(String reason) {
        routineDetail("Failure Reason", reason, false);
    }

}
