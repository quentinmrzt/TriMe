package view;

import static java.awt.Color.WHITE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Image;
import model.Modelisation;

public class Fenetre extends JFrame implements Observer {

	private final Color BACKGROUNDCOLOR = Color.WHITE;
	private final String NOMAPPLICATION = "Trim-Me";

	private Modelisation modelisation;
	private Menu menu;
	private PanelImage zoneImage;
	private PanelInformations zoneInformations;
	private BarreActions barreActions;

	public Fenetre(Modelisation modelisation, Controller controller) {
		super();
		this.modelisation = modelisation;
		menu = new Menu(controller);
		setJMenuBar(menu);
		build();
		setContentPane(buildContentPane(controller));
		setVisible(true);
	}

	private void build() {
		setTitle(NOMAPPLICATION);
		setBackground(BACKGROUNDCOLOR);
		setMinimumSize(new Dimension(500, 300));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setIconImage(new ImageIcon("images/icon.png").getImage()); // https://www.flaticon.com/
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new ControleClavier());
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
		zoneImage = new PanelImage();
		panel.add(zoneImage, contrainteImage());
		zoneInformations = new PanelInformations();
		panel.add(zoneInformations, contrainteInformations());

		return panel;
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof Modelisation) {
			// maj photo
			if (obj instanceof Image) {
				Image image = (Image) obj;
				setTitle(image.getNomFichier() + " - " + NOMAPPLICATION);

				zoneInformations.miseAJour(modelisation);
				zoneImage.miseAJour(modelisation);
			}
		} else {
			// maj dessin
			menu.miseAJour(modelisation);
		}
	}
}