package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Routines.Android.AndroidRoutine;


public class XORoutine extends AndroidRoutine {

    /**
     * Routine Details
     */

    public void job(boolean required, String job) {
        routineDetail("Job #", job, required);
    }

    public void fromLocation(boolean required, String location) {
        routineDetail("From Location", location, required);
    }

    public void fromStatus(boolean required, String status) {
        routineDetail("From Status", status, required);
    }

    public void status(boolean required, String status) {
        routineDetail("Status", status, required);
    }


    public void bin(boolean required, String bin) {
        routineDetail("Bin", bin, required);
    }

    public void toBin(boolean required, String bin) {
        routineDetail("To Bin", bin, required);
    }

    public void fromBin(boolean required, String bin) {
        routineDetail("From Bin", bin, required);
    }

    public void toStatus(boolean required, String status) {
        routineDetail("To Status", status, required);
    }

    public void itemMfgPart(boolean required, String part) {
        routineDetail("Item/Mfg. Part #", part, required);
    }

    public void barcodeType(boolean required, String type) {
        routineDetail("Barcode Type", type, required);
    }

    public void condition(boolean required, String condition) {
        routineDetail("Condition", condition, required);
    }

    public void toLocation(boolean required, String location) {
        routineDetail("To Location", location, required);
    }

    public void serial(boolean required, String serial) {
        routineDetail("Serial #", serial, required);
    }

    public void containerLocation(boolean required, String location) {
        routineDetail("Container Location", location, required);
    }

    public void containerStatus(boolean required, String status) {
        routineDetail("Container Status", status, required);
    }

    public void containerBin(boolean required, String bin) {
        routineDetail("Container Bin", bin, required);
    }


    public void qty(boolean required, int qty) {
        routineDetail("Qty", String.valueOf(qty), true);
    }

    public void assetCodeSerialNumber(boolean required, String item) {
        routineDetail("Asset Code / Serial #", item, required);
    }

    public void shipMethod(Boolean required, String method) {
        routineDetail("Ship Method", method, required);
    }

    public void receiveAssetCode(Boolean required, String assetCode) {
        routineDetail("Receive Asset Code", assetCode, required);
    }

    public void rmaNumber(boolean required) {
        verifyRoutineDetail("RMA #", required);
    }

    public void vehicle(boolean required, String vehicle) {
        routineDetail("Vehicle", vehicle, required);
    }

    public void remedy(boolean required, String remedy) {
        routineDetail("Remedy #", remedy, required);
    }

    public void xoItemMfgPart(boolean required, String item) {
        routineDetail("XO Item #/MFG Part #", item, required);
    }

    public void xoItem(boolean required, String item) {
        routineDetail("XO Item #", item, required);
    }

    public void tracking(boolean required, String tracking) {
        routineDetail("Tracking", tracking, required);
    }

    public void line(boolean required, String line) {
        routineDetail("Line #", line, required);
    }

    public void shipmentNumber(boolean required, String shipment) {
        routineDetail("Shipment #", shipment, required);
    }

    public void transferNumber(boolean required, String transfer) {
        routineDetail("Transfer #", transfer, required);
    }

    public void shipContainer(String container) {
        routineDetail("Ship Container", container, true);
    }

    public void container(String container) {
        routineDetail("Container", container, true);
    }

    public void generateContainer(String answer) {
        routineDetail("Generate Container?", answer, true);
    }


    public void newAssetCode(boolean required, String asset) {
        routineDetail("New Asset Code", asset, required);
    }

    public void auditType(boolean required, String type) {
        routineDetail("Audit Type", type, required);
    }

    public void clei(boolean required) {
        String newCLEI = "CLEI" + mobilityDriver.getRandomInt(10000);
        routineDetail("CLEI", newCLEI, required);
    }


}
