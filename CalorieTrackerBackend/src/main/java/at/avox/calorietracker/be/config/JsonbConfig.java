package at.avox.calorietracker.be.config;


import jakarta.json.bind.config.PropertyNamingStrategy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonbConfig {

    public static final jakarta.json.bind.JsonbConfig CONFIG = new jakarta.json.bind.JsonbConfig()
            .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
            .withNullValues(true);
}
