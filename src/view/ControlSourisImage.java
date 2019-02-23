package view;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ControlSourisImage implements MouseWheelListener {
		
	private PanelImage panelImage;
	
	public ControlSourisImage(PanelImage map) {
		this.panelImage = map;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		boolean zoom = event.getWheelRotation() < 0;
		if (ControleClavier.isToucheControlActif()) {
			if (zoom) {
				panelImage.setEchelle(panelImage.getEchelle()+0.1);
			} else {
				panelImage.setEchelle(panelImage.getEchelle()-0.1);
			}
		}
	}
}
