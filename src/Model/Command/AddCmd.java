package Model.Command;

import Model.InterfacePaper;
import Model.Shapes.Shape;

/**
 * Created by Gustaf on 2017-03-10.
 */
public class AddCmd implements Command{

    private InterfacePaper paper;
    private Shape shape;

    public AddCmd(InterfacePaper paper, Shape shape){
        this.paper = paper;
        this.shape = shape;
    }

    @Override
    public void execute() {
        paper.removeShape(shape);
    }

    public InterfacePaper getPaper() {
        return paper;
    }

    public void setPaper(InterfacePaper paper) {
        this.paper = paper;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
