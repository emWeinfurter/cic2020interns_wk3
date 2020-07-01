package com.ibm.br.cic.internship.covid.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author JamieRichardson
 */
public @Data class Country {

	//Elise Weinfurter changes begin
	//changed country and countryCode to public
	//added getters and setters
    @JsonProperty("Country")
    @SerializedName("Country")
    public String country;

    @JsonProperty("CountryCode")
    @SerializedName("CountryCode")
    public String countryCode;

    public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	//Elise Weinfurter changes end

	@JsonProperty("Slug")
    @SerializedName("Slug")
    private String slug;

    @JsonProperty("NewConfirmed")
    @SerializedName("NewConfirmed")
    private int newConfirmed;

    @JsonProperty("TotalConfirmed")
    @SerializedName("TotalConfirmed")
    private int totalConfirmed;

    @JsonProperty("NewDeaths")
    @SerializedName("NewDeaths")
    private int newDeaths;

    @JsonProperty("TotalDeaths")
    @SerializedName("TotalDeaths")
    private int totalDeaths;

    @JsonProperty("NewRecovered")
    @SerializedName("NewRecovered")
    private int newRecovered;

    @JsonProperty("TotalRecovered")
    @SerializedName("TotalRecovered")
    private int totalRecovered;

    @JsonProperty("Date")
    @SerializedName("Date")
    private String date;
}
