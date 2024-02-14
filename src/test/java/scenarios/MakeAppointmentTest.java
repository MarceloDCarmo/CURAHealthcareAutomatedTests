package scenarios;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AppointmentConfirmationPage;
import pages.AppointmentPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DateUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakeAppointmentTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/home/marcelo/Downloads/chrome-linux64/chrome");
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://katalon-demo-cura.herokuapp.com");
    }

    @Test
    public void shouldMakeAnAppointment(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.tryToMakeUnloggedAnAppointment();
        AppointmentPage appointmentPage = loginPage.login("John Doe", "ThisIsNotAPassword");
        AppointmentConfirmationPage appointmentConfirmationPage = appointmentPage.makeAnAppointment(1, "Just an automated test!");

        assertEquals("Hongkong CURA Healthcare Center", appointmentConfirmationPage.getFacility().getText());
        assertEquals("Yes", appointmentConfirmationPage.getHospitalReadmission().getText());
        assertEquals("Medicaid", appointmentConfirmationPage.getHealthcareProgram().getText());
        assertEquals(DateUtils.getTomorrowDateString(), appointmentConfirmationPage.getVisitDate().getText());
        assertEquals("Just an automated test!", appointmentConfirmationPage.getComment().getText());
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
