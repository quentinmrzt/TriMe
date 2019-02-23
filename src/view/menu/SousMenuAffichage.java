package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.Modelisation;
import view.GestionEchelleImage;
import view.utils.CouleursConstantes;

public class SousMenuAffichage extends JMenu {

	JMenuItem zoomAvant, zoomArriere, ajuster, tailleReelle;

	public SousMenuAffichage(GestionEchelleImage gestionEchelleImage) {
		super("Affichage");
		setForeground(CouleursConstantes.TEXTECOLOR);
		zoomAvant = null;
		zoomArriere = null;

		setName("Affichage");
		add(creationMenuZoomAvant(gestionEchelleImage));
		add(creationMenuZoomArriere(gestionEchelleImage));
		add(creationMenuAjuster(gestionEchelleImage));
		add(creationMenuTailleReelle(gestionEchelleImage));
	}

	private JMenuItem creationMenuZoomAvant(GestionEchelleImage gestionEchelleImage) {
		String nom = "Zoom avant";
		zoomAvant = new JMenuItem(nom);
		zoomAvant.setForeground(CouleursConstantes.TEXTECOLOR);
		zoomAvant.setName(nom);
		zoomAvant.setEnabled(false);
		zoomAvant.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		zoomAvant.setIcon(new ImageIcon(getClass().getResource("zoom-in.png")));
		zoomAvant.setActionCommand("Zoom avant");
		zoomAvant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionEchelleImage.zoom();
			}
		});
		return zoomAvant;
	}

	private JMenuItem creationMenuZoomArriere(GestionEchelleImage gestionEchelleImage) {
		String nom = "Zoom arrière";
		zoomArriere = new JMenuItem(nom);
		zoomArriere.setForeground(CouleursConstantes.TEXTECOLOR);
		zoomArriere.setName(nom);
		zoomArriere.setEnabled(false);
		zoomArriere.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		zoomArriere.setIcon(new ImageIcon(getClass().getResource("zoom-out.png")));
		zoomArriere.setActionCommand("Zoom arrière");
		zoomArriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionEchelleImage.dezoom();
			}
		});
		return zoomArriere;
	}

	private JMenuItem creationMenuAjuster(GestionEchelleImage gestionEchelleImage) {
		String nom = "Ajuster à la fenêtre";
		ajuster = new JMenuItem(nom);
		ajuster.setForeground(CouleursConstantes.TEXTECOLOR);
		ajuster.setName(nom);
		ajuster.setEnabled(false);
		ajuster.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		ajuster.setIcon(new ImageIcon(getClass().getResource("reduce.png")));
		ajuster.setActionCommand("Ajuster à la fenêtre");
		ajuster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//gestionEchelleImage.ajuster();
			}
		});
		return ajuster;
	}

	private JMenuItem creationMenuTailleReelle(GestionEchelleImage gestionEchelleImage) {
		String nom = "Taille réelle";
		tailleReelle = new JMenuItem(nom);
		tailleReelle.setForeground(CouleursConstantes.TEXTECOLOR);
		tailleReelle.setName(nom);
		tailleReelle.setEnabled(false);
		tailleReelle.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		tailleReelle.setIcon(new ImageIcon(getClass().getResource("resize.png")));
		tailleReelle.setActionCommand("Taille réelle");
		tailleReelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionEchelleImage.tailleReelle();
			}
		});
		return tailleReelle;
	}

	public void miseAJour(Modelisation modelisation) {
		zoomAvant.setEnabled(modelisation.getImage() != null);
		zoomArriere.setEnabled(modelisation.getImage() != null);
		tailleReelle.setEnabled(modelisation.getImage() != null);
	}
}
