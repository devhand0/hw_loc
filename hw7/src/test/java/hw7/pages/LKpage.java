package hw7.pages;

import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LKpage extends BasePage{
    public LKpage(WebDriver driver) {
        super(driver);
    }
    final By nameLoc = By.cssSelector("#id_fname");
    final By nameLatinLoc = By.cssSelector("#id_fname_latin");
    final By surnameLoc = By.cssSelector("#id_lname");
    final By surnameLatinLoc = By.cssSelector("#id_lname_latin");
    final By nicknameLoc = By.cssSelector("#id_blog_name");
    final By birthdayLoc = By.cssSelector(".input-icon > input:nth-child(1)");
    final By countryLoc = By.cssSelector(".js-lk-cv-dependent-master");
    final By cityLoc = By.cssSelector(".js-lk-cv-dependent-slave-city");
    final By engLoc = By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)");
    final String name ="Кирилл";

    public static void myAssertEquals(Object expected, Object actual) {
        try {
            Assert.assertEquals(expected, actual );
        } catch (ComparisonFailure e) {
            logger.error("Ошибка");
            Assert.fail("Ошибка");
        }
        logger.info("ОК");
    }
    public void checkPersonal() {


/////////////////////////////////////
        logger.info("Проверка имени");
        myAssertEquals(cfg.name(), driver.findElement(nameLoc).getAttribute("value"));
        logger.info("Проверка name");
        myAssertEquals(cfg.nameLatin(), driver.findElement(nameLatinLoc).getAttribute("value"));
        logger.info("Проверка фимилии");
        myAssertEquals(cfg.surname(), driver.findElement(surnameLoc).getAttribute("value"));
        logger.info("Проверка surname");
        myAssertEquals(cfg.surnameLatin(), driver.findElement(surnameLatinLoc).getAttribute("value"));
        logger.info("Проверка nickname");
        myAssertEquals(cfg.nickname(), driver.findElement(nicknameLoc).getAttribute("value"));
        logger.info("Проверка birthday ");
        myAssertEquals(cfg.birthday(), driver.findElement(birthdayLoc).getAttribute("value"));
        logger.info("Проверка страны");
        myAssertEquals("Россия", driver.findElement(countryLoc).getText());
        logger.info("Проверка города");
        myAssertEquals("Москва", driver.findElement(cityLoc).getText());
        logger.info("Проверка уровня английского");
        myAssertEquals("Средний (Intermediate)", driver.findElement(engLoc).getText());
        logger.info("Проверка типа контакта, выбран Skype");
        myAssertEquals("Skype", driver.findElement(By.cssSelector("div.js-formset-row:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div")).getText());
        logger.info("Проверка значения контакта, выбран Skype");
        myAssertEquals(cfg.skype(), driver.findElement(By.cssSelector("#id_contact-0-value")).getAttribute("value"));
        logger.info("Проверка типа контакта, выбран Тelegram");
        myAssertEquals("Тelegram", driver.findElement(By.cssSelector("div.js-formset-row:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).getText());
        logger.info("Проверка значения контакта, выбран Тelegram");
        myAssertEquals(cfg.telegram(), driver.findElement(By.cssSelector("#id_contact-1-value")).getAttribute("value"));
    }

    public void removeContacts () {            // очистка контактов
            List<WebElement> options = driver.findElements(By.xpath("//*[contains(text(), 'Удалить')]"));
            System.out.println(" CLASS  Elements size " + options.size());
            for (WebElement option : options) {
                if (option.isDisplayed()) {
                    option.click();
                }
            }
        }
        public void modPersonal () {
//        Очистка - ввод текста
            driver.findElement(nameLoc).clear();
            System.out.println(name);
            driver.findElement(nameLoc).sendKeys(cfg.name());
            driver.findElement(nameLatinLoc).clear();
            driver.findElement(nameLatinLoc).sendKeys(cfg.nameLatin());
            driver.findElement(surnameLoc).clear();
            driver.findElement(surnameLoc).sendKeys(cfg.surname());
            driver.findElement(surnameLatinLoc).clear();
            driver.findElement(surnameLatinLoc).sendKeys(cfg.surnameLatin());
            driver.findElement(nicknameLoc).clear();
            driver.findElement(nicknameLoc).sendKeys(cfg.nickname());
            driver.findElement(birthdayLoc).clear();
            driver.findElement(birthdayLoc).sendKeys(cfg.birthday());
//        Списки страна, город, уровень английского
            driver.findElement(countryLoc).click();
            driver.findElement(By.xpath("//*[@title='Россия']")).click();
            waitElement(cityLoc).getText();
            waitElement(cityLoc).click();
            waitElement(By.xpath("//*[@title='Москва']")).click();
            System.out.println(waitElement(cityLoc).getText());
            waitElement(engLoc).click();
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
        }

}


