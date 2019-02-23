package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ControleSourisImage implements MouseMotionListener {

	private PanelImage panelImage;
	private GestionPositionSouris gestionPositionSouris;
	
	public ControleSourisImage(PanelImage panelImage, GestionPositionSouris gestionPositionSouris) {
		this.panelImage = panelImage;
		this.gestionPositionSouris = gestionPositionSouris;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gestionPositionSouris.setPosition(panelImage.getMousePosition());
	}
}
