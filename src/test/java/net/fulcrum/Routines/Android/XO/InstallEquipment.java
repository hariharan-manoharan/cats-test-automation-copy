package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class InstallEquipment extends XORoutine {

    private final String routine = "Install Equipment";
    private final String loopField = "Barcode";
    private final String address = "1 5TH AVE, NEW YORK NY 10003";

    private boolean isInitial = true;

    public InstallEquipment(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String job, String barcode, String location) {
        executeRoutine(job, barcode, -1, location, null);
    }

    public void execute(String job, String barcode, int qty, String location, String status) {
        executeRoutine(job, barcode, qty, location, status);
    }

    public void executeRoutine(String job, String barcode, int qty, String location, String status) {
        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            job(true, job);
            nextField();
            requisitionPrompt(true, "N");
            nextField();
            installAddress(true, address);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (!serialized) {
            qty(true, qty);
            nextField();
            fromLocation(true, location);
            nextField();
            fromStatus(true, status);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void requisitionPrompt(boolean required, String requisition) {
        routineDetail("Is there a Requisition?", requisition, required);
    }

    public void installAddress(boolean required, String address) {
        routineDetail("Install Address", address, required);
    }
}
