package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Pages.Android.*;

import java.util.*;

public class OpenContainer extends AirtelRoutine {

    private final String routine = "Open Container";

    private boolean serialized = false;
    private boolean isInitial = true;
    protected String lot = "LOT1";

    public OpenContainer (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
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

    public void execute (String location, String container) {
        executeRoutine(location, container);
    }

    public void executeRoutine (String location, String container) {
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            location(true, location);
            nextField();
        }

        container(true, container);
        nextField();
        notes(false, notes, true);
        nextField();
        routineComplete();
    }

    public void routineComplete () {
        if (serialized) {
            result = verifyRoutineDetail("CONTAINER", 30, true);
        }
        serialized = false;
    }
}

