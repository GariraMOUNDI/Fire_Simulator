package ui.controller;

import businessLogic.SessionFacade;
import ui.interfaces.LoginInterface;

public class ProfileController implements LoginInterface {

    private SessionFacade session = SessionFacade.getInstance(this);

    @Override
    public void printResults(Object arg) {

    }


}
