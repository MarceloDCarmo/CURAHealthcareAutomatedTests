import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class SeleniumHelloWorld {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        var title = driver.getTitle();

        System.out.println(title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String text = message.getText();

        System.out.println(text);

        driver.quit();
    }
}
