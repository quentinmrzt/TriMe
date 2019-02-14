package view;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ControlSourisImage implements MouseWheelListener {
		
	private PanelImage map;
	
	public ControlSourisImage(PanelImage map) {
		this.map = map;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		boolean zoom = event.getWheelRotation() < 0;
		if (ControleClavier.isToucheControlActif()) {
			if (zoom) {
				map.setEchelle(map.getEchelle()+0.1);
			} else {
				map.setEchelle(map.getEchelle()-0.1);
			}
		}
	}
}
