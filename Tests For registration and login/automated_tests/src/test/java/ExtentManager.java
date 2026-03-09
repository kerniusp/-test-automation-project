
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("SauceDemo Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("QA/Testing", "Kernius Pauliukenas");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }
}