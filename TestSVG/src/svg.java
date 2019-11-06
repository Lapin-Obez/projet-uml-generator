import java.awt.*;
import java.util.List;

import org.apache.batik.svggen.SVGGraphics2D;
public class svg {
	int cmp = 0;
	
   public void paint(SVGGraphics2D svgGenerator, Classe classe) {
      svgGenerator.setPaint(Color.BLACK);
      List<String> att = classe.getAttribut();
      List<String> meth = classe.getMethode();
      int y = cmp / 3 *300;
      int x = (cmp%3)*300;
      int pos = 0;
      svgGenerator.drawRect(10+x, 10+y, 200, 40);
      svgGenerator.drawString(classe.getName()+cmp, 85+x, 35+y);
      pos = 67;
      int yrectatt = att.size()*17+10;//+10 car size()*17 en taille mais on commence pas direct donc doit ajouter décalage
      svgGenerator.drawRect(10+x, 50+y, 200, yrectatt);
      for(String s : att) {
    	  svgGenerator.drawString(s+cmp, 30+x, pos+y);
    	  pos+=17;//+17 car size()*17
    	  //System.out.println("Tour : " +cmp+ "  Position y : "+(pos+y)+ "       Position x : "+(30+x));
      }     
      int yrectmeth = meth.size()*17+10;
      svgGenerator.drawRect(10+x, 50+y+yrectatt, 200, yrectmeth);
      pos = pos+10;
      for(String s : meth) {
    	  svgGenerator.drawString(s+cmp, 30+x, pos+y);
          pos+=17;//+17 car size()*17
      }
      cmp ++;
   }
} 