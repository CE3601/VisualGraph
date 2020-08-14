package VisualGraph.view;

import VisualGraph.view.Constants.Colors;
import VisualGraph.view.Constants.DataViewConstants;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class DataWindowView {

    private VBox vBox;

    public DataWindowView(){
        vBox = new VBox();
        initHBox();
    }

    public VBox getVBox(){
        return vBox;
    }

    public void initHBox(){
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.paddingProperty().setValue(new Insets(10,10,10,10));
        vBox.setSpacing(15.0);
        vBox.setBackground(new Background(new BackgroundFill(Colors.BACKGROUND.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        HBox.setHgrow(vBox, Priority.ALWAYS);
    }

    public void initClearButton(Button clear){
        //Set size, look, and feel of button
        clear.setPrefWidth(DataViewConstants.BUTTONWIDTH.getValue());
        clear.setPrefHeight(DataViewConstants.BUTTONHEIGHT.getValue());
        clear.setBackground(new Background(new BackgroundFill(Colors.BUTTONCOLOR.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        clear.setStyle("-fx-font-size: 15; ");
        clear.setStyle("-fx-text-fill: #0000ff");
    }

    public void initSlider(Slider slider){
        slider.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        slider.setSnapToTicks(true);
        slider.setStyle("-fx-control-inner-background: green;");
    }

    public void initListView(ListView listView){
        listView.setBackground(new Background(new BackgroundFill(Colors.BACKGROUND.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        listView.setStyle("-fx-control-inner-background: black;");
    }

    public void onButtonPressed(Button clear){
        int grow = 5;
        clear.setStyle("-fx-text-fill: #FFFFFF"); //white text
        clear.setPrefWidth(DataViewConstants.BUTTONWIDTH.getValue() + grow);
        clear.setPrefHeight(DataViewConstants.BUTTONHEIGHT.getValue() + grow);
    }

    public void onButtonRelease(Button clear){
        clear.setStyle("-fx-text-fill: #0000ff");
        clear.setPrefWidth(DataViewConstants.BUTTONWIDTH.getValue());
        clear.setPrefHeight(DataViewConstants.BUTTONHEIGHT.getValue());
    }

}
