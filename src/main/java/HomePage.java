import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class HomePage extends Cities {

    private By flightsBox = By.xpath("/html[1]/body[1]/bf-root[1]/bf-layout-public[1]/section[1]/main[1]/bf-layout-page-home[1]/section[1]/section[2]/div[1]/bf-search-tabs-container[1]/bf-tabs[1]/div[1]/bf-tab-buttons[1]/div[1]/div[1]/button[1]");
    private By originInput = By.xpath("/html[1]/body[1]/bf-root[1]/bf-layout-public[1]/section[1]/main[1]/bf-layout-page-home[1]/section[1]/section[2]/div[1]/bf-search-tabs-container[1]/bf-tabs[1]/div[1]/bf-card[1]/div[1]/bf-flights-search-form-container[1]/bf-form-search-flights[1]/form[1]/fieldset[2]/bf-form-schedules[1]/fieldset[1]/div[1]/bf-form-schedule[1]/fieldset[1]/div[1]/div[1]/bf-control-suggestions-airports[1]/bf-control-suggestions[1]/div[1]/label[1]/div[2]/input[1]");
    private By originPlaceSelection = By.xpath("//*[@class='ui-control-suggestions__action ui-control-suggestions__action--public']");
    private By destinationInput = By.xpath("//*[@data-form='search-flights-arrival']");
    private By destinationPlaceSelection = By.xpath("//*[@class='ui-control-suggestions__action ui-control-suggestions__action--public']");
    private By outboundInput = By.xpath("//*[@class='ui-text ui-text--italic ui-text--light ui-text--line ui-text--regular ui-text--small'][contains(., 'Ida')]");
    private By outboundDate;
    private By incomingInput = By.xpath("//*[@class='ui-text ui-text--italic ui-text--light ui-text--line ui-text--regular ui-text--small'][contains(., 'Regreso')]");
    private By incomingDate;
    private By passengersSelection = By.xpath("//*[@class='ui-dropdown__text ui-dropdown__text--default'][contains(., 'PASAJEROS Y CLASES')]");
    private By selectChildren = By.xpath("/html[1]/body[1]/bf-root[1]/bf-layout-public[1]/section[1]/main[1]/bf-layout-page-home[1]/section[1]/section[2]/div[1]/bf-search-tabs-container[1]/bf-tabs[1]/div[1]/bf-card[1]/div[1]/bf-flights-search-form-container[1]/bf-form-search-flights[1]/form[1]/fieldset[2]/bf-form-passengers[1]/bf-dropdown[1]/div[1]/bf-floating-card[1]/div[1]/div[3]/div[1]/bf-control-counter[2]/label[1]/span[2]/bf-button-icon[2]/button[1]");
    private By selectInfants = By.xpath("/html[1]/body[1]/bf-root[1]/bf-layout-public[1]/section[1]/main[1]/bf-layout-page-home[1]/section[1]/section[2]/div[1]/bf-search-tabs-container[1]/bf-tabs[1]/div[1]/bf-card[1]/div[1]/bf-flights-search-form-container[1]/bf-form-search-flights[1]/form[1]/fieldset[2]/bf-form-passengers[1]/bf-dropdown[1]/div[1]/bf-floating-card[1]/div[1]/div[3]/div[1]/bf-control-counter[3]/label[1]/span[2]/bf-button-icon[2]/button[1]");
    private By searchButton = By.xpath("//*[@class='ui-button ui-button--public--filling ui-button--block ui-button--normal']");
    private By calendar = By.xpath("//*[@class='ui-calendar']");
    private ArrayList<By> dates = new ArrayList<>();

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectOriginFlight() {
        //Click en el link de vuelos
        waitElement(flightsBox);
        //Click en el elemento de ciudad de origen
        waitElement(originInput);

        WebElement originElement = driver.findElement(originInput);
        //Obtenemos una ciudad origen random
        String city = getOriginCity();
        //Enviamos el texto
        originElement.sendKeys(city);
        //Seleccionamos la opción dada
        waitElement(originPlaceSelection);
    }

    public void selectDestinationFlight() {
        //Click en el elemento ciudad destino
        waitElement(destinationInput);

        WebElement destinationElement = driver.findElement(destinationInput);
        //Obtenemos una ciudad destino random
        String city = getDestinationCity();
        //Enviamos el texto
        destinationElement.sendKeys(city);
        //Seleccionamos la opción dada
        waitElement(destinationPlaceSelection);
    }

    public void selectOutboundDate() {
        //Click en el campo de selección de fecha de ida
        waitElement(outboundInput);
        //Obtenemos una fecha random
        waitElement(getOutboundDate());
    }

    public void selectIncomingDate() {
        //Click en el campo de selección de fecha de venida
        waitElement(incomingInput);
        //Obtenemos una fecha random
        waitElement(getIncomingDate());
    }

    public void selectPassengers() {
        //Click en el campo de selección de pasajeros
        waitElement(passengersSelection);
        //Adicionamos un niño
        waitElement(selectChildren);
        //Adicionamos un infante
        waitElement(selectInfants);
    }

    public SelectFlightsPage clickSearchButton() {
        //Click en el botón de buscar vuelos
        waitElement(searchButton);
        //Retornamos una nueva página
        return new SelectFlightsPage(driver);
    }

    //Método privado de espera de los elementos
    private void waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    //Método privado para obtener un localizador de una lista
    private By getDate() {
        waitElement(calendar);

        int i = 1;
        Boolean quantity = true;

        while (quantity) {
            //Obtenemos todos los elementos con el mismo localizador y les damos su índice
            By dateOptions = By.xpath("(//*[@class='ui-calendar__day ui-calendar__day--public'])" + "[" + i + "]");
            if (driver.findElements(dateOptions).size() != 0) {
                //Lo añadimos a la lista
                dates.add(dateOptions);
                i++;
            } else {
                quantity = false;
            }
        }

        //Obtenemos un número random de índice de la lista
        Random random = new Random();
        int index = random.nextInt(dates.size());
        return dates.get(index);
    }

    //Método para obtener una fecha random de ida según el índice seleccionado de la lista
    private By getOutboundDate() {
        outboundDate = getDate();
        return outboundDate;
    }

    //Método para obtener una fecha random de venida según el índice seleccionado de la lista
    private By getIncomingDate() {
        incomingDate = getDate();
        Boolean comparison = true;

        while (comparison) {
            //Verificamos si el índice obtenido en la fecha de venida es menor o igual al índice de la fecha de ida
            if (dates.indexOf(incomingDate) < dates.indexOf(outboundDate)) {
                //Si es menor o igual obtiene uno nuevo
                incomingDate = getDate();
            }
            else {
                comparison = false;
            }
        }
        return incomingDate;
    }
}

