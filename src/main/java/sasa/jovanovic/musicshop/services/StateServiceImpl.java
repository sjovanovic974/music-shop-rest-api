package sasa.jovanovic.musicshop.services;

import org.springframework.stereotype.Service;
import sasa.jovanovic.musicshop.models.State;
import sasa.jovanovic.musicshop.repos.StateRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<State> findByCountryCode(String code) {
        return stateRepository.findByCountryCode(code);
    }
}
