package net.fulcrum.Routines.iOS.TMobile;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class OpenContainer extends IOSRoutine {

    private final String routine = "Open Container";

    private boolean isInitial = true;

    public OpenContainer(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String container) {
        executeRoutine(container);
    }

    public void executeRoutine (String container) {
        last = true;
        containerCode(true, container);
        nextField();
        clickYes();
        nextField();
    }

}
