package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

import java.util.*;

public class AssetInquiry extends XORoutine {

    private HashMap<String, String> routineValues = new HashMap<String,String>();

    private final String routine = "Asset Inquiry";

    public AssetInquiry (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
        notes = "CTA: " + routine;
    }

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public void executeRoutine (String assetCode, String owner, String condition, String modifiedContact,
                                String serial, String mfgPartNumber) {

        //Values to validate in the inquiry routine
        routineValues.put("Owner", owner);
        routineValues.put("Condition", condition);
        routineValues.put("Last Modified By", modifiedContact);
        routineValues.put("Serial #", serial);
        routineValues.put("XO Item #", mfgPartNumber);
        routineValues.put("MFG Part Number", mfgPartNumber);

        boolean key;
        boolean value;
        Iterator i;

        assetCode(false, assetCode);
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

