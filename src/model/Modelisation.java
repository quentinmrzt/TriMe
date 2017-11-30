package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import observer.Observable;
import observer.Observer;

public class Modelisation implements Observable {
	// Donnée du model
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();   
	
	// Concernant l'image
	private String chemin = "";
	private int[] pixels;
	private int largeurImage=0, hauteurImage=0;
	
	
	public void imageToTableau() {
	    try {
	    	// On charge l'image
	        BufferedImage image = ImageIO.read(new File(chemin));
	        
	         // On recupère les infos sur l'image
	        largeurImage = image.getWidth();
	        hauteurImage = image.getHeight();
	        
	        // On charge l'image dans un tableau de pixel
			pixels = new int[largeurImage*hauteurImage];
			pixels = image.getRGB(0, 0, largeurImage, hauteurImage, pixels, 0, largeurImage);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void tableauToImage() {
		//image.setRGB(0, 0, width, height, pixels, 0, width);
	}

	
	public void reset() {
		chemin = "";
		pixels = null;
		largeurImage=0;
		hauteurImage=0;
		
		// notification pour les observeurs que le chemin a changé
		notifyObserver(chemin);
	}

	// OBSERVABLE
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

	public int posPixel(int x, int y) {
		return x+y*largeurImage;
	}
	
	// Getteur
	public String getChemin() {
		return chemin;
	}
	
	public int getRouge(int i) {
		return (pixels[i] & 0xff0000) >> 16;		
	}
	public int getRouge(int x, int y) {		
		return (pixels[posPixel(x,y)] & 0xff0000) >> 16;		
	}
	
	public int getVert(int i) {
		return (pixels[i] & 0xff00) >> 8;		
	}	
	public int getVert(int x, int y) {		
		return (pixels[posPixel(x,y)] & 0xff00) >> 8;		
	}
	
	public int getBleu(int i) {
		return pixels[i] & 0xff;		
	}	
	public int getBleu(int x, int y) {		
		return pixels[posPixel(x,y)] & 0xff;		
	}
	
	// Setteur
	public void setChemin(String chemin) {
		this.chemin = chemin;
		imageToTableau();
		
		// notification pour les observeurs que le chemin a changé
		notifyObserver(chemin);
	}
}
