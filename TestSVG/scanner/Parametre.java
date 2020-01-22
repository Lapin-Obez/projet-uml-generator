package scanner;

/**
 *
 */

/**
 * @author UTILISATEUR
 *
 */
public class Parametre {
    private String type;

    public Parametre(String type) {
        super();
        this.type = type;
    }


    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return " : "+type;
    }


}