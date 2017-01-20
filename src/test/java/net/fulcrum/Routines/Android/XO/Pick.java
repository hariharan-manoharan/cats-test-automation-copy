package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class Pick extends XORoutine {

    private final String routine = "Pick";
    private final String loopField = "Line / Item #";

    private boolean isInitial = true;

    public Pick(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines) {
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

    public void execute(String transfer, String line, String assetcode) {
        executeRoutine(transfer, line, assetcode, null, -1);
    }

    public void execute(String transfer, String line, String bin, int qty) {
        executeRoutine(transfer, line, null, bin, qty);
    }

    public void executeRoutine(String transfer, String line, String assetCode, String bin, int qty) {
        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            transferNumber(false, transfer);
            nextField();
        }

        lineItem(false, line);
        nextField();

        if (!serialized) {
            fromBin(false, bin);
            nextField();
            quantity(true, qty);
            nextField();
        } else {
            assetCodeSerialNumber(true, assetCode);
            nextField();
        }

        verifyLoopfield(false, loopField);
    }

    public void lineItem(Boolean required, String line) {
        routineDetail("Line / Item #", line, required);
    }
}
