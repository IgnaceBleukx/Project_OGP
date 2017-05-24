package asteroids.model;

/**
 * A class of Asteroids with an X-Position, Y-Position, X-Velocity, Y-Velocity and radius.
 * Subclass of class MinorPlanet
 *
 */

public class Asteroid extends MinorPlanet {

	public Asteroid(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius){
		this.setPosition(xPosition, yPosition);
		this.setVelocity(xVelocity, yVelocity);
		this.setRadius(radius);
		this.setMass();
	}
	
	@Override
	public double getDensity(){
		return this.density;
	}
	private double density = 2.65E12;
	
	@Override
	public void terminate(){
		this.getWorld().removeAsteroid(this);
		this.isTerminated =true;
	}
	
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	private boolean isTerminated;

	public void shipAsteroidCollision(Ship ship) {
		ship.terminate();
	}
}
