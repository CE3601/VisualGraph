package VisualGraph.controller.Constants;

public enum Constants {

    SQUAREGRAPHSIZE(9);

    private int data;
    Constants(int data){
        this.data = data;
    }

    public int getData(){
        return data;
    }
}
