package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Routines.Android.AndroidRoutine;

public class VerizonRoutine extends AndroidRoutine {


    /**
     * Routine Details
     */


    public void toLDCLookup(boolean required, String toLDC) {
        routineDetailLookup("To LDC", toLDC, required);
    }

    public void toLocationBULookup(boolean required, String toLocationBU) {
        routineDetailLookup("To Location BU", toLocationBU, required);
    }

    public void partCodeLookup(boolean required, String partCode) {
        routineDetailLookup("Part Code", partCode, required);
    }

    public void containerLabelLookup(boolean required, String container) {
        routineDetailLookup("Container Label", container, required);
    }

    public void quarantineLabel(boolean required, String quarantineLabel) {
        routineDetail("Quarantine Label", quarantineLabel, required);
    }

    public void quarantineReason(boolean required, String quarantineReason) {
        routineDetail("Quarantine Reason", quarantineReason, required);
    }

    public void retirementBatch(boolean required, String retirementBatch){
        routineDetail("Retirement Batch", retirementBatch, required);
    }

    public void materialsRequest(boolean required, String mrCode) {
        routineDetail("Materials Request #", mrCode, required);
    }

    public void generatePickList(boolean required, String answer) {
        routineDetail("Generate Pick List?", answer, required);
    }

    public void quarantinePartCode(Boolean required, String value) {
        routineDetail("Quarantine Part Code", value, required);
    }

    public void quarantineQuantity(Boolean required, String value) {
        routineDetail("Quarantine Quantity", value, required);
    }

    public void carrier(boolean required, String carrier) {
        routineDetail("Carrier/Consignee", carrier, required);
    }

    public void failLDC(boolean required, String failLDC) {
        routineDetail("FAIL LDC", failLDC, required);
    }

    public void failTechnician(boolean required, String failTechnician) {
        routineDetail("Fail Technician", failTechnician, required);
    }

    public void generatePackingSlip(boolean required, String yesNo) {
        routineDetail("Generate Package Slip? Y/N", yesNo, required);
    }

    public void confirmBU(boolean required, String result) {
        routineDetail("Confirm BU ", result, required);
    }

    public void projectCode(boolean required, String projectCode) {
        routineDetail("Project", projectCode, required);
    }

    public void newAssetCode(boolean required, String newAssetCode) {
        routineDetail("New Asset Code ", newAssetCode, required);
    }

    public void fromResultLocation(String location) {
        routineDetail("From Reuse Project Location", location, true);
    }

    public void confirmPartCodeChange(String answer) {
        routineDetail("Confirm Part Code Change Y/N", answer, true);
    }


    public void ellipsis(boolean required, String item) {
        routineDetail("Tap ellipsis to view contents", item, required);
    }

    public void results(boolean required, String results) {
        routineDetail("Results", results, required);
    }

    public void verifyTransaction(boolean batched, String trxType) {
        oracleDriver.verifyCustomTransaction(batched, trxType);
    }

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }


}
