package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{

    public AccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "div[role='alert'] > div > div")
    WebElement divSuccessMessage;

    public String textOfSuccessMessage(){
        return divSuccessMessage.getText();
    }
}
