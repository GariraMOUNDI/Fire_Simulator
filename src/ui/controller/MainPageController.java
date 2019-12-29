package ui.controller;

import businessLogic.SessionFacade;
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

    public void toMapManagement() throws Exception {
        ApplicationUI.MapManagementView(ApplicationUI.getStage());
    }
    public void toSoloMenu() throws Exception {
        ApplicationUI.toSoloMenu(ApplicationUI.getStage());
    }

    public void toMainPage() throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }


    @Override
    public void printResults(Object arg) {

    }
}
