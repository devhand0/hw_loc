
import hw7.WDFactory.Browsers;
import hw7.WDFactory.WebDriverFactory;
import javafx.scene.paint.Stop;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected WebDriver driver;
    public static final org.apache.logging.log4j.Logger
            logger = LogManager.getLogger(WebDriver.class);

    public String browser() {
        if (System.getProperty("browser")==null){
            return "EDGE";
        }else {return System.getProperty("browser").toUpperCase();
        }
    }

//    String prop = System.getProperty("options");
    String prop = "--start-fullscreen";

    @Before


    public void setUp(){
        System.out.println("options = " + prop);
        if(browser().equals("CHROME")) {
            driver = WebDriverFactory.create(Browsers.CHROME, prop);
        }
        if(browser().equals("EDGE")) {
            driver = WebDriverFactory.create(Browsers.EDGE, prop);
        }
        if(browser().equals("FIREFOX")) {
            driver = WebDriverFactory.create(Browsers.FIREFOX, prop);
        }
//        else {
//            System.out.println("Нет такого браузер - будет EDGE");
//            driver = WebDriverFactory.create(Browsers.EDGE, options);
//        }
        driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);


    }

    @After
        public void end(){
            if (driver!=null){
                driver.quit();
            }

   }
}
