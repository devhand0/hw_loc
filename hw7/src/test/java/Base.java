
import config.ServerConfig;
import hw7.WDFactory.Browsers;
import hw7.WDFactory.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class Base {
    protected WebDriver driver;
    public static final org.apache.logging.log4j.Logger
            logger = LogManager.getLogger(WebDriver.class);
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    @Before


    public void setUp(){
        String options = "";
        driver = WebDriverFactory.create(Browsers.CHROME, options);
        driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);


    }

    @After
        public void end(){
            if (driver!=null)
                driver.quit();

   }
}
