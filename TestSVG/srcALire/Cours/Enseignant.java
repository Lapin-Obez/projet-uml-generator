package srcALire.Cours;

import java.util.LinkedList;
import java.util.List;

public class Enseignant {
    private List<Matiere> enseignement;
    private String nom;
    public Enseignant(String n){
        this.nom = n;
        this.enseignement = new LinkedList<>();
    }

    public void ajoutMatiere( Matiere m){
        this.enseignement.add(m);
    }
    public void retirerMatiere(Matiere m){
        if(this.enseignement.contains(m)){
            this.enseignement.remove(m);
        }
    }
}