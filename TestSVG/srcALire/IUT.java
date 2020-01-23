package srcALire;

import srcALire.Cours.Enseignant;
import srcALire.Cours.Matiere;
import srcALire.Groupe.Etudiant;
import srcALire.Groupe.groupe;

public class IUT {

    int nbGroupes;
    groupe[] groupes;
    int nbEtudiant;
    Etudiant[] etudiants;
    int nbProfesseurs;
    Enseignant[] professeurs;
    int nbmatieres;
    Matiere[] matieres;
    public IUT(int a,int b,int c, int d){
        this.nbGroupes =a;
        this.groupes=new groupe[this.nbGroupes];
        this.nbEtudiant =b;
        this.etudiants=new Etudiant[this.nbEtudiant];
        this.nbProfesseurs = c;
        this.professeurs = new Enseignant[this.nbProfesseurs];
        this.nbmatieres = d;
        this.matieres = new Matiere[this.nbmatieres];
    }
}