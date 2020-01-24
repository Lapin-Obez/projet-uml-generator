package uml;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class svg {
	
	/**
	 * Méthode qui dessine les classes
	 * @param svgGenerator SVGGraphics2D où on dessine l'UML
	 * @param p list des packages
	 */
	private static void paintClasse(SVGGraphics2D svgGenerator, List<Package> p) {//Creation UML de classe
		svgGenerator.setPaint(Color.BLACK);
		for (Package p1 : p) {//parcours les package et leur classe
			for(Classe classe : p1.getClasse()) {
				List<String> att = classe.getAttribut();
				List<String> meth = classe.getMethode();
				int y = p1.cmpC / 3 *300 + p1.getY();
				int x = (p1.cmpC%3)*280 + p1.getX();//calcule des positions des classes
				int pos = 0;
				
				//calcul largeur maximale
				int largmax = 0;
				for (String s : classe.getAttribut()) {
					if(s.length()*10 >= largmax) {
						largmax = s.length()*10;
					}
				}
				for (String s : classe.getAttribut()) {
					if(s.length()*10 >= largmax) {
						largmax = s.length()*10;
					}
				}
				largmax = largmax + 5 ;//Evité le décalage car on n'écrit pas collé au bordure de la classe
				
				svgGenerator.drawRect(30+x, 40+y, largmax, 40);
				svgGenerator.drawString(classe.getName(), largmax/2+x, 65+y);//dessine nm de la classe
				pos = 67;
				int yrectatt = att.size()*17+10;//+10 car size()*17 en taille mais on commence pas direct donc doit ajouter decalage
				svgGenerator.drawRect(30+x, 80+y, largmax, yrectatt);
				for(String s : att) {//Ecrit les attributs
					svgGenerator.drawString(s, 40+x, pos+y+30);
					pos+=17;//+17 car size()*17
					//System.out.println("Tour : " +cmp+ "  Position y : "+(pos+y)+ "       Position x : "+(30+x));
				}
				int yrectmeth = meth.size()*17+10;
				svgGenerator.drawRect(30+x, 80+y+yrectatt, largmax, yrectmeth);
				pos = pos+10;
				for(String s : meth) {//Ecrit les méthodes
					svgGenerator.drawString(s, 40+x, pos+y+30);
					pos+=17;//+17 car size()*17
				}
				classe.setX(x+30);
				classe.setY(y+40);
				classe.setLarg(largmax);
				classe.setLongu(yrectmeth+40+yrectatt);//Sauvegarde les position et taille des classes
//				svgGenerator.drawLine(classe.getX(), classe.getY(), classe.getLarg()+classe.getX(), classe.getLongu()+classe.getY());//test trait diagonale pour tester si coordonnee bonne
				p1.cmpC += 1;//incrémente les compteur de classes dessiné dans le package
			}
		}
		
	}
	
	/**
	 * méthode qui dessine les packages
	 * @param svgGenerator SVGGraphics2D où on dessine l'UML
	 * @param p list des package à dessiner
	 */
	private static void paintPackage(SVGGraphics2D svgGenerator , List<Package> pack) {//creation du package UML
		svgGenerator.setPaint(Color.BLACK);
		for (Package p : pack) {
			int xlen;
			if( p.getClasse().size() >= 3) {
				xlen = 3*310;
			}else {
				xlen = p.getClasse().size()*310;
			}
			int ylen;
			int tmp = 0;
			if(p.getClasse().size() % 3 > 0) {
				tmp ++;
			}
			ylen = ((p.getClasse().size()/3)+tmp)*315 ;
			p.setX(5 + Package.cmpP%2 * 1000);
			if(Package.cmpP%2>0) {
				p.setY(Package.ancienbas);
			}else {
				p.setY(Package.bas);
			}
			p.setLarg(xlen);
			p.setLongu(ylen);//calcule des position et des taille des packages
			svgGenerator.drawRect( p.getX(), p.getY(), xlen,ylen);
			svgGenerator.drawString(p.getName(), p.getX()+2, p.getY()+13);
			svgGenerator.drawRect(p.getX(),p.getY(),p.getName().length()*7+5, 18);//génération du package
			Package.cmpP++;
			if(Package.bas < p.getY() + ylen+30) {
				Package.ancienbas = Package.bas;
				Package.bas = p.getY() + ylen+30;
			}
			if(Package.droite < p.getX() + xlen+30) {
				Package.droite = p.getX() + xlen+10;
			}//Mise en mémoire du point le plus bas et le plus à droite pour la création de l'UML
		}
	}
	
	/**
	 * Méthode qui dessine les lien entre les classes
	 * @param svgGenerator SVGGraphics2D où on dessine l'UML
	 * @param classe La classe dont on veut dessiner les liens
	 * @param list la list de toutes les classes
	 */
	private static void paintLink(SVGGraphics2D svgGenerator, Classe classe, List<Classe> list) {
		svgGenerator.setPaint(Color.BLACK);
		for(Classe c : list) {
			for(Classe s : classe.getLiaison()) {//parcours des classes et de leur liaisons
				if(s.equals(c)) {//création des liens en fonction de leur position
					if(classe.getX() == s.getX() && classe.getY()>s.getY()) {
						svgGenerator.drawLine( classe.getX()+classe.getLarg()/2, classe.getY() , s.getX()+s.getLarg()/2 , s.getY()+s.getLongu() );
					}else if(classe.getX()<s.getX() && classe.getY() == s.getY()) {
						svgGenerator.drawLine(classe.getX()+classe.getLarg(),classe.getY()+classe.getLongu()/2 , s.getX(), s.getY()+s.getLongu()/2);
					}else if(classe.getX() == s.getX() && classe.getY()<s.getY()) {
						svgGenerator.drawLine(classe.getX()+classe.getLarg()/2, classe.getY()+classe.getLongu(), s.getX()+s.getLarg()/2, s.getY());
					}else if(classe.getX()> s.getX() && classe.getY() == s.getY()){
						svgGenerator.drawLine(classe.getX(),classe.getY()+classe.getLongu()/2, s.getX()+s.getLarg(), s.getY()+s.getLongu()/2);					
					}else if(classe.getX() < s.getX() && classe.getY() > s.getY()) {
						svgGenerator.drawLine( classe.getX()+classe.getLarg(), classe.getY() , s.getX() , s.getY()+s.getLongu() );
					}else if(classe.getX() < s.getX() && classe.getY() < s.getY()) {
						svgGenerator.drawLine( classe.getX()+classe.getLarg(), classe.getY()+classe.getLongu() , s.getX(), s.getY());
					}else if(classe.getX() > s.getX() && classe.getY() < s.getY()) {
						svgGenerator.drawLine( classe.getX(), classe.getY()+classe.getLongu() , s.getX()+s.getLarg() , s.getY() );
					}else if(classe.getX() > s.getX() && classe.getY() > s.getY()) {
						svgGenerator.drawLine( classe.getX(), classe.getY() , s.getX()+s.getLarg() , s.getY()+s.getLongu() );
					}
				}
			}
		}
	}
	
	/**
	 * algo pour trier les classe selon leur package dans un tableau de list
	 * @param pack Liste de package initialement vide qui va être remplit
	 * @param list liste de toute les classes
	 */
	private static void triClasse(List<Package> pack, List<Classe> list) {
//		List<Classe>[] res = new ArrayList[tab.size()]; ancien modèle de tri
//		int taille = 0 ;
//		for(Classe cl : tab) {
//			if(res[0] != null) {
//				boolean aj = false;
//				for(List<Classe> l : res) {
//					if(l != null) {
//						if(cl.getPaquage().equals((l.get(0)).getPaquage()) ) {
//							l.add(cl);
//							aj = true;
//						}
//					}
//				}
//				if(!aj) {
//					res[taille] = new ArrayList<Classe>();
//					res[taille].add(cl);
//					taille ++;
//				}
//			}else {
//				res[taille] = new ArrayList<Classe>();
//				res[taille].add(cl);
//				taille ++;
//			}
//		}
		for(Classe classe : list) {//parcours des classes
			if(pack.size()>0) {//si la liste n'est pas vide
				boolean trv = false;
				for(Package paquage : pack){//onb parcours les package et on cherche si un existant porte le nom du package de la classe
					if(paquage != null) {
						if(classe.getPaquage().equals(paquage.getName())) {
							paquage.addClasse(classe);
							trv = true;
						}
					}
				}
				if(!trv) {//sinon on créé le package 
					Package p = new Package(classe.getPaquage());
					p.addClasse(classe);
					pack.add(p);
			}
			}else {//si elle est vide alors on créé le premier package
				Package p = new Package(classe.getPaquage());
				p.addClasse(classe);
				pack.add(p);
			}
		}
	}
	
	/**
	 * Méthode qui dessine sur une image  SVG un diagramme de classe UML
	 * @param list La liste des classes
	 * @param name Le nom du fichier .svg qui va être créé
	 */
	public static void createUML(List<Classe> list, String name) {
		/*Creation de l'instance Document qui sera utilise pour construire le contenu XML
	      creation d'une instance de svggenerator (graphics2D) en utilisant le doc cree */
		// Recupere la DOMImplementation
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

		// Creation d'une instance de SVG
		Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);

		//Creation d'une instance de SVG Generator
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

		/* Dessine sur le composant svggenerator */
		
		svgGenerator.setPaint(Color.white);
		
		List<Package> paqu = new ArrayList<>();
		svg.triClasse(paqu, list);
//		for(Package p : paqu) { //test du trie des packages
//				System.out.println(p.getName() + " : ");
//				for(Classe c : p.getClasse()) {
//					System.out.println(c.toString());
//				}
//				System.out.println(" ///// ");
//				
//		}
		svg.paintPackage(svgGenerator, paqu);
		svg.paintClasse(svgGenerator, paqu);
		for (Classe c : list) {
			svg.paintLink(svgGenerator, c, list);
		}
		
		Element root = svgGenerator.getRoot();//génération des options pour pouvoir redimensionner l'image sur l'IHM
		root.setAttributeNS(null, "width", Package.droite+"");
		root.setAttributeNS(null, "height", Package.bas+"");
		/* sortir le resultat*/
		Writer out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(name+".svg"), "UTF-8");
			svgGenerator.stream(root,out, true, false);//création du fichier
		} catch (UnsupportedEncodingException | FileNotFoundException | SVGGraphics2DIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String [] args) throws IOException {
		
		List<Classe> list = new ArrayList<>();
		Classe c1 = creationClasse1();
		Classe c2 = creationClasse2();
		Classe c3 = creationClasse3();
		Classe c4 = creationClasse4();
		Classe c5 = creationClasse5();
		Classe c6 = creationClasse6();
		Classe c7 = creationClasse7();
		
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);
		list.add(c6);
		list.add(c7);
		
		c2.addLiaison(c1);
		c1.addLiaison(c2);
		c2.addLiaison(c5);
		c4.addLiaison(c2);
		c2.addLiaison(c6);
		c7.addLiaison(c6);
		
		svg.createUML(list, "test-UML-Create");
	}

	public static Classe creationClasse1() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l.add("# âge : Integer");
		l.add("# ddn : Date");
		l.add("# numEtu : Integer");
		l.add("# moyenne : float");
		l2.add("+ getNumEtu() : Integer");
		l2.add("+ getNomPrenom() : String");
		l2.add("+ getDDN() : Date()");
		l2.add("+ getClasse() : Classe()");
		l2.add("+ toString() : String");
		return new Classe("Etudiant",l,l2, "IUT");
	}

	public static Classe creationClasse2() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# nbrEleve : Integer");
		l.add("# numEtu : Integer");
		l.add("# moyenne : float");
		l2.add("+ getEtud() : List<Etudiant>");
		l2.add("+ getProf() : Professeur()");
		l2.add("+ toString() : String");
		return new Classe("Classe",l,l2, "IUT");
	}

	public static Classe creationClasse3() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# Prenom : String");
		l.add("# age : Integer");
		l.add("# ddn : Date");
		l.add("# numProf : Integer");
		l.add("# nbrEleve : Integer");
		l2.add("+ getNumProf() : Integer");
		l2.add("+ getNomPrenom() : String");
		l2.add("+ getDDN() : Date()");
		l2.add("+ getnbrEleve() : Integer");
		l2.add("+ toString() : String");
		return new Classe("Professeur",l,l2, "IUT");
	}

	public static Classe creationClasse4() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# nbrEleve : String");
		l2.add("+ getnbrEleve() : Integer");
		l2.add("+ getNom() : String");
		l2.add("+ toString() : String");
		return new Classe("Matiere",l,l2, "IUT");
	}

	public static Classe creationClasse5() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		l2.add("+ setNum() : void");
		return new Classe("Groupe",l,l2, "IUT");
	}
	
	public static Classe creationClasse6() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		l2.add("+ setNum() : void");
		return new Classe("test",l,l2, "testO");
	}
	
	public static Classe creationClasse7() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		l2.add("+ setNum() : void");
		return new Classe("Intermittant",l,l2, "testO");
	}
	

} 