package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class Unship extends XORoutine {

    private final String routine = "Unship";
    private final String loopField = "Shipment #";

    private boolean isInitial = true;

    public Unship(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String shipment){
        executeRoutine(shipment);
    }

    public void executeRoutine(String shipment) {
        last = true;
        shipmentNumber(true, shipment);
        nextField();
        confirmUnship("Y");
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void confirmUnship(String answer) {
        routineDetail("Confirm Unship?", answer, true);
    }
}
