package view.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import controller.Controller;
import execution.Traitement;
import model.Modelisation;
import view.boite.BoiteChargement;
import view.boite.BoiteSaisiePixels;

public class SousMenuEdition extends JMenu implements Observer {

	private final Color BACKGROUNDCOLOR = Color.WHITE;

	private Controller controlleur;
	private JMenuItem suppression;
	private BoiteChargement boiteChargement;

	public SousMenuEdition(Controller controller) {
		super("Edition");

		this.controlleur = controller;
		boiteChargement = null;

		setName("Edition");
		add(creationMenuSuppresion());
		add(creationMenuDessiner());
	}

	private JMenuItem creationMenuSuppresion() {
		String nom = "Supprimer pixel(s)";
		suppression = new JMenuItem(nom);
		suppression.setName(nom);
		suppression.setEnabled(false);
		suppression.setBackground(BACKGROUNDCOLOR);
		suppression.setIcon(new ImageIcon(getClass().getResource("crop.png")));
		suppression.setActionCommand(nom);
		suppression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoiteSaisiePixels boite = new BoiteSaisiePixels(getJFrame(suppression), nom);
				if (boite.estValide()) {
					System.out.println("A");
					controlleur.supprimerDesPixels(boite.getSaisie());
					System.out.println("B");
					boiteChargement = new BoiteChargement(getJFrame(suppression), nom);
					System.out.println("C");
				}
			}
		});
		return suppression;
	}

	private JMenuItem creationMenuDessiner() {
		String nom = "Supprimer/dessiner pixel(s)";
		JMenuItem dessin = new JMenuItem(nom);
		dessin.setName(nom);
		dessin.setEnabled(false);
		dessin.setBackground(BACKGROUNDCOLOR);
		dessin.setIcon(new ImageIcon(getClass().getResource("pencil-edit-button.png")));
		dessin.setActionCommand(nom);
		dessin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoiteSaisiePixels boite = new BoiteSaisiePixels(getJFrame(dessin), nom);
				if (boite.estValide()) {
					boiteChargement = new BoiteChargement(getJFrame(dessin), nom);
					controlleur.dessinerDesPixels(boite.getSaisie());
				}
			}
		});
		return dessin;
	}

	private JFrame getJFrame(Component e) throws ClassCastException {
		if (e instanceof JMenuItem) {
			while (null != e && !(e instanceof JFrame))
				if (e instanceof JPopupMenu)
					e = ((JPopupMenu) e).getInvoker();
				else
					e = ((JComponent) e).getParent();
		} else {
			e = SwingUtilities.getWindowAncestor(e);
		}
		return (JFrame) e;
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof Modelisation) {
			Modelisation modelisation = (Modelisation) obs;
			miseAJourMenu(modelisation);
		} else if (obs instanceof Traitement) {
			System.out.println("test");
			Traitement traitement = (Traitement) obs;
			miseAJourBoite(traitement);
		}
	}

	private void miseAJourMenu(Modelisation modelisation) {
		suppression.setEnabled(modelisation.getImage() != null);
	}

	private void miseAJourBoite(Traitement traitement) {
		if (boiteChargement != null) {
			System.out.println("qqq");
			boiteChargement.miseAJour(traitement);
		}
	}
}
