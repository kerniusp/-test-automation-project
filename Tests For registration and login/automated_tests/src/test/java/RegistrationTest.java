import org.example.AccountPage;
import org.example.RegistrationPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest extends BaseTest{

    @Test
    public void testSuccessfulRegistration(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");

        registrationPage.sendRegistrationData("Vardenis","Pavardenis","vardenis@gmail.com",
                "Password1-","Password1-");

        AccountPage accountPage = new AccountPage(driver);

        assertEquals("https://magento.softwaretestingboard.com/customer/account/",driver.getCurrentUrl(),
                "User must be redirected to the account page");
        assertEquals("Thank you for registering with Main Website Store.",accountPage.textOfSuccessMessage(),
                "There should be a message displayed");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/registration_test_data.csv", numLinesToSkip = 1)
    public void testUnsuccessfulRegistration(String firstName, String lastName,String email,String password, String confPassword){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");

        registrationPage.sendRegistrationData(firstName,lastName,email,password,confPassword);

        assertEquals("https://magento.softwaretestingboard.com/customer/account/create/",driver.getCurrentUrl(),
                "User was registered with invalid data form");

    }

}
