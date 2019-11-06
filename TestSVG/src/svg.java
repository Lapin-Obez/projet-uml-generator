import java.awt.*;
import java.io.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;
public class svg {
	
   public void paint(SVGGraphics2D svgGenerator) {
      svgGenerator.setPaint(Color.red);
      svgGenerator.fill(new Rectangle(10, 10, 100, 100));
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
      test.paint(svgGenerator);

      /* sortir le résultat (peut se faire sur n'importe quel java.io.writer) */
      svgGenerator.stream("Image_TestSVGGen.svg");
   }
} 