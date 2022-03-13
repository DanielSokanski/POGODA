
package pl.com.danielsokanski.model.openweathermap.direct;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "de",
    "pl",
    "cs"
})
public class LocalNames {

    @JsonProperty("de")
    private String de;
    @JsonProperty("pl")
    private String pl;
    @JsonProperty("cs")
    private String cs;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("de")
    public String getDe() {
        return de;
    }

    @JsonProperty("de")
    public void setDe(String de) {
        this.de = de;
    }

    @JsonProperty("pl")
    public String getPl() {
        return pl;
    }

    @JsonProperty("pl")
    public void setPl(String pl) {
        this.pl = pl;
    }

    @JsonProperty("cs")
    public String getCs() {
        return cs;
    }

    @JsonProperty("cs")
    public void setCs(String cs) {
        this.cs = cs;
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
