package hw7.pages;


import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public static final org.apache.logging.log4j.Logger
            logger = LogManager.getLogger(WebDriver.class);
    protected static final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    protected static WebDriver driver;

    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    public WebElement waitElement(By locator){return new WebDriverWait(driver, 25).until(ExpectedConditions.elementToBeClickable(locator));}
}

