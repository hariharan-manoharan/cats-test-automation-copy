package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class POCorrect extends XORoutine {

    private final String routine = "PO Correct";
    private final String loopField = "Barcode";
    private final String reason = "WRONG PART";

    private boolean isInitial = true;

    public POCorrect(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public void execute(String barcode, String poCode, String lineNumber) {
        executeRoutine(barcode, poCode, lineNumber, -1, null, null, null);
    }

    public void execute(String barcode, String poCode, String lineNumber, int qty, String fromLocation,
                        String fromStatus, String bin) {
        executeRoutine(barcode, poCode, lineNumber, qty, fromLocation, fromStatus, bin);
    }

    public void executeRoutine(String barcode, String poCode, String lineNumber, int qty, String fromLocation,
                               String fromStatus, String bin) {

        last = true;
        setSerialized(qty);

        if(!isInitial && !result) {
            reset();
        }

        barcode(true, barcode);
        nextField();
        poCode(true, poCode);
        nextField();
        line(true, lineNumber);
        nextField();
        reason(true, reason);
        nextField();

        if(!serialized) {
            fromLocation(true, fromLocation);
            nextField();
            fromStatus(false, fromStatus);
            nextField();
            fromBin(false, bin);
            nextField();
            qtyToCorrect(qty);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void qtyToCorrect(int qty) {
        routineDetail("Qty to Correct", String.valueOf(qty), false);
    }
}
