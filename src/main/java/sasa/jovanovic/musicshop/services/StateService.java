package sasa.jovanovic.musicshop.services;

import sasa.jovanovic.musicshop.models.State;

import java.util.List;

public interface StateService {

    List<State> findByCountryCode(String code);
}

