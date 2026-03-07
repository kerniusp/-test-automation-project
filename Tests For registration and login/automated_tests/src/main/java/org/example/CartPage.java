package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "[data-test='inventory-item-name']")
    List<WebElement> divInventoryName;

    @FindBy(id="checkout")
    WebElement buttonSubmit;

    public List<String> inventoryNames(){
        List<String> allNames = new ArrayList<>();

        for (WebElement item : divInventoryName){
            allNames.add(item.getText());
        }
        return allNames;
    }

    public void clickSubmit(){
        buttonSubmit.click();
    }
}
