package ui.controller;

import businessLogic.SessionFacade;

import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.util.Date;
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

    public void toMapManagement() throws Exception {
        ApplicationUI.MapManagementView(ApplicationUI.getStage());
    }
    public void toSoloMenu() throws Exception {
        ApplicationUI.toSoloMenu(ApplicationUI.getStage());
    }

    public void toMainPage() throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }

    public void elementManagementView() throws Exception{
        ApplicationUI.elementManagementView(ApplicationUI.getStage());
    }

    public void toStore() throws Exception {
        ApplicationUI.storeView(ApplicationUI.getStage());
    }

    @Override
    public void printResults(Object arg) {

    }

    public void goToFriendsWindow() throws Exception {
        ApplicationUI.friendsView(ApplicationUI.getStage());
    }

    public void goToProfilePage() throws Exception{
        ApplicationUI.profileView(ApplicationUI.getStage());
    }
}
