package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="shopping_cart_container")
    WebElement divShoppingCart;

    By spanShoppingCart = By.cssSelector("[data-test='shopping-cart-badge']");

    public void addItemToCart(String itemName){
        driver.findElement(By.cssSelector("[data-test='add-to-cart-" + itemName + "']")).click();
    }

    public void removeItemFromCart(String itemName){
        driver.findElement(By.cssSelector("[data-test='remove-" + itemName + "']")).click();
    }

    public void pressShoppingCart(){
        divShoppingCart.click();
    }

    public int getShoppingCartsCount(){
        return Integer.parseInt(driver.findElement(spanShoppingCart).getText().trim());
    }

    public boolean isShoppingCartCountDisplayed(){
        return !driver.findElements(spanShoppingCart).isEmpty();
    }

}
