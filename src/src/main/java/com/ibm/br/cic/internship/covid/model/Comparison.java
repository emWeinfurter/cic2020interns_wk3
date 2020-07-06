package com.ibm.br.cic.internship.covid.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * @author JamieRichardson
 */
public @Data class Comparison {
	//Elise Weinfurter changes
	//Comparison class constructor
	//added getters and setters for "Countries"
	public Comparison(List<Country> countries) {
        this.countries = countries;
    }

    @JsonProperty("Countries")
    private List<Country> countries;

    /*public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}*/
	//Elise Weinfurter changes end

	private CompareBy compareBy;
}
