package pairmatching;

import pairmatching.config.AppConfig;
import pairmatching.controller.FrontController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        FrontController frontController = appConfig.getFrontController();
        frontController.run();
    }
}
