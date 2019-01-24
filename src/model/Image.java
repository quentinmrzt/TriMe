package model;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private String chemin, nom, extension;
	private int largeur, hauteur;
	private int[][] pixels;

	public Image(File fichier) {
		chemin = fichier.getAbsolutePath();
		nom = rechercheNom(fichier.getName());
		extension = rechercheExtension(fichier.getName());
		fichierEnTableauDePixels(fichier);
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

	public Color getCouleur(int x, int y) {
		return new Color(pixels[x][y]);
	}

	public int getRouge(int x, int y) {
		return getCouleur(x, y).getRed();
	}

	public int getVert(int x, int y) {
		return getCouleur(x, y).getGreen();
	}

	public int getBleu(int x, int y) {
		return getCouleur(x, y).getBlue();
	}

	private String rechercheNom(String nomComplet) {
		String[] str = nomComplet.split("\\.");
		if (str == null || str.length == 0) {
			return "";
		}
		return str[str.length - 2];
	}

	private String rechercheExtension(String nomComplet) {
		String[] str = chemin.split("\\.");
		if (str == null || str.length == 0) {
			return "";
		}
		return str[str.length - 1];
	}

	public void fichierEnTableauDePixels(File fichier) {
		try {
			BufferedImage image = ImageIO.read(fichier);
			largeur = image.getWidth();
			hauteur = image.getHeight();
			int[] tmp = image.getRGB(0, 0, largeur, hauteur, null, 0, largeur);
			pixels = new int[largeur][hauteur];
			for (int y = 0; y < hauteur; y++) {
				for (int x = 0; x < largeur; x++) {
					pixels[x][y] = tmp[positionPixelDansTableauUneDimension(x, y)];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int positionPixelDansTableauUneDimension(int x, int y) {
		return x + y * largeur;
	}

	public static void tableauEnImage(int[][] tab, String nom, String extension) {
		int largeur = tab.length;
		int hauteur = tab[0].length;
		BufferedImage image = new BufferedImage(largeur, hauteur, TYPE_INT_RGB);
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				image.setRGB(x, y, tab[x][y]);
			}
		}
		enregistrementImage(image, nom, extension);
	}

	private static void enregistrementImage(BufferedImage image, String nom, String extension) {
		try {
			ImageIO.write(image, extension, new File(nom));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
