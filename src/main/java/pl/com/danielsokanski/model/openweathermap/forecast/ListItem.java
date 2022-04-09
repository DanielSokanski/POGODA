
package pl.com.danielsokanski.model.openweathermap.forecast;

import com.fasterxml.jackson.annotation.*;

import pl.com.danielsokanski.model.openweathermap.common.Main;
import pl.com.danielsokanski.model.openweathermap.common.Weather;
import pl.com.danielsokanski.model.openweathermap.common.Wind;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dt",
    "main",
    "weather",
    "clouds",
    "wind",
    "visibility",
    "pop",
    "sys",
    "dt_txt",
    "rain",
    "snow"
})
public class ListItem {

    @JsonProperty("dt")
    private Long dt;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("weather")
    private java.util.List<Weather> weather = null;

    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("pop")
    private Double pop;
    @JsonProperty("dt_txt")
    private String dtTxt;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dt")
    public Long getDt() {
        return dt;
    }

    @JsonProperty("dt")
    public void setDt(Long dt) {
        this.dt = dt;
    }

    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    @JsonProperty("weather")
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }



    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @JsonProperty("visibility")
    public Integer getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("pop")
    public Double getPop() {
        return pop;
    }

    @JsonProperty("pop")
    public void setPop(Double pop) {
        this.pop = pop;
    }

    @JsonProperty("dt_txt")
    public String getDtTxt() {
        return dtTxt;
    }

    @JsonProperty("dt_txt")
    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }



    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
