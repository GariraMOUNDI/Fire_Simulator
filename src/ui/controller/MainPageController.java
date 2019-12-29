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
