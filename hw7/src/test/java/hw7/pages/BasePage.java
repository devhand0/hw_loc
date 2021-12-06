package hw7.pages;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement waitElement(By locator){return new WebDriverWait(driver, 25).until(ExpectedConditions.elementToBeClickable(locator));}
}

