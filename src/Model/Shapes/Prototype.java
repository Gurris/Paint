package Model.Shapes;

import Model.Observers.Observer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Gurris on 2017-03-02.
 */
public class Prototype {

    private List<Observer> observers = new ArrayList<Observer>();

    private Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();

    public Shape getShape(String id){
        Shape cachedShape = shapeMap.get(id);
        return (Shape) cachedShape.clone();
    }

    public ArrayList<Shape> getShapes(){
        ArrayList<Shape> shapes = new ArrayList<>();

        for(Shape s : shapeMap.values()){
            shapes.add((Shape) s.clone());
        }
        return shapes;
    }


    public void loadPrototype() {
        Line line = new Line();
        line.setId("1");
        shapeMap.put(line.getId(), line);

        Rectangle rectangle= new Rectangle();
        rectangle.setId("2");
        shapeMap.put(rectangle.getId(), rectangle);

        Circle circle = new Circle();
        circle.setId("3");
        shapeMap.put(circle.getId(), circle);

        notifyObserevers();
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

}
