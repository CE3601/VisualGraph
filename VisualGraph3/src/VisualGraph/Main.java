package VisualGraph;

import VisualGraph.controller.DataWindowController;
import VisualGraph.controller.GraphController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int primaryWidth = 600;
    private static final int primaryHeight = 600;
    private static final int secondaryWidth = 400;
    private static final int secondaryHeight = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        GraphController graphController = new GraphController();

        Stage secondary = new Stage();
        primaryStage.setTitle("Welcome To Visual Graph 3");
        primaryStage.setScene(new Scene(graphController.getGrid(), primaryWidth, primaryHeight));
        primaryStage.show();

        secondary.setTitle("Welcome To Visual Graph 3 Data");
        secondary.setScene(new Scene(graphController.getDataRoot(), secondaryWidth, secondaryHeight));
        secondary.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
