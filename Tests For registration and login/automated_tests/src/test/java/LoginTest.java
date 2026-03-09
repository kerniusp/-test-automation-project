import com.aventstack.extentreports.ExtentTest;
import org.example.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest{



    @Test
    public void standardUserLogin(){

        test = extent.createTest("User login test");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWebsite("standard_user","secret_sauce");

        try{
            assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(),
                    "User must be redirected to the home page of website");
            test.pass("User was logged in");
        }catch(AssertionError e){
            test.fail("User wasn't logged in");
            throw e;
        }

    }

    @Test
    public void lockedOutUserLogin(){

        test = extent.createTest("Locked out user login");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWebsite("locked_out_user","secret_sauce");

        try{
            assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getErrorMessageText(),
                    "User must be locked out from website and provided with an error message");
            test.pass("User was not allowed to log in");
        }catch(AssertionError e){
            test.fail("User was allowed to log in");
            throw e;
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sign_in_data.csv",numLinesToSkip = 1)
    public void incorrectLogin(String username, String password, String error){

        test = extent.createTest("User log in form validation test");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWebsite(username,password);

        try{
            assertEquals(error, loginPage.getErrorMessageText(),"Error message must match");
            test.pass("User was not allowed to log in and provided with correct error message");
        }catch(AssertionError e ){
            test.fail("User was provided with incorrect error message, expeceted: "  + error);
            throw e;
        }

    }

}
