package view.menu;

import static javax.swing.JFileChooser.APPROVE_OPTION;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import model.Modelisation;
import view.utils.CouleursConstantes;

public class SousMenuFichier extends JMenu {

	private Controller controlleur;
	private JMenuItem sauvegarder, fermer;

	public SousMenuFichier(Controller controlleur) {
		super("Fichier");
		setForeground(CouleursConstantes.TEXTECOLOR);
		this.controlleur = controlleur;
		setName("Fichier");
		add(creationMenuChoisir());
		add(creationMenuFermer());
		add(creationMenuSauvegarder());
		add(creationMenuQuitter());
	}

	private JMenuItem creationMenuChoisir() {
		String nom = "Ouvrir...";
		JMenuItem choisir = new JMenuItem(nom);
		choisir.setForeground(CouleursConstantes.TEXTECOLOR);
		choisir.setName(nom);
		choisir.setName("MenuChoisir");
		choisir.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		choisir.setIcon(new ImageIcon(getClass().getResource("open-archive.png")));
		choisir.setActionCommand("Ouvrir...");
		choisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choisir();
			}
		});
		return choisir;
	}

	private void choisir() {
		try {
			JFileChooser jf = new JFileChooser();
			jf.setFileFilter(new FileNameExtensionFilter("Fichier image", "png", "jpg", "bmp"));
			jf.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures"));
			jf.setDialogTitle("Choisir une image");
			if (jf.showOpenDialog(getParent()) == APPROVE_OPTION) {
				controlleur.controleCheminImage(jf.getSelectedFile());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private JMenuItem creationMenuFermer() {
		String nom = "Fermer";
		fermer = new JMenuItem(nom);
		fermer.setForeground(CouleursConstantes.TEXTECOLOR);
		fermer.setName(nom);
		fermer.setEnabled(false);
		fermer.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		fermer.setIcon(new ImageIcon(getClass().getResource("close-archive.png")));
		fermer.setActionCommand(nom);
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment fermer ?", nom, JOptionPane.YES_NO_OPTION);
				if (retour == JOptionPane.OK_OPTION) {
					controlleur.fermerImage();
				}
			}
		});
		return fermer;
	}

	private JMenuItem creationMenuSauvegarder() {
		String nom = "Sauvegarder";
		sauvegarder = new JMenuItem("<HTML>" + nom + "</HTML>");
		sauvegarder.setForeground(CouleursConstantes.TEXTECOLOR);
		sauvegarder.setName(nom);
		sauvegarder.setEnabled(false);
		sauvegarder.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		sauvegarder.setIcon(new ImageIcon(getClass().getResource("save.png")));
		sauvegarder.setActionCommand("Sauvegarder");
		sauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sauvegarder();
			}
		});
		return sauvegarder;
	}

	private void sauvegarder() {
		try {
			JFileChooser jf = new JFileChooser();
			jf.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures"));
			jf.setDialogTitle("Enregistrer");
			if (jf.showSaveDialog(getParent()) == APPROVE_OPTION) {
				controlleur.sauvegarder(jf.getSelectedFile());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private JMenuItem creationMenuQuitter() {
		String nom = "Quitter";
		JMenuItem quitter = new JMenuItem("<HTML><U>Q</U>uitter</HTML>");
		quitter.setForeground(CouleursConstantes.TEXTECOLOR);
		quitter.setName(nom);
		quitter.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		quitter.setActionCommand(nom);
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		return quitter;
	}

	public void miseAJour(Modelisation modelisation) {
		sauvegarder.setEnabled(modelisation.getImage() != null);
		fermer.setEnabled(modelisation.getImage() != null);
	}
}
