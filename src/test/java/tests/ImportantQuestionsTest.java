package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest {
    private WebDriver driver;
    private MainPage mainPage;

    private final int questionIndex;
    private final String expectedAnswerPart;

    public ImportantQuestionsTest(int questionIndex, String expectedAnswerPart) {
        this.questionIndex = questionIndex;
        this.expectedAnswerPart = expectedAnswerPart;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> faqData() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(MainPage.BASE_URL);
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testFaqAnswerContent() {
        mainPage.clickFaqQuestion(questionIndex);
        String actualAnswer = mainPage.getFaqAnswer(questionIndex);
        assertTrue("Ответ должен содержать ожидаемый текст", actualAnswer.contains(expectedAnswerPart));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
