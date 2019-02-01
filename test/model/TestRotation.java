package model;

import org.junit.Test;

public class TestRotation {

	@Test
	public void doit_passer() {
		
	}
	
	@Test
	public void a_supprimer() {
		int largeur = 5;
		int hauteur = 5;
		int degre = 360;
		int[][] tableau = new int[largeur][hauteur];

		// Ini du tableau
		int index = 0;
		for (int y = 0; y < largeur; y++) {
			for (int x = 0; x < hauteur; x++) {
				tableau[x][y] = index;
				index++;
			}
		}

		// Affichage
		for (int y = 0; y < largeur; y++) {
			for (int x = 0; x < hauteur; x++) {
				System.out.print(tableau[x][y] + " ");
			}
			System.out.println();
		}

		// Rotation
		double angle_radian = -degre * Math.PI / 180.0;
		double tcos = Math.cos(angle_radian);
		double tsin = Math.sin(angle_radian);

		/* calcul de la taille de l'image de destination */
		int largeurdest = (int) Math.ceil(largeur * Math.abs(tcos) + largeur * Math.abs(tsin));
		int hauteurdest = (int) Math.ceil(hauteur * Math.abs(tsin) + hauteur * Math.abs(tcos));

		/* on redimensionne l'image */
		int[][] destination = new int[largeurdest][hauteurdest];

		/* calcul du centre des images */
		int mxdest = largeurdest / 2;
		int mydest = hauteurdest / 2;
		int mx = largeur / 2;
		int my = hauteur / 2;

		for (int j = 0; j < hauteurdest; j++) {
			for (int i = 0; i < largeurdest; i++) {
				int bx = (int) (Math.ceil(tcos * (i - mxdest) + tsin * (j - mydest) + mx));
				int by = (int) (Math.ceil(-tsin * (i - mxdest) + tcos * (j - mydest) + my));

				if (bx >= 0 && bx < largeur && by >= 0 && by < hauteur) {
					destination[i][j] = tableau[bx][by];
				}
			}
		}

		// Affichage
		System.out.println();
		for (int y = 0; y < largeur; y++) {
			for (int x = 0; x < hauteur; x++) {
				System.out.print(destination[x][y] + " ");
			}
			System.out.println();
		}
	}
}
