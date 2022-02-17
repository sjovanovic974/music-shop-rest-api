package sasa.jovanovic.musicshop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sasa.jovanovic.musicshop.models.State;
import sasa.jovanovic.musicshop.services.StateService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StateController.class)
class StateControllerIntegrationTest {

    @MockBean
    private StateService service;

    @Autowired
    private MockMvc mockMvc;

    private List<State> states;

    @BeforeEach
    void setUp() {

        State alabama = new State();
        alabama.setName("Alabama");

        State illinois = new State();
        illinois.setName("Illinois");

        states = new ArrayList<>();

        states.add(alabama);
        states.add(illinois);
    }

    @Test
    @DisplayName("Should return states by country code")
    void getStatesByCountryCode() throws Exception {

        when(service.findByCountryCode(any(String.class))).thenReturn(states);

        mockMvc.perform(get("/countries/states")
                .contentType("application/json")
                .param("code", "US"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(service).findByCountryCode(any(String.class));
    }

}