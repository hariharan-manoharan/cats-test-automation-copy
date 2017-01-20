package net.fulcrum.Routines.iOS.Verizon.Test;

import net.fulcrum.Routines.iOS.Verizon.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import net.fulcrum.Pages.iOS.Test.MainTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestRailListener.class)
public class RoutineTest extends MainTest {


    private String containerCode;
    private String toLDC;
    private String fromLDC;
    private String validationTitle;


    private ContainerInquiry containerInquiry;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        containerInquiry = new ContainerInquiry(mobilityDriver, oracleDriver);
        toLDC = "CNS236520";
        fromLDC = "SNS236520";
        containerCode = oracleDriver.createContainerAsset(toLDC, fromLDC);

    }

    //Test to verify DYNAMICVIEWSQL title
    @TestRailCase(automationId = "7042")
    @Test(priority = 100, enabled = true)
    public void VZWP3582() {
        element = iosDriver.findElement(By.name("Container Inquiry"));
        element.click();
        containerInquiry.containerLabel(true, containerCode);
        mobilityDriver.clickValidations();
        validationTitle = mobilityDriver.getValidationTitle().substring(0, 8).toUpperCase();
        Assert.assertEquals(validationTitle, "CONTENTS");
    }

}
