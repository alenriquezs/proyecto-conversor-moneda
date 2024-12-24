import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class ExchangeRate {
    @SerializedName("conversion_rates")
    private Map<String, Double> conversion_rates;

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }
}
