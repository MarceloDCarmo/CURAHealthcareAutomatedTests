package scenarios;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CuraHealthcareTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/home/marcelo/Downloads/chrome-linux64/chrome");
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void shouldMakeAnAppointmentLoggedIn() {
        driver.get("https://katalon-demo-cura.herokuapp.com");

        var makeAppointmentBtn = driver.findElement(By.id("btn-make-appointment"));
        makeAppointmentBtn.click();

        var usernameInput = driver.findElement(By.id("txt-username"));
        usernameInput.sendKeys("John Doe");

        var passwordInput = driver.findElement(By.id("txt-password"));
        passwordInput.sendKeys("ThisIsNotAPassword");

        var loginBtn = driver.findElement(By.id("btn-login"));
        loginBtn.click();

        var title = driver.findElement(By.xpath("//*[@id=\"appointment\"]/div/div/div/h2"));

        assertEquals("Make Appointment", title.getText());

        var facilitySelectElement = driver.findElement(By.id("combo_facility"));
        Select facilitySelect = new Select(facilitySelectElement);

        facilitySelect.selectByIndex(1);

        assertEquals("Hongkong CURA Healthcare Center", facilitySelect.getFirstSelectedOption().getText());

        var hospitalReadmissionChk = driver.findElement(By.id("chk_hospotal_readmission"));
        hospitalReadmissionChk.click();

        var radioProgramMedicaidChk = driver.findElement(By.id("radio_program_medicaid"));
        radioProgramMedicaidChk.click();

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var visitDate = tomorrow.format(formatter);

        var visitDateInput = driver.findElement(By.id("txt_visit_date"));
        visitDateInput.sendKeys(visitDate);

        var commentInput = driver.findElement(By.id("txt_comment"));
        commentInput.sendKeys("Just a test");

        var bookAppointmentBtn = driver.findElement(By.id("btn-book-appointment"));
        bookAppointmentBtn.click();

        title = driver.findElement(By.xpath("//*[@id=\"summary\"]/div/div/div[1]/h2"));
        assertEquals("Appointment Confirmation", title.getText());
    }

    @AfterAll
    public static void close() {
        driver.quit();
    }
}
