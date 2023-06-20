package tests;

import PageObjects.AbstractAddClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CrudTest extends BaseTest{
    private final static int DURATION_TO_WAIT_DEFAULT = 4;
    private final static String FIRST_NAME = "Cara";
    private final static String LAST_NAME = "Delevingne";
    private final static String EMAIL = "testmail@gmail.com";
    private final static int AGE = 30;
    private final static int SALARY = 30000;
    private final static int SALARY_TEST = 999999;
    private final static String DEPARTMENT = "Communications";

    public WebElement getElement(final By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(DURATION_TO_WAIT_DEFAULT))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Test
    public void crudTest(){
        new AbstractAddClient(driver)
                .addIn(FIRST_NAME, LAST_NAME, EMAIL, AGE, SALARY, DEPARTMENT);

        String findNewClientByEmail = driver.findElement(By.xpath("//*[contains(text(),'" + EMAIL + "')]"))
               .getText();
        Assert.assertEquals(findNewClientByEmail, "" + EMAIL + ""
                , "Adding new client was not successful, email not found");
        String findNewClientByLastName = driver.findElement(By.xpath("//*[contains(text(),'" + LAST_NAME + "')]"))
                .getText();
        Assert.assertEquals(findNewClientByLastName, "" + LAST_NAME + ""
                , "Adding new client was not successful, last name not found");



        getElement(By.xpath("//*[contains(text(),'" + EMAIL + "')]/../div[7]/div/span[1]")).click();
        getElement(By.cssSelector("#salary")).clear();
        getElement(By.cssSelector("#salary")).sendKeys(String.valueOf(SALARY_TEST));
        getElement(By.cssSelector("#submit")).click();

        String findNewClientSalary = driver.findElement(By.xpath("//*[contains(text(),'" + EMAIL + "')]/../div[5]"))
                .getText();
        Assert.assertEquals(findNewClientSalary, "" + SALARY_TEST + ""
                , "Edit test was not successful, salary field doesn't match");



        getElement(By.xpath("//*[contains(text(),'" + EMAIL + "')]/../div[7]/div/span[2]")).click();

        List<WebElement> checkClients = driver.findElements(By.xpath("//*[contains(text(),'" + EMAIL + "')]"));
        int founded = checkClients.size();
        Assert.assertTrue(founded == 0, "Delete operation was not successful");
    }
}
