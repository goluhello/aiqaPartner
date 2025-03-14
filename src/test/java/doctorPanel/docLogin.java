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
    static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setup() {
        logger.info("Initializing WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(doctURL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Navigated to Doctor Login Page. Page title: " + driver.getTitle());
    }

    @Test(priority = 1)
    public static void doctorLogin() throws InterruptedException {

        driver.findElement(By.id("standard-size-small")).sendKeys(User_id);

        logger.info("Doctor mobile number entered: " + User_id);

        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        Thread.sleep(2000);

        WebElement otpSendMessage;
        try {
            otpSendMessage = driver.findElement(By.xpath("//div[contains(text(),'Otp send successfully')]"));
        } catch (NoSuchElementException e) {
            logger.error("OTP confirmation message not found!");
            return;
        }

        String actualMessage = otpSendMessage.getText();
        String expectedMessage = "Otp send successfully";

        if (!actualMessage.equals(expectedMessage)) {
            logger.error("Expected message not found! Actual message: " + actualMessage);
            return;
        }

        logger.info("Message is: " + expectedMessage);

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

        try {
            WebElement otpConfirmationMessage = driver.findElement(By.xpath("//div[starts-with(text(),'OTP verified successful.')]"));
            String actualOTPMessage = otpConfirmationMessage.getText();
            String finalMessageOTP = "OTP verified successful.";
            if (actualOTPMessage.equals(finalMessageOTP)) {
                logger.info("Actual message is: " + finalMessageOTP);
            } else {
                logger.info("OTP message not found!");
            }
        } catch (NoSuchElementException e) {
            logger.error("OTP verification message not found!");
        }

        driver.findElement(By.xpath("//button[text()='Verify Otp']")).click();
        logger.info("Login successful");
        Thread.sleep(2000);
    }
    @Test(priority = 2)
    public static void doctorLogout() throws InterruptedException {
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


