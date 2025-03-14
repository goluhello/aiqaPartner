package ABHA;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import resellerPortal.ResellerLogin;

import java.time.Duration;

public class abha_loginPage extends details_class{

    private static final Logger logger = LogManager.getLogger(abha_loginPage.class);

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
    public  void abhaLogin(){
        logger.info("User navigates to signup page");

    }
}
