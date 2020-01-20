package src;

import java.util.ArrayList;
import java.util.List;

public class Classe {
	private String name;
	private List<String> attribut = new ArrayList<>();
	private List<String> methode = new ArrayList<>();
	private List<Classe> liaison = new ArrayList<>();
	private String paquage;
	private int x =0;
	private int y = 0;
	private int larg = 0;
	private int longu = 0;
	
	public Classe(String name, List<String> attribut, List<String> methode, List<Classe> liaison,String pack ) {
		super();
		this.name = name;
		this.attribut = attribut;
		this.methode = methode;
		this.liaison = liaison;
		this.paquage = pack;
	}
	
	

	public String getPaquage() {
		return paquage;
	}
	public void setPaquage(String paquage) {
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

	public Classe(String name, List<String> attribut, List<String> methode, String pack) {
		super();
		this.name = name;
		this.attribut = attribut;
		this.methode = methode;
		this.paquage = pack;
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
	public List<Classe> getLiaison() {
		return liaison;
	}
	public void setLiaison(List<Classe> liaison) {
		this.liaison = liaison;
	}

	@Override
	public boolean equals(Object obj) {
		Classe other = (Classe) obj;
		if (name.equals(other.name))
			return true;
		return false;
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
