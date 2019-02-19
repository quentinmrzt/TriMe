package view.menu;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SousMenuAide extends JMenu {

	private final Color BACKGROUNDCOLOR = Color.WHITE;

	public SousMenuAide() {
		super("Aide");

		add(creationMenuGithub());
	}

	private JMenuItem creationMenuGithub() {
		String nom = "Lien vers GitHub";
		JMenuItem github = new JMenuItem(nom);
		github.setName(nom);
		github.setBackground(BACKGROUNDCOLOR);
		github.setIcon(new ImageIcon(getClass().getResource("github-logo.png")));
		github.setActionCommand(nom);
		github.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/quentinmrzt/TriMe"));
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		return github;
	}
}
