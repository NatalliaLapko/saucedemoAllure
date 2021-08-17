package com.company;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class FinalPage {

    public static SelenideElement FINAL_NOTICE =$("h2.complete-header");

    @Attachment(value = "Screenshot")
    public static byte[] getBytes(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/resources", fileName));
    }
@Step(value = "Get order confirmation")
public String getFinalNotice() {
    String finalNotice = FINAL_NOTICE.getText();
    return finalNotice;
}

@Step(value = "Get screenshot")
public void getScreen() throws IOException {
    getBytes("successfulOrdering.png");

}

public void currentURL(){
url();
    }
}

