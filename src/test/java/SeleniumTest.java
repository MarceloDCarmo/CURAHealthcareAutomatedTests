import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class SeleniumTest {
    @Test
    public void shouldReturnReceivedMessage() {
        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        var title = driver.getTitle();

        assertEquals("Web form", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        var text = message.getText();

        assertEquals("Received!", text);

        driver.quit();
    }
}
