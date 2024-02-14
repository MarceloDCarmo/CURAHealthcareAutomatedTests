package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppointmentPage {

    WebDriver driver;

    @FindBy(id = "combo_facility")
    private WebElement facilitySelectElement;

    @FindBy(id = "chk_hospotal_readmission")
    private WebElement hospitalReadmissionChk;

    @FindBy(id = "radio_program_medicaid")
    private WebElement radioProgramMedicaidChk;

    @FindBy(id = "txt_visit_date")
    private WebElement visitDateInput;

    @FindBy(id = "txt_comment")
    private WebElement commentInput;

    @FindBy(id = "btn-book-appointment")
    private WebElement bookAppointmentBtn;

    public AppointmentPage(WebDriver driver){
        this.driver = driver;
        if(!driver.findElement(By.xpath("//*[@id=\"appointment\"]/div/div/div/h2")).getText().equals("Make Appointment")){
            throw new IllegalStateException("This is not the appointment page");
        }
        PageFactory.initElements(driver, this);
    }

    public AppointmentConfirmationPage makeAnAppointment(Integer facilitySelectIndex, String comment){
        Select facilitySelect = new Select(facilitySelectElement);
        facilitySelect.selectByIndex(facilitySelectIndex);
        hospitalReadmissionChk.click();
        radioProgramMedicaidChk.click();
        visitDateInput.sendKeys(DateUtils.getTomorrowDateString());
        commentInput.sendKeys(comment);
        bookAppointmentBtn.click();

        return new AppointmentConfirmationPage(driver);
    }
}