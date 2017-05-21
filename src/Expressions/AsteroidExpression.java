package Expressions;

import java.util.Set;

import asteroids.model.Asteroid;
import asteroids.model.Ship;

public class AsteroidExpression extends EntityExpression {

	public AsteroidExpression(){
		
	}
	@Override
	public Asteroid evaluate(){
		Set<Asteroid> asteroids = this.getFunction().getProgram().getShip().getWorld().getAllAsteroids();
		Ship ship = this.getFunction().getProgram().getShip();
		double distTo = Double.POSITIVE_INFINITY;
		Asteroid closestTo = null;
		for (Asteroid asteroid : asteroids){
			if(ship.getDistanceBetween(asteroid) < distTo){
				distTo = ship.getDistanceBetween(asteroid);
				closestTo = asteroid;
			}
		}
		return closestTo;
	}
}
