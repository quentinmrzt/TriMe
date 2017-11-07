package affichage;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Affichage {
    protected JFrame frame;
	protected ArrayList<Vue> listeVue;
	
	//
    public Affichage(JFrame f) {
    	frame = f;
		listeVue = new ArrayList<Vue>();
		
		maj();
    }

	public void maj() {
		int i=0;
		for (Vue v: listeVue) {
			System.out.println(i);
			v.mettreAJour();
			
			frame.add((Component)v);
			
    		System.out.println("affichage maj");
    		i++;
    		
    		// On force la maj, à améliorer
    		frame.setVisible(true);
		}
	}
	
	
	
	// GETTEUR & SETTEUR
	public void addVue(Vue v) {
		listeVue.add(v);
	}
	
	public Vue getListeVue(int i) {
		return listeVue.get(i);
	}
	
	public ArrayList<Vue> getListeVue() {
		return listeVue;
	}
	
}