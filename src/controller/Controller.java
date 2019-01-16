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

	private String leDernier(String[] str) {
		if (str == null || str.length == 0) {
			return "";
		}
		return str[str.length - 1];
	}

	private boolean extensionValide(String chemin) {
		String extension = leDernier(chemin.split("\\."));
		return extension.equals("png") || extension.equals("jpg") || extension.equals("bmp");
	}

	public void controleDelete() {
		//modelisation.deletePXs();
	}
}