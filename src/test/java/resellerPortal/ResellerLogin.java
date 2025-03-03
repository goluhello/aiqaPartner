package resellerPortal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class ResellerLogin extends baseClass {
    private static final Logger logger = LogManager.getLogger(ResellerLogin.class);
    static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setup() {
        logger.info("Initializing WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(base_url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Navigated to Reseller Login Page. Page title: " + driver.getTitle());
    }

    @Test(priority = 1)
    public static void loginWithMobileNumber() {
        logger.info("Executing test: loginWithMobileNumber");

        WebElement mobileInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'0000000000')]")));
        mobileInput.sendKeys(resellerMoNo);
        logger.info("Reseller mobile number entered.");

        WebElement generateOtpButton = driver.findElement(By.xpath("//button[contains(text(),'Generate OTP')]"));
        generateOtpButton.click();
        logger.info("OTP generated successfully.");

        List<WebElement> otpFields = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[contains(@aria-label, 'Digit')]")));

        for (int i = 0; i < otp.length(); i++) {
            otpFields.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }
        logger.info("OTP entered successfully.");

        WebElement verifyOtpButton = driver.findElement(By.xpath("//button[contains(text(),'Verify OTP')]"));
        verifyOtpButton.click();
        logger.info("Reseller login successful.");
    }

    @Test(priority = 3)
    public void loginWithMobileEmail() throws InterruptedException {
        logger.info("Executing test: loginWithMobileEmail");
        driver.findElement(By.xpath("//button[.='login with email or Branch']")).click();
        driver.findElement(By.xpath("//input[contains(@type,'text')]")).sendKeys(reseller_eamil);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(reseller_pswd);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        Thread.sleep(5000);
        partnerLogout();
        logger.info(driver.getCurrentUrl());


    }

    @Test(priority = 2)
    public static void partnerLogout() throws InterruptedException {
        logger.info("Executing test: partnerLogout");

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'border-gray-300')]")));
        String partnerName = logoutButton.getText();
        logger.info("Partner name: " + partnerName);
        Thread.sleep(5000);
        logoutButton.click();
        driver.findElement(By.xpath("//div[contains(text(),'Logout')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).click();
        logger.info("Partner logged out successfully.");
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
