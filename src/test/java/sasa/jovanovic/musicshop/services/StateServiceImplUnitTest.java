package sasa.jovanovic.musicshop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sasa.jovanovic.musicshop.models.Country;
import sasa.jovanovic.musicshop.models.State;
import sasa.jovanovic.musicshop.repos.StateRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StateServiceImplUnitTest {

    @Mock
    private StateRepository repository;

    @InjectMocks
    private StateServiceImpl service;

    @Captor
    private ArgumentCaptor<String> captor;

    private List<State> tempList;

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

        State state1 = new State();
        state1.setId(1L);
        state1.setName("Alabama");
        state1.setCountry(us);

        State state2 = new State();
        state2.setId(2L);
        state2.setName("Ohio");
        state2.setCountry(us);

        State state3 = new State();
        state3.setId(3L);
        state3.setName("Bavaria");
        state3.setCountry(germany);

        tempList.add(state1);
        tempList.add(state2);
        tempList.add(state3);
    }


    @Test
    @DisplayName("Should find states by country code")
    void findByCountryCode() {

        when(repository.findByCountryCode("US")).thenReturn(List.of(tempList.get(0), tempList.get(1)));
        when(repository.findByCountryCode("DE")).thenReturn(List.of(tempList.get(2)));

        List<State> usStates = service.findByCountryCode("US");
        List<State> germanStates = service.findByCountryCode("DE");

        verify(repository, times(2)).findByCountryCode(captor.capture());

        List<String> countryCodes = captor.getAllValues();

        assertThat(usStates.size()).isEqualTo(2);
        assertThat(countryCodes.get(0)).isEqualTo("US");

        assertThat(germanStates.size()).isEqualTo(1);
        assertThat(countryCodes.get(1)).isEqualTo("DE");
    }
}