package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class POReturnToVendor extends XORoutine {

    private final String routine = "PO Return To Vendor";
    private final String loopField = "Barcode";
    private final String reason = "DAMAGED";
    private final String condition = "DEFECTIVE";
    private final String shipMethod = "ABF Logistics";

    private boolean isInitial = true;

    public POReturnToVendor(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String type, String barcode, String poCode, String lineNumber, String rmaNumber) {
        executeRoutine(type, barcode, poCode, lineNumber, rmaNumber, -1 ,null, null, null);
    }

    public void execute(String type, String barcode, String poCode, String lineNumber,
                        String rmaNumber, int qty, String location, String status, String bin) {
        executeRoutine(type, barcode, poCode, lineNumber, rmaNumber, qty, location, status, bin);
    }

    public void executeRoutine(String type, String barcode, String poCode, String lineNumber,
                               String rmaNumber, int qty, String location, String status, String bin) {

        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            returnFromType(type);
            nextField();
        }

        barcode(true, barcode);
        nextField();
        poCode(true, poCode);
        nextField();
        line(true, lineNumber);
        nextField();
        condition(true, condition);
        nextField();
        reason(true, reason);
        nextField();
        rmaNumber(true, rmaNumber);
        nextField();
        shipMethod(true, shipMethod);
        nextField();
        trackingNumber(true);
        nextField();

        if (!serialized) {
            fromLocation(true, location);
            nextField();
            fromStatus(true, status);
            nextField();
            fromBin(true, bin);
            nextField();
            qtyToReturn(qty);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void returnFromType(String type) {
        routineDetail("Return From Type", type, true);
    }

    public void qtyToReturn(int qty) {
        routineDetail("Qty to Return", String.valueOf(qty), true);
    }
}
