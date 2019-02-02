package view;

import static java.awt.Color.WHITE;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Modelisation;

public class PanelInformations extends JPanel {

	private JLabel chemin, extension, hauteur, largeur;

	public PanelInformations() {
		super();
		build();

		chemin = new JLabel("Chemin: ...");
		extension = new JLabel("Extension: ...");
		hauteur = new JLabel("Hauteur: ...");
		largeur = new JLabel("Largeur: ...");

		add(chemin, contrainte(0));
		add(extension, contrainte(1));
		add(hauteur, contrainte(2));
		add(largeur, contrainte(3));
	}

	private void build() {
		setLayout(new GridBagLayout());
		setBackground(WHITE);
	}

	private GridBagConstraints contrainte(int positionY) {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = positionY;
		contrainte.gridwidth = GridBagConstraints.REMAINDER;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(10, 10, 10, 10);
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.LINE_START;
		contrainte.weightx = 1.0;
		contrainte.weighty = 0.0;
		return contrainte;
	}

	public void miseAjour(Modelisation modelisation) {
		chemin.setText("Chemin: " + modelisation.getImage().getChemin());
		extension.setText("Extension: " + modelisation.getImage().getExtension());
		hauteur.setText("Hauteur: " + modelisation.getImage().getHauteur());
		largeur.setText("Largeur: " + modelisation.getImage().getLargeur());
	}
}
