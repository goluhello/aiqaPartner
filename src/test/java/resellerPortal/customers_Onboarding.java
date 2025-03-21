package resellerPortal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static resellerPortal.ResellerLogin.driver;


public class customers_Onboarding extends baseClass {
    private static final Logger logger = LogManager.getLogger(customers_Onboarding.class);

    @BeforeClass
    public void setupOnboarding() throws InterruptedException {
        logger.info("Setting up customer onboarding tests...");
        ResellerLogin.setup();
        ResellerLogin.loginWithMobileNumber();
        Thread.sleep(5000);
    }
    @Test(priority = 1)
    public void selectPlan() throws InterruptedException  {
        Thread.sleep(2000);
        WebElement clickOnAddMemberBtn = driver.findElement(By.xpath("//button[contains(text(),'Add New Member')]"));
        clickOnAddMemberBtn.click();
        Thread.sleep(2000);
        List<WebElement> paragraphElements = driver.findElements(By.tagName("p"));
        boolean productFound = false;
        for (WebElement element : paragraphElements) {
            if (element.getText().equals(expectedProduct)) {
                System.out.println("Expected product found: " + expectedProduct);
                Thread.sleep(2000);
                element.click();
                Thread.sleep(5000);
                WebElement continueBtn1=driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
                continueBtn1.click();
                Thread.sleep(1000);
                logger.info("Product name is :- {}", expectedProduct);
                Thread.sleep(2000);
                WebElement spouseBtn = driver.findElement(By.xpath("//button[.='Spouse']"));
                String confirmationSpouseName = spouseBtn.getText();

                if (spouseBtn.isEnabled()) {
                    spouseBtn.click();
                    logger.info("Member relation : - {}", confirmationSpouseName);

                    WebElement daughterBtn = driver.findElement(By.xpath("//button[.='Daughter']"));
                    if (daughterBtn.isEnabled()) {
                        daughterBtn.click();
                        logger.info("Member relation : - " + daughterBtn.getText());

                        WebElement sonBtn = driver.findElement(By.xpath("//button[.='Son']"));
                        if (sonBtn.isEnabled()) {
                            sonBtn.click();
                            logger.info("Member relation : - " + sonBtn.getText());
                        } else {
                            logger.error("{} is not displayed", sonBtn.getText());
                        }

                    } else {
                        logger.error("{} is not displayed", daughterBtn.getText());
                    }

                } else {
                    logger.error("{} is not displayed", confirmationSpouseName);
                }

                WebElement continueBtn2=driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
                continueBtn2.click();





                productFound = true;
                break;

    }




}  }
}
