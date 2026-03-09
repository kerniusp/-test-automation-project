import com.aventstack.extentreports.ExtentTest;
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

        try {
            loginPage = new LoginPage(driver);
            mainPage = new MainPage(driver);
            cartPage = new CartPage(driver);
            loginPage.loginIntoWebsite("standard_user","secret_sauce");
        } catch (Exception e) {
            ExtentTest test = extent.createTest("Login setup failed");
            test.fail("Login failed during setup: " + e.getMessage());
            throw e;
        }

    }



    @Test
    public void filterByPriceTest(){

        test = extent.createTest("Filtering by price from lowest to highest");

        List<Double>sortedFromLowToHigh = mainPage.allItemPrices().stream()
                .sorted()
                .toList();

        mainPage.clickOnFilter();
        mainPage.clickFromLowToHighOption();

        try{
            assertEquals(sortedFromLowToHigh,mainPage.allItemPrices()
                    ,"Prices are not sorted from low to high");
            test.pass("Prices were sorted correctly");
        }catch(AssertionError e){
            test.fail("Prices were not sorted from lowest to highest");
            throw e;
        }



    }

    @Test
    public void addingItemToCart(){


        test = extent.createTest("Adding item to cart");
        mainPage.addItemToCart(items[0]);

        assertTrue(mainPage.isShoppingCartCountDisplayed());

        try {
            assertTrue(mainPage.isShoppingCartCountDisplayed());
            assertEquals(1, mainPage.getShoppingCartsCount());
            test.pass("Item added to cart successfully");
        } catch (AssertionError e) {
            test.fail("Cart validation failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void removingItemFromCart(){

        test = extent.createTest("Removing item from the shopping cart");

        mainPage.addItemToCart(items[0]);
        mainPage.removeItemFromCart(items[0]);

        try{
            assertFalse(mainPage.isShoppingCartCountDisplayed());
            test.pass("Item was removed from the shopping cart");
        }catch(AssertionError e){
            test.fail("Item was not removed from the shopping cart");
            throw e;
        }

    }

    @Test
    public void multipleItemsInCart(){

        test = extent.createTest("Adding multiple items to the shopping cart");

        for(int i = 0; i < 3; i++){
            mainPage.addItemToCart(items[i]);
        }

        try{
            assertEquals(3,mainPage.getShoppingCartsCount());
            test.pass("All items were added to the shopping cart");
        } catch (AssertionError e){
            test.fail("Items were not added to the shopping cart");
            throw e;
        }
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
            try {
                assertTrue(itemExist);
                test.pass("Item found in cart: " + removeDashesFromString(expectedItems));
            } catch (AssertionError e) {
                test.fail("Item was missing in cart: " + removeDashesFromString(expectedItems));
            }
        }


    }


    public String removeDashesFromString(String word){
        return word.replace("-", " ").toLowerCase();
    }
}
