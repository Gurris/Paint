package Model.Command;

import java.util.Stack;

/**
 * Created by Gustaf on 2017-03-10.
 */
public class CommandManager {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void addCommand(Command cmd){
        System.out.println("ADDING COMMAND!");
        redoStack.clear();
        undoStack.push(cmd);
    }

    public void executeUndo(){
        if(undoStack.size() > 0){
            Command tmp = undoStack.pop();
            if(tmp instanceof AddCmd){
                System.out.println("Executing add command!");
                redoStack.push(new DeleteCmd(((AddCmd) tmp).getPaper(), ((AddCmd) tmp).getShape()));
            }
            else if(tmp instanceof DeleteCmd){
                System.out.println("Executing Delete command!");
                redoStack.push(new AddCmd(((DeleteCmd) tmp).getPaper(), ((DeleteCmd) tmp).getShape()));
            }
            else if(tmp instanceof EditCmd){
                System.out.println("Executing Edite command!");
                redoStack.push(new EditCmd(((EditCmd) tmp).getPaper(), ((EditCmd) tmp).getSavedShape(), ((EditCmd) tmp).getReferenceShape()));
            }
            tmp.execute();
        }
    }

    public void executeRedo(){
        if(redoStack.size() > 0){
            Command tmp = redoStack.pop();
            if(tmp instanceof EditCmd){
                undoStack.push(new EditCmd(((EditCmd) tmp).getPaper(), ((EditCmd) tmp).getSavedShape(), ((EditCmd) tmp).getReferenceShape()));
            }else if(tmp instanceof AddCmd){
                undoStack.push(new DeleteCmd(((AddCmd) tmp).getPaper(), ((AddCmd) tmp).getShape()));
            }else if(tmp instanceof DeleteCmd){
                undoStack.push(new AddCmd(((DeleteCmd) tmp).getPaper(), ((DeleteCmd) tmp).getShape()));
            }
            tmp.execute();
        }
    }

}
