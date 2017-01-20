package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Pages.Android.*;

import java.util.*;

public class RemoveFromContainer extends AirtelRoutine {

    private final String routine = "Remove From Container";

    private boolean serialized = false;
    private boolean childContainer = false;

    private boolean isInitial = true;

    public RemoveFromContainer (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        notes = "AIRTEL CTA Android: " + routine;
    }

    public void reset() {
        mobilityDriver.clickByName("LOCATION");
    }

    public void setInitial() {
        isInitial = true;
    }

    public void select(Menu menu, Routines routines) {
        selectRoutine(menu, routines, routine);
    }

    public void execute (String location, String container, String childContainer) {
        executeRoutine(location, container, childContainer, null, null, -1);
    }

    public void execute (String location, String container, String partCode, String assetCode) {
        executeRoutine(location, container, partCode, assetCode, null, 0);
    }

    public void execute (String location, String container, String partCode, String lot, int qty) {
        executeRoutine(location, container, partCode, null, lot, qty);
    }

    public void executeRoutine (String location, String container, String barcode, String assetCode,
                                String lot, int qty) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (qty == 0) {
            serialized = true;
        } else {
            serialized = false;
        }

        if (qty == -1) {
            childContainer = true;
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            location(true, location);
            nextField();
            container(true, container);
            nextField();
        }

        barcode(true, barcode, true);
        nextField();

        if (!serialized && !childContainer) {
            qty(true, qty);
            nextField();
            lotNumber(true, lot);
        } else if (serialized){
            barcode2(false, assetCode);
        }

        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        if (serialized) {
            result = verifyRoutineDetail("BARCODE", 30, true);
        }
        serialized = false;
        childContainer = false;
    }
}

