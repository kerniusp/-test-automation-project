import org.example.CartPage;
import org.example.LoginPage;
import org.example.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartShoppingTest extends BaseTest {

    MainPage mainPage;
    LoginPage loginPage;
    CartPage cartPage;

    String[] items = {
            "sauce-labs-backpack",
            "sauce-labs-bike-light",
            "sauce-labs-bolt-t-shirt"
    };

    @BeforeEach
    void login() {

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);

        loginPage.loginIntoWebsite("standard_user","secret_sauce");
    }



    @Test
    public void addingItemToCart(){

        mainPage.addItemToCart(items[0]);
        assertTrue(mainPage.isShoppingCartCountDisplayed());
        assertEquals(1,mainPage.getShoppingCartsCount(),
                "Shopping cart should have one item");
    }

    @Test
    public void removingItemFromCart(){

        mainPage.addItemToCart(items[0]);
        mainPage.removeItemFromCart(items[0]);
        assertFalse(mainPage.isShoppingCartCountDisplayed(),
                "Item cart should be empty");
    }

    @Test
    public void multipleItemsInCart(){

        mainPage.addItemToCart(items[0]);
        mainPage.addItemToCart(items[1]);
        mainPage.addItemToCart(items[2]);
        assertEquals(3,mainPage.getShoppingCartsCount(),
                "Shopping cart should have three items");

        mainPage.pressShoppingCart();


        List<String> inventory = cartPage.inventoryNames();

        for(String expectedItems : items){

            boolean itemExist = false;

            for(String foundItems : inventory){
                if(removeDashesFromString(expectedItems)
                        .equals(removeDashesFromString(foundItems))){
                    itemExist = true;
                    break;
                }
            }
            assertTrue(itemExist, "Item was missing: " + removeDashesFromString(expectedItems));
        }


    }


    public String removeDashesFromString(String word){
        return word.replace("-", " ").toLowerCase();
    }
}
