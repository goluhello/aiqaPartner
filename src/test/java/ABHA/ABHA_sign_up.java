package ABHA;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class ABHA_sign_up extends details_class {
    private static final Logger logger = LogManager.getLogger(ABHA_sign_up.class);

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void setup() {
        logger.info("Initializing WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(abha_url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("ABHA title is: " + driver.getTitle());
    }

    @Test
    public void signUp() throws InterruptedException {
        logger.info("User navigates to signup page");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Sign up')]")).click();

        // User details ---------
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(FullName);
        logger.info("Entered Full Name: -  " + FullName);

        driver.findElement(By.xpath("//input[@placeholder='0000000000']")).sendKeys(MobileNumber);
        logger.info("Entered Mobile Number: -  " + MobileNumber);

        driver.findElement(By.xpath("//input[@placeholder='dd-mm-yyyy']")).sendKeys(DOB);
        logger.info("Entered Date of Birth: - " + DOB);

        Thread.sleep(1000);
        driver.findElement(By.xpath("//option[text()='MALE']")).click();
        logger.info("Selected Gender: - MALE");

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(EmailAddress);
        logger.info("Entered Email Address: -  " + EmailAddress);

        driver.findElement(By.xpath("//input[contains(@name,'medicalId')]")).sendKeys(MedicalRegistrationNumber);
        logger.info("Entered Medical Registration Number: - " + MedicalRegistrationNumber);

        driver.findElement(By.xpath("//input[@name='hospitalName']")).sendKeys(HospitalName);
        logger.info("Entered Hospital Name: -  " + HospitalName);

        driver.findElement(By.xpath("//input[@name='address']")).sendKeys(HospitalAddress);
        logger.info("Entered Hospital Address: - " + HospitalAddress);

        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Passwrod);
        logger.info("Entered Password: - " + Passwrod);

        driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys(ConfirmPassword);
        logger.info("Entered Confirm Password: - " + ConfirmPassword);
        Thread.sleep(2000);
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        fileInput.sendKeys(imageLocation);
        logger.info("File successfully uploaded with image location and name : -  " +imageLocation);
        driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
        Thread.sleep(5000);

        WebElement createAcountBtn=driver.findElement(By.xpath("//button[contains(text(),'Create an Account')]"));
        createAcountBtn.click();
        Thread.sleep(1000);

        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
        String actualMessage = toastMessage.getText();


        String successMessage = "Success";
        String existingUserMessage = "User already exist by this email or mobile";

        if (actualMessage.contains(successMessage)) {
            logger.info("Registration successful: " + successMessage);
        } else if (actualMessage.contains(existingUserMessage)) {
            logger.info("User already exists: " + existingUserMessage);
        } else {
            logger.info("Unexpected alert message: " + actualMessage);
        }


        Thread.sleep(5000);

        driver.quit();



    }
}
