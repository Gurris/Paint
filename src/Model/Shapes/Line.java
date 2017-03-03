package Model.Shapes;


import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * Created by Gurris on 2017-03-02.
 */
public class Line extends Shape  implements Serializable {

    public Line(){
        type = "Line";
    }


    @Override
    protected void drawShape(GraphicsContext g) {
        g.strokeLine(getX1(), getY1(), getX2(), getY2());
    }

}
