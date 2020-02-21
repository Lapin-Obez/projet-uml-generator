package srcALire.fichier;

import java.util.Date;

public class Fichier extends Element{

	private String contenu;

	public Fichier(String nom, int taille, Date date, String contenu) {
		super(nom, taille, date);
		this.contenu = contenu;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	@Override
	public String toString() {
		return "Fichier [contenu=" + contenu + "]";
	}
	
}
