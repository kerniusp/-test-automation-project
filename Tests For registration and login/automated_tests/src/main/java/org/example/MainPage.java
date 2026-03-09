package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="shopping_cart_container")
    WebElement divShoppingCart;

    @FindBy(css="[data-test='inventory-item-price']")
    List<WebElement> divInventoryItemsPrices;

    @FindBy(css="[data-test='product-sort-container']")
    WebElement spanFilter;

    @FindBy(css="option[value='lohi']")
    WebElement optionLowToHigh;

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

    public List<Double> allItemPrices(){

        List<Double> items = new ArrayList<>();
        for(WebElement item : divInventoryItemsPrices){
           items.add(Double.parseDouble(item.getText().replace("$","").trim())) ;
        }

        return items;
    }

    public void clickOnFilter(){
        spanFilter.click();
    }

    public void clickFromLowToHighOption(){
        optionLowToHigh.click();
    }


}
