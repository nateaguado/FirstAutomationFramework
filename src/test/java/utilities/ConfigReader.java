package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static FileInputStream input;
    private static Properties properties;

    static {
        String path = "C:\\Users\\Butbu\\IdeaProjects\\MindtekCucumberFramework\\src\\test\\resources\\configurations\\Configuration.properties";
        try {
            input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("Path for properties file is invalid or the file is missing from provided location");
        } catch (IOException e) {
            System.out.println("Failed to load properties file");
        }finally {
            try {
                assert  input != null;
                input.close();
            } catch (IOException e) {
                System.out.println("Exception occurred when trying to close the input object");
            }
        }
    }

    /**
     * this method accepts the key as string
     * and returns the value as string
     * The keys and values are stored in the properties file
     */

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
