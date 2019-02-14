package view;

import static javax.swing.JFileChooser.APPROVE_OPTION;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import model.Modelisation;
import view.boite.BoiteChargement;

public class Menu extends JMenuBar {

	private BoiteChargement boiteChargement;
	private Controller controlleur;
	private final Color BACKGROUNDCOLOR = Color.WHITE;

	public Menu(Controller controlleur) {
		this.controlleur = controlleur;

		setBackground(BACKGROUNDCOLOR);
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180, 180, 180)));

		boiteChargement = null;

		JMenu fichier = new JMenu("Fichier");
		fichier.add(creationMenuChoisir());
		fichier.add(creationMenuFermer());
		fichier.add(creationMenuSauvegarder());
		fichier.add(creationMenuQuitter());
		add(fichier);

		JMenu edition = new JMenu("Edition");
		edition.add(creationMenuSuppresion());
		edition.add(creationMenuDessiner());
		add(edition);

		JMenu affichage = new JMenu("Affichage");
		affichage.add(creationMenuZoomAvant());
		affichage.add(creationMenuZoomArriere());
		affichage.add(creationMenuAjuster());
		affichage.add(creationMenuTailleReelle());
		add(affichage);

		JMenu aide = new JMenu("Aide");
		aide.add(creationMenuGithub());
		add(aide);
	}

	private JMenuItem creationMenuChoisir() {
		JMenuItem choisir = new JMenuItem("Ouvrir...");
		choisir.setBackground(BACKGROUNDCOLOR);
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
		JMenuItem choisir = new JMenuItem("Fermer");
		choisir.setEnabled(false);
		choisir.setBackground(BACKGROUNDCOLOR);
		choisir.setIcon(new ImageIcon(getClass().getResource("close-archive.png")));
		choisir.setActionCommand("Fermer");
		choisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		return choisir;
	}

	private JMenuItem creationMenuSauvegarder() {
		JMenuItem sauvegarder = new JMenuItem("<HTML>Sauvegarder</HTML>");
		sauvegarder.setEnabled(false);
		sauvegarder.setBackground(BACKGROUNDCOLOR);
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
		JMenuItem quitter = new JMenuItem("<HTML><U>Q</U>uitter</HTML>");
		quitter.setBackground(BACKGROUNDCOLOR);
		quitter.setActionCommand("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		return quitter;
	}

	private JMenuItem creationMenuSuppresion() {
		String nom = "Supprimer pixel(s)";
		JMenuItem suppression = new JMenuItem(nom);
		suppression.setBackground(BACKGROUNDCOLOR);
		suppression.setIcon(new ImageIcon(getClass().getResource("crop.png")));
		suppression.setActionCommand(nom);
		suppression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog(null, "Combien de pixel(s) voulez-vous supprimer ?", nom, JOptionPane.QUESTION_MESSAGE);
				boiteChargement = new BoiteChargement(getJFrame(suppression), nom);
				controlleur.supprimerDesPixels(nombre);
			}
		});
		return suppression;
	}

	private JMenuItem creationMenuDessiner() {
		String nom = "Supprimer/dessiner pixel(s)";
		JMenuItem dessin = new JMenuItem(nom);
		dessin.setBackground(BACKGROUNDCOLOR);
		dessin.setIcon(new ImageIcon(getClass().getResource("pencil-edit-button.png")));
		dessin.setActionCommand(nom);
		dessin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog(null, "Combien de pixel(s) voulez-vous supprimer/dessiner ?", nom, JOptionPane.QUESTION_MESSAGE);
				boiteChargement = new BoiteChargement(getJFrame(dessin), nom);
				controlleur.dessinerDesPixels(nombre);
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

	private JMenuItem creationMenuZoomAvant() {
		JMenuItem zoomAvant = new JMenuItem("Zoom avant");
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
		JMenuItem zoomArriere = new JMenuItem("Zoom arrière");
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
		JMenuItem ajuster = new JMenuItem("Ajuster à la fenêtre");
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
		JMenuItem tailleReelle = new JMenuItem("Taille réelle");
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

	private JMenuItem creationMenuGithub() {
		String nom = "Lien vers GitHub";
		JMenuItem github = new JMenuItem(nom);
		github.setBackground(BACKGROUNDCOLOR);
		github.setIcon(new ImageIcon(getClass().getResource("github-logo.png")));
		github.setActionCommand(nom);
		github.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/quentinmrzt/TriMe"));
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		return github;
	}

	public void miseAJour(Modelisation modelisation) {
		if (boiteChargement != null) {
			boiteChargement.miseAJour(modelisation);
		}
	}
}
