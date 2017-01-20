package net.fulcrum.Routines.Android.Verizon.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import java.util.ArrayList;
import java.util.Random;

public class VerizonTest extends MainTest{

    protected final String inactiveLocationDetailCode = "CNS114733";
    protected final String inactiveAsset = "054X01058643";

    //Error Messages
    protected final String invalidToLDC = "DEVICE: To LDC INVALID LDC is not a valid selection.";
    protected final String invalidFromLDC = "DEVICE: From LDC INVALID LDC is not a valid selection.";
    protected final String invalidLDC = "DEVICE: LDC INVALID LDC is not a valid selection.";
    protected final String invalidToBU = "DEVICE: To Location BU INVALID BU is not a valid selection.";
    protected final String invalidBU = "DEVICE: Location BU INVALID BU is not a valid selection.";
    protected final String invalidFromBU = "DEVICE: From Location BU INVALID BU is not a valid selection.";
    protected final String invalidPartCode = "INVALID PART CODE ENTERED.";
    protected final String invalidPOLine = "THE VALUE ENTERED MUST BE NUMERIC and formatted as ##.##.";
    protected final String invalidQuantity = "TRIGGER: PART TRANSACTION REQUIRES A QUANTITY.";
    protected final String invalidBarcode = "BARCODE VALUE ENTERED DOES NOT EXIST AS A VALID ASSET CODE OR PART CODE.";
    protected final String inactiveLDCMsg = "DEVICE: To LDC " + inactiveLocationDetailCode + " is not a valid selection.";
    protected final String inactiveFromLDCMsg = "DEVICE: From LDC " + inactiveLocationDetailCode + " is not a valid selection.";
    protected final String zeroQtyMsg = "DEVICE: Quantity is a required field. The value must be greater than 0.";
    protected final String overQtyMsg = "THE REQUESTED QUANTITY EXCEEDS THE PART DETAIL DATA QUANTITY AVAILABLE.";
    protected final String invalidPO = "DEVICE: PO # INVALIDPO is not a valid selection.";
    protected final String invalidProject = "DEVICE: PROJECT INVALIDPROJECT is not a valid selection.";

    //Part Codes
    protected String partCodeLN = "179-530090-0101";
    protected String partCodeSO = "LOC-VAS335055-0013";
    protected String partCodeSU = "120-110-1001";
    protected String partCodeSerialized = "EV106-H5-H5-100";
    protected String partCodeSerialized2 = "44WR8";

    //Variables
    protected String projectCode = "PROJECT-CTA-EXP";
    protected String lineNumber = "1.0";
    protected String poCode = "PO-CTA-EXP";
    protected String location;
    protected String multiBULDC = "%NVCLX";
    protected final String multiBULocationDescription = "CATS MULTI BU LOCATION";

    protected ArrayList<String> locationDetailCodes = new ArrayList<String>();

    protected int qty;

    private Random rand = new Random();

    public int getRandomNumber(int num) {
        return rand.nextInt(num);
    }

    public int getRandomNumber() {
        return rand.nextInt(1000);
    }

    public String createNewAssetLabel() {
        return "ASSET" + getRandomNumber(1000000);
    }

    public String createNewPOLine() {
        return getRandomNumber(100) + 1 + ".0";
    }

    public String createNewPOCode() {
        return "PO-CTA-" + getRandomNumber(10000);
    }

    public String createNewProjectCode() {
        return "PROJECT-CTA-" + getRandomNumber(10000);
    }

    public String invalidSelection(String field, String value) {
        return "DEVICE: " + field + " " + value + " is not a valid selection.";
    }

}
