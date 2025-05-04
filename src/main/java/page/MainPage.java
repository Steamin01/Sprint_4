package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    private By cookieBanner = By.className("App_CookieButton__3cvqF");
    private By orderButtonTop = By.xpath("//button[text()='Заказать']");
    private By orderButtonBottom = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
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
        driver.findElement(By.id("accordion__heading-" + index)).click();
    }

    public String getFaqAnswer(int index) {
        return driver.findElement(By.id("accordion__panel-" + index)).getText();
    }
}
