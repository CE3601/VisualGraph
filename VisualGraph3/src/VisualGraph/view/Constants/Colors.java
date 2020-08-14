package VisualGraph.view.Constants;

import javafx.scene.paint.Color;

public enum Colors {
    BACKGROUND(Color.BLACK),
    STROKECOLOR(Color.GREEN),
    NODESELECTEDCOLOR(Color.RED),
    NODEHOVERCOLOR(Color.YELLOW),
    BUTTONCOLOR(Color.GREEN);

    private Color color;
    Colors(Color color){
        this.color = color;
    }
    public Color getColor(){
        return color;
    }

}
