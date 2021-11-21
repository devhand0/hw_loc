import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Run {

    WebDriver driver;
    private final org.apache.logging.log4j.Logger
            logger = LogManager.getLogger(Logger.class);
    @Before
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }
    @Test
    /*
    1)
Открыть Chrome в headless режиме
Перейти на https://duckduckgo.com/
В поисковую строку ввести ОТУС
Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
     */
    public void hw1(){
        openDuck();
        searchOtus();
        turnToRussia();
        checkOtus();
    }
    private void turnToRussia(){
        driver.findElement(By.cssSelector(".dropdown__switch")).click();
        logger.info("turn to Russia OK");
    }
    private void searchOtus(){
        String id = "search_form_input_homepage";
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys("otus.ru");
        driver.findElement(By.id(id)).submit();
    }
    private void checkOtus(){
        assert(driver.findElement(By.cssSelector("#links > div:nth-child(1) > div:nth-child(1) > h2:nth-child(1) > a:nth-child(1)")).getText().contains("Онлайн‑курсы")) : "Отус больше не первый" ;
        logger.info("успех");
    }

    //    private void checkOtus(){
//        if
//        (driver.findElement(By.cssSelector("#links > div:nth-child(1) > div:nth-child(1) > h2:nth-child(1) > a:nth-child(1)")).getText().contains("Онлайн‑курсы"))
//        {
//            logger.info("приверил ссылку ОК");
//        }
//        else
//        {
//            logger.info("приверил ссылку не ОК");
//        }
//        logger.info("успех");
//    }
    private void openDuck(){
        end();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://duckduckgo.com/");
        Assert.assertEquals(driver.getTitle() ,"DuckDuckGo — Максимум конфиденциальности, минимум усилий.");
    }
    /*
2)

Открыть Chrome в режиме киоска
Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
Нажать на любую картинку
Проверить что картинка открылась в модальном окне
     */
    @Test
    public void hw2() throws InterruptedException
    {
     openGal();
     checkPhoto();
    }
    private void openGal()
    {
        end();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        final String url = "https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818";
        driver.get(url);
        Assert.assertEquals(driver.getTitle() ,"Photoflash - Photo Gallery Category Bootstrap Responsive Website Template - Home :: W3layouts ");
        logger.info("открылся сайт");
    }
    private void checkPhoto() throws InterruptedException {

        final String photo = "li.portfolio-item2:nth-child(1)";



        driver.findElement(By.cssSelector(photo)).click();
        logger.info("открылось модальное окно");
        Thread.sleep(1000);
        logger.info("Ждем появления елемента модально окна");
        Assert.assertNotNull(checkElement(By.cssSelector(".pp_close")));
        checkElement(By.cssSelector(".pp_close")).click();
        logger.info("успех");
    }

    private WebElement checkElement(By locator){

        return new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(locator));
        }

/*
1)Открыть Chrome в режиме полного экрана
2)Перейти на https://otus.ru
3)Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
4)Вывести в лог все cookie
*/
@Test
public void hw3() {
    openOtusFS();
    auth();
    getCookie();
    }
    public void openOtusFS(){
    end();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-fullscreen");
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    driver.get("http://otus.ru");
    Assert.assertEquals(driver.getTitle() ,"Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");
}

    public void getCookie(){
        logger.info(driver.manage().getCookies());
    }
    public void auth(){
        final String login = "dev.loop1@gmail.com";
        final String pass = "12345";
        String buttonReg = ".header2__auth";
        String loginWindow = "div.new-input-line_slim:nth-child(3) > input:nth-child(1)";
        String passWindow = ".js-psw-input";
        driver.findElement(By.cssSelector(buttonReg)).click();
        driver.findElement(By.cssSelector(loginWindow)).clear();
        driver.findElement(By.cssSelector(loginWindow)).sendKeys(login);
        driver.findElement(By.cssSelector(passWindow)).clear();
        driver.findElement(By.cssSelector(passWindow)).sendKeys(pass);
        //driver.findElement(By.xpath(("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[4]/button"))).click();
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).click();
        Assert.assertNotNull(driver.findElement(By.cssSelector(".button__my-course")));
    }

    @After
    public void end(){
        if (driver!=null)
            driver.quit();
    }

}
