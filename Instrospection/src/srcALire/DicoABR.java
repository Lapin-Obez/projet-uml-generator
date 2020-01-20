package srcALire;
import collections.ABRA;
import collections.iABR;
import exceptions.DernierMotRechercheException;
import exceptions.DicoVideException;
import exceptions.MotDejaPresentException;
import exceptions.MotNonPresentException;

import java.util.Iterator;

/**
 * Implementation d'un dico en utilisant un arbre binaire de recherche
 * @author Arnaud Lanoix
 * @version
 */
public class DicoABR implements iDico {

    private iABR<String> arbre;
    private DicoHashSet dico [];

    public DicoABR() {
        arbre = new ABRA<>();
        // TODO
    }
    public DicoABR(int a) {
        arbre = new ABRA<>();

    }
    public DicoABR(String s) {
        arbre = new ABRA<>();
        // TODO
    }
    public DicoABR(int a,String s) {
        arbre = new ABRA<>();
        // TODO
    }

    @Override
    public boolean dicoVide() {
        // TODO
        return arbre.estVide();
    }

    @Override
    public boolean appartient(String mot) {
        // TODO
        return arbre.contient(mot);
    }

    @Override
    public void ajoutMot(String mot) throws MotDejaPresentException {
        // TODO
    	arbre.ajouter(mot);
    }

    @Override
    public int nbMots() {
        // TODO
        return arbre.taille();
    }

    @Override
    public String premierMot() throws DicoVideException {
        // TODO
        return arbre.plusPetiteValeur();
    }

    @Override
    public String dernierMot() throws DicoVideException {
        // TODO
        return arbre.plusGrandeValeur();
    }

    @Override
    public String motSuivant(String mot) throws DernierMotRechercheException, MotNonPresentException {
        // TODO
        return arbre.valeurSuivante(mot);
    }

    @Override
    public void suppressionMot(String mot) throws MotNonPresentException {
        arbre.supprimer(mot);
    }

    @Override
    public Iterator<String> iterator() {
        // TODO
        return arbre.iterator();
    }
}
