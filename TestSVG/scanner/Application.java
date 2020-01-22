package scanner;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import srcALire.Cours.Enseignant;
import srcALire.Cours.Matiere;
import src.*;

public class Application {

    public static void main(String[] args) {
//        /*Test avec des variables */
//        System.out.println("-----------------Test avec des variables---------------------");
//        Parametre p=new Parametre("int");
//        Parametre p2=new Parametre("String");
//        Parametre[] pp= {p,p2};
//
//        Methode m=new Methode("String","Somme",pp,"public");
//        Methode m2=new Methode("String","Mulitplier",pp,"public");
//        Argument a1=new Argument("age","int", "private");
//        Argument a2=new Argument("nom","String","protected");
//        Argument[] a= {a1,a2};
//        Methode[] ms= {m,m2};
//        Classe c=new Classe("Test",ms,a);
//        System.out.println(c.toString());

        System.out.println("-----------------Test avec de vraies classes---------------------");
        Modifier mo=new Modifier();
//   Enseignant d=new Enseignant("Jean-François Remm");
        Class cD= new Enseignant("").getClass();
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
        ArrayList<String> l = new ArrayList<>();
        for(Methode m : cl.getMeth()) {
        	l.add(m.toString());
        }
        ArrayList<String> a = new ArrayList<>();
        for(Argument m : cl.getAttribut()) {
        	a.add(m.toString());
        }
        src.Classe c = new src.Classe(cl.getNom(), a,l, cl.getPackage());
        /*Creation de l'instance Document qui sera utilisé pour construire le contenu XML
	      création d'une instance de svggenerator (graphics2D) en utilisant le doc créé */
		// Récupére la DOMImplementation
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

		// Création d'une instance de SVG
		Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);

		//Création d'une instance de SVG Generator
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

		/* Dessine sur le composant svggenerator */
		
		svgGenerator.setPaint(Color.white);
		
		ArrayList<src.Classe> list = new ArrayList<>();
		list.add(c);
		
		List<src.Package> paqu = new ArrayList<>();
		svg.triClasse(paqu, list);
		for(src.Package p : paqu) {
			if(p != null) {
				System.out.println(p.getName() + " : ");
				for(src.Classe c1 : p.getClasse()) {
					System.out.println(c.toString());
				}
				System.out.println(" ///// ");
				svg.paintPackage(svgGenerator, p);
			}
		}
		svg.paintClasse(svgGenerator, paqu);
		for (src.Classe c1 : list) {
			svg.paintLink(svgGenerator, c, list);
		}
		Element root = svgGenerator.getRoot();
		root.setAttributeNS(null, "width", src.Package.droite+"");
		root.setAttributeNS(null, "height", src.Package.bas+"");
		/* sortir le résultat*/
		Writer out;
		try {
			out = new OutputStreamWriter(new FileOutputStream("Test_multi.svg"), "UTF-8");
			svgGenerator.stream(root,out, true, false);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SVGGraphics2DIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
        Modifier Mod=new Modifier();
        Matiere dh=new Matiere("Programmation objet 2",18);
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
