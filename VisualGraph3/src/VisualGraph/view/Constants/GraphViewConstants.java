package VisualGraph.view.Constants;

public enum GraphViewConstants {

    VGAP(5), HGAP(5);

    private int value;
    GraphViewConstants(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }

}
