package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;

public class DestroyContainer extends XORoutine {

    private final String routine = "Destroy Container";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public DestroyContainer (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute(String containderCode) {
        executeRoutine(containderCode);
    }

    public void executeRoutine (String containerCode) {
        last = true;
        containerCode(true, containerCode);
        nextField();
        destroyContainer("Y");
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("Container Code", 60, true);
    }

    public void destroyContainer(String answer) {
        routineDetail("Destroy Container? Y/N", answer, true);
    }

}
