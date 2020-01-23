package scanner;
import java.lang.reflect.*;
/**
 *
 */

import java.util.LinkedList;
import java.util.List;

import static scanner.Lecture.getTerme;

/**
 * @author UTILISATEUR
 *
 */
public class Classe {
    private String nom;
    private String pack;
    private String[] methodes;
    private Argument[] attributs;
    private List<Lien> liens;

    public Classe(Class a) {
        this.liens = new LinkedList<>();
        this.nom = getTerme(a.getName());
        System.out.println(this.nom);
        this.pack = a.getPackage().getName();
        Modifier mo=new Modifier();
        //récupère les différentes méthodes de la classe
        Method[] mD=a.getDeclaredMethods();
        methodes = new String[mD.length];
        Parameter[] f;
        Parametre[] P;
        String n ,tr,vis;

        for(int i=0;i<mD.length;i++) {
            n= getTerme(mD[i].getName());
            tr= getTerme(mD[i].getReturnType().getName());
            vis=mo.toString(mD[i].getModifiers());

            if(mD[i].getParameterCount()>0) {
                f=mD[i].getParameters();
                P=new Parametre[mD[i].getParameterCount()];
                for(int j=0;j<P.length;j++) {
                    P[j]=new Parametre(getTerme(f[j].toString()));
                }
            }else {
                P=null;
            }

            Methode Meth =new Methode(tr,n,P,vis);
            methodes[i]= Meth.toString();
        }

        //attributs classe
        Field[] at= a.getDeclaredFields();
        attributs = new Argument[at.length];
        String type;
        String type1;
        for(int i=0;i<at.length;i++) {
            type= getTerme(at[i].getGenericType().getTypeName());
            type1= getTerme(at[i].getType().getName());
            if(type.equals(type1)){
                attributs[i]=new Argument(at[i].getName(),type,mo.toString(at[i].getModifiers()));
            }else {
                String Ty=type+type1;
                attributs[i]=new Argument(at[i].getName(),Ty.trim(),mo.toString(at[i].getModifiers()));
            }


        }

    }
    public void trouverLien(List<Classe> cl){
        for(int i =0;i<cl.size();i++){
            Lien li =new Lien(this);
            if(li.presenceLien(cl.get(i))){
                this.liens.add(li);
                System.out.println(this.nom+" ---->  "+li.getLier().nom);
                System.out.println(li.getMultipliciteD()+" -----> "+li.getMultipliciteF());
            }
        }
    }
    
    public List<Lien> getLiens() {
		return liens;
	}
	public void setLiens(List<Lien> liens) {
		this.liens = liens;
	}
	public String getPackage(){return pack;}
    public String getNom() {
        return nom;
    }

    public String[] getMethodes() {
        return methodes;
    }

    public Argument[] getAttributs(){
        return attributs;
    }

    public String toString() {
        String res = this.nom+":\n\n";
        for(int i =0;i<attributs.length;i++){
            res+=attributs[i].toString()+"\n";
        }
        res+="\n";
        for(int i =0;i<methodes.length;i++){
            res+=methodes[i]+"\n";
        }
        return res;
    }





}