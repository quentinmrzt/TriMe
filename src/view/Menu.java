package view;

import static javax.swing.JFileChooser.APPROVE_OPTION;

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
	
	public Menu(Controller controlleur) {
		this.controlleur = controlleur;
		
		JMenu menu = new JMenu("Menu");

		JMenuItem choisir = new JMenuItem("Choisir une image");
		choisir.setActionCommand("Choisir une image");
		choisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choisir();
			}
		});
		menu.add(choisir);

		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.setActionCommand("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(quitter);
		
		add(menu);

		JMenu mode = new JMenu("Mode");

		JMenuItem suppression = new JMenuItem("Supprimer pixel");
		suppression.setActionCommand("Supprimer pixel");
		suppression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlleur.controleDelete();
			}
		});
		mode.add(suppression);

		JMenuItem debug = new JMenuItem("Débuger");
		debug.setActionCommand("Débuger");
		debug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Débuger");
			}
		});
		mode.add(debug);
		
		add(mode);
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
}
