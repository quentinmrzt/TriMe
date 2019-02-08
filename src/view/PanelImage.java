package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Modelisation;

public class PanelImage extends JPanel {

	private final Color COULEURFOND = new Color(230, 230, 230);
	private final int LARGEURIMAGEMAX = 600;
	private final int HAUTEURIMAGEMAX = 450;

	private JLabel image;

	public PanelImage() {
		super();
		build();
		image = new JLabel();
		image.setIcon(null);
		add(image, contrainte());
	}

	private void build() {
		setPreferredSize(new Dimension(2000, 2000));
		setLayout(new GridBagLayout());
		setBackground(COULEURFOND);
	}

	private GridBagConstraints contrainte() {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.anchor = GridBagConstraints.CENTER;
		contrainte.weightx = 1.0;
		contrainte.weighty = 1.0;
		return contrainte;
	}

	public void miseAJour(Modelisation modelisation) {
		image.setIcon(new ImageIcon(modelisation.getImage().getBufferedImage()));
		//image.setIcon(resize(modelisation.getImage().getBufferedImage()));
	}

	private ImageIcon resize(BufferedImage img) {
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
