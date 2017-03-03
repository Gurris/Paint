package Model.Observers;

import Controller.mainViewController;
import Model.Paper;

/**
 * Created by Gurris on 2017-03-03.
 */
public class shapeObserver extends Observer {

    private Paper paper;
    private mainViewController controller;

    public shapeObserver(Paper paper, mainViewController controller){
        this.paper = paper;
        this.paper.addObserver(this);
        this.controller = controller;
    }

    @Override
    public void update() {
        controller.shapeMenu(paper.getShapes());
    }

}
