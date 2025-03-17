package ABHA;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    public static void abhaLogin() throws InterruptedException {
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

    }
}
