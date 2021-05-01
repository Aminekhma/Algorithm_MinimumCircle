package algorithms;



import java.awt.Point;
import java.util.ArrayList;

import java.util.Random;

import supportGUI.Circle;



public class Welzl {
	Random r = new Random();

	
	public Circle calculCercleMin(ArrayList<Point> points) {return MinCercle(points, new ArrayList<Point>());}

	public Circle MinCercle(ArrayList<Point> liste1, ArrayList<Point> liste2) {
		Circle resultat = null;
///////////////////////////////// Cas initial ////////////////////////////////////////////////////////
		
		if (liste1.isEmpty()) {    						// Tous les points ont ete traites
			
			if (liste2.isEmpty())  						// Pas de cercle minimum
				return new Circle(new Point(0, 0), 10); // On retourne un cercle par defaut
			
			if (liste2.size() == 1) {					//On va retourner un cercle centrer sur liste2[0] avec un rayon =0
				resultat = new Circle(liste2.get(0), 0);}
			
			if (liste2.size() == 2) {					// On va retourner le cercle minimum pour liste2[0] et liste2[1]
				double cx = 0.5*(liste2.get(0).x + liste2.get(1).x); 	// calcul de la coordonnee x du centre du cercle
				double cy = 0.5*(liste2.get(0).y + liste2.get(1).y); 	// calcul de la coordonee y du centre du cercle
				double d = 0.5*(liste2.get(0).distance(liste2.get(1))); // calcul du rayon
				Point p = new Point((int) cx, (int) cy);	
				resultat = new Circle(p, (int) Math.ceil(d));			
			}
			if (liste2.size() == 3) {					// On va retourner le cercle minimum entre les 3 paires liste2[0], liste2[1] et liste2[3]
					
				Point a = liste2.get(0);				// Intialisation des points 
				Point b	= liste2.get(1);
				Point c =liste2.get(2);
				double tmp = (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) * 2;  // Calcul 
				double alpha = (a.x * a.x) + (a.y * a.y);
				double beta = (b.x * b.x) + (b.y * b.y);
				double zigma = (c.x * c.x) + (c.y * c.y);
				double x = ((alpha * (b.y - c.y)) + (beta * (c.y - a.y)) + (zigma * (a.y - b.y)))/tmp;
				double y = ((alpha * (c.x - b.x)) + (beta * (a.x - c.x)) + (zigma * (b.x - a.x)))/tmp;
				Point p = new Point(((int) x), ((int) y));
				resultat = new Circle(p, (int) Math.ceil(p.distance(a)));
			}}else {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			Point p = liste1.remove(r.nextInt(liste1.size())); 		// On choisi un point aleatoire p puis on le retire de la liste1
			resultat = MinCercle(liste1, liste2);					// Appel de la fonction sur liste1 et liste2 pour obtenir le cercle resultat
			if (resultat != null) {									// On verfie qu'il y'a bien un resultat
				double dx = (p.x-resultat.getCenter().x);		
				double dy = (p.y-resultat.getCenter().y);
				
				if(dx*dx+dy*dy <= resultat.getRadius()*resultat.getRadius() == false) {	//	Si p est entoure de resultat, alors nous renvoyons le resultat sinon, p doit se trouver a la limite du cercle minimum
					liste2.add(p);														//On ajoute le point limite a liste2
					resultat = MinCercle(liste1, liste2);								//Appel recursive
					liste2.remove(p);													//On retire le point de liste2
					liste1.add(p);														//On ajoute le point p a liste1
				}}}
		return resultat;	
	}




}
