package asteroids.model;

public class Planetoid extends MinorPlanet {

	
	public Planetoid(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double totalTravelDistance){
		this.setPosition(xPosition, yPosition);
		this.setVelocity(xVelocity, yVelocity);
		this.setRadius(radius - 0.0001 * totalTravelDistance);
		this.setMass();
	}
	
	
	@Override
	public void move(double dt){
		if (dt < 0){
			throw new IllegalArgumentException();
		}
		else{
			double calculatedDistance = Math.sqrt(Math.pow(this.getVelocity()[0] * dt - this.getPosition()[1],2) +Math.pow(this.getVelocity()[0] * dt - this.getPosition()[1],2)); 
			this.setPosition(this.getPosition()[0] + this.getVelocity()[0] * dt, this.getPosition()[1] + this.getVelocity()[1] *dt);
			this.setRadius(this.getRadius() - 0.0001 * calculatedDistance);
			this.totalTraveledDistance += calculatedDistance;
		}
	}
	
	@Override
	public void terminate(){
		this.getWorld().removePlanetoid(this);
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
	
	private double totalTraveledDistance;

	public void shipPlanetoidCollision(Ship otherEntity) {
		// TODO Auto-generated method stub
		
	}
	
}
