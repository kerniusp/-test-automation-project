package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id ="user-name")
    WebElement inputUserName;

    @FindBy(id ="password")
    WebElement inputPassword;

    @FindBy(id ="login-button")
    WebElement buttonLogin;

    @FindBy(css = "div.error-message-container.error > h3")
    WebElement errorMessage;

    public void loginIntoWebsite(String username, String password){

        inputUserName.sendKeys(username);
        inputPassword.sendKeys(password);

        buttonLogin.click();
    }

    public String getErrorMessageText(){
        return errorMessage.getText();
    }
}
