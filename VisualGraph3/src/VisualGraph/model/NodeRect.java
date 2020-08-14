package VisualGraph.model;

import VisualGraph.controller.GraphController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Rectangle;

public class NodeRect extends Rectangle {

    private String label;
    private boolean selected;
    private GraphController controller;

    public NodeRect(String label, GraphController controller){
        this.label = label;
        this.controller = controller;
        selected = false;
    }

    public void updateSelected(boolean value){
        selected = value;
    }

    public NodeRect getNodeRect(){
        return this;
    }

    public boolean isSelected(){
        return selected;
    }

    public String getLabel(){
        return label;
    }

    public ChangeListener<Boolean> getHoverListener(){
        return hoverListener;
    }

    public void removeListener(){
        this.hoverProperty().removeListener(hoverListener);
    }

    public void addListener(){
        this.hoverProperty().addListener(hoverListener);
    }

    private ChangeListener<Boolean> hoverListener = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
            controller.updateFromHoverListener(getNodeRect(), newValue);
        }
    };
}
