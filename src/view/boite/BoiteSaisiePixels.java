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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import execution.Traitement;

public class BoiteSaisiePixels extends JDialog {

	private final int LARGEUR = 400;
	private final int HAUTEUR = 130;

	private JLabel information, message;
	private JTextField saisie;
	private JButton valider;
	private boolean valide;

	public BoiteSaisiePixels(JFrame parent, String titre){
		super(parent, titre, true);
		build();

		valide = false;
		information = new JLabel("Combien de pixel(s) voulez-vous supprimer ?");
		message = new JLabel(" ");
		saisie = new JTextField();

		int milieuFenetreX = parent.getLocation().x + (parent.getWidth() / 2);
		int milieuFenetreY = parent.getLocation().y + (parent.getHeight() / 2);
		int moitieBoiteX = LARGEUR / 2;
		int moitieBoiteY = HAUTEUR / 2;
		setLocation(milieuFenetreX - moitieBoiteX, milieuFenetreY - moitieBoiteY);

		add(information, contrainte(0));
		add(saisie, contrainte(1));
		add(message, contrainte(2));
		add(creerBoutonValider(), contrainteBouton(0, 3));
		add(creerBoutonAnnuler(), contrainteBouton(1, 3));

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
				int nombre = conversionNombre(getSaisie());
				valide = nombreValide(nombre);

				if (valide) {
					fermer(event);
				} else {
					afficherMessageErreur("Le nombre n'est pas valide !");
				}
			}
		});
		return valider;
	}
	
	private void afficherMessageErreur(String erreur) {
		saisie.setText("<html><p style='color:red;'>" + saisie.getText() + "</p></html>");
		message.setText("<html><p style='color:red;'>" + erreur + "</p></html>");
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
				fermer(event);
			}
		});
		return annuler;
	}

	private void fermer(ActionEvent event) {
		Window window = SwingUtilities.windowForComponent((Component)event.getSource());
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

	}
}
