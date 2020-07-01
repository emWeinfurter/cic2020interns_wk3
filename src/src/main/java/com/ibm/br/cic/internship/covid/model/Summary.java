package com.ibm.br.cic.internship.covid.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * @author JamieRichardson
 */
public @Data class Summary {

    public Summary(Global global, List<Country> countries) {
        this.global = global;
        this.countries = countries;
    }

    //Elise Weinfurter changes begin here
    //changed Global and List<Country> to public
    //added getters and setters to global and countries
    @JsonProperty("Global")
    public Global global;

    @JsonProperty("Countries")
    public List<Country> countries;

	public Global getGlobal() {
		return global;
	}

	public void setGlobal(Global global) {
		this.global = global;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	//Elise Weinfurter changes end
}
