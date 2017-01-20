/**
 * AdvancedRepairToRepair.java
 *
 * Java Class Object to perform a ToRepair for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;
import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;

public class AdvancedRepairToRepair extends VerizonRoutine {

    private final String routine = "Advanced Repair To Repair";

    private String toLDC = "CNS103383";
    private String vendorRA = "# T84TAA02GU";
	private String catsRA = "RA00242942";
	private String RALine = "1";
	private String failAssetCode = "";
	private String failReasonCode = "24H";
	private String fromLDC = "";

    private boolean isAsset;

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public AdvancedRepairToRepair(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void executeRoutine ( String partcode) {
        //check if part is serialized or non serialized
        isAsset = oracleDriver.isAsset(partcode);

		if (isAsset) {
            failAssetCode= oracleDriver.generateAssetCode();
            oracleDriver.expressStock(001,"CNS236520", partcode, failAssetCode,10 ,"Project1", "PO-23132",1);
        } else {
            oracleDriver.expressStock(001,"CNS236520", partcode, null, 10 , "Project1", "PO-23132",1);
        }
	    if (isInitial) {
            toLDC(true, toLDC);
            nextField();
            nextField();
            vendorRA(true, vendorRA);
            nextField();
            catsRA(false, catsRA);
            nextField();
            RALine(false, RALine);
            nextField();
            nextField();
        }
        if (isAsset) {
            failAssetCode(true, failAssetCode);
        } else {
            nextField();
            fromLDC(true, fromLDC);
            nextField();
        }
        nextField();
        nextField();
        nextField();
        failReasonCode(true, failReasonCode);
        nextField();
        nextField();
        notes(false, routine);
        nextField();
    }
}
