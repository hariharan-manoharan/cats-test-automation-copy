package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class Unpick extends XORoutine {

    private final String routine = "Unpick";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public Unpick(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines) {
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

    public void execute(String transfer, String barcode, String bin) {
        executeRoutine(transfer, barcode, bin, -1);
    }

    public void execute(String transfer, String barcode, String bin, int qty) {
        executeRoutine(transfer, barcode, bin, qty);
    }

    public void executeRoutine(String transfer, String barcode, String bin, int qty) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            transferNumber(false, transfer);
            nextField();
        }

        barcode(true, barcode);
        nextField();
        toBin(true, bin);
        nextField();

        if (qty > 0) {
            quantity(true, qty);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void lineItem(Boolean required, String line) {
        routineDetail("Line / Item #", line, required);
    }
}
