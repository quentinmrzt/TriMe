package view;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JScrollBar;

public class ControlSourisImage implements MouseWheelListener {

	private ScrollImage scrollImage;
	
	public ControlSourisImage(ScrollImage scrollImage) {
		this.scrollImage = scrollImage;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		boolean zoom = event.getWheelRotation() < 0;
		
		if (ControleClavier.isToucheControlActif()) {
			if (zoom) {
				System.out.println("ZOOM");
			} else {
				System.out.println("DEZOOM");
			}
		} else if (ControleClavier.isToucheShiftActif()) {
			JScrollBar horizontalScrollBar = scrollImage.getHorizontalScrollBar();
			if (zoom) {
				//horizontalScrollBar.setValue(horizontalScrollBar.getValue() - 40);
			} else {
				//horizontalScrollBar.setValue(horizontalScrollBar.getValue() + 40);
			}
		} else {
			JScrollBar verticalScrollBar = scrollImage.getVerticalScrollBar();
			if (zoom) {
				//verticalScrollBar.setValue(verticalScrollBar.getValue() - 20);
			} else {
				//verticalScrollBar.setValue(verticalScrollBar.getValue() + 20);
			}
		}
	}
}
