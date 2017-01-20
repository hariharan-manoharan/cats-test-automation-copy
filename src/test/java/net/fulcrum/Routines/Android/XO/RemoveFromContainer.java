package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;

public class RemoveFromContainer extends XORoutine {

    private final String routine = "Remove from Container";

    public RemoveFromContainer (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String location, String container, String asset) {
        executeRoutine(location, container, asset, 0);
    }

    public void execute(String location, String container, String item, int qty) {
        executeRoutine(location, container, item, qty);
    }

    public void executeRoutine(String location, String container, String item, int qty) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            containerCode(true, container);
            nextField();
            toLocation(true, location);
            nextField();
            nextField();
        }

        itemToRemove(item);
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
        result = verifyRoutineDetail("Item to Remove", 60, true);
    }

    public void itemToRemove(String item) {
        routineDetail("Item to Remove", item, true);
    }

}
