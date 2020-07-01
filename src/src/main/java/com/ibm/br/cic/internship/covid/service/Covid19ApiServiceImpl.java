package com.ibm.br.cic.internship.covid.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ibm.br.cic.internship.covid.model.CompareBy;
import com.ibm.br.cic.internship.covid.model.Comparison;
import com.ibm.br.cic.internship.covid.model.Country;
import com.ibm.br.cic.internship.covid.model.Global;
import com.ibm.br.cic.internship.covid.model.Summary;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author JamieRichardson
 */
@Service
public class Covid19ApiServiceImpl implements Covid19ApiService {

    @Value("${covid19api.uri}")
    private String uri;

    @Override
    public Summary getSummary() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(uri, String.class);
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

        String $global = new Gson().toJson(jsonObject.get("Global").getAsJsonObject());
        String $countries = new Gson().toJson(jsonObject.get("Countries").getAsJsonArray());

        Global global = new Gson().fromJson($global, Global.class);

        List<Country> countries = new Gson().fromJson($countries, new TypeToken<ArrayList<Country>>() {
        }.getType());

        return new Summary(global, countries);
    }

    @Override
    public Comparison getComparison(List<Country> countries, CompareBy compareBy) {
        //Your work goes here 
    	if (compareBy == CompareBy.TotalConfirmed) {
    		Collections.sort(countries, new Comparator<Country>() {
        		@Override
        		public int compare(Country a, Country b) {
        			return b.getTotalConfirmed()-a.getTotalConfirmed();
        		}
        	});
    	} else if (compareBy == CompareBy.TotalDeaths) {
    		Collections.sort(countries, new Comparator<Country>() {
        		@Override
        		public int compare(Country a, Country b) {
        			return b.getTotalDeaths()-a.getTotalDeaths();
        		}
        	});
    	} else if (compareBy == CompareBy.TotalRecovered) {
    		Collections.sort(countries, new Comparator<Country>() {
        		@Override
        		public int compare(Country a, Country b) {
        			return b.getTotalRecovered()-a.getTotalRecovered();
        		}
        	});
    	} else throw new UnsupportedOperationException("Not supported yet.");
      
    	return new Comparison(countries);
    }
}
