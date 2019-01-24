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

import controller.Controller;

public class Menu extends JMenuBar {

	private Controller controlleur;
	private final Color BACKGROUNDCOLOR = Color.WHITE;

	public Menu(Controller controlleur) {
		this.controlleur = controlleur;

		setBackground(BACKGROUNDCOLOR);

		JMenu menu = new JMenu("Menu");
		creationMenuChoisir(menu);
		creationMenuQuitter(menu);
		add(menu);

		JMenu mode = new JMenu("Mode");
		creationMenuSuppresion(mode);
		creationMenuDebug(mode);
		add(mode);
	}

	private void creationMenuChoisir(JMenu menu) {
		JMenuItem choisir = new JMenuItem("Choisir une image");
		choisir.setBackground(BACKGROUNDCOLOR);
		choisir.setActionCommand("Choisir une image");
		choisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choisir();
			}
		});
		menu.add(choisir);
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

	private void creationMenuQuitter(JMenu menu) {
		JMenuItem quitter = new JMenuItem("<HTML><U>Q</U>uitter</HTML>");
		quitter.setBackground(BACKGROUNDCOLOR);
		quitter.setActionCommand("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(quitter);
	}

	private void creationMenuSuppresion(JMenu mode) {
		JMenuItem suppression = new JMenuItem("Supprimer pixel");
		suppression.setBackground(BACKGROUNDCOLOR);
		suppression.setActionCommand("Supprimer pixel");
		suppression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Supprimer pixel");
			}
		});
		mode.add(suppression);
	}

	private void creationMenuDebug(JMenu mode) {
		JMenuItem debug = new JMenuItem("Débuger");
		debug.setBackground(BACKGROUNDCOLOR);
		debug.setActionCommand("Débuger");
		debug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Débuger");
			}
		});
		mode.add(debug);
	}

}
