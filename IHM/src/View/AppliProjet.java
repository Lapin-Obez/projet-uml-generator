package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppliProjet extends JFrame {
	// Les éléments de la vue soumis à modification	
	
	public AppliProjet() {
		super("Projet");
//		SVGApplication app = new SVGApplication(f);
		
		
		// mise en page des différents JComponents
		JPanel main = new JPanel();
		main.setLayout(new GridBagLayout());
		this.getContentPane().add(main);
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
		 boutons.add(new JButton("Importation"), gbc);
		
		 gbc.gridx = 0;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.gridy = 1;
		 boutons.add(new JButton("Générer"), gbc);
		 
		 gbc.gridx = 0;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.gridy = 2;
		 gbc.ipady = 500;
		 boutons.add(new JPanel(), gbc);
		 
		 gbc.gridx = 0;
		 gbc.gridwidth = 1;
		 gbc.gridheight = 1;
		 gbc.gridy = 3;
		 gbc.ipady = 0;
		 boutons.add(new JButton("Télécharger"), gbc);
		 
		 JButton image = new JButton("image SVG");
		 svg.add(image);
		 
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
