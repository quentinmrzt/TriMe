package view;

import static java.awt.Color.WHITE;

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

public class Fenetre extends JFrame implements Observer {

	private final int LARGEURFENETRE = 1000;
	private final int HAUTEURFENETRE = 600;

	private Modelisation modelisation;
	private Menu menu;
	private PanelImage zoneImage;
	private ScrollInformations zoneScrollInformations;
	private PanelPiedDePage zonePiedDePage;

	public Fenetre(Modelisation modelisation, Controller controller) {
		super();
		this.modelisation = modelisation;
		menu = new Menu(controller);
		setJMenuBar(menu);
		build();
		setVisible(true);

	}

	private void build() {
		setTitle("Trim-Me");
		setSize(LARGEURFENETRE, HAUTEURFENETRE);
		setIconImage(new ImageIcon("images/icon.png").getImage());
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new ControleClavier());
		setContentPane(buildContentPane());
	}

	private GridBagConstraints contrainte(int positionX, int positionY, int tailleX, double poidsX, double poidsY) {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = positionX;
		contrainte.gridy = positionY;
		contrainte.gridwidth = tailleX;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(10, 10, 10, 10);
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.WEST;
		contrainte.weightx = poidsX;
		contrainte.weighty = poidsY;
		return contrainte;
	}

	private JPanel buildContentPane() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(WHITE);

		zoneImage = new PanelImage();
		panel.add(zoneImage, contrainte(0, 0, 1, 1.0, 1.0));
		zoneScrollInformations = new ScrollInformations();
		panel.add(zoneScrollInformations, contrainte(1, 0, 1, 1.0, 1.0));


		zonePiedDePage = new PanelPiedDePage();
		//panel.add(zonePiedDePage, contrainte(0, 1, 2, 1.0, 0.0));

		return panel;
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof Modelisation) {
			// maj photo
			if (obj instanceof Image) {
				zoneScrollInformations.miseAJour(modelisation);
				zoneImage.miseAJour(modelisation);
			}
		} else {
			// maj dessin
			menu.miseAJour(modelisation);
		}
	}
}