package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class Stock extends AirtelRoutine {

    private final String routine = "Stock";

    private String lot = "LOT1";
    private String packageTag = "TAG1";
    private boolean serialized = false;

    private boolean isInitial = true;

    public Stock (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        notes = "AIRTEL CTA Android: " + routine;
    }

    public void reset() {
        mobilityDriver.clickByName("CONTAINERIZED");
    }

    public void setInitial() {
        isInitial = true;
    }

    public void execute (String location, String container, String partCode, String mfgPart, String serialNumber, String assetCode,
                         String packageTag) {
        executeRoutine(location, container, partCode, mfgPart, serialNumber, assetCode, packageTag, 0);
    }

    public void execute (String location, String container, String partCode, String mfgPart, int qty) {
        executeRoutine(location, container, partCode, mfgPart, null, null, null, qty);
    }

    public void executeRoutine (String location, String container, String partCode, String mfgPart, String serialNumber,
                                String assetCode, String packageTag, int qty) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (qty == 0) {
            serialized = true;
        } else {
            serialized = false;
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            location(true, location);
            nextField();
            lot(true, lot);
            nextField();
            containerized(container);
        }

        barcode(true, partCode, true);
        nextField();
        choosePart(true, mfgPart);
        nextField();

        if (serialized) {
            serial(false, serialNumber);
            nextField();
            UID(false, assetCode);
            nextField();
            packageTag(true, packageTag);
            nextField();
        } else {
            qty(true, qty);
            nextField();
        }

        notes(false, notes, true);
        nextField();
        routineComplete(serialized);
    }

    public void containerized(String container) {
        if (container != null) {
            mobilityDriver.confirmYes();
            containerCode(true, container, true);
            nextField();
        } else {
            mobilityDriver.confirmNo();
        }
    }

    public void routineComplete (boolean serialized) {
        if (serialized) {
            result = verifyRoutineDetail("SERIAL #", 30, false);
        } else {
            result = verifyRoutineDetail("BARCODE", 30, true);
        }
    }

}

