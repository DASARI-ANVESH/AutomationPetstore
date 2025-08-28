import com.petstore.automation.ui.utils;
import com.petstore.automation.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import java.time.Duration;
import java.time.chrono.ChronoLocalDateTime;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal= new ThreadLocal<>();
    public static void intitializeDriver(){
        String browser=ConfigManager.getProperty("ui.browseer","chrome");
        boolen headless=ConfigManager.getBooleanProperty("ui.headless", false)
        int timeout=ConfigManager.getIntProperty("ui.timeout",10);

        WebDriver driver;

        switch(browser.toLowerCase()){
            case "chrome":
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions=new ChromeOptins();
            
            chromeOptions.setExperimnetalOptions("useAutomationExtension",false);
            chromeOptions.addArguments("--disable-blink_features=AutomationControlles");
            chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)"+"AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        if(headless){
            chromeOptions.addArguments("--headless");
        }
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");

        driver=new chromeDriver(chromeOptions);
        break;

        case "firefox":
        io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions=new FirefoxOptions();
        if(headless){
            firefoxOptions.addArguments("--headless");
        }
        driver=new FirefoxDriver(firefoxOptions);
        break;

        default:
        throw new IllegalArgumentException("Browser not supported"+browser);          
        
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().window().maximize();

        driverThreadLocal.set(driver);
    }
    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }
    public static void quitDriver(){
        WebDriver driver=driverThreadLocal.get();
        if(driver!=null){
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
