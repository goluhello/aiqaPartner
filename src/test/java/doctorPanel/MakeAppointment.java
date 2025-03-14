package doctorPanel;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static doctorPanel.docLogin.driver;

public class MakeAppointment {

    private static final Logger logger = LoggerFactory.getLogger(MakeAppointment.class);

    @BeforeMethod
    public void setup() throws InterruptedException {
        logger.info("Setting up doctor login");
        docLogin.setup();
        docLogin.doctorLogin();
        logger.info("Doctor login successful");
    }

    @Test
    public void Appoinment_users() throws InterruptedException {
        logger.info("Executing appointment user test");
        driver.findElement(By.xpath("//span[starts-with(text(),'Appointments')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'New Appointment')]")).click();

    }



}
