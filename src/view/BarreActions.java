package view;

import static javax.swing.JFileChooser.APPROVE_OPTION;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import model.Modelisation;

public class BarreActions extends JPanel {

	private final int LARGEURPANEL = 0;
	private final int HAUTEURPANEL = 25;
	private final Color BACKGROUNDCOLOR = Color.WHITE;
	
	private Controller controlleur;
	private JButton sauvegarder;

	public BarreActions(Controller controlleur) {
		super();
		this.controlleur = controlleur;
		build();
		
		sauvegarder = new JButton(new ImageIcon("images/save.png"));
		sauvegarder.setSize(5, 25);
		sauvegarder.setFocusPainted(false);
		sauvegarder.setContentAreaFilled(false);

		add(creationBoutonOuvrir(), contrainteOuvrir());
		add(sauvegarder, contrainteSauvegarder());
	}

	private void build() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));
		setPreferredSize(new Dimension(LARGEURPANEL, HAUTEURPANEL));
		setBackground(BACKGROUNDCOLOR);
	}
	
	private JButton creationBoutonOuvrir() {
		JButton ouvrir = new JButton(new ImageIcon("images/open-archive.png"));
		ouvrir.setSize(new Dimension(50,50));
		//ouvrir.setPreferredSize(new Dimension(50,50));
		//ouvrir.setFocusPainted(false);
		ouvrir.setContentAreaFilled(false);
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


	private GridBagConstraints contrainteOuvrir() {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.weightx = 0.0;
		contrainte.weighty = 1.0;
		return contrainte;
	}
	
	private GridBagConstraints contrainteSauvegarder() {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 1;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.anchor = GridBagConstraints.LINE_START;
		contrainte.weightx = 1.0;
		contrainte.weighty = 1.0;
		return contrainte;
	}

	public void miseAJour(Modelisation modelisation) {
	}
}
