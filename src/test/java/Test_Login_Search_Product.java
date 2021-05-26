import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class Test_Login_Search_Product extends BaseTest{

    private By lowProductPriceBy  = By.id("sp-price-lowPrice");
    private By highProductPriceBy = By.id("sp-price-highPrice");
    private By discountProductPriceBy = By.id("sp-price-discountPrice");
    private By productQuantity = By.id("buyitnow_adet");


    @Test
    @Order(1)
    public void Login() throws InterruptedException {

        Log4j.info("Login has started.");
        driver.get("https://www.gittigidiyor.com/uye-girisi");
        setById("L-UserNameField", "mzv31636@cuoly.com");
        setById("L-PasswordField", "123456test");
        click(By.id("gg-login-enter"),0);


    }

    @Test
    @Order(2)
    public void searchByKeyword() throws InterruptedException {
        Log4j.info("searchByKeyword method started");
        driver.findElement(By.className("sc-4995aq-0")).sendKeys("bilgisayar" + Keys.ENTER);
        driver.findElement(By.id("header-search-find-link")).click();
    }

    @Test
    @Order(3)
    public void goSecondPage() throws InterruptedException {
        Log4j.info("Expecting to open second page");
        driver.get(driver.getCurrentUrl() + "&sf=" + 2);
        if(driver.getCurrentUrl().contains("sf=2")){
            Log4j.info("Correct Page.");
        }
    }
    @Test
    @Order(4)
    public void chooseProduct() throws InterruptedException {
        Log4j.info("choseProduct is started");
        int randomProductIndex = new Random().nextInt(28);
        WebElement productElement = driver.findElement(By.cssSelector(".products-container > li:nth-child(" + randomProductIndex + ")"));

        // Navigate to details
        productElement.findElement(By.cssSelector("a")).click();

        // Wait until page load
        Thread.sleep(2000);


    }

    @Test
    @Order(5)
    public void addToBasket() throws InterruptedException {
        Log4j.info("addToBasket is started");

        WebElement element = driver.findElement(By.id("add-to-basket"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);

        Thread.sleep(1000);

        // Click to "add to cart"
        element.click();
    }
    @Test
    @Order(6)
    public void productPrice(){
        Log4j.info("The final product price is below (discount check)");
        driver.findElement(discountProductPriceBy).getText();
        if (!driver.findElement(discountProductPriceBy).getText().isEmpty()) {
            Log4j.info("Price is "+driver.findElement(discountProductPriceBy).getText());
        }
        else{
            if(!driver.findElement(lowProductPriceBy).getText().isEmpty()){
                Log4j.info("Price is "+lowProductPriceBy);
            }
            else
                Log4j.info("Price is "+highProductPriceBy);

        }

    }

    @Test
    @Order(7)
    public void basketCountUp(){
        Log4j.info("The basket is opened");
        driver.get("https://www.gittigidiyor.com/sepetim");

        Log4j.info("Make product count in basket 2 ");

        click(By.cssSelector("option[value='2']"),3);


    }


    @Test
    @Order(8)
    public void basketClean(){
        Log4j.info("Clear the basket");

        click(By.cssSelector("a[title='Sil']"),5);

    }
}
