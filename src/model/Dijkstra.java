package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graphe.Graphe;
import graphe.Noeud;

public class Dijkstra {

	public static List<Noeud> executer(Graphe graphe) {
		List<Element> estFini = new ArrayList<Element>();
		algorithmeDijkstra(graphe.getNoeudDepart(), estFini);
		Element arrive = rechercheArrive(estFini);
		return rechercheChemin(arrive);
	}

	private static void algorithmeDijkstra(Noeud depart, List<Element> estFini) {
		List<Element> aTraiter = new ArrayList<Element>();

		Element parent = new Element(null, depart);
		aTraiter.add(parent);

		while (parent != null) {
			ajoutDesFilsATraiter(aTraiter, estFini, parent);
			parent = rechercheDuPlusPetit(aTraiter);
		}
	}

	private static void ajoutDesFilsATraiter(List<Element> aTraiter, List<Element> estFini, Element parent) {
		Noeud noeudParent = parent.getNoeudCourant();
		for (Noeud noeudFils : noeudParent.getFils()) {
			Element element = new Element(parent, noeudFils);
			if (!estDejaTraite(estFini, element)) {
				int index = aTraiter.indexOf(element);
				if (index != -1) {
					Element elementIdentique = aTraiter.get(index);
					if (element.getValeur() < elementIdentique.getValeur()) {
						aTraiter.remove(index);
						aTraiter.add(element);
					}
				} else {
					aTraiter.add(element);
				}
			}
		}
		aTraiter.remove(parent);
		estFini.add(parent);
	}

	private static boolean estDejaTraite(List<Element> estFini, Element element) {
		return estFini.contains(element);
	}

	private static Element rechercheDuPlusPetit(List<Element> elements) {
		int min = Integer.MAX_VALUE;
		Element plusPetit = null;
		for (Element element : elements) {
			if (element.getValeur() < min) {
				min = element.getValeur();
				plusPetit = element;
			}
		}
		return plusPetit;
	}

	private static Element rechercheArrive(List<Element> estFini) {
		for (Element element : estFini) {
			if (element.getNoeudCourant().equals(Noeud.ARRIVE)) {
				return element;
			}
		}
		return null;
	}

	private static List<Noeud> rechercheChemin(Element arrive) {
		List<Noeud> chemin = new ArrayList<Noeud>();
		Element courant = arrive.getParent();
		while (!courant.getNoeudCourant().equals(Noeud.DEPART)) {
			chemin.add(courant.getNoeudCourant());
			courant = courant.getParent();
		}
		Collections.reverse(chemin);
		return chemin;
	}
}
