/**
 * 
 */

import java.util.List;

/**
 * @author UTILISATEUR
 *
 */
public class Classe {
	private String nom;
	private String pack;
	private Methode[] methodes;
	private Argument[] attributs;

	public Classe(String n,Methode[] m,Argument[] a) {
		nom=Lecture.getTerme(n);
		pack=Lecture.getPackage(n);
		methodes=m;
		attributs=a;
	}
	public String getPackage(){return pack;}
	public String getNom() {
		return nom;
	}

	public String getMethodes() {
		String res="";
		for(int i=0;i<methodes.length;i++) {
			res+=methodes[i].toString()+"\n";
		}
		return res;
	}
	public Methode[] getMeth(){
		return this.methodes;
	}

	public String getAttributs() {
		String res="";
		for(int i=0;i<attributs.length;i++) {
			res+=attributs[i].toString()+"\n";
		}
		res+="\n";
		return res;
	}
	public Argument[] getAttribut(){
		return attributs;
	}
	
	public String toString() {
		return nom+":\n\n"+getAttributs()+getMethodes();
	}

	
	
	

}
