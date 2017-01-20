package net.fulcrum.Routines.iOS.TimeWarnerCable;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class SerialInquiry extends IOSRoutine {

    private final String routine = "Serial Inquiry";

    private boolean isInitial = true;

    public SerialInquiry(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String serial) {
        executeRoutine(serial);
    }

    public void executeRoutine (String serial) {

        last = true;

        serialNumber(true, serial);
        nextField();

    }

}
