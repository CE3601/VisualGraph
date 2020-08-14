package VisualGraph.view.Constants;

public enum DataViewConstants {

    BUTTONWIDTH(200),
    BUTTONHEIGHT(40);

    private int value;
    DataViewConstants(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
