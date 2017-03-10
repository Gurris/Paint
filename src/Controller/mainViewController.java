package Controller;


import Model.*;
import Model.Command.*;
import Model.Shapes.Prototype;
import Model.Shapes.Shape;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Gurris on 2017-03-02.
 */
public class mainViewController {

    @FXML
    private Node node;

    @FXML
    private VBox vBox;

    @FXML
    private Canvas canvas;

    @FXML
    private VBox shapeMenu;

    private InterfacePaper paper;

    private String drawingName;

    private Shape selectedType;
    private double selectedShape_X1, selectedShape_Y1, selectedShape_X2, selectedShape_Y2;
    private Color selectedColor;
    private int selectedLineWidth;
    private boolean selectedFillValue;

    private Shape selectedShape;

    private CommandManager cmdManager;


    public CommandManager getCmdManager() {
        return cmdManager;
    }

    public void setCmdManager(CommandManager cmdManager) {
        this.cmdManager = cmdManager;
    }

    public void setPaper(InterfacePaper paper){
        this.paper = paper;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public void shapeDrawingStart(MouseEvent mouseEvent){
        if(selectedType == null)
            return;
        this.selectedShape_X1 = mouseEvent.getX();
        this.selectedShape_Y1 = mouseEvent.getY();
    }

    public void shapeDrawingEnd(MouseEvent mouseEvent){
        this.selectedShape_X2 = mouseEvent.getX();
        this.selectedShape_Y2 = mouseEvent.getY();

        if(selectedType instanceof Shape){
            Shape tmp = (Shape) selectedType.clone();
            tmp = applyValues(tmp);
            if(selectedShape_X1 == selectedShape_X2 && selectedShape_Y1 == selectedShape_Y2) // to avoid invisible objects
                return;
            paper.addShape(tmp);
            cmdManager.addCommand(new AddCmd(paper, tmp));
            selectedShape = null;
        }else {
            System.out.println("No shape selected");
        }
    }

    public void shapeDrawingPreview(MouseEvent mouseEvent) {
        if(selectedType instanceof Shape){
            Shape tmp = (Shape) selectedType.clone();
            selectedShape_X2 = mouseEvent.getX();
            selectedShape_Y2 = mouseEvent.getY();
            tmp = applyValues(tmp);
            paper.drawShapes(canvas.getGraphicsContext2D());
            tmp.draw(canvas.getGraphicsContext2D());
        }
    }

    public void Undo(){
        System.out.println("Undo");
        cmdManager.executeUndo();
    }

    public void Redo(){
        System.out.println("Redo");
        cmdManager.executeRedo();
    }

    private Shape applyValues(Shape shape){
        shape.setX1(selectedShape_X1);
        shape.setY1(selectedShape_Y1);
        shape.setX2(selectedShape_X2);
        shape.setY2(selectedShape_Y2);
        shape.setColor(selectedColor);
        shape.setLineWidth(selectedLineWidth);
        shape.setFill(selectedFillValue);
        return shape;
    }

    public void shapeMenu(ArrayList<Shape> shapes){
        shapeMenu.getChildren().clear();

        HBox clearBox = new HBox();
        Button clearButton = new Button("Clear");
        clearButton.setStyle("-fx-base: #574cff;");
        clearBox.getChildren().add(clearButton);
        shapeMenu.getChildren().add(clearBox);

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paper.drawShapes(canvas.getGraphicsContext2D());
                selectedShape = null;
            }
        });

        for (Shape s: shapes) {
            HBox shapeBox = new HBox();
            Button shapeButton = new Button(s.getType());
            shapeBox.getChildren().add(shapeButton);
            Button delete = new Button("Delete");
            delete.setStyle("-fx-base: #ff1f1c;");
            shapeMenu.getChildren().add(shapeBox);
            shapeBox.getChildren().add(delete);

            shapeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedShape(s);
                }
            });
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    paper.removeShape(s);
                    cmdManager.addCommand(new DeleteCmd(paper, s));
                }
            });
        }
    }

    private void selectedShape(Shape shape){
        selectedShape = shape;
        GraphicsContext g2 = canvas.getGraphicsContext2D();
        //reset other shapes
        paper.drawShapes(g2);
        Color tmp = shape.getColor();
        shape.setColor(Color.BLUE);
        shape.draw(g2);
        shape.setColor(tmp);
    }

    public void optionsMenu(Prototype p){
        ArrayList<Shape> shapes = p.getShapes();

        for(int i=0; i<shapes.size(); i++){
            Shape tmp = shapes.get(i);
            if(shapes.get(i) instanceof Shape){
                // box
                HBox box = new HBox();
                // Create button
                Button shapeButton = new Button(shapes.get(i).getType());
                shapeButton.setPrefSize(150, 0);
                // add button to box
                box.getChildren().add(shapeButton);
                // Listen to button clicks
                shapeButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        selectedType = tmp;
                        System.out.println("Selected shape is: " + selectedType.getType());
                    }
                });

                vBox.getChildren().add(box);
            }
        }

        System.out.println("Creating color picker!");
        //Creates box
        HBox colorPickerBox = new HBox();
        //Creates ColorPicker
        ColorPicker colorPicker = new ColorPicker();
        //set starting value
        colorPicker.setValue(Color.BLACK);
        selectedColor = Color.BLACK;
        //add color picker to box
        colorPickerBox.getChildren().add(colorPicker);
        //add box to VBox
        vBox.getChildren().add(colorPickerBox);

        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Color picker value: " + colorPicker.getValue());
                Shape savedShape = selectedShape;
                selectedColor = colorPicker.getValue();
                if(selectedShape != null){
                    selectedShape.setColor(colorPicker.getValue());
                    paper.editedShape(savedShape, selectedShape);
                    cmdManager.addCommand(new EditeCmd(paper, savedShape, selectedShape));
                }
            }
        });

        System.out.println("Creating line size option!");
        //Creates box
        HBox LineOption = new HBox();
        //Textfield: Value will be inputted here
        TextField lineWidthField = new TextField();
        //Starting value is one
        lineWidthField.setText("3");
        selectedLineWidth = 3;
        //Add Textfield to box
        LineOption.getChildren().add(lineWidthField);
        //Add box to VBox
        vBox.getChildren().add(LineOption);

        // TODO: förbättra då man inte vill trycka på enter för att värdet ska ändras
        lineWidthField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: MAKE SURE IT IS A NUMBER!!!
                selectedLineWidth = Integer.parseInt(lineWidthField.getText());
                if(selectedShape != null){
                    selectedShape.setLineWidth(Integer.parseInt(lineWidthField.getText()));
                    //paper.editedShape(selectedShape);
                }
            }
        });

        System.out.println("Creating filling option");
        HBox fillerOptions = new HBox();
        CheckBox cBox = new CheckBox("Fill");
        fillerOptions.getChildren().add(cBox);
        vBox.getChildren().add(fillerOptions);
        cBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedFillValue = cBox.isSelected();
                if(selectedShape != null){
                    selectedShape.setFill(selectedFillValue);
                    //paper.editedShape(selectedShape);
                }
            }
        });

        System.out.println("Creating name field!");
        //Creates box
        HBox nameBox = new HBox();
        //Textfield: Value will be inputted here
        TextField nameField = new TextField();
        //Starting value is one
        nameField.setText("NAME!");
        drawingName = null;
        //Add Textfield to box
        nameBox.getChildren().add(nameField);
        //Add box to VBox
        vBox.getChildren().add(nameBox);

        // TODO: förbättra då man inte vill trycka på enter för att värdet ska ändras
        nameField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: MAKE SURE IT IS A NUMBER!!!
                drawingName = nameField.getText();
            }
        });

    }

    public void openFile(ActionEvent actionEvent) {
        System.out.println("FILE OPEN!");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecte what drawing you want to open!");
        //String path = new File(".").getCanonicalPath();
        File file = null;
        try{
            File f = new File(".").getCanonicalFile();
            fileChooser.setInitialDirectory(f);
            file = fileChooser.showOpenDialog(node.getScene().getWindow());
        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayList<Shape> arraylist = new ArrayList<Shape>();
        try
        {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arraylist = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }

        paper.clearPaper();

        for (Shape s: arraylist) {
            System.out.println("Shapes: " + s.getType() + " Id: " + s.getId());
            s.setColor(Color.web(s.getSerilizeColor()));
            paper.addShape(s);
        }

    }

    public void saveFile(){
        if(drawingName == null){
            System.out.println("No name given");
            return;
        }

        System.out.println("Saving file!");

        ArrayList<Shape> shapes = paper.getShapes();
        for (Shape s: shapes) {
            System.out.println("Shapes: " + s.getType());
        }

        ArrayList<Shape> al = new ArrayList<>();
        al = paper.getShapes();

        try{
            FileOutputStream fos= new FileOutputStream("Drawings/"+drawingName+".ser");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(al);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }
}
