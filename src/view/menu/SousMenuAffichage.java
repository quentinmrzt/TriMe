package view.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SousMenuAffichage extends JMenu {
	
	private final Color BACKGROUNDCOLOR = Color.WHITE;
	
	public SousMenuAffichage() {
		super("Affichage");
				
		setName("Affichage");
		add(creationMenuZoomAvant());
		add(creationMenuZoomArriere());
		add(creationMenuAjuster());
		add(creationMenuTailleReelle());
	}


	private JMenuItem creationMenuZoomAvant() {
		String nom = "Zoom avant";
		JMenuItem zoomAvant = new JMenuItem(nom);
		zoomAvant.setName(nom);
		zoomAvant.setEnabled(false);
		zoomAvant.setBackground(BACKGROUNDCOLOR);
		zoomAvant.setIcon(new ImageIcon(getClass().getResource("zoom-in.png")));
		zoomAvant.setActionCommand("Zoom avant");
		zoomAvant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		return zoomAvant;
	}

	private JMenuItem creationMenuZoomArriere() {
		String nom = "Zoom arrière";
		JMenuItem zoomArriere = new JMenuItem(nom);
		zoomArriere.setName(nom);
		zoomArriere.setEnabled(false);
		zoomArriere.setBackground(BACKGROUNDCOLOR);
		zoomArriere.setIcon(new ImageIcon(getClass().getResource("zoom-out.png")));
		zoomArriere.setActionCommand("Zoom arrière");
		zoomArriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		return zoomArriere;
	}

	private JMenuItem creationMenuAjuster() {
		String nom = "Ajuster à la fenêtre";
		JMenuItem ajuster = new JMenuItem(nom);
		ajuster.setName(nom);
		ajuster.setEnabled(false);
		ajuster.setBackground(BACKGROUNDCOLOR);
		ajuster.setIcon(new ImageIcon(getClass().getResource("reduce.png")));
		ajuster.setActionCommand("Ajuster à la fenêtre");
		ajuster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		return ajuster;
	}

	private JMenuItem creationMenuTailleReelle() {
		String nom = "Taille réelle";
		JMenuItem tailleReelle = new JMenuItem(nom);
		tailleReelle.setName(nom);
		tailleReelle.setEnabled(false);
		tailleReelle.setBackground(BACKGROUNDCOLOR);
		tailleReelle.setIcon(new ImageIcon(getClass().getResource("resize.png")));
		tailleReelle.setActionCommand("Taille réelle");
		tailleReelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		return tailleReelle;
	}
}
