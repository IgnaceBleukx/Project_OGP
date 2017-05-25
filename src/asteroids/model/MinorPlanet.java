package asteroids.model;

/**
 * A class that represents a Minor Planet, subclass of Entity
 * @author Ignace Bleukx and Mats Ruell
 *@Invar The radius will always be valid.
 *			| this.isValidRadius(this.getRadius)
 */
public class MinorPlanet extends Entity {

	/**
	 * This method sets the mass of the current Minor Planet.
	 * @Post The mass of the minorPlanet equals 4/3 * pi * radius * density
	 * 			| new.getMass() == 4/3 * this.getRadius * this.getDensity() * pi
	 */
	public void setMass(){	
		this.mass = (4.0/3.0)*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
	}
	
	/**
	 * This method returns the mass of the minor planet.
	 * @return Returns the mass of the minor planet.
	 * 			| return this.mass
	 */
	public double getMass(){
		return this.mass;
	}
	
	private double mass;
	
	/**
	 * This method returns the minimum radius of the minor planet.
	 * @return Returns the minimum radius of the minor planet.
	 * 			| return this.minimumRadius
	 */
	public double getMinimumRadius(){
		return this.minimumRadius;
	}
	
	private double minimumRadius = 5;

	/**
	 * This method is overridden by all subclasses of MinorPlanet
	 */
	public double getDensity(){
		throw new IllegalStateException("This method should be overrided by all subclasses");
	}
	
	/**
	 * This method returns true if the given radius is valid.
	 * @return Returns true if the given radius is larger than or equal to the minimumradius of the minor planet.
	 * 			| return radius >= this.getMinimumRadius() 
	 */
	@Override
	public boolean isValidRadius(double radius){
		return (radius >= this.getMinimumRadius() && !Double.isNaN(radius));
	}

	/**
	 * This method represents a collision with another minor planet.
	 * @param otherMinorPlanet The other minor planet witch this one collides with.
	 * @Post The velocity of this minor planet and the other minor planet are updated to represent the collision.
	 */
	public void minorPlanetCollision(MinorPlanet otherMinorPlanet) {
		double deltaRX = otherMinorPlanet.getPosition()[0] - this.getPosition()[0];
		double deltaRY = otherMinorPlanet.getPosition()[1] - this.getPosition()[1];
		double deltaVX = otherMinorPlanet.getVelocity()[0] - this.getVelocity()[0];
		double deltaVY = otherMinorPlanet.getVelocity()[1] - this.getVelocity()[1];
		double deltaRV = deltaVX*deltaRX + deltaVY*deltaRY;
		double sigma = this.getRadius() + otherMinorPlanet.getRadius();
		
		double j = (2 * this.getMass()*otherMinorPlanet.getMass() *deltaRV) / (sigma * (this.getMass() + otherMinorPlanet.getMass()));
		double xJ = (j * deltaRX) / sigma;
		double yJ = (j * deltaRY) / sigma;
		
		this.setVelocity(this.getVelocity()[0] + xJ/this.getMass(), this.getVelocity()[1] + yJ/this.getMass());
		otherMinorPlanet.setVelocity(otherMinorPlanet.getVelocity()[0] - xJ/otherMinorPlanet.getMass(), otherMinorPlanet.getVelocity()[1] - yJ/otherMinorPlanet.getMass());
		
	}
	/**
	 * This method determines the kind of collision with another ship and calls its corresponding methods.
	 * @param ship The ship the current Minor planet collides with.
	 */
	public void shipCollision(Ship ship) {
		if (this instanceof Planetoid){
			((Planetoid)this).shipPlanetoidCollision(ship);
		}
		if (this instanceof Asteroid){
			((Asteroid)this).shipAsteroidCollision(ship);
		}
	}
}
