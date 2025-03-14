package resellerPortal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static resellerPortal.ResellerLogin.driver;

public class rembershumentRaise extends baseClass {
    private static final Logger logger = LogManager.getLogger(rembershumentRaise.class);

    @BeforeClass
    public void setupOnboarding() throws InterruptedException {
        logger.info("Setting up customer onboarding tests...");
        ResellerLogin.setup();
        ResellerLogin.loginWithMobileNumber();
        Thread.sleep(5000);

    }
    @Test
    public void  raiseClaim() throws InterruptedException {

        driver.findElement(By.xpath("//div[@aria-label='Show search options']")).click();
        driver.findElement(By.xpath("//input[@name='mobile']")).sendKeys(searchByMobileNo);

        driver.findElement(By.xpath("//button[@type='submit']")).submit();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@index,'0')]")).click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[contains(text(),'Raise Reimbursement')]")).click();

        logger.info(driver.getCurrentUrl());
        logger.info("Start Claim !.....");




    }



}
