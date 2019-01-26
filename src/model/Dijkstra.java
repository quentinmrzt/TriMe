package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graphe.Graphe;
import graphe.Noeud;

public class Dijkstra {

	public static List<Noeud> executer(Graphe graphe) {		
		Element arrive = algorithmeDijkstra(graphe);

		List<Noeud> chemin = rechercheChemin(arrive);

		return chemin;
	}

	private static Element algorithmeDijkstra(Graphe graphe) {
		Elements elements = new Elements(graphe.getLargeur(), graphe.getHauteur());

		Element parent = new Element(null, graphe.getNoeudDepart());

		elements.add(parent);

		while (parent != null) {
			ajoutDesFilsATraiter(parent, elements);
			parent = elements.lePlusPetit();
		}

		return elements.getArrive();
	}

	private static void ajoutDesFilsATraiter(Element parent, Elements elements) {
		Noeud noeudParent = parent.getNoeudCourant();
		for (Noeud noeudFils : noeudParent.getFils()) {
			int x = noeudFils.getX();
			int y = noeudFils.getY();
			
			if (elements.existe(x, y)) {
				if (!elements.getElement(x, y).estFini()) {
					Element nouvelleElement = new Element(parent, noeudFils);
					Element elementDejaExistant = elements.getElement(x, y);

					if (nouvelleElement.getValeur() < elementDejaExistant.getValeur()) {
						elements.add(nouvelleElement);
					}
				}
			} else {
				elements.add(new Element(parent, noeudFils));
			}
		}

		parent.setFini();
	}


	private static List<Noeud> rechercheChemin(Element arrive) {
		List<Noeud> chemin = new ArrayList<Noeud>();
		Element courant = arrive.getParent();
		while (!courant.getNoeudCourant().equals(Noeud.getNoeudDepart())) {
			chemin.add(courant.getNoeudCourant());
			courant = courant.getParent();
		}
		Collections.reverse(chemin);
		return chemin;
	}
}
