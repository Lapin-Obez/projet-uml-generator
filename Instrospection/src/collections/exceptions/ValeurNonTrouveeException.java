package collections.exceptions;


/**
 * classe indiquant des problèmes dans les valeurs recherchées
 */
public class ValeurNonTrouveeException extends ABRException {

    public ValeurNonTrouveeException(String msg) {
        super(msg);
    }
}
