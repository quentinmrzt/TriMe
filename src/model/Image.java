package model;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private BufferedImage image;
	private String chemin, nom, extension;
	private int largeur, hauteur;
	private int[][] pixels;

	public Image(File fichier) {
		try {
			chemin = fichier.getParent();
			rechercheNomEtExtension(fichier);
			image = ImageIO.read(fichier);
			largeur = image.getWidth();
			hauteur = image.getHeight();
			bufferedImageEnTableauDePixels();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void rechercheNomEtExtension(File fichier) {
		String[] str = fichier.getName().split("\\.");
		if (str != null && str.length == 2) {
			nom = str[0];
			extension = str[1];
		} else {
			System.err.println("Mauvais format pour la recherche du nom et de l'extension.");
		}
	}

	private void bufferedImageEnTableauDePixels() {
		pixels = new int[largeur][hauteur];
		int[] tmp = image.getRGB(0, 0, largeur, hauteur, null, 0, largeur);
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				pixels[x][y] = tmp[positionPixelDansTableauUneDimension(x, y)];
			}
		}
	}

	private int positionPixelDansTableauUneDimension(int x, int y) {
		return x + y * largeur;
	}

	public Image(String nom, String extension, String chemin, int[][] tableau) {
		this.chemin = chemin;
		this.nom = nom;
		this.extension = extension;
		largeur = tableau.length;
		hauteur = tableau[0].length;
		pixels = tableau;
		tableauEnBufferedImage();
	}

	private void tableauEnBufferedImage() {
		image = new BufferedImage(largeur, hauteur, TYPE_INT_RGB);
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				image.setRGB(x, y, getPixel(x, y));
			}
		}
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public String getChemin() {
		return chemin;
	}

	public String getNom() {
		return nom;
	}

	public String getExtension() {
		return extension;
	}
	
	public String getNomFichier() {
		return getNom() + "." + getExtension();
	}

	public int getPixel(int x, int y) {
		return pixels[x][y];
	}

	public Color getCouleur(int x, int y) {
		return new Color(pixels[x][y]);
	}

	public void enregistrementImage(String nomComplet) {
		try {
			ImageIO.write(image, extension, new File(nomComplet));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public BufferedImage getBufferedImage() {
		return image;
	}
}
