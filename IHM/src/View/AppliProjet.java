package View;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;

public class AppliProjet extends JFrame {
	// Les éléments de la vue soumis à modification	
	
	public AppliProjet() {
		super("Projet");
		
		// mise en page des différents JComponents
		JPanel main = new JPanel();
		main.setLayout(new GridBagLayout());
		this.getContentPane().add(main);
		
		JSVGCanvas svgCanvas = new JSVGCanvas();
		
		JPanel svg = new JPanel();
		svg.setLayout(new GridLayout(1,1));
		JPanel boutons = new JPanel();
		boutons.setLayout(new GridBagLayout());		
		
		GridBagConstraints gbc = new GridBagConstraints();
		 gbc.weightx = 1;
		 gbc.weighty = 1;
		 
		 gbc.gridx = 0;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.gridy = 0;
		 gbc.ipadx = 500;
		 gbc.fill = GridBagConstraints.BOTH;
		 main.add(svg, gbc);
		 
		 gbc.gridx = 2;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.gridy = 0;
		 gbc.ipadx = 0;
		 main.add(boutons, gbc);
		 
		 gbc.gridx = 0;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.insets = new Insets(10,10,10,10);
		 gbc.gridy = 0;
		 JButton imp = new JButton("Importer");
		 boutons.add(imp, gbc);
		
		 gbc.gridx = 0;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.gridy = 1;
		 boutons.add(new JButton("Générer"), gbc);
		 
		 gbc.gridx = 0;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.gridy = 2;
		 gbc.ipady = 400;
		 boutons.add(new JPanel(), gbc);
		 
		 gbc.gridx = 0;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.gridy = 3;
		 gbc.ipady = 0;
		 boutons.add(new JButton("Télécharger"), gbc);
		 
		 gbc.gridx = 0;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.gridy = 4;
		 gbc.ipady = 0;
		 JProgressBar prog = new JProgressBar(0);
		 boutons.add(prog, gbc);
		 prog.setValue(0);
		 prog.setStringPainted(true);
		 
		 svg.add(svgCanvas);
		 
	        // Set the button action.
	        imp.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                JFileChooser fc = new JFileChooser(".");
	                int choice = fc.showOpenDialog(main);
	                if (choice == JFileChooser.APPROVE_OPTION) {
	                    File f = fc.getSelectedFile();
	                    try {
	                        svgCanvas.setURI(f.toURL().toString());
	                    } catch (IOException ex) {
	                        ex.printStackTrace();
	                    }
	                }
	            }
	        });
	        
		this.setPreferredSize(new Dimension(850,700));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new AppliProjet();
	}
}
