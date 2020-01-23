package scanner;

import java.util.LinkedList;
import java.util.List;

public class Lien {
   private Classe classe;
   private String multipliciteD;
   private String multipliciteF;
   private Classe lier;
   public Lien(Classe f){
       this.multipliciteD="";
       this.multipliciteF="";
       this.classe = f;
   }
   public void calculerMultipliciteD(Classe actuelle){
       List<String> li = new LinkedList<>();
       Argument[] argdeb=this.classe.getAttributs();
       for (Argument a:argdeb) {
           li.add(a.getType());
       }
       int cmp=0;
       for(int i =0; i<li.size();i++){
           if(li.get(i).equals(actuelle.getNom())||li.get(i).equals(actuelle.getNom()+"  ")){
               cmp++;
           }
           if(li.get(i).equals(actuelle.getNom().trim()+"[]")){
               cmp=3;
           }

       }
       if(cmp==0){
           multipliciteD="0";
       }else if(cmp==1){
           multipliciteD="1";
       }else{
           multipliciteD="*";
       }
       lier = actuelle;
   }

   public void calculerMultipliciteF(Classe actuelle){
       List<String> li = new LinkedList<>();
       Argument[] argdeb=actuelle.getAttributs();
       for (Argument a:argdeb) {
           li.add(a.getType());
       }

       int cmp=0;
       for(int i =0; i<li.size();i++){
           if(li.get(i).equals(classe.getNom())||li.get(i).equals(classe.getNom()+"  ")){
               cmp++;
           }
           if(li.get(i).equals(classe.getNom().trim()+"[]")){
               cmp=3;
           }
       }

       if(cmp==0){
           multipliciteF="0";
       }else if(cmp==1){
           multipliciteF="1";
       }else{
           multipliciteF="*";
       }
   }
   public Classe getLier(){
       return this.lier;
   }
   public boolean presenceLien(Classe actuelle){
       calculerMultipliciteD( actuelle);
       calculerMultipliciteF( actuelle);
       if(multipliciteD.equals("0") && multipliciteF.equals("0")){
           return false;
       }else{
           return true;
       }
   }
    public String getMultipliciteD() {
        return multipliciteD;
    }

    public String getMultipliciteF() {
        return multipliciteF;
    }
}
