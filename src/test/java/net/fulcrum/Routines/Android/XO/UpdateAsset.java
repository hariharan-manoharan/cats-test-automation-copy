package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class UpdateAsset extends XORoutine {

    private String routine = "Update Asset";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public UpdateAsset (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute (String assetCode, String newSerial) {
        executeRoutine(assetCode, newSerial);
    }

    public void executeRoutine (String assetCode, String newSerial) {

        last = true;
        assetCode(false, assetCode);
        nextField();
        nextField();
        serial(false, newSerial);
        nextField();
        nextField();
        nextField();
        nextField();
        nextField();
        nextField();
        nextField();
        nextField();
        nextField();
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
    }

}
