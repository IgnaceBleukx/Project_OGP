package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements asteroids.part2.facade.IFacade {
	
	public Ship createShip() throws ModelException{
		Ship newShip = new Ship();
		return newShip;
	}
	
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction, double mass)
			throws ModelException{
		try{
			Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);
			return ship;
		}
		catch(IllegalArgumentException exc){
			throw new ModelException("ModelException in createShip");
		}
	}
	
	public double[] getShipPosition(Ship ship) throws ModelException{
		try{
			return ship.getPosition();
		}catch(NullPointerException exc){
			throw new ModelException("ModelException in getShipPosition");
		}
	}
	
	public double[] getShipVelocity(Ship ship) throws ModelException{
		return ship.getVelocity();
	}
	
	public double getShipRadius(Ship ship) throws ModelException{
		return ship.getRadius();
	}

	public double getShipOrientation(Ship ship) throws ModelException{
		return ship.getOrientation();
	}
	
	
	public void move(Ship ship, double dt) throws ModelException{
		ship.move(dt);
	}
	
	public void thrust(Ship ship, double amount) throws ModelException{
		ship.thrust(amount);
	}
	
	public void turn(Ship ship, double angle) throws ModelException{
		ship.rotate(angle);
	}

	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException{
		return ship1.getDistanceBetween(ship2);
	}
	
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException{
		return ship1.overlap(ship2);
	}
	
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException{
		return ship1.getTimeToCollapse(ship2);
	}

	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException{
		return ship1.getPositionOfCollapse(ship2);
	}



	
}
