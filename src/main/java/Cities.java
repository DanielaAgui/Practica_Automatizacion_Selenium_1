import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Random;

public class Cities {

    public WebDriver driver;
    private String originCity;
    private String destinationCity;

    public Cities(WebDriver driver) {
        this.driver = driver;
    }

    //Método que recibe una lista de ciudades
    private String getCity() {

        ArrayList<String> cities = new ArrayList<>();

        cities.add("medellin");
        cities.add("bogota");
        cities.add("bucaramanga");
        cities.add("san andres");
        cities.add("cartagena");
        cities.add("cali");

        //Devuelve un número random como índice para devolver una ciudad del array
        Random random = new Random();
        int indexOrigin = random.nextInt(cities.size());
        return cities.get(indexOrigin);
    }

    //Método que devuelve una ciudad origen de la lista anterior
    public String getOriginCity() {
        return originCity = getCity();
    }

    //Método que devuelve una ciudad destino de la lista anterior
    public String getDestinationCity() {
        destinationCity = getCity();
        Boolean comparison = true;
        while(comparison){
            //Comparamos si la ciudad origen es igual a la ciudad destino
            if (originCity == destinationCity) {
                //Si son iguales elige una nueva
                destinationCity = getCity();
            }
            else {
                comparison = false;
            }
        }
        return destinationCity;
    }
}
