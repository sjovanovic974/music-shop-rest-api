package sasa.jovanovic.musicshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sasa.jovanovic.musicshop.models.State;
import sasa.jovanovic.musicshop.services.StateService;

import java.util.List;

@RestController
@RequestMapping("countries/states")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping()
    List<State> getStatesByCountryCode(@RequestParam(name = "code") String code) {
        return stateService.findByCountryCode(code);
    }

}
