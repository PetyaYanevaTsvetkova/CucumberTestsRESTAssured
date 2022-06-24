package helper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    private static final String FILE_PATH = "src/test/resources/config.properties";
    private Properties properties;

    private static PropertiesHelper helper;

    static {
        try {
            helper = new PropertiesHelper();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesHelper() throws IOException {
        this.properties = new Properties();
        FileReader fileReader = new FileReader(FILE_PATH);
        this.properties.load(fileReader);
    }

    public String getProperty(String property){
        return this.properties.getProperty(property);
    }

    public static PropertiesHelper getInstance() {
        return helper;
    }
}
