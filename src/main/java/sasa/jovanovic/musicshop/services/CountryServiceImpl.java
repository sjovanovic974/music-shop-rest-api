package sasa.jovanovic.musicshop.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sasa.jovanovic.musicshop.errorhandling.NotFoundException;
import sasa.jovanovic.musicshop.models.Country;
import sasa.jovanovic.musicshop.repos.CountryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> {
            log.error("Country was not found!");
            return new NotFoundException("Country was not found!");
        });
    }
}
