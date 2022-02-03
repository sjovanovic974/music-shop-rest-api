package sasa.jovanovic.musicshop.services;


import sasa.jovanovic.musicshop.models.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    Country getCountryById(Long id);
}
