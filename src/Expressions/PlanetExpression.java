package Expressions;

import java.util.Set;

import asteroids.model.MinorPlanet;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetExpression extends EntityExpression{
	
	public PlanetExpression(SourceLocation sourceLocation){
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
	public MinorPlanet evaluate(){
		System.out.println("Program =  " + this.getProgram());
		Set<MinorPlanet> minorPlanets = this.getProgram().getShip().getWorld().getAllMinorPlanets();
		Ship ship = this.getProgram().getShip();
		MinorPlanet closestTo = null;
		double distTo = Double.POSITIVE_INFINITY;
		for (MinorPlanet minorPlanet : minorPlanets){
			if (ship.getDistanceBetween(minorPlanet) < distTo){
				distTo = ship.getDistanceBetween(minorPlanet);
				closestTo = minorPlanet;
			}
		}
		return closestTo;
	}
}
