package model;

import org.junit.Test;

public class TestDijkstra {

	@Test
	public void doit_passer() {
		Elements elements = new Elements(10,10);
		System.out.println(elements.existe(5, 5));

		for (int i=0; i<1 ; i++) {
			long debut = System.currentTimeMillis();

			int[][] tab = new int[600][600];

			for (int y=0; y<600; y++) {
				for (int x=0; x<600; x++) {
					tab[x][y] = 0;
					System.out.print(tab[x][y]+" ");
				}
				System.out.println();
			}

			long fin = System.currentTimeMillis();

			long difference = fin - debut;
			System.out.println("Temps: "+difference+"ms");
		}

	}
}
