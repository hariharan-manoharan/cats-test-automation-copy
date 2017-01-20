package net.fulcrum.Routines.iOS.TimeWarnerCable;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class AdvanceReplacement extends IOSRoutine {
    private final String routine = "Advance Replacement";

    private boolean isInitial = true;

    public AdvanceReplacement(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLDC, String rmaCode, String replacementAsset, String replacementPartCode,
                         String replacementSerial) {
        executeRoutine(toLDC, rmaCode, replacementAsset, replacementPartCode, replacementSerial);
    }

    public void executeRoutine (String toLDC, String rmaCode, String replacementAsset, String replacementPartCode,
                                String replacementSerial) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLocation(toLDC);
            nextField();
        }
        rmaCode(rmaCode);
        nextField();
        nextField();
        replacementAssetCode(replacementAsset);
        nextField();
        replacementPartCode(replacementPartCode);
        nextField();
        replacementSerialNumber(replacementSerial);
        nextField();
        fixNotes("CTA: iOS " + routine);
        nextField();
    }

    private void toLocation (String location) {
        routineDetail("toLocation", location, true);
    }

    private void replacementPartCode (String partCode) {
        routineDetail("Replacement Part Code", partCode, true);
    }

    private void replacementSerialNumber (String serial) {
        routineDetail("Replacement Serial Number", serial, false);
    }

    private void fixNotes (String notes) {
        routineDetail("Fix Notes", notes, false);
    }


}
