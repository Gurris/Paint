package Model.Observers;

import Controller.mainViewController;
import Model.Shapes.Prototype;

/**
 * Created by Gurris on 2017-03-03.
 */
public class menuObserver extends Observer{

    private Prototype prototype;
    private mainViewController controller;

    public menuObserver(Prototype prototype, mainViewController controller){
        this.prototype = prototype;
        this.prototype.addObserver(this);
        this.controller = controller;
    }

    @Override
    public void update() {
        controller.optionsMenu(prototype);
    }
}
