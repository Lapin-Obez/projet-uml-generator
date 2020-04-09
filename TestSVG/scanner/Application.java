package scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import srcALire.*;
import srcALire.Cours.*;
import srcALire.Groupe.*;
import srcALire.fichier.Dossier;
import srcALire.fichier.Element;
import srcALire.fichier.Fichier;

public class Application {

	/**Anciennce fonction
	 * Fonction qui dessine un diagramme UML sur une image svg via les donn√©e pass√© dans la liste de classe
	 * @param l liste des classes √† dessiner
	 * @param name nom du fichier .svg √† g√©n√©rer
	 */
	//	public static void UML(List<Classe> l, String name) {
	//		 List<uml.Classe> listC = new ArrayList<>();
	//	        for (Classe classe : l) {//traduction des Classe en src.Classe pour g√©n√©rer l'image UML
	//	        	List<String> lmeth = new ArrayList<>();
	//				for (String s : classe.getMethodes()) {
	//					lmeth.add(s);
	//				}
	//				List<String> latt = new ArrayList<>();
	//				for (Argument att : classe.getAttributs()) {
	//					latt.add(att.toString());
	//				}
	//				uml.Classe c0 = new uml.Classe(classe.getNom(), latt, lmeth, classe.getPackage());
	//				listC.add(c0);
	//			}
	//	        for(Classe classe : l) {//g√©n√©ration des liens sur les classe src.Classe
	//	        	uml.Classe depart = null;
	//	        	for (uml.Classe classe2 : listC) {
	//					if(classe2.getName().equals(classe.getNom())) {
	//						depart = classe2;
	//					}
	//				}
	//	        	uml.Classe arriver = null;
	//	        	for (Lien lien : classe.getLiens()) {
	//	        		for (uml.Classe classe2 : listC) {
	//						if(classe2.getName().equals(lien.getLier().getNom())) {
	//							arriver = classe2;
	//						}
	//					}
	//	        	if(depart != null && arriver != null)
	//	        		depart.addLiaison(arriver);
	//				}
	//	        }
	//	     uml.svg.createUML(listC, name);//appel de la fonction pour cr√©√© l'UML
	//	}

	/**
	 * Fonction qui cr√©√© le fichier contenant le plantuml
	 * @param l la liste des classes dont on va cr√©er le diagramme en plantuml 
	 * @param name le nom du fichiers .txt qui sera cr√©er contenant le plantuml
	 */
	public static void fichier(List<Classe> l, String name) {
		List<Package> pack = new ArrayList<>();
		Map<String,Boolean> tab = new HashMap<>();
		for (Classe cl : l) {//cr√©ation d'une hashmap qui est util pour empecher le d√©doublement des liens
			tab.put(cl.getNom(),false);
		}
		Application.triClasse(pack, l);//trie des packages
		try {
			Writer write = new FileWriter(name+".txt");//ouverture du fichiers de sortit
			write.write("@startuml\n");//d√©but du plantuml
			/* Exemple plantuml
			 * @startuml
				package "Classic Collections" #DDDDDD {
				class Dummy1 {
				  +myMethods()
				}
				class Dummy2 {
				  +hiddenMethod()
				}
				}
				package net.sourceforge.plantuml {
				class Dummy3 <<Serializable>> {
					String name
				}
				}
				Dummy3 -- Dummy1
				@enduml
			 */
			for (Package paqu : pack) {//Parcours les packages
				write.write("package \""+paqu.getName()+"\"{\n");//cr√©ation du package en plantuml
				for (Classe classe : paqu.getList()) {//parcours les classe

					if(classe.isInterface())//affichage si la classe est une interface ou une classe ou abstrait
						write.write("interface "+classe.getNom()+" {\n");
					if(classe.isAbstrait())
						write.write("abstract class "+classe.getNom()+" {\n");
					else
						write.write("class "+classe.getNom()+" {\n");

					for (Argument s : classe.getAttributs()) {//affichage des attributs
						write.write("{field} "+s.toString()+"\n");
					}
					for (String s : classe.getMethodes()) {//affichage des mathodes
						write.write("{method} "+s.toString()+"\n");
					}
					write.write("}\n");//ferme la classe
				}
				write.write("}\n");//ferme le package
			}
			for (Classe classe : l) {//affichage des liens entre classe avec leur multiplicit√©
				List<Lien> liens = classe.getLiens();
				for (Lien lien : liens) {
					if( !tab.get(lien.getLier().getNom())) {//v√©rifie si le lien n'a pas d√©j√† √©t√© fait dans un sens
						write.write("\n"+classe.getNom() + "\""+lien.getMultipliciteF()+"\" -- \""+lien.getMultipliciteD() +"\"" + lien.getLier().getNom()+"\n");
					}
				}
				tab.replace(classe.getNom(), true);//indique que le lien a d√©j√† √©t√© fait dans un sens
				if(classe.isExtend()) {//g√®re les extends
					write.write("\n"+classe.getNom()+" --|> "+classe.getExt().getNom()+"\n");
				}
				if(classe.isImplement()) {//g√®re les implementation
					for (Classe ck : classe.getImpl()) {
						write.write("\n"+classe.getNom()+" ..|> "+ck.getNom()+"\n");
					}
				}
			}
			write.write("class explicationVisibilit√© {\n"+
						"- private\n"+
						"+ public\n"+
						"# protected\n"+
						"~ package\n } ");
			write.write("\n@enduml");//ferme le plantuml
			write.close();//ferme le Writer du fichiers
		} catch (IOException e) {
			System.out.println(e+" "+e.getMessage());
		}
	}

	/**
	 * Fonction qui trie les classes dans une liste de leur package
	 * @param pack la list des package ( entr√©e/sortie ) vide au d√©part puis en sortit elle contiendra les package et leur classe tri√©
	 * @param list
	 */
	private static void triClasse(List<Package> pack, List<Classe> list) {
		for(Classe classe : list) {//parcours des classes
			if(pack.size()>0) {//si la liste n'est pas vide
				boolean trv = false;
				for(Package paquage : pack){//on parcours les packages et on cherche si un existant porte le nom du package de la classe
					if(paquage != null) {
						if(classe.getPackage().equals(paquage.getName())) {
							paquage.addClasse(classe);
							trv = true;
						}
					}
				}
				if(!trv) {//sinon on cr√©√© le package 
					Package p = new Package(classe.getPackage());
					p.addClasse(classe);
					pack.add(p);
				}
			}else {//si elle est vide alors on cr√©√© le premier package
				Package p = new Package(classe.getPackage());
				p.addClasse(classe);
				pack.add(p);
			}
		}
	}
	
	public static List<Classe> chercherExtend(List<Classe> li,List<Class> cla){
		for(int i=0;i<li.size();i++){
			for (Class e:cla) {
				if(li.get(i).getNom().equals(Lecture.getTermeC(e.getName()).trim())){
					if(!li.get(i).isInterface()&&!e.getSuperclass().getName().equals("java.lang.Object")){
						for(int j =0;j<li.size();j++){
							if(li.get(j).getNom().equals(Lecture.getTermeC(e.getSuperclass().getName()).trim())){
								li.get(i).setExt(li.get(j));
							}
						}
					}
					Class[] inter = e.getInterfaces();
					if (inter.length>0){
						for(int k =0;k<inter.length;k++){
							for(int j =0;j<li.size();j++){
								if(li.get(j).getNom().equals(Lecture.getTermeC(inter[k].getName()).trim())){
									li.get(i).addImp(li.get(j));
									System.out.println(li.get(j).toString());
								}
							}
						}
					}
				}

			}
		}
		return li;
	}

	public static void main(String[] args) {
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("Veuillez rentrer le chemin du dossier o˘ sont ranger les fichers java ‡ transformer en UML \n");
		String chaine= scanner.nextLine();
		scanner.close();
		List<String> chemins= scanDoss(chaine);
		List<Classe> lClass=new LinkedList<Classe>();
		for(int i=0;i<chemins.size();i++) {
			lClass.add(Application.scan(chemins.get(i).toString()));
		}
		
		
	//test pour l'affichage
		System.out.println("-----------------Test avec de vraies classes---------------------");
		groupe gr = new groupe(1,20);
		Class pr = new Enseignant("Jean-Fran√ßois Remm").getClass();
		Class ma =new Matiere("Programmation objet 2",18).getClass();
		Class et =new Etudiant("De Lapatefeuillet√©","Hubert","e185475j",gr).getClass();
		Class de = MatiereI.class;
		Class s = Personne.class;
		Class iut = IUT.class;
		Class fi = Fichier.class;
		Class dos = Dossier.class;
		Class elem = Element.class;
		
		List<Class> cla = new LinkedList<>();
		Class gro= gr.getClass();

		cla.add(pr);
		cla.add(ma);
		cla.add(et);
		cla.add(de);
		cla.add(gro);
		cla.add(s);
		cla.add(iut);
		cla.add(fi);
		cla.add(dos);
		cla.add(elem);

		List<Classe> li = new LinkedList<>();
		for (int i=0;i<cla.size();i++){
			li.add(new Classe(cla.get(i)));
		}
		for (Classe classe : li) {
			classe.trouverLien(li);
		}
		li = chercherExtend(li,cla);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le nom du fichier ");//lecture pour determiner
		String str = sc.nextLine();
		sc.close();
		Application.fichier(li, str);//appelle de la mÔøΩthode qui gÔøΩnÔøΩre un fichier qui contient le plantuml des classes donnÔøΩes en paramÔøΩtre
		//Application.UML(li, "test-fusion-1");
	}
	
	public static List<String> scanDoss(String c) {
		List<String> chemins=new LinkedList<>();
		
			File f=new File(c);
			File[] fs=f.listFiles();
			File[] fss;
			for(int i=0;i<fs.length;i++) {
				System.out.println(fs[i].toString());
				if(!fs[i].toString().contains(".")) {
					fss=fs[i].listFiles();
					for(int j=0;j<fss.length;j++) {
						System.out.println(fss[j].toString());
						if(fss[j].toString().contains(".java")) {
							chemins.add(fss[j].toString());
						}else if(fss[j].toString().contains(".txt")) {
							chemins.add(fss[j].toString());
						}
					}
				}else if(fs[i].toString().contains(".java")) {
					chemins.add(fs[i].toString());
				}else if(fs[i].toString().contains(".txt")) {
					chemins.add(fs[i].toString());
				}
			}
		
		return chemins;
		
	}


	public static Classe scan(String chemin) {
		Scanner sc;
		try {
			sc = new Scanner(new File(chemin));
			String s;
			String imple=" ",thro= " ",extend = "";
			List<String> impleList=new LinkedList<>();
			List<String> throList=new LinkedList<>();
			String[] thT,implT;
			String nomC="";
			List<String> methodes= new LinkedList<>();
			List<String> attributs= new LinkedList<>();
			boolean implement=false,abstrait=false,etend=false, Interface=false;
			String pack=" ";
			
			/*Scan du fichier*/
			while(sc.hasNextLine()) {
				s=sc.nextLine();
				s=s.trim();
			if(s.contains("interface")) {
				Interface=true;
			}
			if(s.contains("abstract")) {
				abstrait=true;
			}
			if(s.contains("public")) {
				if(s.contains("static")) {
					
				}
				
				/*RÈcupÈration de la ligne public class ...*/
				if(s.contains("class")||s.contains("interface")) {
					
					/*Gerer les exceptions de la classe*/
					if(s.contains("throws")) {
						thro=s.substring(s.indexOf("throws")+6);
						if(thro.contains("{")) {
							thro=thro.substring(0,thro.indexOf("{"));
						}
						thro=thro.trim();
						if(thro.contains(",")) {
							thT=thro.split(",");
							for(int i=0;i<thT.length;i++) {
								throList.add(thT[i]);
							}
						}
					}
					/*Gerer les implÈmentation de la classe*/
					if(s.contains("implements")) {
						implement=true;
						imple=s.substring(s.indexOf("implements")+10);
						if(imple.contains(thro) && !thro.equals(" ")) {
							imple=imple.substring(0,imple.indexOf(thro)-7);
						}
						if(imple.contains("{")) {
							imple=imple.substring(0,imple.indexOf("{"));
						}
						imple=imple.trim();
						if(imple.contains(",")) {
							implT=imple.split(",");
							for(int i=0;i<implT.length;i++) {
								impleList.add(implT[i]);
							}
						}
					}
					/*GÈrer l'hÈritage de la classe*/
					if(s.contains("extends")) {
						etend=true;
						extend=s.substring(s.indexOf("extends")+7);
						extend=extend.trim();
						if(extend.contains(thro)) {
							extend=extend.substring(0,extend.indexOf(thro)-7);
						}
						if(extend.contains(imple)) {
							extend=extend.substring(0,extend.indexOf(imple)-11);
						}
						if(extend.contains("{")) {
							extend=extend.substring(0,extend.indexOf("{"));
						}
					}
					/*RÈcupÈration du nom de la classe*/
					if(extend!=" ") {
						nomC=s.substring(s.indexOf("class")+5, s.indexOf(extend)-8);
					}else if(imple!=" ") {
						nomC=s.substring(s.indexOf("class")+5, s.indexOf(imple)-11);
					}else if(thro!=" ") {
						nomC=s.substring(s.indexOf("class")+5, s.indexOf(thro)-7);
					}else {
						nomC=s.substring(s.indexOf("class")+5);
					}
					if(nomC.contains("{")) {
						nomC=nomC.substring(0,nomC.indexOf("{"));
					}
					System.out.println(nomC);
					
				}else {
					/*RÈcupÈration des mÈthodes ou attributs*/
					if(s.contains("{")) {
						methodes.add(s.substring(0,s.indexOf("{")).trim());
					}else {
						if(s.contains(";")) {
							attributs.add(s.substring(0,s.indexOf(";")).trim());
						}
					}
				}
				
				
			}else if(s.contains("private")) {
				/*RÈcupÈration de la ligne public class ...*/
				if(s.contains("class")||s.contains("interface")) {
					
					/*Gerer les exceptions de la classe*/
					if(s.contains("throws")) {
						thro=s.substring(s.indexOf("throws")+6);
						if(thro.contains("{")) {
							thro=thro.substring(0,thro.indexOf("{"));
						}
						thro=thro.trim();
						if(thro.contains(",")) {
							thT=thro.split(",");
							for(int i=0;i<thT.length;i++) {
								throList.add(thT[i]);
							}
						}
					}
					/*Gerer les implÈmentation de la classe*/
					if(s.contains("implements")) {
						imple=s.substring(s.indexOf("implements")+10);
						if(imple.contains(thro)) {
							imple=imple.substring(0,imple.indexOf(thro)-7);
						}
						if(imple.contains("{")) {
							imple=imple.substring(0,imple.indexOf("{"));
						}
						imple=imple.trim();
						if(imple.contains(",")) {
							implT=imple.split(",");
							for(int i=0;i<implT.length;i++) {
								impleList.add(implT[i]);
							}
						}
					}
					/*GÈrer l'hÈritage de la classe*/
					if(s.contains("extends")) {
						extend=s.substring(s.indexOf("extends")+7);
						extend=extend.trim();
						if(extend.contains(thro)) {
							extend=extend.substring(0,extend.indexOf(thro)-7);
						}
						if(extend.contains(imple)) {
							extend=extend.substring(0,extend.indexOf(imple)-11);
						}
						if(extend.contains("{")) {
							extend=extend.substring(0,extend.indexOf("{"));
						}
					}
					/*RÈcupÈration du nom de la classe*/
					if(extend!=" ") {
						nomC=s.substring(s.indexOf("class")+5, s.indexOf(extend)-8);
					}else if(imple!=" ") {
						nomC=s.substring(s.indexOf("class")+5, s.indexOf(imple)-11);
					}else if(thro!=" ") {
						nomC=s.substring(s.indexOf("class")+5, s.indexOf(thro)-7);
					}else {
						nomC=s.substring(s.indexOf("class")+5);
					}
					if(nomC.contains("{")) {
						nomC=nomC.substring(0,nomC.indexOf("{"));
					}
					System.out.println(nomC);
					
				}else {
					/*RÈcupÈration des mÈthodes ou attributs*/
					if(s.contains("{")) {
						methodes.add(s.substring(0,s.indexOf("{")).trim());
					}else {
						if(s.contains(";")) {
							attributs.add(s.substring(0,s.indexOf(";")).trim());
						}
					}
				}
			}else {
				if(s.contains("package")) {
					pack=s.substring(s.indexOf("package")+7);
				}
				
			}
		
				
			
			/*Organisations des attributs*/
			List<Argument> att=new LinkedList<>();
			String[] tmp;
			for(int i=0;i<attributs.size();i++) {
				tmp=attributs.get(i).split(" ");
				if(tmp[2].contains(";")) {
					att.add(new Argument(tmp[2].substring(0, tmp[2].indexOf(";")),tmp[1],tmp[0]));
				}else {
					att.add(new Argument(tmp[2],tmp[1],tmp[0]));
				}
					
				
			}
			/*Tri des mÈthodes*/
			List<Methode> meth = new LinkedList<>();
			List<Parametre> param = new LinkedList<>();
			String tmpA;
			String tmpB;
			String tmp2;
			String[] tP;
			String exception;
			for(int i=0;i<methodes.size();i++) {
				
				tmp=methodes.get(i).split(" ");
				
				tmp2=" ";
				/* tri de la string / sÈparation des chaÓnes (espaces rajoutÈ ‡ certains endroits et parenthËse enlevÈ)*/
				for(int j=0;j<tmp.length;j++) {
					tmpB=null;
					tmpA=null;
					
					
					if(tmp[j].contains("(")) {
						tmpA=tmp[j].substring(0, tmp[j].indexOf("("));
						tmpB=tmp[j].substring(tmp[j].indexOf("(")+1);
						if(tmp[j].contains(")")){
							tmpB=null;
							
						}
					}else if(tmp[j].contains(")")){
						tmpA=tmp[j].substring(0, tmp[j].indexOf(")"));
						
					}
					
					
					if(tmpB!=null) {
						tmp2+= tmpA + " "+tmpB+" ";
						
					}else {
						if(tmpA!=null) {
							tmp2+= tmpA+" ";
						}else {
							tmp2+= tmp[j]+" ";
						}
					}
					
				}
				/*SÈparation des exceptions si il y en a*/
				exception="";
				if(tmp2.contains("throws")) {
					tP=tmp2.split(" ");
					int j=0;
					tmp2="";
					while(!tP[j].equals("throws")) {
						tmp2+=tP[j]+" ";
						j++;
					}
					j++;
					for(int k=j;k<tP.length;k++) {
						exception+=tP[k]+" ";
					}
					
				}
				
				
				
				
				/* CrÈation du tableau de parametre */
				if(tmp2.contains("abstract")) {
					tP=tmp2.split(" ");
					
					if(tP.length>5) {
						for(int j=4;j<tP.length;j+=2) {
							param.add(new Parametre(tP[j],tP[j+1]));
						}
					}
					if(tP[2].equals(nomC)) {
						if(param.isEmpty()) {
							if(!exception.equals("")) {
								meth.add(new Methode("",tP[2],tP[1],exception.split(" ")));
							}else {
								meth.add(new Methode("",tP[2],tP[1]));
							}
						}else {
							if(!exception.equals("")) {
								meth.add(new Methode("",tP[2],param,tP[1],exception.split(" ")));
							}else {
								meth.add(new Methode("",tP[2],param,tP[1]));
							}
						}
					}else {
						if(param.isEmpty()) {
							if(!exception.equals("")) {
								meth.add(new Methode(tP[2],tP[3],tP[1],exception.split(" ")));
							}else {
								meth.add(new Methode(tP[2],tP[3],tP[1]));
							}
						}else {
							if(!exception.equals("")) {
								meth.add(new Methode(tP[2],tP[3],param,tP[1],exception.split(" ")));
							}else {
								meth.add(new Methode(tP[2],tP[3],param,tP[1]));
							}
						}
					}
					
				}else {
					tP=tmp2.split(" ");
					
					if(tP.length>4) {
						for(int j=3;j<tP.length-1;j+=2) {
							param.add(new Parametre(tP[j],tP[j+1]));
						}
					}
					if(tP[2].equals(nomC)) {
						if(param.isEmpty()) {
							if(!exception.equals("")) {
								meth.add(new Methode("",tP[2],tP[1],exception.split(" ")));
							}else {
								meth.add(new Methode("",tP[2],tP[1]));
							}
						}else {
							if(!exception.equals("")) {
								meth.add(new Methode("",tP[2],param,tP[1],exception.split(" ")));
							}else {
								meth.add(new Methode("",tP[2],param,tP[1]));
							}
						}
					}else {
						if(param.isEmpty()) {
							if(!exception.equals("")) {
								meth.add(new Methode(tP[2],tP[3],tP[1],exception.split(" ")));
							}else {
								meth.add(new Methode(tP[2],tP[3],tP[1]));
							}
						}else {
							if(!exception.equals("")) {
								meth.add(new Methode(tP[2],tP[3],param,tP[1],exception.split(" ")));
							}else {
								meth.add(new Methode(tP[2],tP[3],param,tP[1]));
							}
						}
					}
				}
				param.clear();
			}
			
			sc.close();
			Classe cl=new Classe(nomC,meth,att,etend,implement,pack,Interface,abstrait);
			return cl;
		}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
		return null;
		
	}


}
