package PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    // Нижняя кнопка заказа
    private By bottomOrderButton = By.className("Button_Button__ra12g Button_Middle__1CSJM");
    // Верхняя кнопка закакза
    private By topOrderButton = By.className("Button_Button__ra12g");

    // Тест "Вопросы о важном"
    private By textImpotientQuestions = By.className("Home_FAQ__3uVm4");

    // Заголовок формы ввода
    private By formHeader = By.className("Order_Header__BZXOb");

    private By nameField = By.cssSelector(".Order_Form__17u6u > div:nth-child(1) > input:nth-child(1)");
    // Фамилия
    private By familyField = By.cssSelector("div.Input_InputContainer__3NykH:nth-child(2) > input:nth-child(1)");
    // Адрес
    private By addressField = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Form__17u6u > div:nth-child(3) > input");
    // Метро
    private By metroField = By.className("select-search__input");
    private By metroSelect = By.className("select-search__select");
    // Телефон
    private By phoneField = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Form__17u6u > div:nth-child(5) > input");
    // Кнопка "Далее" в форме информации о заказчике
    private By customerButtonNext = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_NextButton__1_rCA > button");
    // Поле ввода даты заказа
    private By dateField = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Form__17u6u > div.Order_MixedDatePicker__3qiay > div.react-datepicker-wrapper > div > input");
    // Поле ввода срока аренды
    private By periodField = By.className("Dropdown-control");
    private By periodItem2 = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[1]");
    // Чекбокс цвет черный
    private By blackCheckBox = By.id("black");
    // Чекбокс цвет серая безысходность
    private By greyCheckBox = By.id("grey");
    // Комментарий курьеру
    private By commentField = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Form__17u6u > div.Input_InputContainer__3NykH > input");
    // Кнопка Заказать
    private By orderButton = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Buttons__1xGrp > button:nth-child(2)");
    // Окно подтверждения заказа
    private By orderConfirm = By.className("Order_Modal__YZ-d3");
    // Кнопка подтверждения заказа
    private By orderConfirmButton = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Modal__YZ-d3 > div.Order_Buttons__1xGrp > button:nth-child(2)");
    //
    private By popupHeader = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickQuestionElement(String text) {
        driver.findElement(By.xpath("//*[contains(text(), '"+text+"')]")).click();
    }
    public void waitForAnswerText(String text) {
        By element = By.xpath("//*[contains(text(), '" + text + "')]");
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.textToBePresentInElementLocated(element, text)
        );
    }

    public void scrollToQuestions() {
        WebElement element = driver.findElement(textImpotientQuestions);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickBottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setFamily(String family) {
        driver.findElement(familyField).sendKeys(family);
    }
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    public void setMetro(String metro) {
        driver.findElement(metroField).click();
        driver.findElement(metroField).sendKeys(metro);
        driver.findElement(metroSelect).click();
    }
    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void clickCustomerButtonNext() {
        driver.findElement(customerButtonNext).click();
    }

    public void insertCustomerInfo(String name, String family, String address, String metro, String phone) {
        setName(name);
        setFamily(family);
        setAddress(address);
        setMetro(metro);
        setName(name);
        setPhone(phone);
        clickCustomerButtonNext();
    }

    public void setDate(String date) {
        driver.findElement(dateField).click();
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateField).sendKeys(Keys.RETURN);
    }
    public void setPeriod(String period) {
        driver.findElement(periodField).click();
        driver.findElement(periodItem2).click();
    }
    public void setBlackCheckBox() {
        driver.findElement(blackCheckBox).click();
    }
    public void setGreyCheckBox() {
        driver.findElement(greyCheckBox).click();
    }
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    public void clickOrder() {
        driver.findElement(orderButton).click();
    }

    public void insertOrderDetails(String date, String period, String comment) {
        setDate(date);
        setPeriod(period);
        setBlackCheckBox();
        setGreyCheckBox();
        setComment(comment);
        clickOrder();
    }
    public void waitForBottomOrderButton() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(bottomOrderButton)
        );
    }
    public void waitForTopOrderButton() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(topOrderButton)
        );
    }

    public void waitForOrderDetails(String header) {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.textToBePresentInElementLocated(formHeader, header)
        );
    }
    public void waitForConfirmPopup(String headerText) {
        new WebDriverWait(driver, 10).until(
                driver -> (driver.findElement(orderConfirm) != null
                        && driver.findElement(popupHeader).getText() == headerText
                ));
    }

    public void confirmOrder() {
        driver.findElement(orderConfirmButton).click();
    }
}
