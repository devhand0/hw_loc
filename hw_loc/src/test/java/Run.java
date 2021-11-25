import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Run {

    WebDriver driver;
    private final org.apache.logging.log4j.Logger
            logger = LogManager.getLogger(Logger.class);
    @Before
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        Duration duration = Duration.ofSeconds(3) ;
        driver.manage().timeouts().implicitlyWait(duration);
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
//        assert(driver.findElement(By.cssSelector("#links > div:nth-child(1) > div:nth-child(1) > h2:nth-child(1) > a:nth-child(1)")).getText().contains("Онлайн‑курсы")) : "Отус больше не первый" ;
//        assert(driver.findElement(By.cssSelector("#r1-0>div>h2.result__title")).getText().contains("Онлайн‑курсы")) : "Отус больше не первый" ;
//        logger.info("успех");
        try {Assert.assertTrue(driver.findElement(By.cssSelector("#r1-0>div>h2.result__title")).getText().contains("Онлайн‑курсы"));}
        catch (AssertionError e){logger.error("Отус больше не первый");Assert.fail();}
        logger.info("Отус первый в списке");
    }
    public void openDuck(){
        end();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        Duration duration = Duration.ofSeconds(3) ;
        driver.manage().timeouts().implicitlyWait(duration);
        driver.get("https://duckduckgo.com/");
        try {Assert.assertTrue(driver.getTitle().contains("DuckDuckGo"));}
        catch (AssertionError e){logger.error("https://duckduckgo.com/ не открылся");Assert.fail();}
        logger.info("https://duckduckgo.com/ открылся успешно");
        }
    /*
2)

Открыть Chrome в режиме киоска
Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
Нажать на любую картинку
Проверить что картинка открылась в модальном окне
     */
    @Test
    public void hw2()
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
//        Assert.assertEquals(driver.getTitle() ,"Photoflash - Photo Gallery Category Bootstrap Responsive Website Template - Home :: W3layouts ");
//        logger.info("открылся сайт");
        try {Assert.assertTrue(driver.getTitle().contains("Photoflash"));}
        catch (AssertionError e){logger.error("Photoflash не открылся");Assert.fail();}
        logger.info("Photoflash открылся успешно");
    }
    private void checkPhoto() {

        final String photo = "li.portfolio-item2:nth-child(1)";
        driver.findElement(By.cssSelector(photo)).click();
        logger.info("открылось модальное окно");
//        Thread.sleep(3000);
        logger.info("Ждем появления елемента модально окна");
        try {Assert.assertNotNull(checkElement(By.cssSelector(".pp_close")));}
        catch (AssertionError e){logger.error("Нет элемента модального окна");Assert.fail();}
        logger.info("успех");
        checkElement(By.cssSelector(".pp_close")).click();
        logger.info("закрыли модальное окно");
    }

    private WebElement checkElement(By locator){

        return new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(locator));

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
