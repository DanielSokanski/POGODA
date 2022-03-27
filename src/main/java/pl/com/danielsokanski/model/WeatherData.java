package pl.com.danielsokanski.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.com.danielsokanski.model.openweathermap.daily.OneCall;
import pl.com.danielsokanski.model.openweathermap.direct.Direct;
import pl.com.danielsokanski.model.openweathermap.forecast.Forecast;
import pl.com.danielsokanski.model.openweathermap.weather.CurrentWeather;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


public class WeatherData {

    public static final String API_KEY = "9f50290acb8f2bf479a301a08f4d725b";
    private static final ObjectMapper mapper = new ObjectMapper();

    private String city;
    private CurrentWeather currentWeather;
    private OneCall oneCall;


    public WeatherData(String current_city) {
        this.city = current_city;
    }

    public void loadData(){
        this.gatherForecast();
        this.gatherWeather();
    }

    private void gatherForecast() {
        try {
            String directJson = jsonFromWeb(String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", city, API_KEY));
            Direct[] direct = mapper.readValue(directJson, Direct[].class);
            this.gatherOneCall(direct[0].getLat(), direct[0].getLon());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void gatherWeather() {
        try {
            String currentWeatherJson = jsonFromWeb(String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&lang=pl&units=metric", city, API_KEY));
            CurrentWeather currentWeather = mapper.readValue(currentWeatherJson, CurrentWeather.class);
            this.currentWeather = currentWeather;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void gatherOneCall(Double lat, Double lon){
        try {
            String oneCallJson = jsonFromWeb(String.format("https://api.openweathermap.org/data/2.5/onecall?lat=%s&lon=%s&exclude=current,minutely,hourly,alerts&appid=%s&lang=pl&units=metric&cnt=3", lat, lon, API_KEY));
            OneCall oneCall = mapper.readValue(oneCallJson, OneCall.class);
            this.oneCall = oneCall;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private String jsonFromWeb(String url) {
        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonData = readAll(bufferedReader);
            return jsonData;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int no;
        while ((no = reader.read()) != -1) {
            stringBuilder.append((char) no);
        }
        return stringBuilder.toString();
    }


    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }


    public OneCall getOneCall() {
        return oneCall;
    }

    public void setOneCall(OneCall oneCall) {
        this.oneCall = oneCall;
    }
}
