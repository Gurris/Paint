package Model;

import Model.Shapes.Shape;
import Model.Observers.Observer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurris on 2017-03-02.
 */
public class Paper implements InterfacePaper{

    ArrayList<Shape> shapes = new ArrayList<>();
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void addShape(Shape shape) {
        System.out.println("Adding: " + shape.getIdetifyer() + " " + shape.getType() + " at: x1: " + shape.getX1() + " y1: " + shape.getY1() + " x2: " + shape.getX2() + " y2: " + shape.getY2());
        shapes.add(shape);
        notifyObserevers();
    }

    @Override
    public void removeShape(Shape shape) {
        System.out.println("Removing: " + shape.getIdetifyer() + " " + shape.getType() + " at: x1: " + shape.getX1() + " y1: " + shape.getY1() + " x2: " + shape.getX2() + " y2: " + shape.getY2());
        for(Shape s : shapes){
            if(s.getIdetifyer() == shape.getIdetifyer()){
                System.out.println("Removing!");
                shapes.remove(s);
                break;
            }
        }
        notifyObserevers();
    }

    @Override
    public void editedShape(Shape referemceShape, Shape savedShape) {
        //System.out.println("Editing: " + shape.getType() + " at: x1: " + shape.getX1() + " y1: " + shape.getY1() + " x2: " + shape.getX2() + " y2: " + shape.getY2());
        for(int i=0; i<shapes.size(); i++){
            if(shapes.get(i).getIdetifyer() == savedShape.getIdetifyer()){
                this.removeShape(shapes.get(i));
                this.addShape(savedShape);
            }
        }
        notifyObserevers();
    }

    @Override
    public void drawShapes(GraphicsContext gc) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setLineWidth(3);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for(int i=0; i<shapes.size(); i++){
            shapes.get(i).draw(gc);
        }
    }

    public void addObserver(Observer obs){
        observers.add(obs);
    }

    public void removeObserver(Observer obs){
        observers.remove(obs);
    }

    private void notifyObserevers(){
        for (Observer observers : observers) {
            observers.update();
        }
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void clearPaper() {
        shapes.clear();
    }
}
