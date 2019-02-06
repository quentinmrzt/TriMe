package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Modelisation;

public class PanelInformations extends JPanel {

	private final int LARGEURPANEL = 0;
	private final int HAUTEURPANEL = 25;
	private final Color BACKGROUNDCOLOR = Color.WHITE;
	
	private JLabel chemin, dimension;

	public PanelInformations() {
		super();
		build();

		chemin = new JLabel("Chemin: ...");
		dimension = new JLabel("");

		add(chemin, contrainte(0));
		add(dimension, contrainte(1));
	}

	private void build() {
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(LARGEURPANEL, HAUTEURPANEL));
		setBackground(BACKGROUNDCOLOR);
	}

	private GridBagConstraints contrainte(int positionX) {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = positionX;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.LINE_START;
		contrainte.weightx = 1.0;
		contrainte.weighty = 1.0;
		return contrainte;
	}

	public void miseAJour(Modelisation modelisation) {
		chemin.setText("Chemin: " + modelisation.getImage().getChemin());
		dimension.setText(modelisation.getImage().getHauteur() + "x" + modelisation.getImage().getLargeur());
	}
}
