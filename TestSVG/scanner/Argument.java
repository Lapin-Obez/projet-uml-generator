package scanner;

/**
 *
 */

/**
 * @author UTILISATEUR
 *
 */
public class Argument extends Parametre{
    private String nom;
    private String visibilite;

    public Argument(String nom, String type,String v) {
        super( type);
        visibilite=v;
        this.nom=nom;

    }

    public String getNom() {
        return nom;
    }

    public String getVisibilite() {
        return visibilite;
    }

    @Override
    public String toString() {
        if(visibilite.equals("public")) {
            return "+ "+nom+super.toString();
        }else if(visibilite.equals("private")) {
            return "- "+nom+super.toString();
        }else if(visibilite.equals("protected")) {
            return "# "+nom+super.toString();
        }else if(visibilite.equals("abstract")) {
            return "<<abstract>> "+nom+super.toString();
        }else {
            return " "+nom+super.toString();
        }

    }


}