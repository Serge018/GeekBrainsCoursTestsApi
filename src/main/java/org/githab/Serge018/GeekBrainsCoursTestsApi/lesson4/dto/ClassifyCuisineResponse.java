package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson4.dto;

import java.util.List;
//import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cuisine",
        "cuisines",
        "confidence"
})
//@Generated("jsonschema2pojo")
public class ClassifyCuisineResponse {

    @JsonProperty("cuisine")
    private String cuisine;
    @JsonProperty("cuisines")
    private List<String> cuisines;
    @JsonProperty("confidence")
    private Double confidence;

    /**
     * No args constructor for use in serialization
     *
     */
    public ClassifyCuisineResponse() {
    }

    /**
     *
     * @param confidence
     * @param cuisine
     * @param cuisines
     */
    public ClassifyCuisineResponse(String cuisine, List<String> cuisines, Double confidence) {
        super();
        this.cuisine = cuisine;
        this.cuisines = cuisines;
        this.confidence = confidence;
    }

    @JsonProperty("cuisine")
    public String getCuisine() {
        return cuisine;
    }

    @JsonProperty("cuisine")
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @JsonProperty("cuisines")
    public List<String> getCuisines() {
        return cuisines;
    }

    @JsonProperty("cuisines")
    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    @JsonProperty("confidence")
    public Double getConfidence() {
        return confidence;
    }

    @JsonProperty("confidence")
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.cuisine == null)? 0 :this.cuisine.hashCode()));
        result = ((result* 31)+((this.cuisines == null)? 0 :this.cuisines.hashCode()));
        result = ((result* 31)+((this.confidence == null)? 0 :this.confidence.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ClassifyCuisineResponse) == false) {
            return false;
        }
        ClassifyCuisineResponse rhs = ((ClassifyCuisineResponse) other);
        return ((((this.cuisine == rhs.cuisine)||((this.cuisine!= null)&&this.cuisine.equals(rhs.cuisine)))&&((this.cuisines == rhs.cuisines)||((this.cuisines!= null)&&this.cuisines.equals(rhs.cuisines))))&&((this.confidence == rhs.confidence)||((this.confidence!= null)&&this.confidence.equals(rhs.confidence))));
    }

}