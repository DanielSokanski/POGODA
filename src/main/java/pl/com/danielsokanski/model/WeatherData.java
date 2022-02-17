package pl.com.danielsokanski.model;

import org.json.JSONException;
import org.json.JSONObject;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherData {
    private String city;
    private String day;
    private String tommorow;
    private Integer temperature;
    private String icon;
    private String description;
    private String windSpeed;
    private String cloudiness;
    private String pressure;
    private Integer temperature1;
    private String pressure1;
    

    public String getCity() {
        return city;
    }

    public String getDay() {
        return day;
    }
    public String getTommorow() {
        return tommorow;
    }

    public Integer getTemperature() {
        return temperature;
    }
    public Integer getTemperature1() {
        return temperature1;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getPressure() {
        return pressure;
    }
    public String getPressure1() {
        return pressure1;
    }

    public String getHumidity() {
        return humidity;
    }

    private String humidity;

    public WeatherData(String current_city) {
        this.city = current_city;
    }
    public String api = "9f50290acb8f2bf479a301a08f4d725b";
    public void gatherWeather(){
        int day=0;

        JSONObject jsonObject;
        JSONObject json_format;

        SimpleDateFormat date = new SimpleDateFormat("EEEE");
        Calendar calendar = Calendar.getInstance();
        try{
            jsonObject = jsonFromWeb("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+api+"&lang=pl&units=metric");
        } catch (IOException e){
            return;
        }
        json_format = jsonObject.getJSONObject("main");
        this.temperature = json_format.getInt("temp");
        this.pressure = json_format.get("pressure").toString();
        this.humidity = json_format.get("humidity").toString();
        json_format = jsonObject.getJSONObject("wind");
        this.windSpeed = json_format.get("speed").toString();
        json_format = jsonObject.getJSONObject("clouds");
        this.cloudiness = json_format.get("all").toString();

        calendar.add(Calendar.DATE, day);
        this.day = date.format(calendar.getTime());
        json_format = jsonObject.getJSONArray("weather").getJSONObject(0);

    }

    public JSONObject jsonFromWeb(String url) throws IOException, JSONException{
        InputStream inputStream = new URL(url).openStream();
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jSonData = readAll(bufferedReader);
            JSONObject json = new JSONObject(jSonData);
            return json;
        }finally {
         inputStream.close();
        }
    }


    private String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int no;
        while ((no = reader.read()) != -1){
            stringBuilder.append((char)no);
        }
        return stringBuilder.toString();
    }

}
