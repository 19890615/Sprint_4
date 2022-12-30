import PageObject.OrderPage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class MakeOrder {

    private WebDriver driver;
    private final String name;
    private final String family;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String comment;

    public MakeOrder(String name, String family, String address, String metro, String phone,
                            String date, String period, String comment) {
        this.name = name;
        this.family = family;
        this.address = address;
        this.metro = metro;
        this.phone = phone;

        this.date = date;
        this.period = period;
        this.comment = comment;

    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getBlabla() {
        return new Object[][] {
                { "Иван", "Иванов", "ввв Ленинград", "Печатники", "79998887766", "09.12.2022", "трое суток", "спасибо!"},
                { "Петька", "Петров", "спб точка ру", "Дубровка", "71234567890", "01.01.2023", "сутки", "вход со двора"},
        };
    }

    @Test
    public void Order() {
        // Запускаем браузер, переходим на сайт
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Создадим объект страницы заказа
        OrderPage objOrderPage = new OrderPage(driver);

        // Дождаться появления верхней кнопки заказа
        objOrderPage.waitForTopOrderButton();

        // Нажать на кнопку заказа снизу
        objOrderPage.clickTopOrderButton();

        // Дождаться появления формы ввода персональных данных
        objOrderPage.waitForOrderDetails("Для кого самокат");

        // Заполняем форму о заказчике
        objOrderPage.insertCustomerInfo(name, family, address, metro, phone);

        // Дождемся загрузки формы деталей аренды
        objOrderPage.waitForOrderDetails("Про аренду");

        // Заполнение формы деталей аренды + заказать
        objOrderPage.insertOrderDetails(date, period, comment);

        // Дождаться появления вскрывающего окна с текстом Хотите оформить заказ?
        objOrderPage.waitForConfirmPopup("Хотите оформить заказ?");

        // Подтвердить заказ
        objOrderPage.confirmOrder();

        // Убедиться в появления всплывающего окна с текстом Заказ оформлен
        objOrderPage.waitForConfirmPopup("Заказ оформлен");

    }

    @After
    public void teardown() {
        driver.quit();
    }

}
