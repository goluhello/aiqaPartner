package resellerPortal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class resellerLogin extends baseClass{
    private static final Logger logger = LogManager.getLogger(resellerLogin.class);
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        logger.info("Initializing WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(base_url);
        logger.info("Page title is : -" + driver.getTitle());
        logger.info("Navigated to Reseller Login Page.");
    }

    @Test
    public void testResellerValidLogin() {
        logger.info("Executing test: testResellerValidLogin");
        System.out.println("all done");
        logger.info("Test execution completed.");
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        logger.info("Waiting before closing browser...");
        Thread.sleep(2000);
        driver.quit();
        logger.info("Browser closed successfully.");
    }
}
