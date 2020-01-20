package collections;

import collections.exceptions.ArbreVideException;
import collections.exceptions.PasDePrecedentException;
import collections.exceptions.PasDeSuivantException;
import collections.exceptions.ValeurNonTrouveeException;

import java.util.Iterator;
import java.util.List;

/**
 * Interface iABR : classe générique qui représente un arbre binaire de recherche.
 *
 * @author Solen Quiniou et Arnaud Lanoix
 * @version 2.0
 *
 */

public interface iABR<V extends Comparable<? super V>> extends Iterable<V> {


    /**
     * Méthode donnant une représentation de l'ABR sous la forme d'une chaîne de caractères
     * @return une chaîne de caractères représentant l'iABR
     */
    @Override
    String toString();

    // TODO : 1ere partie : méthodes de base

    /**
     * Méthode qui teste si l'ABR est vide.
     * @return vrai si l'ABR est vide ; faux sinon.
     */
    boolean estVide();

    /**
     * Méthode RECURSIVE qui ajoute un noeud dans l'ABR,
     * si la valeur associée n'est pas déjà présente.
     * @param valeurAjoutee la valeur à ajouter à l'ABR.
     * @return vrai si la valeur a été ajoutée ; faux sinon (si la valeur était déjà présente).
     */
    boolean ajouter(V valeurAjoutee);

    /**
     * Méthode qui teste si une valeur appartient à l'ABR.
     * @param valeurRecherchee la valeur considérée.
     * @return vrai si la valeur est présente dans l'ABR ; faux sinon.
     */
    boolean contient(V valeurRecherchee);

    /**
     * Méthode RECURSIVE qui retourne le nombre d'elements contenus dans l'ABR.
     * @return le nombre d'éléments contenus dans l'ABR
     */
    int taille();

    /**
     * Méthode RECURSIVE qui retourne la hauteur de l'ABR.
     * @return la hauteur de l'ABR
     */
    int hauteur();

    /**
     * Méthode RECURSIVE qui retourne le nombre de feuilles de l'ABR.
     * @return le nombre de feuilles de l'ABR ; 0 si l'arbre est vide
     */
    int nbFeuilles();


    // TODO : 2nde partie : les différents parcours en profondeur

    /**
     * Methode qui retourne la liste des valeurs contenues dans l'ABR dans l'ordre infixé
     * @return la liste des valeurs dans l'ordre infixé
     */
    List<V> parcoursInfixe();

    /**
     * Methode qui retourne la liste des valeurs contenues dans l'ABR dans l'ordre prefixé
     * @return la liste des valeurs dans l'ordre préfixé
     */
    List<V> parcoursPrefixe();

    /**
     * Methode qui retourne la liste des valeurs contenues dans l'ABR dans l'ordre postfixé
     * @return la liste des valeurs dans l'ordre postfixé
     */
    List<V> parcoursPostfixe();


    // TODO : 3ième partie : des méthodes plus spécifiques

    /**
     * retourne la plus petite valeur contenue de l'ABR
     * @return la plus petite valeur, au sens de la comparaison du type V.
     * @throws ArbreVideException si l'ABR considéré est vide
     */
    V plusPetiteValeur() throws ArbreVideException;

    /**
     * retourne la plus grande valeur contenue dans l'ABR
     * @return la plus grande valeur, au sens de la comparaison du type V.
     * @throws ArbreVideException si l'ABR considéré est vide
     */
    V plusGrandeValeur() throws ArbreVideException;

    /**
     * retourne la valeur suivant celle recherchée contenue dans l'ABR
     * @param valeurRecherchee la valeur recherchée
     * @return la valeur suivant celle recherchée
     * @throws ArbreVideException si l'ABR est vide
     * @throws PasDeSuivantException s'il n'y a pas de suivant
     * @throws ValeurNonTrouveeException si la valeur recherchée n'est pas trouvée
     */
    V valeurSuivante(V valeurRecherchee)
            throws ArbreVideException, PasDeSuivantException, ValeurNonTrouveeException;

    /**
     * retourne la valeur suivant celle recherchée contenue dans l'ABR
     * @param valeurRecherchee la valeur recherchée
     * @return la valeur suivant celle recherchée
     * @throws ArbreVideException si l'ABR est vide
     * @throws PasDePrecedentException s'il n'y a pas de suivant
     * @throws ValeurNonTrouveeException si la valeur recherchée n'est pas trouvée
     */
    V valeurPrecedente(V valeurRecherchee)
            throws ArbreVideException, PasDePrecedentException, ValeurNonTrouveeException;

    /**
     * supprimer une valeur dans l'ABR
     * @param valeurSupprimee la valeur à supprimer
     * @throws ValeurNonTrouveeException si la valeur n'est pas trouvée
     * @throws ArbreVideException si l'arbre est vide
     */
    void supprimer(V valeurSupprimee) throws ValeurNonTrouveeException, ArbreVideException;


    // TODO : 4ième partie : implémentations itératives

    /**
     * Méthode ITERATIVE qui teste si une valeur appartient à l'ABR.
     * @param valeurRecherchee la valeur considérée.
     * @return vrai si la valeur est présente dans l'ABR ; faux sinon.
     */
    boolean contient_iteratif(V valeurRecherchee);

    /**
     * Méthode ITERATIVE qui retourne le nombre d'elements contenus dans l'ABR.
     * @return le nombre d'éléments contenus dans l'ABR
     */
    int taille_iteratif();

    /**
     * Méthode ITERATIVE qui retourne le nombre de feuilles de l'ABR.
     * @return le nombre de feuilles de l'ABR ; 0 si l'arbre est vide
     */
    int nbFeuilles_iteratif();

    // TODO : 5ième partie : iterator sur un ABR

    /**
     * donne un iterateur réalisant un parcours (en profondeur) INFIXE de l'ABR
     * @return un iterateur
     * @throws ArbreVideException si l'iABR est vide
     */
    @Override
    Iterator<V> iterator() throws ArbreVideException;

    /**
     * donne un iterateur réalisant un parcours (en profondeur) PREFIXE sur l'ABR
     * @return un iterateur
     * @throws ArbreVideException si l'arbre est vide
     */
    Iterator<V>  iterator_prefixe()  throws ArbreVideException;

    /**
     * donne un iterateur réalisant un parcours (en profondeur) POSTFIXE sur l'ABR
     * @return un iterateur
     * @throws ArbreVideException si l'arbre est vide
     */
    Iterator<V>  iterator_postfixe()  throws ArbreVideException;

    /**
     * donne un iterateur réalisant un parcours en LARGEUR sur l'ABR
     * @return un iterateur
     * @throws ArbreVideException si l'arbre est vide
     */
    Iterator<V>  iteratorEnLargeur()  throws ArbreVideException;

}
