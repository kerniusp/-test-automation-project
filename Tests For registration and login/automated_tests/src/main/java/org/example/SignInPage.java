package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage{

    public SignInPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "pass")
    WebElement inputPassword;

    @FindBy(css = "fieldset button")
    WebElement buttonSignIn;

    public Boolean displayedForm(){
        return inputEmail.isDisplayed() &&
                inputPassword.isDisplayed();

    }

    public void sendSignInData(String email, String password){
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        buttonSignIn.click();
    }
}
