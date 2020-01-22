package srcALire.Groupe;

public class groupe {
    private int numero;
    private Etudiant []etu;
    private int nbEtudiant;
    public groupe(int a,int b){
        this.numero = a;
        this.etu=new Etudiant[b];
        this.nbEtudiant=0;
    }
    public void ajoutEtu(Etudiant e){
        etu[nbEtudiant]=e;
        nbEtudiant++;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}