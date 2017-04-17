package asteroids.model;

public class Bullet extends Entity {
	
	/**
	 * @param x
	 * @param y
	 * @param xVelocity
	 * @param yVelocity
	 * @param radius
	 * @throws IllegalArgumentException
	 * 			If one of the given parameters is invalid, a new exception of the type IllegalArgumentException will be thrown.
	 * This method creates a new object of type Bullet with given parameters.
	 */
	
	public Bullet (double x, double y, double xVelocity, double yVelocity, double radius) {
		this.setPosition(x, y);
		this.setVelocity(xVelocity, yVelocity);
		this.setRadius(radius);
	}

	/**
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @post Sets the position of the current bullet to the parameters xPosition and yPosition if they are valid.
	 * 			| if this.isValidPosition(xPosition, yPosition)
	 * 			|		new.getPosition()[0] = xPosition
	 * 			|		new.getPosition()[1] = yPostition
	 */
	public void setPosition(double xPosition, double yPosition){
		if (this.getWorld() == null){
			this.xPosition = xPosition;
			this.yPosition = yPosition;
			
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
	public final double maxVelocity = 300000;
	
	/**
	 * 
	 * @param radius
	 * @throws Throws IllegalArgumentException if the radius is not valid.
	 * 			| if (! isValidRadius(radius))
	 * 			|		throw IllegalArgumentException
	 * @post Sets the radius of the current bullet to the given parameter radius if it is valid.
	 * 			| if isValidRadius(radius)
	 * 			|		new.getRadius = radius
	 */
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!isValidRadius(radius)){
			throw new IllegalArgumentException();
		}
		else{
			this.radius = radius;
		}
	}
	
	/**
	 * 
	 * @return Returns the radius of the current bullet.
	 * 			| return this.getRadius
	 */
	public double getRadius(){
		return this.radius;
	}
	
	private double radius;

	
	/**
	 * 
	 * @param radius
	 * @return returns true if the given radius is greater than 1 and if the radius is smaller than Double.MAX_VALUE.
	 * 			| return (radius < 1 && radius < Double.MAX_VALUE)
	 */
	public boolean isValidRadius(double radius){
		if (this.isPartOfAShip()){
			return (radius > this.getShip().getRadius() * .1 && radius < this.getShip().getRadius());
		}
		else{
			return (radius > 1 && radius < Double.POSITIVE_INFINITY);
		}
	}
	
	/**
	 * @post Sets the mass of the current bullet to the calculated mass using this.denisty.
	 * 			| new.getMass() == this.getRadius ** 3 * 4/3 * pi * this.density
	 */
	public void setMass(){
		this.mass = Math.pow(this.getRadius(),3) * Math.PI * 4/3 * this.density;
	}

	/**
	 * 
	 * @return Returns the mass of the current bullet.
	 * 			| return this.mass
	 */
	public double getMass(){
		return this.mass;
	}
	
	/**
	 * 
	 * @param mass
	 * @return Returns true if the given mass is equal to the current radius to the power of 3, multiplied with the denisity and 4/3 * pi.
	 * 			| return (mass == this.getRadius() ** 3 * pi * 4/3 * this.desitity 
	 */
	public boolean isValidMass(double mass){
		return (mass == Math.pow(this.getRadius(),3) * Math.PI * 4/3 * this.density);
	}
	
	private double mass;
	public final double density = 7.8 * Math.pow(10,12);
	
	
	
	/**
	 * 
	 * @param world
	 * @post Sets the world of the current bullet to the given parameter world.\
	 * 			| new.getWorld = world
	 */
	public void setWorld(World world){
		this.isPartOfWorld = world;
	}
	
	/**
	 * @return Returns the world the current bullet is located in.
	 */
	public World getWorld(){
		return this.isPartOfWorld;
	}
	
	/**
	 * @return Returns true if the world is equal to the world of the ship if the bullet is loaded on one.
	 * 			| if (this.isPartOfAShip())
	 * 			|		return (this.getShip().getWorld() == world)
	 * 			| return !(world == null)
	 */
	public boolean isValidWorld(World world){
		if (this.getShip() != null){
			return this.getShip().getWorld() == world;
		}
		else{
			return !(world == null);
		}
	}
	
	/**
	 * @return Returns true if the bullet is part of a world.
	 * @return Returns false if the bullet is not part of a world.
	 * 			|return this.getWorld() == null
	 */
	public boolean isPartOfAWorld(){
		return !(this.getWorld() == null);
	}
	
	private World isPartOfWorld;
	
	/**
	 * @return Returns true if the current bullet is loaded onz a ship.
	 * @return Returns false if the current bullet is not loaded on a ship.
	 */
	public boolean isPartOfAShip(){
		return  !(this.getShip() == null);
	}
	
	/**
	 * @param ship The ship the bullet must be loaded on.
	 * @post The bullet is part of the ship.
	 * 			| new.getShip == ship
	 */
	public void setShip(Ship ship){
		this.isPartOfShip = ship;
		this.firedFrom = null;
	}
	
	/**
	 * @return Returns true if the current bullet is loaded on a ship.
	 * @return Returns false if the current bullet is not loaded on a ship.
	 * 			| return this.isPartOfAShip
	 */
	public Ship getShip(){
		return this.isPartOfShip;
	}
	
	/**
	 * @return Returns true if the given ship is in the same world as the bullet and exists.
	 			| return ship.getWorld() == this.getWorld() && ship != null
	 */
	public boolean isValidShip(Ship ship){
		return ship.getWorld() == this.getWorld() && ship != null;
		}
	
	/**
	 * @post The bullet is not a part of the ship it was loaded on anymore.
	 * 			| new.getShip == null;
	 */
	public void fired(){
		this.firedFrom = this.getShip();
		this.setShip(null);
	}
	
	/**
	 * @return Returns the ship the bullet was previousely fired from.
	 * @return Returns null if the bullet is loaded on a ship.
	 * 			| return this.firedFrom
	 */
	public Ship firedFrom(){
		return this.firedFrom;
	}
	
	private Ship isPartOfShip;
	private Ship firedFrom = null;
	
	/**
	 * @post Terminates the current bullet. There are not references to the current bullet left.
	 */
	public void terminate(){
		if (this.isPartOfAShip()) {
		this.getShip().removeBulletFromShip(this);
		}
		this.getWorld().removeBulletToWorld(this);
		this.isTerminated = true;
		
	}
	
	/**
	 * 
	 * @return Returns true if the current bullet is terminated.
	 * @return Returns false if the current bullet is not terminated.
	 * 			| return this.isTerminated
	 */
	public boolean isTerminated() {
		return isTerminated;
	}

	private boolean isTerminated = false;
}
