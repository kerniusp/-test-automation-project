import org.example.SignInPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignInTest extends BaseTest {

    @Test
    public void successfulLogin(){

        SignInPage signInPage = new SignInPage(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/");
        signInPage.sendSignInData("vardenis@gmail.com","Password1-");

        assertEquals("https://magento.softwaretestingboard.com/customer/account/",driver.getCurrentUrl(),
                "User must be redirected to the account page");

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sign_in_data.csv", numLinesToSkip = 1)
    public void unsuccessfulLogin(String email, String password) {

        SignInPage signInPage = new SignInPage(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/");
        signInPage.sendSignInData(email, password);

        assertEquals("https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/",
                driver.getCurrentUrl(), "User was registered with invalid data form");
    }
}
