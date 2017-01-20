package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class VendorStatusUpdate extends XORoutine {

    private final String routine = "Vendor Status Update";
    private final String loopField = "RMA #";

    private boolean isInitial = true;

    public VendorStatusUpdate(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String rmaNumber, String line, String condition, String fault)  {
        executeRoutine(rmaNumber, line, -1, -1, condition, fault);
    }

    public void execute(String rmaNumber, String line, int lineQty, int repairQty) {
        executeRoutine(rmaNumber, line, lineQty, repairQty, null, null);
    }

    public void executeRoutine(String rmaNumber, String mfgPartNumber, int lineQty, int repairQty, String condition,
                               String fault) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        rmaNumber(true, rmaNumber);
        nextField();
        verifyRoutineDetail("Line #", true);
        clickValidationFile();
        clickLookupValue(mfgPartNumber);

        if(lineQty > 0) {
            repairQty(repairQty);
            nextField();
        } else {
            conditionCode(condition);
            nextField();
            faultCode(fault);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void conditionCode(String condition) {
        routineDetail("Condition Code", condition, true);
    }

    public void faultCode(String fault) {
        routineDetail("Fault Code", fault, true);
    }

    public void lineQty(int qty) {
        routineDetail("Line Qty", String.valueOf(qty), true);
    }

    public void repairQty(int qty) {
        routineDetail("Repair Qty", String.valueOf(qty), true);
    }

    public void replaceQty(int qty) {
        routineDetail("Replace Qty", String.valueOf(qty), true);
    }

    public void berQty(int qty) {
        routineDetail("BER Qty", String.valueOf(qty), true);
    }

}
