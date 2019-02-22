package view;

import static javax.swing.JFileChooser.APPROVE_OPTION;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import model.Modelisation;

public class BarreActions extends JPanel {

	private final Color BACKGROUNDCOLOR = Color.WHITE;
	private final Color BORDURECOLOR = new Color(180, 180, 180);
	private final int NOMBREBOUTON = 4;

	private Controller controlleur;
	private JButton sauvegarder, annuler, retablir;

	public BarreActions(Controller controlleur) {
		super();
		this.controlleur = controlleur;
		build();
		setName("BarreActions");

		add(creationBoutonOuvrir(), contrainte(0));
		add(creationBoutonSauvegarder(), contrainte(1));
		add(creationBoutonAnnuler(), contrainte(2));
		add(creationBoutonRetablir(), contrainte(3));
	}

	private void build() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDURECOLOR));
		setBackground(BACKGROUNDCOLOR);
	}

	private JButton creationBoutonOuvrir() {
		JButton ouvrir = new JButton(new ImageIcon(getClass().getResource("open-archive.png")));
		ouvrir.addMouseListener(new ControleSourisBouton(ouvrir));
		ouvrir.setFocusable(false) ;

		ouvrir.setToolTipText("Ouvrir");
		ouvrir.setBorder(new LineBorder(BACKGROUNDCOLOR, 1, true));
		ouvrir.setFocusPainted(false);
		ouvrir.setBackground(BACKGROUNDCOLOR);
		ouvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choisir();
			}
		});
		return ouvrir;
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

	private JButton creationBoutonSauvegarder() {
		String nom = "Sauvegarder";
		sauvegarder = new JButton(new ImageIcon(getClass().getResource("save.png")));
		sauvegarder.setEnabled(false);
		sauvegarder.setToolTipText(nom);
		sauvegarder.setBorder(new LineBorder(BACKGROUNDCOLOR, 1, true));
		sauvegarder.setFocusPainted(false);
		sauvegarder.setBackground(BACKGROUNDCOLOR);
		sauvegarder.setFocusable(false) ;
		sauvegarder.addMouseListener(new ControleSourisBouton(sauvegarder));
		sauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sauvegarder();
			}
		});
		return sauvegarder;
	}

	private JButton creationBoutonAnnuler() {
		String nom = "Annuler";
		annuler = new JButton(new ImageIcon(getClass().getResource("undo.png")));
		annuler.setEnabled(false);
		annuler.setToolTipText(nom);
		annuler.setBorder(new LineBorder(BACKGROUNDCOLOR, 1, true));
		annuler.setFocusPainted(false);
		annuler.setBackground(BACKGROUNDCOLOR);
		annuler.setFocusable(false) ;
		annuler.addMouseListener(new ControleSourisBouton(annuler));
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlleur.annuler();
			}
		});
		return annuler;
	}

	private void sauvegarder() {
		try {
			JFileChooser jf = new JFileChooser();
			jf.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures"));
			jf.setFileFilter(new FileNameExtensionFilter("JPEG (*.jpg)", "jpg"));
			jf.setFileFilter(new FileNameExtensionFilter("BMP (*.bmp)", "bmp"));
			jf.setFileFilter(new FileNameExtensionFilter("PNG (*.png)", "png"));
			jf.setDialogTitle("Enregistrer");
			if (jf.showSaveDialog(getParent()) == APPROVE_OPTION) {
				controlleur.sauvegarder(jf.getSelectedFile());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private JButton creationBoutonRetablir() {
		String nom = "Rétablir";
		retablir = new JButton(new ImageIcon(getClass().getResource("redo.png")));
		retablir.setEnabled(false);
		retablir.setToolTipText(nom);
		retablir.setBorder(new LineBorder(BACKGROUNDCOLOR, 1, true));
		retablir.setFocusPainted(false);
		retablir.setBackground(BACKGROUNDCOLOR);
		retablir.setFocusable(false) ;
		retablir.addMouseListener(new ControleSourisBouton(retablir));
		retablir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlleur.retablir();
			}
		});
		return retablir;
	}

	private GridBagConstraints contrainte(int positionX) {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = positionX;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(5, 5, 5, 2);
		if(positionX < NOMBREBOUTON-1) {
			contrainte.weightx = 0.0;
		} else {
			contrainte.anchor = GridBagConstraints.LINE_START;
			contrainte.weightx = 1.0;
		}
		contrainte.weighty = 0.0;
		return contrainte;
	}

	public void miseAJour(Modelisation modelisation) {
		sauvegarder.setEnabled(modelisation.getImage() != null);
		annuler.setEnabled(modelisation.getHistorique().peutAnnuler());
		retablir.setEnabled(modelisation.getHistorique().peutRetablir());
	}
}
