package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public static String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    // Локаторы
    private final By cookieBanner = By.className("App_CookieButton__3cvqF");
    private final By orderButtonTop = By.xpath("//button[text()='Заказать']");
    private final By orderButtonBottom = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");
    private final String faqQuestionIdTemplate = "accordion__heading-";
    private final String faqAnswerIdTemplate = "accordion__panel-";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void acceptCookies() {
        if (driver.findElements(cookieBanner).size() > 0) {
            driver.findElement(cookieBanner).click();
        }
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void clickFaqQuestion(int index) {
        By questionLocator = By.id(faqQuestionIdTemplate + index);
        WebElement question = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
    }

    public String getFaqAnswer(int index) {
        By answerLocator = By.id(faqAnswerIdTemplate + index);
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        wait.until(driver -> !driver.findElement(answerLocator).getText().trim().isEmpty());
        return driver.findElement(answerLocator).getText();
    }
}
