package view;

import static java.awt.Color.WHITE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Image;
import model.Modelisation;
import view.menu.Menu;

public class Fenetre extends JFrame implements Observer {

	private final Color BACKGROUNDCOLOR = Color.WHITE;
	private final String NOMAPPLICATION = "Trim-Me";

	private Menu menu;
	private ScrollImage scrollImage;
	private PanelInformations zoneInformations;
	private BarreActions barreActions;

	public Fenetre(Modelisation modelisation, Controller controller) {
		super();
		setName(NOMAPPLICATION);
		menu = new Menu(modelisation, controller);
		setJMenuBar(menu);
		build();
		setContentPane(buildContentPane(controller));
		addKeyListener(new ControleClavier(scrollImage));
		setVisible(true);
	}

	private void build() {
		setTitle(NOMAPPLICATION);
		setBackground(BACKGROUNDCOLOR);
		setMinimumSize(new Dimension(500, 300));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage()); // https://www.flaticon.com/
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private GridBagConstraints contrainteBarre() {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.anchor = GridBagConstraints.CENTER;
		contrainte.weightx = 1;
		contrainte.weighty = 0;
		return contrainte;
	}

	private GridBagConstraints contrainteImage() {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = 1;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.CENTER;
		contrainte.weightx = 1;
		contrainte.weighty = 1;
		return contrainte;
	}

	private GridBagConstraints contrainteInformations() {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = 2;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(5, 0, 5, 0);
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.anchor = GridBagConstraints.CENTER;
		contrainte.weightx = 1;
		contrainte.weighty = 0;
		return contrainte;
	}

	private JPanel buildContentPane(Controller controller) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(WHITE);

		barreActions = new BarreActions(controller);
		panel.add(barreActions, contrainteBarre());

		scrollImage = new ScrollImage();
		panel.add(scrollImage, contrainteImage());

		zoneInformations = new PanelInformations();
		panel.add(zoneInformations, contrainteInformations());

		return panel;
	}

	private void miseAJour(Modelisation modelisation) {
		Image image = modelisation.getImage();
		if (image != null) {
			String indicateurModifie = modelisation.estModifie() ? "*" : "";
			setTitle(image.getNomFichier() + indicateurModifie + " - " + NOMAPPLICATION);
		} else {
			setTitle(NOMAPPLICATION);
		}
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof Modelisation) {
			Modelisation modelisation = (Modelisation) obs;
			miseAJour(modelisation);
			zoneInformations.miseAJour(modelisation);
			scrollImage.miseAJour(modelisation);
			barreActions.miseAJour(modelisation);
		}
		
		menu.update(obs, obj);
	}
}