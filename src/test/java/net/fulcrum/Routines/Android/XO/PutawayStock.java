package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class PutawayStock extends XORoutine {

    private final String routine = "Putaway Stock";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public PutawayStock(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String location, String type, String toStatus, String toBin, String barcode) {
        executeRoutine(location, type, toStatus, toBin, barcode, -1, null);
    }

    public void execute (String location, String type, String toStatus, String toBin, String barcode, int qty,
                         String fromStatus) {
        executeRoutine(location, type, toStatus, toBin, barcode, qty, fromStatus);
    }

    public void executeRoutine (String location, String type, String toStatus, String toBin, String barcode, int qty,
                                String fromStatus) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            location(true, location);
            nextField();
            type(true, type);
            nextField();
            toStatus(false, toStatus);
            nextField();
            toBin(true, toBin);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (qty > 0) {
            qty(true, qty);
            nextField();
            fromStatus(true, fromStatus);
        } else {
            serialNumber(false, "S" + barcode);
        }

        nextField();
        verifyLoopfield(true, loopField);
    }
}
