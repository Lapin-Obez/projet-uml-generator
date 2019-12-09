package src;

import java.util.List;

public class Classe {
	private String name;
	private List<String> attribut;
	private List<String> methode;
	private List<Classe> liaison;
	private String paquage;
	private int x =0;
	private int y = 0;
	private int longu = 0;
	private int larg = 0;
	
	public Classe(String name, List<String> attribut, List<String> methode, List<Classe> liaison, String paquage) {
		super();
		this.name = name;
		this.attribut = attribut;
		this.methode = methode;
		this.liaison = liaison;
		this.paquage = paquage;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLongu() {
		return longu;
	}

	public void setLongu(int longu) {
		this.longu = longu;
	}

	public int getLarg() {
		return larg;
	}

	public void setLarg(int larg) {
		this.larg = larg;
	}

	public Classe(String name, List<String> attribut, List<String> methode) {
		super();
		this.name = name;
		this.attribut = attribut;
		this.methode = methode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAttribut() {
		return attribut;
	}
	public void setAttribut(List<String> attribut) {
		this.attribut = attribut;
	}
	public List<String> getMethode() {
		return methode;
	}
	public void setMethode(List<String> methode) {
		this.methode = methode;
	}
	public String getPaquage() {
		return paquage;
	}
	public void setPaquage(String paquage) {
		this.paquage = paquage;
	}
	public List<Classe> getLiaison() {
		return liaison;
	}
	public void setLiaison(List<Classe> liaison) {
		this.liaison = liaison;
	}
	public String toString() {
		String res =  "Nom " + name ;
		if(paquage != null) {
			res = res +" package " + paquage; 
		}
		res = res+ " Attribut " + attribut.toString() + " M�thode " + methode.toString();
		if(liaison != null) {
			res= res + liaison.toString();
		}
		return res;
	}
}
