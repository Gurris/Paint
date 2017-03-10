package Model.Command;

import Model.InterfacePaper;
import Model.Paper;
import Model.Shapes.Shape;

/**
 * Created by Gustaf on 2017-03-10.
 */
public class EditeCmd implements Command{

    private InterfacePaper paper;
    private Shape oldShape;
    private Shape newShape;


    public EditeCmd(InterfacePaper paper, Shape oldShape, Shape newShape){
        this.paper = paper;
        this.oldShape = oldShape;
        this.newShape = newShape;
    }

    @Override
    public void execute() {
        paper.editedShape(newShape, oldShape);
    }

    public InterfacePaper getPaper() {
        return paper;
    }

    public void setPaper(InterfacePaper paper) {
        this.paper = paper;
    }

    public Shape getOldShape() {
        return oldShape;
    }

    public void setOldShape(Shape oldShape) {
        this.oldShape = oldShape;
    }

    public Shape getNewShape() {
        return newShape;
    }

    public void setNewShape(Shape newShape) {
        this.newShape = newShape;
    }
}
