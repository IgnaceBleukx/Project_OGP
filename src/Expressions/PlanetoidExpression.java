package Expressions;

import java.util.Set;

import asteroids.model.Planetoid;
import asteroids.model.Ship;

public class PlanetoidExpression extends EntityExpression {

	public PlanetoidExpression(){
		
	}
	
	@Override
	public Planetoid evaluate(){
		Set<Planetoid> planetoids = this.getProgram().getShip().getWorld().getAllPlanetoids();
		Ship ship = this.getProgram().getShip();
		Planetoid closestTo = null;
		double distTo = Double.POSITIVE_INFINITY;
		for (Planetoid planetoid : planetoids){
			if (ship.getDistanceBetween(planetoid) < distTo){
				distTo = ship.getDistanceBetween(planetoid);
				closestTo = planetoid;
			}
		}
		return closestTo;
	}
}
