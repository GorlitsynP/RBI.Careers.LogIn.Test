import okio.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LogInLogic {

    WebDriver driver;
    WebDriverWait wait;
    WebElement signInButton;
    WebElement loginField;
    WebElement passwordField;
    WebElement careersTab;
    WebElement signInTab;
    WebElement sighInButton1;
    String actualURL;

    public LogInLogic(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3);
    }

    public void navigateToTheHomePage(String address){
        System.out.println(driver);
        driver.get(address);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    public void navigateToTheCareers(){
        careersTab = driver.findElement(By.xpath("//*[contains(@class, 'menuCareers')]"));
        careersTab.click();

    }

    public  String logIn(String loginInput, String passwordInput){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String currentWindow = driver.getWindowHandle();
        Set<String>s = driver.getWindowHandles();
        for (String currentURL: s) {
            if (!currentURL.equals(currentWindow)){
                driver.switchTo().window(currentURL);
                break;
            }

        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        signInTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@data-ph-id, 'ph-default-ph-candidate-login-v1ikjhys-KaZbxu')]")));
        signInTab.click();
        signInButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@aria-label='Sign in']")));
        signInButton.click();
        loginField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='signinEmail']")));
        loginField.sendKeys(loginInput);
        passwordField = driver.findElement(By.xpath("//*[@id='signInPassword']"));
        passwordField.sendKeys(passwordInput);
        sighInButton1 = driver.findElement(By.xpath("//*[@type='submit']"));
        sighInButton1.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actualURL = driver.getCurrentUrl();
        return actualURL;
    }

    public void verifyResult(String linkInput){
        Assert.assertEquals(actualURL,linkInput," Something Went Wrong");
        System.out.println("Test Passed");

    }





}
