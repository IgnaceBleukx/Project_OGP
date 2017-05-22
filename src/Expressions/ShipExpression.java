package Expressions;

import java.util.Set;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ShipExpression extends EntityExpression {

	public ShipExpression(SourceLocation sourceLocation){
		setSourceLocation(sourceLocation);
	}
	
	private SourceLocation sourceLocation;
	
	@Override
	public Ship evaluate(){
		Set<Ship> ships = this.getFunction().getProgram().getShip().getWorld().getAllShips();
		Ship thisShip = this.getFunction().getProgram().getShip();
		Ship closestTo = null;
		double distTo = Double.POSITIVE_INFINITY;
		for (Ship ship : ships){
			if (!ship.equals(thisShip) && ship.getDistanceBetween(thisShip) < distTo){
				distTo = ship.getDistanceBetween(thisShip);
				closestTo = ship;
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
