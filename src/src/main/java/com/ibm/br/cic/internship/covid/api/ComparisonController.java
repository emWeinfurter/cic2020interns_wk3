/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.br.cic.internship.covid.api;

import com.ibm.br.cic.internship.covid.api.payload.ComparisonPayload;
import com.ibm.br.cic.internship.covid.model.CompareBy;
import com.ibm.br.cic.internship.covid.model.Comparison;
import com.ibm.br.cic.internship.covid.model.Country;
import com.ibm.br.cic.internship.covid.model.Summary;
import com.ibm.br.cic.internship.covid.service.Covid19ApiService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JamieRichardson
 */
@RestController
@RequestMapping("api/v1")
public final class ComparisonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Covid19ApiService covid19ApiService;

    @Autowired
    public ComparisonController(Covid19ApiService covid19ApiService) {
        this.covid19ApiService = covid19ApiService;
    }

    @ApiOperation(value = "Get comparison", response = ResponseEntity.class)
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/comparison", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getComparison(@RequestBody ComparisonPayload payload) {

    	//Elise Weinfurter changes begin here
    	//removed 'final' from List<Country>, CompareBy, and Summary
        List<Country> countries = new ArrayList<>();
        CompareBy compareBy = CompareBy.valueOf(payload.compareBy);
        Summary summary = this.covid19ApiService.getSummary();
        //Elise Weinfurter end of changes

        Optional<Country> $countries = summary.getCountries().stream().
                filter(country -> payload.countryCodes.contains(country.getCountryCode()))
                .findAny();

        if (!$countries.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final Comparison comparison = this.covid19ApiService.getComparison(countries, compareBy);
        return new ResponseEntity<>(comparison, HttpStatus.OK);
    }
}
