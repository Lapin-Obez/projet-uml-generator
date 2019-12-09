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

	public void paintClasse(SVGGraphics2D svgGenerator, Classe classe) {//Création UML de classe
		svgGenerator.setPaint(Color.BLACK);
		List<String> att = classe.getAttribut();
		List<String> meth = classe.getMethode();
		int y = cmp / 3 *300;
		int x = (cmp%3)*300;
		int pos = 0;
		svgGenerator.drawRect(30+x, 40+y, 200, 40);
		svgGenerator.drawString(classe.getName()+" "+cmp, 105+x, 65+y);
		pos = 67;
		int yrectatt = att.size()*17+10;//+10 car size()*17 en taille mais on commence pas direct donc doit ajouter décalage
		svgGenerator.drawRect(30+x, 80+y, 200, yrectatt);
		for(String s : att) {
			svgGenerator.drawString(s+" "+cmp, 50+x, pos+y+30);
			pos+=17;//+17 car size()*17
			//System.out.println("Tour : " +cmp+ "  Position y : "+(pos+y)+ "       Position x : "+(30+x));
		}     
		int yrectmeth = meth.size()*17+10;
		svgGenerator.drawRect(30+x, 80+y+yrectatt, 200, yrectmeth);
		pos = pos+10;
		for(String s : meth) {
			svgGenerator.drawString(s+" "+cmp, 50+x, pos+y+30);
			pos+=17;//+17 car size()*17
		}
		classe.setX(x+30);
		classe.setY(y+40);
		cmp += 1;
	}

	public void paintPackage(SVGGraphics2D svgGenerator, List<Classe> classe) {//création du package UML
		svgGenerator.setPaint(Color.BLACK);
		svgGenerator.drawRect(5,5,classe.size()*180,classe.size()*110);//A modifié lors changement et création algo UML générale par package
		svgGenerator.drawString(classe.get(0).getPaquage(), 7, 18);
		svgGenerator.drawRect(5,5,classe.get(0).getPaquage().length()*8, 18);
	}
	
	public void paintLink(SVGGraphics2D svgGenerator, Classe classe) {
		svgGenerator.setPaint(Color.BLACK);
		//svgGenerator.drawLine(x1, y1, x2, y2);
	}

	public List<Classe>[] triClasse(List<Classe> tab) {//algo pour trier les classe selon leur package dans un tableau de list
		List<Classe>[] res = new ArrayList[tab.size()];
		int taille = 0 ;
		for(Classe cl : tab) {
			if(res[0] != null) {
				boolean aj = false;
				for(List<Classe> l : res) {
					if(l != null) {
						if(cl.getPaquage().equals((l.get(0)).getPaquage()) ) {
							l.add(cl);
							aj = true;
						}
					}
				}
				if(!aj) {
					res[taille] = new ArrayList<Classe>();
					res[taille].add(cl);
					taille ++;
				}
			}else {
				res[taille] = new ArrayList<Classe>();
				res[taille].add(cl);
				taille ++;
			}
		}
		return res;
	}

	public static void main(String [] args) throws IOException {
		/*Creation de l'instance Document qui sera utilisé pour construire le contenu XML
	      création d'une instance de svggenerator (graphics2D) en utilisant le doc créé */
		// Récupére la DOMImplementation
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

		// Création d'une instance org.w3c.dom.Document
		Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);

		//Création d'une instance de SVG Generator
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

		/* Dessine sur le composant svggenerator */
		svg test;
		test = new svg();
		svgGenerator.setPaint(Color.white);
//		svgGenerator.fill(new Rectangle(0,0,500,500));
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
		c1.setPaquage("IUT");c2.setPaquage("IUTTTT");c3.setPaquage("IUT");c4.setPaquage("IUTT");c5.setPaquage("IUT");
		tab.add(c5);tab.add(c4);tab.add(c3);tab.add(c2);tab.add(c1);
		List[] listF = new ArrayList[tab.size()];
		listF = test.triClasse(tab);
		for(List<Classe> l : listF) {
			if(l != null) {
				System.out.println(l.getClass() + " : ");
				for(Classe c : l) {
					System.out.println(c.toString());
				}
				System.out.println(" ///// ");
			}
		}
		test.paintPackage(svgGenerator,tab);
		/* sortir le résultat*/
		svgGenerator.stream("Image_TestSVGGen.svg");
	}

	public static Classe créationClasse1() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l.add("# ége : Integer");
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
		l.add("# ége : Integer");
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
		return new Classe("Matiére",l,l2);
	}

	public static Classe créationClasse5() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		l2.add("+ setNum() : void");
		return new Classe("Groupe",l,l2);
	}

} 