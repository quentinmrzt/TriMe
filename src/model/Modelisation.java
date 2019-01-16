package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;


public class Modelisation extends Observable {
	private Image image;
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();   



	// Concernant le traitement de l'image
	private int[][] pixelsCourant; // image qui est modifiée
	private boolean[][] chemins; // les chemins emprinté
	private int nbDelete = 200;

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
			obs.update(this, str);
		}
	}

	// ----------------------------------------
	// DeletePXs
	/*public void deletePXs() {
		// On enregistre l'image initiale
		tableauToImage(pixels,"Ini"+nbDelete+"px.png", true);

		for (int nbPixDel=1 ; nbPixDel<=nbDelete ; nbPixDel++) {
			System.out.println("Suppression "+nbPixDel+".");
			deletePX();
		} 

		// On enregistre l'image finale
		tableauToImage(pixelsCourant,"Finale"+nbDelete+"px.png", true);
		// On enregistre l'image avec les chemins empruntés
		tableauToImage(masqueChemins(),"Chemins"+nbDelete+"px.png", true);
	}

	public void deletePX() {
		// Copie du tableau courant car il sera modifié par la suite
		int[][] tmpImage = pixelsCourant;
		int largeur = pixelsCourant.length;
		int hauteur = pixelsCourant[0].length;

		// Création et ini du tableau d'intérêt
		int[][] interImage = new int[largeur][hauteur];
		//interImage = interestVertical(tmpImage);
		interImage = interestVertical();
		
		// Algo implicite: ?
		Graph g = new GraphImplicit(interImage, largeur, hauteur);

		// Tritopo: ?
		ArrayList<Integer> ordre = tritopo(g);

		// Bellman: ?
		ArrayList<Integer> cheminMin = Bellman(g, g.vertices()-1, g.vertices()-2, ordre);

		// Parcours et suppression des pixels inutiles
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
	}*/

	// Pour de la couleur
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

	public Image getImage() {
		return image;
	}
	
	public void setImage(File fichier) {
		this.image = new Image(fichier);
		notifyObserver(null);
	}
}
