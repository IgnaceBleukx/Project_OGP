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
		double deltaX = otherMinorPlanet.getPosition()[0] - this.getPosition()[0];
		double deltaY = otherMinorPlanet.getPosition()[1] - this.getPosition()[1];
		double deltaR = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) );
		double deltaV = Math.sqrt(Math.pow(otherMinorPlanet.getVelocity()[0] - this.getVelocity()[0],2 ) + Math.pow(otherMinorPlanet.getVelocity()[1] - this.getVelocity()[1], 2));
		double sigma = this.getDistanceBetween(otherMinorPlanet);
		
		double j = (2 * this.getMass()*otherMinorPlanet.getMass() * (deltaV * deltaR)) / (sigma * (this.getMass() + otherMinorPlanet.getMass()));
		double xJ = (j * deltaX) / sigma;
		double yJ = (j * deltaY) / sigma;
		
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
