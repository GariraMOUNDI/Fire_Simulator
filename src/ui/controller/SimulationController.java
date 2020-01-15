package ui.controller;
import businessLogic.MapManagementFacade;
import businessLogic.SessionFacade;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import persistence.data.*;
import persistence.data.Box;
import ui.interfaces.LoginInterface;
import ui.model.ApplicationUI;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;



public class SimulationController implements LoginInterface{
    /**
     * The map management facade instance.
     */
    MapManagementFacade MMF = MapManagementFacade.getInstance(this);

    Terrain terrain;
    GridPane matrice;

    /**
     * The session facade instance.
     */
    SessionFacade SF = SessionFacade.getInstance(this);
    @FXML
    ScrollPane scrollPane;

    ArrayList<GridPane> listGridPane = new ArrayList<>();
    int i = 0;


    @FXML
    public void initialize() {
        matrice = new GridPane();
        terrain = MMF.getCurrentTerrain();
        for (int x = 0; x < terrain.getMap().getSize(); x++) {
            for (int y = 0; y < terrain.getMap().getSize(); y++) {
                Shape cell = (Shape) new javafx.scene.shape.Rectangle(30, 30);
                cell.setFill(Color.web(terrain.getMap().getBox(x, y).getElement().getColor()));
                matrice.add(cell, x, y);
                matrice.setHgap(1);
                matrice.setVgap(1);
            }
        }
        scrollPane.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            /**
             * This method set the current Element
             */
            @Override
            public void handle(MouseEvent t) {
                scrollPane.setContent(listGridPane.get(i));
                i++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        scrollPane.setContent(matrice);
    }


    public void simulation() throws InterruptedException,AWTException {
        ArrayList<Box> burningBoxes = new ArrayList<>();
        burningBoxes.add(start_fire());

        runFire(burningBoxes);
        Robot r = new Robot();
        int ycursor = (int) (MouseInfo.getPointerInfo().getLocation().getY()-20);
        int xcursor = (int) (MouseInfo.getPointerInfo().getLocation().getX());

        for (int a = 0; a < listGridPane.size(); a++){
            r.mouseMove(xcursor,ycursor);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }

    public Box start_fire() throws InterruptedException {
        int x = (int)(Math.random() * (MMF.getCurrentTerrain().getMap().getSize()));
        int y = (int)(Math.random() * (MMF.getCurrentTerrain().getMap().getSize()));
        while (!MMF.getCurrentTerrain().getMap().getBox(x,y).getElement().getType().equals(TypeElementEnum.Vegetation) && MMF.getCurrentTerrain().getMap().getBox(x,y).getState().equals(StateBox.burning)){
            x = (int)(Math.random() * (MMF.getCurrentTerrain().getMap().getSize()));
            y = (int)(Math.random() * (MMF.getCurrentTerrain().getMap().getSize()));
        }
        MMF.getCurrentTerrain().getMap().getBox(x,y).setState(StateBox.burning);

        GridPane newGrid = new GridPane();
        for (int i = 0; i < MMF.getCurrentTerrain().getMap().getSize(); i++) {
            for (int j = 0; j < MMF.getCurrentTerrain().getMap().getSize(); j++) {
                Shape cell = (Shape) new javafx.scene.shape.Rectangle(30, 30);
                cell.setFill(Color.web(MMF.getCurrentTerrain().getMap().getBox(i, j).getElement().getColor()));
                newGrid.add(cell, i, j);
                newGrid.setHgap(1);
                newGrid.setVgap(1);
            }
        }
        listGridPane.add(newGrid);



        return MMF.getCurrentTerrain().getMap().getBox(x,y);
    }

    public ArrayList<Box> get_neighbours(Box box, Matrice map){
        int x = box.getX();
        int y = box.getY();
        ArrayList<Box> result = new ArrayList<>();
        if (x == 0){
            if (y == 0){
                result.add(map.getBox(x + 1,y));
                result.add(map.getBox(x,y+1));
            }
            else if (y == map.getSize()-1){
                result.add(map.getBox(x + 1,y));
                result.add(map.getBox(x,y-1));
            }
            else{
                result.add(map.getBox(x + 1,y));
                result.add(map.getBox(x,y-1));
                result.add(map.getBox(x,y+1));
            }
        }
        else if (x == map.getSize()-1){
            if (y == 0){
                result.add(map.getBox(x - 1,y));
                result.add(map.getBox(x,y+1));
            }
            else if (y == map.getSize()-1){
                result.add(map.getBox(x - 1,y));
                result.add(map.getBox(x,y-1));
            }
            else{
                result.add(map.getBox(x - 1,y));
                result.add(map.getBox(x,y-1));
                result.add(map.getBox(x,y+1));
            }
        }
        else if (y == 0){
            result.add(map.getBox(x + 1,y));
            result.add(map.getBox(x-1,y));
            result.add(map.getBox(x,y+1));
        }
        else if (y == map.getSize()-1){
            result.add(map.getBox(x + 1,y));
            result.add(map.getBox(x-1,y));
            result.add(map.getBox(x,y-1));
        }
        else {
            result.add(map.getBox(x, y-1));
            result.add(map.getBox(x, y+1));
            result.add(map.getBox(x-1, y));
            result.add(map.getBox(x+1, y));
        }
        return result;
    }


    public void fire_probability(int x, int y){
        int random = (int)(Math.random() * (70-1)) + 1;
        if ((random > MMF.getCurrentTerrain().getMap().getBox(x,y).getElement().getFlammability())){
            MMF.getCurrentTerrain().getMap().getBox(x,y).setState(StateBox.burning);
            GridPane newGrid = new GridPane();
            for (int i = 0; i < MMF.getCurrentTerrain().getMap().getSize(); i++) {
                for (int j = 0; j < MMF.getCurrentTerrain().getMap().getSize(); j++) {
                    Shape cell = (Shape) new javafx.scene.shape.Rectangle(30, 30);
                    cell.setFill(Color.web(MMF.getCurrentTerrain().getMap().getBox(i, j).getElement().getColor()));
                    newGrid.add(cell, i, j);
                    newGrid.setHgap(1);
                    newGrid.setVgap(1);
                }
            }
            listGridPane.add(newGrid);
        }
    }


    public void runFire(ArrayList<Box> burningBoxes) throws InterruptedException {
        if (!(burningBoxes.isEmpty())){

            ArrayList<Box> toRemove = new ArrayList<>();
            ArrayList<Box> toAdd = new ArrayList<>();
            for (Box burningBox : burningBoxes) {
                MMF.getCurrentTerrain().getMap().setBox(burningBox.getX(),burningBox.getY(),burningBox);
                if ((burningBox.getLife() < 0) && burningBox.getElement().getType().equals(TypeElementEnum.Vegetation)) {
                    burningBox.setState(StateBox.dust);
                    MMF.getCurrentTerrain().getMap().setBox(burningBox.getX(),burningBox.getY(),burningBox);

                    GridPane newGrid = new GridPane();
                    for (int i = 0; i < MMF.getCurrentTerrain().getMap().getSize(); i++) {
                        for (int j = 0; j < MMF.getCurrentTerrain().getMap().getSize(); j++) {
                            Shape cell = (Shape) new javafx.scene.shape.Rectangle(30, 30);
                            cell.setFill(Color.web(MMF.getCurrentTerrain().getMap().getBox(i, j).getElement().getColor()));
                            newGrid.add(cell, i, j);
                            newGrid.setHgap(1);
                            newGrid.setVgap(1);
                        }
                    }
                    listGridPane.add(newGrid);
                    toRemove.add(burningBox);
                }
                else {
                    burningBox.setLife(burningBox.getLife() - 25);
                }

                ArrayList<Box> neighbours = get_neighbours(burningBox, MMF.getCurrentTerrain().getMap());
                for (Box neighbour : neighbours) {
                    if ((neighbour.getElement().getType().equals(TypeElementEnum.Vegetation) && neighbour.getState().equals(StateBox.intact)) || ((neighbour.getElement().getType().equals(TypeElementEnum.Vegetation) && neighbour.getState().equals(StateBox.scorched)))) {
                        fire_probability(neighbour.getX(),neighbour.getY());
                        if (neighbour.getState().equals(StateBox.burning)) {
                            MMF.getCurrentTerrain().getMap().setBox(neighbour.getX(),neighbour.getY(),neighbour);
                            toAdd.add(neighbour);
                        }
                    }
                }
            }
            burningBoxes.removeAll(toRemove);
            burningBoxes.addAll(toAdd);
            runFire(burningBoxes);
        }

    }

    public void backToMainPage(MouseEvent mouseEvent) throws Exception {
        ApplicationUI.mainPageView(ApplicationUI.getStage());
    }


    @Override
    public void printResults(Object arg) {

    }

}
