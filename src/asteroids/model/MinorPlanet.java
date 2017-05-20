package asteroids.model;

public class MinorPlanet extends Entity {

	public void setMass(){
		if (this instanceof Asteroid){
			this.mass = 4.0 * Math.pow(this.getRadius(), 3) * this.getDensity() / 4.0;
		}
	}
	
	public double getMass(){
		return this.mass;
	}
	
	private double mass;
	
	@Deprecated
	public double getDensity(){
		return Double.POSITIVE_INFINITY;
	}
	
	@Override
	public boolean isValidRadius(double radius){
		return (radius > 5 && !Double.isNaN(radius));
	}

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

	public void shipCollision(Ship otherEntity) {
		if (this instanceof Planetoid){
			((Planetoid)this).shipPlanetoidCollision((Ship)otherEntity);
		}
		if (this instanceof Asteroid){
			((Asteroid)this).shipAsteroidCollision((Ship)otherEntity);
		}
	}
}
