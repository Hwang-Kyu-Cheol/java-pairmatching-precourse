package pairmatching.config;

import pairmatching.controller.FrontController;
import pairmatching.controller.PairMatchingController;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchingResultRepository;
import pairmatching.repository.MissionRepository;
import pairmatching.repository.PairHistoryRepository;
import pairmatching.service.PairMatchingService;
import pairmatching.util.Input;
import pairmatching.util.InputResolver;
import pairmatching.util.InputValidator;

public class AppConfig {

    private FrontController frontController;
    private PairMatchingController pairMatchingController;
    private PairMatchingService pairMatchingService;
    private Input input;
    private InputResolver inputResolver;
    private InputValidator inputValidator;
    private PairHistoryRepository pairHistoryRepository;
    private MissionRepository missionRepository;
    private MatchingResultRepository matchingResultRepository;
    private CrewRepository crewRepository;

    public AppConfig() {
        //repository
        crewRepository = new CrewRepository();
        matchingResultRepository = new MatchingResultRepository();
        missionRepository = new MissionRepository();
        pairHistoryRepository = new PairHistoryRepository();
        //util
        inputValidator = new InputValidator(missionRepository);
        inputResolver = new InputResolver(inputValidator, missionRepository);
        input = new Input(inputResolver);
        //service
        pairMatchingService = new PairMatchingService(crewRepository, pairHistoryRepository, matchingResultRepository);
        //controller
        pairMatchingController = new PairMatchingController(input, pairMatchingService);
        frontController = new FrontController(input, pairMatchingController);
        init();
    }

    public FrontController getFrontController() {
        return frontController;
    }

    private void init() {
        crewRepository.init();
        missionRepository.init();
    }
}
