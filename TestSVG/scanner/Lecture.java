package scanner;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import srcALire.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Lecture.
 */
public class Lecture {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Class> l=new LinkedList();
		DicoABR d1= new DicoABR();
		DicoHashSet d2= new DicoHashSet();
		
		l.add(d1.getClass());
		l.add(d2.getClass());
		Method[] m;
		Field[] f;
		Modifier mod= new Modifier();
		String s;
		Type[] param;
		String lect;
		String fLect;
		String classe;
		
		
		for(int i=0;i<l.size();i++) {
			classe=l.get(i).getName();
			System.out.println("\nAttributs:");
			f=l.get(i).getDeclaredFields();
			for(int j=0;j<f.length;j++) {
				lect=mod.toString(f[j].getModifiers())+" "+f[j].getType().getName()+" "+f[j].getName()+" ";
				fLect=getTerme(lect);
				System.out.println(fLect);
			}
			System.out.println("\nM�thodes:");
			m=l.get(i).getDeclaredMethods();
			for(int j=0;j<m.length;j++) {
				if(m[j].getParameterCount()==0) {
					lect=mod.toString(m[j].getModifiers())+" "+m[j].getGenericReturnType().getTypeName()+" "+m[j].getName()+" ";
					fLect=getTerme(lect);
					System.out.println(fLect);
				}else if(m[j].getParameterCount()>1){
					s=mod.toString(m[j].getModifiers())+" "+m[j].getGenericReturnType().getTypeName()+" "+m[j].getName()+" (";
					param=m[j].getGenericParameterTypes();
					for(int k=0;k<param.length;k++) {
						s+=param[k].getTypeName()+", ";
					}
					s+=") ";
					fLect=getTerme(s);
					System.out.println(fLect);
				}else {
					s=mod.toString(m[j].getModifiers())+" "+m[j].getReturnType().getName()+" "+m[j].getName()+" (";
					param=m[j].getGenericParameterTypes();
					s+=param[0].getTypeName()+" )";
					fLect=getTerme(s);
					System.out.println(fLect);
				}
				
			}
			System.out.println("\n");
		}
	
	
	}*/
	
	/**
	 * Gets the terme.
	 *
	 * @param texte the texte
	 * @return the terme
	 */
	public static String getTerme(String texte) {
	    List<String> li=new LinkedList<>();

	    Pattern pattern = Pattern.compile("([A-z]*) |([(])|([)])|([,])|([:])|(['['])|([']'])|([<])|([>])");   
	    Matcher matcher = pattern.matcher(texte+" ");
	    
	    String s,s2="";
	    while(matcher.find()) {
	        s=matcher.group();
	        if(s.charAt(0)=='.') {
	            for (int i = 1; i < s.length() ; i++) {
	                s2 = s2 + s.charAt(i) + "";
	            }
	        }else{
	            s2=s;
	        }
	        li.add(s2);
	        s2="";
	    }
	    String str=li.get(0);
	    for (int i =1;i<li.size();i++){
	        str=str+" "+li.get(i);
	    }
	    return str;
	}
	
	/**
	 * Gets the class from the format String.ressourceWanted
	 *
	 * @param texte the texte
	 * @return the terme C
	 */
	public static String getTermeC(String texte) {
	    List<String> li=new LinkedList<>();

	    Pattern pattern = Pattern.compile("([A-z]*) |([.])");
	    Matcher matcher = pattern.matcher(texte+" ");
	    String s,s2="";
	    while(matcher.find()) {
	        s=matcher.group();
	        if(s.charAt(0)=='.') {
	            for (int i = 1; i < s.length() ; i++) {
	                s2 = s2 + s.charAt(i) + "";
	            }
	        }else{
	            s2=s;
	        }
	        li.add(s2);
	        s2="";
	    }
	    String str=li.get(0);
	    for (int i =1;i<li.size();i++){
	        str=str+" "+li.get(i);
	    }
	    return str;
	}
	
	/**
	 * Gets the package.
	 *
	 * @param texte the texte
	 * @return the package
	 */
	public static String getPackage(String texte){
		  Pattern pattern = Pattern.compile("([A-z].)");
		  Matcher matcher = pattern.matcher(texte+" ");
		  String s,s2="";
		  while(matcher.find()) {
		     s=matcher.group();
		     if(s.charAt(0)=='.') {
		        for (int i = 1; i < s.length() ; i++) {
		           s2 = s2 + s.charAt(i) + "";
		        }
		     }else{
		        s2=s;
		     }
		  }
		  return s2;
	}
	
	public static String getTermeP(String texte) {
	    List<String> li=new LinkedList<>();

	    Pattern pattern = Pattern.compile("([A-z]*)|([(])");
	    Matcher matcher = pattern.matcher(texte+" ");
	    String s,s2="";
	    while(matcher.find()) {
	        s=matcher.group();
	       //System.out.println("sgh");
	        li.add(s2);
	        s2="";
	    }
	    return s2;
	}
	/* |([<])|([>])*/
}