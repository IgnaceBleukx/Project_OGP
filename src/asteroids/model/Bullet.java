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
	 * @param radius
	 * @throws IllegalArgumentException if the radius is not valid.
	 *			| if (! isValidRadius(radius)
	 *				throw IllegalArgumentException
	 * @post Sets the radius of the ship on the parameter radius.
	 * 			| new.radius = radius
	 */			
	public void setRadius(double radius) throws IllegalArgumentException{
		if (!isValidRadius(radius)){
			throw new IllegalArgumentException();
		}
		else{
			this.radius = radius;
		}
	}
	
	/**
	 * 
	 * @param radius
	 * 			The radius to check.
	 * @return True if the radius is larger than 10. False if the radius smaller than 10 or the given radius is not a number.
	 * 			| result = radius > 10
	 */
	public boolean isValidRadius(double radius){
		if (Double.isNaN(radius)){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * 
	 * @return Returns the current radius of the ship.
	 * 			| result = this.radius
	 */
	public double getRadius(){
		return this.radius;
	}
	
	private double radius;

	
	
	public void setMass(double mass){
		if (this.isValidMass(mass)){
			this.mass = mass;
		}
		else{
			this.mass = (4/3) * Math.PI * Math.pow(this.getRadius(),3) * this.density;
		}
	}
	
	public double getMass(){
		return this.mass;
	}
	
	public boolean isValidMass(double mass){
		return (mass != Double.NaN);
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
	public void setFiredState(){
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
		
		if (this.getShip() != null){
		this.getShip().removeBulletFromShip(this);
		}
		if (this.getWorld() != null){
		this.getWorld().removeBulletFromWorld(this);
		}
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

	public void setCollisionState(boolean state){
		this.canCollide = state;
	}
	
	public boolean getCollisionState(){
		return this.canCollide;
	}
	private boolean canCollide = true;

	public void setBoundaryCollisions(int boundaryCollisions){
		this.boundaryCollisions = boundaryCollisions;
	}
	public int getBoundaryCollisions(){
		return this.boundaryCollisions;
	}
	private int boundaryCollisions = 0;

	public void setMaxBoundaryCollisions(int collisions){
		this.maxBoundaryCollisions = collisions;
	}
	public int getMaxBoundaryCollisions(){
		return this.maxBoundaryCollisions;
	}
	private int maxBoundaryCollisions = 2;

	public void boundaryCollision() {
		if (this.getBoundaryCollisions() < this.getMaxBoundaryCollisions()){
			if (this.getPosition()[0] >= this.getRadius() * 0.99 && this.getPosition()[0] <= this.getRadius() *1.01){
				this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);
			}
			if (this.getPosition()[1] >= this.getRadius() * 0.99 && this.getPosition()[1] <= this.getRadius() *1.01){
				this.setVelocity(this.getVelocity()[0],-this.getVelocity()[1]);
			}
			if (this.getPosition()[0] >= (this.getWorld().getDimension()[0] - this.getRadius())*0.99 && this.getPosition()[0] >= (this.getWorld().getDimension()[0] - this.getRadius())*1.01){
				this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);
			}
			if (this.getPosition()[1] >= (this.getWorld().getDimension()[1] - this.getRadius())*0.99 && this.getPosition()[1] >= (this.getWorld().getDimension()[1] - this.getRadius())*1.01){
				this.setVelocity(this.getVelocity()[0], -this.getVelocity()[1]);
			}
			else{
				throw new IllegalStateException();
			}
		}
	}

	public void bulletCollision(Bullet otherBullet){
		otherBullet.terminate();
		this.terminate();
	}


}



	
