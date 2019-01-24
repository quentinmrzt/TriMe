package controller;

import java.io.File;

import model.*;

public class Controller {
	private Modelisation modelisation;

	public Controller(Modelisation modelisation) {
		this.modelisation = modelisation;
	}

	public void controleCheminImage(File image) {
		String chemin = image.getAbsolutePath();
		if (extensionValide(chemin)) {
			modelisation.setImage(image);
		}
	}

	private boolean extensionValide(String chemin) {
		String[] str = chemin.split("\\.");
		if (str == null || str.length == 0) {
			return false;
		}
		String extension = str[str.length - 1];
		return extension.equals("png") || extension.equals("jpg") || extension.equals("bmp");
	}
}