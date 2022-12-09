package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
    private WebDriver driver;
    // Вопросы о важном
    private By questions = By.className("accordion");
    // Кнопка "Заказать" сверху
    private By ButtonOne = By.className("Button_Button__ra12g");
   // Кнопка "Заказать" снизу
    private By ButtonTwo = By.className("Button_Button__ra12g Button_Middle__1CSJM");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickQuestions() {
        driver.findElement(questions).click();
    }
    public void clickButtonOne() {
        driver.findElement(ButtonOne).click();
    }
    public void clickButtonTwo() {
        driver.findElement(ButtonTwo).click();
    }
    public void zakaz() {
        clickQuestions();
        clickButtonOne();
        clickButtonTwo();
    }
}
