package Model.Shapes;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Created by Gurris on 2017-03-02.
 */
public abstract class Shape implements Cloneable, Serializable{

    private String id;
    protected String type;

    private double x1, y1, x2, y2;
    private transient Color color;
    private double lineWidth;
    private boolean fill;
    private String serilizeColor;

    public void draw(GraphicsContext g2){
        g2.setStroke(getColor());
        g2.setLineWidth(lineWidth);
        g2.setFill(getColor());
        drawShape(g2);
    }

    abstract protected void drawShape(GraphicsContext g);


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.serilizeColor = this.color.toString();
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public String getSerilizeColor() {
        return serilizeColor;
    }

    public void setSerilizeColor(String serilizeColor) {
        this.serilizeColor = serilizeColor;
    }

    public Object clone(){
        Object clone = null;
        try{
            clone = super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("Could not clone shape... In class Shape");
            e.printStackTrace();
        }
        return clone;
    }

}
