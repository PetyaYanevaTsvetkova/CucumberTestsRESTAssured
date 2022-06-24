package helper.POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EntryPOJO {
    @JsonProperty
    private String API;
    @JsonProperty
    private String Description;
    @JsonProperty
    private String Auth;
    @JsonProperty
    private Boolean HTTPS;
    @JsonProperty
    private String Cors;
    @JsonProperty
    private String Link;
    @JsonProperty
    private String Category;
}
