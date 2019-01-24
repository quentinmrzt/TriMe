package view;

import static java.awt.Color.WHITE;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;

public class Fenetre extends JFrame {

	private final int LARGEURFENETRE = 1000;
	private final int HAUTEURFENETRE = 600;

	private PanelImage zoneImage;
	private ScrollInformations zoneScrollInformations;

	public Fenetre(Controller controller) {
		super();
		setJMenuBar(new Menu(controller));
		build();
		setVisible(true);

	}

	public PanelImage getZoneImage() {
		return zoneImage;
	}

	public ScrollInformations getScrollInformations() {
		return zoneScrollInformations;
	}

	private void build() {
		setTitle("Trim-Me");
		setSize(LARGEURFENETRE, HAUTEURFENETRE);
		setIconImage(new ImageIcon("icon.png").getImage());
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new ControleClavier());
		setContentPane(buildContentPane());
	}

	private GridBagConstraints contrainte(int positionX, double poidsX) {
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = positionX;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.insets = new Insets(10, 10, 10, 10);
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.anchor = GridBagConstraints.WEST;
		contrainte.weightx = poidsX;
		contrainte.weighty = 1.0;
		return contrainte;
	}

	private JPanel buildContentPane() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(WHITE);

		zoneImage = new PanelImage();
		panel.add(zoneImage, contrainte(0, 1.0));
		zoneScrollInformations = new ScrollInformations();
		panel.add(zoneScrollInformations, contrainte(1, 1.0));

		return panel;
	}
}