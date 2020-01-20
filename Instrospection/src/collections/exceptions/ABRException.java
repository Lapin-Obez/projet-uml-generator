package collections.exceptions;


/**
 * classe indiquant des problèmes dans les valeurs recherchées
 */
public abstract class ABRException  extends RuntimeException  {

    public ABRException(String msg) {
        super(msg);
    }
}
