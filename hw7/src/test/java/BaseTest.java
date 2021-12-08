
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

    String options = "";
    @Before


    public void setUp(){

        if(browser().equals("CHROME")) {
            driver = WebDriverFactory.create(Browsers.CHROME, options);
        }
        if(browser().equals("EDGE")) {
            driver = WebDriverFactory.create(Browsers.EDGE, options);
        }
        if(browser().equals("FIREFOX")) {
            driver = WebDriverFactory.create(Browsers.FIREFOX, options);
        }
        else {
            System.out.println("Нет такого браузер - будет EDGE");
            driver = WebDriverFactory.create(Browsers.EDGE, options);
        }
        driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);


    }

    @After
        public void end(){
            if (driver!=null)                 ;

   }
}
