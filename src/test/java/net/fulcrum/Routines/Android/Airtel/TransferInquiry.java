package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class TransferInquiry extends AirtelRoutine {

    private HashMap<String, String> routineValues = new HashMap<String,String>();
    private HashMap<String, String> transferLines = new HashMap<String, String>();

    private final String routine = "Transfer Inquiry";

    public TransferInquiry (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;

        notes = "AIRTEL CTA Android: " + routine;
    }

    private void reset() {
        mobilityDriver.clickByName("Transfer #");
        isInitial = true;
    }

    public void executeRoutine (String transferNumber, String type, String fromLocation, String toLocation,
                                String toStatus, String transferLinePart) {
        boolean key;
        boolean value;
        Iterator i;

        routineValues.put("TRANSFER #", transferNumber);
        routineValues.put("TYPE", type);
        routineValues.put("FROM LOCATION", fromLocation);
        routineValues.put("TO LOCATION", toLocation);
        routineValues.put("TO STATUS", toStatus);
        routineValues.put("TRANSFER LINES",transferNumber);
        routineValues.put("TYPE", type);

        transferLines.put("ITEMCODE", transferLinePart);

        transferNumber(true, transferNumber);
        nextField();
        i = routineValues.entrySet().iterator();

        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry) i.next();
            key = validateValue(String.valueOf(pair.getKey()));
            value = validateValue(String.valueOf(pair.getValue()));
            if (!key || !value) {
                result = false;
            }
        }

        //Check transfer line contents
        clickValidationFile();
        swipeRight();
        i = transferLines.entrySet().iterator();

        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry) i.next();
            value = verifyValidationFileContents(String.valueOf(pair.getValue()));
            if (!value) {
                result = false;
            }
        }
    }
}

