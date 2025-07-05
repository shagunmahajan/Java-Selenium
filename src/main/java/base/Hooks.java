package base;

import io.cucumber.java.*;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.PropertiesReader;
import utils.ScreenshotUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Properties;

public class Hooks {
    public static final Path workingDir = Paths.get(System.getProperty("user.dir"));

    @BeforeAll
    public static void beforeAll() {
        deleteDir();
    }
    @Before
    public void setUp() {
        DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            File screenshotFile = ScreenshotUtil.takeScreenshotFile(screenshotName);

            scenario.attach(ScreenshotUtil.getBytesFromFile(screenshotFile), "image/png", "Failure Screenshot");

            System.out.println("Screenshot saved at: " + screenshotFile.getAbsolutePath());
        }
        DriverManager.quitDriver();
    }

    @AfterAll()
    public static void afterAll() throws IOException, InterruptedException {
        Properties prop = PropertiesReader.loadProperties("allure.properties");

        String resultsDir = prop.getProperty("allure.results.directory", "allure-results");
        String reportDir = prop.getProperty("allure.report.directory", "allure-report");
        // Using CLI through ProcessBuilder
        ProcessBuilder pb = new ProcessBuilder(
//                "C:\\allure-2.34.1\\bin\\allure.bat",
                "allure",
                "generate",
                "--single-file",
                "--clean",
                resultsDir,
                "-o",
                reportDir);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // Capture and print output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[Allure CLI] " + line);
            }
        }
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Allure report generated successfully at " + reportDir);
        } else {
            System.err.println("Failed to generate Allure report");
        }
    }

    public static void deleteDir(){
        Path dir = Paths.get("test-results");

        try {
            Files.walk(dir)
                    .sorted(Comparator.reverseOrder()) // Delete children first
                    .map(Path::toFile)
                    .forEach(File::delete);

            System.out.println("Directory deleted: " + dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
