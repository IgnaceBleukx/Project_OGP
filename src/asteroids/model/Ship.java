package asteroids.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class Ship extends Entity {
	/**
	 * @param xVelocity
	 * @param yVelocity
	 * @param xPosition
	 * @param yPosition
	 * @param orientation
	 * @param radius
	 * @param maxVelocity
	 * @throws IllegalArgumentException
	 * 			If one of the given parameters is invalid, a new exception of the type IllegalArgumentException will be thrown.
	 * This method creates a new object of type Ship with given parameters.
	 */
	public Ship (double xPosition, double yPosition,double xVelocity, double yVelocity, double radius, double orientation, double mass) throws IllegalArgumentException{
		try{
			this.setVelocity(xVelocity, yVelocity);
			this.setPosition(xPosition, yPosition);
			this.setOrientation(orientation);
			this.setRadius(radius);
			this.setMass(mass);
		}catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}
	
	public double getMinimumRadius(){
		return this.minimumRadius;
	}
	
	private double minimumRadius = 10;

	
	/**
	 * This method creates a new object of the type Ship with default paramaters.'
	 * Default parameters for velocity are 0 and 0.
	 * Default parameters for position are 0 and 0.
	 * Default parameter for Orientation is 0.
	 * Default parameter for radius is 10.
	 */
	public Ship(){
		this.setVelocity(0, 0);
		this.setPosition(0,0);
		this.setOrientation(0);
		this.setRadius(10);
	}
	
	
	/**
	 * Checks if the given double is a valid mass for the current ship.
	 * @param mass
	 * @return Returns true if the mass is valid
	 * 			| if(mass >= this.radius **3 * 4/3 * pi)
	 * 				result = true
	 * @return Returns false if the mass is invalid.
	 * 			| if(mass < this.radius **3 * 4/3 * pi)
	 *				result = false
	 * @return Returns false if the mass is not a number
	 * 			| if (Double.isNaN(mass))
	 * 				result = false
	 */
	public void setMass(double mass){
		if (this.isValidMass(mass)){
			this.mass = mass;
		}
		else{
			this.mass = 4.0 * Math.PI * Math.pow(this.getRadius(),3) * this.density / 3.0;
		}
	}
	
	public double getMass(){
		return this.mass;
	}
	
	
	private double mass;
	
	
	public boolean isValidMass(double mass){
		return (!Double.isNaN(mass) && mass > 4.0 * this.density * Math.PI * Math.pow(this.getRadius(),3) / 3.0);
	}
	
	private double density = 1.42E12;
	
	
	/**
	 * 
	 * @return Returns the minimum radius of the ship.
	 */
	public double getMinimumShipRadius(){
		return this.minimumShipRadius;
	}
	private double minimumShipRadius = 10;
	
	/**
  	 * Enables the thruster of the current ship if not enabled yet.
  	 * 			| if (thrusterstate == false)
 	 * 			|		thrusterstate = true
 	 * 			|		thrust(thrusterforce / mass)
 	 * 
 	 */
 	public void thrustOn(){
		this.thrusterState = true;
		this.thrust(thrusterForce/this.getMass());  		
		}
			
	/**
	 * Disables the thruster of the ship.
	 * 			|		thrust(0)
	 */
	 public void thrustOff(){
		this.thrusterState = false;
	  	}	
		
	
	/**
	 * 
	 * @return Returns the current state of the thruster.
	 * 			| returns this.thrusterState
	 */
	public boolean inspectThruster(){
		return this.thrusterState;
	}
	
	public double getShipAcceleration(){
		if(this.getMass() != 0 && this.inspectThruster()){
			return (this.getThrusterForce())/(this.getMass());
		}
		else{
			return 0;
		}
	}
	
	private boolean thrusterState;
	private double thrusterForce = 1.1E18;
	
	/**
	 * 
	 * @param angle
	 * 			The angle that needs to be added to the current orientation.
	 * @pre  The sum of the current orientation and the given angle must be smaller or equal to 2*PI and must be larger than or equal to 0.
	 * 			| this.getOrientation() + angle <= 2*PI && this.getOrientation() + angle >= 0.
	 * @post Increases the current orientation with the value of the parameter angle.
	 * 			| new.orientation = old.orientation + angle
	 */
	public void rotate(double angle){
		this.setOrientation(this.getOrientation() + angle);
	}
	
	/**
	 * 
	 * @param a
	 * @post	The velocity to be added to the current velocity.
	 * 			If the total orientation exceeds the maximum velocity. The total will be limited to the maximum velocity.
	 * 			| new.xVelocity = this.xVelocity + a * cos(orientation)
	 * 			| new.yVelocity = this.yVelocity + a * sin(orientation)
	 */
	public void thrust(double a){
		if (a <= 0){
			a = 0;
		}
		
		this.setVelocity(this.getVelocity()[0] + a * Math.cos(this.getOrientation()), this.getVelocity()[1] + Math.sin(this.getOrientation()));
	}
	
	/**
	 * 
	 * @return Returns a hash set of all bullets loaded on the ship.
	 */
	public Set<Bullet> getAllBulletsShip() {
		return this.allBulletsShip;
	}
	
	/**
	 * 
	 * @param bullet
	 * @post The given bullet is loaded on the ship, thus the velocity and position is equal to the velocity and position of the current ship.
	 * 			| bullet.getPosition == this.getPosition
	 * 			| bullet.getVelocity == this.getVelocity
	 */
	public void loadBulletOnShip(Bullet bullet) throws IllegalArgumentException{
		if (this.getNbBulletsOnShip() < this.maxNbOfBullets){
			if (bullet == null){
				throw new IllegalArgumentException();
			}
			this.allBulletsShip.add(bullet);
			bullet.setShip(this);
			bullet.setCollisionState(false);
			bullet.setPosition(this.getPosition()[0], this.getPosition()[1]);
			bullet.setVelocity(this.getVelocity()[0], this.getVelocity()[1]);
		}
		
	}
	
	public void loadBulletsOnShip(Collection<Bullet> bullets) throws IllegalArgumentException{
		try{
			for (Bullet bullet : bullets){
		
			this.loadBulletOnShip(bullet);
			}
		}catch (IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}
	/**
	 * 
	 * @return Returns the amount of bullets loaded on the ship.
	 */
	public int getNbBulletsOnShip() {
		return this.allBulletsShip.size();
	}
	
	/**
	 * @throws IllegalArgumentException Throws a new IllegalArgumentException if the given bullet is not loaded on the ship.
	 * 			| if (! allBulletsShip.contains(bullet)
	 * 			| 		throw IllegalArgumentException
	 * @param bullet
	 * @effect Removes the given bullet from the ship if it is loaded on it.
	 * 			| ! this.getAllBulletsShip().contains(this)
	 * @effect The mass of the ship is lowered with the mass of the bullet.
	 * 			| new.getMass == this.getMass - bullet.getMass
	 * 
	 */
	public void removeBulletFromShip(Bullet bullet) throws IllegalArgumentException{
		if (this.allBulletsShip.contains(bullet)){
			this.allBulletsShip.remove(bullet);
			bullet.setShip(null);
			this.setMass(this.getMass() - bullet.getMass());
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * 
	 * @throws IndexOutOfBoundsException
	 * @post The first bullet in allBulletsShip is fired from the ship and thus is removed from the ship.
	 * 			|	 * 
	 */
	public void fire() throws IndexOutOfBoundsException{
		if (this.getWorld() != null && this.getNbBulletsOnShip() > 0){
			Bullet bulletToBeFired = allBulletsShip.iterator().next();
			this.removeBulletFromShip(bulletToBeFired);
			this.getWorld().addBulletToWorld(bulletToBeFired);
			bulletToBeFired.setCollisionState(true);
			bulletToBeFired.setBulletScource(this);
			double meetingpointX = this.getPosition()[0] + this.getRadius() * Math.cos(this.getOrientation());
			double meetingpointY = this.getPosition()[1] + this.getRadius() * Math.sin(this.getOrientation());
		
			double bulletPosX = meetingpointX + bulletToBeFired.getRadius() * Math.cos(this.getOrientation());
			double bulletPosY = meetingpointY + bulletToBeFired.getRadius() * Math.sin(this.getOrientation());
			System.out.println("bulletPosY in func = " +bulletPosY);
			bulletToBeFired.setPosition(bulletPosX, bulletPosY);
		
			double xVelocityBullet = this.getVelocity()[0] + Math.cos(this.getOrientation()) * 250;
			double yVelocityBullet = this.getVelocity()[1] + Math.sin(this.getOrientation()) * 250;
			bulletToBeFired.setVelocity(xVelocityBullet, yVelocityBullet);
		
			if (bulletToBeFired.isOutOfBounds()){
				bulletToBeFired.terminate();
				return;
			}
			
			for (Entity entity : this.getWorld().getAllEntities()){
				if (bulletToBeFired.overlap(entity) && !bulletToBeFired.equals(entity)){
					this.getWorld().collisionResolver(bulletToBeFired,entity);
					System.out.println("EnityCollision");
					System.out.println(entity.toString());
					break;
				}
			}
			
			
		
		}

	}

	private Set<Bullet> allBulletsShip = new HashSet<Bullet>();
	
	
	private double maxNbOfBullets = 15;
	
	/**
	 * Terminates the current ship
	 * @post All bullets loaded on the ship are terminate.
	 * 			| for bullet in getAllBulletsShip()
	 * 			|		bullet.terminate
	 * @post The current ship is removed from the world it is currently in.
	 * 			| this.getWorld.removeShip(this)
	 */
	@Override
	public void terminate(){
		while(!this.getAllBulletsShip().isEmpty()){
			Bullet bullet = this.getAllBulletsShip().iterator().next();
			bullet.terminate();
			}
		this.getWorld().removeShipFromWorld(this);
		this.isTerminated = true;
	}
	/**
	 * 
	 * @return Returns true if the current ship is terminated, returns false if it is not.
	 * 			| returns this.isTerminated
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	public double getThrusterForce() {
		return thrusterForce;
	}

	public void setThrusterForce(double thrusterForce) {
		this.thrusterForce = thrusterForce;
	}

	private boolean isTerminated = false;
	

	public void shipCollision(Ship otherShip){
		double deltaX = otherShip.getPosition()[0] - this.getPosition()[0];
		double deltaY = otherShip.getPosition()[1] - this.getPosition()[1];
		double deltaR = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) );
		double deltaV = Math.sqrt(Math.pow(otherShip.getVelocity()[0] - this.getVelocity()[0],2 ) + Math.pow(otherShip.getVelocity()[1] - this.getVelocity()[1], 2));
		double sigma = this.getDistanceBetween(otherShip);
		
		double j = (2 * this.getMass()*otherShip.getMass() * (deltaV * deltaR)) / (sigma * (this.getMass() + otherShip.getMass()));
		double xJ = (j * deltaX) / sigma;
		double yJ = (j * deltaY) / sigma;
		
		this.setVelocity(this.getVelocity()[0] + xJ/this.getMass(), this.getVelocity()[1] + yJ/this.getMass());
		otherShip.setVelocity(otherShip.getVelocity()[0] - xJ/otherShip.getMass(), otherShip.getVelocity()[1] - yJ/otherShip.getMass());
	}
	
	public void loadProgram(Program program){
		this.program = program;
	}
	public Program getShipProgram(){
		return this.program;
	}
	private Program program;
	
	
	

}