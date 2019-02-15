package view.boite;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import execution.Traitement;

public class BoiteChargement extends JDialog {

	private final int LARGEUR = 400;
	private final int HAUTEUR = 120;

	private JLabel information;
	private BarreDeChargement barreDeChargement;
	private JButton annuler;

	public BoiteChargement(JFrame parent, String titre){
		super(parent, titre, false);
		build();

		int pourcentage = 0;
		information = new JLabel("Suppression des pixels... "+pourcentage+"%");

		barreDeChargement = new BarreDeChargement();

		annuler = new JButton("Annuler");    
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer((Component)e.getSource());
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

	private void fermer(Component component) {
		Window window = SwingUtilities.windowForComponent(component);
		window.dispose();
	}

	private void build() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		setSize(LARGEUR, HAUTEUR);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public void miseAJour(Traitement traitement) {
		information.setText("Suppression des pixels... "+traitement.getIteration()+"%");
		barreDeChargement.miseAJour(traitement);
		if(traitement.getIteration() == Traitement.MAXIMUM) {
			this.dispose();
		}
	}
}
