package view.menu;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;

import controller.Controller;
import model.Modelisation;

public class Menu extends JMenuBar implements Observer {

	private final Color BACKGROUNDCOLOR = Color.WHITE;

	private SousMenuFichier sousMenuFichier;
	private SousMenuEdition sousMenuEdition;

	public Menu(Modelisation modelisation, Controller controlleur) {
		super();

		setName("Menu");
		setBackground(BACKGROUNDCOLOR);
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180, 180, 180)));

		sousMenuFichier = new SousMenuFichier(controlleur);
		sousMenuEdition = new SousMenuEdition(modelisation, controlleur);

		add(sousMenuFichier);
		add(sousMenuEdition);
		add(new SousMenuAffichage());
		add(new SousMenuAide());
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof Modelisation) {
			Modelisation modelisation = (Modelisation) obs;
			sousMenuFichier.miseAJour(modelisation);
		}
		sousMenuEdition.update(obs, obj);
	}
}
