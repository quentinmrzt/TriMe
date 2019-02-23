package view;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Modelisation;
import view.utils.CouleursConstantes;

public class ScrollImage extends JScrollPane {

	private PanelImage nouveauPanelImage;

	public ScrollImage(GestionEchelleImage gestionEchelleImage, GestionPositionSouris gestionPositionSouris) {
		super();
		build();
		setName("ScrollImage");
		nouveauPanelImage = new PanelImage(gestionPositionSouris);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getVerticalScrollBar().setUnitIncrement(15);
		getHorizontalScrollBar().setUnitIncrement(15);
		addMouseWheelListener(new ControleRouletteSourisImage(gestionEchelleImage));
		setViewportView(nouveauPanelImage);
	}

	public PanelImage getPanelImage() {
		return nouveauPanelImage;
	}

	private void build() {
		setBackground(CouleursConstantes.BACKGROUNDCOLOR);
	}

	public void miseAJour(Modelisation modelisation) {
		nouveauPanelImage.miseAJour(modelisation);
	}
}
