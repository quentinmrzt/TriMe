package view;

import static java.awt.Color.WHITE;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class ScrollInformations extends JScrollPane {

	private final int LARGEURFENETRE = 200;
	private final int HAUTEURFENETRE = 0;
	private PanelInformations zoneInformations;

	public ScrollInformations() {
		super();
		build();
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
}