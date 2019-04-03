import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class test {

    public static void main(String[] args) throws IOException {
        Properties property = new Properties();
        FileInputStream fis = new FileInputStream("/Users/wasel/IdeaProjects/untitled/src/calc/property.properties");
        property.load(fis);
        for (int i = 0; i < property.size(); i++) {
            String in = property.getProperty("task." + i);
            System.out.println(in);
        }
    }


}
