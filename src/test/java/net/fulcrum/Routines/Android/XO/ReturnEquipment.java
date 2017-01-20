package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class ReturnEquipment extends XORoutine {

    private final String routine = "Return Equipment";
    private final String loopField = "Barcode";
    private final String address = "1 5TH AVE, NEW YORK NY 10003";

    private boolean isInitial = true;

    public ReturnEquipment(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String job, String barcode, String location, String condition) {
        executeRoutine(job, barcode, -1, location, condition);
    }

    public void execute(String job, String barcode, int qty, String location, String condition) {
        executeRoutine(job, barcode, qty, location, condition);
    }

    public void executeRoutine(String job, String barcode, int qty, String location, String condition) {
        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            job(true, job);
            nextField();
            customerAddress(true, address);
            nextField();
            nextField();
            nextField();
            toLocation(true, location);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (!serialized) {
            qty(true, qty);
        }

        nextField();

        condition(true, condition);
        nextField();
        toBin(true, "ENG");
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void customerAddress(boolean required, String address) {
        routineDetail("Customer Address", address, required);
    }
}
