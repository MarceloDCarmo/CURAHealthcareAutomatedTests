package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    @FindBy(id = "btn-make-appointment")
    private WebElement makeAppointmentBtn;

    public HomePage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().equals("CURA Healthcare Service")) {
            throw new IllegalStateException("This is not the homepage");
        }
        PageFactory.initElements(driver, this);
    }

    public LoginPage tryToMakeUnloggedAnAppointment(){
        makeAppointmentBtn.click();

        return new LoginPage(driver);
    }
}
