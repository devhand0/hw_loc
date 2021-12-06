package hw7.pages;

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
    final By cityLoc = By.cssSelector(".js-lk-cv-dependent-slave-city>label>div");
    final By engLoc = By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)");
    final String name ="Кирилл";
    final String nameLatin ="Kirill";
    final String surname ="Антонов";
    final String surnameLatin ="Antonov";
    final String nickname ="Dev.Loop1";
    final String birthday ="18.12.1987";
    final String telegram ="telegram_nick";
    final String skype ="skype_nick";


    public void removeContacts(){            // очистка контактов
        List<WebElement> options = driver.findElements(By.xpath("//*[contains(text(), 'Удалить')]"));
        System.out.println(" CLASS  Elements size " + options.size());
        for (WebElement option : options){
            if(option.isDisplayed()){
                option.click();
            }
        }
    }
    public void modPersonal()  {
//        Очистка - ввод текста
        driver.findElement(nameLoc).clear();
        System.out.println(name);
        driver.findElement(nameLoc).sendKeys(name);
        driver.findElement(nameLatinLoc).clear();
        driver.findElement(nameLatinLoc).sendKeys(nameLatin);
        driver.findElement(surnameLoc).clear();
        driver.findElement(surnameLoc).sendKeys(surname);
        driver.findElement(surnameLatinLoc).clear();
        driver.findElement(surnameLatinLoc).sendKeys(surnameLatin);
        driver.findElement(nicknameLoc).clear();
        driver.findElement(nicknameLoc).sendKeys(nickname);
        driver.findElement(birthdayLoc).clear();
        driver.findElement(birthdayLoc).sendKeys(birthday);
//        Списки страна, город, уровень английского
        driver.findElement(countryLoc).click();
        waitElement(By.xpath("//*[@title='Россия']")).click();
//        System.out.println(driver.findElement((By.cssSelector(countryLoc))).getText());
        waitElement(cityLoc).click();
//        waitElement(By.cssSelector("body > div.body-wrapper > div > div.js-lk-cv > div.container.container-padding-bottom > div.container__row > div.container__col.container__col_9.container__col_md-8.container__col_sm-12.container__col_border-left.lk-rightbar.print-block.print-wide > div > form > div:nth-child(2) > div.container__col.container__col_12 > div:nth-child(1) > div > div.container__col.container__col_9.container__col_ssm-12 > div:nth-child(2) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")).click();
        waitElement(By.xpath("//*[@title='Москва']")).click();
        System.out.println(waitElement(cityLoc).getText());
//        driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).click();
        waitElement(engLoc);
        waitElement(By.xpath("//*[@title='Средний (Intermediate)']")).click();
        System.out.println(waitElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).getText());
//   Добавление контактов
// Telegramm
        driver.findElement(By.xpath("//*[contains(text(), 'Добавить')]")).click();
//        Thread.sleep(3000);
//        waitElement(By.cssSelector("span.placeholder")).click();
        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>div.select>label")).click();
//        Thread.sleep(3000);
        //System.out.println(waitElement(By.cssSelector("div.js-formset-row:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div")).getText());
//        Thread.sleep(3000);
//        waitElement(By.xpath("//*[contains(text(), 'Тelegram')]")).click();
        driver.findElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>div.select>div>div>button[data-value='telegram']")).click();
//        Thread.sleep(3000);
        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>input")).clear();
        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>input")).sendKeys(telegram);
//  Skype
        driver.findElement(By.xpath("//*[contains(text(), 'Добавить')]")).click();
        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>div.select>label")).click();
//        Thread.sleep(3000);
        //System.out.println(waitElement(By.cssSelector("div.js-formset-row:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div")).getText());
//        Thread.sleep(3000);
//        waitElement(By.xpath("//*[contains(text(), 'Тelegram')]")).click();
        driver.findElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>div.select>div>div>button[data-value='skype']")).click();
//        Thread.sleep(3000);
        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>input")).clear();
        waitElement(By.cssSelector("div.js-formset-row:last-child>div>div>div>input")).sendKeys(skype);
        //    Сохранить продолжить
        driver.findElement(By.xpath("//*[contains(text(), 'Сохранить и продолжить')]")).click();
        //    Thread.sleep(15000);
    }
}

