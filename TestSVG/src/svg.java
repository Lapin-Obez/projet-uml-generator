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
	
   public void paint(SVGGraphics2D svgGenerator, Classe classe) {
      svgGenerator.setPaint(Color.BLACK);
      List<String> att = classe.getAttribut();
      List<String> meth = classe.getMethode();
      int y = cmp / 3 *300;
      int x = (cmp%3)*300;
      int pos = 0;
      svgGenerator.drawRect(10+x, 10+y, 200, 40);
      svgGenerator.drawString(classe.getName()+cmp, 85+x, 35+y);
      pos = 67;
      int yrectatt = att.size()*17+10;//+10 car size()*17 en taille mais on commence pas direct donc doit ajouter d�calage
      svgGenerator.drawRect(10+x, 50+y, 200, yrectatt);
      for(String s : att) {
    	  svgGenerator.drawString(s+cmp, 30+x, pos+y);
    	  pos+=17;//+17 car size()*17
    	  //System.out.println("Tour : " +cmp+ "  Position y : "+(pos+y)+ "       Position x : "+(30+x));
      }     
      int yrectmeth = meth.size()*17+10;
      svgGenerator.drawRect(10+x, 50+y+yrectatt, 200, yrectmeth);
      pos = pos+10;
      for(String s : meth) {
    	  svgGenerator.drawString(s+cmp, 30+x, pos+y);
          pos+=17;//+17 car size()*17
      }
      cmp += 2;
   }
   
   public static void main(String [] args) throws IOException {
		/*Creation de l'instance Document qui sera utilis� pour construire le contenu XML
	      cr�ation d'une instance de svggenerator (graphics2D) en utilisant le doc cr�� */
		// R�cup�re la DOMImplementation
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

		// Cr�ation d'une instance org.w3c.dom.Document
		Document document = domImpl.createDocument(null, "svg", null);

		//Cr�ation d'une instance de SVG Generator
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

		/* Dessine sur le composant svggenerator */
		svg test;
		test = new svg();
		svgGenerator.setPaint(Color.white);
		svgGenerator.fill(new Rectangle(0,0,1000,1000));
		Classe c = cr�ationClasse1();
		test.paint(svgGenerator,c);
		c = cr�ationClasse2();
		test.paint(svgGenerator,c);
		c = cr�ationClasse3();
		test.paint(svgGenerator,c);
		c = cr�ationClasse4();
		test.paint(svgGenerator,c);
		c = cr�ationClasse5();
		test.paint(svgGenerator,c);
		/* sortir le r�sultat*/
		svgGenerator.stream("Image_TestSVGGen.svg");
	}
   
   public static Classe cr�ationClasse1() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l.add("# �ge : Integer");
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
	
	public static Classe cr�ationClasse2() {
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
	
	public static Classe cr�ationClasse3() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l.add("# �ge : Integer");
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
	
	public static Classe cr�ationClasse4() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# nbrEleve : String");
		l2.add("+ getnbrEleve() : Integer");
		l2.add("+ getNom() : String");
		l2.add("+ toString() : String");
		return new Classe("Mati�re",l,l2);
	}
	
	public static Classe cr�ationClasse5() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		return new Classe("Groupe",l,l2);
	}

} 