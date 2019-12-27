package ui.controller;

import businessLogic.ElementFacade;
import ui.interfaces.LoginInterface;

public class ElementController implements LoginInterface {

    ElementFacade ef = ElementFacade.getInstance(this);

    @Override
    public void printResults(Object arg) {

    }
}
