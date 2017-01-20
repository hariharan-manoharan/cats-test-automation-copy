package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class ContainerInquiry extends AirtelRoutine {

    private HashMap<String, String> routineValues = new HashMap<String,String>();
    private HashMap<String, String> containerContents = new HashMap<String, String>();

    private final String routine = "Container Inquiry";


    public ContainerInquiry (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        notes = "AIRTEL CTA Android: " + routine;
    }

    private void reset() {
        mobilityDriver.clickByName("CONTAINER CODE");
        isInitial = true;
    }

    public void executeRoutine (String container, String siteID, String openClosed, String status,
                                String assetCode, String partCode) {
        boolean key;
        boolean value;
        Iterator i;

        routineValues.put("CONTAINER CODE", container);
        routineValues.put("SITE ID", siteID);
        routineValues.put("STATUS", status);
        routineValues.put("OPEN/CLOSED", openClosed);

        containerContents.put("Asset", assetCode);
        containerContents.put("Part", partCode);

        containerCode(true, container, true);
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

        //Check container contents
        clickValidationFile();
        swipeRight();
        i = containerContents.entrySet().iterator();

        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry) i.next();
            value = verifyValidationFileContents(String.valueOf(pair.getValue()));
            if (!value) {
                result = false;
            }
        }
    }
}

