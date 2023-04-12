package pairmatching.controller;

import pairmatching.constant.Function;
import pairmatching.util.Display;
import pairmatching.util.InputHandler;

public class FrontController {

    private final PairMatchingController pairMatchingController;

    public FrontController(PairMatchingController pairMatchingController) {
        this.pairMatchingController = pairMatchingController;
    }

    public void run() {
        while (true) {
            Display.displaySelectingFunction();
            Function input = InputHandler.selectFunction();
            if (input.equals(Function.QUIT)) {
                break;
            }
            start(input);
        }
    }

    public void start(Function input) {
        if (input.equals(Function.MATCH_PAIR)) {
            pairMatchingController.matchPair();
        }
        if (input.equals(Function.FIND_PAIR)) {
            pairMatchingController.findPair();
        }
        if (input.equals(Function.RESET_PAIR)) {
            pairMatchingController.resetPair();
        }
    }
}
