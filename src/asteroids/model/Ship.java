package asteroids.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;




/**
 * A Class of Ships with an X-Velocity, Y-Velocity, X-Position, Y-Position, Orientation, Radius and Mass.
 * Subclass of class Entity
 * 
 * @invar The mass of a ship must be a valid mass.
 * 			| isValidMass(getMass())
 * 
 */

public class Ship extends Entity {
	/**
	 * 
	 * 
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
	
	/**
	 * This method creates a new object of the type Ship with default paramaters.'
	 * Default parameters for velocity are 0 and 0.
	 * Default parameters for position are 0 and 0.
	 * Default parameter for Orientation is 0.
	 * Default parameter for radius is 10.
	 * Default parameter for mass is 0. (Gets adjusted by extended constructor, by invoking this.setMass).
	 */
	
	public Ship(){
		this(0, 0, 0, 0, 10, 0, 0);
	}
	
	
	public double getMinimumRadius(){
		return this.minimumRadius;
	}
	
	private double minimumRadius = 10;

	

	
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
	 // Possible to invoke method against objects that does not satisfy all invariants.
	public void setMass(double mass){
		if (this.isValidMass(mass)){
			this.mass = mass;
		}
		else{
			this.mass = 4.0 * Math.PI * Math.pow(this.getRadius(),3) * this.density / 3.0;
		}
	}
	
	
	/**
	 * Returns the mass of the Ship.
	 * @return this.mass
	 */
	public double getMass(){
		return this.mass;
	}
	
	
	private double mass;
	
	
	/**
	 * Checks whether the given mass is a valid mass for any Ship.
	 * @param mass
	 * 		| The mass to check.
	 * @return True if and only if the given mass is a number and greater than (4/3)*density*PI*(radius**3)
	 */
	
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
  	 * Enables the thruster of the current ship.
 	 */
	
 	public void thrustOn(){
		this.thrusterState = true;  		
		}
			
	/**
	 * Disables the thruster of the current ship.
	 */
	 public void thrustOff(){
		this.thrusterState = false;
	  	}	
		
	
	/**
	 * Returns the current state of the thruster.
	 * @return this.thrusterState
	 * 			| returns true if thrusterState = true
	 * 			| returns false if thrusterState = false
	 */
	public boolean inspectThruster(){
		return this.thrusterState;
	}
	
	
	/**
	 * Returns the acceleration of the ship.
	 * @return thrusterForce/mass if the mass is not 0 and the thrusterState = true
	 * 			else returns 0.
	 */
	
	
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
	
	@Deprecated
	public void thrust(double a){
		if (a < 0){
			a = 0;
		}
		
	}
	
	/** 
	 * @param a
	 * 		 | The ship's acceleration, which equals the thrusterforce divided by the ship's total mass.
	 * @param dt
	 * 		 | The given time duration of thrust.
	 * @effect in the case that the ship's acceleration is negative, a = 0 and nothing happens to the velocity.
	 * @effect The velocity is updated based on the ship's acceleration and the time duration of thrust given 
	 * if the newly calculated velocity is valid.
	 * 		 | new.getVelocity[0] = this.getVelocity[0] + a*cos(this.getOrientation)*dt
	 * 		 | new.getVelocity[1] = this.getVelocity[1] + a*sin(this.getOrientation)*dt
	 * @effect if the newly calculated velocity isn't valid (greater than the max velocity maxVelocity) the 
	 * velocity gets updated to the max velocity maxVelocity without changing the new direction of the velocity.
	 * @post	The new velocity is adjusted and isn't greater than the max velocity maxVelocity.
	 */
	public void thrust(double a, double dt){
		if (a <= 0){
			a = 0;
		}

		double newXVelocity = this.getVelocity()[0] + a * Math.cos(this.getOrientation())*dt; 
		double newYVelocity = this.getVelocity()[1] + a * Math.sin(this.getOrientation())*dt;
		double tempVelocity = Math.sqrt(Math.pow(newXVelocity, 2) + Math.pow(newYVelocity, 2));
		
		if (this.isValidVelocity(newXVelocity, newYVelocity)) {
			System.out.println(this.isValidVelocity(newXVelocity, newYVelocity));
			this.setVelocity(newXVelocity, newYVelocity);
		}
		
		else {
			// De xvelocity gained (verliest) wat yvelocity verliest (gained), vandaar sin en cos omgewisseld

			double newMaxXVelocity = newXVelocity - (tempVelocity - this.getMaxVelocity())*Math.sin(this.getOrientation());
			double newMaxYVelocity = newYVelocity - (tempVelocity - this.getMaxVelocity())*Math.cos(this.getOrientation());
			this.setVelocity(newMaxXVelocity, newMaxYVelocity);
					
		}
		
	}
	
	/**
	 * 
	 * @return Returns a hash set of all bullets loaded on the ship.
	 */
	public Set<Bullet> getAllBulletsShip() {
		return this.allBulletsShip;
	}
	
	/** 
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
	
	/**
	 * returns the thrusterForce of the ship.
	 * @return this.thrusterForce
	 */
	public double getThrusterForce() {
		return this.thrusterForce;
	}

	/**
	 * @param thrusterForce
	 * 		 | thrusterForce is the force of the thruster of the ship.
	 * @effect
	 * 		 | sets thrusterForce to the new thrusterForce.
	 */
	public void setThrusterForce(double thrusterForce) {
		this.thrusterForce = thrusterForce;
	}

	private boolean isTerminated = false;
	

	
	
	public void shipCollision(Ship otherShip){

		double deltaRX = otherShip.getPosition()[0] - this.getPosition()[0];
		double deltaRY = otherShip.getPosition()[1] - this.getPosition()[1];
		double deltaVX = otherShip.getVelocity()[0] - this.getVelocity()[0];
		double deltaVY = otherShip.getVelocity()[1] - this.getVelocity()[1];
		double deltaRV = deltaVX*deltaRX + deltaVY*deltaRY;
		double sigma = this.getRadius() + otherShip.getRadius();
		
		double j = (2 * this.getMass()*otherShip.getMass() * deltaRV) / (sigma * (this.getMass() + otherShip.getMass()));

		double xJ = (j * deltaRX) / sigma;
		double yJ = (j * deltaRY) / sigma;
		this.setVelocity((this.getVelocity()[0] + xJ/this.getMass()), (this.getVelocity()[1] + yJ/this.getMass()));
		otherShip.setVelocity((otherShip.getVelocity()[0] - xJ/otherShip.getMass()), (otherShip.getVelocity()[1] - yJ/otherShip.getMass()));

	}
	
	public void loadProgram(Program program){
		this.program = program;
		this.getShipProgram().setShip(this);
	}
	public Program getShipProgram(){
		return this.program;
	}
	private Program program;
	
	
	

}