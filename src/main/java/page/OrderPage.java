package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.className("select-search__input");
    private final By metroOption = By.className("select-search__option");
    private final By phoneInput = By.xpath("//input[contains(@placeholder, 'Телефон')]");
    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By pageHeader = By.className("Order_Header__BZXOb");
    private final By rentalPeriodDropdown = By.className("Dropdown-control");
    private final By rentalPeriodOption = By.xpath("//div[@class='Dropdown-menu']/div[text()='сутки']");
    private final By blackColorCheckbox = By.id("black");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By confirmYesButton = By.xpath("//button[text()='Да']");
    private final By orderConfirmation = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void setName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void selectMetro() {
        driver.findElement(metroInput).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroOption)).click();
    }

    public void setPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void setDate(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput)).sendKeys(date);
        driver.findElement(pageHeader).click(); // Закрыть календарь
    }

    public void selectRentalPeriod() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
        dropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption)).click();
    }

    public void chooseBlackColor() {
        driver.findElement(blackColorCheckbox).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void submitOrder() {
        driver.findElement(orderButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton)).click();
    }

    public String getOrderConfirmationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation)).getText();
    }

    public void fillOrderForm(String name, String surname, String address, String metro, String phone, String date, String comment) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        selectMetro();
        setPhone(phone);
        clickNext();
        setDate(date);
        selectRentalPeriod();
        chooseBlackColor();
        setComment(comment);
    }
}