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
import java.util.List;


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

    @Test(priority = 1)
    public void signUp  () throws InterruptedException {
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
    }
    @Test(priority = 2)
    public void loginABHA() throws InterruptedException {
        logger.info("User navigates to signup page");
//        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
//        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='0000000000']")).sendKeys(MobileNumber);
        driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys(Passwrod);
        Thread.sleep(1000);
        WebElement viewPassword=driver.findElement(By.xpath("//button[@class='absolute inset-y-0 end-0 flex items-center z-20 px-3 cursor-pointer text-gray-400 rounded-e-md focus:outline-none focus:text-blue-600']"));
        viewPassword.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("user login sucess for the confirmation : - "+ driver.getCurrentUrl());
        Thread.sleep(5000);
    }
    @Test(priority = 3)
    public void createABHA() {
        driver.findElement(By.xpath("//button[normalize-space()='Create ABHA']")).click();
        logger.info("User navigate on Create Screen: - " + driver.getCurrentUrl());

        logger.info("Enter Aadhaar number  ");

        List<WebElement> inputFields = driver.findElements(By.xpath("//input"));

        String inputValue = null;
        for (WebElement inputField : inputFields) {
            inputValue = "123456767898";
            inputField.sendKeys(inputValue);
        }
        logger.info("Entered value in input field: " + inputValue);
        driver.quit();


    }

}





