/**
 * Created by Gurris on 2017-02-23.
 */

import Model.Command.CommandManager;
import Model.Observers.PaperObserver;
import Model.Observers.menuObserver;
import Model.Observers.shapeObserver;
import Model.Shapes.Prototype;
import Model.InterfacePaper;
import Model.Paper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Controller.mainViewController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View/View.fxml"));
        Parent root = loader.load();
        mainViewController controller = loader.getController();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Paint 2.0");
        primaryStage.setScene(new Scene(root, 750, 600));

        // menu items
        Prototype prototype = new Prototype();

        // drawing board
        Paper p = new Paper();
        InterfacePaper paper = p;

        //Command manager
        CommandManager cmdManager = new CommandManager();
        controller.setCmdManager(cmdManager);

        // Observers
        new PaperObserver(p, controller.getCanvas());   // Calls draw method on change
        new shapeObserver(p, controller);               // Calls shapeMenu in controller on change
        new menuObserver(prototype, controller);        // Calls optionsMenu on change

        prototype.loadPrototype();                      // here is where options menu can be changed. Might not need to be a
                                                        // observer. But... Meh...

        controller.setPaper(paper);
        paper.drawShapes(controller.getCanvas().getGraphicsContext2D()); // only draws outline
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
