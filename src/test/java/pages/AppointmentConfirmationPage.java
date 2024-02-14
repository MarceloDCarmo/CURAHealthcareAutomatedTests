package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppointmentConfirmationPage {

    WebDriver driver;
    @FindBy(id = "facility")
    private WebElement facility;

    @FindBy(id = "hospital_readmission")
    private WebElement hospitalReadmission;

    @FindBy(id = "program")
    private WebElement healthcareProgram;

    @FindBy(id = "visit_date")
    private WebElement visitDate;

    @FindBy(id = "comment")
    private WebElement comment;

    public AppointmentConfirmationPage(WebDriver driver){
        this.driver = driver;
        if(!driver.findElement(By.xpath("//*[@id=\"summary\"]/div/div/div[1]/h2")).getText().equals("Appointment Confirmation")){
            throw new IllegalStateException("This is not the appointment confirmation page");
        }
        PageFactory.initElements(driver, this);
    }

    public WebElement getFacility() {
        return facility;
    }

    public WebElement getHospitalReadmission() {
        return hospitalReadmission;
    }

    public WebElement getHealthcareProgram() {
        return healthcareProgram;
    }

    public WebElement getVisitDate() {
        return visitDate;
    }

    public WebElement getComment() {
        return comment;
    }
}
