package ui.controller;

import businessLogic.SessionFacade;
import javafx.application.Application;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.concurrent.ExecutionException;

public class MainPageController implements LoginInterface {
    SessionFacade SF = SessionFacade.getInstance(this);

    public void logOut() throws Exception {
        SF.logout();
        backToLogin();
    }

    public void toPosts() throws Exception {
        ApplicationUI.postView(ApplicationUI.getStage());
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
