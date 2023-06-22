package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ButtonTest extends BaseTest {
    private final static int DURATION_TO_WAIT_DEFAULT = 4;
    private final By contextButtons = By.cssSelector("#item-4");
    private final By clickMeButton = By.xpath("//div[@class=\"mt-4\"]/button[text()=\"Click Me\"]");
    protected WebDriver driver = new ChromeDriver();

    public WebElement getElement(final By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(DURATION_TO_WAIT_DEFAULT))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Test
    public void testButtonClick(){
        driver.get("https://demoqa.com/elements");
        getElement(contextButtons).click();
        getElement(clickMeButton).click();

        String checkText = driver.findElement(By.cssSelector("#dynamicClickMessage")).getText();
        Assert.assertEquals(checkText, "You have done a dynamic click"
                , "The ButtonTest was not successful");

        driver.close();
        driver.quit();
    }
}
