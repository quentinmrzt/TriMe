package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Modelisation;

public class PanelImage extends JPanel {

	private final int LARGEURPANEL = 700;
	private final int HAUTEURPANEL = 0;

	private final int LARGEURIMAGEMAX = 600;
	private final int HAUTEURIMAGEMAX = 450;

	private JLabel image;

	public PanelImage() {
		super();
		build();
		image = new JLabel();
		add(image, contrainte());
	}

	private void build() {
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(LARGEURPANEL, HAUTEURPANEL));
		setBorder(BorderFactory.createTitledBorder("Image"));
		setBackground(Color.WHITE);
	}

	private GridBagConstraints contrainte() {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(10, 10, 10, 10);
		//contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.CENTER;
		contrainte.weightx = 1.0;
		contrainte.weighty = 1.0;
		return contrainte;
	}

	public void miseAJour(Modelisation modelisation) {
		image.setIcon(resize(modelisation.getImage().getChemin()));
	}

	private ImageIcon resize(String chemin) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(chemin));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int largeur = 0;
		int hauteur = 0;

		if (img.getWidth() > img.getHeight()) {
			largeur = LARGEURIMAGEMAX;
			hauteur = (LARGEURIMAGEMAX * img.getHeight()) / img.getWidth();
		} else {
			largeur = (HAUTEURIMAGEMAX * img.getWidth()) / img.getHeight();
			hauteur = HAUTEURIMAGEMAX;
		}

		return new ImageIcon(img.getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH));
	}
}
