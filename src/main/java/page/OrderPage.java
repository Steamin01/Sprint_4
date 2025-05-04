package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroInput = By.className("select-search__input");
    private By metroOption = By.className("select-search__option");
    private By phoneInput = By.xpath("//input[contains(@placeholder, 'Телефон')]");
    private By nextButton = By.xpath("//button[text()='Далее']");

    private By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By pageHeader = By.className("Order_Header__BZXOb");

    private By rentalPeriodDropdown = By.className("Dropdown-control");
    private By rentalPeriodOption = By.xpath("//div[@class='Dropdown-menu']/div[text()='сутки']");

    private By blackColorCheckbox = By.id("black");
    private By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private By confirmYesButton = By.xpath("//button[text()='Да']");
    private By orderConfirmation = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void fillOrderForm(String name, String surname, String address, String metro, String phone, String date, String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);

        driver.findElement(metroInput).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroOption)).click();

        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput)).sendKeys(date);

        wait.until(ExpectedConditions.elementToBeClickable(pageHeader)).click();

        WebElement rentalDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rentalDropdown);
        wait.until(ExpectedConditions.elementToBeClickable(rentalDropdown)).click();

        WebElement rentalOption = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption));
        rentalOption.click();

        driver.findElement(blackColorCheckbox).click();
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void submitOrder() {
        driver.findElement(orderButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton)).click();
    }

    public boolean isOrderConfirmed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation)).isDisplayed();
    }
}
