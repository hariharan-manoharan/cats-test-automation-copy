package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class AssemblyDetail extends AirtelRoutine {
    private final String routine = "Assembly Detail";

    private boolean serialized = false;
    private boolean isInitial = true;

    private String parentAssetCode;

    public AssemblyDetail (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String barcode, String barcode2, String childItem, String childSerial,
                         String childUID) {
        executeRoutine(location, barcode, barcode2, childItem, childSerial, childUID, 0);
    }

    public void execute (String location, String barcode, String barcode2, int qty) {
        executeRoutine(location, barcode, barcode2, null, null, null, qty);
    }

    public void executeRoutine (String location, String barcode, String barcode2, String childItem,
                                String childSerial, String childUID, int qty) {
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
            parentBarcode(true, barcode);
            nextField();
            barcode2(false, barcode2);
            nextField();
        }

        childItemCode(true, childItem);
        nextField();

        if (!serialized) {
            qty(true, qty);
        } else {
            childSerialNumber(true, childSerial);
            nextField();
            childUID(true, childUID);
        }

        nextField();
        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }


    public void routineComplete () {
        serialized = false;
    }

    private void qty(int qty) {
        routineDetail("Enter QTY*:", String.valueOf(qty));
    }

    private void markAsIncomplete () {
        verifyRoutineDetail("MARK AS INCOMPLETE?", true);
    }

    public boolean complete() {
        return validateValue("ASSEMBLY HAS BEEN FULLY DETAILED");
    }
}

