package test;
import org.junit.jupiter.api.Test;
import scanner.Classe;
import scanner.Lien;
import srcALire.Cours.Enseignant;
import srcALire.Cours.Matiere;
import srcALire.Groupe.Etudiant;
import srcALire.Groupe.groupe;


import static org.junit.jupiter.api.Assertions.*;


public class testLien {
    groupe gr = new groupe(1,20);
    Classe prof = new Classe(new Enseignant("Jean-François Remm").getClass());
    Classe matiere = new Classe(new Matiere("Programmation objet 2",18).getClass());
    Classe groupe = new Classe(gr.getClass());
    Classe etudiant = new Classe(new Etudiant("De Lapatefeuilleté","Hubert","e185475j",gr).getClass());
    @Test
    public void trouverLien(){

        Lien li =new Lien(prof);
        assertTrue(li.presenceLien(matiere));
        assertEquals("*",li.getMultipliciteD());
        assertEquals("1",li.getMultipliciteF());
    }
    @Test
    public void testnonLien(){

        Lien li =new Lien(prof);
        assertFalse(li.presenceLien(etudiant));
        assertEquals("0",li.getMultipliciteD());
        assertEquals("0",li.getMultipliciteF());
    }
    @Test
    public void testMultiplicit�(){
        Lien li = new Lien(matiere);
        li.presenceLien(groupe);
        assertEquals("0",li.getMultipliciteD());
        assertEquals("*",li.getMultipliciteF());

    }



}