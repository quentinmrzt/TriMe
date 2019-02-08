package view;

import static java.awt.Color.WHITE;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Modelisation;

public class ScrollImage extends JScrollPane {

	//private final int LARGEURFENETRE = 200;
	//private final int HAUTEURFENETRE = 0;
	private PanelImage panelImage;

	public ScrollImage() {
		super();
		build();
		panelImage = new PanelImage();
		setViewportView(panelImage);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	}

	public PanelImage getZoneInformations() {
		return panelImage;
	}

	private void build() {
		//setPreferredSize(new Dimension(LARGEURFENETRE, HAUTEURFENETRE));
		setBackground(WHITE);
	}
	
	public void miseAJour(Modelisation modelisation) {
		panelImage.miseAJour(modelisation);
	}
}
