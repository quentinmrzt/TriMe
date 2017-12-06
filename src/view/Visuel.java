package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.Controller;

public class Visuel extends JLabel {
	private Image img;

	public Visuel(String str) {
		this.setSize(300, 300);
		
		if (str != "") {
		img = new ImageIcon(getClass().getResource(str)).getImage();
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img == null) {
			return;
		}
		
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
	}
}
