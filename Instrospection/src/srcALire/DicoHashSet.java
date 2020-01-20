package srcALire;
import exceptions.DernierMotRechercheException;
import exceptions.DicoVideException;
import exceptions.MotDejaPresentException;
import exceptions.MotNonPresentException;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Implémentation d'un dictionnaire en utilisant un ensemble (non trié)
 */

public class DicoHashSet implements iDico {

    private HashSet<String> set;
    private DicoABR dico;

    public DicoHashSet() {
        set = new HashSet<>();
        dico = new DicoABR();

    }

    @Override
    public boolean dicoVide() {
        return set.isEmpty();
    }

    @Override
    public boolean appartient(String mot) {
        
        return set.contains(mot);
    }

    @Override
    public void ajoutMot(String mot) throws MotDejaPresentException {
        if(this.appartient(mot)) {
        	throw new MotDejaPresentException();
        }else {
        	set.add(mot);
        }
    }

    @Override
    public int nbMots() {
        return set.size();
    }

    @Override
    public String premierMot() throws DicoVideException {
        if(dicoVide()) {
        	throw new DicoVideException();
        }else {
        	Iterator<String> tmp=set.iterator();
        	String s=tmp.next();
        	String suivant;
        	while(tmp.hasNext()) {
        		suivant=tmp.next();
        		if(suivant.compareToIgnoreCase(s)<0) {
        			s=suivant;
        		}
        	}     	
        	return s;
        }
        
    }

    @Override
    public String dernierMot() throws DicoVideException {
    	if(dicoVide()) {
        	throw new DicoVideException();
        }else {
        	Iterator<String> tmp=set.iterator();
        	String s=tmp.next();
        	String suivant;
        	while(tmp.hasNext()) {
            	suivant=tmp.next();
            	if(suivant.compareToIgnoreCase(s)>0) {
            		s=suivant;
            	}
        	}
        	
        	return s;
        }
    }

    @Override
    public String motSuivant(String mot) throws DernierMotRechercheException, MotNonPresentException {
        if(!this.appartient(mot)) {
        	throw new MotNonPresentException();
        }else if(this.dernierMot().equals(mot)) {
        	throw new DernierMotRechercheException();
        }else {
        	Iterator<String> tmp=set.iterator();
        	String suivant;
        	String actual=tmp.next();
        	while(tmp.hasNext()&& actual.compareToIgnoreCase(mot)<=0) {
        		actual=tmp.next();
        	}
        	while(tmp.hasNext()) {
        		suivant=tmp.next();
        		if(suivant.compareToIgnoreCase(actual)<0 && suivant.compareToIgnoreCase(mot)>0) {
        			actual=suivant;
        		}
        	}
        	return actual;
        }
    }

    @Override
    public void suppressionMot(String mot) throws MotNonPresentException {
        if(this.appartient(mot)) {
        	set.remove(mot);
        }else {
        	throw new MotNonPresentException();
        }

    }

    @Override
    public Iterator<String> iterator() {
    	Iterator<String> tmp=set.iterator();
    	String courant=premierMot();
    	//public boolean hasNext() {return true;}
    	return tmp;
    }
    public int test(DicoABR abr){
        return 4;
    }
}
