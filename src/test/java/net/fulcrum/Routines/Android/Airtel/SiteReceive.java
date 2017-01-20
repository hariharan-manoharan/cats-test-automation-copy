package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class SiteReceive extends AirtelRoutine {

    private final String routine = "Site Receive";

    private boolean serialized = false;
    private boolean isInitial = true;

    public SiteReceive (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String transfer, String shipment, String barcode, String assetCode) {
        executeRoutine(location, transfer, shipment, barcode, assetCode, null, 0);
    }

    public void execute (String location, String transfer, String shipment, String barcode, String lot, int qty) {
        executeRoutine(location, transfer, shipment, barcode, null, lot, qty);
    }

    public void executeRoutine (String location, String transfer, String shipment, String barcode, String barcode2,
                                String lot, int qty) {
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
            receiveLocation(location);
            nextField();
            transferNumber(true, transfer);
            nextField();
            shipmentNumber(true, shipment);
            nextField();
            nextField();
        }

        barcode(true, barcode, true);
        nextField();

        if (serialized) {
            barcode2(false, barcode2);
        } else {
            lotNumber(true, lot);
            nextField();
            qty(true, qty);
        }

        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("BARCODE", 30, true);
    }

    public void receiveLocation(String location) {
        routineDetail("RECEIVE LOCATION", location, true);
    }

}

