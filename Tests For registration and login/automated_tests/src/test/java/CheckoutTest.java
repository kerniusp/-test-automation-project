import com.aventstack.extentreports.ExtentTest;
import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutTest extends BaseTest {

    MainPage mainPage;
    LoginPage loginPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeEach
    void login() {
        try {
            loginPage = new LoginPage(driver);
            mainPage = new MainPage(driver);
            cartPage = new CartPage(driver);
            checkoutPage = new CheckoutPage(driver);
            loginPage.loginIntoWebsite("standard_user","secret_sauce");
        } catch (Exception e) {
            ExtentTest test = extent.createTest("Login setup failed");
            test.fail("Login failed during setup: " + e.getMessage());
            throw e;
        }
    }


    @Test
    public void checkoutTest(){

        test = extent.createTest("Complete checkout");

        checkoutFlow("Tomas","Tomauskas","LT-1234");
        checkoutPage.clickFinish();


        try{
            assertEquals("https://www.saucedemo.com/checkout-complete.html",driver.getCurrentUrl());
            test.pass("User was redirected to the checkout page");
        }catch(AssertionError e){
            test.fail("User was not redirected");
            throw e;
        }

        try{
            assertTrue(checkoutPage.isOrderCompletedMessageDisplayed());
            test.pass("Checkout flow was completed ");
        }catch(AssertionError e){
            test.fail("Checkout was not completed");
            throw e;
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkout_data.csv",numLinesToSkip = 1)
    public void checkoutWithIncorrectData(String firstName,String lastName,String postalCode,String error){

        test = extent.createTest("Checking out with incorrect information");

        checkoutFlow(firstName,lastName,postalCode);

        try{
            assertTrue(checkoutPage.isErrorMessageDisplayed());
            test.pass("User got an error message regarding incorrect information");
        }catch(AssertionError e){

            String screenshotPath = takeScreenshot("CheckoutWithIncorrectData");
            test.fail("User was not provided with an error message such as:" + error)
                    .addScreenCaptureFromPath(screenshotPath);


            throw e;
        }

        try{
            assertEquals(error,checkoutPage.errorMessage());
            test.pass("User was provided with correct error message");
        }catch(AssertionError e){
            test.fail("User was provided with incorrect information: excepted error: " + error);
            throw e;
        }


    }


    @Test
    public void priceTotalCountTest(){

        test = extent.createTest("Item price count test");

        checkoutFlow("Tomas","Tomauskas","LT-1234");

        try{
            assertEquals(39.98,checkoutPage.totalPriceSum());
            test.pass("Price was counted correctly");
        }catch(AssertionError e){
            test.fail("Price was counted incorrectly ");
            throw e;
        }

    }

    public void checkoutFlow(String firstName, String lastName, String postalCode){

        mainPage.addItemToCart("sauce-labs-backpack");
        mainPage.addItemToCart("sauce-labs-bike-light");
        mainPage.pressShoppingCart();

        cartPage.clickSubmit();

        checkoutPage.enterInformationCredentials(firstName,lastName,postalCode);
        checkoutPage.clickSubmit();

    }
}
