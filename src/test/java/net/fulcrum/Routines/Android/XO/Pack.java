package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class Pack extends XORoutine {

    private final String routine = "Pack";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public Pack(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines) {
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

    public void execute(String transfer, String container, String assetCode) {
        executeRoutine(transfer, container, assetCode, -1);
    }

    public void execute(String transfer, String container, String barcode, int qty) {
        executeRoutine(transfer, container, barcode, qty);
    }

    public void executeRoutine(String transfer, String container, String barcode, int qty) {
        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            transferNumber(true, transfer);
            nextField();
            generateContainer("N");
            nextField();
            container(container);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (!serialized) {
            quantity(true, qty);
            nextField();
        }

        verifyLoopfield(true, loopField);
    }
}
