package view.boite;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import execution.Traitement;
import model.Modelisation;
import view.utils.CouleursConstantes;

public class BoiteChargement extends Boite implements Observer {

	private final int LARGEUR = 400;
	private final int HAUTEUR = 120;

	private Controller controlleur;

	private JLabel information;
	private BarreDeChargement barreDeChargement;
	private JButton annuler;

	public BoiteChargement(Modelisation modelisation, JFrame parent, String titre, Controller controller) {
		super(parent, titre);

		modelisation.addObserver(this);
		this.controlleur = controller;

		build();

		information = new JLabel("Suppression des pixels... 0%");
		information.setBackground(CouleursConstantes.BACKGROUNDCOLOR);

		barreDeChargement = new BarreDeChargement();

		annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlleur.annulerTraitement();
				dispose();
			}
		});

		add(information, contrainte(0));
		add(barreDeChargement, contrainte(1));
		add(annuler, contrainte(2));

		setVisible(true);
	}

	private GridBagConstraints contrainte(int positionY) {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = positionY;
		contrainte.gridwidth = GridBagConstraints.REMAINDER;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(5, 5, 5, 5);
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.LINE_START;
		contrainte.weightx = 1.0;
		contrainte.weighty = 0.0;
		return contrainte;
	}

	private void build() {
		setLayout(new GridBagLayout());
		setSize(LARGEUR, HAUTEUR);
		setResizable(false);
	}

	public void miseAJour(Traitement traitement) {
		information.setText("Suppression des pixels... " + traitement.getIteration() + "%");
		barreDeChargement.miseAJour(traitement);
		if (traitement.getIteration() == Traitement.MAXIMUM) {
			dispose();
		}
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof Traitement) {
			Traitement traitement = (Traitement) obs;

			information.setText("Suppression des pixels... " + traitement.getIteration() + "%");
			barreDeChargement.miseAJour(traitement);
			if (traitement.getIteration() == Traitement.MAXIMUM) {
				dispose();
			}
		}
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getHauteur() {
		return HAUTEUR;
	}
}
