package resellerPortal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static resellerPortal.ResellerLogin.driver;

public class customers_Onboarding  {
    private static final Logger logger = LogManager.getLogger(customers_Onboarding.class);


    @BeforeClass
    public void setupOnboarding() throws InterruptedException {
        logger.info("Setting up customer onboarding tests...");
        ResellerLogin.setup();
        ResellerLogin.loginWithMobileNumber();
        Thread.sleep(5000);
        ResellerLogin.partnerLogout();
    }
    @Test(priority = 1)
    public void selectPlan() throws InterruptedException {

    }




}
