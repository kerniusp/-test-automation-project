package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="first-name")
    WebElement inputFirstName;

    @FindBy(id="last-name")
    WebElement inputLastName;

    @FindBy(id="postal-code")
    WebElement inputPostalCode;

    @FindBy(id="continue")
    WebElement inputSubmit;

    By buttonErrorMessage = By.cssSelector("[data-test='error']");

    public void enterInformationCredentials(String firstName, String lastName, String postalCode){
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputPostalCode.sendKeys(postalCode);

    }

    public void clickSubmit(){
        inputSubmit.click();
    }

    public boolean isErrorMessageDisplayed(){
        return !driver.findElements(buttonErrorMessage).isEmpty();
    }

    public String errorMessage(){
        return driver.findElement(buttonErrorMessage).getText();
    }
}
