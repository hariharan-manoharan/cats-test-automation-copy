package net.fulcrum.Routines.iOS.TimeWarnerCable;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class AdvanceReplacementDefReturn extends IOSRoutine {
    private final String routine = "Advance Rpl. - Def. Return";

    private boolean isInitial = true;

    public AdvanceReplacementDefReturn(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String rmaCode, String replacementAsset, String defectAsset, String failure) {
        executeRoutine(rmaCode, replacementAsset, defectAsset, failure);
    }

    public void executeRoutine (String rmaCode, String replacementAsset, String defectAsset, String failure) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            rmaCode(rmaCode);
            nextField();
        }

        replacementAssetCode(replacementAsset);
        nextField();
        defectiveAssetCode(defectAsset);
        nextField();
        failureCode(failure);
        notes("CTA: iOS " + routine);
        nextField();
    }

    private void defectiveAssetCode(String asset) {
        routineDetail("Defective Asset Code", asset, false);
    }

    private void failureCode(String reason) {
        routineDetail("Failure Code", reason, false);
    }

}
