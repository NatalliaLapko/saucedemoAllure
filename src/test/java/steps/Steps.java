package steps;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.ElementsCollection;
import com.company.CartPage;
import com.company.Links;
import com.company.LoginPage;
import com.codeborne.selenide.Condition;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;

public class Steps {

    @Допустим("^открыта страница \"([^\"]*)\"$")
    public void openPage(String pageURL) {
        open(pageURL);
        getWebDriver().manage().window().maximize();
        String currentPageURL = getWebDriver().getCurrentUrl();
        assertEquals(pageURL, currentPageURL, "Значения ссылок не сопадают");

    }



    @Допустим("на странице присутствует форма логина")
    public void isLoginFormExists() {
        $(LoginPage.USERNAME_FIELD).should(Condition.exist);
        $(LoginPage.PASSWORD_FIELD).should(Condition.exist);
        $(LoginPage.LOGIN_BUTTON).shouldBe(Condition.visible);

    }


@Допустим("^пользователь вводит в поле Username логин \"([^\"]*)\"$")
    public void fillLoginField1 (String login) {
        $(LoginPage.USERNAME_FIELD).setValue(login);

    }

    @Допустим("^пользователь вводит в поле Password пароль \"([^\"]*)\"$")
    public void fillPasswordField(String password) {
        $(LoginPage.PASSWORD_FIELD).setValue(password);
    }

    @Допустим("^пользователь нажимает кнопку \"([^\"]*)\"$")
    public void clickLoginButton(String string) {
        $(LoginPage.LOGIN_BUTTON).click();
    }

    @Тогда("^открыт профиль пользователя \"([^\"]*)\"$")
    public void isUserAccountOpened(String string) {
        Assertions.assertEquals(Links.loginPage, url(), "Wrong page!");
    }


    @Допустим("все поля формы пусты")
    public void isAllFieldsAreEmpty() {
    $(LoginPage.USERNAME_FIELD).shouldBe(Condition.empty);
    $(LoginPage.PASSWORD_FIELD).shouldBe(Condition.empty);
    }

    @Тогда("^появляется сообщение об ошибке \"([^\"]*)\"$")
    public void warningAppears(String expectedError) {
     $(LoginPage.ERROR_MESSAGE).should(Condition.exist);
     String realError = $(LoginPage.ERROR_MESSAGE).getText();
     Assertions.assertEquals(realError,expectedError);
    }

    @Допустим("^осуществлён вход на страницу \"([^\"]*)\" с помощью логина \"([^\"]*)\" и пароля \"([^\"]*)\"$")
    public void login(String url, String username, String password) {

      $(LoginPage.USERNAME_FIELD).should(Condition.exist).setValue(username);
      $(LoginPage.PASSWORD_FIELD).should(Condition.exist).setValue(password);
      $(LoginPage.LOGIN_BUTTON).shouldBe(Condition.visible).click();
      Assertions.assertEquals(url, url());
    }



    @Допустим("на странице отображён список товаров")
    public void isItemsListExists() {
    $("div.inventory_list").should(Condition.exist);

    }


    @Допустим("^выполнено нажатие на ссылку \"([^\"]*)\"$")
    public void linkTap(String link) {
    $(By.xpath("//div[contains(text(), \"" + link + "\")]")).click();


    }

    @Тогда("^цена товара равна \"([^\"]*)\"$")
    public void sLBackpackPrice(String price) {
        String actualPrice = $("div.inventory_details_price").getText();
        assertEquals(price, actualPrice);


    }


}
