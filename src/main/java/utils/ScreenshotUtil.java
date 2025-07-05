package utils;

import base.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "test-results/screenshots/";

    public static File takeScreenshotFile(String name) {
        WebDriver driver = DriverManager.getDriver();
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(SCREENSHOT_DIR + name + ".png");

        try {
            Files.createDirectories(destFile.getParentFile().toPath());
            Files.copy(srcFile.toPath(), destFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destFile;
    }

    public static byte[] getBytesFromFile(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
