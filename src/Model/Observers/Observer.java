package Model.Observers;

import Model.Paper;

import javax.security.auth.Subject;
import java.io.Serializable;


/**
 * Created by Gurris on 2017-03-03.
 */
public abstract class Observer{

    protected Subject subject;
    public abstract void update();

}
