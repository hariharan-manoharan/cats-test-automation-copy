package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class Ship extends XORoutine {

    private final String routine = "Ship";
    private final String loopField = "Transfer #";
    private final String shipMethod = "ABF LOGISTICS";

    private boolean isInitial = true;

    public Ship(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String transfer, String container) {
        executeRoutine(transfer, container);
    }

    public void executeRoutine(String transfer, String container) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        transferNumber(true, transfer);
        nextField();
        shipContainer(container);
        nextField();
        shipMethod(true, shipMethod);
        nextField();
        trackingNumber(true);
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }
}
