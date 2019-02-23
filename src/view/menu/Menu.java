package view.menu;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;

import controller.Controller;
import model.Modelisation;
import view.utils.CouleursConstantes;

public class Menu extends JMenuBar implements Observer {

	private SousMenuFichier sousMenuFichier;
	private SousMenuEdition sousMenuEdition;
	private SousMenuAffichage sousMenuAffichage;

	public Menu(Modelisation modelisation, Controller controlleur) {
		super();

		setName("Menu");
		setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(180, 180, 180)));

		sousMenuFichier = new SousMenuFichier(controlleur);
		sousMenuEdition = new SousMenuEdition(modelisation, controlleur);
		sousMenuAffichage = new SousMenuAffichage();

		add(sousMenuFichier);
		add(sousMenuEdition);
		add(sousMenuAffichage);
		add(new SousMenuAide());
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof Modelisation) {
			Modelisation modelisation = (Modelisation) obs;
			sousMenuFichier.miseAJour(modelisation);
			sousMenuAffichage.miseAJour(modelisation);
		}
		sousMenuEdition.update(obs, obj);
	}
}
