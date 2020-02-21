package srcALire.fichier;

import java.util.Date;

public abstract class Element {
	
	protected String nom;
	protected int taille;
	protected Date date;
	
	public Element(String nom, int taille, Date date) {
		super();
		this.nom = nom;
		this.taille = taille;
		this.date = date;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
}
