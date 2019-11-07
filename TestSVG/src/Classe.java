package src;

import java.util.List;

public class Classe {
	private String name;
	private List<String> attribut;
	private List<String> methode;
	private List<Classe> liaison;
	
	public Classe(String name, List<String> attribut, List<String> methode, List<Classe> liaison, String paquage) {
		super();
		this.name = name;
		this.attribut = attribut;
		this.methode = methode;
		this.liaison = liaison;
		this.paquage = paquage;
	}
	private String paquage;
	
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
}
