package algorithms;

import java.awt.Point;
import java.util.ArrayList;

import supportGUI.Circle;



public class naive {
	
	
	 public Circle calculCercleMin(ArrayList<Point> points){
	        
	      if (points.isEmpty()) {
	    	  return null;
	      }
	      double cX,cY,radius;
	      
	      for (Point p: points){
	          for (Point q: points){										// Pour chaque couple de points
	              cX = (p.x+q.x)/2;											// On calcul la coordonnee x du centre entre p et q
	              cY = (p.y+q.y)/2;											// On calcul la coordonnee y du centre entre p et q
	              radius = 0.25*((p.x-q.x)*(p.x-q.x)+(p.y-q.y)*(p.y-q.y));  // On calcul le rayon selon p et q
	              boolean toucher = true;
	             
	              for (Point s: points) {									// Pour tout les points du plan, on verifie si ceux ci sont tous contenu dans le cercle de centre et de rayon precedement calculer
	                  if ((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY)>radius){		// Si la distance entre le centre du cercle et point est superieur au rayon du cercle, alors celui ci n'est pas couvert 
	                      toucher = false;									// le cercle ne couvre donc pas tous les points du plan
	                      break;
	                  }
	              }
	              if (toucher == true) {									// Sinon, le cercle couvre tous les points du plan et est un cercle minimum
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
	                  
	                  if (arecolinear(p,q,r)) continue;						// On verifie si 3 points du plan sont colineaire
	                  
	                  if ((p.y==q.y)||(p.y==r.y)) {							// On echange les points si ceux ci sont sur la meme ligne
	                      if (p.y==q.y){
	                         p=points.get(k); r=points.get(i); 
	                      }else {
	                    	  p=points.get(j); q=points.get(i);
	                      }}
	                  	// Calcul des coordonnees du cercle circonscrit 
	                  	//soit y=alpha1*x+beta1 l'equation de la droite passant par m et perpendiculaire a la droite (pq)
	                    //soit y=alpha2*x+beta2 l'equation de la droite passant par n et perpendiculaire a la droite (pr)
	                    
	                  a1=(q.x-p.x)/(double)(p.y-q.y);						
	                  b1=(0.5 * (p.y+q.y))-((q.x-p.x)/(double)(p.y-q.y))*(0.5 * (p.x+q.x));
	                  a2=(r.x-p.x)/(double)(p.y-r.y);						
	                  b2=(0.5 * (p.y+r.y))-a2*(0.5 * (p.x+r.x));
	                  
	                  cX=(b2-b1)/(double)(a1-a2);							//Coordonee x du centre du cercle circonscrit
	                  cY=a1*cX+b1;											//Coordonee x du centre du cercle circonscrit
	                  radius=(p.x-cX)*(p.x-cX)+(p.y-cY)*(p.y-cY);			//rayon du cercle circonscrit
	                  
	                  if (radius>=cercleminR) continue;						// Si ce rayon est superieur au rayon actuel, on continue
	                  boolean toucher = true;
	                  
	                  for (Point s: points) {								// On verifie si tous les points sont contenu dans le cercle precedement calculer
	                      if ((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY)>radius){
	                          toucher = false;
	                          break;}}										// On sort des qu'un points est en dehors du cercle pour optimiser la fonction

	                  if (toucher == true){									//Le cercle contient tous les points car le boolean n'est pas mis a false
	                	  cercleminX=cX;cercleminY=cY;cercleminR=radius;	// On stock les coordonnees du cercle 
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
