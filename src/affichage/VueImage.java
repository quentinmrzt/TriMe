package affichage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.Application;

public class VueImage extends JPanel implements Vue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JButton[][] boutons;
	protected Application app;
	protected JLabel image;
		
	public VueImage(Application ap) {
		super();
		app = ap;
		image = new JLabel();
		
		if(!app.getChemin().isEmpty()) {
			image = new JLabel( new ImageIcon(app.getChemin()));
			this.add(image);
		}
		
		app.getAff().addVue(this);
	}
	
	public void mettreAJour() {
		if(!app.getChemin().equals(null)) {
			this.remove(image);
			image = new JLabel(new ImageIcon(app.getChemin()));
			this.add(image);
			this.repaint();
		}
	}
}
