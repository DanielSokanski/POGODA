package pl.com.danielsokanski.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.com.danielsokanski.model.WeatherData;

import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    String current_city;
    String searched_city;
    WeatherData weatherData;

    @FXML
    private Label today;

    @FXML
    private TextField myCity;

    @FXML
    private TextField newCity;

    @FXML
    private Label myCityName;

    @FXML
    private Label searchedCityName;

    @FXML
    private Label myCityTemp;

    @FXML
    private Label myCityPressure;

    @FXML
    private Label myCityHumidity;

    @FXML
    private Label myCityWind;

    @FXML
    private Label newCityTemp;

    @FXML
    private Label newCityPressure;

    @FXML
    private Label newCityHumidity;

    @FXML
    private Label newCityWind;

    @FXML
    private Label tomorrow;

    @FXML
    private Label temp1;

    @FXML
    private Label pres1;


    @FXML
    void searchMyCity() {
        if(myCity.getText().equals("")) {
            return;
        }
        else {
            try{
                this.current_city = myCity.getText().trim();
                myCity.setText((myCity.getText().trim()).toUpperCase());
                weatherData = new WeatherData(current_city);
                fillWeatherData();
            }catch (Exception e){
                myCity.setText("BLAD");
                clearLabels();
            }
        }
    }

    @FXML
    void searchNewCity() {
        if(newCity.getText().equals("")) {
            return;
        }
        else {
            try{
                this.searched_city = newCity.getText().trim();
                newCity.setText((newCity.getText().trim()).toUpperCase());
                weatherData = new WeatherData(searched_city);
                fillWeatherData();
            }catch (Exception e){
                newCity.setText("BLAD");
                clearLabels();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        current_city = myCity.getText().toUpperCase();
        myCity.setDisable(false);
        weatherData = new WeatherData(current_city);
        try{
            fillWeatherData();
        } catch (Exception e){
            myCity.setText("BLAD - brak polaczenia");
            clearLabels();
            myCity.setText("");
        }
    }

    private void fillWeatherData() {
        weatherData.gatherWeather();
        myCity.setText(weatherData.getCity().toUpperCase());
        myCityTemp.setText(weatherData.getTemperature().toString()+"°C");
        myCityPressure.setText(weatherData.getPressure()+" hPa");
        myCityHumidity.setText(weatherData.getHumidity()+"%");
        myCityWind.setText(weatherData.getWindSpeed()+" m/s");
        today.setText(weatherData.getDay().toUpperCase());

    }

    private void clearLabels() {
        myCityName.setText("");
        searchedCityName.setText("");
        myCityTemp.setText("");
        myCityPressure.setText("");
        myCityHumidity.setText("");
        myCityWind.setText("");
        newCityTemp.setText("");
        newCityPressure.setText("");
        newCityHumidity.setText("");
        newCityWind.setText("");
    }
}