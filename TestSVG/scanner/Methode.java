package scanner;
import java.util.List;

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
	private List<Parametre> parametres;
	private String visibilite;
	private String[] exceptions;
	
	public Methode(String typeRetour, String name, List<Parametre> parametres, String visibilite, String[] e) {
		super();
		this.typeRetour = typeRetour;
		this.name = name;
		this.parametres = parametres;
		this.visibilite = visibilite;
		this.exceptions=e;
	}
	
	public Methode(String typeRetour, String name, List<Parametre> parametres, String visibilite) {
		super();
		this.typeRetour = typeRetour;
		this.name = name;
		this.parametres = parametres;
		this.visibilite = visibilite;
		this.exceptions=null;
	}
	
	public Methode(String typeRetour, String name,  String visibilite) {
		super();
		this.typeRetour = typeRetour;
		this.name = name;
		this.parametres = null;
		this.visibilite = visibilite;
		this.exceptions=null;
	}
	public Methode(String typeRetour, String name,  String visibilite, String[] e) {
		super();
		this.typeRetour = typeRetour;
		this.name = name;
		this.parametres = null;
		this.visibilite = visibilite;
		this.exceptions=e;
	}

	public String getTypeRetour() {
		return typeRetour;
	}

	public String getName() {
		return name;
	}

	public String getParametres() {
		if(parametres.size()==0) {
			return "";
		}else {
			String res=parametres.get(0).toString();
			for(int i=1;i<parametres.size();i++) {
				res+=", "+parametres.get(i).toString();
			}
			return res;
		}
	}

	public String getVisibilite() {
		return visibilite;
	}
	
	@Override
	public String toString() {
		String res="";
		if(parametres==null) {
			if(visibilite.equals("public")) {
				res= "+ "+ name+" ( )";
			}else if(visibilite.equals("private")) {
				res= "- "+ name+" ( )";
			}else if(visibilite.equals("protected")) {
				res= "# "+ name+" ( )";
			}else if(visibilite.equals("abstract")) {
				res= "<<abstract>> "+ name+" ( )";
			}else {
				res= " "+ name+" ( )";
			}
		}else {
			if(visibilite.contains("abstract")) {
				res= "<<abstract>> ";
			}
			if(visibilite.equals("public")) {
				res+= "+ "+ name+" ("+getParametres()+")";
			}else if(visibilite.equals("private")) {
				res+= "- "+ name+" ("+getParametres()+")";
			}else if(visibilite.equals("protected")) {
				res+= "# "+ name+" ("+getParametres()+")";
			}else {
				res+= " "+ name+" ("+getParametres()+")";
			}
			
		}
		if(!typeRetour.equals("")) {
			
			res += " : "+typeRetour;
		}
		return res;
		
			
	}


	
	
}