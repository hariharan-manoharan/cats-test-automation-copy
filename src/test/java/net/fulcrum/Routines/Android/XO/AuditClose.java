package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class AuditClose extends XORoutine {

    private final String routine = "Audit Close";
    private final String loopField = "Location";

    private boolean isInitial = true;

    public AuditClose(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String location, String confirm) {
        executeRoutine(location, confirm);
    }

    public void executeRoutine(String location, String confirm) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        location(true, location);
        nextField();
        accuracyScore();
        confirmClose(confirm);
        verifyLoopfield(true, loopField);
    }


    public void accuracyScore() {
        clickPreviousRoutineDetail("Accuracy Score");
    }

    public void confirmClose(String answer) {
        verifyRoutineDetail("Confirm Close", true);
        clickValidationFile();
        clickLookupValue(answer);
    }
}
