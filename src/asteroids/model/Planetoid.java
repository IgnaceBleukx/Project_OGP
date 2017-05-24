package asteroids.model;

import java.util.Random;

/**
 * A class of Planetoids with an X-Position, Y-Position, X-Velocity, Y-Velocity, Radius and Total Travel Distance.
 * Subclass of MinorPlanet.
 *
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
			this.totalTraveledDistance += calculatedDistance;
			}
			
			else{ 
				this.terminate();
			}
		}
	}
	
	@Override
	public void terminate(){
		if (this.getWorld() != null){
		this.getWorld().removePlanetoid(this);
		}
		this.isTerminated =true;
	}
	
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	private boolean isTerminated;
	
	@Override
	public double getDensity(){
		return this.density;
	}
	private double density = 0.917E12;
	
	public double getTotalTraveledDistance(){
		return this.totalTraveledDistance;
	}
	public void setTotalTraveledDistance(double totalTraveledDistance){
		this.totalTraveledDistance = totalTraveledDistance;
	}
	
	private double totalTraveledDistance;

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
