package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class VehicleCheckIn extends XORoutine {

    private final String routine = "Vehicle Check-in";
    private final String loopField = "Vehicle";

    public VehicleCheckIn(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public void execute (String vehicle) {
        executeRoutine(vehicle);
    }

    public void executeRoutine (String vehicle) {
        last = true;
        vehicle(false, vehicle);
        nextField();
        verifyLoopfield(false, loopField);
    }
}
