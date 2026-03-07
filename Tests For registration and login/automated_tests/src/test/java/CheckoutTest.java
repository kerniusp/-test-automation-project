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

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.loginIntoWebsite("standard_user","secret_sauce");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkout_data.csv",numLinesToSkip = 1)
    public void checkoutWithIncorrectDataFormat(String firstName, String lastName,
                                                String postalCode,String error){

        mainPage.addItemToCart("sauce-labs-backpack");
        mainPage.addItemToCart("sauce-labs-bike-light");
        mainPage.pressShoppingCart();

        cartPage.clickSubmit();

        checkoutPage.enterInformationCredentials(firstName,lastName,postalCode);
        checkoutPage.clickSubmit();

        assertTrue(checkoutPage.isErrorMessageDisplayed(),
                "Error is not displayed for entering incorrect data");
        assertEquals(error,checkoutPage.errorMessage(),
                "Error message do not match the incorrect data input");
    }

}
