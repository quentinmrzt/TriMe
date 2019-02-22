package view.boite;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import execution.Traitement;

public class BoiteSaisiePixels extends Boite {

	private final int LARGEUR = 400;
	private final int HAUTEUR = 120;

	private JLabel information;
	private JTextField saisie;
	private JButton valider;
	private boolean valide;

	public BoiteSaisiePixels(JFrame parent, String titre){
		super(parent, titre);
		build();
		valide = false;
		information = new JLabel("Combien de pixel(s) voulez-vous supprimer ?");

		saisie = new JFormattedTextField(NumberFormat.getInstance());

		add(information, contrainte(0));
		add(saisie, contrainte(1));
		add(creerBoutonValider(), contrainteBouton(0, 2));
		add(creerBoutonAnnuler(), contrainteBouton(1, 2));

		valider.requestFocusInWindow();
		setVisible(true);
	}

	public String getSaisie() {
		return saisie.getText();
	}

	public boolean estValide() {
		return valide;
	}

	private GridBagConstraints contrainte(int positionY) {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = positionY;
		contrainte.gridwidth = GridBagConstraints.REMAINDER;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(1, 5, 1, 5);
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.LINE_START;
		contrainte.weightx = 1.0;
		contrainte.weighty = 0.0;
		return contrainte;
	}

	private GridBagConstraints contrainteBouton(int positionX, int positionY) {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = positionX;
		contrainte.gridy = positionY;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(5, 5, 5, 5);
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.LINE_START;
		contrainte.weightx = 1.0;
		contrainte.weighty = 0.0;
		return contrainte;
	}

	private JButton creerBoutonValider() {
		valider = new JButton("Valider");  
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				valider();

				if (valide) {
					dispose();
				} else {
					saisie.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
			}
		});
		return valider;
	}

	public void valider() {
		int nombre = conversionNombre(getSaisie());
		valide = nombreValide(nombre);
	}

	private int conversionNombre(String nombre) {
		try {
			return Integer.parseInt(nombre);
		} catch (Exception e) {
			return -1;
		}
	}

	private boolean nombreValide(int nombre) {
		return nombre > 0;
	}

	private JButton creerBoutonAnnuler() {
		JButton annuler = new JButton("Annuler");    
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		return annuler;
	}

	private void build() {
		setLayout(new GridBagLayout());
		setSize(LARGEUR, HAUTEUR);
		setResizable(false);
	}

	public void miseAJour(Traitement traitement) {

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
