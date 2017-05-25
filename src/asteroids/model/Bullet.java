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
		this.setMass();
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

	
	
	public void setMass(){
		this.mass = 4.0 * this.density * Math.PI * Math.pow(this.getRadius(),3) / 3.0;
	}
	
	public double getMass(){
		return this.mass;
	}
	
//	public boolean isValidMass(double mass){
//		return (mass != Double.NaN && mass >= (4/3) * Math.PI * Math.pow(this.getRadius(),3) * this.density);
//	}
	
	private double mass;
	public final double density = 7.8E12;
	
	/**
	 * @return Returns true if the current bullet is loaded on a ship.
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
	
	public void setBulletScource(Ship ship){
		this.bulletScource = ship;
	}
	
	/**
	 * @return Returns the ship the bullet was previousely fired from.
	 * @return Returns null if the bullet is loaded on a ship.
	 * 			| return this.firedFrom
	 */
	public Ship getBulletScource(){
		return this.bulletScource;
	}
	
	private Ship isPartOfShip;
	private Ship bulletScource;
	
	/**
	 * @post Terminates the current bullet. There are not references to the current bullet left.
	 */
	@Override
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

	/**
	 * Sets the collision state to the parameter state. If the collisionState of a bullet is false,
	 * 		it means it is loaded on a ship and thus can not collide with any other entities.
	 * @param state
	 * @post new.getCollisionState() = state
	 */
	public void setCollisionState(boolean state){
		this.canCollide = state;
	}
	
	/**
	 * Returns the collisionstate of the current bullet.
	 * @return Returns the collisionstate
	 */
	public boolean getCollisionState(){
		return this.canCollide;
	}
	private boolean canCollide = true;

	public void setBoundaryCollisions(int boundaryCollisions){
		this.boundaryCollisions = boundaryCollisions;
	}
	
	public void addBoundaryCollision(){
		this.boundaryCollisions += 1;
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

	/**
	 * Sets the velocity of the current bullet to reflect the effect of a boundarycollision if the maximum amount of boundarycollisions
	 * of the current bullet is not exceeded. If the maximum is exceeded, the bullet is terminated.
	 * @post If the bullet has exceeded its maximum amount of boundary collisions, the bullet is terminated.
	 * 			| if (this.getBoundaryCollisions > this.getMaxBoundaryCollisions)
	 * 						this.terminate()
	 * @post If the bullet collides with a horizontal boundary, its Y-velocity is set tot the negative of its previous Y-velocity.
	 * 			| if (yPosition == world.yDimension - this.radius || yPosition == this.radius)
	 *  					new.getVelocity()[1] = -this.getVelocity()[0]
	 * @post If the bullet	 collides with a vertical boundary, its X-Velocity is set to the negative of its previous X-velocity.
	 * 			| if (xPosition == world.xDimension - this.radius || xPosition == this.radius)
	 *  					new.getVelocity()[0] = -this.getVelocity()[0]
	 */
	public void boundaryCollision() {
		if (this.getBoundaryCollisions() < this.getMaxBoundaryCollisions()){
			this.addBoundaryCollision();
			double xPosColBound = this.getPositionCollisionBoundary()[0];
			double yPosColBound = this.getPositionCollisionBoundary()[1];
			
			if (xPosColBound == 0 || xPosColBound == this.getWorld().getDimension()[0]){
				System.out.println("negative x velocity");
				this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);
			}
			if (yPosColBound == 0 || yPosColBound == this.getWorld().getDimension()[1]){
				System.out.println("negative y velocity");
				this.setVelocity(this.getVelocity()[0], -this.getVelocity()[1]);
			}
		}
		else{
			this.terminate();
		}
	}

	/**
	 * Reflects the effect of a collision with another entity.
	 * @param otherEntity
	 * @post If the other entity is the ship the bullet is fired from, the bullet is reloaded on the ship.
	 * 			| if  (this.getBulletScource.equals(otherEntity)
	 * 					this.getBulletScource().getAllBulletsOnShip().contains(new)
	 * @post If the other entity is not the ship the bullet is fired from, both this bullet and the other entity are terminated.
	 * 			| if (!this.getBulletScource().equals(otherEntity)
	 * 					this.terminate()
	 * 					otherEntity.terminate()
	 */
	public void collision(Entity otherEntity){
		System.out.println("this=" + this);
		System.out.println("otherEntity=" + otherEntity);
		System.out.println("getbulletsource=" + this.getBulletScource());
		
		if (this.getBulletScource() == null){
			otherEntity.terminate();
			this.terminate();
			
		}
	
		else if (this.getBulletScource().equals(otherEntity)){
			System.out.println("shipotherentity=" + (Ship) otherEntity);
			this.getWorld().removeBulletFromWorld(this);
			((Ship) otherEntity).loadBulletOnShip(this);
		}
		else{
			otherEntity.terminate();
			this.terminate();
		}
		
	}


}



	
