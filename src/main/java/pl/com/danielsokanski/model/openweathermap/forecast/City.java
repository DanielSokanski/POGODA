
package pl.com.danielsokanski.model.openweathermap.forecast;

import com.fasterxml.jackson.annotation.*;
//import pl.com.danielsokanski.model.openweathermap.common.Coord;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "coord",
    "country",
    "population",
    "timezone",
    "sunrise",
    "sunset"
})
public class City {

    @JsonProperty("name")
    private String name;




    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }


}
