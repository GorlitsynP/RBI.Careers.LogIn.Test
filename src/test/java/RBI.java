import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RBI {
    WebDriver driver;
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win64/chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println(driver);

    }
    @Test
    public void RBICareersLogin(){
        LogInLogic rBILogInLogic = new LogInLogic(driver);
        rBILogInLogic.navigateToTheHomePage(LogInData.rBIAddress);
        rBILogInLogic.navigateToTheCareers();
        rBILogInLogic.logIn(LogInData.login, LogInData.password);
        rBILogInLogic.verifyResult(LogInData.verificationLink);
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }


}
