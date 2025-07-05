package pages;
import base.BasePage;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;

import java.time.Duration;
import java.util.Properties;

public class CommonPage extends BasePage {

    Properties prop = PropertiesReader.loadProperties("data/testData.properties");

    public void launchUrl() throws InterruptedException {
        String url = prop.getProperty("baseUrl");
        driver.get(url);
    }

    public void waitForElementToBeDisplayed(WebElement loc) throws InterruptedException {
        System.out.println("loc");
        System.out.println(loc);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(loc));
    }

    public void waitForElementToBeClickable(WebElement loc) throws InterruptedException {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(loc));
    }

    public void waitForElementToDisappear(WebElement loc) throws InterruptedException {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOf(loc));
    }

    public void openUrl(String url){
        driver.navigate().to(url);
        driver.manage().window().fullscreen();
    }

    public void selectDropDownByText(By locator, String expectedText){
        var select = new Select(driver.findElement(locator));
        select.selectByVisibleText(expectedText);
    }

    public void selectDropDownByValue(By locator, String expectedValue){
        var select = new Select(driver.findElement(locator));
        select.selectByValue(expectedValue);
    }

    public void click(WebElement loc) throws InterruptedException {
        waitForElementToBeDisplayed(loc);
        waitForElementToBeClickable(loc);
        loc.click();
    }

    public void sendKeys(WebElement loc, String value) throws InterruptedException {
        waitForElementToBeDisplayed(loc);
        loc.sendKeys(value);
    }

    public void selectCustomDropdownOption(WebElement dropdownToggle, String optionText) throws InterruptedException {
        click(dropdownToggle);

        String dynamicXpath = String.format("//ul/li[text()='%s']", optionText);
        WebElement option = driver.findElement(By.xpath(dynamicXpath));
        click(option);
    }


    public void clickOption(String text) throws InterruptedException {
        String dynamicLoc = String.format("//li[contains(text(),'%s')]", text);
        WebElement ele = driver.findElement(By.xpath(dynamicLoc));
        waitForElementToBeDisplayed(ele);
        click(ele);
    }

    public void clickMenu(String option) throws InterruptedException {
        String dynamicLoc = String.format("//*[contains(@data-cy,'%s')]", option);
        WebElement ele = driver.findElement(By.xpath(dynamicLoc));
        waitForElementToBeDisplayed(ele);
        click(ele);
    }

    public void selectCalendarDate(String date) throws InterruptedException {
        String dynamicLoc = String.format("//*[contains(@aria-label,'%s')]", date);
        WebElement ele = driver.findElement(By.xpath(dynamicLoc));
        waitForElementToBeDisplayed(ele);
        click(ele);
    }

}
