package algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graphe.Graphe;
import graphe.Noeud;

public class AlgoPerso {

	public static List<Noeud> executer(Graphe graphe) {		
		Element arrive = algorithme(graphe);

		List<Noeud> chemin = rechercheChemin(arrive);

		return chemin;
	}

	private static Element algorithme(Graphe graphe) {
		Elements elements = new Elements(graphe.getLargeur(), graphe.getHauteur());
		
		elements.add(new Element(null, graphe.getNoeudDepart()));
		iteration(-1, -1, graphe, elements);
		
		for (int y=0; y<elements.getHauteur(); y++) {
			for (int x=0; x<elements.getLargeur(); x++) {
				iteration(x, y, graphe, elements);
			}
		}

		return elements.getArrive();
	}
	
	private static void iteration(int x, int y, Graphe graphe, Elements elements) {
		Noeud noeud = graphe.getNoeud(x, y);
		Element element = elements.getElement(x, y);
		for (Noeud noeudFils : noeud.getFils()) {			
			int filsX = noeudFils.getX();
			int filsY = noeudFils.getY();
			Element nouvelleElement = new Element(element, noeudFils);
			if (elements.existe(filsX, filsY)) {
				Element elementDejaExistant = elements.getElement(filsX, filsY);
				if (nouvelleElement.getValeur() < elementDejaExistant.getValeur()) {
					elements.add(nouvelleElement);
				}
			} else {
				elements.add(nouvelleElement);
			}
		}
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
