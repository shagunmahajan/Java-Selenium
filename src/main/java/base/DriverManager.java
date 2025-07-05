package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.PropertiesReader;

import java.util.Properties;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    static Properties prop = PropertiesReader.loadProperties("data/testData.properties");

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();
            if (prop.getProperty("headless").equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
            }
            WebDriver newDriver = new ChromeDriver(options);
            newDriver.manage().window().maximize();
            driver.set(newDriver);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

