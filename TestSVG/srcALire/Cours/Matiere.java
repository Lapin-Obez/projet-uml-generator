package srcALire.Cours;

public class Matiere implements MatiereI{
    private String sujet;
    private int nbHeure;
    public Enseignant profReferent;

    public Matiere(String s,int nb){
        this.nbHeure = nb;
        this.sujet = s;
    }
    public void ajoutProf(Enseignant e){
        this.profReferent= e;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getNbHeure() {
        return nbHeure;
    }

    public void setNbHeure(int nbHeure) {
        this.nbHeure = nbHeure;
    }
}