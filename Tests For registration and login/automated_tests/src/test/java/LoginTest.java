import org.example.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest{



    @Test
    public void standardUserLogin(){

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWebsite("standard_user","secret_sauce");
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(),
                "User must be redirected to the home page of website");
    }

    @Test
    public void lockedOutUserLogin(){

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWebsite("locked_out_user","secret_sauce");
        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getErrorMessageText(),
                "User must be locked out from website and provided with an error message");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sign_in_data.csv",numLinesToSkip = 1)
    public void incorrectLogin(String username, String password, String error){

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWebsite(username,password);
        assertEquals(error, loginPage.getErrorMessageText(),"Error message must match");
    }

}
