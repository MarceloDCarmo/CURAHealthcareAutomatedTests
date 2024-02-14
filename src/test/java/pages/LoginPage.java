package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "txt-username")
    private WebElement usernameInput;

    @FindBy(id = "txt-password")
    private WebElement passwordInput;

    @FindBy(id = "btn-login")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        if(!driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div[1]/h2")).getText().equals("Login")){
            throw new IllegalStateException("This is not the login page");
        }
        PageFactory.initElements(driver, this);
    }

    public AppointmentPage login(String username, String password){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();

        return new AppointmentPage(driver);
    }
}
