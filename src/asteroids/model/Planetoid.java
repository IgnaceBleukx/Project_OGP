package asteroids.model;

import java.util.Random;

/**
 * @author Ignace Bleukx and Mats Ruell
 * A class of Planetoids with an X-Position, Y-Position, X-Velocity, Y-Velocity, Radius and Total Travel Distance.
 * Subclass of MinorPlanet.
 */

public class Planetoid extends MinorPlanet {

	public Planetoid(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double totalTravelDistance){
		this.setPosition(xPosition, yPosition);
		this.setVelocity(xVelocity, yVelocity);
		this.setTotalTraveledDistance(totalTravelDistance);
		if (radius - 0.000001 * totalTravelDistance < 5){
			this.terminate();
		}
		else{
			this.setRadius(radius - 0.000001 * totalTravelDistance);
			this.setMass();
		}
	}
	
	
	/**
	 * This method moves the current planetoid during a given time dt. If the planetoid shrinks below its minimum radius, it is terminated.
	 * @param dt: The time over which the planetoid must be evolved.
	 */
	@Override
	public void move(double dt){
		if (dt < 0){
			throw new IllegalArgumentException();
		}
		else{
			double calculatedDistance = Math.sqrt(Math.pow(this.getVelocity()[0] * dt,2) +Math.pow(this.getVelocity()[1] * dt,2)); 
			this.setPosition(this.getPosition()[0] + this.getVelocity()[0] * dt, this.getPosition()[1] + this.getVelocity()[1] *dt);
			if (this.isValidRadius(this.getRadius() - 0.000001*calculatedDistance)){
				this.setRadius(this.getRadius() - 0.000001 * calculatedDistance);
				this.setTotalTraveledDistance(getTotalTraveledDistance() + calculatedDistance);
			}
			else{ 
				this.terminate();
			}
		}
	}
	
	/**
	 * This method terminated the current planetoid. If its radius was larger than or equal to 30 the moment before it was terminated,
	 * 	2 new asteroids will be born out of the planetoid with a radius half of the current planetoid and a speed of double the speed
	 * 		of the current planetoid.
	 * @post The planetoid is removed from the world, if the radius was larger than or equal to 30, 2 new asteroids will be added to the world.
	 * 			| if (this.getRadius() >= 30)
	 * 				newworld.getAllAsteroids().size() = world.getAllAsteroids().size + 2
	 * 			| new.isTerminated()
	 * 				
	 */
	@Override
	public void terminate(){
		if (this.getWorld() != null){
		this.getWorld().removePlanetoid(this);
		}
		
		if (this.getRadius() >= 30){
			Random randomGen = new Random();
			double alpha = randomGen.nextDouble() * Math.PI;
			double vPx = this.getVelocity()[1];
			double vPy = this.getVelocity()[0];
			double vAx = Math.sqrt((4 * (Math.pow(vPx, 2) + Math.pow(vPy, 2))) / (Math.tan(alpha) +1));
			double vAy = Math.tan(alpha) * vAx;
			double beta = Math.PI - alpha;
			double xSup = Math.cos(beta) * this.getRadius() / 2;
			double ySup = Math.sin(beta) * this.getRadius() / 2;
			this.getWorld().addAsteroid(new Asteroid(this.getPosition()[0] + xSup, this.getPosition()[1] + ySup, vAx, vAy, this.getRadius()/2));
			this.getWorld().addAsteroid(new Asteroid(this.getPosition()[0] - xSup, this.getPosition()[1] - ySup, -vAx, -vAy, this.getRadius()/2));
		}
		
		this.isTerminated =true;
	}
	
	/**
	 * This method returns if the Planetoid is terminated.
	 * @return Returns true if the planetoid is terminated, returns false if it is not.
	 * 			| return this.isTerminated
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	private boolean isTerminated;
	
	/**
	 * This method returns the density of the planetoid.
	 * @return Returns the density of the planetoid.
	 * 			| return this.density
	 */
	@Override
	public double getDensity(){
		return this.density;
	}
	
	private double density = 0.917E12;
	
	/**
	 * This method returns the total travaled distance of the planetoid.
	 * @return The total traveled distance of the planetoid.
	 * 			| return this.totalTraveledDistance
	 */
	public double getTotalTraveledDistance(){
		return this.totalTraveledDistance;
	}
	
	/**
	 * This method sets the total traveled distance of the planetoid.
	 * @param totalTraveledDistance
	 * @Post The total traveled distance equals the parameter totalTraveledDistance.
	 * 			| new.getTotalTraveledDistance() = totalTraveledDistance
	 */
	public void setTotalTraveledDistance(double totalTraveledDistance){
		this.totalTraveledDistance = totalTraveledDistance;
	}
	
	private double totalTraveledDistance;

	/**
	 * This method resolves a collisision between a ship and a planetoid.
	 * The ship will be teleported to another place in the world, keeping its current velocity and radius, if the ship spawns on another entity,
	 * 	the ship is terminated. 
	 * @param ship: The ship that collides with this planetoid.
	 * 
	 */
	public void shipPlanetoidCollision(Ship ship) {
		Random randomGen = new Random();
		ship.setPosition(randomGen.nextDouble()*this.getWorld().getDimension()[0],randomGen.nextDouble()*this.getWorld().getDimension()[1]);
		for (Entity entity : this.getWorld().getAllEntities()){
			if (ship.overlap(entity) && !entity.equals(ship)){
				ship.terminate();
			}
		}
	}
	
}
