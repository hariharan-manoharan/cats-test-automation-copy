package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class PartInquiry extends AirtelRoutine {

    private HashMap<String, String> routineValues = new HashMap<String,String>();

    private final String routine = "Part Inquiry";

    public PartInquiry (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;

        notes = "AIRTEL CTA Android: " + routine;
    }

    private void reset() {
        mobilityDriver.clickByName("Part Code");
        isInitial = true;
    }

    public void executeRoutine (String partCode, String desc, String serialized, String manufacturer, String mfgPartNo,
                                String type) {
        boolean key;
        boolean value;
        Iterator i;

        routineValues.put("BARCODE", partCode);
        routineValues.put("ITEM CODE", partCode);
        routineValues.put("ITEM DESCRIPTION", desc);
        routineValues.put("SERIALIZED", serialized);
        routineValues.put("MANUFACTURER", manufacturer);
        routineValues.put("MFG PART NUMBER", mfgPartNo);
        routineValues.put("TYPE", type);

        barcode(true, partCode, true);
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

