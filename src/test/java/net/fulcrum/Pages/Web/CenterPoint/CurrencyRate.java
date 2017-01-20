package net.fulcrum.Pages.Web.CenterPoint;

import org.openqa.selenium.By;
import net.fulcrum.Drivers.*;

public class CurrencyRate extends DataForm {

    public CurrencyRate(WebBrowserDriver driver) {
        super(driver);
    }

    //Search Tab Columns
    protected By searchBaseCurrencyRate = By.name("CR.BASECURRENCYCODE");
    protected By searchLocalCurrencyCode = By.name("CR.LOCALCURRENCYCODE");
    protected By searchConversionRate = By.name("CR.RATE");
    protected By searchFromEffectiveDate = By.name("FROM_EFFECTIVE_DATE");
    protected By searchToEffectiveDate = By.name("TO_EFFECTIVE_DATE");
    protected By searchAddedBy = By.name("CR.ADDCONTACTID");
    protected By searchModifedBy = By.name("CR.MODIFIEDCONTACTID");

    //Edit Tab Columns
    protected By editBaseCurrencyCode = By.name("BASECURRENCYCODE");
    protected By editLocalCurrencyCode = By.name("LOCALCURRENCYCODE");
    protected By editConversionRate = By.name("RATE");
    protected By editEffectiveDate = By.name("EFFECTIVEDDTM");
    protected By editExpirationDate = By.name("EXPIRATIONDDTM");


    public void searchBaseCurrencyRate(String rate) {
        driver.sendText(searchBaseCurrencyRate, rate);
    }

    public void searchLocalCurrencyCode(String code) {
        driver.sendText(searchLocalCurrencyCode, code);
    }

    public void searchConversionRate(String rate) {
        driver.sendText(searchConversionRate, rate);
    }

    public void searchFromEffectiveDate(String date) {
        driver.sendText(searchFromEffectiveDate, date);
    }

    public void searchToEffectiveDate(String date) {
        driver.sendText(searchToEffectiveDate, date);
    }

    public void searchAddedby(String addedBy) {
        driver.sendText(searchAddedBy, addedBy);
    }

    public void searchModifiedBy(String modifiedBy) {
        driver.sendText(searchModifedBy, modifiedBy);
    }

    public void editBaseCurrencyCode(String code) {
        driver.sendText(editBaseCurrencyCode, code);
    }

    public void editLocalCurrencyCode(String code) {
        driver.sendText(editLocalCurrencyCode, code);
    }

    public void editConversionRate(String rate) {
        driver.sendText(editConversionRate, rate);
    }

    public void editEffectiveDate(String date) {
        driver.sendText(editEffectiveDate, date);
    }

    public void editExpirationDate(String date) {
        driver.sendText(editExpirationDate, date);
    }

}
