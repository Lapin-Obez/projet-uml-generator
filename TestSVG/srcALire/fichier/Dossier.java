package srcALire.fichier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dossier extends Element{

	private List<Element> l = new ArrayList<>();

	public Dossier(String nom, int taille, Date date) {
		super(nom, taille, date);
	}
	
	public void ajoutElem(Element elem) {
		l.add(elem);
	}

	public List<Element> getL() {
		return l;
	}

	public void setL(List<Element> l) {
		this.l = l;
	}

	@Override
	public String toString() {
		return "Dossier [l=" + l + "]";
	}
	
}
