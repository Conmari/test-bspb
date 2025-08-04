package scari.corp.jsonPractic.officeRate;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "buyRate",
        "cbRate",
        "currencyCode",
        "currencyCodeSecond",
        "lotSize",
        "sellRate",
        "transactionVolume"
})
@Generated("jsonschema2pojo")
public class Rate {

    @JsonProperty("buyRate")
    private Double buyRate;
    @JsonProperty("cbRate")
    private Double cbRate;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("currencyCodeSecond")
    private String currencyCodeSecond;
    @JsonProperty("lotSize")
    private Integer lotSize;
    @JsonProperty("sellRate")
    private Double sellRate;
    @JsonProperty("transactionVolume")
    private Integer transactionVolume;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("buyRate")
    public Double getBuyRate() {
        return buyRate;
    }

    @JsonProperty("buyRate")
    public void setBuyRate(Double buyRate) {
        this.buyRate = buyRate;
    }

    @JsonProperty("cbRate")
    public Double getCbRate() {
        return cbRate;
    }

    @JsonProperty("cbRate")
    public void setCbRate(Double cbRate) {
        this.cbRate = cbRate;
    }

    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currencyCode")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("currencyCodeSecond")
    public String getCurrencyCodeSecond() {
        return currencyCodeSecond;
    }

    @JsonProperty("currencyCodeSecond")
    public void setCurrencyCodeSecond(String currencyCodeSecond) {
        this.currencyCodeSecond = currencyCodeSecond;
    }

    @JsonProperty("lotSize")
    public Integer getLotSize() {
        return lotSize;
    }

    @JsonProperty("lotSize")
    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

    @JsonProperty("sellRate")
    public Double getSellRate() {
        return sellRate;
    }

    @JsonProperty("sellRate")
    public void setSellRate(Double sellRate) {
        this.sellRate = sellRate;
    }

    @JsonProperty("transactionVolume")
    public Integer getTransactionVolume() {
        return transactionVolume;
    }

    @JsonProperty("transactionVolume")
    public void setTransactionVolume(Integer transactionVolume) {
        this.transactionVolume = transactionVolume;
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
