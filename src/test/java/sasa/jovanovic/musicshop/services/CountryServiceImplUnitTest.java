package sasa.jovanovic.musicshop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sasa.jovanovic.musicshop.errorhandling.NotFoundException;
import sasa.jovanovic.musicshop.models.Country;
import sasa.jovanovic.musicshop.repos.CountryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplUnitTest {

    @Mock
    CountryRepository repository;

    @InjectMocks
    CountryServiceImpl service;

    List<Country> tempList;

    @BeforeEach
    public void initEach() {
        tempList = new ArrayList<>();

        Country us = new Country();
        us.setId(1L);
        us.setName("United States of America");
        us.setCode("US");

        Country germany = new Country();
        germany.setId(2L);
        germany.setName("Germany");
        germany.setCode("DE");

        tempList.add(us);
        tempList.add(germany);
    }


    @Test
    @DisplayName("Should get all countries")
    void getAllCountries() {

        when(repository.findAll()).thenReturn(tempList);

        List<Country> countries = service.getAllCountries();

        assertThat(countries.size()).isEqualTo(tempList.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find country by given id")
    void getCountryById() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(tempList.get(0)));
        Country returnedCountry = service.getCountryById(anyLong());

        assertThat(returnedCountry.getId()).isEqualTo(tempList.get(0).getId());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should throw NotFoundException if country was not found")
    void countryNotFoundById() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            service.getCountryById(anyLong());
        }).isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Country was not found!");

        verify(repository, times(1)).findById(anyLong());
    }

}