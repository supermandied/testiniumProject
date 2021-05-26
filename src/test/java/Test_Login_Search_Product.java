import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class Test_Login_Search_Product extends BaseTest{


    @Test
    public void Login(){
        getUrl("https://www.gittigidiyor.com/uye-girisi");
        setById("L-UserNameField", "taskmailim2020@gmail.com");
        setById("L-PasswordField", "tasksifre2020");
        clickById("gg-login-enter");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    private void searchByKeyword() throws InterruptedException {
        setById("search_word", "fırın");
        clickById("header-search-find-link");
        Thread.sleep(5000);
        wait.until(documentReady);
    }

    @Test
    private void listProducts() {
        List<WebElement> productElement = driver.findElements(By.cssSelector(".products-container > li"));

        for (WebElement element : productElement) {
            String title = element.findElement(By.cssSelector(".product-title > span")).getText();
            String price = element.findElement(By.cssSelector(".price-txt")).getText();

            System.out.println("Title : " + title + " Price :" + price);
        }
    }
    @Test
    public void chooseProduct() throws InterruptedException {
        int randomProductIndex = new Random().nextInt(28);
        WebElement productElement = driver.findElement(By.cssSelector(".products-container > li:nth-child(" + randomProductIndex + ")"));

        // Navigate to details
        productElement.findElement(By.cssSelector("a")).click();

        // Wait until page load
        Thread.sleep(4000);

    }

    @Test
    private void addToBasket() throws InterruptedException {
        WebElement element = driver.findElement(By.id("add-to-basket"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);

        Thread.sleep(1000);

        // Click to "add to cart"
        element.click();

        Thread.sleep(1000);
    }
}
