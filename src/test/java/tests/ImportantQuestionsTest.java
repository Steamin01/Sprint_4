package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;

public class ImportantQuestionsTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testFaqQuestions() {
        for (int i = 0; i < 8; i++) {
            mainPage.clickFaqQuestion(i);
            String answer = mainPage.getFaqAnswer(i);
            assertFalse("Ответ на вопрос " + i + " не должен быть пустым", answer.isEmpty());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
