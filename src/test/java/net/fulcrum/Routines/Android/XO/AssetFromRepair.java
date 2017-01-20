package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;

public class AssetFromRepair extends XORoutine {

    private String routine = "Asset From Repair";

    private String shipper = "UPS";
    private String trackingNumber = "2421421";

    public AssetFromRepair (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String location, String item, String rmaNumber, String bin) {
        executeRoutine(location, item, rmaNumber, bin);
    }

    public void executeRoutine(String location, String item, String status, String bin) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        isInitial = false;
        receiveAssetCode(true, item);
        nextField();
        rmaNumber(true);
        nextField();
        shipper(true, shipper);
        nextField();
        trackingNumber(true, trackingNumber);
        nextField();
        toLocation(true, location);
        nextField();
        toStatus(true, status);
        nextField();

        if (bin != null) {
            toBin(true, bin);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
    }

    public void routineComplete() {
        result = verifyRoutineDetail("Receive Asset Code", 60, true);
    }

}
