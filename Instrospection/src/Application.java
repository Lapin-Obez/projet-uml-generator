import java.lang.reflect.*;

import srcALire.*;

public class Application {

    public static void main(String[] args) {
        /*Test avec des variables */
        System.out.println("-----------------Test avec des variables---------------------");
        Parametre p=new Parametre("int");
        Parametre p2=new Parametre("String");
        Parametre[] pp= {p,p2};

        Methode m=new Methode("String","Somme",pp,"public");
        Methode m2=new Methode("String","Mulitplier",pp,"public");
        Argument a1=new Argument("age","int", "private");
        Argument a2=new Argument("nom","String","protected");
        Argument[] a= {a1,a2};
        Methode[] ms= {m,m2};
        Classe c=new Classe("Test",ms,a);
        System.out.println(c.toString());

        System.out.println("-----------------Test avec de vraies classes---------------------");
        Modifier mo=new Modifier();
        DicoABR d=new DicoABR();
        Class cD= d.getClass();
        //methodes classe
        Method[] mD=cD.getDeclaredMethods();
        Methode[] M=new Methode[mD.length];
        Parameter[] f;
        Parametre[] P;
        String n ,tr,vis;
        for(int i=0;i<mD.length;i++) {
            n= Lecture.getTerme(mD[i].getName().toString());
            tr= Lecture.getTerme(mD[i].getReturnType().getName());
            vis=mo.toString(mD[i].getModifiers());
            if(mD[i].getParameterCount()>0) {
                f=mD[i].getParameters();
                P=new Parametre[mD[i].getParameterCount()];
                for(int j=0;j<P.length;j++) {
                    P[j]=new Parametre(Lecture.getTerme(f[j].toString()));
                }
            }else {
                P=null;
            }
            M[i]=new Methode(tr,n,P,vis);
        }
        //attributs classe
        Field[] at= cD.getDeclaredFields();
        Argument[] At=new Argument[at.length];
        String type;
        String type1;
        for(int i=0;i<at.length;i++) {
            type=Lecture.getTerme(at[i].getGenericType().getTypeName());
            type1=Lecture.getTerme(at[i].getType().getName());
            if(type.equals(type1)){
                At[i]=new Argument(at[i].getName(),type,mo.toString(at[i].getModifiers()));
            }else {
                String Ty=type+type1;
                At[i]=new Argument(at[i].getName(),Ty.trim(),mo.toString(at[i].getModifiers()));
            }


        }

        Classe cl=new Classe(cD.getName(),M,At);
        System.out.println(cl.toString());


        Modifier Mod=new Modifier();
        DicoHashSet dh=new DicoHashSet();
         cD= dh.getClass();
        //methodes classe
        mD=cD.getDeclaredMethods();
        M=new Methode[mD.length];
        for(int i=0;i<mD.length;i++) {
            n= Lecture.getTerme(mD[i].getName().toString());
            tr= Lecture.getTerme(mD[i].getReturnType().getName());
            vis=mo.toString(mD[i].getModifiers());
            if(mD[i].getParameterCount()>0) {
                f=mD[i].getParameters();
                P=new Parametre[mD[i].getParameterCount()];
                for(int j=0;j<P.length;j++) {
                    P[j]=new Parametre(Lecture.getTerme(f[j].toString()));
                }
            }else {
                P=null;
            }
            M[i]=new Methode(tr,n,P,vis);
        }
        //attributs classe
        at= cD.getDeclaredFields();
        At=new Argument[at.length];
        for(int i=0;i<at.length;i++) {
            type=Lecture.getTerme(at[i].getGenericType().getTypeName());
            type1=Lecture.getTerme(at[i].getType().getName());
            if(type.equals(type1)){
                At[i]=new Argument(at[i].getName(),type,mo.toString(at[i].getModifiers()));
            }else {
                String Ty=type+type1;
                At[i]=new Argument(at[i].getName(),Ty.trim(),mo.toString(at[i].getModifiers()));
            }


        }

        Classe cf=new Classe(cD.getName(),M,At);
        System.out.println(cf.toString());
        System.out.println(cf.getNom());
        System.out.println(cf.getPackage());
        Lien li =new Lien(cl,cf);
        if(li.presenceLien()){
            System.out.println("Lien trouvé");
            System.out.println(li.getMultipliciteD());
            System.out.println(li.getMultipliciteF());
        }else{
            System.out.println("pas de lien");
        }
    }


}
