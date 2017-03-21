package asteroids.model;

public class Bullet {

	public void setPosition(double xPosition, double yPosition){
		if (!isValidPosition(xPosition,yPosition)){
			throw new IllegalArgumentException();
		}
		else{
			this.xPosition = xPosition;
			this.yPosition = yPosition;
		}
	}
	
	public double[] getPosition(){
		return new double[] {this.xPosition,this.yPosition};
		
	}
	
	public boolean isValidPosition(double xPosition, double yPosition){
		if (xPosition < 0 || yPosition < 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void setVelocity(double xVelocity, double yVelocity){
		if (isValidVelocity(xVelocity,yVelocity)){
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;
		}
	}
	
	public double[] getVelocity(){
		return new double[] {this.xVelocity,this.yVelocity};
	}
	
	public boolean isValidVelocity(double xVelocity, double yVelocity){
		if (Math.sqrt(Math.pow(xVelocity,2) + Math.pow(yVelocity, 2)) < this.maxVelocity){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!isValidRadius(radius)){
			throw new IllegalArgumentException();
		}
		else{
			this.radius = radius;
		}
	}
	
	public double getRadius(){
		return this.radius;
	}
	
	public boolean isValidRadius(double radius){
		if (radius > 1 && radius < Double.MAX_VALUE){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setMass(){
		this.mass = Math.pow(this.getRadius(),3) * Math.PI * 4/3 * this.density;
	}

	public double getMass(){
		return this.mass;
	}
	
	public boolean isValidMass(double mass){
		return (mass == Math.pow(this.getRadius(),3) * Math.PI * 4/3 * this.density);
	}
	
	private double xPosition;
	private double yPosition;
	private double xVelocity;
	private double yVelocity;
	private double mass;
	private double maxVelocity = 300000;
	private double radius;
	private double density = 7.8 * Math.pow(10,12);
}
