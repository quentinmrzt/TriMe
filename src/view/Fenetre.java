package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import observer.Observer;


public class Fenetre extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JLabel image;
	protected Menu menu;
	protected Controller controler;
	
	public Fenetre(Controller c) {
	    this.setSize(800, 450);
	    this.setTitle("Trim-Me");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    this.controler = c;
	    
	    // Menu
	    menu = new Menu(controler);
		this.setJMenuBar(menu);
		
		// L'image
		image = new JLabel();
		this.add(image);
	    
	    //pack();
	    this.setVisible(true);
	}

	// Observer
	public void update(String str) {
		// On affiche la nouvelle image
		if(!str.equals("")) {
			this.remove(image);
			image = new JLabel(new ImageIcon(str));
			this.add(image);
			
			// sert a reconstruire les composants au sein d'un layoutmanager en cas de modification "majeure"
			this.validate();
		}
	}

}