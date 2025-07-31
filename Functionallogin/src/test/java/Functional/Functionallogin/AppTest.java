package Functional. functionallogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager; 
import org. testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup(); 
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(priority = 1)
    public void testValidLogin() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123"); // FIXED: typo 'by' and 'sendkeys'
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(3000);
        WebElement dashboard = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(dashboard.isDisplayed(), "Login Failed - Dashboard not found");
    }

    @Test(priority = 2)
    public void testInvalidLogin() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.name("username")).sendKeys("invalidUser");
        driver.findElement(By.name("password")).sendKeys("wrongPass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(3000);
        WebElement errorMsg = driver.findElement(By.xpath("//p[contains(text(),'Invalid credentials')]"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not shown for invalid login");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
