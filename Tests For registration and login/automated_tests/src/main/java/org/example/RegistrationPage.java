package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "firstname")
    WebElement inputFirstname;

    @FindBy(id = "lastname")
    WebElement inputLastname;

    @FindBy(id = "email_address")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(id = "password-confirmation")
    WebElement inputPasswordConfirmation;

    @FindBy(css = "button[title='Create an Account']")
    WebElement buttonCreateAnAccount;


    public Boolean displayedForm(){
        return inputFirstname.isDisplayed() &&
                inputLastname.isDisplayed() &&
                inputEmail.isDisplayed() &&
                inputPassword.isDisplayed() &&
                inputPasswordConfirmation.isDisplayed();
    }

    public void sendRegistrationData(String name,String lastname,String email, String password,
                                     String confirmPassword){

        inputFirstname.sendKeys(name);
        inputLastname.sendKeys(lastname);
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        inputPasswordConfirmation.sendKeys(confirmPassword);

        buttonCreateAnAccount.click();

    }

}
