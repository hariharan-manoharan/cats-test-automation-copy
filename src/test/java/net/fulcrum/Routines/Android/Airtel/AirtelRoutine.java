package net.fulcrum.Routines.Android.Airtel;

import net.fulcrum.Routines.Android.AndroidRoutine;

public class AirtelRoutine extends AndroidRoutine{

    /**
     * Routine Details
     */


    public void partCode(boolean required, String partCode, boolean airtel) {
        routineDetail("PART CODE", partCode, required);
    }

    public void assetCode(boolean required, String partCode, boolean airtel) {
        routineDetail("ASSET CODE", partCode, required);
    }

    public void barcode(boolean required, String barcode, boolean airtel) {
        routineDetail("BARCODE", barcode, required);
    }

    public void notes(boolean required, String notes, boolean airtel) {
        routineDetail("NOTES", notes, required);
    }

    public void packageTag(boolean required, String tag) {
        routineDetail("PACKAGE TAG", tag, required);
    }

    public void containerCode(boolean required, String containerCode, boolean airtel) {
        routineDetail("CONTAINER CODE", containerCode, required);
    }

    public void container(boolean required, String container) {
        routineDetail("CONTAINER", container, required);
    }

    public void qty(boolean required, int qty) {
        routineDetail("QTY", String.valueOf(qty), true);
    }

    public void lotNumber(boolean required, String lotNumber) {
        routineDetail("LOT NUMBER", lotNumber, required);
    }

    public void newSerialNumber(boolean required, String newSerialNumber) {
        routineDetail("NEW SERIAL NUMBER", newSerialNumber, required);
    }

    public void faid(boolean required, String faid) {
        routineDetail("FAID", faid, required);
    }

    public void toStatus(boolean required, String status) {
        routineDetail("TO STATUS", status, required);
    }

    public void parentAssemblyUID(boolean required, String uid) {
        routineDetail("PARENT ASSEMBLY UID", uid, required);
    }

    public void parentAssetCode(boolean required, String parentAssetCode) {
        routineDetail("PARENT ASSET CODE", parentAssetCode, required);
    }

    public void childPartCode(boolean required, String parentAssetCode) {
        routineDetail("CHILD PART CODE", parentAssetCode, required);
    }

    public void fromLocation(boolean required, String location) {
        routineDetail("FROM LOCATION", location, required);
    }

    public void toLocation(boolean required, String location) {
        routineDetail("TO LOCATION", location, required);
    }

    public void serialNumber(boolean required, String serialNumber) {
        routineDetail("SERIAL NUMBER", serialNumber, required);
    }

    public void UID(boolean required, String serialNumber) {
        routineDetail("UID", serialNumber, required);
    }

    public void barcode2(boolean required, String serialNumber) {
        routineDetail("BARCODE 2", serialNumber, required);
    }

    public void choosePart(boolean required, String part) {
        routineDetail("CHOOSE PART", part, required);
    }

    public void toStatus(boolean required, String status, boolean airtel) {
        routineDetail("TO STATUS", status, required);
    }

    public void fromStatus(boolean required, String status, boolean airtel) {
        routineDetail("FROM STATUS", status, required);
    }

    public void transferNumber(boolean required, String transfer) {
        routineDetail("TRANSFER #", transfer, required);
    }

    public void rmaNumber(boolean required, String rma) {
        routineDetail("RMA #", rma, required);
    }

    public void serialNumber(String serial) {
        routineDetail("SERIAL #", serial, false);
    }

    public void jobNumber(boolean required, String job) {
        routineDetail("JOB #", job, required);
    }

    public void failBarcode(boolean required, String barcode) {
        routineDetail("FAIL BARCODE", barcode, required);
    }

    public void mrrNumber(boolean required, String barcode) {
        routineDetail("MRR #", barcode, required);
    }

    public void childItemCode(boolean required, String itemCode) {
        routineDetail("CHILD ITEM CODE", itemCode, required);
    }

    public void childSerialNumber(boolean required, String serial) {
        routineDetail("CHILD SERIAL NUMBER", serial, required);
    }

    public void childUID(boolean required, String uid) {
        routineDetail("CHILD UID", uid, required);
    }

    public void childPackageTag(boolean required, String packageTag) {
        routineDetail("CHILD PACKAGE TAG", packageTag, required);
    }

    public void shipmentNumber(boolean required, String shipment) {
        routineDetail("SHIPMENT #", shipment, required);
    }

    public void failBarcode2(boolean required, String shipment) {
        routineDetail("FAIL BARCODE 2", shipment, required);
    }

    public void parentBarcode(boolean required, String barcode) {
        routineDetail("PARENT BARCODE", barcode, required);
    }

    public void childComponentBarcode(boolean required, String barcode) {
        routineDetail("CHILD COMPONENT BARCODE", barcode, required);
    }

    public void locationDetailCode(boolean required, String ldc) {
        routineDetail("LOCATION DETAIL CODE", ldc, true);
    }

    public void location(boolean required, String toLDC) {
        routineDetail("LOCATION", toLDC, required);
    }

    public void lot(boolean required, String toLDC) {
        routineDetail("LOT #", toLDC, required);
    }

    public void serial(boolean required, String serial) {
        routineDetail("SERIAL #", serial, required);
    }
    
    // test
    
    public void Location(boolean required, String location) {
        routineDetail("Location", location, required);
    }
    
    public void FromLocation(boolean required, String location) {
        routineDetail("From Location", location, required);
    }
    
    public void transferRequest(boolean required, String transferRequest) {
        routineDetail("Transfer Request", transferRequest, required);
    }
    
    public void lineItem(boolean required, String barcode) {
        routineDetail("Line/Item #", barcode, required);
    }
    
    public void Lot(boolean required, String lot) {
        routineDetail("Lot #", lot, required);
    }
    
    public void quantity(boolean required, String qty) {
        routineDetail("Quantity", qty, required);
    }    
    
    public void notes(boolean required, String notes) {
        routineDetail("Notes", notes, required);
    }
    
     
    public void shipment(boolean required, String Shipment) {
        routineDetail("Shipment", Shipment, required);
    }
    
    public void shipmentMethod(boolean required, String shipmentMethod) {
        routineDetail("Shipment Method", shipmentMethod, required);
    }
    
    public void trackingNumber(boolean required, String trackingNumber) {
        routineDetail("Tracking Number", trackingNumber, required);
    }
    
    public void receivingLocation(boolean required, String receivingLocation) {
        routineDetail("Receiving Location", receivingLocation, required);
    }
    
    public void healthCheckup(boolean required,String healthCheckup) {
        routineDetail("Health Checkup (OCI)", healthCheckup, required);
    }
    
    public void ToStatus(boolean required,String toStatus) {
        routineDetail("To Status", toStatus, required);
    }
    
    public void ShipmentNumber(boolean required, String label, String shipment) {
        routineDetail(label, shipment, required);
    }
    

    
     
}
