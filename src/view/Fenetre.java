package view;

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
import view.utils.CouleursConstantes;

public class Fenetre extends JFrame implements Observer {

	private final String NOMAPPLICATION = "Trim-Me";

	private Menu menu;
	private ScrollImage scrollImage;
	private PiedDePage piedDePage;
	private BarreActions barreActions;

	public GestionEchelleImage gestionEchelleImage;
	public GestionPositionSouris gestionPositionSouris;

	public Fenetre(Modelisation modelisation, Controller controller) {
		super();
		build();

		gestionEchelleImage = new GestionEchelleImage();
		gestionPositionSouris = new GestionPositionSouris();

		creationMenu(modelisation, controller);
		creationContenu(controller);

		gestionEchelleImage.addObserver(scrollImage.getPanelImage());
		gestionEchelleImage.addObserver(piedDePage.getInformationsImage());
		gestionPositionSouris.addObserver(piedDePage.getInformationsImage());

		addKeyListener(new ControleClavier(scrollImage));
		setVisible(true);
	}

	public PanelImage getPanelImage() {
		return scrollImage.getPanelImage();
	}

	private void build() {
		setTitle(NOMAPPLICATION);
		setName(NOMAPPLICATION);
		setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		setMinimumSize(new Dimension(500, 300));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage()); // https://www.flaticon.com/
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void creationMenu(Modelisation modelisation, Controller controller) {
		menu = new Menu(modelisation, controller, gestionEchelleImage);
		setJMenuBar(menu);
	}

	private void creationContenu(Controller controller) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		barreActions = new BarreActions(controller);
		scrollImage = new ScrollImage(gestionEchelleImage, gestionPositionSouris);
		piedDePage = new PiedDePage();
		panel.add(barreActions, contrainteBarre());
		panel.add(scrollImage, contrainteImage());
		panel.add(piedDePage, contrainteInformations());
		setContentPane(panel);
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
			piedDePage.miseAJour(modelisation);
			scrollImage.miseAJour(modelisation);
			barreActions.miseAJour(modelisation);
			if (obj.equals("chargerImage")) {
				gestionEchelleImage.tailleReelle();
			}
		}
		menu.update(obs, obj);
	}
}