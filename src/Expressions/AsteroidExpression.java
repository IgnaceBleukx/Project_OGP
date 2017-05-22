package Expressions;

import java.util.Set;

import asteroids.model.Asteroid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class AsteroidExpression extends EntityExpression {

	public AsteroidExpression(SourceLocation sourceLocation){
		setSourceLocation(sourceLocation);
	}
	
	private SourceLocation sourceLocation;
	
	@Override
	public Asteroid evaluate(){
		Set<Asteroid> asteroids = this.getProgram().getShip().getWorld().getAllAsteroids();
		Ship ship = this.getProgram().getShip();
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

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
