package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class Unpack extends XORoutine {

    private final String routine = "Unpack";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public Unpack(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String container, String barcode) {
        executeRoutine(container, barcode, -1);
    }


    public void execute(String container, String barcode, int qty) {
        executeRoutine(container, barcode, qty);
    }

    public void executeRoutine(String container, String barcode, int qty) {
        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            shipContainer(container);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if(!serialized) {
            quantity(true, qty);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }
}
