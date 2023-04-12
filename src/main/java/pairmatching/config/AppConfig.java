package pairmatching.config;

import pairmatching.controller.FrontController;
import pairmatching.controller.PairMatchingController;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchingResultRepository;
import pairmatching.service.PairMatchingService;

public class AppConfig {

    private final FrontController frontController;
    private final PairMatchingController pairMatchingController;
    private final PairMatchingService pairMatchingService;
    private final MatchingResultRepository matchingResultRepository;
    private final CrewRepository crewRepository;

    public AppConfig() {
        //repository
        crewRepository = new CrewRepository();
        matchingResultRepository = new MatchingResultRepository();
        //service
        pairMatchingService = new PairMatchingService(crewRepository, matchingResultRepository);
        //controller
        pairMatchingController = new PairMatchingController(pairMatchingService);
        frontController = new FrontController(pairMatchingController);
        init();
    }

    public FrontController getFrontController() {
        return frontController;
    }

    private void init() {
        crewRepository.init();
    }
}
