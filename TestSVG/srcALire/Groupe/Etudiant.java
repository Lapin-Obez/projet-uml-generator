package srcALire.Groupe;

public class Etudiant extends Personne{
    
    private String numEtudiant;
    private groupe gr;
    
    public Etudiant(String n, String p, String num,groupe gr){
        super(n,p);
        this.numEtudiant = num;
        this.gr = gr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public groupe getGr() {
        return gr;
    }

    public void setGr(groupe gr) {
        this.gr = gr;
    }
}