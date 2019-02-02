package view;

import static javax.swing.JFileChooser.APPROVE_OPTION;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Controller;

public class Menu extends JMenuBar {

	private Controller controlleur;
	private final Color BACKGROUNDCOLOR = Color.WHITE;

	public Menu(Controller controlleur) {
		this.controlleur = controlleur;

		setBackground(BACKGROUNDCOLOR);

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
			jf.setCurrentDirectory(
					new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures"));
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
		JMenuItem suppression = new JMenuItem("Supprimer pixel(s)");
		suppression.setBackground(BACKGROUNDCOLOR);
		suppression.setActionCommand("Supprimer pixel");
		suppression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    String nombre = JOptionPane.showInputDialog(null, "Combien de pixel(s) voulez-vous supprimer ?", "Supprimer pixel(s)", JOptionPane.QUESTION_MESSAGE);				
				controlleur.supprimerDesPixels(nombre);
			}
		});
		return suppression;
	}
	
	private JMenuItem creationMenuDessiner() {
		JMenuItem dessin = new JMenuItem("Supprimer/dessiner pixel(s)");
		dessin.setBackground(BACKGROUNDCOLOR);
		dessin.setActionCommand("Supprimer/dessiner pixel(s)");
		dessin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    String nombre = JOptionPane.showInputDialog(null, "Combien de pixel(s) voulez-vous supprimer/dessiner ?", "Supprimer/dessiner pixel(s)", JOptionPane.QUESTION_MESSAGE);				
				controlleur.dessinerDesPixels(nombre);
			}
		});
		return dessin;
	}
	

	private JMenuItem creationMenuRotation() {
		JMenuItem rotation = new JMenuItem("Faire une rotation");
		rotation.setBackground(BACKGROUNDCOLOR);
		rotation.setActionCommand("Faire une rotation");
		rotation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    String nombre = JOptionPane.showInputDialog(null, "Une rotation de combien de degré(s) ?", "Faire une rotation", JOptionPane.QUESTION_MESSAGE);				
				controlleur.rotation(nombre);
			}
		});
		return rotation;
	}

}
