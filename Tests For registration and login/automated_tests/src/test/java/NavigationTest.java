import org.example.MainPage;
import org.example.RegistrationPage;
import org.example.SignInPage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest extends BaseTest{

    @Test
    public void navigatingToRegistration(){

        MainPage mainPage = new MainPage(driver);
        driver.get("https://magento.softwaretestingboard.com/");

        mainPage.clickCreateAnAccount();

        assertEquals("https://magento.softwaretestingboard.com/customer/account/create/",
                driver.getCurrentUrl(),"User must be redirected to the registration page");
    }

    @Test
    public void visibleFormsForRegistration(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");

        assertTrue(registrationPage.displayedForm(),"Form for registration is not visible on the website");
    }

    @Test
    public void navigationToLogin(){

        MainPage mainPage = new MainPage(driver);
        driver.get("https://magento.softwaretestingboard.com/");

        mainPage.clickSignIn();

        assertEquals("https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/",
                driver.getCurrentUrl(),"User must be redirected to the sign in page");

    }

    @Test
    public void visibleFormsForSignIn(){

        SignInPage signInPage = new SignInPage(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/");

        assertTrue(signInPage.displayedForm(),"Sign in form is not displayed for the user");

    }


}
