import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class SeagullTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.goodreads.com/");;
    }

    @Test(testName = "1. Registration on the Seagull Way", priority = 0)
    public void registration() {
        WebElement signUp = driver.findElement(By.xpath("//div/a[contains(text(), 'Sign up with email')]"));
        signUp.click();

        WebElement testName = driver.findElement(By.xpath("//input[@id='ap_customer_name']"));
        testName.click();
        testName.sendKeys("Ivan Ivanov");

        WebElement email = driver.findElement(By.xpath("//input[@id='ap_email']"));
        email.click();
        email.sendKeys("test25idea@gmail.com");

        WebElement password = driver.findElement(By.xpath("//input[@id='ap_password']"));
        password.click();
        password.sendKeys("Olga2019");

        WebElement reEnterPassword = driver.findElement(By.xpath("//input[@id='ap_password_check']"));
        reEnterPassword.click();
        reEnterPassword.sendKeys("Olga2019");

        WebElement buttonCreateAccount = driver.findElement(By.xpath("//input[@id='continue']"));
        buttonCreateAccount.click();
    }

    @Test(testName = "2. Login with invalid credentials", priority = 1)
    public void loginInvalid() {
        WebElement loginLink  = driver.findElement(By.linkText("Войти"));
        loginLink.click();
        WebElement signInWithEmail = driver.findElement(By.xpath("//div/a/button[contains(text(), 'Sign in with email')]"));
        signInWithEmail.click();
        WebElement enterEmail = driver.findElement(By.xpath("//input[@id='ap_email']"));
        enterEmail.click();
        enterEmail.sendKeys("testnew15te.com");
        WebElement password = driver.findElement(By.xpath("//input[@id='ap_password']"));
        password.click();
        password.sendKeys(" ");
        WebElement signInSubmit = driver.findElement(By.xpath("//input[@id='signInSubmit']"));
        signInSubmit.click();
        WebElement message = driver.findElement(By.xpath("//span[contains(text(), 'We cannot find an account with that email address')]"));
        assertTrue(message.isDisplayed(), "Oops...Something went wrong!");
    }

    @Test(testName = "3. Login with valid credentials and other actions", priority = 2)
    public void loginValidCredentials(){
        WebElement loginLink  = driver.findElement(By.linkText("Войти"));
        loginLink.click();
        WebElement signInWithEmail = driver.findElement(By.xpath("//div/a/button[contains(text(), 'Sign in with email')]"));
        signInWithEmail.click();
        WebElement enterEmail = driver.findElement(By.xpath("//input[@id='ap_email']"));
        enterEmail.click();
        enterEmail.sendKeys("test25idea@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@id='ap_password']"));
        password.click();
        password.sendKeys("Olga2019");
        WebElement signInSubmit = driver.findElement(By.xpath("//input[@id='signInSubmit']"));
        signInSubmit.click();

        //4. Search for "Best crime and mystery books"
        WebElement searchField  = driver.findElement(By.xpath("//footer/div/div/form/input[@placeholder = 'Search books']"));
        searchField.click();
        searchField.sendKeys("Best crime and mystery books");
        WebElement searchButton = driver.findElement(By.xpath("//*[@class = 'searchBox__icon--magnifyingGlass gr-iconButton searchBox__icon searchBox__icon--currentlyReading']"));
        searchButton.click();

        //5. Mark top 3 books as "Want to read"
        WebElement wantToReadFist = driver.findElement(By.xpath("//tr[1]/td[2]/div[2]/div/div[1]/form/button"));
        wantToReadFist.click();
        WebElement wantToReadSecond = driver.findElement(By.xpath("//tr[2]/td[2]/div[2]/div/div[1]/form/button"));
        wantToReadSecond.click();
        WebElement wantToReadThird = driver.findElement(By.xpath("//tr[3]/td[2]/div[2]/div/div[1]/form/button"));
        wantToReadThird.click();

        //6. Mark as read
        WebElement wtrShelfButton = driver.findElement(By.xpath("//tr[1]/td[2]/div[2]/div/div[2]/button"));
        wtrShelfButton.click();
        WebElement readLabel = driver.findElement(By.xpath("//tr[1]/td[2]/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/button"));
        readLabel.click();

        //7. Rate and leave feedback for them (add different read dates)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement rate = driver.findElement(By.xpath("//body/div[5]/div[2]/div/form/div/div[2]/div[1]/a[4]"));
        rate.click();

        WebElement textField = driver.findElement(By.xpath("//*[@id='review_review_usertext']"));
        textField.click();
        textField.sendKeys("really liked it");

        //Аdd different read dates
        WebElement yearStartSelector= driver.findElement(By.xpath("//tr[2]/td[1]/span/div[1]/select"));
        yearStartSelector.click();
        WebElement startYear= driver.findElement(By.xpath("//tr[2]/td[1]/span/div[1]/select/option[4]"));
        startYear.click();
        WebElement monthStartSelector= driver.findElement(By.xpath("//tr[2]/td[1]/span/div[3]/select"));
        monthStartSelector.click();
        WebElement startMonth= driver.findElement(By.xpath("//tr[2]/td[1]/span/div[3]/select/option[5]"));
        startMonth.click();
        WebElement dayStartSelector= driver.findElement(By.xpath("//tr[2]/td[1]/span/div[5]/select"));
        dayStartSelector.click();
        WebElement startDay= driver.findElement(By.xpath("//tr[2]/td[1]/span/div[5]/select/option[3]"));
        startDay.click();

        //Set today's date as the end date.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement setToToday= driver.findElement(By.xpath("//a[@class = 'endedAtSetTodayLink gr-button']"));
        setToToday.click();

        //Click on the "Add read date" button and fill in the dates
        WebElement addReadDate= driver.findElement(By.xpath("//a[@id='readingSessionAddLink']"));
        addReadDate.click();
        WebElement secondStartDate = driver.findElement(By.xpath("//tr[3]/td[1]/span/div[6]/a"));
        secondStartDate.click();
        WebElement yearFinishSelector= driver.findElement(By.xpath("//tr[3]/td[2]/span/span[1]/select"));
        yearFinishSelector.click();
        WebElement finishYear = driver.findElement(By.xpath("//tr[3]/td[2]/span/span[1]/select/option[3]"));
        finishYear.click();
        WebElement monthFinishSelector= driver.findElement(By.xpath("//tr[3]/td[2]/span/span[2]/select"));
        monthFinishSelector.click();
        WebElement finishMonth= driver.findElement(By.xpath("//tr[3]/td[2]/span/span[2]/select/option[10]"));
        finishMonth.click();
        WebElement dayFinishSelector= driver.findElement(By.xpath("//tr[3]/td[2]/span/span[3]/select"));
        dayFinishSelector.click();
        WebElement finishDay= driver.findElement(By.xpath("//tr[3]/td[2]/span/span[3]/select/option[19]"));
        finishDay.click();

        //Click on the "POST" button
        WebElement postButton= driver.findElement(By.xpath("//input[@value = 'Post']"));
        postButton.click();

        //8. Logout
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        WebElement profileIcon= driver.findElement(By.xpath("//a[contains(@class, 'dropdown__trigger dropdown__trigger--profileMenu')]"));
        profileIcon.click();
        WebElement signOut = driver.findElement(By.xpath(" //li[@role='menuitem Sign out']"));
        signOut.click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

