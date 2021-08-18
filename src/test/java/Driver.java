import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Driver {


    @BeforeAll
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/user.properties"));
        Configuration.startMaximized = true;


    }


}
