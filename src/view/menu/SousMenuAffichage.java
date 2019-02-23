package view.menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.Modelisation;
import view.Fenetre;
import view.utils.CouleursConstantes;

public class SousMenuAffichage extends JMenu {

	JMenuItem zoomAvant, zoomArriere, ajuster, tailleReelle;

	public SousMenuAffichage() {
		super("Affichage");

		zoomAvant = null;
		zoomArriere = null;

		setName("Affichage");
		add(creationMenuZoomAvant());
		add(creationMenuZoomArriere());
		add(creationMenuAjuster());
		add(creationMenuTailleReelle());
	}


	private JMenuItem creationMenuZoomAvant() {
		String nom = "Zoom avant";
		zoomAvant = new JMenuItem(nom);
		zoomAvant.setName(nom);
		zoomAvant.setEnabled(false);
		zoomAvant.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		zoomAvant.setIcon(new ImageIcon(getClass().getResource("zoom-in.png")));
		zoomAvant.setActionCommand("Zoom avant");
		zoomAvant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getJFrame(zoomAvant).getPanelImage().zoom();
			}
		});
		return zoomAvant;
	}

	private JMenuItem creationMenuZoomArriere() {
		String nom = "Zoom arrière";
		zoomArriere = new JMenuItem(nom);
		zoomArriere.setName(nom);
		zoomArriere.setEnabled(false);
		zoomArriere.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		zoomArriere.setIcon(new ImageIcon(getClass().getResource("zoom-out.png")));
		zoomArriere.setActionCommand("Zoom arrière");
		zoomArriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getJFrame(zoomArriere).getPanelImage().dezoom();
			}
		});
		return zoomArriere;
	}

	private JMenuItem creationMenuAjuster() {
		String nom = "Ajuster à la fenêtre";
		ajuster = new JMenuItem(nom);
		ajuster.setName(nom);
		ajuster.setEnabled(false);
		ajuster.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		ajuster.setIcon(new ImageIcon(getClass().getResource("reduce.png")));
		ajuster.setActionCommand("Ajuster à la fenêtre");
		ajuster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getJFrame(zoomArriere).getPanelImage().ajuster();
			}
		});
		return ajuster;
	}

	private JMenuItem creationMenuTailleReelle() {
		String nom = "Taille réelle";
		tailleReelle = new JMenuItem(nom);
		tailleReelle.setName(nom);
		tailleReelle.setEnabled(false);
		tailleReelle.setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		tailleReelle.setIcon(new ImageIcon(getClass().getResource("resize.png")));
		tailleReelle.setActionCommand("Taille réelle");
		tailleReelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getJFrame(zoomArriere).getPanelImage().tailleReelle();
			}
		});
		return tailleReelle;
	}

	private Fenetre getJFrame(Component e) throws ClassCastException {
		if (e instanceof JMenuItem) {
			while (null != e && !(e instanceof JFrame))
				if (e instanceof JPopupMenu)
					e = ((JPopupMenu) e).getInvoker();
				else
					e = ((JComponent) e).getParent();
		} else {
			e = SwingUtilities.getWindowAncestor(e);
		}
		return (Fenetre) e;
	}

	public void miseAJour(Modelisation modelisation) {
		zoomAvant.setEnabled(modelisation.getImage() != null);
		zoomArriere.setEnabled(modelisation.getImage() != null);
		ajuster.setEnabled(modelisation.getImage() != null);
		tailleReelle.setEnabled(modelisation.getImage() != null);
	}
}
