package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import supportGUI.Circle;
import supportGUI.Line;

public class DefaultTeam {

	Random r = new Random();

  //////////////////////////// ALGO WELZL /////////////////////////////////////////////////////
  
	
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



//	  private Circle calculCercleMin(ArrayList<Point> inputPoints){

  
  //////////////////////////// ALGO NAIVE /////////////////////////////////////////////////////
 

	 public Circle z(ArrayList<Point> points){
	        
	      if (points.isEmpty()) {
	    	  return null;
	      }
	      double cX,cY,radius;
	      
	      for (Point p: points){
	          for (Point q: points){
	              cX = (p.x+q.x)/2;
	              cY = (p.y+q.y)/2;
	              radius = 0.25*((p.x-q.x)*(p.x-q.x)+(p.y-q.y)*(p.y-q.y));
	              boolean toucher = true;
	             
	              for (Point s: points) {
	                  if ((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY)>radius){
	                      toucher = false;
	                      break;
	                  }
	              }
	              if (toucher == true) {
	            	  return new Circle(new Point((int)cX,(int)cY),(int)Math.sqrt(radius));
	              }
	          }
	      }
	      
	      double cercleminX=0;
	      double cercleminY=0;
	      double cercleminR=Double.MAX_VALUE;
	      Point cerclemincenter = new Point();
	      cerclemincenter.setLocation(cercleminX, cercleminY);
	      double a1,a2,b1,b2;

	      for (int i=0 ; i<points.size() ; i++){
	          for (int j=i+1 ; j<points.size() ; j++){
	              for (int k=j+1; k<points.size() ; k++){
	                  Point p=points.get(i);Point q=points.get(j);Point r=points.get(k);
	                  
	                  if (arecolinear(p,q,r)) continue;
	                  
	                  if ((p.y==q.y)||(p.y==r.y)) {
	                      if (p.y==q.y){
	                         p=points.get(k); r=points.get(i); 
	                      }else {
	                    	  p=points.get(j); q=points.get(i);
	                      }}
	                  
	                  a1=(q.x-p.x)/(double)(p.y-q.y);
	                  b1=(0.5 * (p.y+q.y))-((q.x-p.x)/(double)(p.y-q.y))*(0.5 * (p.x+q.x));
	                  a2=(r.x-p.x)/(double)(p.y-r.y);
	                  b2=(0.5 * (p.y+r.y))-a2*(0.5 * (p.x+r.x));
	                  
	                  cX=(b2-b1)/(double)(a1-a2);
	                  cY=a1*cX+b1;
	                  radius=(p.x-cX)*(p.x-cX)+(p.y-cY)*(p.y-cY);
	                  
	                  if (radius>=cercleminR) continue;
	                  boolean toucher = true;
	                  
	                  for (Point s: points) {
	                      if ((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY)>radius){
	                          toucher = false;
	                          break;}}

	                  if (toucher == true){
	                	  cercleminX=cX;cercleminY=cY;cercleminR=radius;
	                	  cerclemincenter = new Point();
	                	  cerclemincenter.setLocation(cercleminX, cercleminY);
	                  }}}}
	      return new Circle(cerclemincenter,(int)Math.sqrt(cercleminR));
	  }
	
	
	
	public boolean arecolinear(Point p, Point q, Point r) {
		
		if ((q.x-p.x)*(r.y-p.y)-(q.y-p.y)*(r.x-p.x)==0) {
			return true;
		}
		return false;
		
	}
	

   
}
