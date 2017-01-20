package net.fulcrum.Pages.Web.Requisitions;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RequisitionsAddInstallationLocationPage{

    private WebDriver driver;

    @FindBy(id="locationSearchText")
    protected WebElement searchTextField;

    @FindBy(xpath="//form[@id='installLocationSearchForm']/div/span/button")
    protected WebElement searchButton;

    @FindBy(xpath="xpath=(//a[contains(text(),'Close')])[2]")
    protected WebElement closeButton;

    @FindBy(id="Next")
    protected WebElement nextButton;

    @FindBy(id="Previous")
    protected WebElement previousButton;

    @FindBy(name="DataTables_Table_0_length")
    protected WebElement showEntriesDropDown;


    public RequisitionsAddInstallationLocationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void validateTitle() {
        String getTitle = driver.getTitle();
        Assert.assertEquals("CATS Requisitions:", getTitle);
        searchTextField.click();
    }

    public void validatePage() {
        validateTitle();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void clickPreviousButton() {
        previousButton.click();
    }

    public void selectEntries (Integer entries) {
        new Select(showEntriesDropDown).selectByVisibleText(entries.toString());
    }
}

