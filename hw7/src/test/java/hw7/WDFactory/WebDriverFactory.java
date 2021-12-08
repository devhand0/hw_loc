package hw7.WDFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {


    private static WebDriver driver;

    public static WebDriver create(Browsers bw, String par) {
        switch (bw){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                if (par!=null){
                ChromeOptions options = new ChromeOptions();
                options.addArguments(par);}
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                if (par!=null){
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments(par);
                }
                driver =new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                if (par!=null)
                {
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments(par);
                }
                driver =new EdgeDriver();
                break;
        }
        return  driver;
        }
    }