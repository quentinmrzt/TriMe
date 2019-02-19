package view;

import static java.awt.Color.WHITE;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Modelisation;

public class ScrollImage extends JScrollPane {

	private PanelImage nouveauPanelImage;

	public ScrollImage() {
		super();
		build();
		setName("ScrollImage");
		nouveauPanelImage = new PanelImage();
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getVerticalScrollBar().setUnitIncrement(15);
		getHorizontalScrollBar().setUnitIncrement(15);
		addMouseWheelListener(new ControlSourisImage(nouveauPanelImage));
		setViewportView(nouveauPanelImage);
	}

	public PanelImage getPanelImage() {
		return nouveauPanelImage;
	}

	private void build() {
		setBackground(WHITE);
	}

	public void miseAJour(Modelisation modelisation) {
		nouveauPanelImage.miseAJour(modelisation);
	}
}
