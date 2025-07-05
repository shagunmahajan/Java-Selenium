package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public static Properties loadProperties(String resourcePath) {
        Properties prop = new Properties();
        try (InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (input == null) {
                System.out.println("Unable to find " + resourcePath);
                return null;
            }
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
