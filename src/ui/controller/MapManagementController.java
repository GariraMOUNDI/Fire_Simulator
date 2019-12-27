package ui.controller;

import businessLogic.MapManagementFacade;
import businessLogic.SessionFacade;
import ui.interfaces.LoginInterface;

public class MapManagementController implements LoginInterface {
    MapManagementFacade MMF = MapManagementFacade.getInstance(this);
    SessionFacade SF = SessionFacade.getInstance(this);


    @Override
    public void printResults(Object arg) {

    }
}
