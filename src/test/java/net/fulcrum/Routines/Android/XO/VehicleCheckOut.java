package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;
import net.fulcrum.Routines.Android.XO.XORoutine;

public class VehicleCheckOut extends XORoutine {

    private final String routine = "Vehicle Check-out";
    private final String loopField = "Vehicle";

    public VehicleCheckOut(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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
        vehicle(true, vehicle);
        nextField();
        verifyLoopfield(true, loopField);
    }
}
