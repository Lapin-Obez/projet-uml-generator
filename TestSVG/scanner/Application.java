package scanner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        
        Application.UML(li, "test-fusion");
    }


}
