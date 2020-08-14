package VisualGraph.controller;

import VisualGraph.controller.Constants.Constants;
import VisualGraph.model.NodeRect;
import VisualGraph.model.SquareGraph;
import VisualGraph.view.GraphView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphController {

    private GraphView view;
    private List<NodeRect> nodeRectList;
    private SquareGraph squareGraph;
    private List<NodeRect> selectedNodes;
    private List<NodeRect> path;
    private DataWindowController dataController;

    public GraphController(){
        view = new GraphView();
        squareGraph = new SquareGraph(Constants.SQUAREGRAPHSIZE.getData());
        selectedNodes = new ArrayList<>();
        nodeRectList = new ArrayList<>();
        path = new ArrayList<>();

        setNodeRects();
        setDataView();
    }

    public GridPane getGrid(){
        return view.getGrid();
    }

    public VBox getDataRoot(){
        return dataController.getVBox();
    }

    public Map<String, Integer> getLabelDistanceData(){
        return squareGraph.getLabelDistanceMap();
    }

    private void setDataView(){
        dataController = new DataWindowController(this);
    }

    private void setNodeRects(){
        List<String> labels = squareGraph.getLabels();
        nodeRectList = labels.stream().map(label -> new NodeRect(label, this)).collect(Collectors.toList());
        updateView();
        addHoverListeners();
        addMouseEventListener();
    }

    private void updateView(){
        view.setNodeDisplay(nodeRectList);
        view.addNodesGrid(nodeRectList, squareGraph.getRowLength());
    }

    private void handleSelectedNodes(){
        if(selectedNodes.size() == 2){
            findPath();
            selectedNodes.clear();
        }
    }

    private void findPath(){
        List<String> pathLabels = squareGraph.shortestPaths(selectedNodes.get(0).getLabel(), selectedNodes.get(1).getLabel());
        for(String label : pathLabels){
            nodeRectList.stream().filter(node -> node.getLabel().equals(label)).forEach(entry -> {
                path.add(entry);
                entry.updateSelected(true);
            });
        } highlightPath();
        dataController.populate();
    }

    private void highlightPath(){
        path.stream().forEach(NodeRect::removeListener);
        path.stream().forEach(view::setHoverFill);
    }

    private void addHoverListeners(){
        nodeRectList.stream().forEach(NodeRect::addListener);
    }

    public void updateFromHoverListener(NodeRect node, boolean newValue){
        if(newValue){
            view.setHoverFill(node);
        } else{
            view.setBackgroundFill(node);
        }
    }

    public void highlightNodeRed(String label){
        for(NodeRect node : path){
            if(node.getLabel().equals(label)){
                view.selectedFill(node);
            }
        }
    }

    public void highlightNodeYellow(String label){
        for(NodeRect node : path){
            if(node.getLabel().equals(label)){
                view.setHoverFill(node);
            }
        }
    }

    public void clearGrid(){
        path.stream().forEach(entry -> {
            view.setBackgroundFill(entry);
            entry.updateSelected(false);
            entry.addListener();
        });
        path.clear();
    }

    public void changeGridSize(int newSize){
        squareGraph = new SquareGraph(newSize);
        nodeRectList.clear();
        view.clearAllNodeRects();
        setNodeRects();
    }

    private void addMouseEventListener(){
        view.getGrid().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getTarget() instanceof NodeRect) {
                    NodeRect node = (NodeRect) mouseEvent.getTarget();
                    if (!node.isSelected()) {
                        node.removeListener();
                        node.updateSelected(true);
                        selectedNodes.add(node);
                        view.selectedFill(node);
                    } else {
                        node.addListener();
                        node.updateSelected(false);
                        selectedNodes.remove(node);
                        view.setBackgroundFill(node);
                    }
                } handleSelectedNodes();
            }
        });
    }
}
