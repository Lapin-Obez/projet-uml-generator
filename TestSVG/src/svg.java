package src;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
public class svg {
	int cmp = 0;
	
   public void paintClasse(SVGGraphics2D svgGenerator, Classe classe) {
      svgGenerator.setPaint(Color.BLACK);
      List<String> att = classe.getAttribut();
      List<String> meth = classe.getMethode();
      int y = cmp / 3 *300;
      int x = (cmp%3)*300;
      int pos = 0;
      svgGenerator.drawRect(30+x, 40+y, 200, 40);
      svgGenerator.drawString(classe.getName()+cmp, 105+x, 65+y);
      pos = 67;
      int yrectatt = att.size()*17+10;//+10 car size()*17 en taille mais on commence pas direct donc doit ajouter décalage
      svgGenerator.drawRect(30+x, 80+y, 200, yrectatt);
      for(String s : att) {
    	  svgGenerator.drawString(s+cmp, 50+x, pos+y+30);
    	  pos+=17;//+17 car size()*17
    	  //System.out.println("Tour : " +cmp+ "  Position y : "+(pos+y)+ "       Position x : "+(30+x));
      }     
      int yrectmeth = meth.size()*17+10;
      svgGenerator.drawRect(30+x, 80+y+yrectatt, 200, yrectmeth);
      pos = pos+10;
      for(String s : meth) {
    	  svgGenerator.drawString(s+cmp, 50+x, pos+y+30);
          pos+=17;//+17 car size()*17
      }
      cmp += 2;
   }
   
   public void paintPackage(SVGGraphics2D svgGenerator, List<Classe> classe) {
	   svgGenerator.setPaint(Color.BLACK);
	   svgGenerator.drawRect(5,5,classe.size()*180,classe.size()*180);//On modifié 
	   svgGenerator.drawString(classe.get(0).getPaquage(), 9, 18);
	   svgGenerator.drawRect(5,5,classe.get(0).getPaquage().length()*8, 18);
   }
   
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
		Classe c1 = créationClasse1();
		test.paintClasse(svgGenerator,c1);
		Classe c2 = créationClasse2();
		test.paintClasse(svgGenerator,c2);
		Classe c3 = créationClasse3();
		test.paintClasse(svgGenerator,c3);
		Classe c4 = créationClasse4();
		test.paintClasse(svgGenerator,c4);
		Classe c5 = créationClasse5();
		test.paintClasse(svgGenerator,c5);
		List<Classe> tab = new ArrayList<>();
		c1.setPaquage("IUTTTTTTTTTTTTTTTTTTT");
		c2.setPaquage("IUTTTTTTTTTTTTTTTTTTT");
		c3.setPaquage("IUTTTTTTTTTTTTTTTTTTT");
		c4.setPaquage("IUTTTTTTTTTTTTTTTTTTT");
		c5.setPaquage("IUTTTTTTTTTTTTTTTTTTT");
		tab.add(c5);
		tab.add(c4);
		tab.add(c3);
		tab.add(c2);
		tab.add(c1);
		test.paintPackage(svgGenerator,tab);
		/* sortir le résultat*/
		svgGenerator.stream("Image_TestSVGGen.svg");
	}
   
   public static Classe créationClasse1() {
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
		return new Classe("Etudiant",l,l2);
	}
	
	public static Classe créationClasse2() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# nbrEleve : Integer");
		l.add("# numEtu : Integer");
		l.add("# moyenne : float");
		l2.add("+ getEtud() : List<Etudiant>");
		l2.add("+ getProf() : Professeur()");
		l2.add("+ toString() : String");
		return new Classe("Classe",l,l2);
	}
	
	public static Classe créationClasse3() {
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
		return new Classe("Professeur",l,l2);
	}
	
	public static Classe créationClasse4() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# nbrEleve : String");
		l2.add("+ getnbrEleve() : Integer");
		l2.add("+ getNom() : String");
		l2.add("+ toString() : String");
		return new Classe("Matière",l,l2);
	}
	
	public static Classe créationClasse5() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		return new Classe("Groupe",l,l2);
	}

} 