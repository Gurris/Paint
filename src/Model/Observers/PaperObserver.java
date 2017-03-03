package Model.Observers;

import Model.Paper;
import javafx.scene.canvas.Canvas;

/**
 * Created by Gurris on 2017-03-03.
 */
public class PaperObserver extends Observer {

    private Paper paper;
    private Canvas canvas;

    public PaperObserver(Paper paper, Canvas canvas){
        this.paper = paper;
        this.paper.addObserver(this);
        this.canvas = canvas;
    }

    @Override
    public void update() {
        paper.drawShapes(canvas.getGraphicsContext2D());
    }
}
