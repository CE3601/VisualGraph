package VisualGraph.view;

import VisualGraph.model.NodeRect;
import VisualGraph.view.Constants.Colors;
import VisualGraph.view.Constants.GraphViewConstants;
import VisualGraph.view.Constants.NodeConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphView {

    private GridPane grid;

    public GraphView(){
        this.grid = new GridPane();
        initGrid();
    }

    public GridPane getGrid(){
        return grid;
    }

    public void setNodeDisplay(List<NodeRect> nodeRectList){
        for(NodeRect node : nodeRectList){
            node.setHeight(NodeConstants.HEIGHT.getData());
            node.setWidth(NodeConstants.WIDTH.getData());
            node.setStrokeWidth(NodeConstants.STROKEWIDTH.getData());
            node.setFill(Colors.BACKGROUND.getColor());
            node.setStroke(Colors.STROKECOLOR.getColor());
        }
    }

    public void addNodesGrid(List<NodeRect> nodeRectList, int rowLength){
        Queue<NodeRect> nodeRectQ = new LinkedList<>();
        nodeRectQ.addAll(nodeRectList);
        for(int rowIndex = 0; rowIndex < rowLength; rowIndex++){
            for(int columnIndex = 0; columnIndex < rowLength; columnIndex++){
                NodeRect node = nodeRectQ.poll();
                grid.add(node, columnIndex, rowIndex);
            }
        }
    }

    private void initGrid(){
        grid.setBackground(new Background( new BackgroundFill(Colors.BACKGROUND.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(GraphViewConstants.VGAP.getValue());
        grid.setHgap(GraphViewConstants.HGAP.getValue());
    }

    public void clearAllNodeRects(){
        grid.getChildren().clear();
    }

    public void setHoverFill(NodeRect node){
        node.setFill(Colors.NODEHOVERCOLOR.getColor());
    }

    public void setBackgroundFill(NodeRect node){
        node.setFill(Colors.BACKGROUND.getColor());
    }

    public void selectedFill(NodeRect node){
        node.setFill(Colors.NODESELECTEDCOLOR.getColor());
    }

}
