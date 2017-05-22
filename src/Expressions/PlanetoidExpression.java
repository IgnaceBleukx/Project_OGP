package Expressions;

import java.util.Set;

import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetoidExpression extends EntityExpression {

	public PlanetoidExpression(SourceLocation sourceLocation){
		setSourceLocation(sourceLocation);
	}
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	private SourceLocation sourceLocation;
	
	@Override
	public Planetoid evaluate(){
		Set<Planetoid> planetoids = this.getFunction().getProgram().getShip().getWorld().getAllPlanetoids();
		Ship ship = this.getFunction().getProgram().getShip();
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
