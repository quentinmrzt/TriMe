package view;

import static view.utils.CouleursConstantes.BACKGROUNDCOLOR;
import static view.utils.CouleursConstantes.TEXTECOLOR;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Modelisation;

public class InformationsImage extends JPanel implements Observer {

	private JLabel taille, position, zoom;

	public InformationsImage() {
		super();
		build();

		taille = new JLabel("");
		taille.setForeground(TEXTECOLOR);

		position = new JLabel("");
		position.setForeground(TEXTECOLOR);

		zoom = new JLabel("");
		zoom.setForeground(TEXTECOLOR);

		add(taille, contrainte(0));
		add(position, contrainte(1));
		add(zoom, contrainte(2));
	}

	private void build() {
		setName("InformationsImage");
		setLayout(new GridBagLayout());
		setBackground(BACKGROUNDCOLOR);
	}

	private GridBagConstraints contrainte(int positionX) {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = positionX;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(0, 10, 0, 10);
		// contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.LINE_END;
		if (positionX == 0) {
			contrainte.weightx = 1.0;
		} else {
			contrainte.weightx = 0.0;
		}
		contrainte.weighty = 1.0;
		return contrainte;
	}

	public void miseAJour(Modelisation modelisation) {
		if (modelisation.getImage() != null) {
			taille.setText(formatageTaille(modelisation.getImage().getLargeur(), modelisation.getImage().getHauteur()));
			position.setText(formatagePosition(0, 0));
		} else {
			taille.setText("");
			position.setText("");
			zoom.setText("");
		}
	}

	private String formatageTaille(int largeur, int hauteur) {
		return largeur + " x " + hauteur;
	}

	private String formatagePosition(int x, int y) {
		return x + ", " + y;
	}

	private String formatageZoom(double valeur) {
		int pourcentage = (int) (valeur * 100);
		return pourcentage + "%";
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof GestionEchelleImage) {
			GestionEchelleImage gestionEchelleImage = (GestionEchelleImage) obs;
			zoom.setText(formatageZoom(gestionEchelleImage.getEchelle()));
		} else if (obs instanceof GestionPositionSouris) {
			GestionPositionSouris gestionPositionSouris = (GestionPositionSouris) obs;
			position.setText(formatagePosition(gestionPositionSouris.getPositionX(), gestionPositionSouris.getPositionY()));
		}
	}
}
