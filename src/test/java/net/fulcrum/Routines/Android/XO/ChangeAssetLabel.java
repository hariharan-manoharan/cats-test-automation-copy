package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class ChangeAssetLabel extends XORoutine {

    private String routine = "Change Asset Label";

    private boolean isInitial = true;

    public ChangeAssetLabel (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String oldAssetCode, String newAssetCode) {
        executeRoutine(oldAssetCode, newAssetCode);
    }

    public void executeRoutine (String oldAssetCode, String newAssetCode) {

        last = true;

        oldAssetCode(oldAssetCode);
        nextField();
        newAssetCode(newAssetCode);
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
    }

    private void oldAssetCode(String asset) {
        routineDetail("Old Asset Code", asset, true);
    }

    private void newAssetCode(String asset) {
        routineDetail("New Asset Code", asset, true);
    }

}
