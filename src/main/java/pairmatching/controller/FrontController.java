package pairmatching.controller;

import pairmatching.util.Input;

public class FrontController {

    private final Input input;
    private final PairMatchingController pairMatchingController;

    public FrontController(Input input, PairMatchingController pairMatchingController) {
        this.input = input;
        this.pairMatchingController = pairMatchingController;
    }

    public void run() {
        while (true) {
            String str = input.selectFunction();
            if (str.equals("Q")) {
                return;
            }
            if (str.equals("1")) {
                pairMatchingController.matchPair();
            }
            if (str.equals("2")) {
                pairMatchingController.findPair();
            }
            if (str.equals("3")) {
                pairMatchingController.resetPair();
            }
        }
    }
}
