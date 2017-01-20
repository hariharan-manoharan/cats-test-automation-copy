package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class TakeASpare extends XORoutine {

    private final String routine = "Take a Spare";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public TakeASpare(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String job, String assetCode) {
        executeRoutine(job, assetCode, -1, null, null);
    }

    public void execute (String job, String barcode, int qty, String fromLocation, String fromStatus) {
        executeRoutine(job, barcode, qty, fromLocation, fromStatus);
    }

    public void executeRoutine (String job, String barcode, int qty, String fromLocation, String fromStatus) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            job(true, job);
            nextField();
        }

        barcode(true, barcode);
        nextField();
        if (qty > 0) {
            qty(true, qty);
            nextField();
            fromLocation(true, fromLocation);
            nextField();
            fromStatus(true, fromStatus);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }
}
