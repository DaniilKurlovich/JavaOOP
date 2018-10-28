package sample;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private String nameProperty;

    public Settings(String nameProperty){
        this.nameProperty = nameProperty;
    }

    public String getPropertyValue(String propertyName) {
        String propertyValue = "";
        Properties properties = new Properties();

        try (InputStream inputStream = this.getClass().getResourceAsStream(nameProperty)) {
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return propertyValue;
    }
}
