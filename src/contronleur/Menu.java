package contronleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import application.Application;

public class Menu extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JMenu menu;
	protected JMenuItem quitter;
	protected JMenuItem choisir;
	
	protected JMenu mode;
	protected JMenuItem debug;
	
	protected Application app;

	
	public Menu(Application appli) {		
		app = appli;
		
		
	//MENU
		menu = new JMenu("Menu") ;
		menu.setMnemonic(KeyEvent.VK_M);

		
		// Item pour choisir une image
		choisir = new JMenuItem("Choisir une image");
		choisir.setMnemonic(KeyEvent.VK_B);
		choisir.setActionCommand("Choisir une image");
		choisir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				choisir();				
				app.maj();
			}
		});
		
		// Item pour quitter
		quitter = new JMenuItem("Quitter");
		quitter.setMnemonic(KeyEvent.VK_B);
		quitter.setActionCommand("Quitter");
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		
		// Intégration des items au menu
		menu.add(choisir);
		menu.add(quitter);
		
		
		
	// MODE
		mode = new JMenu("Mode");
		mode.setMnemonic(KeyEvent.VK_M);
		
		// Item pour débuger
		debug = new JMenuItem("Débuger");
		debug.setMnemonic(KeyEvent.VK_B);
		debug.setActionCommand("Débuger");
		debug.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		mode.add(debug);
		
		// Intégration du menu à la bar
		this.add(menu);
		this.add(mode);		
	}
	
	public void choisir() {
		try {
			JFileChooser jf = new JFileChooser();
			int reponse = jf.showSaveDialog(getParent());
			if (reponse == JFileChooser.APPROVE_OPTION) {
				//File fichier = jf.getSelectedFile();
				app.setChemin(jf.getSelectedFile().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
