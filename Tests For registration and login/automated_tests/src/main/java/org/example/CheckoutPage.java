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

    @FindBy(id="finish")
    WebElement buttonFinish;

    @FindBy(css="[data-test='subtotal-label']")
    WebElement divPriceTotal;

    By divOrderComplete = By.cssSelector("[data-test='complete-text']");

    By buttonErrorMessage = By.cssSelector("[data-test='error']");

    public void enterInformationCredentials(String firstName, String lastName, String postalCode){
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputPostalCode.sendKeys(postalCode);

    }

    public void clickSubmit(){
        inputSubmit.click();
    }

    public void clickFinish() { buttonFinish.click();}

    public boolean isErrorMessageDisplayed(){
        return !driver.findElements(buttonErrorMessage).isEmpty();
    }

    public boolean isOrderCompletedMessageDisplayed(){
        return !driver.findElements(divOrderComplete).isEmpty();
    }

    public String errorMessage(){
        return driver.findElement(buttonErrorMessage).getText();
    }

    public double totalPriceSum(){
        String text = divPriceTotal.getText().replace("Item total:","")
                .replace("$", "")
                .trim();
        return Double.parseDouble(text);
    }
}
