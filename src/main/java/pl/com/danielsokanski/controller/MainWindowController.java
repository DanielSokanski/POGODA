package pl.com.danielsokanski.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.com.danielsokanski.model.ForecastTableItem;
import pl.com.danielsokanski.model.WeatherData;
import pl.com.danielsokanski.model.openweathermap.forecast.Forecast;
import pl.com.danielsokanski.model.openweathermap.forecast.ListItem;
import pl.com.danielsokanski.model.openweathermap.weather.CurrentWeather;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    String current_city;
    String searched_city;

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
    private TableView<ForecastTableItem> myCityForecastTable;

    @FXML
    private TableView<ForecastTableItem> newCityForecastTable;

    @FXML
    void searchMyCity() {
        if (myCity.getText().equals("")) {
            return;
        } else {
            try {
                this.current_city = myCity.getText().trim();
                myCity.setText((myCity.getText().trim()).toUpperCase());
                WeatherData weatherData = new WeatherData(current_city);
                fillMyWeatherData(weatherData);
            } catch (Exception e) {
                myCity.setText("BLAD");
                clearLabels();
            }
        }
    }

    @FXML
    void searchNewCity() {
        if (newCity.getText().equals("")) {
            return;
        } else {
            try {
                this.searched_city = newCity.getText().trim();
                newCity.setText((newCity.getText().trim()).toUpperCase());
                WeatherData weatherData = new WeatherData(searched_city);
                fillNewWeatherData(weatherData);
            } catch (Exception e) {
                newCity.setText("BLAD");
                clearLabels();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myCity.setText("");
        myCityTemp.setText("");
        myCityPressure.setText("");
        myCityHumidity.setText("");
        myCityWind.setText("");
        today.setText("");
        newCity.setText("");
        newCityTemp.setText("");
        newCityPressure.setText("");
        newCityHumidity.setText("");
        newCityWind.setText("");
        today.setText("");
        initTable(myCityForecastTable);
        initTable(newCityForecastTable);
    }

    private void fillMyWeatherData(WeatherData weatherData) {
        weatherData.loadData();
        Forecast forecast = weatherData.getForecast();
        CurrentWeather currentWeather = weatherData.getCurrentWeather();
        myCity.setText(currentWeather.getName().toUpperCase());
        myCityTemp.setText(currentWeather.getMain().getTemp().toString() + "°C");
        myCityPressure.setText(currentWeather.getMain().getPressure() + " hPa");
        myCityHumidity.setText(currentWeather.getMain().getHumidity() + "%");
        myCityWind.setText(currentWeather.getWind().getSpeed() + " m/s");
        DayOfWeek day = Instant.ofEpochSecond(currentWeather.getDt()).atZone(ZoneId.systemDefault()).getDayOfWeek();
        today.setText(day.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pl-PL")).toUpperCase());
        ObservableList<ForecastTableItem> data = FXCollections.observableArrayList();
        forecast.getList().forEach((item) -> {
            data.add(new ForecastTableItem(item.getDtTxt(), item.getMain().getTemp(), item.getMain().getPressure(), item.getMain().getHumidity(), item.getWind().getSpeed()));
        });
        this.myCityForecastTable.setItems(data);
    }

    private void initTable(TableView tableView) {
        TableColumn<ForecastTableItem, String> dateColumn = new TableColumn<>("Data");
        dateColumn.setCellValueFactory(new PropertyValueFactory<ForecastTableItem, String>("date"));
        TableColumn<ForecastTableItem, String> tempColumn = new TableColumn<>("Temperatura");
        tempColumn.setCellValueFactory(new PropertyValueFactory<ForecastTableItem, String>("temp"));
        TableColumn<ForecastTableItem, String> pressureColumn = new TableColumn<>("Ciśnienie");
        pressureColumn.setCellValueFactory(new PropertyValueFactory<ForecastTableItem, String>("pressure"));
        TableColumn<ForecastTableItem, String> humidityColumn = new TableColumn<>("Wilgotność");
        humidityColumn.setCellValueFactory(new PropertyValueFactory<ForecastTableItem, String>("humidity"));
        TableColumn<ForecastTableItem, String> windSpeedColumn = new TableColumn<>("Prędkość wiatru");
        windSpeedColumn.setCellValueFactory(new PropertyValueFactory<ForecastTableItem, String>("windSpeed"));

        dateColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        tempColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        pressureColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        humidityColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.18));
        windSpeedColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.22));

        tableView.getColumns().add(dateColumn);
        tableView.getColumns().add(tempColumn);
        tableView.getColumns().add(pressureColumn);
        tableView.getColumns().add(humidityColumn);
        tableView.getColumns().add(windSpeedColumn);
    }

    private void fillNewWeatherData(WeatherData weatherData) {
        weatherData.loadData();
        Forecast forecast = weatherData.getForecast();
        CurrentWeather currentWeather = weatherData.getCurrentWeather();
        newCity.setText(currentWeather.getName().toUpperCase());
        newCityTemp.setText(currentWeather.getMain().getTemp().toString() + "°C");
        newCityPressure.setText(currentWeather.getMain().getPressure() + " hPa");
        newCityHumidity.setText(currentWeather.getMain().getHumidity() + "%");
        newCityWind.setText(currentWeather.getWind().getSpeed() + " m/s");
        DayOfWeek day = Instant.ofEpochSecond(currentWeather.getDt()).atZone(ZoneId.systemDefault()).getDayOfWeek();
        today.setText(day.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pl-PL")).toUpperCase());
        ObservableList<ForecastTableItem> data = FXCollections.observableArrayList();
        forecast.getList().forEach((item) -> {
            data.add(new ForecastTableItem(item.getDtTxt(), item.getMain().getTemp(), item.getMain().getPressure(), item.getMain().getHumidity(), item.getWind().getSpeed()));
        });
        this.newCityForecastTable.setItems(data);
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