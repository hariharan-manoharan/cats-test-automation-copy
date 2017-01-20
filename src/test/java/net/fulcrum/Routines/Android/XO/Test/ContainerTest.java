package net.fulcrum.Routines.Android.XO.Test;

import net.fulcrum.Pages.Android.Test.MainTest;
import net.fulcrum.Routines.Android.XO.*;
import org.testng.Assert;
import org.testng.annotations.*;
import net.fulcrum.testrail.annotations.*;

public class ContainerTest extends MainTest {

    private AddToContainer addToContainer;
    private RemoveFromContainer removeFromContainer;
    private DestroyContainer destroyContainer;
    
    //Error Messages
    private final String invalidContainerCodeMsg = "DEVICE: Container Code INVALID is not a valid selection";
    private final String invalidContainerLocationMsg = "DEVICE: Container Location INVALID is not a valid selection";
    private final String invalidContainerStatusMsg = "DEVICE: Container Status INVALID is not a valid selection";
    private final String invalidNewContainerMsg = "DEVICE: New Container? Y/N INVALID is not a valid selection";
    private final String invalidDestroyContainerMsg = "DEVICE: Destroy Container? Y/N INVALID is not a valid selection";
    private final String invalidBarcodeMsg = "DEVICE: Barcode INVALID is not a valid selection";
    private final String invalidItemToRemoveMsg = "DEVICE: Item To Remove INVALID is not a valid selection";
    private final String invalidQuantityMsg = "DEVICE: Quantity -1 is not a valid selection";
    private final String invalidToLocationMsg = "DEVICE: To Location INVALID is not a valid selection";

    private final String partCodeNS = "00031484";
    private final String transferLocation = "PDX-WAREHOUSE";
    private String status = "CONFIGURATION";

    private int qty;
    private String assetCode = mobilityDriver.getNewAssetCode();

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        addToContainer = new AddToContainer(mobilityDriver, oracleDriver, routines);
        removeFromContainer = new RemoveFromContainer(mobilityDriver, oracleDriver, routines);
        destroyContainer = new DestroyContainer(mobilityDriver, oracleDriver, routines);
        qty = mobilityDriver.getRandomInt(100);
        assetCode = oracleDriver.createAsset("");
    }

    @TestRailCase(automationId = "900000")
    @Test(priority = 1, enabled = true)
    public void containerCodeRequired() {
        addToContainer.selectRoutine();
        addToContainer.testRequiredField("Container Code");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }
    
    @TestRailCase(automationId = "800002")
    @Test(priority = 2, enabled = true)
    public void containerCodeValid() {
        addToContainer.containerCode(true, containerCode);
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800003")
    @Test(priority = 4, enabled = true)
    public void newContainerRequired() {
        addToContainer.testRequiredField("New Container? Y/N");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800004")
    @Test(priority = 5, enabled = true)
    public void newContainerInvalid() {
        addToContainer.newContainer("INVALID");
        addToContainer.verifyMessage(invalidNewContainerMsg);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800005")
    @Test(priority = 6, enabled = true)
    public void newContainerValid() {
        addToContainer.newContainer("Y");
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800006")
    @Test(priority = 7, enabled = true)
    public void containerLocationRequired() {
        addToContainer.testRequiredField("Container Location");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800007")
    @Test(priority = 8, enabled = true)
    public void containerLocationInvalid() {
        addToContainer.toBin(true, "INVALID");
        addToContainer.verifyMessage(invalidContainerLocationMsg);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800008")
    @Test(priority = 9, enabled = true)
    public void containerLocationValid() {
        addToContainer.containerLocation(true, transferLocation);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800009")
    @Test(priority = 10, enabled = true)
    public void containerStatusRequired() {
        addToContainer.testRequiredField("Container Status");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800010")
    @Test(priority = 11, enabled = true)
    public void containerStatusInvalid() {
        addToContainer.itemMfgPart(true, "INVALID");
        addToContainer.verifyMessage(invalidContainerStatusMsg);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800011")
    @Test(priority = 12, enabled = true)
    public void containerStatusValid() {
        addToContainer.containerStatus(true, status);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800014")
    @Test(priority = 13, enabled = true)
    public void barcodeRequired() {
        addToContainer.selectRoutine();
        addToContainer.testRequiredField("Barcode");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }
    
    @TestRailCase(automationId = "800012")
    @Test(priority = 14, enabled = true)
    public void barcodeInvalid() {
        addToContainer.barcode(false, "INVALID");
        addToContainer.verifyMessage(invalidBarcodeMsg);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800013")
    @Test(priority = 15, enabled = true)
    public void barcodeValid() {
        addToContainer.barcode(false, assetCode);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800014")
    @Test(priority = 15, enabled = true)
    public void emptyNotes() {
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }
    
    @TestRailCase(automationId = "800019")
    @Test(priority = 16, enabled = true)
    public void verifyLoopfield() {
        addToContainer.verifyLoopfield(true, "Barcode");
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800020")
    @Test(priority = 21, enabled = true)
    public void barcodeNonserialized() {
        addToContainer.barcode(true, partCodeNS);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800014")
    @Test(priority = 15, enabled = true)
    public void qtyRequired() {
        addToContainer.testRequiredField("Qty");
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800016")
    @Test(priority = 16, enabled = true)
    public void qtyInvalid() {
        addToContainer.qty(true, -1);
        addToContainer.verifyMessage(invalidQuantityMsg);
        addToContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800017")
    @Test(priority = 17, enabled = true)
    public void qtyValid() {
        addToContainer.qty(true, 10);
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }
    
    @TestRailCase(automationId = "800026")
    @Test(priority = 26, enabled = true)
    public void enterNotes() {
        addToContainer.notes(true, "CTA: Stock Equipment");
        addToContainer.nextField();
        Assert.assertEquals(addToContainer.getResult(), true);
    }
    
    @TestRailCase(automationId = "900030")
    @Test(priority = 31, enabled = true)
    public void containerCodeRequiredRemove() {
        removeFromContainer.reset();
        removeFromContainer.testRequiredField("Container Code");
        removeFromContainer.clickOk();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }


    @TestRailCase(automationId = "800031")
    @Test(priority = 32, enabled = true)
    public void containerCodeInvalidRemove() {
        removeFromContainer.containerCode(true, "INVALID");
        removeFromContainer.nextField();
        removeFromContainer.verifyMessage(invalidContainerCodeMsg);
        removeFromContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }
    
    @TestRailCase(automationId = "800032")
    @Test(priority = 33, enabled = true)
    public void containerCodeValidRemove() {
        removeFromContainer.containerCode(true, containerCode);
        removeFromContainer.nextField();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800034")
    @Test(priority = 34, enabled = true)
    public void toLocationRequiredRemove() {
        removeFromContainer.testRequiredField("Container Location");
        removeFromContainer.clickOk();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800035")
    @Test(priority = 35, enabled = true)
    public void toLocationInvalidRemove() {
        removeFromContainer.toLocation(true, "INVALID");
        removeFromContainer.verifyMessage(invalidToLocationMsg);
        removeFromContainer.clickOk();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800036")
    @Test(priority = 36, enabled = true)
    public void toLocationValidRemove() {
        removeFromContainer.toLocation(true, transferLocation);
        removeFromContainer.nextField();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800037")
    @Test(priority = 37, enabled = true)
    public void itemToRemoveRequired() {
        removeFromContainer.selectRoutine();
        removeFromContainer.testRequiredField("Item To Remove");
        removeFromContainer.clickOk();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800038")
    @Test(priority = 38, enabled = true)
    public void itemToRemoveInvalid() {
        removeFromContainer.itemToRemove("INVALID");
        removeFromContainer.nextField();
        removeFromContainer.verifyMessage(invalidItemToRemoveMsg);
        removeFromContainer.clickOk();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800039")
    @Test(priority = 39, enabled = true)
    public void itemToRemoveValid() {
        removeFromContainer.itemToRemove(assetCode);
        removeFromContainer.nextField();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800040")
    @Test(priority = 40, enabled = true)
    public void emptyNotesRemove() {
        removeFromContainer.nextField();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800041")
    @Test(priority = 41, enabled = true)
    public void verifyLoopfieldRemove() {
        removeFromContainer.verifyLoopfield(true, "Item To Remove");
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800042")
    @Test(priority = 42, enabled = true)
    public void itemToRemoveNonserializedRemove() {
        removeFromContainer.itemToRemove(partCodeNS);
        removeFromContainer.nextField();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800043")
    @Test(priority = 43, enabled = true)
    public void qtyRequiredRemove() {
        removeFromContainer.testRequiredField("Qty");
        removeFromContainer.clickOk();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800044")
    @Test(priority = 44, enabled = true)
    public void qtyInvalidRemove() {
        removeFromContainer.qty(true, -1);
        removeFromContainer.verifyMessage(invalidQuantityMsg);
        removeFromContainer.clickOk();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800045")
    @Test(priority = 45, enabled = true)
    public void qtyValidRemove() {
        removeFromContainer.qty(true, 10);
        removeFromContainer.nextField();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800046")
    @Test(priority = 46, enabled = true)
    public void enterNotesRemove() {
        removeFromContainer.notes(true, "CTA: Remove From Container");
        removeFromContainer.nextField();
        Assert.assertEquals(removeFromContainer.getResult(), true);
    }

    @TestRailCase(automationId = "900050")
    @Test(priority = 51, enabled = true)
    public void containerCodeRequiredDestroy() {
        destroyContainer.reset();
        destroyContainer.testRequiredField("Container Code");
        destroyContainer.clickOk();
        Assert.assertEquals(destroyContainer.getResult(), true);
    }


    @TestRailCase(automationId = "800051")
    @Test(priority = 52, enabled = true)
    public void containerCodeInvalidDestroy() {
        destroyContainer.containerCode(true, "INVALID");
        destroyContainer.nextField();
        destroyContainer.verifyMessage(invalidContainerCodeMsg);
        destroyContainer.clickOk();
        Assert.assertEquals(addToContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800052")
    @Test(priority = 53, enabled = true)
    public void containerCodeValidDestroy() {
        destroyContainer.containerCode(true, containerCode);
        destroyContainer.nextField();
        Assert.assertEquals(destroyContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800053")
    @Test(priority = 54, enabled = true)
    public void destroyContainerRequired() {
        destroyContainer.testRequiredField("Destroy Container? Y/N");
        destroyContainer.clickOk();
        Assert.assertEquals(destroyContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800054")
    @Test(priority = 55, enabled = true)
    public void destroyContainerInvalid() {
        destroyContainer.destroyContainer("INVALID");
        destroyContainer.verifyMessage(invalidNewContainerMsg);
        destroyContainer.clickOk();
        Assert.assertEquals(destroyContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800055")
    @Test(priority = 56, enabled = true)
    public void destroyContainerValid() {
        destroyContainer.destroyContainer("Y");
        destroyContainer.nextField();
        Assert.assertEquals(destroyContainer.getResult(), true);
    }

    @TestRailCase(automationId = "800056")
    @Test(priority = 57, enabled = true)
    public void enterNotesDestroy() {
        destroyContainer.notes(true, "CTA: Destroy Container");
        destroyContainer.nextField();
        Assert.assertEquals(destroyContainer.getResult(), true);
    }

}
