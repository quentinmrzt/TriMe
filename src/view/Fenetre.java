package view;

import static java.awt.Color.WHITE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.Modelisation;

public class Fenetre extends JFrame implements Observer {

	private final int LARGEURFENETRE = 1000;
	private final int HAUTEURFENETRE = 600;

	private JPanel zoneImage, zoneInformations;
	private JLabel chemin, extension, hauteur, largeur;
	private JLabel image;

	public Fenetre(Controller controller) {
		super();

		chemin = new JLabel("Chemin: ");
		extension = new JLabel("Extension: ");
		hauteur = new JLabel("Hauteur: ");
		largeur = new JLabel("Largeur: ");

		image = new JLabel();

		build();
		setVisible(true);

		setJMenuBar(new Menu(controller));
	}

	private ImageIcon resize(String chemin) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(chemin));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int largeurVoulu = 650;
		int hauteurVariable = (largeurVoulu * img.getHeight()) / img.getWidth();
		Image dimg = img.getScaledInstance(largeurVoulu, hauteurVariable, Image.SCALE_SMOOTH);
		return new ImageIcon(dimg);
	}

	private void build() {
		setTitle("Trim-Me");
		setSize(LARGEURFENETRE, HAUTEURFENETRE);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
	}

	private GridBagConstraints contrainte(int positionX, int positionY, int largeur, int hauteur, double poidsX, double poidsY) {
		GridBagConstraints contrainte = new GridBagConstraints();

		contrainte.gridx = positionX;
		contrainte.gridy = positionY;

		contrainte.gridwidth = largeur;
		contrainte.gridheight = hauteur;

		contrainte.insets = new Insets(10, 10, 10, 10);
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.WEST;

		contrainte.weightx = poidsX;
		contrainte.weighty = poidsY;

		return contrainte;
	}

	private JPanel buildContentPane() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(WHITE);

		panel.add(getPanelImage(), 				contrainte(0, 0, 1, 1, 0.0, 1.0));
		panel.add(getPanelInformation(), 	contrainte(1, 0, 1, 1, 1.0, 1.0));

		return panel;
	}

	private JPanel getPanelInformation() {
		zoneInformations = new JPanel(new GridBagLayout());

		zoneInformations.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		zoneInformations.setBackground(WHITE);

		zoneInformations.add(chemin, 		contrainte(0, 0, 1, 1, 0.0, 0.0));
		zoneInformations.add(extension, contrainte(0, 1, 1, 1, 0.0, 0.0));
		zoneInformations.add(hauteur, 	contrainte(0, 2, 1, 1, 0.0, 0.0));
		zoneInformations.add(largeur, 	contrainte(0, 3, 1, 1, 0.0, 0.0));

		return zoneInformations;
	}

	private JPanel getPanelImage() {
		zoneImage = new JPanel(new GridBagLayout());
		zoneImage.setPreferredSize(new Dimension(650, 0));
		zoneImage.setBackground(WHITE);
		zoneImage.add(image, contrainte(0, 0, 1, 1, 1.0, 1.0));
		return zoneImage;
	}

	@Override
	public void update(Observable obs, Object obj) {
		// TODO: à voir si c'est la bonne méthode
		if (obs instanceof Modelisation) {
			Modelisation modelisation = (Modelisation) obs;
			chemin.setText("Chemin: " + modelisation.getImage().getChemin());
			extension.setText("Extension: " + modelisation.getImage().getExtension());
			hauteur.setText("Hauteur: " + modelisation.getImage().getHauteur());
			largeur.setText("Largeur: " + modelisation.getImage().getLargeur());
			
			image.setIcon(resize(modelisation.getImage().getChemin()));
		}
	}
}