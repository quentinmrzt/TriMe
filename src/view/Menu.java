package view;

import static javax.swing.JFileChooser.APPROVE_OPTION;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

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

		boiteChargement = null;
		
		JMenu menu = new JMenu("Menu");
		menu.add(creationMenuChoisir());
		menu.add(creationMenuQuitter());
		add(menu);

		JMenu mode = new JMenu("Mode");
		mode.add(creationMenuSuppresion());
		mode.add(creationMenuDessiner());
		add(mode);
	}

	private JMenuItem creationMenuChoisir() {
		JMenuItem choisir = new JMenuItem("Choisir une image");
		choisir.setBackground(BACKGROUNDCOLOR);
		choisir.setActionCommand("Choisir une image");
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
			jf.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures"));
			jf.setApproveButtonText("Ouvrir");
			jf.setDialogTitle("Choisir une image");
			if (jf.showSaveDialog(getParent()) == APPROVE_OPTION) {
				controlleur.controleCheminImage(jf.getSelectedFile());
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

	public void miseAJour(Modelisation modelisation) {
		if (boiteChargement != null) {
			boiteChargement.miseAJour(modelisation);
		}
	}
}
