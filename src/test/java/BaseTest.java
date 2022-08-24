import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver driver;
    public HomePage homePage;

    @BeforeClass
    public void setUp() {
        String driverName = "chrome";

        //Elegimos el driver a utilizar
        if (driverName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver(getChromeOptions());
            homePage = new HomePage(driver);
            driver.get("https://travel.luegopago.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } else if (driverName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
            driver = new FirefoxDriver(getFirefoxOptions());
            homePage = new HomePage(driver);
            driver.get("https://travel.luegopago.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } else if (driverName.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", "resources/msedgedriver.exe");
            driver = new EdgeDriver(getEdgeOptions());
            homePage = new HomePage(driver);
            driver.get("https://travel.luegopago.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } else {
            System.out.println("Nombre de driver no válido");
        }
    }

    //Especificacioes de Chrome
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        return options;
    }

    //Especificaciones de Firefox
    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        return options;
    }

    //Especificaciones de Edge
    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("-inprivate");
        options.addArguments("start-maximized");
        return options;
    }
}
