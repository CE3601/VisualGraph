package VisualGraph.controller;

import VisualGraph.view.DataWindowView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.Map;

public class DataWindowController {

    private DataWindowView view;
    private GraphController graphController;
    private Button clear;
    private Slider slider;
    private ListView<String> listView;

    private static final ObservableList<String> observableList = FXCollections.observableArrayList();

    public DataWindowController(GraphController gc){
        view = new DataWindowView();
        listView = new ListView<>();
        graphController = gc;
        initVbox();
    }

    public VBox getVBox(){
        return view.getVBox();
    }

    public void initVbox(){
        addSlider();
        addButton();
        addListView();
    }

    public void addButton(){
        clear = new Button("Clear Grid");
        view.getVBox().getChildren().add(clear);
        view.initClearButton(clear);
        setButtonAction();
    }

    public void addSlider(){
        slider = new Slider(0, 21, 9);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(3.0);
        view.getVBox().getChildren().add(slider);
        view.initSlider(slider);
        setSliderAction();
    }

    public void addListView(){
        populate();
        listView.setItems(observableList);
        view.initListView(listView);
        view.getVBox().getChildren().add(listView);
        setListViewListener();
    }

    public void clearListView(){
        listView.getItems().clear();
        observableList.clear();
    }

    public void populate(){
        Map<String, Integer> pathData = graphController.getLabelDistanceData();
        for(Map.Entry<String, Integer> entry: pathData.entrySet()){
            observableList.add("Node: " + entry.getKey() + " Distance: " + entry.getValue());
        }
    }

    private void handleButtonAction(){
        graphController.clearGrid();
    }

    private void setButtonAction(){
        clear.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.onButtonPressed(clear);
            }
        });

        clear.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.onButtonRelease(clear);
                clearListView();
                handleButtonAction();
            }
        });
    }

    private void setSliderAction(){
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue) {
                graphController.changeGridSize(newValue.intValue());
            }
        });
    }

    private void setListViewListener(){
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                String oldLabel = "";
                boolean oldFlag = false;
                if(oldValue != null) {
                    //remove red highlight from old value
                    for (char c : oldValue.toCharArray()) {
                        if (Character.isDigit(c)) {
                            oldFlag = true;
                            oldLabel = oldLabel.concat(c + "");
                        } else if (oldFlag == true) {
                            break;
                        }
                        graphController.highlightNodeYellow(oldLabel);
                    }
                }

                //extract the node number from the newValue and pass it as a label to the graphController
                String newLabel = "";
                boolean newFlag = false;
                for(char c : newValue.toCharArray()){
                    if(Character.isDigit(c)){
                        newFlag = true;
                        newLabel = newLabel.concat(c + "");
                    }
                    else if(newFlag == true){
                        break;
                    }

                } graphController.highlightNodeRed(newLabel);
            }
        });
    }
}
