package collections;

import collections.exceptions.ArbreVideException;
import collections.exceptions.PasDePrecedentException;
import collections.exceptions.PasDeSuivantException;
import collections.exceptions.ValeurNonTrouveeException;

import java.util.*;

/**
 * Classe ABRA : classe générique qui implémente
 * un arbre binaire de recherche en utilisant uniquement
 * des sous-arbres
 * <p>
 * une grande partie des algorithmes sont implémentés
 * itérativement, et non récursivement.
 *
 * @author Arnaud Lanoix
 * @version 2.0
 */

public class ABRA<V extends Comparable<? super V>> implements iABR<V> {

    private V valeur;
    private ABRA<V> arbreG;
    private ABRA<V> arbreD;

    /**
     * Constructeur de ABR.
     */
    public ABRA() {
        this(null);
    }

    private ABRA(V val) {
        valeur = val;
        arbreG = null;
        arbreD = null;
    }

    @Override
    public String toString() {
        if (estVide())
            return "arbre vide";
        return this.construitString("", true);
    }

    private String construitString(String prefixe, boolean dernier) {
        String str = prefixe + (dernier ? "└── " : "├── ") + valeur.toString() + "\n";
        if (arbreG != null) {
            str += arbreG.construitString(prefixe + (dernier ? "    " : "│   "), (arbreD == null ? true : false));
        }
        if (arbreD != null) {
            str += arbreD.construitString(prefixe + (dernier ? "    " : "│   "), true);
        }
        return str;
    }

    @Override
    public boolean estVide() {
        return (valeur == null);
    }

    @Override
    public boolean ajouter(V valeurAjoutee) {
        if (estVide()) {
            this.valeur = valeurAjoutee;
            return true;
        }
        ABRA<V> arbreCourant = this;

        int comparaison = valeurAjoutee.compareTo(arbreCourant.valeur);
        while (comparaison != 0) {
            if (comparaison < 0) {
                if (arbreCourant.arbreG != null)
                    arbreCourant = arbreCourant.arbreG;
                else {
                    arbreCourant.arbreG = new ABRA<>(valeurAjoutee);
                    return true;
                }
            } else if (comparaison > 0) {
                if (arbreCourant.arbreD != null)
                    arbreCourant = arbreCourant.arbreD;
                else {
                    arbreCourant.arbreD = new ABRA<>(valeurAjoutee);
                    return true;
                }
            }
            comparaison = valeurAjoutee.compareTo(arbreCourant.valeur);
        }
        return false;
    }

    public boolean contient_iteratif(V valeurRecherchee) {
        return contient(valeurRecherchee);
    }

    @Override
    public boolean contient(V valeurRecherchee) {
        if (estVide())
            return false;
        ABRA<V> arbreCourant = this;
        while (arbreCourant != null) {
            int comparaison = valeurRecherchee.compareTo(arbreCourant.valeur);
            if (comparaison == 0)
                return true;
            else if (comparaison < 0)
                arbreCourant = arbreCourant.arbreG;
            else if (comparaison > 0)
                arbreCourant = arbreCourant.arbreD;
        }
        return false;
    }


    @Override
    public int hauteur() {
        if (estVide())
            return 0;
        int hauteurG = 0;
        int hauteurD = 0;
        if (arbreG != null)
            hauteurG = arbreG.hauteur();
        if (arbreD != null)
            hauteurD = arbreD.hauteur();
        return Math.max(hauteurG, hauteurD) + 1;
    }

    @Override
    public int taille_iteratif() {
        return taille();
    }

    @Override
    public int taille() {
        if (estVide())
            return 0;
        int compteur = 0;
        Queue<ABRA<V>> file = new LinkedList<>();
        file.offer(this);
        while (!file.isEmpty()) {
            ABRA<V> arbreCourant = file.poll();
            if (arbreCourant.arbreG != null)
                file.offer(arbreCourant.arbreG);
            if (arbreCourant.arbreD != null)
                file.offer(arbreCourant.arbreD);
            compteur++;
        }
        return compteur;
    }

    @Override
    public int nbFeuilles_iteratif() {
        return nbFeuilles();
    }

    @Override
    public int nbFeuilles() {
        if (estVide())
            return 0;
        int compteur_feuille = 0;
        Deque<ABRA<V>> pile = new LinkedList<>();
        pile.push(this);
        while (!pile.isEmpty()) {
            ABRA<V> arbreCourant = pile.pop();
            if (arbreCourant.arbreG != null)
                pile.push(arbreCourant.arbreG);
            if (arbreCourant.arbreD != null)
                pile.push(arbreCourant.arbreD);
            if (arbreCourant.arbreG == null && arbreCourant.arbreD == null)
                compteur_feuille++;
        }
        return compteur_feuille;
    }

    @Override
    public Iterator<V> iterator() throws ArbreVideException {
        if (!estVide()) {
            return new Iterator<V>() {
                Deque<ABRA<V>> pile;

                // constructeur anonyme
                {
                    pile = new ArrayDeque<>();
                    ABRA<V> courant = ABRA.this;
                    while (courant != null) {
                        pile.push(courant);
                        courant = courant.arbreG;
                    }
                }

                @Override
                public boolean hasNext() {
                    return (!pile.isEmpty());
                }

                @Override
                public V next() {
                    ABRA<V> courant = pile.pop();
                    V resultat = courant.valeur;
                    if (courant.arbreD != null) {
                        courant = courant.arbreD;
                        while (courant != null) {
                            pile.push(courant);
                            courant = courant.arbreG;
                        }
                    }
                    return resultat;
                }
            };
        } else
            throw new ArbreVideException("Arbre vide");
    }

    @Override
    public Iterator<V> iterator_prefixe() throws ArbreVideException {
        if (!estVide()) {
            return new Iterator<V>() {
                Deque<ABRA<V>> pile;

                {
                    pile = new ArrayDeque<>();
                    pile.push(ABRA.this);
                }

                @Override
                public boolean hasNext() {
                    return (!pile.isEmpty());
                }

                @Override
                public V next() {
                    ABRA<V> courant = pile.pop();

                    if (courant.arbreD != null)
                        pile.push(courant.arbreD);
                    if (courant.arbreG != null)
                        pile.push(courant.arbreG);
                    return courant.valeur;
                }
            };
        } else
            throw new ArbreVideException("Arbre vide");
    }

    @Override
    public Iterator<V> iterator_postfixe() throws ArbreVideException {
        if (estVide())
            throw new ArbreVideException("Arbre vide");
        return new Iterator<V>() {
            Iterator<ABRA<V>> ite = iterator_arbre_postfixe();
            @Override
            public boolean hasNext() {
                return ite.hasNext();
            }
            @Override
            public V next() {
                return ite.next().valeur;
            }
        };
    }

    private Iterator<ABRA<V>> iterator_arbre_postfixe() throws ArbreVideException {
        if (estVide())
            throw new ArbreVideException("Arbre vide");
        return new Iterator<ABRA<V>>() {
            Deque<ABRA<V>> pile;

            {
                pile = new ArrayDeque<>();
                empilerNoeuds(ABRA.this);
            }

            private void empilerNoeuds(ABRA<V> arbre) {
                ABRA<V> courant = arbre;
                while (courant != null) {
                    pile.push(courant);
                    if (courant.arbreG != null)
                        courant = courant.arbreG;
                    else
                        courant = courant.arbreD;
                }
            }

            @Override
            public boolean hasNext() {
                return (!pile.isEmpty());
            }

            @Override
            public ABRA<V> next() {
                ABRA<V> courant = pile.pop();
                if (!pile.isEmpty()) {
                    ABRA<V> suivant = pile.peek();
                    if (suivant.arbreG == courant)
                        empilerNoeuds(suivant.arbreD);
                }
                return courant;
            }
        };

    }

    @Override
    public Iterator<V> iteratorEnLargeur() throws ArbreVideException {
        if (!estVide()) {
            return new Iterator<V>() {
                Queue<ABRA<V>> file;

                {
                    file = new LinkedList<>();
                    file.offer(ABRA.this);
                }

                @Override
                public boolean hasNext() {
                    return (!file.isEmpty());
                }

                @Override
                public V next() {
                    ABRA<V> arbre = file.poll();
                    if (arbre.arbreG != null)
                        file.offer(arbre.arbreG);
                    if (arbre.arbreD != null)
                        file.offer(arbre.arbreD);
                    return arbre.valeur;
                }
            };
        } else
            throw new ArbreVideException("Arbre vide");
    }

    @Override
    public List<V> parcoursInfixe() {
        List<V> liste = new LinkedList<V>();
        Iterator<V> ite = this.iterator();
        while (ite.hasNext()) {
            liste.add(ite.next());
        }
        return liste;
    }

    @Override
    public List<V> parcoursPrefixe() {
        List<V> liste = new LinkedList<V>();
        Iterator<V> ite = this.iterator_prefixe();
        while (ite.hasNext()) {
            liste.add(ite.next());
        }
        return liste;
    }

    @Override
    public List<V> parcoursPostfixe() {
        List<V> liste = new LinkedList<V>();
        Iterator<V> ite = this.iterator_postfixe();
        while (ite.hasNext()) {
            liste.add(ite.next());
        }
        return liste;
    }


    // TODO 3ième partie


    public V plusPetiteValeur() throws ArbreVideException {
        if (estVide())
            throw new ArbreVideException("Arbre vide");

        ABRA<V> arbre = this;
        while (arbre.arbreG != null) {
            arbre = arbre.arbreG;
        }
        return arbre.valeur;
    }

    @Override
    public V plusGrandeValeur() throws ArbreVideException {
        if (estVide())
            throw new ArbreVideException("Arbre vide");

        ABRA<V> arbre = this;
        while (arbre.arbreD != null) {
            arbre = arbre.arbreD;
        }
        return arbre.valeur;
    }

    public V valeurSuivante(V valeurRecherchee) throws ArbreVideException, PasDeSuivantException, ValeurNonTrouveeException {
        if (estVide())
            throw new ArbreVideException("Arbre vide");

        ABRA<V> courant = this;
        ABRA<V> suivantPossible = null;
        while (courant != null) {
            int comparaison = valeurRecherchee.compareTo(courant.valeur);
            if (comparaison == 0) {
                // on a trouve la valeur
                if (courant.arbreD != null)
                    return courant.arbreD.plusPetiteValeur();
                if (suivantPossible != null)
                    return suivantPossible.valeur;
                throw new PasDeSuivantException("derniere valeur recherchée");
            } else if (comparaison < 0) {
                suivantPossible = courant;
                courant = courant.arbreG;
            } else if (comparaison > 0) {
                courant = courant.arbreD;
            }
        }
        throw new ValeurNonTrouveeException("valeur non trouvée, pas de suivant");
    }


    @Override
    public V valeurPrecedente(V valeurRecherchee) throws ArbreVideException, PasDePrecedentException, ValeurNonTrouveeException {
        // TODO
        return null;
    }

    public void supprimer(V valeurSupprimee) throws ValeurNonTrouveeException, ArbreVideException {
        if (estVide())
            throw new ArbreVideException("Arbre vide");
        ABRA<V> courant = this;
        ABRA<V> pereGauche = null;
        ABRA<V> pereDroit = null;
        while (courant != null) {
            int comparaison = valeurSupprimee.compareTo(courant.valeur);
            if (comparaison < 0) {
                pereGauche = courant;
                pereDroit = null;
                courant = courant.arbreG;
            } else if (comparaison > 0) {
                pereGauche = null;
                pereDroit = courant;
                courant = courant.arbreD;
            }
            else { // (comparaison == 0)
                // on a trouve le noeud portant la  valeur a supprimer
                if (courant.arbreG == null && courant.arbreD == null) {
                    // on est sur une feuille
                    if (pereGauche == null && pereDroit == null)  // on a pas de pere
                        courant.valeur = null;
                    else if (pereGauche != null) // on est a la gauche du pere
                        pereGauche.arbreG = null;
                    else // on est à la droite du pere
                        pereDroit.arbreD = null;
                } else if ((courant.arbreG != null && courant.arbreD == null)
                        || (courant.arbreG == null && courant.arbreD != null)) {
                    // on a un seul fils, on remplace
                    ABRA<V> fils = (courant.arbreG != null) ? courant.arbreG : courant.arbreD;
                    courant.valeur = fils.valeur;
                    courant.arbreG = fils.arbreG;
                    courant.arbreD = fils.arbreD;
                } else {
                    // on a deux fils : on cherche la valeur suivante de celle à supprimer,
                    // on remplace, et on relance la suppression
                    V valeurSuivante = courant.valeurSuivante(valeurSupprimee);
                    courant.supprimer(valeurSuivante);
                    courant.valeur = valeurSuivante;
                }
                break;
            }
        }
        if (courant == null)
            throw new ValeurNonTrouveeException("valeur a supprimer non trouvee");
    }
}
