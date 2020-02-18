package scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import srcALire.*;
import srcALire.Cours.*;
import srcALire.Groupe.*;

public class Application {
	
	/**
	 * Fonction qui dessine un diagramme UML sur une image svg via les donnée passé dans la liste de classe
	 * @param l liste des classes à dessiner
	 * @param name nom du fichier .svg à générer
	 */
	public static void UML(List<Classe> l, String name) {
		 List<uml.Classe> listC = new ArrayList<>();
	        for (Classe classe : l) {//traduction des Classe en src.Classe pour générer l'image UML
	        	List<String> lmeth = new ArrayList<>();
				for (String s : classe.getMethodes()) {
					lmeth.add(s);
				}
				List<String> latt = new ArrayList<>();
				for (Argument att : classe.getAttributs()) {
					latt.add(att.toString());
				}
				uml.Classe c0 = new uml.Classe(classe.getNom(), latt, lmeth, classe.getPackage());
				listC.add(c0);
			}
	        for(Classe classe : l) {//génération des liens sur les classe src.Classe
	        	uml.Classe depart = null;
	        	for (uml.Classe classe2 : listC) {
					if(classe2.getName().equals(classe.getNom())) {
						depart = classe2;
					}
				}
	        	uml.Classe arriver = null;
	        	for (Lien lien : classe.getLiens()) {
	        		for (uml.Classe classe2 : listC) {
						if(classe2.getName().equals(lien.getLier().getNom())) {
							arriver = classe2;
						}
					}
	        	if(depart != null && arriver != null)
	        		depart.addLiaison(arriver);
				}
	        }
	     uml.svg.createUML(listC, name);//appel de la fonction pour créé l'UML
	}
	
	public static void fichier(List<Classe> l, String name) {
		List<Package> pack = new ArrayList<>();
		Map<String,Boolean> tab = new HashMap<>();
		for (Classe cl : l) {
			tab.put(cl.getNom(),false);
		}
        Application.triClasse(pack, l);
		try {
			Writer write = new FileWriter(name+".txt");
			write.write("@startuml\n");
			/* Exemple module
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
			for (Package paqu : pack) {
				write.write("package \""+paqu.getName()+"\"{\n");
				for (Classe classe : paqu.getList()) {
					write.write("class "+classe.getNom()+"{\n");
					for (Argument s : classe.getAttributs()) {
						write.write("{field} "+s.toString()+"\n");
					}
					for (String s : classe.getMethodes()) {
						write.write("{method} "+s.toString()+"\n");
					}
					write.write("}\n");
				}
				write.write("}\n");
			}
			for (Classe classe : l) {
				List<Lien> liens = classe.getLiens();
				for (Lien lien : liens) {
					if( !tab.get(lien.getLier().getNom()) ) {
					write.write("\n"+classe.getNom() + "\""+lien.getMultipliciteF()+"\" --- \""+lien.getMultipliciteD() +"\"" + lien.getLier().getNom()+"\n");
					}
				}
				tab.replace(classe.getNom(), true);
			}
			write.write("\n@enduml");
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
				if(!trv) {//sinon on créé le package 
					Package p = new Package(classe.getPackage());
					p.addClasse(classe);
					pack.add(p);
			}
			}else {//si elle est vide alors on créé le premier package
				Package p = new Package(classe.getPackage());
				p.addClasse(classe);
				pack.add(p);
			}
		}
	}

    public static void main(String[] args) {

        System.out.println("-----------------Test avec du vrai code---------------------");
        groupe gr = new groupe(1,20);
        Classe prof = new Classe(new Enseignant("Jean-François Remm").getClass());
        Classe matiere = new Classe(new Matiere("Programmation objet 2",18).getClass());
        Classe groupe = new Classe(gr.getClass());
        Classe etudiant = new Classe(new Etudiant("De Lapatefeuilleté","Hubert","e185475j",gr).getClass());
        Classe iut = new Classe(new IUT(8, 109, 30, 14).getClass());
        List<Classe> li = new LinkedList<>();
        li.add(prof);
        li.add(matiere);
        li.add(groupe);
        li.add(etudiant);
        li.add(iut);
//        System.out.println(prof.toString());
//        System.out.println(matiere.toString());
//        System.out.println(groupe.toString());
//        System.out.println(etudiant.toString());
        for(int i =0;i<li.size();i++){
            li.get(i).trouverLien(li);
        }
        Application.fichier(li, "Bonjour");
        //Application.UML(li, "test-fusion-1");
    }


}
