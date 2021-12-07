import hw7.pages.LKpage;
import hw7.pages.MainPage;
import org.junit.*;

public class HW7run extends BaseTest {
//
//    В результате выполнения дз вы реализуете автоматический тест, используя Java + Selenium
//
//    Создайте класс WebDriverFactory со статическим методом create();
//
//    Метод create() принимает обязательный параметр webDriverName и необязтельный параметр options, а возвращает соответствующий имени вебдрайвер с заданными (если были) options
//
//    Примеры использования
//    WebDriver wd = WebDriverFactory.createNewDriver("chrome");
//    или
//    FirefoxOptions options = new FirefoxOptions();
//    WebDriver wd = WebDriverFactory.createNewDriver("firefox", options);
//
//    Шаги теста:
//
//    Открыть https://otus.ru
//    Авторизоваться на сайте
//    Войти в личный кабинет
//    В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
//    Нажать сохранить
//    Открыть https://otus.ru в "чистом браузере"
//    Авторизоваться на сайе
//    Войти в личный кабинет
//    Проверить, что в разделе "О себе" отображаются указанные ранее данные
//    Критерии оценки:
//            +1 балл - код компилируется и запускается
//+1 балл - код запускается без дополнительных действий со стороны проверяющего (не нужно скачивать WebDriver, решать конфликты зависимостей и т.п.)
//+1 балл - логин/пароль для авторизации не зашиты в код (передаются как параметры при старте)
//+1 балл - логи пишутся в консоль и файл
//+1 балл - тест проходит без падений (допускается падение теса только при некорректной работе SUT)
//+1 балла - реализован паттерн PageObject
//+1 балл - проект компилируется и собирается
//+1 балл - в репозитории нет лишних файлов (.iml, директория idea и т.д.)
//+1 балл - регистр значения параметра -Dbrowser не влияет на результат
//+1 балл - для хранения имен драйверов используются Enum
    @Test

    public void any() throws InterruptedException {
    logger.info("Тест начат");
    MainPage mainPage = new MainPage(driver);
    mainPage.open().auth().goToPersonal();
    LKpage lKpage = new LKpage(driver);
    logger.info("Заполнение");
    lKpage.removeContacts();
    lKpage.modPersonal();
//    Thread.sleep(3000);
        driver.quit();
        logger.info("Заполнение закончено успешно");

    setUp();
    MainPage mainPage1 = new MainPage(driver);
    mainPage1.open().auth().goToPersonal();
//    mainPage1.auth().goToPersonal();
    lKpage.checkPersonal();
    end();
    }



}



