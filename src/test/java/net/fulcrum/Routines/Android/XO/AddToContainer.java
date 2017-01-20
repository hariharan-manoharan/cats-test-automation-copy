package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;

public class AddToContainer extends XORoutine {

    private String routine = "Add To Container";

    public AddToContainer (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String location, String container, String status, String barcode) {
        executeRoutine(location, container, status, barcode, 0);
    }

    public void execute(String location, String container, String status, String barcode, int qty) {
        executeRoutine(location, container, status, barcode, qty);
    }

    public void executeRoutine(String location, String container, String status, String barcode, int qty) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            containerCode(true, container);
            nextField();
            newContainer("Y");
            nextField();
            containerLocation(true, location);
            nextField();
            containerStatus(true, status);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (qty > 0) {
            qty(true, qty);
            nextField();
        }

        notes(false, routine);
        nextField();
        routineComplete();
    }

    public void routineComplete() {
        result = verifyRoutineDetail("Barcode", 60, true);
    }

    public void newContainer(String answer) {
        routineDetail("New Container? Y/N", answer, true);
    }

}
