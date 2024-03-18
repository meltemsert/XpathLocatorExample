package tests;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyManager;

import java.net.MalformedURLException;
import java.time.Duration;

public class DemoQATests {
    public static WebDriver driver;

    Driver webDriver = new Driver();
    PropertyManager propertyManager = new PropertyManager();
    String url = propertyManager.getProperty("APP_URL");

    @BeforeMethod(alwaysRun = true)
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openDemoqaPageTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "DEMOQA");
    }

    @Test
    public void clickButtonAndReadMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Buttons seçeneğine tıklama
        WebElement clickButton = driver.findElement(new By.ByXPath("//li[@id= 'item-4']"));
        clickButton.click();

        // Click Me düğmesine tıklama
//        WebElement iframe = driver.findElement(By.tagName("iframe"));
//        driver.switchTo().frame(iframe);

        WebElement clickMeButton = driver.findElement(new By.ByXPath("//button[text()= 'Click Me' ]"));
        wait.until(ExpectedConditions.elementToBeClickable(clickMeButton));
        clickMeButton.click();

//        driver.switchTo().defaultContent();

        // Görünen mesajı okuma
        WebElement messageElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text() = 'You have done a right click']")));
        String messageText = messageElement.getText();
        String expectedText = "You have done a right click";
        Assert.assertEquals(messageText, expectedText);

    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        webDriver.quitDriver();
    }

}

