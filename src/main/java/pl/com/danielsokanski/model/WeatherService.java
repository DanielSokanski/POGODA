package pl.com.danielsokanski.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.com.danielsokanski.Messages;
import pl.com.danielsokanski.model.openweathermap.daily.Forecast;
import pl.com.danielsokanski.model.openweathermap.direct.Direct;

import pl.com.danielsokanski.model.openweathermap.weather.CurrentWeather;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static pl.com.danielsokanski.Messages.*;


public class WeatherService {

    private static final ObjectMapper mapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    private String city;
    private CurrentWeather currentWeather;
    private Forecast forecast;


    public WeatherService(String current_city) {
        city = current_city;
    }

    public void loadData(){
        gatherForecast();
        gatherWeather();
    }

    private void gatherForecast() {
        try {
            String directJson = jsonFromWeb(String.format(OWM() + "/geo/1.0/direct?q=%s&limit=1&appid=%s", city, API_KEY));
            Direct[] direct = mapper.readValue(directJson, Direct[].class);
            this.gatherOneCall(direct[0].getLat(), direct[0].getLon());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println(PROBLEM_JSON);
        }
    }

    private void gatherWeather() {
        try {
            String currentWeatherJson = jsonFromWeb(String.format(OWM() + "/data/2.5/weather?q=%s&appid=%s&lang=pl&units=metric", city, API_KEY));
            this.currentWeather = mapper.readValue(currentWeatherJson, CurrentWeather.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println(PROBLEM_JSON);

        }
    }

    private String OWM() {
        return "https://api.openweathermap.org";
    }

    private void gatherOneCall(Double lat, Double lon){
        try {
            String oneCallJson = jsonFromWeb(String.format(OWM() + "/data/2.5/onecall?lat=%s&lon=%s&exclude=current,minutely,hourly,alerts&appid=%s&lang=pl&units=metric&cnt=3", lat, lon, API_KEY));
            this.forecast = mapper.readValue(oneCallJson, Forecast.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private String jsonFromWeb(String url) {
        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return readAll(bufferedReader);
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


    public Forecast getOneCall() {
        return forecast;
    }

}
