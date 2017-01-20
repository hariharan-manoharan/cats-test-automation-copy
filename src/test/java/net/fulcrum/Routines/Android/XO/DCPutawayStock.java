package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class DCPutawayStock extends XORoutine {

    private final String routine = "DC Putaway Stock";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public DCPutawayStock(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String toBin, String barcode) {
        executeRoutine(toBin, barcode, -1, null);
    }

    public void execute (String toBin, String barcode, int qty, String fromBin) {
        executeRoutine(toBin, barcode, qty, fromBin);
    }

    public void executeRoutine (String toBin, String barcode, int qty, String fromBin) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toBin(false, toBin);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (qty > 0) {
            qty(true, qty);
            nextField();
            fromBin(true, fromBin);
            nextField();
        }

        verifyLoopfield(true, loopField);
    }
}
