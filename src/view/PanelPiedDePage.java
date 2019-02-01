package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class PanelPiedDePage extends JPanel {
	private final int LARGEURPANEL = 0;
	private final int HAUTEURPANEL = 30;

	private BarreDeChargement barreDeChargement;
	
	public PanelPiedDePage() {
		super();
		build();
		barreDeChargement = new BarreDeChargement();
		add(barreDeChargement, contrainte());
	}
	
	private void build() {
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(LARGEURPANEL, HAUTEURPANEL));
		setBackground(Color.WHITE);
	}

	private GridBagConstraints contrainte() {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.LINE_END;
		contrainte.weightx = 1.0;
		contrainte.weighty = 1.0;
		return contrainte;
	}

	public BarreDeChargement getBarreDeChargement() {
		return barreDeChargement;
	}
}
