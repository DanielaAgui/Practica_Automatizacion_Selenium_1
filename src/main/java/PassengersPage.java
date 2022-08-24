import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class PassengersPage {

    private WebDriver driver;
    private By emailInput = By.xpath("//*[@data-cy='email']");
    private By phoneNumberInput = By.xpath("//*[@data-cy='phoneNumber']");
    private By addressInput = By.xpath("//*[@data-cy='address']");
    private By acceptTermsCheckbox = By.xpath("//*[@class='form-control-checkbox__mask']");
    private By continueButton = By.xpath("//*[@id='checkout-action']");
    private ArrayList<By> passengers = new ArrayList<>();


    public PassengersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getPassengers() {

        int i = 1;
        Boolean quantity = true;

        while (quantity) {
            //Obtenemos todos los elementos con el mismo localizador y le damos si índice
            By passengersQuantity = By.xpath("(//*[@class='ui-form-passengers__passengers ng-invalid ng-dirty ng-touched'])" + "[" + i + "]");
            if (driver.findElements(passengersQuantity).size() != 0) {
                //Lo agregamos a una lista
                passengers.add(passengersQuantity);
                i++;
            } else {
                quantity = false;
            }
        }

        information("(//*[@data-cy='firstName'])", "name");
        information("(//*[@data-cy='lastName'])", "lastname");
        information("(//*[@data-cy='documentNumber'])", "id");
    }

    //Método que termina de llenar los datos solicitados para el adulto
   public void setPersonalInformation() {
        Faker faker = new Faker();

        WebElement emailElement = driver.findElement(emailInput);
        emailElement.sendKeys(faker.internet().emailAddress());

        WebElement phoneNumberElement = driver.findElement(phoneNumberInput);
        phoneNumberElement.sendKeys(faker.numerify("3#########"));

        WebElement addressElement = driver.findElement(addressInput);
        addressElement.sendKeys(faker.address().fullAddress());
   }

   //Aceptamos términos y condiciones
   public void clickTermsCheckbox() {
        driver.findElement(acceptTermsCheckbox).click();
   }

   public PaymentPage clickContinueButton() {
        //Click en el botón para continuar
        driver.findElement(continueButton).click();
        //Retoranmos una página nueva
        return new PaymentPage(driver);
   }

   //Método para llenar la información igual solicitada para los viajeros
   private void information(String ruta, String key) {
       Faker faker = new Faker();
       //Desde el número hasta el tamaño de la lista de localizadores
       for(int j = 1; j <= passengers.size() ; j++){
           //Obtenemos la ruta del elemento a llenar con su índice
           By rutaElemento = By.xpath(ruta + "[" + j + "]");
           WebElement element = driver.findElement(rutaElemento);
           //Esperamos que el elemento esté disponible para dar click
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
           wait.until(ExpectedConditions.elementToBeClickable(rutaElemento));
           //Llenamos los datos de forma aleatoria según la clave dada
           if (key == "name") {
               element.sendKeys(faker.name().firstName());
           } else if (key == "lastname") {
               element.sendKeys(faker.name().lastName());
           } else if (key == "id") {
               element.sendKeys(faker.numerify("1#########"));
           }
       }
   }
}

    /*for(int j = 1; j <= passengers.size() ; j++) {
        Faker faker = new Faker();
        By provisional = By.xpath("(//*[@data-cy='documentNumber'])" + "[" + j + "]");
        WebElement documentNumberElement = driver.findElement(provisional);
        documentNumberElement.sendKeys(faker.number().digits(10));
        System.out.println(provisional);
    }*/