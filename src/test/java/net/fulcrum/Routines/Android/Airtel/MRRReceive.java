package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class MRRReceive extends AirtelRoutine {

    private final String routine = "MRR Receive";

    private boolean serialized = false;
    private boolean isInitial = true;

    public MRRReceive (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String mrrNumber, String barcode, String choosePart, String serial,
                         String uid, String packageTag) {
        executeRoutine(location, mrrNumber, barcode, choosePart, serial, uid, packageTag, 0);
    }


    public void execute (String location, String mrrNumber, String barcode, String choosePart, int qty) {
        executeRoutine(location, mrrNumber, barcode, choosePart, null, null, null, qty);
    }

    public void executeRoutine (String location, String mrrNumber, String barcode, String choosePart, String serial,
                                String uid, String packageTag, int qty) {
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
            mrrNumber(true, mrrNumber);
            nextField();
        }

        barcode(true, barcode, true);
        nextField();
        choosePart(true, choosePart);
        nextField();
        clickNo();

        if (serialized) {
            serialNumber(serial);
            nextField();
            UID(false, uid);
            nextField();
            packageTag(true, packageTag);
        } else {
            qty(true, qty);
        }

        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        if (serialized) {
            result = verifyRoutineDetail("SERIAL #", 30, false);
        } else {
            result = verifyRoutineDetail("QTY", 30, true);
        }
    }
}

