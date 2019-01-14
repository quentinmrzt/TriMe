package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	private File fichier;
	private String chemin, extension;
	private int largeur, hauteur;
	private int[][] pixels, pixelsCourant;
	private boolean chemins[][];
	
	public Image(File fichier) {
		this.fichier = fichier;
		chemin = fichier.getAbsolutePath();
		setExtension();
		imageToTableau();
	}
	
	public String getChemin() {
		return chemin;
	}
	
	public String getExtension() {
		return extension;
	}
	
	private void setExtension() {
		extension = leDernier(chemin.split("\\."));
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	private String leDernier(String[] str) {
		if (str == null || str.length == 0) {
			return "";
		}
		return str[str.length - 1];
	}

	public void imageToTableau() {
		try {
			// On charge l'im age
			BufferedImage image = ImageIO.read(fichier);

			// On recupère les infos sur l'image
			largeur = image.getWidth();
			hauteur = image.getHeight();

			// On charge l'image dans un tableau de pixel provisoire
			int[] tmp = new int[largeur*hauteur];
			tmp = image.getRGB(0, 0, largeur, hauteur, tmp, 0, largeur);

			// On place le tableau à une dimension dans une matrice
			pixels = new int[largeur][hauteur];
			pixelsCourant = new int[largeur][hauteur];
			chemins = new boolean[largeur][hauteur];

			for (int y=0 ; y<hauteur ; y++) {
				for (int x=0 ; x<largeur ; x++) {
					// L'image de départ en n&b
					pixels[x][y] = tmp[x+y*largeur];
					// L'image courante est identique au départ
					pixelsCourant[x][y] = pixels[x][y];
					//pixelsCourant[x][y] = getGris(x,y);
					// Il n'y a aucun chemin au départ
					chemins[x][y] = false;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	    
	}

	public void tableauToImage(int[][] tab, String nom, boolean couleur) {
		int tailleX = tab.length;
		int tailleY = tab[0].length;

		// On créer une image couleur
		BufferedImage img = new BufferedImage(tailleX, tailleY, BufferedImage.TYPE_INT_RGB);

		// On place une matrice dans un tableau à une dimension
		for (int y=0 ; y<tailleY ; y++) {
			for (int x=0 ; x<tailleX ; x++) {
				if (couleur) {
					img.setRGB(x, y, tab[x][y]); 
				} else {
					// Si c'est du n&b, on triche on le transformant en rgb 
					int gris = (tab[x][y]<<16)+(tab[x][y]<<8)+(tab[x][y]);
					img.setRGB(x, y, gris);
				}
			}
		}

		// On enregistre notre image
		try {
			ImageIO.write(img, "png", new File(nom));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
