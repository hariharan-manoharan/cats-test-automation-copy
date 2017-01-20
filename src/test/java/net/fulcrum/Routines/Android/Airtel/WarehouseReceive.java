package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class WarehouseReceive extends AirtelRoutine {

    private final String routine = "Warehouse Receive";

    private boolean serialized = false;
    private boolean isInitial = true;

    public WarehouseReceive (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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
        executeRoutine(location, transfer, shipment, barcode, assetCode, 0);
    }

    public void execute (String location, String transfer, String shipment, String barcode, int qty) {
        executeRoutine(location, transfer, shipment, barcode, null, qty);
    }

    public void executeRoutine (String location, String transfer, String shipment, String barcode, String assetCode,
                                int qty) {
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
            transferNumber(transfer);
            nextField();
            shipmentNumber(true, shipment);
            nextField();
            nextField();
        }

        barcode(true, barcode, true);
        nextField();

        if (serialized) {
            barcode2(false, assetCode);
        } else {
            qty(qty);
        }
        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("BARCODE", 30, true);
    }

    public void transferNumber(String transfer) {
        routineDetail("TRANSFER NUMBER", transfer, true);
    }

    public void qty(int qty) {
        routineDetail("QTY*", String.valueOf(qty));
    }
}

