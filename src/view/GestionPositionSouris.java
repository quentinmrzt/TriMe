package view;

import java.awt.Point;
import java.util.Observable;

public class GestionPositionSouris extends Observable {

	private Point position;

	public GestionPositionSouris() {
		position = new Point(0, 0);
	}

	public int getPositionX() {
		return position != null ? (int) position.getX() : 0;
	}

	public int getPositionY() {
		return position != null ? (int) position.getY() : 0;
	}

	public void setPosition(Point point) {
		position = point;
		setChanged();
		notifyObservers("setPosition");
	}
}
