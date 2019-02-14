package controller;

import java.io.File;

import model.Modelisation;

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

	public void supprimerDesPixels(String nombresPixels) {
		int nombre = nombreValide(nombresPixels);
		if(modelisation.getImage() != null) {
			if(nombre > 0 && nombre < modelisation.getImage().getLargeur()) {
				modelisation.suppressionDePixels(nombre);
			}
		}
	}

	public void dessinerDesPixels(String nombresPixels) {
		int nombre = nombreValide(nombresPixels);
		if(modelisation.getImage() != null) {
			if(nombre > 0 && nombre < modelisation.getImage().getLargeur()) {
				modelisation.dessinDePixels(nombre);
			}
		}
	}

	public void rotation(String nombresPixels) {
		int nombre = nombreValide(nombresPixels);
		if(modelisation.getImage() != null) {
			if(nombre >= 0 && nombre <= 360) {
				modelisation.rotation(nombre);
			}
		}
	}

	private int nombreValide(String nombresPixels) {
		try {
			return Integer.parseInt(nombresPixels);
		} catch (Exception e) {
			System.out.println("nombreValide(): Mauvais nombre !");
			return 0;
		}
	}

	public void sauvegarder(File selectedFile) {
		System.out.println(selectedFile.getAbsolutePath());
	}


}