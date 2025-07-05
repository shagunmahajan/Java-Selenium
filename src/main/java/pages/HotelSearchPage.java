package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchPage extends BasePage {

    //page factory
    @FindBy(id="city")
    WebElement cityField;

    @FindBy(xpath = "//*[@title='Where do you want to stay?']")
    WebElement cityInputField;

    @FindBy(xpath="//*[contains(@id,'react-autowhatever-1-section-0-item-0')]")
    WebElement suggestionListFirstEle;

    @FindBy(id = "checkin")
    WebElement checkinDateField;

    @FindBy(id = "checkout")
    WebElement checkoutDateField;

    @FindBy(className = "rmsGst__body")
    WebElement roomsGuestsModal;

    @FindBy(xpath = "//*[@data-testid='room_count']/parent::*")
    WebElement roomCountDropDown;

    @FindBy(xpath = "//*[@data-testid='adult_count']/parent::*")
    WebElement adultCountDropDown;

    @FindBy(xpath = "//*[@data-testid='children_count']/parent::*")
    WebElement childCountDropDown;

    @FindBy(className = "rmsGst__slctAge")
    WebElement childAgeSection;

    @FindBy(xpath = "//*[@data-testid='child_count']")
    WebElement childAgeDropDown;

    @FindBy(xpath = "//*[text()='APPLY']")
    WebElement applyBtn;

    CommonPage commonPage;

    public HotelSearchPage() {
        super();
        PageFactory.initElements(driver, this);
        this.commonPage = new CommonPage();
    }

    public void selectCity(String city) throws InterruptedException {
        cityField.click();
        commonPage.sendKeys(cityInputField, city);
        commonPage.click(suggestionListFirstEle);
    }

    //format to be used as Jul 09 2025
    public void selectCheckInDate(String date) throws InterruptedException {
        cityField.click();
        checkinDateField.click();
        commonPage.selectCalendarDate(date);
    }
    //format to be used as Jul 09 2025
    public void selectCheckOutDate(String date) throws InterruptedException {
//        cityField.click();
//        checkoutDateField.click();
        commonPage.selectCalendarDate(date);
    }

    public void selectsRoomsGuests(String count, String fieldName) throws InterruptedException {
        commonPage.waitForElementToBeDisplayed(roomsGuestsModal);
        if(fieldName.equalsIgnoreCase("Rooms"))
            commonPage.selectCustomDropdownOption(roomCountDropDown, count);
        else if(fieldName.equalsIgnoreCase("Adults"))
            commonPage.selectCustomDropdownOption(adultCountDropDown, count);
        else {
            commonPage.selectCustomDropdownOption(childCountDropDown, count);
            selectAgeOfChild(Integer.parseInt(count));

        }
    }

    public void selectAgeOfChild(int childCount) throws InterruptedException {
        commonPage.waitForElementToBeDisplayed(childAgeSection);
        while(childCount>0){
            commonPage.selectCustomDropdownOption(childAgeDropDown, "2");
            childCount--;
        }
    }

    public void clickApplyBtn() throws InterruptedException {
        commonPage.click(applyBtn);
    }

}
