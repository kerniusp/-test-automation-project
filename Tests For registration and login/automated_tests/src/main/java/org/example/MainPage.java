package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver){
        super(driver);
    }


    @FindBy(css = ".header.panel > .header.links > li:nth-of-type(3) > a")
    WebElement hrefCreateAnAccount;

    @FindBy(css = ".header.panel > .header.links > .authorization-link > a")
    WebElement hrefSignIn;

    public void clickCreateAnAccount(){
        hrefCreateAnAccount.click();
    }

    public void clickSignIn(){
        hrefSignIn.click();
    }
}
