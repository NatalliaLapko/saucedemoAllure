package com.company;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class RegistrationPage {
    public static SelenideElement CONTINUE_BUTTON = $("#continue");
    public static SelenideElement FIRSTNAME_FORM = $("input#first-name");
    public static SelenideElement LASTNAME_FORM = $("input#last-name");
    public static SelenideElement ZIP_CODE = $("input#postal-code");

    public static SelenideElement FINISH_BUTTON = $("#finish");
    public static SelenideElement CANCEL_BUTTON = $("button#cancel");

    public static SelenideElement ITEM_TOTAL = $("div.summary_subtotal_label");
    public static SelenideElement TAX = $("div.summary_tax_label");
    public static SelenideElement TOTAL = $("div.summary_total_label");


    //Filling registration form
    @Step("Set first name in the FirstName field in the Registration form")
    public void setFirstName(String firstName) {
        FIRSTNAME_FORM.sendKeys(firstName);
    }

    @Step("Set last name in the LastName field in the Registration form")
    public void setLastnameName(String lastName) {
        LASTNAME_FORM.sendKeys(lastName);
    }

    @Step("Set zip-code in the Zip-code field in the Registration form")
    public void setZipCode(String zipCode) {
        ZIP_CODE.sendKeys(zipCode);
    }

    @Step("Click Registration btn")
    public void registration() {
        CONTINUE_BUTTON.click();

    }

    //Cancelling order
    @Step("Click Finish btn")
    public void finish() {
        FINISH_BUTTON.click();
    }

    @Step("Click Cancel btn")
    public void cancel() {
        CANCEL_BUTTON.click();
    }






    //Calculating and comparing total sum
    @Step(value = "Get value from field 'Total Items price' from site")
    public double getTotalItem() {
        String totalItem = ITEM_TOTAL.getText().substring(ITEM_TOTAL.getText().lastIndexOf("$") + 1);
        return Double.parseDouble(totalItem);

    }

    @Step(value = "\"Get value from field 'Tax' from site\"")
    public static double getTax() {
        String tax = TAX.getText().substring(TAX.getText().lastIndexOf("$") + 1);
        return Double.parseDouble(tax);
    }

    @Step(value = "\"Get value from field 'Total Price' from site\"")
    public double getTotalSum() {
        String total = TOTAL.getText().substring(TOTAL.getText().lastIndexOf("$") + 1);
        return Double.parseDouble(total);
    }
    double totalItemSum = 0;
    double itemPrice = 0;
    double realSum = 0;
    @Step("Calculate Real TotalSum ")
    public void sumCalculation() {
        ElementsCollection cartItemsCollection = CartPage.FINAL_LIST.$$(By.cssSelector("div.cart_item_label"));
        int colSize = cartItemsCollection.size();

        for (int i = 0; i < colSize; i++) {
            String temp = cartItemsCollection.get(i).$(By.cssSelector("div.inventory_item_price")).getText();
            temp = temp.substring(1);
            itemPrice = Double.parseDouble(temp);
            totalItemSum = totalItemSum + itemPrice;

        }


        realSum = totalItemSum + getTax();


        System.out.println("Total Item = " + totalItemSum);
        System.out.println("Tax = " + getTax());
        System.out.println("Total Sum = " + realSum);
        System.out.println("RealSum = " + getTotalSum() + "\n" + "ExpectedSum = " + realSum);

    }


    @Step(value = "Fill Registration Form")
public void fillRegistrationForm(){
    setFirstName("Default");
    setLastnameName("User");
    setZipCode("111111");
}
@Step(value = "Get calculated Total Items Sum")
    public double getTotalItemSum() {
        return totalItemSum;
    }

    @Step(value = "Get real Final Sum")
    public double getExpectedSum(){
        return realSum;
    }
    }






