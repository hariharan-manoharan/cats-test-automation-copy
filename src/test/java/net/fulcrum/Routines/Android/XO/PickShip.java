package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class PickShip extends XORoutine {

    private final String routine = "Pick/Ship";
    private final String loopField = "Transfer #";
    private final String shipMethod = "ABF LOGISTICS";
    private final String trackingNumber = "8888";

    private boolean isInitial = true;

    public PickShip(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String transfer, String line, String assetCode) {
        executeRoutine(transfer, line, null, -1, assetCode);
    }

    public void execute(String transfer, String line, String bin, int qty){
        executeRoutine(transfer, line, bin, qty, null);
    }

    public void executeRoutine(String transfer, String line, String bin, int qty, String assetCode) {
        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        transferNumber(true, transfer);
        nextField();
        line(true, line);
        nextField();

        if (!serialized) {
            fromBin(true, bin);
            nextField();
            quantity(true, qty);
        } else {
            assetCodeSerialNumber(true, assetCode);
        }

        nextField();
        shipMethod(true, shipMethod);
        nextField();
        tracking();
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void tracking() {
        String tracking = "TRACK" + mobilityDriver.getRandomInt(10000);
        routineDetail("Tracking#", tracking, true);
    }
}
