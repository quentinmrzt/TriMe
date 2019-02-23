package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Modelisation;
import view.utils.CouleursConstantes;

public class PiedDePage extends JPanel {

	private JLabel chemin;
	private InformationsImage informations;

	public PiedDePage() {
		super();
		build();
		setName("PanelInformations");

		chemin = new JLabel("Chemin: ...");
		chemin.setForeground(CouleursConstantes.TEXTECOLOR);

		informations = new InformationsImage();

		add(chemin, contrainte(0));
		add(informations, contrainte(1));
	}

	public InformationsImage getInformationsImage() {
		return informations;
	}

	private void build() {
		setLayout(new GridBagLayout());
		setBackground(CouleursConstantes.BACKGROUNDCOLOR);
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
		if (modelisation.getImage() != null) {
			chemin.setText("Chemin: " + modelisation.getImage().getChemin());
		} else {
			chemin.setText("Chemin: ...");
		}
		informations.miseAJour(modelisation);
	}
}
