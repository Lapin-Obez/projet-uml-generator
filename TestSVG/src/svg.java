package src;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class svg {
	private static List<Classe> list = new ArrayList<>();

	public static void paintClasse(SVGGraphics2D svgGenerator, List<Package> p) {//Création UML de classe
		svgGenerator.setPaint(Color.BLACK);
		for (Package p1 : p) {
			for(Classe classe : p1.getClasse()) {
				List<String> att = classe.getAttribut();
				List<String> meth = classe.getMethode();
				int y = p1.cmpC / 3 *300 + p1.getY();
				int x = (p1.cmpC%3)*270 + p1.getX();
				int pos = 0;
				svgGenerator.drawRect(30+x, 40+y, 200, 40);
				svgGenerator.drawString(classe.getName(), 105+x, 65+y);
				pos = 67;
				int yrectatt = att.size()*17+10;//+10 car size()*17 en taille mais on commence pas direct donc doit ajouter décalage
				svgGenerator.drawRect(30+x, 80+y, 200, yrectatt);
				for(String s : att) {
					svgGenerator.drawString(s, 50+x, pos+y+30);
					pos+=17;//+17 car size()*17
					//System.out.println("Tour : " +cmp+ "  Position y : "+(pos+y)+ "       Position x : "+(30+x));
				}
				int yrectmeth = meth.size()*17+10;
				svgGenerator.drawRect(30+x, 80+y+yrectatt, 200, yrectmeth);
				pos = pos+10;
				for(String s : meth) {
					svgGenerator.drawString(s, 50+x, pos+y+30);
					pos+=17;//+17 car size()*17
				}
				classe.setX(x+30);
				classe.setY(y+40);
				classe.setLarg(200);
				classe.setLongu(yrectmeth+40+yrectatt);
//				svgGenerator.drawLine(classe.getX(), classe.getY(), classe.getLarg()+classe.getX(), classe.getLongu()+classe.getY());//test trait diagonale pour tester si coordonnée bonne
				p1.cmpC += 1;
			}
		}
		
	}

	public static void paintPackage(SVGGraphics2D svgGenerator , Package p) {//création du package UML
		svgGenerator.setPaint(Color.BLACK);
		int xlen;
		if( p.getClasse().size() >= 3) {
			xlen = 3*285;
		}else {
			xlen = p.getClasse().size()*285;
		}
		int ylen;
		int tmp = 0;
		if(p.getClasse().size() % 3 > 0) {
			tmp ++;
		}
		ylen = ((p.getClasse().size()/3)+tmp)*305 ;
		p.setX(5 + Package.cmpP%2 * 900);
		if(Package.cmpP%2>0) {
			p.setY(Package.ancienbas);
		}else {
			p.setY(Package.bas);
		}
		p.setLarg(xlen);
		p.setLongu(ylen);
		svgGenerator.drawRect( p.getX(), p.getY(), xlen,ylen);//A modifié lors changement et création algo UML générale par package
		svgGenerator.drawString(p.getName(), p.getX()+2, p.getY()+13);
		svgGenerator.drawRect(p.getX(),p.getY(),p.getName().length()*7+5, 18);
		Package.cmpP++;
		if(Package.bas < p.getY() + ylen+30) {
			Package.ancienbas = Package.bas;
			Package.bas = p.getY() + ylen+30;
		}
		if(Package.droite < p.getX() + xlen+30) {
			Package.droite = p.getX() + xlen+10;
		}
	}
	
	public static void paintLink(SVGGraphics2D svgGenerator, Classe classe) {
		svgGenerator.setPaint(Color.BLACK);
		for(Classe c : list) {
			for(Classe s : classe.getLiaison()) {
				if(s.equals(c)) {
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

	public static void triClasse(List<Package> pack) {//algo pour trier les classe selon leur package dans un tableau de list
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
		for(Classe classe : list) {
			if(pack.size()>0) {
				boolean trv = false;
				for(Package paquage : pack){
					if(paquage != null) {
						if(classe.getPaquage().equals(paquage.getName())) {
							paquage.addClasse(classe);
							trv = true;
						}
					}
				}
				if(!trv) {
					Package p = new Package(classe.getPaquage());
					p.addClasse(classe);
					pack.add(p);
			}
			}else {
				Package p = new Package(classe.getPaquage());
				p.addClasse(classe);
				pack.add(p);
			}
		}
	}

	public static void main(String [] args) throws IOException {
		/*Creation de l'instance Document qui sera utilisé pour construire le contenu XML
	      création d'une instance de svggenerator (graphics2D) en utilisant le doc créé */
		// Récupére la DOMImplementation
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

		// Création d'une instance de SVG
		Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);

		//Création d'une instance de SVG Generator
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

		/* Dessine sur le composant svggenerator */
		svg test;
		test = new svg();
		svgGenerator.setPaint(Color.white);
		
		Classe c1 = créationClasse1();
		Classe c2 = créationClasse2();
		Classe c3 = créationClasse3();
		Classe c4 = créationClasse4();
		Classe c5 = créationClasse5();
		Classe c6 = créationClasse6();
		Classe c8 = créationClasse8();
		Classe c7 = créationClasse7();
		Classe c9 = créationClasse9();
		Classe c10 = créationClasse10();
		Classe c11 = créationClasse11();
		Classe c12 = créationClasse10();
		Classe c13 = créationClasse10();
		Classe c14 = créationClasse10();
		
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);
		list.add(c6);
		list.add(c7);
		list.add(c8);
		list.add(c9);
		list.add(c10);
		list.add(c11);
		list.add(c12);
		list.add(c13);
		list.add(c14);
		
		c2.addLiaison(c1);
		c2.addLiaison(c3);
		c2.addLiaison(c5);
		c2.addLiaison(c4);
		c2.addLiaison(c6);

		c4.addLiaison(c1);
		
		c5.addLiaison(c1);
		c5.addLiaison(c3);
		c9.addLiaison(c8);
		c2.addLiaison(c9);
		c10.addLiaison(c7);
		c11.addLiaison(c9);
		c11.addLiaison(c10);
		c11.addLiaison(c14);
		c12.addLiaison(c10);
		
		List<Package> paqu = new ArrayList<>();
		svg.triClasse(paqu);
		
		for(Package p : paqu) {
			if(p != null) {
				System.out.println(p.getName() + " : ");
				for(Classe c : p.getClasse()) {
					System.out.println(c.toString());
				}
				System.out.println(" ///// ");
				svg.paintPackage(svgGenerator, p);
			}
		}
		svg.paintClasse(svgGenerator, paqu);
		for (Classe c : list) {
			svg.paintLink(svgGenerator, c);
		}
		
		Element root = svgGenerator.getRoot();
		root.setAttributeNS(null, "width", Package.droite+"");
		root.setAttributeNS(null, "height", Package.bas+"");
		/* sortir le résultat*/
		Writer out = new OutputStreamWriter(new FileOutputStream("Test_SVG.svg"), "UTF-8");
		svgGenerator.stream(root,out, true, false);
	}

	public static Classe créationClasse1() {
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
		return new Classe("Classe",l,l2, "IUT");
	}

	public static Classe créationClasse3() {
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
		l2.add("+ getnbrEleve : Integer");
		l2.add("+ toString() : String");
		return new Classe("Professeur",l,l2, "IUT");
	}

	public static Classe créationClasse4() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Nom : String");
		l.add("# nbrEleve : String");
		l2.add("+ getnbrEleve() : Integer");
		l2.add("+ getNom() : String");
		l2.add("+ toString() : String");
		return new Classe("Matiere",l,l2, "IUT");
	}

	public static Classe créationClasse5() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		l2.add("+ setNum() : void");
		return new Classe("Groupe",l,l2, "IUT");
	}
	
	public static Classe créationClasse6() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		l2.add("+ setNum() : void");
		return new Classe("test",l,l2, "testO");
	}
	
	public static Classe créationClasse7() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		l2.add("+ setNum() : void");
		return new Classe("Sett",l,l2, "testO");
	}
	
	public static Classe créationClasse8() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		l2.add("+ action() : String");
		l2.add("+ lancer_de_dé() : String");
		l2.add("+ setNum() : void");
		return new Classe("T32",l,l2, "Robot");
	}
	
	public static Classe créationClasse9() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# tabClasse : Classe[]()");
		l2.add("+ getNum() : Integer");
		l2.add("+ toString() : String");
		l2.add("+ crier() : String");
		l2.add("+ setNum() : void");
		return new Classe("Terminator",l,l2, "Robot");
	}
	
	public static Classe créationClasse10() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# Num : Integer");
		l.add("# QI : Integer");
		l2.add("+ toString() : String");
		l2.add("+ setQi(x : Integer) : void");
		return new Classe("Tanguy",l,l2, "Tesseract");
	}
	
	public static Classe créationClasse11() {
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("# QI : Integer");
		l2.add("+ toString() : String");
		l2.add("+ setQi(x : Integer) : void");
		return new Classe("Mat",l,l2, "Avengers");
	}


} 