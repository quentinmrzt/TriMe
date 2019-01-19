package model;

import java.util.ArrayList;
import java.util.List;

import graphe.Graphe;
import graphe.Noeud;

public class Dijkstra {

	public static void executer(Graphe graphe) {

		List<Element> aTraiter = new ArrayList<Element>();
		List<Element> estFini = new ArrayList<Element>();

		Element parent = new Element(null, graphe.getNoeudDepart());
		aTraiter.add(parent);
		
		while (parent != null) {
			parent = iteration(aTraiter, estFini, parent);
		}
		
		System.out.println("------------------------");
		
		Element arrive = null;
		for (Element element: estFini) {
			if(element.getNoeud().equals(Noeud.ARRIVE)) {
				arrive = element;
			}
		}
		
		List<Noeud> chemin = new ArrayList<Noeud>();
		Element courant = arrive.getParent();
		while (!courant.getNoeud().equals(Noeud.DEPART)) {
			chemin.add(courant.getNoeud());
			courant = courant.getParent();
		}
		
		System.out.println("------------------------");
		
		for (Noeud noeud: chemin) {
			System.out.println(noeud);
		}
	}
	
	private static boolean estDejaTraite(List<Element> estFini, Element element) {
		return estFini.contains(element);
	}
	
	private static void ajoutDesFilsATraiter(List<Element> aTraiter, List<Element> estFini, Element parent) {
		Noeud noeudParent = parent.getNoeud();
		for (Noeud noeudFils: noeudParent.getFils()) {
			Element element = new Element(parent, noeudFils);
			if(!estDejaTraite(estFini, element)) {
				int index = aTraiter.indexOf(element);
				if(index != -1) {
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

	private static Element iteration(List<Element> aTraiter, List<Element> estFini, Element parent) {
		ajoutDesFilsATraiter(aTraiter, estFini, parent);

		// On parcourt cette liste à la recherche du plus petit
		int min = Integer.MAX_VALUE;
		Element plusPetit = null;
		for (Element element : aTraiter) {
			if (element.getValeur() < min) {
				min = element.getValeur();
				plusPetit = element;
			}
		}
		
		return plusPetit;
	}
}
