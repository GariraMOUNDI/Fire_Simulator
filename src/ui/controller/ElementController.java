package ui.controller;

import businessLogic.ElementFacade;
import businessLogic.SessionFacade;
import persistence.data.TypeElement;
import ui.interfaces.LoginInterface;

public class ElementController implements LoginInterface {

    ElementFacade EF = ElementFacade.getInstance(this);
    SessionFacade SF = SessionFacade.getInstance(this);






    @Override
    public void printResults(Object arg) {

    }

    public void createElement(String elementName, int flammability, String color, TypeElement type, String username){
        EF.createElement(elementName,flammability,color,type,username);
    }
}
