package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;

public class AssetToRepair extends XORoutine {

    private String routine = "Asset To Repair";

    private String shipMethod = "UPS 2DAY";
    private String trackingNumber = "2421421";
    private String reason = "DAMAGED";

    public AssetToRepair (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String location, String item, String rmaNumber) {
        executeRoutine(location, item, rmaNumber);
    }

    public void executeRoutine(String location, String item, String rmaNumber) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        isInitial = false;
        assetCodeSerialNumber(true, item);
        nextField();
        rmaNumber(true, rmaNumber);
        nextField();
        shipMethod(true, shipMethod);
        nextField();
        trackingNumber(true, trackingNumber);
        nextField();
        toLocation(true, location);
        nextField();
        reason(false, reason);
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
    }

    public void routineComplete() {
        result = verifyRoutineDetail("Asset Code / Serial #", 60, true);
    }

}
