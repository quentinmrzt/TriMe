package view;

import static java.awt.Color.WHITE;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.Modelisation;

public class Fenetre extends JFrame implements Observer {

	private JLabel chemin, extension, hauteur, largeur;
	

	public Fenetre(Controller controller) {
		setMinimumSize(new Dimension(800, 400));
		setTitle("Trim-Me");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setJMenuBar(new Menu(controller));
		
		JPanel pannel = new JPanel(); 
		pannel.setBackground(WHITE);
		chemin = new JLabel("Chemin: ");
		extension = new JLabel("Extension: ");
		hauteur = new JLabel("Hauteur: ");
		largeur = new JLabel("Largeur: ");
		pannel.add(chemin);
		pannel.add(extension);
		pannel.add(hauteur);
		pannel.add(largeur);

		add(pannel);

		pack();
		setVisible(true);
	}

	@Override
	public void update(Observable obs, Object obj) {
		// TODO: à voir si c'est la bonne méthode
		if(obs instanceof Modelisation) {
			Modelisation modelisation = (Modelisation) obs;
			chemin.setText("Chemin: "+modelisation.getImage().getChemin());
			extension.setText("Extension: "+modelisation.getImage().getExtension());
			hauteur.setText("Hauteur: "+modelisation.getImage().getHauteur());
			largeur.setText("Largeur: "+modelisation.getImage().getLargeur());
		}
	}
}