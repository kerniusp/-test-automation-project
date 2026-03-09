
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;
    protected WebDriver driver;

    @BeforeAll
    public static void setupReport() {
        extent = ExtentManager.getExtent();
    }

    @AfterAll
    public static void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }


    @BeforeEach
    void startup(){
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().fullscreen();
    }

    @AfterEach
    void tearDown(){
        driver.close();
    }

}

