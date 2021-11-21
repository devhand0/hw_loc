import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReadWrite {

    WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);
    @Before
    public void StartUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.error("Драйвер поднят");
    }

       @Test
       public void openPage(){
           driver.get("http://otus.ru");}
           public void cookie(){
            driver.manage().addCookie(new Cookie("otus1", "value1"));
            Cookie temp = driver.manage().getCookieNamed("otus1");
           logger.error(temp);
       }
    @Test
    public void takeScreenshot(){
        driver.get("https://ya.ru");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#text")).clear();
        driver.findElement(By.cssSelector("#text")).sendKeys("file");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        saveFile(file);

    }
    private void saveBase64(String data) {
        File file = OutputType.FILE.convertFromBase64Png(data);
        saveFile(file);
    }

    private void saveFile(File data) {
        String fileName = "D:\\" + System.currentTimeMillis() + ".png";
        try {
            FileUtils.copyFile(data, new File(fileName));
        } catch (IOException e) {
            logger.error(e);
        }
    }


    //       public void auth() throws InterruptedException {
//           String login = "dev.loop1@gmail.com";
//           String pass = "xxxx";
//           String locator = "header2__auth";
//          driver.findElement(By.cssSelector(locator)).click();
//
@Test
public void waitSample() {
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    driver.get("http://otus.ru");
}
@Test
public void window() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.manage().window().setSize(new Dimension(800,600));
        logger.info(driver.manage().window().getSize());
        Thread.sleep(5000);
        Point point = driver.manage().window().getPosition();
        point.x = point.x + 500;
        driver.manage().window().setPosition(point);
        Thread.sleep(5000);

}
    @Test
    public void headless() throws InterruptedException {
        driver.quit();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://otus.ru");
        String locator = ".header2__auth";
        driver.findElement(By.cssSelector(locator)).click();
        Thread.sleep(5000);

    }
    @After
    public void End(){
        if (driver!=null)
            driver.quit();
    }

}

