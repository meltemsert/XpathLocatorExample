package tests;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyManager;

import java.net.MalformedURLException;

public class DemoQAGenerateTests {

    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager = new PropertyManager();
    String url = propertyManager.getProperty("APP_URL2");

    @BeforeMethod(alwaysRun = true)
    public void Before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openDemoqaPageTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "DEMOQA");
    }

    @Test
    public void addAndEditRecordInWebTable() {

        //ADD butonunu bulup tıklama
        WebElement addButton = driver.findElement(new By.ByXPath("//button[@id='addNewRecordButton']"));
        addButton.click();

        // Yeni kayıt ekranındaki bilgileri doldurun (örneğin, ad ve soyad)
        WebElement firstNameInput = driver.findElement(new By.ByXPath("//input[@id='firstName']"));
        WebElement lastNameInput = driver.findElement(new By.ByXPath("//input[@id='lastName']"));
        WebElement emailInput = driver.findElement(new By.ByXPath("//input[@id='userEmail']"));
        WebElement ageInput = driver.findElement(new By.ByXPath("//input[@id='age']"));
        WebElement salaryInput = driver.findElement(new By.ByXPath("//input[@id='salary']"));
        WebElement departmentInput = driver.findElement(new By.ByXPath("//input[@id='department']"));
        WebElement submitBtn = driver.findElement(new By.ByXPath("//button[@id='submit']"));

        // Örnek veri girişi
        firstNameInput.sendKeys("Meltem");
        lastNameInput.sendKeys("Sert");
        emailInput.sendKeys("meltemsert29@gmail.com");
        ageInput.sendKeys("30");
        salaryInput.sendKeys("50000");
        departmentInput.sendKeys("EGM");
        submitBtn.click();

        // Eklenen kaydın düzenleme düğmesini tıklayın (örnekte ilk satır düzenleniyor)
        WebElement editBtn = driver.findElement(new By.ByXPath("//span[@id= 'edit-record-4']"));
        editBtn.click();

        WebElement ageEditInput = driver.findElement(new By.ByXPath("//input[@id='age']"));
        WebElement salaryEditInput = driver.findElement(new By.ByXPath("//input[@id='salary']"));
        WebElement saveBtn = driver.findElement(new By.ByXPath("//button[@id='submit']"));

        // Yeni veri girişi
        ageEditInput.clear();
        ageEditInput.sendKeys("29");
        salaryEditInput.clear();
        salaryEditInput.sendKeys("55000");
        saveBtn.click();
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        webDriver.quitDriver();
    }
}


