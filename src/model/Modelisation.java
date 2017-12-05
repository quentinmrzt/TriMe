package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

import javax.imageio.ImageIO;

import observer.Observable;
import observer.Observer;

public class Modelisation implements Observable {
	// ----------------------------------------
	// Donnée du model
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();   

	// Concernant l'image
	private String chemin = "";
	private int[][] pixels; // sauvegarde sous forme de tableau de notre image
	private int largeurImage=0, hauteurImage=0;

	// Concernant le traitement de l'image
	private int[][] pixelsCourant; // image qui est modifiée
	private boolean[][] chemins; // les chemins emprinté
	private int nbDelete = 500;

	// Pas utile pour l'instant
	// private boolean[][] zonePxl; 
	// private boolean prioriteSuppression=true, usageSelecPix=false, grapheImplicite=true;


	// ----------------------------------------
	// Observable
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}

	public void notifyObserver(String str) {
		// On maj tout les observeur
		for(Observer obs : listObserver) {
			obs.update(str);
		}
	}

	// ----------------------------------------
	// Getteur
	public String getChemin() {
		return chemin;
	}

	public int getRouge(int x, int y) {
		return (pixels[x][y] & 0xff0000) >> 16;		
	}

	public int getVert(int x, int y) {
		return (pixels[x][y] & 0xff00) >> 8;		
	}

	public int getBleu(int x, int y) {
		return pixels[x][y] & 0xff;		
	}

	private int getGris(int x, int y) {
		return (getRouge(x,y)+getVert(x,y)+getBleu(x,y))/3;
	}

	// ----------------------------------------
	// Setteur
	public void setChemin(String chemin) {
		this.chemin = chemin;
		imageToTableau();

		// notification pour les observeurs que le chemin a changé
		notifyObserver(chemin);
	}

	public void reset() {
		chemin = "";
		pixels = null;
		largeurImage=0;
		hauteurImage=0;

		// notification pour les observeurs que le chemin a changé
		notifyObserver(chemin);
	}

	// ----------------------------------------
	// Affichage
	public void affichageTableau(int[][] tab) {
		for (int y=0 ; y<tab[0].length ; y++) {
			for (int x=0 ; x<tab.length ; x++) {
				System.out.print(tab[x][y]+" ");
			}
			System.out.println(" ");
		}
	}

	// ----------------------------------------
	// Tableau
	public void imageToTableau() {
		try {
			// On charge l'image
			BufferedImage image = ImageIO.read(new File(chemin));

			// On recupère les infos sur l'image
			largeurImage = image.getWidth();
			hauteurImage = image.getHeight();

			// On charge l'image dans un tableau de pixel provisoire
			int[] tmp = new int[largeurImage*hauteurImage];
			tmp = image.getRGB(0, 0, largeurImage, hauteurImage, tmp, 0, largeurImage);

			// On place le tableau à une dimension dans une matrice
			pixels = new int[largeurImage][hauteurImage];
			pixelsCourant = new int[largeurImage][hauteurImage];
			chemins = new boolean[largeurImage][hauteurImage];

			for (int y=0 ; y<hauteurImage ; y++) {
				for (int x=0 ; x<largeurImage ; x++) {
					// L'image de départ en n&b
					pixels[x][y] = tmp[x+y*largeurImage];
					pixels[x][y] = getGris(x,y);
					// L'image courante est identique au départ
					pixelsCourant[x][y] = pixels[x][y];
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

	public int posPixel(int x, int y) {
		return x+y*largeurImage;
	}

	// ----------------------------------------
	// DeletePXs
	public void deletePXs() {
		// On enregistre l'image initiale
		tableauToImage(pixelsCourant,"Ini"+nbDelete+"px.png", false);

		for (int nbPixDel=1 ; nbPixDel<=nbDelete ; nbPixDel++) {
			System.out.println("Suppression "+nbPixDel+".");
			deletePX();
		} 

		// On enregistre l'image finale
		tableauToImage(pixelsCourant,"Finale"+nbDelete+"px.png", false);
		// On enregistre l'image avec les chemins empruntés
		tableauToImage(masqueChemins(),"Chemins"+nbDelete+"px.png", false);
	}

	public void deletePX() {
		// Copie du tableau courant car il sera modifié par la suite
		int[][] tmpImage = pixelsCourant;
		int largeur = pixelsCourant.length;
		int hauteur = pixelsCourant[0].length;

		// Création et ini du tableau d'intérêt
		int[][] interImage = new int[largeur][hauteur];
		interImage = interestVertical(tmpImage);

		// Algo implicite: ?
		Graph g = new GraphImplicit(interImage, largeur, hauteur);

		// Tritopo: ?
		ArrayList<Integer> ordre = tritopo(g);

		// Bellman: ?
		ArrayList<Integer> cheminMin = Bellman(g, g.vertices()-1, g.vertices()-2, ordre);

		/* Parcours et suppression des pixels inutiles */
		pixelsCourant = new int[largeur-1][hauteur];
		for (int y=0 ; y<hauteur ; y++) {
			int decalPxl = 0;
			int decalChemin = 0;
			for (int x=0 ; x<largeur ; x++) {
				// Position sur une dimension
				int position = x + largeur*y;

				while(chemins[x+decalChemin][y] == true) {
					decalChemin++;
				}

				// Si la position actuelle est différente de la position du cheminMin
				if (position != cheminMin.get(y+1)) {
					// On recouvre le cheminMin
					pixelsCourant[decalPxl][y] = tmpImage[x][y];
					decalPxl++;
				} else {
					// on ini le pixel en noir
					chemins[x+decalChemin][y] = true;
				}
			}
		}
	}

	public int[][] masqueChemins() {
		int imageChemins[][] = pixels;

		for (int y=0 ; y<hauteurImage ; y++) {
			for (int x=0 ; x<largeurImage ; x++) {
				if (chemins[x][y] == true) {
					// le pixel devient noir
					imageChemins[x][y] = 0;	
				}
			}
		}

		return imageChemins;
	}

	public static int[][] interestVertical(int[][] image) {
		// on part du principe qu'une image d'un pixel, ça ne sert à rien
		assert(image.length>1):"tableau trop petit";

		int tailleX = image.length;
		int tailleY = image[0].length;

		// on crée un nouveau tableau[x][y] tab
		int[][] tab = new int[tailleX][tailleY];

		for (int y=0 ; y<tailleY ; y++) {
			// Cas particulier: on parcours la première case 
			// On fait la différence entre le pixel courant et son voisin de droite
			tab[0][y] = Math.abs(image[0][y] - image[1][y]);

			// On parcours la ligne en ignorant la première et la dernière case
			for (int x=1 ; x<tailleX-1 ; x++){
				int somme = image[x-1][y] + image[x+1][y];
				int moyenne = 0;
				// On fait la moyenne du pixel voisin: avant et après
				if (somme != 0) {
					moyenne = somme/2;
				}

				// On fait la différence de cette moyenne avec le pixel courant
				tab[x][y] = Math.abs(moyenne - image[x][y]);
			}

			// Cas particulier: on parcours la dernière case 
			// On fait la différence entre le pixel courant et son voisin de gauche
			tab[tailleX-1][y] = Math.abs(image[tailleX-1][y] - image[tailleX-2][y]);
		}

		return tab;
	}

	public static ArrayList<Integer> tritopo(Graph g) {
		// Pile d'entier
		Stack<Integer> uStack = new Stack<Integer>();
		// Pile de jsp quoi
		Stack<Iterator> itStack = new Stack<Iterator>();

		// Un tableau de taille: tailleX*tailleY+2, nb de noeud en gros
		boolean visited[] = new boolean[g.vertices()];
		int v; // un entier ?
		ArrayList<Integer> suffixe = new ArrayList<Integer>(); // une list d'entier ?

		// Ajout de (s,next(s))
		int s = g.vertices()-1; // nb case-1 ?
		uStack.push(s);
		itStack.push(g.next(s).iterator());
		visited[s] = true; // on a visité s, l'avant dernière case soit le début ? 

		// On boucle dans que les piles ne sont pas vide
		while (!uStack.empty() && !itStack.empty()) {
			// On regarde le sommet de la pile
			int u = uStack.peek();
			Iterator<Edge> it = itStack.peek();

			// s'il existe une suite
			if(it.hasNext()) {
				// On regarde la valeur du suivant
				v = it.next().to;

				// Si la suite n'a pas jamais été visité
				if (!visited[v]) {
					visited[v] = true; // maintenant c'est le cas
					// on ajoute à la pile
					uStack.push(v);
					itStack.push(g.next(v).iterator());
				}
			} else {
				// On retire le sommet de la pile..
				uStack.pop();
				itStack.pop();
				// ..et on l'ajoute à la liste
				suffixe.add(u);
			}
		}

		// On inverse la liste qui n'est pas dans le bon sens
		Collections.reverse(suffixe);

		return suffixe;
	}

	// ----------------------------------------
	// Bellman
	public static ArrayList<Integer> Bellman(Graph g, int s, int t, ArrayList<Integer> order) {
		//
		int[] chemin = creerTabChemin(g,s,order);

		//
		ArrayList<Integer> ccm = new ArrayList<>();
		ccm = creerListChemin(chemin,s,t);

		return ccm;
	}

	public static int[] creerTabChemin(Graph g, int s, ArrayList<Integer> order) {
		int[] tab = new int[order.size()];
		int[] chemin = new int[order.size()];

		for(int i:order) {
			tab[i]=9999;

			if (i == s) {
				tab[i]=0;
			}
			for(Edge e:g.prev(i)) {
				if (tab[i] > tab[e.from]+e.cost) {
					tab[i] = tab[e.from]+e.cost;
					chemin[i] = e.from;
				} 
			}
		}

		return chemin;
	}

	public static ArrayList<Integer> creerListChemin(int[] chemin, int s, int t) {
		ArrayList<Integer> ccm = new ArrayList<>();
		int k = t;
		while (k != s) {
			ccm.add(k);
			k = chemin[k];
		}
		ccm.add(s);

		Collections.reverse(ccm);

		return ccm;
	}

	// ----------------------------------------
	// Pas utile pour l'instant
	public static int[][] interestHorizontal(int[][] image) {
		assert(image.length>1):"tableau trop petit";
		int[][]tab=new int[image.length][image[0].length];
		for (int i=0;i<image[0].length;i++){
			tab[0][i]=Math.abs(image[0][i]-image[1][i]);
			for (int j=1;j<image.length-1;j++){

				int moyenne = (image[j-1][i] + image[j+1][i])/2;
				tab[j][i]=Math.abs(moyenne-image[j][i]);
			}
			tab[image.length-1][i]=Math.abs(image[image.length-1][i]-image[image.length-2][i]);
		}

		return tab;
	}

	public static void prioriteSuppression(int[][] tab, boolean[][] zonePxl, boolean priorite) {
		for (int y=0 ; y<tab.length ; y++) {
			for (int x=0 ; x<tab[0].length ; x++) {	   
				if(zonePxl[y][x]) {
					if(priorite) {
						tab[y][x] -= 99999;
					} else {
						tab[y][x] += 99999;
					}  
				}
			}
		}
	}

	public static Graph tographEA(int[][] itr){
		int hauteur=itr.length;
		int largeur = itr[0].length;
		GraphArrayList graph=new GraphArrayList(hauteur*largeur+2);
		for (int j=0;j<largeur;j++){
			graph.addEdge(new Edge(hauteur*largeur+1,j,0));
		}

		for (int j=0;j<hauteur-1;j++){
			graph.addEdge(new Edge(j*largeur,(j+1)*largeur, itr[j][1] ));
			graph.addEdge(new Edge(j*largeur,(j+1)*largeur+1, Math.abs( itr[j][1] - itr[j+1][0] )));
			for (int i=1;i<largeur-1;i++){
				graph.addEdge(new Edge(i+j*largeur,i-1+(j+1)*largeur,Math.abs( itr[j+1][i] - itr[j][i-1] )));   	// bas à gauche
				graph.addEdge(new Edge(i+j*largeur,i+(j+1)*largeur,Math.abs( itr[j][i+1] - itr[j][i-1] )));		// bas
				graph.addEdge(new Edge(i+j*largeur,i+1+(j+1)*largeur,Math.abs( itr[j+1][i] - itr[j][i+1] ))); 	// bas à droite
			}
			graph.addEdge(new Edge(j*largeur+largeur-1,(j+1)*largeur+largeur-2, Math.abs( itr[j+1][largeur-1] - itr[j][largeur-2] )));
			graph.addEdge(new Edge(j*largeur+largeur-1,(j+1)*largeur+largeur-1, Math.abs( itr[j][1] - itr[j][largeur-2] )));
		}


		for (int j=0;j<largeur;j++){
			if (j==0){
				graph.addEdge(new Edge(largeur*(hauteur-1)+j,hauteur*largeur,itr[hauteur-1][j+1]));
			}else{
				if (j==largeur-1){
					graph.addEdge(new Edge(largeur*(hauteur-1)+j,hauteur*largeur,itr[hauteur-1][j-1]));
				}else{
					graph.addEdge(new Edge(largeur*(hauteur-1)+j,hauteur*largeur, Math.abs( itr[hauteur-1][j+1] - itr[hauteur-1][j-1] )));
				}
			}
		}

		return graph;
	}
}
