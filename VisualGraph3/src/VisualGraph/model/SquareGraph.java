package VisualGraph.model;

import SimpleGraph.Graph;
import java.util.List;

public class SquareGraph extends Graph {

    private int size;
    private int rowLength;
    private String[][] labelMatrix;

    public SquareGraph(int N){
        size = N*N;
        rowLength = N;
        labelMatrix = new String[N][N];
        build();
    }

    public void build(){
        setLabels();
        initLabelMatrix();
        connectRows();
        connectColumns();
        connectDiagonals();
    }

    public String[][] getLabelMatrix() {
        return labelMatrix;
    }

    public int getRowLength(){
        return rowLength;
    }

    private void setLabels(){
        for(int i = 0; i < size; i++) {
            super.add("" + (i + 1));
        }
    }

    private void initLabelMatrix(){
        List<String> allLabels = super.getLabels();
        int labelIndex = 0;
        for(int rowIndex = 0; rowIndex < rowLength; rowIndex++){
            for(int columnIndex = 0; columnIndex < rowLength; columnIndex++){
                labelMatrix[rowIndex][columnIndex] = allLabels.get(labelIndex);
                labelIndex++;
            }
        }
    }

    private void connectRows(){
        for(int rowIndex = 0; rowIndex < rowLength; rowIndex++){
            for(int columnIndex = 0; columnIndex < rowLength - 1; columnIndex++){
                super.connect(labelMatrix[rowIndex][columnIndex], labelMatrix[rowIndex][columnIndex+1], 1);
            }
        }
    }

    private void connectColumns(){
        for(int columnIndex = 0; columnIndex < rowLength; columnIndex++){
            for(int rowIndex = 0; rowIndex < rowLength - 1; rowIndex++){
                super.connect(labelMatrix[rowIndex][columnIndex], labelMatrix[rowIndex+1][columnIndex], 1);
            }
        }
    }

    private void connectDiagonals(){
        //left to right diagonals
        for(int row = 0; row < rowLength-1; row++){
            for(int column = 0; column < rowLength - 1; column++){
                super.connect(labelMatrix[row][column], labelMatrix[row + 1][column + 1], 1);
            }
        }
        //right to left diagonals
        for(int row = rowLength - 2; row > -1; row--){
            for(int column = rowLength - 1; column > 0; column--){
                super.connect(labelMatrix[row][column], labelMatrix[row + 1][column - 1], 1);
            }
        }
    }

    public void rowColumnTestConnect(){
        List<String> labels = super.getLabels();
    }

}