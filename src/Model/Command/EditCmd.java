package Model.Command;

import Model.InterfacePaper;
import Model.Paper;
import Model.Shapes.Shape;

/**
 * Created by Gustaf on 2017-03-10.
 */
public class EditCmd implements Command{

    private InterfacePaper paper;
    private Shape referenceShape;
    private Shape savedShape;


    public EditCmd(InterfacePaper paper, Shape referenceShape, Shape savedShape){
        this.paper = paper;
        this.referenceShape = referenceShape;
        this.savedShape = savedShape;
    }

    @Override
    public void execute() {
        paper.editedShape(referenceShape, savedShape);
    }

    public InterfacePaper getPaper() {
        return paper;
    }

    public void setPaper(InterfacePaper paper) {
        this.paper = paper;
    }

    public Shape getReferenceShape() {
        return referenceShape;
    }

    public void setReferenceShape(Shape referenceShape) {
        this.referenceShape = referenceShape;
    }

    public Shape getSavedShape() {
        return savedShape;
    }

    public void setSavedShape(Shape savedShape) {
        this.savedShape = savedShape;
    }
}
