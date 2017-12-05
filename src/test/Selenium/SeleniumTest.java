package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");


        WebDriver driver = new ChromeDriver();

        driver.get("http://em.endava.com/login");


        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement login = driver.findElement(By.xpath("/html/body/div/form/div/input[1]"));
        login.click();
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.xpath("/html/body/div/form/div/input[2]"));
        password.sendKeys("12345678");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement element= driver.findElement(By.xpath("/html/body/div/form/div/button"));
        element.click();

        System.out.println("Page title is: " + driver.getTitle());

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.get("http://em.endava.com/index");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://em.endava.com/userPage");


        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.get("http://em.endava.com/logout");
        driver.quit();

    }


}
