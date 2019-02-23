package view;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ControleRouletteSourisImage implements MouseWheelListener {

	private GestionEchelleImage gestionEchelleImage;

	public ControleRouletteSourisImage(GestionEchelleImage gestionEchelleImage) {
		this.gestionEchelleImage = gestionEchelleImage;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		boolean zoom = event.getWheelRotation() < 0;
		if (ControleClavier.isToucheControlActif()) {
			if (zoom) {
				gestionEchelleImage.zoom();
			} else {
				gestionEchelleImage.dezoom();
			}
		}
	}
}
