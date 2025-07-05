package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PropertiesReader;

import java.util.Properties;

public class FlightSearchPage extends BasePage {

    //page factory
    @FindBy(id="fromCity")
    WebElement fromCityField;

    @FindBy(id="toCity")
    WebElement toCityField;

    @FindBy(xpath="//*[contains(@id,'react-autowhatever-1-section-0-item-')]")
    WebElement suggestionList;

    @FindBy(xpath = "//*[text()='Departure']")
    WebElement departDateFieldLabel;

    @FindBy(xpath = "//*[@data-cy='departureDate']")
    WebElement departDateField;

    @FindBy(xpath = "//*[text()='Return']")
    WebElement returnDateFieldLabel;

    @FindBy(xpath = "//*[@data-cy='returnDefaultText']")
    WebElement returnDateField;

    @FindBy(xpath = "//*[text()='Search']")
    WebElement searchBtn;

    @FindBy(xpath = "//*[@data-cy='closeModal']")
    WebElement closeModal;

    CommonPage commonPage;

    public FlightSearchPage() {
        super();
        PageFactory.initElements(driver, this);
        this.commonPage = new CommonPage();
    }

    public void selectCity(String city) {
        String dynamicLoc = String.format("//*[contains(@id,'react-autowhatever-1-section-0-item-')]//*[contains(text(),'%s')]", city);
        driver.findElement(By.xpath(dynamicLoc)).click();
    }

    public void selectFromCityField(String city) throws InterruptedException {
        fromCityField.sendKeys(city);
        commonPage.waitForElementToBeClickable(suggestionList);
        selectCity(city);
        fromCityField.click();
    }

    public void selectToCityField(String city) throws InterruptedException  {
        toCityField.sendKeys(city);
        commonPage.waitForElementToBeClickable(suggestionList);
        selectCity(city);
    }

    //format to be used as Jul 09 2025
    public void selectDepartDate(String date) throws InterruptedException {
        fromCityField.click();
        departDateField.click();
        commonPage.selectCalendarDate(date);
    }

    //format to be used as Jul 09 2025
    public void selectReturnDate(String date) throws InterruptedException {
        fromCityField.click();
        returnDateField.click();
        commonPage.selectCalendarDate(date);
    }

    public void clickSearchBtn() throws InterruptedException {
        searchBtn.click();
    }
    public void closeModal() throws InterruptedException {
        commonPage.click(closeModal);
    }

}
