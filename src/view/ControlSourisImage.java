package view;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ControlSourisImage implements MouseWheelListener {

	private boolean ctrlEstPresse;

	public ControlSourisImage() {
		ctrlEstPresse = false;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		if (ctrlEstPresse) {
			boolean zoom = event.getWheelRotation() < 0;

			if (zoom) {
				System.out.println("ZOOM");
			} else {
				System.out.println("DEZOOM");
			}
		}
	}
}
