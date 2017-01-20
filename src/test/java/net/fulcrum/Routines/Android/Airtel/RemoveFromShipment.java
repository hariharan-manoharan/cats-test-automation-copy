package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class RemoveFromShipment extends AirtelRoutine {

    private final String routine = "Remove From Shipment";

    private boolean serialized = false;
    private boolean isInitial = true;

    public RemoveFromShipment (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String transferNumber, String partCode, String assetCode) {
        executeRoutine(location, transferNumber, partCode, assetCode, null, 0);
    }

    public void execute (String location, String transferNumber, String partCode, String lot, int qty) {
        executeRoutine(location, transferNumber, partCode, null, lot, qty);
    }

    public void executeRoutine (String location, String transferNumber, String barcode, String assetCode, String lot, int qty) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (qty == 0) {
            serialized = true;
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            location(true, location);
            nextField();
        }

        transferNumber(true, transferNumber);
        nextField();
        barcode(true, barcode, true);
        nextField();

        if (!serialized) {
            lotNumber(true, lot);
            nextField();
            qty(true, qty);
        } else if (serialized) {
            barcode2(false, assetCode);
        }

        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        if (serialized) {
            result = verifyRoutineDetail("TRANSFER #", 30, true);
        }
        serialized = false;
    }
}

