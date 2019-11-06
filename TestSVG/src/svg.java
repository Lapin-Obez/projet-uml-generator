import java.awt.*;
import java.io.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;
public class svg {
	int cmp = 0;
	
   public void paint(SVGGraphics2D svgGenerator) {
      svgGenerator.setPaint(Color.BLACK);
      int y;
      y = cmp / 3 ;
      svgGenerator.drawRect(10+(300*(cmp%3)), 10+(300*y), 200, 40);
      svgGenerator.drawString("Eleve", 90+(300*(cmp%3)), 35+(300*y));
      svgGenerator.drawRect(10+(300*(cmp%3)), 50+(300*y), 200, 100);
      svgGenerator.drawString("# Nom : String", 30+(300*(cmp%3)), 70+(300*y));
      svgGenerator.drawString("# Prenom : String", 30+(300*(cmp%3)), 85+(300*y));
      svgGenerator.drawString("# Âge : Integer", 30+(300*(cmp%3)), 100+(300*y));
      svgGenerator.drawString("# ddn : Date", 30+(300*(cmp%3)), 115+(300*y));
      svgGenerator.drawString("# numEtu : Integer", 30+(300*(cmp%3)), 130+(300*y));
      svgGenerator.drawString("# Moyenne : float", 30+(300*(cmp%3)), 145+(300*y));
      svgGenerator.drawRect(10+(300*(cmp%3)), 150+(300*y), 200, 80);
      svgGenerator.drawString("+ getNumEtu() : Integer", 30+(300*(cmp%3)), 165+(300*y));
      svgGenerator.drawString("+ getNomPrenom() : String", 30+(300*(cmp%3)), 180+(300*y));
      svgGenerator.drawString("+ getDDN() : Date()", 30+(300*(cmp%3)),195+(300*y));
      svgGenerator.drawString("+ getClasse() : Class()", 30+(300*(cmp%3)), 210+(300*y));
      svgGenerator.drawString("+ toString() : String", 30+(300*(cmp%3)), 225+(300*y));
      cmp ++;
   }
   
   public static void main(String [] args) throws IOException {
      /*Creation de l'instance Document qui sera utilisé pour construire le contenu XML
      création d'une instance de svggenerator (graphics2D) en utilisant le doc créé */
      // Récupère la DOMImplementation
      DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

      // Création d'une instance org.w3c.dom.Document
      Document document = domImpl.createDocument(null, "svg", null);

      //Création d'une instance de SVG Generator
      SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

      /* Dessine sur le composant svggenerator */
      svg test;
      test = new svg();
      svgGenerator.setPaint(Color.white);
	  svgGenerator.fill(new Rectangle(0,0,1000,1000));
	  test.paint(svgGenerator);
      test.paint(svgGenerator);
      test.paint(svgGenerator);
      test.paint(svgGenerator);
      test.paint(svgGenerator);
      test.paint(svgGenerator);
      test.paint(svgGenerator);
      test.paint(svgGenerator);
      test.paint(svgGenerator);
      /* sortir le résultat (peut se faire sur n'importe quel java.io.writer) */
      svgGenerator.stream("Image_TestSVGGen.svg");
   }
} 