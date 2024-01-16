package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Customer {

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"name\":\"" + name +
                "\", \"longitude\":\"" + longitude +
                "\", \"latitude\":\"" + latitude +
                "\", \"contact\":\""+ contact +"\""+
                '}';
    }

    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

    @JsonProperty("longitude")
    String longitude;

    @JsonProperty("latitude")
    String latitude;

    @JsonProperty("contact")
    String contact;
}
