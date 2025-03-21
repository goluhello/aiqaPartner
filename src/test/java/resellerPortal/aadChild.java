package resellerPortal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;

import static resellerPortal.ResellerLogin.*;

public class aadChild  extends baseClass{

    private static final Logger logger = LogManager.getLogger(aadChild.class);


    @BeforeClass
    public void setupOnboarding() throws InterruptedException {
        logger.info("Setting up customer onboarding tests...");
        ResellerLogin.setup();
        ResellerLogin.loginWithMobileNumber();
        Thread.sleep(5000);
    }
    @Test(priority = 1)
    public void click_OnView_Teams() throws InterruptedException {
        logger.info("Partner navigate on Add Child Screen.....");

        driver.findElement(By.xpath("//h5[contains(text(),'View Team')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//p[contains(text(),'Add Team')]")).click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'Enter name...')]")).sendKeys(child_name);
        driver.findElement(By.xpath("//input[contains(@placeholder,'0000000000')]")).sendKeys(childMo_Number);
        driver.findElement(By.xpath("//input[contains(@type,'email')]")).sendKeys(child_name + "@gmail.com");
        Thread.sleep(5000);
        WebElement submitButton =driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submitButton.click();
        Thread.sleep(1000);
        logger.info("Member added successfully.");
        Thread.sleep(5000);
        partnerLogout();
        logger.info("Member added successfully & Partners logout successfully");
        Thread.sleep(2000);
    }
    @Test(enabled = false)
    public void admin_Login() throws InterruptedException {
        logger.info("Now admin login start from here.....");
        driver.findElement(By.xpath("//button[.='login with email or Branch']")).click();
        driver.findElement(By.xpath("//input[contains(@type,'text')]")).sendKeys(admin_eamil);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(admin_pswd);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        Thread.sleep(4000);
        partnerLogout();
        logger.info("Admin login success ");
        partnerLogout();
    }
    @Test( enabled = false)
    public void click_On_manageReseller() throws InterruptedException {
        logger.info("Click on Manage Reseller");
        driver.findElement(By.xpath("//p[contains(.,'Manage')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(childMo_Number);
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
        Thread.sleep(5000);
        partnerLogout();
        Thread.sleep(1000);
    }
    @Test(priority = 2)
    public void memberApproved() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[contains(@placeholder,'0000000000')]")).sendKeys(childMo_Number);
        logger.info("Reseller mobile number entered: {}", childMo_Number);
        driver.findElement(By.xpath("//button[contains(text(),'Generate OTP')]")).click();
        logger.info("Generate OTP button clicked.");
        List<WebElement> otpFields = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[contains(@aria-label, 'Digit')]")));

        for (int i = 0; i < addTeamsOTP.length() && i < otpFields.size(); i++) {
            otpFields.get(i).sendKeys(String.valueOf(addTeamsOTP.charAt(i)));
        }
        logger.info("OTP entered successfully.{}", addTeamsOTP);
        Thread.sleep(1000);
        WebElement clickOnTermsAndCondition=driver.findElement(By.xpath("//input[@id='t_c_accepted']"));
        clickOnTermsAndCondition.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Verify OTP')]"))).click();
        logger.info("Verify OTP button clicked.");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@placeholder='Pincode']")).sendKeys("110096");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[contains(text(),'Save and Continue')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(normalize-space(),'Save and Continue')]")).click();
        Thread.sleep(5000);
        WebElement resellerNameConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[contains(@class,'text-2xl font-medium text-black mb-6')]")
        ));
        String actualResellerName = resellerNameConfirmation.getText();

        if (actualResellerName.equals(child_name)) {
            logger.info("New reseller name is: {}", actualResellerName);
            Thread.sleep(5000);
            partnerLogout();
        } else {
            logger.info("{} not found...!", child_name);
        }
        Thread.sleep(2000);
        partnerLogout();
        Thread.sleep(2000);
        loginWithMobileNumber();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//h5[contains(text(),'View Team')]")).click();
        Thread.sleep(2000);
    }
    @Test(priority = 3)
    public void userBlock() throws InterruptedException {
        WebElement childMobileNumber=driver.findElement(By.xpath("//input[@placeholder='Search...']"));
        childMobileNumber.sendKeys(childMo_Number);
        Thread.sleep(500);
        WebElement searchBtn=driver.findElement(By.xpath("//button[contains(text(),'Search')]"));
        searchBtn.click();
        Thread.sleep(5000);
        WebElement threeDotBtn= driver.findElement(By.xpath("//button[img[@alt='Menu']]"));
        threeDotBtn.click();
        Thread.sleep(2000);
        WebElement blockAccoutn = driver.findElement(By.xpath("//button[contains(text(),'Block Account')]"));
        blockAccoutn.click();
        Thread.sleep(1000);
        WebElement confirmationBtn=driver.findElement(By.xpath("//button[contains(text(),'Confirm')]"));
        confirmationBtn.click();
        logger.info("user block successfully");
        Thread.sleep(5000);
        partnerLogout();
    }
    @Test(enabled = false)
    public void userUnblock() throws InterruptedException {

        WebElement childMobileNumber=driver.findElement(By.xpath("//input[@placeholder='Search...']"));
        childMobileNumber.sendKeys(childMo_Number);
        Thread.sleep(500);
        WebElement searchBtn=driver.findElement(By.xpath("//button[contains(text(),'Search')]"));
        searchBtn.click();
        Thread.sleep(5000);
        WebElement threeDotBtn= driver.findElement(By.xpath("//button[img[@alt='Menu']]"));
        threeDotBtn.click();
        Thread.sleep(2000);
        WebElement clickOnUnblockAccount=driver.findElement(By.xpath("//button[contains(text(),'Activate Account')]"));
        clickOnUnblockAccount.click();
        Thread.sleep(2000);
        WebElement confirmationBtn=driver.findElement(By.xpath("//button[contains(text(),'Confirm')]"));
        confirmationBtn.click();
        Thread.sleep(1000);
        logger.info("User Unblock Successfully");
    }
    @Test(priority = 4)
    public void LoginAgain() throws InterruptedException {
        Thread.sleep(2000);
        WebElement mobileInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'0000000000')]")));
        mobileInput.sendKeys(childMo_Number);
        logger.info("Reseller mobile number entered.");

        WebElement generateOtpButton = driver.findElement(By.xpath("//button[contains(text(),'Generate OTP')]"));
        generateOtpButton.click();
        Thread.sleep(1000);
        WebElement deactivateMessage = driver.findElement(By.xpath("//div[contains(text(),'Your account is inactive please contact support team.')]"));

        String confirmationDeactivateMessage = deactivateMessage.getText();

        if (confirmationDeactivateMessage.contains("Your account is inactive please contact support team.")) {
            System.out.println("Deactivation message is displayed correctly: " + confirmationDeactivateMessage);
            userUnblock();
        } else {
            System.out.println("Deactivation message is NOT displayed as expected.");
        }





    }
    

    }


