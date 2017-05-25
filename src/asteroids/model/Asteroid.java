package asteroids.model;

/**
 * @author Ignace Bleukx and Mats Ruell
 * A class of Asteroids with an X-Position, Y-Position, X-Velocity, Y-Velocity and radius.
 * Subclass of class MinorPlanet
 */

public class Asteroid extends MinorPlanet {

	/**
	 * This method is a constructor for an asteroid.
	 * @param xPosition The position along the x-axis of the asteroid.
	 * @param yPosition The position along the y-axis of the asteroid.
	 * @param xVelocity The velocity along the x-axis of the asteroid.
	 * @param yVelocity The velocity along the y-axis of the asteroid.
	 * @param radius: The radius of the asteroid.
	 */
	public Asteroid(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius){
		this.setPosition(xPosition, yPosition);
		this.setVelocity(xVelocity, yVelocity);
		this.setRadius(radius);
		this.setMass();
	}
	
	/**
	 * This method returns the density of an asteroid.
	 * @return Returns the density of an asteroid.
	 * 			| return this.density
	 */
	@Override
	public double getDensity(){
		return this.density;
	}

	private double density = 2.65E12;
	
	/**
	 * This method terminates the current asteroid.
	 * @Post The asteroid is removed from the world and the terminationstate is set to true.
	 * 			| !this.getWorld().getAllAsteroids().contains(new)
	 * 			| this.isTerminated == true
	 */
	@Override
	public void terminate(){
		this.getWorld().removeAsteroid(this);
		this.isTerminated =true;
	}
	
	/**
	 * This method returns true if the asteroid is terminated and false if it is not.
	 * @return Returns true if the asteroid is terminated and false if it is not.
	 * 			| return this.isTerminated
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	private boolean isTerminated;

	/**
	 * This method resolves a collision with a ship, this means that the ship is terminated.
	 * @param ship: The ship that collides with this asteroid.
	 * @Post The ship is terminated.
	 * 			| ship.isTerminated() == true
	 */
	public void shipAsteroidCollision(Ship ship) {
		ship.terminate();
	}
}
