package application;
import java.awt.Dimension;

//import javax.swing.ImageIcon;
import javax.swing.JFrame;

import affichage.*;
//import modelisation.*;
import contronleur.Menu;

public class Lancer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Lancer() {
		super("Trim-Me");
		setPreferredSize(new Dimension(800, 450));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
		
	    // AFFICHAGE
	    Affichage aff = new Affichage(this);
	    
	    // APP
	    Application app = new Application(aff);
	    
	    // IMAGE
	    new VueImage(app); 
	    
		// MENU
		Menu menu = new Menu(app);
		this.setJMenuBar(menu);
		
		// ON MET A JOUR
		app.maj();
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Lancer();
	}

}
