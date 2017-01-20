package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class InternalReceive extends XORoutine {

    private final String routine = "Internal Receive";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public InternalReceive(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String requisition, String container, String destroy, String bin) {
        executeRoutine(requisition, container, destroy, bin);
    }

    public void executeRoutine(String requisition, String container, String destroy, String bin) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            requisition(false, requisition);
            nextField();
            shipContainer(container);
            nextField();
        }

        barcode(true, container);
        nextField();

        if (destroy.equalsIgnoreCase("Y")) {
            destroyContainer(true, "Y");
        } else {
            destroyContainer(true, "N");
        }

        nextField();

        if (bin != null) {
            bin(true, bin);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void requisition(boolean required, String requisition) {
        routineDetail("Requisition #", requisition, required);
    }

    public void destroyContainer(boolean required, String answer) {
        routineDetail("Destroy Container?", answer, required);
    }
}
