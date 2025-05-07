package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import static page.MainPage.BASE_URL;

import java.util.Arrays;
import java.util.Collection;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;
import page.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    private final boolean clickTopButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String comment;

    public OrderTest(boolean clickTopButton, String name, String surname, String address,
                     String metro, String phone, String date, String comment) {
        this.clickTopButton = clickTopButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тест заказа через {0}")
    public static Collection<Object[]> getOrderData() {
        return Arrays.asList(new Object[][]{
                {true, "Иван", "Иванов", "ул. Пушкина, д. 1", "Черкизовская", "89998887766", "02.05.2025", "Позвонить за час"},
                {false, "Анна", "Петрова", "ул. Ленина, д. 10", "Сокольники", "89997776655", "03.05.2025", "Без звонка"}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(MainPage.BASE_URL);
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testOrder() {
        if (clickTopButton) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        orderPage.fillOrderForm(name, surname, address, metro, phone, date, comment);
        orderPage.submitOrder();

        String actualConfirmationText = orderPage.getOrderConfirmationText();

        System.out.println("Actual confirmation text: " + actualConfirmationText);

        assertEquals("Заказ должен быть оформлен", "Заказ оформлен", actualConfirmationText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
