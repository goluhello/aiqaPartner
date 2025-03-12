package doctorPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class docLogin extends featureWithUrlFiles {

    private static final Logger logger = LogManager.getLogger(docLogin.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        logger.info("Initializing WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(doctURL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Navigated to Doctor Login Page. Page title: " + driver.getTitle());
    }

    @Test(priority = 1)
    public void doctorLogin() throws InterruptedException {
        driver.findElement(By.id("standard-size-small")).sendKeys(User_id);
        logger.info("Doctor mobile number entered: " + User_id);

        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        Thread.sleep(2000);

        List<WebElement> otpFields = driver.findElements(By.xpath("//input[contains(@aria-label, 'Digit')]"));
        if (otpFields.isEmpty()) {
            logger.error("OTP fields not found!");
            return;
        }

        for (int i = 0; i < OTP.length() && i < otpFields.size(); i++) {
            otpFields.get(i).sendKeys(String.valueOf(OTP.charAt(i)));
        }
        logger.info("The entered OTP is: " + OTP);

        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Verify Otp']")).click();
        logger.info("Login successful");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void doctorLogout() throws InterruptedException {
        logger.info("Doctor navigating to My Profile section for Logout");

        WebElement myProfile = driver.findElement(By.xpath("//span[contains(text(),'My Profile')]"));
        myProfile.click();
        Thread.sleep(2000);

        WebElement logoutButton = driver.findElement(By.xpath("//button[contains(text(),'Logout')]"));
        logoutButton.click();

        WebElement confirmLogoutButton = driver.findElement(By.xpath("//button[contains(@class, 'MuiButton-containedError')]"));
        confirmLogoutButton.click();

        logger.info("Doctor logged out successfully.");
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(5000);
            driver.quit();
            logger.info("WebDriver closed.");
        }
    }
}


