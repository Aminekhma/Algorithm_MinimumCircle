package test;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import algorithms.Welzl;
import algorithms.naive;
import supportGUI.Circle;


public class NaiveTest {
	
	String path = "samples/" ;
	String pattern = "test-" ;
	String ext = ".points" ;
    float res [] = new float[1664] ; 


	naive n = new naive();


    ArrayList<Point> points =new ArrayList<Point>();
	
	public ArrayList<Point> load(final String parentPath, final String childPath) {
		ArrayList<Point> list = null;
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(parentPath, childPath));
			list = new ArrayList<Point>(256);
			
			while (scanner.hasNextInt()) {        						   	//Tant que la prochaine ligne n'est pas vide 
		        list.add(new Point(scanner.nextInt(), scanner.nextInt())); 	//On ajoute le point de la ligne dans la liste
			    
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			scanner.close();
		}

		
		return list;
	}
	
	public void testN() throws IOException {


		long totaltimeN = 0;
		int i = 1;
		while(i <= 1664  ) {									//On boucle pour tous les 1664 fichiers
			String expr = pattern + Integer.toString(i) + ext;	//Initialisation du chemin des fichiers de la base de test
			points = load(path,expr );							// Appel de la fonction pour charger les points du fichier i dans la liste

				
			long debutN = System.nanoTime();
			
			Circle c = n.calculCercleMin(points);				//Appel de la fonction calculant le cercle minimum par l'algorithme naif
			
			long tempsN=System.nanoTime() - debutN;				//Temps d'execution de la fonction 
			totaltimeN = totaltimeN + tempsN;

			res[i-1] = (float) (tempsN*10e-6);					//On stocke le resultat en ms
			
		
			System.out.println("fichier : "+expr+ " Naif : "+tempsN*10e-6);			
			i++;

		}
		
		FileWriter fw = new FileWriter("resultats/resNaive",true);	
		 int j =0;
		 while (j < res.length) {								//On ajoute les resultats stocker dans res dans un fichier resultat "resultats/resNaive"
			 fw.write(res[j]+"\n");
			 j++;
			
		}
		fw.close();
		
		//System.out.println("Total time elapsed Naive : " + totaltimeN*10e-6 + " ms.");

	
			
	}
	
	public static void main(String[] args) {
		
		
		NaiveTest n = new NaiveTest();
		Welzltest w = new Welzltest();
		
		try {
			n.testN();	//Appel de la fonction permettant le test de l'algorithme Naif sur les fichiers de la base de test "Varoumas_benchmark"
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
	}


}
