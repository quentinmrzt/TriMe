package view;

import static java.awt.Color.WHITE;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Modelisation;

public class ScrollImage extends JScrollPane {

	private PanelImage panelImage;

	public ScrollImage() {
		super();
		build();
		panelImage = new PanelImage();
		setViewportView(panelImage);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		addMouseWheelListener(new ControlSourisImage(this));
	}

	public PanelImage getPanelImage() {
		return panelImage;
	}

	private void build() {
		setBackground(WHITE);
	}

	public void miseAJour(Modelisation modelisation) {
		panelImage.miseAJour(modelisation);
	}
}
