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
    private String nom;

    public Parametre(String type, String n) {
    	super();
    	this.type = type;
    	nom=n;
    }

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