package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class Audit extends XORoutine {

    private final String routine = "Audit";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public Audit(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String location, String status, String barcode, int qty) {
        executeRoutine(location, status, barcode, qty);
    }

    public void execute(String location, String status, String barcode) {
        executeRoutine(location, status, barcode, -1);
    }

    public void executeRoutine(String location, String status, String barcode, int qty) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            location(true, location);
            nextField();
            status(true, status);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (qty > 0) {
            qty(true, qty);
            nextField();
        }

        verifyLoopfield(true, loopField);
    }
}
