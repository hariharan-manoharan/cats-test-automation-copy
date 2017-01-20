package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

import java.util.*;

public class AssetInquiry extends AirtelRoutine {

    private HashMap<String, String> routineValues = new HashMap<String,String>();

    private final String routine = "Asset Inquiry";

    public AssetInquiry (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        notes = "AIRTEL CTA Android: " + routine;

    }

    public void executeRoutine (String siteID, String locationStatus, String lot, String assetCode, String partCode,
                         String serialNumber, String mfgPartNumber) {

        //Values to validate in the inquiry routine
        routineValues.put("BARCODE 2", assetCode);
        routineValues.put("UID", assetCode);
        routineValues.put("SERIAL NUMBER", serialNumber);
        routineValues.put("BARCODE", partCode);
        routineValues.put("MFG PART NUMBER", mfgPartNumber);
        routineValues.put("LOT #", lot);
        routineValues.put("SITE ID", siteID);
        routineValues.put("LOCATION STATUS", locationStatus);

        boolean key;
        boolean value;
        Iterator i;

        barcode(true, partCode, true);
        nextField();
        barcode2(false, assetCode);
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

