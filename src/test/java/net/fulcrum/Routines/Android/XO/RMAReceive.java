package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;

public class RMAReceive extends XORoutine {

    private final String routine = "RMA Receive";
    private final String shipper = "UPS";
    private final String trackingNumber = "2421421";

    public RMAReceive(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String location, String assetCode, String item, String rmaNumber, String remedy) {
        executeRoutine(location, assetCode, item, rmaNumber, remedy);
    }

    public void executeRoutine(String location, String assetCode, String item, String rmaNumber, String remedy) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        isInitial = false;
        receiveAssetCode(true, assetCode);
        nextField();
        xoItemMfgPart(true, item);
        nextField();
        serial(true, "S" + assetCode);
        nextField();
        remedy(true, remedy);
        nextField();
        rmaNumber(true, rmaNumber);
        nextField();
        shipper(true, shipper);
        nextField();
        trackingNumber(true, trackingNumber);
        nextField();
        toLocation(true, location);
        nextField();
        confirmSpareReplacement(true);
        notes(false, "CTA: " + routine);
        nextField();
    }

    public void routineComplete() {
        result = verifyRoutineDetail("Receive Asset Code", 60, true);
    }

    public void confirmSpareReplacement(boolean confirm) {
        if (confirm) {
            mobilityDriver.confirmYes();
        } else {
            mobilityDriver.confirmNo();
        }
    }

}
