package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Modelisation;
import view.utils.CouleursConstantes;

public class PanelImage extends JPanel implements Observer {

	private BufferedImage image;
	private double echelle = 1;

	public PanelImage(GestionPositionSouris gestionPositionSouris) {
		super();
		setBackground(CouleursConstantes.ZONEIMAGECOLOR);
		addMouseMotionListener(new ControleSourisImage(this, gestionPositionSouris));
		setName("PanelImage");
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		recalculDeLaTaille();
		repaint();
	}

	private void recalculDeLaTaille() {
		int largeur = 0;
		int hauteur = 0;
		if (image != null) {
			largeur += image.getWidth() * echelle;
			hauteur += image.getHeight() * echelle;
		}
		Dimension taille = new Dimension(largeur, hauteur);
		setSize(taille);
		setMinimumSize(taille);
		setPreferredSize(taille);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		if (image != null) {
			Dimension taille = getSize();
			int panelLargeur = taille.width;
			int panelHauteur = taille.height;
			int nouvelleLargeur = (int) (image.getWidth() * echelle);
			int nouvelleHauteur = (int) (image.getHeight() * echelle);
			int x = (panelLargeur - nouvelleLargeur) / 2;
			int y = (panelHauteur - nouvelleHauteur) / 2;
			// System.out.println(x + " / " + y);
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			g2d.drawImage(image, x, y, nouvelleLargeur, nouvelleHauteur, null);
		}
	}

	public void miseAJour(Modelisation modelisation) {
		if (modelisation.getImage() != null) {
			setImage(modelisation.getImage().getBufferedImage());
		} else {
			setImage(null);
		}
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof GestionEchelleImage) {
			GestionEchelleImage gestionEchelleImage = (GestionEchelleImage) obs;
			echelle = gestionEchelleImage.getEchelle();
			recalculDeLaTaille();
			repaint();
		}
	}
}