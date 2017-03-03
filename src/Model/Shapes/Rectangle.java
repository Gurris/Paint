package Model.Shapes;


import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

/**
 * Created by Gurris on 2017-03-02.
 */
public class Rectangle extends Shape implements Serializable {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    protected void drawShape(GraphicsContext g) {
        double width, height;
        double x, y;
        width = getX2() - getX1();
        height = getY2() - getY1();
        x = getX1();
        y = getY1();

        if(width < 0){
            x = getX1()+width;
            width = width*-1;
        }
        if(height < 0){
            y = getY1()+height;
            height = height*-1;
        }

        if(isFill())
            g.fillRect(x, y, width, height);
        else
            g.strokeRect(x, y, width, height);
    }
}
