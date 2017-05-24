package Expressions;

import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.MinorPlanet;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class PlanetExpression extends EntityExpression {

	public PlanetExpression(SourceLocation sourceLocation){
		this.setSourceLocation(sourceLocation);
	}
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	private SourceLocation sourceLocation;
	
	@Override
	public Entity evaluate() throws ModelException{
		if (getProgram()!= null){
			Set<MinorPlanet> minorPlanets = getProgram().getShip().getWorld().getAllMinorPlanets();
			Ship ship = getProgram().getShip();
			double distTo = Double.POSITIVE_INFINITY;
			MinorPlanet toReturn = null;
			for (MinorPlanet minorPlanet : minorPlanets){
				if (minorPlanet.getDistanceBetween(ship) < distTo){
					toReturn = minorPlanet;
					distTo = minorPlanet.getDistanceBetween(ship);
				}
			}
			if (toReturn == null){
				throw new ModelException("There are no MinorPlanets in the world");
			}
			else{
				return toReturn;
			}
		}
		else{
			throw new ModelException("The expression is not part of a program");
		}
	}
}
