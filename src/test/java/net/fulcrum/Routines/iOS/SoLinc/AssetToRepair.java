package net.fulcrum.Routines.iOS.SoLinc;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class AssetToRepair extends IOSRoutine {

    private final String routine = "Asset To Repair";

    private boolean isInitial = true;

    private String shipper = "UPS";
    private String trackingNumber = "123456";
    private String failReason = "DC FAIL";

    public AssetToRepair(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLDC, String raCode, String assetCode, String failLDC) {
        executeRoutine(toLDC, raCode, assetCode, failLDC);
    }

    public void executeRoutine (String toLDC, String raCode, String assetCode, String failLDC) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLocationDetailCode(true, toLDC);
            nextField();
            raCode(true, raCode);
            nextField();
            shipper(false, shipper);
            nextField();
            trackingNumber(false, trackingNumber);
            nextField();
        }

        failAssetCode(true, assetCode);
        nextField();
        failReasonCode(failReason);
        nextField();
        failFoundBy("CATSADM");
        nextField();
        failLocationDetailCode(false, failLDC);
        nextField();
        populateNotes(true, "CTA iOS: " + routine);
        nextField();
    }

    public void failFoundBy(String contact){
        routineDetail("Fail Found By", contact, false);
    }

}
