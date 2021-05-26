import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    static WebDriverWait wait;
    static WebDriver driver;
    static ExpectedCondition<Boolean> documentReady = driver -> {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    };

    @BeforeAll
    public void setUp(){
        System.out.println("Base Test");
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.gittigidiyor.com/");
        wait = new WebDriverWait(driver, 15);
    }
    @AfterAll
    public void testFinish(){
        System.out.println("Base Test Finish");
        driver.quit();

    }

    public void setById(String id, String value) {
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys(value);
    }

    public void clickById(String id) {
        driver.findElement(By.id(id)).click();
    }

    public void getUrl(String URL) {
        driver.get(URL);
    }
}
