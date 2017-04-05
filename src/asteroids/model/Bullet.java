package asteroids.model;

public class Bullet {

	/**
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @post Sets the position of the current bullet to the parameters xPosition and yPositionif they are valid.
	 * 			| if this.isValidPosition(xPosition, yPosition)
	 * 			|		new.getPosition()[0] = xPosition
	 * 			|		new.getPosition()[1] = yPostition
	 */
	public void setPosition(double xPosition, double yPosition){
		if (!isValidPosition(xPosition,yPosition)){
			throw new IllegalArgumentException();
		}
		else{
			this.xPosition = xPosition;
			this.yPosition = yPosition;
		}
	}
	
	/**
	 * 
	 * @return Returns the current position of the current bullet in an array of length 2 with the current xPosition on index 0
	 * 				and the current yPosition on index 1.
	 * 			| returns [this.xPosition, this.yPosition]
	 */
	public double[] getPosition(){
		return new double[] {this.xPosition,this.yPosition};
		
	}
	
	/**
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @return Returns true if the given xPosition and the given yPosition is greater or equal to 0 and they are both not equal to Double.Nan.
	 * 			| returns (! (xPosition < 0 || yPosition < 0 || xPosition == Double.NaN || yPosition == Double.NaN))
	 */
	public boolean isValidPosition(double xPosition, double yPosition){
		if (xPosition < 0 || yPosition < 0 || xPosition == Double.NaN || yPosition == Double.NaN){
			return false;
		}
		else{
			return true;
		}
	}
	
	private double xPosition;
	private double yPosition;
	
	
	/**
	 * 
	 * @param xVelocity
	 * @param yVelocity
	 * @post Sets the xVelocity and yVelocity of the current bullet to the given parameters if they are valid.
	 * 			| if this.isValidVelocity(xVelocity, yVelocity)
	 * 			|	new.getVelocity()[0] = xVelocity
	 * 			| 	new.getVelocity()[1] = yVelocity
	 */
	public void setVelocity(double xVelocity, double yVelocity){
		if (isValidVelocity(xVelocity,yVelocity)){
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;
		}
	}
	
	/**
	 * 
	 * @return Returns the current velocity of the current bullet in an array of length 2 with this.xVelocity on index 0 and this.yVelocity on index 1.
	 * 				| return [this.xVelocity, this.yVelocity]
	 */
	public double[] getVelocity(){
		return new double[] {this.xVelocity,this.yVelocity};
	}
	
	/**
	 * 
	 * @param xVelocity
	 * @param yVelocity
	 * @return Returns true if the total calculated velocity is less than this.maxVelocity.
	 * 			| return (sqrt(xVelocity ** 2 + yVelocity ** 2) 
	 */
	
	public boolean isValidVelocity(double xVelocity, double yVelocity){
		if (Math.sqrt(Math.pow(xVelocity,2) + Math.pow(yVelocity, 2)) < this.maxVelocity){
			return true;
		}
		else{
			return false;
		}
	}
	
	private double xVelocity;
	private double yVelocity;
	
	
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
	
	

	private double mass;
	public final double maxVelocity = 300000;
	private double radius;
	public final double density = 7.8 * Math.pow(10,12);
}
