import java.util.List;

public class Classe {
	private String name;
	private List<String> attribut;
	private List<String> methode;
	
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

}
