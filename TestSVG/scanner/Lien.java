package scanner;

import java.util.LinkedList;
import java.util.List;

public class Lien {
   //La classe de départ
   private Classe classe;
   //la multiplicité du coté opposé à la classe de départ
   private String multipliciteD;
   //La multiplicité du côté de la classe de départ
   private String multipliciteF;
   //Une des classes à laquelle la classe de départ est liée
   private Classe lier;


   public Lien(Classe f){
       this.multipliciteD="0";
       this.multipliciteF="0";
       this.classe = f;
   }
   //Calcule la multiplicitéD
   public void calculerMultipliciteD(Classe actuelle){
       List<String> li = new LinkedList<>();
       List<Argument> argdeb=this.classe.getAttributs();
       for (Argument a:argdeb) {
           li.add(a.getType());
       }
       int cmp=0;
       for(int i =0; i<li.size();i++){
           if(li.get(i).equals(actuelle.getNom())||li.get(i).equals(actuelle.getNom()+"  ")){
               cmp++;
               multipliciteD =""+cmp;
           }
           if(li.get(i).equals(actuelle.getNom().trim()+"[]")||li.get(i).equals("List<"+actuelle.getNom().trim()+">")){
               multipliciteD = "*";
               lier = actuelle;
               return;
           }

       }
       lier = actuelle;
   }

    //Calcule la multiplicitéF
    public void calculerMultipliciteF(Classe actuelle){
       List<String> li = new LinkedList<>();
       List<Argument> argdeb=actuelle.getAttributs();
        for (Argument a:argdeb) {
           li.add(a.getType());
       }

       int cmp=0;
       for(int i =0; i<li.size();i++){
           if(li.get(i).equals(classe.getNom())||li.get(i).equals(classe.getNom()+"  ")){
               cmp++;
               multipliciteF =""+cmp;
           }
           if(li.get(i).equals(classe.getNom().trim()+"[]")||li.get(i).equals("List<"+classe.getNom().trim()+">")){
               multipliciteD = "*";
               return;
           }
       }
   }

   //Permet de récupérer la classe lier
   public Classe getLier(){
       return this.lier;
   }


   public boolean presenceLien(Classe actuelle){
           calculerMultipliciteD(actuelle);
           calculerMultipliciteF(actuelle);
       if(multipliciteD.equals("0") && multipliciteF.equals("0")){
           return false;
       }else{
           return true;
       }
   }
    //permet de récupérer la multiplicitéD
    public String getMultipliciteD() {
        return multipliciteD;
    }
    //permet de récupérer la multiplicitéF
    public String getMultipliciteF() {
        return multipliciteF;
    }
}
