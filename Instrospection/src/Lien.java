import java.util.LinkedList;
import java.util.List;

public class Lien {
    private Classe debut;
    private Classe fin;
    private String multipliciteD;
    private String multipliciteF;

    public Lien(Classe d,Classe f){
        this.multipliciteD="";
        this.multipliciteF="";
        this.debut=d;
        this.fin=f;
    }

    public void identifierLienDebut(){
        List<String> li = new LinkedList<>();
        Argument[] argdeb=debut.getAttribut();
        for (Argument a:argdeb) {
            li.add(a.getType());
        }

        int cmp=0;
        for(int i =0; i<li.size();i++){
            System.out.println(fin.getNom()+"[]");
            System.out.println(li.get(i));
            if(li.get(i).equals(fin.getNom())||li.get(i).equals(fin.getNom()+"  ")){
                cmp++;
            }
            if(li.get(i).equals(fin.getNom().trim()+"[]")){
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
    }


    public void identifierLienFin(){
        List<String> li = new LinkedList<>();
        Argument[] argdeb=fin.getAttribut();
        for (Argument a:argdeb) {
            li.add(a.getType());
        }

        int cmp=0;
        for(int i =0; i<li.size();i++){
            if(li.get(i).equals(debut.getNom())||li.get(i).equals(debut.getNom()+"  ")){
                cmp++;
            }
            if(li.get(i).equals(debut.getNom().trim()+"[]")){
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
    public boolean presenceLien(){
        identifierLienDebut();
        identifierLienFin();
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
