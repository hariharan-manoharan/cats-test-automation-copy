package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Menu;
import net.fulcrum.Pages.Android.Routines;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class AssetUpdate extends AirtelRoutine {

    private final String routine = "Asset Update";

    private String lot = "LOT1";
    private int num;
    private boolean isInitial = true;

    private Random rand;

    public AssetUpdate (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        rand = new Random();
        notes = "AIRTEL CTA Android: " + routine;
    }

    public void reset() {
        mobilityDriver.clickByName("CONTAINERIZED");
    }

    public void setInitial() {
        isInitial = true;
    }

    public void select(Menu menu, Routines routines) {
        selectRoutine(menu, routines, routine);
    }

    public void execute (String partCode, String assetCode) {
        executeRoutine(partCode, assetCode);
    }

    public void executeRoutine (String partCode, String assetCode) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        num = rand.nextInt(100000) + 1;
        barcode(true, partCode, true);
        nextField();
        barcode2(false, assetCode);
        nextField();
        newSerialNumber(false, "SERIAL" + num);
        nextField();
        faid(false, String.valueOf(num));
        nextField();
        lotNumber(false, lot);
        nextField();
        packageTag(false, "");
        nextField();
       // swipeDown(1);
        notes(false, notes, true);
        nextField();
        routineComplete();
    }


    public void routineComplete() {
        result = verifyRoutineDetail("BARCODE", 60, true);
    }

}

