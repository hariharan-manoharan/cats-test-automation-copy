package net.fulcrum.Routines.iOS.SoLinc;

        import net.fulcrum.Drivers.*;
        import net.fulcrum.Routines.iOS.IOSRoutine;

public class ChangeAssetLabel extends IOSRoutine {

    private final String routine = "Change Asset Label";

    private boolean isInitial = true;

    public ChangeAssetLabel(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String assetCode, String newAssetCode) {
        executeRoutine(assetCode, newAssetCode);
    }

    public void executeRoutine (String assetCode, String newAssetCode) {

        last = true;

        oldAssetCode(assetCode);
        nextField();
        newAssetCode(newAssetCode);
        nextField();
        populateNotes(true, "CTA iOS: " + routine);
        nextField();
    }

    public void oldAssetCode(String assetCode) {
        routineDetail("Old Asset Code", assetCode, true);
    }

    public void newAssetCode(String assetCode) {
        routineDetail("New Asset Code", assetCode, true);
    }

}
