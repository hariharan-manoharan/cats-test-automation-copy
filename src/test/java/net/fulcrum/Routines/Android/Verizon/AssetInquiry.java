/**
 * AssetInquiry.java
 *
 * Java Class Object to perform an Asset Inquiry for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;


public class AssetInquiry extends VerizonRoutine {

    public AssetInquiry (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void execute (String assetCode) {
        executeRoutine(assetCode);
    }

    public void executeRoutine (String assetCode) {
        assetCode(false, assetCode);
        nextField();
        nextField();
        nextField();
        nextField();
        nextField();
        nextField();
        results(false, assetCode);
        nextField();
    }
}
