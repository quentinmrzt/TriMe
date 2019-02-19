package view;

import static java.awt.Color.WHITE;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import model.Modelisation;

public class ScrollInformations extends JScrollPane {

	private final int LARGEURFENETRE = 200;
	private final int HAUTEURFENETRE = 0;
	private PanelInformations zoneInformations;

	public ScrollInformations() {
		super();
		build();
		setName("ScrollInformations");
		zoneInformations = new PanelInformations();
		setViewportView(zoneInformations);
	}

	public PanelInformations getZoneInformations() {
		return zoneInformations;
	}

	private void build() {
		setBorder(BorderFactory.createTitledBorder("Informations"));
		setPreferredSize(new Dimension(LARGEURFENETRE, HAUTEURFENETRE));
		setBackground(WHITE);
	}
	
	public void miseAJour(Modelisation modelisation) {
		zoneInformations.miseAJour(modelisation);
	}
}
