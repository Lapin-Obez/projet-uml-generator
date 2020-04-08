package scanner;

import java.util.ArrayList;
import java.util.List;

public class Package {
	
	private List<Classe> list;//liste des classes
	private String nom;//nom du package
	//Classe qui repr�sente un package sera utilis� lors du passage au format plantuml
	/**
	 * Constructeur de Package
	 * @param name le nom que portera le package
	 */
	public Package(String name) {
		this.nom = name;
		this.list = new ArrayList<>();
	}

	/**
	 * M�thode pour obtenir la list de classe du package
	 * @return une liste des classes
	 */
	public List<Classe> getList() {
		return list;
	}

	/**
	 * d�finit la liste de classe du package
	 * @param list la nouvelle liste de classe du package
	 */
	public void setList(List<Classe> list) {
		this.list = list;
	}
	/**
	 * M�thode pour obtenir le nom du package
	 * @return
	 */
	public String getName() {
		return nom;
	}

	/**
	 * M�thode pour changer le nom du package
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * M�thode pour ajouter une classe � la liste de classe du package
	 * @param cl
	 */
	public void addClasse( Classe cl) {
		list.add(cl);
	}

	/**
	 * M�thode qui d�crit le package
	 */
	@Override
	public String toString() {
		return "Package [list=" + list + ", nom=" + nom + "]";
	}
	

}
