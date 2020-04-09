package scanner;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 */

/**
 * @author UTILISATEUR
 *
 */
public class Classe {
	private String nom;
	private List<Methode> methodes;
	private List<Argument> attributs;
	private List<Lien> liens;
	private boolean extend;
	private boolean implement;
	private Classe ext;
	private List<Classe> imp;
	private String pack;
	private boolean Interface;
	private boolean abstrait;

	public Classe(String n,List<Methode> m,List<Argument> a){
		nom = n;
		methodes=m;
		attributs=a;
	}
	/*Constructeur utilis� par le scanner*/
	public Classe(String nomC, List<Methode> meth, List<Argument> att, boolean etend, boolean implement2, String pack2,
			boolean interface2, boolean abstrait2) {
		super();
		this.nom = nom;
		this.methodes = methodes;
		this.attributs = attributs;
		this.liens = new LinkedList<Lien>();
		this.extend = extend;
		this.implement = implement;
		this.ext = null;
		this.imp = new LinkedList<Classe>();
		this.pack = pack;
		Interface = interface2;
		this.abstrait = abstrait;
	}

	/**
	 * Constructeur qui permet à partir d'une class java de récupérer le package, le nom de la classe, le attributs, les méthodes, les classes qu'elle implemente, etc et aussis'il s'agit d'une interfece, d'une classe abstraite.
	 * **/
	public Classe(Class cD) {

		Modifier mo=new Modifier();
		//methodes classe
		Method[] mD=cD.getDeclaredMethods();
		List<Methode> M= new LinkedList();
		Parameter[] f;
		List<Parametre> P;
		String n ,vis,trg;
		String[] tt;
		nom=Lecture.getTermeC(cD.getName()).trim();
		pack = Lecture.getTermeC(cD.getPackage().getName()).trim();
		for(int i=0;i<mD.length;i++) {
			n= Lecture.getTerme(mD[i].getName().toString());
			trg=mD[i].getGenericReturnType().getTypeName();

			if(trg.contains("<")) {
				trg=trg.substring(0, trg.indexOf("<"))+" < "+trg.substring(trg.indexOf("<")+1,trg.indexOf(">"))+" >";
			}
			trg=Lecture.getTerme(trg);
			tt=trg.split(" ");
			trg="";
			for(int j=0;j<tt.length;j++) {
				trg+=tt[j];
			}

			//System.out.println(trg);
			vis=mo.toString(mD[i].getModifiers());
			if(mD[i].getParameterCount()>0) {
				f=mD[i].getParameters();
				P=new LinkedList();
				for(int j=0;j<f.length;j++) {
					P.add(new Parametre(Lecture.getTerme(f[j].toString())));
				}
			}else {
				P=null;
			}
			M.add(new Methode(trg,n,P,vis));

		}
		//attributs classe
		Field[] at= cD.getDeclaredFields();
		List<Argument> At=new LinkedList();
		String type;


		for(int i=0;i<at.length;i++) {
			//System.out.println("Generique :"+at[i].getGenericType().getTypeName());

			/*Controle des liste pour le type*/
			type=at[i].getGenericType().getTypeName();


			if(type.contains("<")) {
				type=type.substring(0, type.indexOf("<"))+" < "+type.substring(type.indexOf("<")+1,type.indexOf(">"))+" >";
			}
			type=Lecture.getTermeC(type);
			tt=type.split(" ");
			type="";
			for(int j=0;j<tt.length;j++) {
				type+=tt[j];
			}

			//System.out.println("generic : "+type+" );

			String Ty=type;
			At.add(new Argument(at[i].getName(),Ty.trim(),mo.toString(at[i].getModifiers())));
		}
		Interface =cD.isInterface();
		int m =cD.getModifiers();
		abstrait = mo.isAbstract(m);
		methodes=M;
		attributs=At;
		extend = false;
		implement = false;
		ext =null;
		imp = new LinkedList<>();
		liens = new LinkedList<>();
	}
	//renvoie la liste des classes implémentées
	public List<Classe> getImpl(){
		return imp;
	}
	//renvoie true si la classe est une interface, false si non
	public boolean isInterface(){
		return Interface;
	}
	//renvoie true si la classe est une classe abstraite, false si non
	public boolean isAbstrait(){
		return abstrait;
	}
	//renvoie la classe qu'elle extend
	public Classe getExt(){
		return ext;
	}
	//permet de stocké la classe étendu
	public void setExt(Classe e){
		ext=e;
		setExtend();
	}
	//permet d'ajouter une classe implementée à la liste des classe implémentées.
	public void addImp(Classe e){
		this.imp.add(e);
		setImplement();
	}
	//renvoie le nom de la classe
	public String getNom() {
		return nom;
	}
	//set implements à true
	public void setImplement(){
		implement = true;
	}
	//renvoie la liste des méthodes
	public List<String> getMethodes() {
		List<String> li = new LinkedList<>();
		for(int i =0;i<methodes.size();i++){
			li.add(methodes.get(i).toString());
		}
		return li;
	}
	//remplie la liste lien avec toute les classes auxquelles la classe actuelle est liée
	public void trouverLien(List<Classe> cl){
		for(int i =0;i<cl.size();i++){
			Lien li = new Lien(this);
			if(li.presenceLien(cl.get(i))){
				this.liens.add(li);
			}
		}
	}
	//renvoie la liste des classes liées à la classe actuelle
	public List<Lien> getLiens(){
		return this.liens;
	}
	//set extend à true
	public void setExtend(){
		extend = true;
	}
	//renvoie la valeur de extend
	public boolean isExtend() {
		return extend;
	}
	//renvoie la valeur de implement
	public boolean isImplement() {
		return implement;
	}
	//renvoie la liste des attibuts
	public List<Argument> getAttributs(){
		return attributs;
	}
	
	public String toString() {
		String res =nom+" :\n\n";
		for (Argument a:attributs) {
			res = res+a.toString()+"\n";
		}
		for(String s:getMethodes()){
			res+=s+"\n";
		}
		return res;
	}

	//renvoie le nom du package
	public String getPackage() {
		return pack;
	}

	
	
	

}