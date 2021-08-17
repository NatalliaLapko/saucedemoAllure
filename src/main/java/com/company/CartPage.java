package com.company;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$;


public class CartPage {

    public static SelenideElement CART_BUTTON = $("a.shopping_cart_link");
    public static SelenideElement ITEMS_LIST = $("div.inventory_list");
    public static SelenideElement CHECKOUT_BUTTON = $("#checkout");
    public static SelenideElement CART_LIST = $("div#cart_contents_container");
    public static SelenideElement FINAL_LIST = $("div#checkout_summary_container");


@Step("Go to cart")
    public void goToCart(){
        CART_BUTTON.click();
    }

    @Step("Add to cart")
    public  void addToCart() {

        ElementsCollection itemsCollection = CartPage.ITEMS_LIST.$$(By.cssSelector("div.inventory_item_description"));
        int itemsCollectionSize = itemsCollection.size();


        for (int i = 0;i <itemsCollectionSize; i++) {
            itemsCollection.get(i).$((By.xpath(".//button[text()='Add to cart']"))).click();
        }
    }

@Step("click checkout btn")
    public void purchase () {
        CHECKOUT_BUTTON.click();
    }



    //Removing items
    int colSize =0;

    @Step("Remove all items from cart")
    public void removeItem() {
        ElementsCollection cartItemsCollection = CartPage.CART_LIST.$$(By.cssSelector("div.cart_item"));
        colSize = cartItemsCollection.size();
        for (int i = 0; i < colSize; i++) {
            cartItemsCollection.first().$((By.xpath(".//button[text()='Remove']"))).click();
        }

        colSize = cartItemsCollection.size();

    }


@Step(value = "Get the number of items in the cart")
    public int getColSize(){
        return colSize;
    }

}