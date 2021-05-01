package test;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import algorithms.Welzl;
import supportGUI.Circle;



public class Main {
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
