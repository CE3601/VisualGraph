package VisualGraph.view.Constants;

public enum NodeConstants {

    WIDTH(20), HEIGHT(20), STROKEWIDTH(2);

    private int data;
    NodeConstants(int data){
        this.data = data;
    }

    public int getData(){
        return data;
    }
}
