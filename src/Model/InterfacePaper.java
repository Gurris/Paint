package Model;


import Model.Shapes.Shape;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Created by Gurris on 2017-03-02.
 */
public interface InterfacePaper {
    void addShape(Shape shape);
    void removeShape(Shape shape);
    void editedShape(Shape shape);
    void drawShapes(GraphicsContext gc);
    ArrayList<Shape> getShapes();
    void clearPaper();
}
