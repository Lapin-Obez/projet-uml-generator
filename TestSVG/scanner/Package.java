package scanner;

import java.util.ArrayList;
import java.util.List;

public class Package {
	
	private List<Classe> list;
	private String nom;

	public Package(String name) {
		this.nom = name;
		this.list = new ArrayList<>();
	}

	public List<Classe> getList() {
		return list;
	}

	public void setList(List<Classe> list) {
		this.list = list;
	}

	public String getName() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void addClasse( Classe cl) {
		list.add(cl);
	}

	@Override
	public String toString() {
		return "Package [list=" + list + ", nom=" + nom + "]";
	}
	

}
