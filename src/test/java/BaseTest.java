import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    WebDriver driver;
    @BeforeAll
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver.get("https://www.gittigidiyor.com/");
    }

    @AfterAll
    public void endTest(){
        driver.quit();
    }
}
