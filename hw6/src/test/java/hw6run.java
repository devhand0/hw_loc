import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.bouncycastle.cms.RecipientId.password;

public class hw6run {
    protected static WebDriver driver;
    public static final org.apache.logging.log4j.Logger
            logger = LogManager.getLogger(WebDriver.class);
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    public static void myAssertEquals(Object expected, Object actual) {
        try {
            Assert.assertEquals(expected, actual );
        } catch (ComparisonFailure e) {
            logger.error("Ошибка");
            Assert.fail("Ошибка");
        }
        logger.info("ОК");
    }

    final String nameLoc = "#id_fname";
    final String nameLatinLoc = "#id_fname_latin";
    final String surnameLoc = "#id_lname";
    final String surnameLatinLoc = "#id_lname_latin";
    final String nicknameLoc = "#id_blog_name";
    final String birthdayLoc = ".input-icon > input:nth-child(1)";
    final String countryLoc = ".js-lk-cv-dependent-master";
//    final String contactTypeList = "";

    @Before
    public void logStart()
    {
        logger.info("Тест начат");
    }
    @After
    public void end(){
        if (driver!=null)
            driver.quit();
    }

//    1)Открыть https://otus.ru
//    2)Авторизоваться на сайте
//    3)Войти в личный кабинет
//    4)В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
//    5)Нажать сохранить
//    6)Открыть https://otus.ru в "чистом браузере"
//    7)Авторизоваться на сайе
//    8)Войти в личный кабинет
//    10)Проверить, что в разделе "О себе" отображаются указанные ранее данные

    @Test
    public void hw6()  {
        String log = System.getProperty("log");
        System.out.println(log);
        startUp();
        openOtus();
        auth();
        goToPersonal();
        modPersonal();
        checkPersonal();
    }
public void checkPersonal() {

    end();
    startUp();
    openOtus();
    auth();
    goToPersonal();
/////////////////////////////////////
    logger.info("Проверка имени");
    myAssertEquals(cfg.name(), driver.findElement(By.cssSelector(nameLoc)).getAttribute("value"));
    logger.info("Проверка name");
    myAssertEquals(cfg.nameLatin(), driver.findElement(By.cssSelector(nameLatinLoc)).getAttribute("value"));
    logger.info("Проверка фимилии");
    myAssertEquals(cfg.surname(), driver.findElement(By.cssSelector(surnameLoc)).getAttribute("value"));
    logger.info("Проверка surname");
    myAssertEquals(cfg.surnameLatin(), driver.findElement(By.cssSelector(surnameLatinLoc)).getAttribute("value"));
    logger.info("Проверка nickname");
    myAssertEquals(cfg.nickname(), driver.findElement(By.cssSelector(nicknameLoc)).getAttribute("value"));
    logger.info("Проверка birthday ");
    myAssertEquals(cfg.birthday(), driver.findElement(By.cssSelector(birthdayLoc)).getAttribute("value"));
    logger.info("Проверка страны");
    myAssertEquals("Россия", driver.findElement((By.cssSelector(countryLoc))).getText());
    logger.info("Проверка города");
    myAssertEquals("Москва", driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city")).getText());
    logger.info("Проверка уровня английского");
    myAssertEquals("Средний (Intermediate)", driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).getText());
    logger.info("Проверка типа контакта, выбран Skype");
    myAssertEquals("Skype", driver.findElement(By.cssSelector("div.js-formset-row:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div")).getText());
    logger.info("Проверка значения контакта, выбран Skype");
    myAssertEquals(cfg.skype(), driver.findElement(By.cssSelector("#id_contact-0-value")).getAttribute("value"));
    logger.info("Проверка типа контакта, выбран Тelegram");
    myAssertEquals("Тelegram", driver.findElement(By.cssSelector("div.js-formset-row:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).getText());
    logger.info("Проверка значения контакта, выбран Тelegram");
    myAssertEquals(cfg.telegram(), driver.findElement(By.cssSelector("#id_contact-1-value")).getAttribute("value"));
}



    public WebElement waitElement(By locator){

        return new WebDriverWait(driver, 25).until(ExpectedConditions.elementToBeClickable(locator));

    }
    public void modPersonal()  {
// очистка контактов
        List<WebElement> options = driver.findElements(By.xpath("//*[contains(text(), 'Удалить')]"));
        System.out.println(" CLASS  Elements size " + options.size());
        for (WebElement option : options){
            if(option.isDisplayed()){
                option.click();
            }
        }
//        Очистка - ввод текста
        driver.findElement(By.cssSelector(nameLoc)).clear();
        System.out.println(cfg.name());
        driver.findElement(By.cssSelector(nameLoc)).sendKeys(cfg.name());
        driver.findElement(By.cssSelector(nameLatinLoc)).clear();
        driver.findElement(By.cssSelector(nameLatinLoc)).sendKeys(cfg.nameLatin());
        driver.findElement(By.cssSelector(surnameLoc)).clear();
        driver.findElement(By.cssSelector(surnameLoc)).sendKeys(cfg.surname());
        driver.findElement(By.cssSelector(surnameLatinLoc)).clear();
        driver.findElement(By.cssSelector(surnameLatinLoc)).sendKeys(cfg.surnameLatin());
        driver.findElement(By.cssSelector(nicknameLoc)).clear();
        driver.findElement(By.cssSelector(nicknameLoc)).sendKeys(cfg.nickname());
        driver.findElement(By.cssSelector(birthdayLoc)).clear();
        driver.findElement(By.cssSelector(birthdayLoc)).sendKeys(cfg.birthday());
//        Списки страна, город, уровень английского
        driver.findElement(By.cssSelector(countryLoc)).click();
        waitElement(By.xpath("//*[@title='Россия']")).click();
//        System.out.println(driver.findElement((By.cssSelector(countryLoc))).getText());
        waitElement(By.cssSelector(".js-lk-cv-dependent-slave-city>label>div")).click();
//        waitElement(By.cssSelector("body > div.body-wrapper > div > div.js-lk-cv > div.container.container-padding-bottom > div.container__row > div.container__col.container__col_9.container__col_md-8.container__col_sm-12.container__col_border-left.lk-rightbar.print-block.print-wide > div > form > div:nth-child(2) > div.container__col.container__col_12 > div:nth-child(1) > div > div.container__col.container__col_9.container__col_ssm-12 > div:nth-child(2) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")).click();
        waitElement(By.xpath("//*[@title='Москва']")).click();
        System.out.println(waitElement(By.cssSelector(".js-lk-cv-dependent-slave-city")).getText());
        driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).click();
        waitElement(By.xpath("//*[@title='Средний (Intermediate)']")).click();
        System.out.println(waitElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).getText());
//   Добавление контактов
// Telegramm
        driver.findElement(By.xpath("//*[contains(text(), 'Добавить')]")).click();

        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>div.select>label")).click();

        driver.findElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>div.select>div>div>button[data-value='telegram']")).click();

        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>input")).clear();
        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>input")).sendKeys(cfg.telegram());
//  Skype
        driver.findElement(By.xpath("//*[contains(text(), 'Добавить')]")).click();
        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>div.select>label")).click();

        driver.findElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>div.select>div>div>button[data-value='skype']")).click();

        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>input")).clear();
        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>input")).sendKeys(cfg.skype());
        //    Сохранить продолжить
        driver.findElement(By.xpath("//*[contains(text(), 'Сохранить и продолжить')]")).click();
    //    Thread.sleep(15000);
    }
    public void goToPersonal()
    {
    driver.findElement(By.cssSelector(".ic-blog-default-avatar")).click();
    driver.findElement(By.cssSelector(".header2-menu__dropdown-text")).click();
    }
    public void openOtus(){
//        System.out.println(cfg.login());
        //        end();
        driver.get("http://otus.ru");
        Assert.assertEquals(driver.getTitle() ,"Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");
    }
    public static String loginE() {
        return System.getProperty("login");
    }
    public static String passE() {
        return System.getProperty("pass");
    }
    public void auth(){
//        System.setProperty("login", "dev.loop1@gmail.com");
//        System.setProperty("pass", "12345");
        final String buttonReg = ".header2__auth";
        final String loginWindow = "div.new-input-line_slim:nth-child(3) > input:nth-child(1)";
        final String passWindow = ".js-psw-input";
        final String buttonEnter = "div.new-input-line_last:nth-child(5) > button:nth-child(1)";
        System.out.println(loginE()+ " "+passE());
        driver.findElement(By.cssSelector(buttonReg)).click();
        driver.findElement(By.cssSelector(loginWindow)).clear();
        driver.findElement(By.cssSelector(loginWindow)).sendKeys(loginE());
        driver.findElement(By.cssSelector(passWindow)).clear();
        driver.findElement(By.cssSelector(passWindow)).sendKeys(passE());
        driver.findElement(By.cssSelector(buttonEnter)).click();
        Assert.assertNotNull(driver.findElement(By.cssSelector(".button__my-course")));
    }
    protected void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }
}
