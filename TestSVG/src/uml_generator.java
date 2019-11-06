import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class uml_generator {
		
	public static void main(String [] args) throws IOException {
		/*Creation de l'instance Document qui sera utilisé pour construire le contenu XML
	      création d'une instance de svggenerator (graphics2D) en utilisant le doc créé */
		// Récupère la DOMImplementation
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

		// Création d'une instance org.w3c.dom.Document
		Document document = domImpl.createDocument(null, "svg", null);

		//Création d'une instance de SVG Generator
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

		/* Dessine sur le composant svggenerator */
		svg test;
		test = new svg();
		svgGenerator.setPaint(Color.white);
		svgGenerator.fill(new Rectangle(0,0,1000,1000));
		Classe c = null;
		créationClasse1(c);
		test.paint(svgGenerator,c);
		créationClasse2(c);
		test.paint(svgGenerator,c);
		créationClasse3(c);
		test.paint(svgGenerator,c);
		créationClasse4(c);
		test.paint(svgGenerator,c);
		créationClasse5(c);
		test.paint(svgGenerator,c);
		créationClasse6(c);
		test.paint(svgGenerator,c);
		créationClasse7(c);
		test.paint(svgGenerator,c);
		créationClasse8(c);
		test.paint(svgGenerator,c);
		créationClasse9(c);
		test.paint(svgGenerator,c);
		/* sortir le résultat*/
		svgGenerator.stream("Image_TestSVGGen.svg");
	}
	

	public static void créationClasse1(Classe c) {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l.add("# Âge : Integer");
		l.add("# ddn : Date");
		l.add("# numEtu : Integer");
		l.add("# moyenne : float");
		l2.add("+ getNumEtu() : Integer");
		l2.add("+ getNomPrenom() : String");
		l2.add("+ getDDN() : Date()");
		l2.add("+ getClasse() : Classe()");
		l2.add("+ toString() : String");
		c = new Classe("Etudiant",l,l2);
	}
	
	public static void créationClasse2(Classe c) {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# nbrEleve : Integer");
		l.add("# numEtu : Integer");
		l.add("# moyenne : float");
		l2.add("+ getEtud() : List<Etudiant>");
		l2.add("+ getProf() : Professeur()");
		l2.add("+ toString() : String");
		c = new Classe("Classe",l,l2);
	}
	
	public static void créationClasse3(Classe c) {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l.add("# Âge : Integer");
		l.add("# ddn : Date");
		l.add("# numProf : Integer");
		l.add("# nbrEleve : Integer");
		l2.add("+ getNumProf() : Integer");
		l2.add("+ getNomPrenom() : String");
		l2.add("+ getDDN() : Date()");
		l2.add("+ getnbrEleve : Integer");
		l2.add("+ toString() : String");
		c = new Classe("Proffesseur",l,l2);
	}
	
	public static void créationClasse4(Classe c) {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# nbrEleve : String");
		l2.add("+ getnbrEleve() : Integer");
		l2.add("+ getNom() : String");
		l2.add("+ toString() : String");
		c = new Classe("Matière",l,l2);
	}
	
	public static void créationClasse5(Classe c) {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		c = new Classe("Groupe",l,l2);
	}
	
	public static void créationClasse6(Classe c) {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l2.add("+ getNumEtu() : Integer");
		l2.add("+ getClasse() : Class()");
		l2.add("+ toString() : String");
		c = new Classe("Employé",l,l2);
	}
	
	public static void créationClasse7(Classe c) {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l.add("# Âge : Integer");
		l.add("# numEtu : Integer");
		l2.add("+ getNumEtu() : Integer");
		l2.add("+ getDDN() : Date()");
		l2.add("+ toString() : String");
		c = new Classe("Employé",l,l2);
	}
	
	public static void créationClasse8(Classe c) {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# ddn : Date");
		l.add("# numEtu : Integer");
		l2.add("+ getNumEtu() : Integer");
		l2.add("+ getNomPrenom() : String");
		l2.add("+ getClasse() : Class()");
		l2.add("+ toString() : String");
		c = new Classe("Employé",l,l2);
	}
	
	public static void créationClasse9(Classe c) {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l.add("# ddn : Date");
		l.add("# numEtu : Integer");
		l.add("# Moyenne : float");
		l2.add("+ getNumEtu() : Integer");
		l2.add("+ toString() : String");
		c = new Classe("Employé",l,l2);
	}
}
