package sasa.jovanovic.musicshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sasa.jovanovic.musicshop.models.Country;
import sasa.jovanovic.musicshop.services.CountryServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = CountryController.class)
class CountryControllerIntegrationTest {

    @MockBean
    private CountryServiceImpl service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Country> countries;

    @BeforeEach
    void setUp() {
        Country usa = new Country();
        usa.setId(1L);
        usa.setName("United States of America");
        usa.setCode("US");

        Country germany = new Country();
        germany.setId(2L);
        germany.setName("Germany");
        germany.setCode("DE");

        Country turkey = new Country();
        turkey.setId(3L);
        turkey.setName("Turkey");
        turkey.setCode("TK");

        countries = new ArrayList<>();

        countries.add(usa);
        countries.add(germany);
        countries.add(turkey);
    }

    @Test
    @DisplayName("Should return all countries")
    void getAllCountries() throws Exception {

        when(service.getAllCountries()).thenReturn(countries);

        mockMvc.perform(get("/countries")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()", is(countries.size())));

        verify(service).getAllCountries();
    }

    @Test
    @DisplayName("Should return country by given id")
    void getCountryById() throws Exception {

        when(service.getCountryById(anyLong())).thenReturn(countries.get(0));

        mockMvc.perform(get("/countries/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("United States of America"))
                .andExpect(jsonPath("$.code").value("US"));

        verify(service).getCountryById(anyLong());
    }
}