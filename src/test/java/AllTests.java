
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.company.*;
import com.company.Links;
import org.junit.jupiter.api.*;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;


import java.io.IOException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AllTests {

    LoginPage loginPage = new LoginPage();
    CartPage cartPage = new CartPage();
    RegistrationPage registrationPage = new RegistrationPage();
    FinalPage finalPage = new FinalPage();




    @BeforeEach
    @DisplayName("LogIn")

    public void login() throws IOException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        loginPage.openLoginPage();
        loginPage.login();
        assertEquals(Links.loginPage, url(), "Wrong page!");
    }

    @AfterEach
    @DisplayName("LogOut")
    public void logout(){
        loginPage.logout();
    }




    @Feature(value = "CartPage feature")
    @Severity(SeverityLevel.CRITICAL)
    @Description(value = "Successful ordering with correctly entered data")
    @Owner(value = "Natallia Lapko")

    @Test
    @DisplayName("Ordering")
    @Order(2)
    @Link(name = "login Page link", url = "https://www.saucedemo.com/")
    public void purchase() throws IOException {

        cartPage.addToCart();
        cartPage.goToCart();
        cartPage.purchase();
        registrationPage.fillRegistrationForm();
        registrationPage.registration();
        registrationPage.finish();
        finalPage.currentURL();
        String notice = finalPage.getFinalNotice();
        finalPage.getScreen();

        Assertions.assertAll(
                () -> assertEquals(Links.checkoutComplete, url(), "Wrong page!"),
                () -> assertEquals("THANK YOU FOR YOUR ORDER", notice, "Wrong message!")
        );



    }

    @Feature(value = "RegistrationPage feature")
    @Owner(value = "Natallia Lapko")
    @Severity(SeverityLevel.NORMAL)
    @Description(value = "Order cancellation")

    @Test
    @DisplayName("Order cancellation")
    @Order(3)
    public void orderCancellation()  {

        cartPage.goToCart();
        cartPage.purchase();
        registrationPage.fillRegistrationForm();
        registrationPage.registration();
        registrationPage.cancel();

        assertEquals(Links.inventory, url());

    }

    @Feature(value = "CartPage feature")
    @Severity(SeverityLevel.NORMAL)
    @Description(value = "Removing all the items from the checkout list")

    @Test
    @DisplayName("Items Removing")
    @Order(1)
    public void removeItemsFromCart()  {

        cartPage.addToCart();
        cartPage.goToCart();
        cartPage.removeItem();

       assertEquals( 0, cartPage.getColSize());
    }


    @Flaky
    @Feature(value = "RegistrationPage feature")
    @Link(name = "login Page link", url = "https://www.saucedemo.com/")
    @Severity(SeverityLevel.CRITICAL)
    @Description(value = "Checking the correctness of the calculation of the total amount of items in the cart and the total amount indicated on the site")

    @Test
    @DisplayName("Total Sum Calculation")
    @Order(4)
    public void totalItems() {

        cartPage.addToCart();
        cartPage.goToCart();
        cartPage.purchase();
        registrationPage.fillRegistrationForm();
        registrationPage.registration();
        registrationPage.sumCalculation();

        Assertions.assertAll(
                () -> assertEquals(registrationPage.getTotalItem(), registrationPage.getTotalItemSum()),
                () -> assertEquals(registrationPage.getTotalSum(), registrationPage.getExpectedSum())
        );




    }
}















