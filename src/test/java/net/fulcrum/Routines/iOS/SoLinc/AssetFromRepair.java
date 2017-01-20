package net.fulcrum.Routines.iOS.SoLinc;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class AssetFromRepair extends IOSRoutine {

    private final String routine = "Asset From Repair";

    private boolean isInitial = true;

    private String shipper = "UPS";
    private String trackingNumber = "1234567890";
    private String fixReason = "DC FAIL";

    public AssetFromRepair(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String receiveLDC, String raCode, String assetCode, String fixAssetCode, String fixPartCode,
                         String fixSerial) {
        executeRoutine(receiveLDC, raCode, assetCode, fixAssetCode, fixPartCode, fixSerial);
    }

    public void executeRoutine (String receiveLDC, String raCode, String assetCode, String fixAssetCode, String fixPartCode,
        String fixSerial) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            receiveLocationDetailCode(receiveLDC);
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
        clickYes();
        replacement("Y");
        nextField();
        fixAssetCode(fixAssetCode);
        nextField();
        fixPartCode(fixPartCode);
        nextField();
        fixSerial(fixSerial);
        nextField();
        fixReason(fixReason);
        nextField();
        populateNotes(true, "CTA iOS: " + routine);
        nextField();
    }

    public void receiveLocationDetailCode(String ldc){
        routineDetail("Receive Location Detail Code", ldc, true);
    }

    public void replacement(String answer){
        routineDetail("Is This a Replacement?", answer, true);
    }

    public void fixPartCode(String partCode){
        routineDetail("Fix Part Code", partCode, true);
    }

    public void fixSerial(String serial){
        routineDetail("Fix Serial", serial, false);
    }

    public void fixReason(String reason) {
        routineDetail("Fix Reason", reason, false);
    }

}
