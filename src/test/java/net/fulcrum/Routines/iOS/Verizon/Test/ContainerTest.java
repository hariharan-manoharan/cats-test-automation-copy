package net.fulcrum.Routines.iOS.Verizon.Test;

import net.fulcrum.Routines.iOS.Verizon.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.iOS.Test.MainTest;

import org.openqa.selenium.By;
import org.testng.annotations.*;

@Listeners(TestRailListener.class)
public class ContainerTest extends MainTest {


    private String assetCode;
    private String assetCode2;
    private String containerCode;
    private String containerCode2;
    private String partCode;
    private String toLDC;
    private String fromLDC;

    private AddToContainer addToContainer;
    private CaseBreak caseBreak;
    private DestroyContainer destroyContainer;
    private ContainerInquiry containerInquiry;

    @BeforeClass(alwaysRun = true)
    public void StageData() {


        addToContainer = new AddToContainer(mobilityDriver, oracleDriver);
        caseBreak = new CaseBreak(mobilityDriver, oracleDriver);
        destroyContainer = new DestroyContainer(mobilityDriver, oracleDriver);
        containerInquiry = new ContainerInquiry(mobilityDriver, oracleDriver);

        containerCode = oracleDriver.generateContainerCode();
        assetCode = oracleDriver.createAsset("CNS236520");
        assetCode2 = oracleDriver.createAsset("CNS236520");
        partCode = "107893304";
        oracleDriver.createPart("CNS236520", 10);
        containerCode2 = oracleDriver.createContainerAsset("CNS236520", "SNS236520");

        fromLDC = "CNS236520";
        toLDC = "SNS236520";

    }


    //Add Asset to Container
    @TestRailCase(automationId = "7015")
    @Test(priority = 100, enabled = true)
    public void addToContainer001 () {
        element = iosDriver.findElement(By.name("Add To Container"));
        element.click();
        addToContainer.execute(toLDC, fromLDC, containerCode, assetCode);
        // Assert.assertEquals(result.GetSuccess(), true);
    }

    //Add Asset to Container
    @Test(priority = 101, enabled = true)
    public void addToContainer002 () {
        addToContainer.execute(toLDC, fromLDC, containerCode, assetCode2);
        // Assert.assertEquals(result.GetSuccess(), true);
    }

    //Add Part to Container
    @TestRailCase(automationId = "7016")
    @Test(priority = 102, enabled = true)
    public void addToContainer003 () {
        addToContainer.execute(toLDC, fromLDC, containerCode, partCode, 10);
        // Assert.assertEquals(result.GetSuccess(), true);

    }



    //Add Container with an Asset to another Container
    @TestRailCase(automationId = "7017")
    @Test(priority = 103, enabled = true)
    public void addToContainer004 () {
        addToContainer.execute(toLDC, fromLDC, containerCode, containerCode2);
        // Assert.assertEquals(result.GetSuccess(), true);

    }

    //Container Inquiry
    @TestRailCase(automationId = "7042")
    @Test(priority = 104, enabled = true)
    public void containerInquiry001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Container Inquiry"));
        element.click();
    }


    //Case Break an Asset from Container
    @TestRailCase(automationId = "7018")
    @Test(priority = 105, enabled = true)
    public void caseBreak0001 () {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Case Break"));
        element.click();
        caseBreak.executeRoutine(toLDC, containerCode, assetCode, 1);
    }

    //Case Break a Part from Container
    @TestRailCase(automationId = "7019")
    @Test(priority = 106, enabled = true)
    public void caseBreak0002()
    {
        caseBreak.executeRoutine(toLDC, containerCode, partCode, 10);
        //Assert.assertEquals(result.GetSuccess(),true);
    }

    //Case Break a Container from Container
    @TestRailCase(automationId = "7020")
    @Test(priority = 107, enabled = true)
    public void caseBreak0003()
    {
        caseBreak.executeRoutine(toLDC, containerCode, containerCode2, 1);
        //Assert.assertEquals(result.GetSuccess(),true);
    }

    //Destroy Container
    @TestRailCase(automationId = "7021")
    @Test(priority = 108, enabled = true)
    public void destroyContainer001() {
        mobilityDriver.navigateToMainMenu();
        element = iosDriver.findElement(By.name("Destroy Container"));
        element.click();
        destroyContainer.executeRoutine(containerCode, assetCode2);
        // Assert.assertEquals(result.GetSuccess(), true);
    }
}
