package OrangeHRMLiveProject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class login_user extends values_Data_class {
    private static final Logger logger = LogManager.getLogger(login_user.class);
    private static WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        logger.info("Initializing WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(demo_url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Browser title is : - "+ driver.getTitle());
        Thread.sleep(2000);
    }
    @Test(priority = 2)
    public void valid_Login_test() throws InterruptedException {
        logger.info("Now start login in " + driver.getTitle() + "portal");
        driver.findElement(By.name("username")).sendKeys(Username);
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        logger.info(driver.getCurrentUrl());
    }
    @Test(priority = 1)
    public void Invalid_Login_test() throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys(Invalid_Username);
        driver.findElement(By.name("password")).sendKeys(Invalid_Password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        WebElement captured_error = driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
        String actual_error = captured_error.getText();
        String expected_error = "Invalid credentials";
        if (actual_error.equals(expected_error)){
            logger.info(expected_error);
        }else {
            logger.info("message not found ....");
        }
        logger.info(driver.getCurrentUrl());
        Thread.sleep(2000);
    }
    @Test
     public void user_logout() throws InterruptedException {
        logger.info("Navigate to logout screen....");
        driver.findElement(By.xpath("//li[@class='oxd-userdropdown']")).click();
        Thread.sleep(2000);
    }







    @AfterClass
    public void teardown() throws InterruptedException {
        if (driver != null) {
            logger.info("Closing browser...");
            Thread.sleep(5000);
            driver.quit();
            logger.info("Browser closed successfully.");
        }
    }
}
