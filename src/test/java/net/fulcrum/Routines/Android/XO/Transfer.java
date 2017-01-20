package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class Transfer extends XORoutine {

    private final String routine = "Transfer";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public Transfer(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String job, String toLocation, String assetCode) {
        executeRoutine(job, toLocation, assetCode, -1, null, null, null);
    }

    public void execute (String job, String toLocation, String barcode, int qty, String fromLocation, String fromStatus,
                         String fromBin) {
        executeRoutine(job, toLocation, barcode, qty, fromLocation, fromStatus, fromBin);
    }

    public void executeRoutine (String job, String toLocation, String barcode, int qty, String fromLocation, String fromStatus,
                                String fromBin) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            job(true, job);
            nextField();
            toLocation(true, toLocation);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (qty > 0) {
            quantity(true, qty);
            nextField();
            fromLocation(true, fromLocation);
            nextField();
            fromStatus(true, fromStatus);
            nextField();
            fromBin(true, fromBin);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }
}
