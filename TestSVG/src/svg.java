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
	static int cmp = 0;
	private static List<Classe> list = new ArrayList<>();

	public static void paintClasse(SVGGraphics2D svgGenerator, Classe classe) {//Création UML de classe
		svgGenerator.setPaint(Color.BLACK);
		List<String> att = classe.getAttribut();
		List<String> meth = classe.getMethode();
		int y = cmp / 3 *300;
		int x = (cmp%3)*300;
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
//		svgGenerator.drawLine(classe.getX(), classe.getY(), classe.getLarg()+classe.getX(), classe.getLongu()+classe.getY());//test trait diagonale pour tester si coordonnée bonne
		cmp += 1;
	}

	public static void paintPackage(SVGGraphics2D svgGenerator , Package p) {//création du package UML
		svgGenerator.setPaint(Color.BLACK);
		int xlen;
		if( p.getClasse().size() >= 3) {
			xlen = 3*285;
		}else {
			xlen = p.getClasse().size()*285;
		}
		p.setX(5);
		p.setY(5+Package.cmpP*600);
		svgGenerator.drawRect( p.getX(), p.getY(), xlen, (p.getClasse().size()/3)*270 );//A modifié lors changement et création algo UML générale par package
		svgGenerator.drawString(p.getName(), p.getX()+2, p.getY()+13);
		svgGenerator.drawRect(p.getX(),p.getY(),p.getName().length()*8+2, 18);
		Package.cmpP++;
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
		Document document = domImpl.createDocument("", "svg", null);

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
		Classe c7 = créationClasse1();
		
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);
		list.add(c6);
		list.add(c7);
		
		List<Classe> liaison = new ArrayList<>();
		liaison.add(c1);
		liaison.add(c3);
		liaison.add(c5);
		liaison.add(c4);
		liaison.add(c6);
		c2.setLiaison(liaison);
		
		List<Classe> liaison2 = new ArrayList<>();
		liaison2.add(c1);
		c4.setLiaison(liaison2);
		
		List<Classe> liaison3 = new ArrayList<>();
		liaison3.add(c1);
		liaison3.add(c3);
		c5.setLiaison(liaison3);
		
		List<Package> paqu = new ArrayList<>();
		svg.triClasse(paqu);
		
		for (Classe c : list) {
			svg.paintClasse(svgGenerator, c);
		}
		for (Classe c : list) {
			svg.paintLink(svgGenerator, c);
		}
		
		
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
		l.add("# ége : Integer");
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
		return new Classe("Matiére",l,l2, "IUT");
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
		return new Classe("test",l,l2, "IUTT");
	}

} 