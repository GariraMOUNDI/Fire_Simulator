package ui.controller;

import businessLogic.SessionFacade;
import javafx.application.Application;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

public class MainPageController implements LoginInterface {
    SessionFacade SF = SessionFacade.getInstance(this);

    public void logOut() throws Exception {
        SF.logout();
        backToLogin();
    }

    public void backToLogin() throws Exception {
        ApplicationUI.loginView(ApplicationUI.getStage());
    }

    public void toStore() throws Exception {
        ApplicationUI.storeView(ApplicationUI.getStage());
    }

    @Override
    public void printResults(Object arg) {

    }
}
