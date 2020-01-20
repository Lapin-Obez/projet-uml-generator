package srcALire;
import exceptions.DernierMotRechercheException;
import exceptions.DicoVideException;
import exceptions.MotDejaPresentException;
import exceptions.MotNonPresentException;

import java.util.Iterator;


/**
 * Interface iDico : interface qui représente un dictionnaire de mots.
 *
 * @author Solen Quiniou & Arnaud Lanoix
 * @version 30-09-2019
 */
public interface iDico extends Iterable<String> {

    /**
     * Méthode qui teste si le dictionnaire est vide.
     *
     * @return vrai si le dictionnaire est vide ; faux sinon.
     */
    boolean dicoVide();

    /**
     * Méthode qui teste si un mot appartient au dictionnaire.
     *
     * @param mot le mot considéré.
     * @return vrai si le mot est présent dans le dictionnaire ; faux sinon.
     */
    boolean appartient(String mot);

    /**
     * Méthode qui ajoute un mot dans le dictionnaire, s'il n'est pas déjà présent.
     *
     * @param mot le mot à ajouter au dictionnaire.
     * @throws MotDejaPresentException si le mot est déjà présent dans le dictionnaire
     */
    void ajoutMot(String mot) throws MotDejaPresentException;


    /**
     * Méthode qui retourne le nombre de mots du dictionnaire.
     *
     * @return le nombre de mots présents dans le dictionnaire.
     */
    int nbMots();

    /**
     * Méthode qui retourne le premier mot du dictionnaire considéré, selon l'ordre alphabétique.
     *
     * @return le premier mot du dictionnaire.
     * @throws DicoVideException si le dictionnaire est vide
     */
    String premierMot() throws DicoVideException;


    /**
     * Méthode qui retourne le dernier mot du dictionnaire considéré, selon l'ordre alphabétique.
     *
     * @return le dernier mot du dictionnaire.
     * @throws DicoVideException si le dictionnaire est vide
     */
    String dernierMot() throws DicoVideException;


    /**
     * Méthode qui retourne le mot suivant le mot donné, dans le dictionnaire considéré, selon l'ordre alphabétique.
     *
     * @param mot le mot considéré.
     * @return le mot suivant, dans le dictionnaire.
     * @throws DernierMotRechercheException si le mot donné est le dernier mot du dictionnaire
     * @throws MotNonPresentException       si le mot donné n'est pas dans le dictionnaire
     */
    String motSuivant(String mot) throws DernierMotRechercheException, MotNonPresentException;


    /**
     * Méthode qui supprime le mot considéré du dictionnaire.
     *
     * @param mot le mot à supprimer du dictionnaire.
     * @throws MotNonPresentException si le mot donné n'est pas dans le dictionnaire
     */
    void suppressionMot(String mot) throws MotNonPresentException;

    /**
     * Méthode qui retourne un itérateur sur le dictionnaire.
     *
     * @return l'itérateur sur la liste des mots du dictionnaire
     * (dans l'ordre lexicographique).
     */
    @Override
    Iterator<String> iterator();
}
