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

public class BarreActions extends JPanel {

	private final int LARGEURPANEL = 0;
	private final int HAUTEURPANEL = 30;
	private final Color BACKGROUNDCOLOR = Color.WHITE;

	private Controller controlleur;

	public BarreActions(Controller controlleur) {
		super();
		this.controlleur = controlleur;
		build();

		add(creationBoutonOuvrir(), contrainteOuvrir());
		add(creationBoutonSauvegarder(), contrainteSauvegarder());
	}

	private void build() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));
		setPreferredSize(new Dimension(LARGEURPANEL, HAUTEURPANEL));
		setBackground(BACKGROUNDCOLOR);
	}

	private JButton creationBoutonOuvrir() {
		JButton ouvrir = new JButton(new ImageIcon(getClass().getResource("open-archive.png")));
		ouvrir.addMouseListener(new ControleSourisBouton(ouvrir));
		ouvrir.setPreferredSize(new Dimension(25, 25));
		ouvrir.setBorder(null);
		ouvrir.setFocusPainted(false);
		ouvrir.setBackground(Color.WHITE);
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
		contrainte.weighty = 0.0;
		return contrainte;
	}

	private JButton creationBoutonSauvegarder() {
		
		JButton sauvegarder = new JButton(new ImageIcon(getClass().getResource("save.png")));
		sauvegarder.setPreferredSize(new Dimension(25, 25));
		sauvegarder.setBorder(null);
		sauvegarder.setFocusPainted(false);
		sauvegarder.setBackground(Color.WHITE);
		sauvegarder.addMouseListener(new ControleSourisBouton(sauvegarder));
		return sauvegarder;
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
}
