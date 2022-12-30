import PageObject.OrderPage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class PopupMenu {

    private WebDriver driver;
    private final String menuItem;
    private final String menuText;

    public PopupMenu(String menuItem, String menuText) {
        this.menuItem = menuItem;
        this.menuText = menuText;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getBlabla() {
        return new Object[][] {
                { "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
        };
    }

    @Test
    public void PushMenu() {
        // Запускаем браузер, переходим на сайт
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Создадим объект страницы заказа
        OrderPage objOrderPage = new OrderPage(driver);

        // Дождаться появления текста "Вопросы о важном"
        objOrderPage.scrollToQuestions();

        // Нажать на стрелку
        objOrderPage.clickQuestionElement(menuItem);

        // Убедиться в появлении текста
        objOrderPage.waitForAnswerText(menuText);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
