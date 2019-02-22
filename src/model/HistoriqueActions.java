package model;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueActions {

	private int index;
	private List<Image> liste;

	public HistoriqueActions() {
		index = -1;
		liste = new ArrayList<Image>();
	}

	public void ajouterImage(Image image) {
		liste.add(image);
		index++;
	}

	public Image getImageCourant() {
		return liste.get(index);
	}

	public Image annuler() {
		return liste.get(--index);
	}

	public Image retablir() {
		return liste.get(++index);
	}

	public boolean peutAnnuler() {
		return index > 0;
	}

	public boolean peutRetablir() {
		return index < liste.size() - 1;
	}
}
