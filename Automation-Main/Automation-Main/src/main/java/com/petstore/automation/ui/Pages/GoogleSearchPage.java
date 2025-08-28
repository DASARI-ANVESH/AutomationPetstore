
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.time.Duration;
import java.util.List;

public class GoogleSearchPage {
    
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(name="q")
    private WebElement searchBox;

    @FindBy(css="[name='btnk']")
    private WebElement searchButton;

    @FindBy(css="h3")
    private List<WebElement> searchResults;

    @FindBy(css="#search")
    private WebElement searchResultsContainer;

    public GoogleSearchPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }
    public void navigatetoGoogle(){
        driver.get("https://www.google.com");
    
    try{
        WebElement acceptButton =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Accept') or contains(text(),'I agree')]")));
        acceptButton.click();
    }
    catch(Exception e){

    }
}
public void searchFor(String searchTerm){
    wait.until(ExpectedConditions.elementToBeClickable(searchBox));
    searchBox.clear();
    searchBox.sendKeys(searchTerm);
    try{
        Thread.sleep(1000);
    }
    catch(Exception e){
        Thread.currentThread().interrupt();
    }
    searchBox.submit();
}
public boolean areSearchResultsDisplayed(){
    try{
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search")));
        return searchResults.size()>0;
        }
    catch(Exception e){
        return false;
    }
}
public String getPageTitle(){
    return driver.getTitle();
}}