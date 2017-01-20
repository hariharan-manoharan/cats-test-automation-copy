package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class LocationInquiry extends AirtelRoutine {

    private HashMap<String, String> routineValues = new HashMap<String,String>();

    private final String routine = "Location Inquiry";


    public LocationInquiry (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;

        notes = "AIRTEL CTA Android: " + routine;

    }

    private void reset() {
        mobilityDriver.clickByName("LOCATION");
        isInitial = true;
    }

    public void executeRoutine (String location, String name, String locatorCode, String address) {
        boolean key;
        boolean value;
        Iterator i;

        routineValues.put("LOCATION", location);
        routineValues.put("LOCATION NAME", name);
        routineValues.put("ORACLE LOCATOR CODE", locatorCode);
        routineValues.put("ADDRESS", address);

        location(true, location);
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
    }
}

