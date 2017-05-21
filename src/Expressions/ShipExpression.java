package Expressions;

import java.util.Set;

import asteroids.model.Ship;

public class ShipExpression extends EntityExpression {

	public ShipExpression(){
		
	}
	
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
}
