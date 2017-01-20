package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class AuditOpen extends XORoutine {

    private final String routine = "Audit Open";
    private final String loopField = "Location";

    private boolean isInitial = true;

    public AuditOpen(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String location, String type, String confirm) {
        executeRoutine(location, type, confirm);
    }

    public void executeRoutine(String location, String type, String confirm) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        location(true, location);
        nextField();
        auditType(true, type);
        nextField();
        confirmOpen(true, confirm);
        nextField();
        verifyLoopfield(true, loopField);
    }
}
