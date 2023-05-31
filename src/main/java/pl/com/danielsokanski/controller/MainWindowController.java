package pl.com.danielsokanski.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import pl.com.danielsokanski.model.WeatherService;
import pl.com.danielsokanski.model.openweathermap.common.OneCallData;
import pl.com.danielsokanski.model.openweathermap.daily.Daily;
import pl.com.danielsokanski.model.openweathermap.daily.Forecast;
import static pl.com.danielsokanski.Messages.BLAD;
import pl.com.danielsokanski.model.openweathermap.weather.CurrentWeather;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class MainWindowController implements Initializable {

    @FXML
    private AnchorPane mainPane;

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
    private ScrollPane myCityScrollPane;

    @FXML
    private HBox myCityHbox;

    @FXML
    private ScrollPane newCityScrollPane;

    @FXML
    private HBox newCityHbox;

    @FXML
    private ImageView myCityIcon;

    @FXML
    private ImageView newCityIcon;

    @FXML
    private Label Date;

    @FXML
    private Label Date1;


    @FXML
    void searchMyCity() {
        if (!myCity.getText().equals("")) {
            try {
                String currentCity = myCity.getText().trim();
                myCity.setText((myCity.getText().trim()).toUpperCase());
                WeatherService weatherService = new WeatherService(currentCity);
                fillMyWeatherData(weatherService);
            } catch (Exception e) {
                myCity.setText(BLAD);
                clearLabels();
            }
        }
    }

    @FXML
    void searchNewCity() {
        if (!newCity.getText().equals("")) {
            try {
                String searchedCity = newCity.getText().trim();
                newCity.setText((newCity.getText().trim()).toUpperCase());
                WeatherService weatherService = new WeatherService(searchedCity);
                fillNewWeatherData(weatherService);
            } catch (Exception e) {
                newCity.setText(BLAD);
                clearLabels();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearLabels();
    }

    private void fillMyWeatherData(WeatherService weatherService){
        weatherService.loadData();
        Forecast forecast = weatherService.getOneCall();
        CurrentWeather currentWeather = weatherService.getCurrentWeather();
        setFields(currentWeather);
        setHbox();
        List<OneCallData> oneCallData = forecast.getDaily().stream().limit(4)
                .map((daily) -> {
                    long unixSecond = daily.getDt();
                    String formattedDate = setNextDates(unixSecond);
                    Label date1 = new Label(String.format("%s", formattedDate));
                    Label temp = new Label(String.format("Temp: %s °C", daily.getTemp().getDay()));
                    Label windSpeed = new Label(String.format("Wiatr: %s m/s", daily.getWindSpeed()));
                    Image image = null;
                    try {
                        image = setImage(daily);
                    } catch (URISyntaxException | FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    myCityIcon.setImage(image);
                    return new OneCallData(date1, temp, windSpeed, image);
                })
                .collect(Collectors.toList());
        oneCallData.forEach(element -> {
            VBox vBox = new VBox();
            ImageView imageView = new ImageView(element.getImage());
            imageView.setScaleX(1);
            imageView.setScaleY(1);
            vBox.setSpacing(2);
            vBox.getChildren().add(imageView);
            vBox.getChildren().add(element.getDt());
            vBox.getChildren().add(element.getTemp());
            vBox.getChildren().add(element.getWindSpeed());
            vBox.setAlignment(Pos.CENTER);
            myCityHbox.getChildren().add(vBox);
        });
        myCityScrollPane.setContent(myCityHbox);
        myCityScrollPane.setFitToHeight(true);
    }

    private Image setImage(Daily daily) throws URISyntaxException, FileNotFoundException {
        Image image;
        URL url = getClass().getResource(String.format("/img/%s@2x.png",daily.getWeather().get(0).getIcon()));
        assert url != null;
        File file = Paths.get(url.toURI()).toFile();
        image = new Image(new FileInputStream(file));
        return image;
    }

    private String setNextDates(long unixSecond){
        Date date = new Date(unixSecond*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    private void setHbox() {
            myCityHbox = new HBox();
            myCityHbox.setSpacing(10);
            myCityHbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            newCityHbox = new HBox();
            newCityHbox.setSpacing(10);
            newCityHbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    private void setFields(CurrentWeather currentWeather) {
        myCity.setText("");
        myCityName.setText(currentWeather.getName().toUpperCase());
        myCityTemp.setText(currentWeather.getMain().getTemp().toString() + "°C");
        myCityPressure.setText(currentWeather.getMain().getPressure() + " hPa");
        myCityHumidity.setText(currentWeather.getMain().getHumidity() + "%");
        myCityWind.setText(currentWeather.getWind().getSpeed() + " m/s");
        setDate(currentWeather);
    }

    private void setDate(CurrentWeather currentWeather) {
        long today = currentWeather.getDt();
        Date todaysdate = new Date(today*1000L);
        SimpleDateFormat sdfToday = new SimpleDateFormat("yyyy-MM-dd");
        String today_date = sdfToday.format(todaysdate);
        if(currentWeather.getName().equals(myCityName.getText())) {
            Date1.setText(today_date);
        } else {
            Date.setText(today_date);
        }
    }

    private void fillNewWeatherData(WeatherService weatherService) {
        weatherService.loadData();
        Forecast forecast = weatherService.getOneCall();
        CurrentWeather currentWeather = weatherService.getCurrentWeather();
        setNewFields(currentWeather);
        setHbox();
        List<OneCallData> oneCallData = forecast.getDaily().stream().limit(4)
                .map((daily) -> {
                    long unixSecond = daily.getDt();
                    String formattedDate = setNextDates(unixSecond);
                    Label date1 = new Label(String.format("%s", formattedDate));
                    Label temp = new Label(String.format("Temp: %s", daily.getTemp().getDay()));
                    Label windSpeed = new Label(String.format("Wiatr: %s m/s", daily.getWindSpeed()));
                    Image image = null;
                    try {
                        image = setImage(daily);
                    } catch (URISyntaxException | FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    newCityIcon.setImage(image);
                    return new OneCallData(date1, temp, windSpeed, image);
                }).collect(Collectors.toList());

        oneCallData.forEach(element -> {
            VBox vBox = new VBox();
            ImageView imageView = new ImageView(element.getImage());
            imageView.setScaleX(1);
            imageView.setScaleY(1);
            vBox.setSpacing(2);
            vBox.getChildren().add(imageView);
            vBox.getChildren().add(element.getDt());
            vBox.getChildren().add(element.getTemp());
            vBox.getChildren().add(element.getWindSpeed());
            vBox.setAlignment(Pos.CENTER);
            newCityHbox.getChildren().add(vBox);
        });
        newCityScrollPane.setContent(newCityHbox);
        newCityScrollPane.setFitToHeight(true);
    }

    private void setNewFields(CurrentWeather currentWeather) {
        newCity.setText("");
        searchedCityName.setText(currentWeather.getName().toUpperCase());
        newCityTemp.setText(currentWeather.getMain().getTemp().toString() + "°C");
        newCityPressure.setText(currentWeather.getMain().getPressure() + " hPa");
        newCityHumidity.setText(currentWeather.getMain().getHumidity() + "%");
        newCityWind.setText(currentWeather.getWind().getSpeed() + " m/s");
        setDate(currentWeather);
    }

    public void clearLabels() {
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