import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectFlightsPage {

    private WebDriver driver;
    private By selectOutboundFlightButton = By.xpath("/html[1]/body[1]/bf-root[1]/bf-layout-public[1]/section[1]/main[1]/bf-layout-page-results[1]/section[1]/main[1]/section[4]/bf-recommendations-flights-container[1]/div[1]/bf-recommendations[1]/div[1]/bf-scrolling[1]/div[1]/div[1]/bf-recommendation-card[1]/div[1]/div[2]/bf-fare-summary[1]/div[1]/bf-button[1]/button[1]/div[1]");
    private By selectIncomingFlightButton = By.xpath("/html[1]/body[1]/bf-root[1]/bf-layout-public[1]/section[1]/main[1]/bf-layout-page-results[1]/section[1]/main[1]/section[4]/bf-recommendations-flights-container[1]/div[1]/bf-recommendations[1]/div[1]/bf-scrolling[1]/div[1]/div[1]/bf-recommendation-card[1]/div[1]/div[2]/bf-fare-summary[1]/div[1]/bf-button[1]/button[1]");

    public SelectFlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    //Seleccionamos un vuelo de ida
    public void selectOutboundFlight() {
        waitElement(selectOutboundFlightButton);
    }

    //Seleccionamos un vuelo de venida
    public PassengersPage selectIncomingFlight() {
        waitElement(selectIncomingFlightButton);
        return new PassengersPage(driver);
    }

    //Método de espera
    private void waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
