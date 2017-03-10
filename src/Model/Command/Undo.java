package Model.Command;

import Model.Paper;
import Model.Shapes.Shape;

/**
 * Created by Gustaf on 2017-03-10.
 */
public class Undo implements Command{

    private Paper paper;
    private Shape shape;

    public Undo(Paper p, Shape shape){
        this.paper = p;
        this.shape = shape;
    }

    @Override
    public void execute() {
        paper.removeShape(shape);
    }

}
