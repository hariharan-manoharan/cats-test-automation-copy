package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;

public class RMAReturn extends XORoutine {

    private final String routine = "RMA Return";
    private final String shipMethod = "ABF LOGISTICS";
    private final String trackingNumber = "2421421";
    private final String reason = "DAMAGED";

    public RMAReturn(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String assetCode, String rmaNumber) {
        executeRoutine(assetCode, rmaNumber);
    }

    public void executeRoutine(String assetCode, String rmaNumber) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        isInitial = false;
        assetCodeSerialNumber(true, assetCode);
        nextField();
        rmaNumber(true, rmaNumber);
        nextField();
        searchClickLookup(assetCode);
        nextField();
        shipMethod(true, shipMethod);
        nextField();
        trackingNumber(true, trackingNumber);
        nextField();
        reason(false, reason);
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
    }

    public void routineComplete() {
        result = verifyRoutineDetail("Receive Asset Code", 60, true);
    }

    public void confirmSpareReplacement(boolean confirm) {
        confirmMessage("IS THIS A SPARE REPLACEMENT?");

        if (confirm) {
            clickYes();
        } else {
            clickNo();
        }
    }

}
