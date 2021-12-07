package hw7.pages;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    public MainPage(WebDriver driver) {
        super(driver);
        }

    public MainPage open(){
        String otus_URL = "https://otus.ru";
        driver.get(otus_URL);
        return this;
    }
    public MainPage auth(){
        final String buttonReg = ".header2__auth";
        final String loginWindow = "div.new-input-line_slim:nth-child(3) > input:nth-child(1)";
        final String passWindow = ".js-psw-input";
        final String buttonEnter = "div.new-input-line_last:nth-child(5) > button:nth-child(1)";
        driver.findElement(By.cssSelector(buttonReg)).click();
        waitElement(By.cssSelector(loginWindow)).clear();
        driver.findElement(By.cssSelector(loginWindow)).sendKeys(cfg.login());
        driver.findElement(By.cssSelector(passWindow)).clear();
        driver.findElement(By.cssSelector(passWindow)).sendKeys(cfg.pass());
        driver.findElement(By.cssSelector(buttonEnter)).click();
        Assert.assertNotNull(driver.findElement(By.cssSelector(".button__my-course")));
        return this;
    }
    public void goToPersonal(){
        final String  buttonMenu = ".ic-blog-default-avatar";
        final String  menuLK = ".header2-menu__dropdown-text";
        driver.findElement(By.cssSelector(buttonMenu)).click();
        driver.findElement(By.cssSelector(menuLK)).click();
            }
}
