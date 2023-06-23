package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class AbstractAddClient {
    private final static int DURATION_TO_WAIT_DEFAULT = 4;
    private final By firstNameField = By.cssSelector("#firstName");
    private final By lastNameField = By.cssSelector("#lastName");
    private final By emailField = By.cssSelector("#userEmail");
    private final By ageField = By.cssSelector("#age");
    private final By salaryField = By.cssSelector("#salary");
    private final By departmentField = By.cssSelector("#department");
    private final By addButton = By.cssSelector("#addNewRecordButton");
    private final By submitButton = By.cssSelector("#submit");
    protected WebDriver driver;

    public AbstractAddClient(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(final By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(DURATION_TO_WAIT_DEFAULT))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
    @Step
    public AbstractAddClient addIn(String firstName, String lastName, String userEmail, int age, int salary, String department){
        driver.get("https://demoqa.com/webtables");
        getElement(addButton).click();
        getElement(firstNameField).sendKeys(firstName);
        getElement(lastNameField).sendKeys(lastName);
        getElement(emailField).sendKeys(userEmail);
        getElement(ageField).sendKeys(String.valueOf(age));
        getElement(salaryField).sendKeys(String.valueOf(salary));
        getElement(departmentField).sendKeys(department);
        getElement(submitButton).click();
        return new AbstractAddClient(driver);
    }
}
