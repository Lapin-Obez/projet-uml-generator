package scanner;

/**
 *
 */

/**
 * @author UTILISATEUR
 *
 */
public class Methode {
    private String typeRetour;
    private String name;
    private Parametre[] parametres;
    private String visibilite;
    private String[] exceptions;

    public Methode(String typeRetour, String name, Parametre[] parametres, String visibilite) {
        super();
        this.typeRetour = typeRetour;
        this.name = name;
        this.parametres = parametres;
        this.visibilite = visibilite;
    }

    public Methode(String trg, String n, String vis) {
		super();
		typeRetour = trg;
		name = n;
		parametres = null;
		this.visibilite = vis;
		this.exceptions = null;
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
            return "+ "+ name.trim()+"("+getParametres().trim()+")"+" : "+typeRetour;
        }else if(visibilite.equals("private")) {
            return "- "+ name.trim()+"("+getParametres().trim()+")"+" : "+typeRetour;
        }else if(visibilite.equals("protected")) {
            return "# "+ name.trim()+"("+getParametres().trim()+")"+" : "+typeRetour;
        }else if(visibilite.equals("abstract")) {
            return "<<abstract>> "+ name.trim()+"("+getParametres().trim()+")"+" : "+typeRetour;
        }else {
            return " "+ name.trim()+"("+getParametres().trim()+")"+" : "+typeRetour;
        }

    }


}
