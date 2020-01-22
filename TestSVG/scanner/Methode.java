package scanner;


/**
 * @author UTILISATEUR
 *
 */
public class Methode {
    private String typeRetour;
    private String name;
    private Parametre[] parametres;
    private String visibilite;

    public Methode(String typeRetour, String name, Parametre[] parametres, String visibilite) {
        super();
        this.typeRetour = typeRetour;
        this.name = name;
        this.parametres = parametres;
        this.visibilite = visibilite;
    }

    public String getTypeRetour() {
        return typeRetour;
    }

    public String getName() {
        return name;
    }

    public String getParametres() {
        if(parametres==null) {
            return "";
        }else {
            String res=parametres[0].toString();
            for(int i=1;i<parametres.length;i++) {
                res+=", "+parametres[i].toString();
            }
            return res;
        }
    }
    public Parametre[] getParam(){
        return parametres;
    }

    public String getVisibilite() {
        return visibilite;
    }

    @Override
    public String toString() {
        if(visibilite.equals("public")) {
            return "+ "+ name+" ("+getParametres()+")"+" : "+typeRetour;
        }else if(visibilite.equals("private")) {
            return "- "+ name+" ("+getParametres()+")"+" : "+typeRetour;
        }else if(visibilite.equals("protected")) {
            return "# "+ name+" ("+getParametres()+")"+" : "+typeRetour;
        }else if(visibilite.equals("abstract")) {
            return "<<abstract>> "+ name+" ("+getParametres()+")"+" : "+typeRetour;
        }else {
            return " "+ name+" ("+getParametres()+")"+" : "+typeRetour;
        }

    }


}
