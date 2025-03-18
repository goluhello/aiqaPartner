package OrangeHRMLiveProject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class login_user extends values_Data_class {
    private static final Logger logger = LogManager.getLogger(login_user.class);
    private static WebDriver driver;
    private WebDriverWait wait;

    // Extent Report Variables
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setupExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @BeforeClass
    public void setup() throws InterruptedException {
        logger.info("Initializing WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(demo_url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Browser title is : - " + driver.getTitle());
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    @Attachment(value = "Valid Login Screenshot", type = "image/png")
    public void valid_Login_test() throws InterruptedException {
        test = extent.createTest("Valid Login Test");
        logger.info("Now start login in " + driver.getTitle() + " portal");

        driver.findElement(By.name("username")).sendKeys(Username);
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);

        String currentUrl = driver.getCurrentUrl();
        logger.info(currentUrl);
        test.pass("Successfully logged in.");

        // Capture screenshot for Allure
        Allure.addAttachment("Valid Login Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Test(priority = 1)
    @Attachment(value = "Invalid Login Screenshot", type = "image/png")
    public void Invalid_Login_test() throws InterruptedException {
        test = extent.createTest("Invalid Login Test");
        driver.findElement(By.name("username")).sendKeys(Invalid_Username);
        driver.findElement(By.name("password")).sendKeys(Invalid_Password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);

        WebElement captured_error = driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
        String actual_error = captured_error.getText();
        String expected_error = "Invalid credentials";

        if (actual_error.equals(expected_error)) {
            logger.info(expected_error);
            test.pass("Invalid login test passed.");
        } else {
            logger.info("Message not found ....");
            test.fail("Invalid login test failed.");
        }

        logger.info(driver.getCurrentUrl());
        Thread.sleep(2000);

        // Capture screenshot for Allure
        Allure.addAttachment("Invalid Login Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Test(priority = 3)
    public void user_logout() throws InterruptedException {
        test = extent.createTest("User Logout Test");
        // Add logout implementation
        test.info("Logout functionality to be implemented.");
          logger.info(driver.getCurrentUrl());
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

    @AfterSuite
    public void flushReports() {
        extent.flush();
    }
}
