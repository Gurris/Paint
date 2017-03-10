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
                redoStack.push(new DeleteCmd(((AddCmd) tmp).getPaper(), ((AddCmd) tmp).getShape()));
            }
            else if(tmp instanceof DeleteCmd){
                redoStack.push(new AddCmd(((DeleteCmd) tmp).getPaper(), ((DeleteCmd) tmp).getShape()));
            }
            else if(tmp instanceof EditeCmd){
                redoStack.push((new EditeCmd(((EditeCmd) tmp).getPaper(), ((EditeCmd) tmp).getNewShape(), ((EditeCmd) tmp).getOldShape())));
            }
            tmp.execute();
        }
    }

    public void executeRedo(){
        if(redoStack.size() > 0){
            redoStack.pop().execute();
        }
    }

}
