package asteroids.model;

import java.util.HashSet;
import java.util.Set;

public class Object {
	
	public void setVelocity(double xVelocity, double yVelocity){
		if (this.isValidVelocity(xVelocity, yVelocity)){
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;	
			for (Object bullet : this.getAllBulletsShip()){
				bullet.setVelocity(xVelocity, yVelocity);
			}
		}
	}
	
	/**
	 * @return Returns true if the calculated velocity of xVelocity and yVelociity does not exceed the maximum velocity for the ship.
	 * @return Returns false if the calculated velocity of xVelocity and yVelocity does exceed the maximum velocity for the ship.
	 * 			| result = sqrt(xVelocity**2 + yVelocity **2) < maxVelocity
	 */
	public boolean isValidVelocity(double xVelocity, double yVelocity){
		if (xVelocity == Double.NaN || yVelocity == Double.NaN){
			return false;
		}
		if (Math.sqrt(Math.pow(this.xVelocity + xVelocity,2) + Math.pow(this.yVelocity + yVelocity, 2))<= maxVelocity){
			return true;
		}
		else {
			return false;
			}
	}
	
	/**
	 * @return Returns an array of length 2 with the current xVelocity on index 0 and the current yVelocity on index 1.
	 * 			| result = [xVelocity, yVelocity]
	 */
	public double[] getVelocity(){
		double[] velocity;
		velocity = new double[] {this.xVelocity, this.yVelocity};
		return velocity;		
	}
	
	private double xVelocity;
	private double yVelocity;
	private final double maxVelocity = 300000;


	
	/**
	 * 
	 * @return Returns the current orientation of the ship.
	 * 			| result = this.orientation
	 */
	public double getOrientation() {
		return this.orientation;
	}

	/**
	 * @param orientation
	 * @pre  The value of the parameter orientation must be larger than or equal to 0 and smaller than or equal to 2 * pi.
	 * 			| 0 <= orientation <= 2 * pi
	 * @post Sets the current orientation of the ship to the parameter orientation.
	 * 			| new.orientation = orientation 
	 */
	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
	
	private double orientation;

	
	/**
	 * @throws NullPointerException if the position of the ship is not initialized.
	 * 			| if xPosition == 0.0d || yPosition == 0.0d:
	 * 					throw NullPointerException
	 * @return Returns the current position of the ship in an array of length 2 with the xPosition on index 0 and yPosition on index 1.
	 * 			result = [xPosition, yPosition]
	 */
	public double[] getPosition(){		
		double[] position;
		position = new double[] {this.xPosition, this.yPosition};
		return position;		
	}
	
	public boolean isValidPosition(double xPosition, double yPosition){
		if (xPosition == Double.NaN || yPosition == Double.NaN){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * @param xPosition
	 * @param yPosition
	 * @throws IllegalArgumentException if the given xPosition or yPosition is not a valid number.
	 * 			| if (xPosition == NaN || yPosition == NaN)
	 * 					throw IllegalArgumentException	 * 					
	 * @post Sets the current position of the ship to the parameters xPosition and yPosition.
	 * 			| new.xPosition = xPosition
	 * 			| new.yPosition = yPosition
	 */
	public void setPosition(double xPosition, double yPosition) throws IllegalArgumentException{
		if (!isValidPosition(xPosition, yPosition)){
			throw new IllegalArgumentException();
		}
		else{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		for (Object bullet : this.getAllBulletsShip()){
			bullet.setPosition(xPosition, yPosition);
		}
		}
	}
	
	private double xPosition;
	private double yPosition;
	
	
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
		if (radius < 10 || radius == Double.NaN){
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

	
	/**
	 * Sets the mass of the current ship to the given mass if it is valid.
	 * @param mass
	 * 		if (isValidMass(mass)
	 * 			this.mass = mass
	 */
	public void setMass(double mass){
		if (this.isValidMass(mass)){
			this.mass = mass;
		}
	}
	
	/**
	 * 
	 * @return Returns the mass of the current ship.
	 * result = this.mass;
 	 */
	public double getMass(){
		return this.mass;
	}
	
	/**
	 * Checks if the given double is a valid mass for the current ship.
	 * @param mass
	 * @return Returns true if the mass is valid
	 * 			if(mass >= this.radius **3 * 4/3 * pi)
	 * 				result = true
	 * @return Returns false if the mass is invalid.
	 * 			if(mass < this.radius **3 * 4/3 * pi)
	 *				result = false
	 */
	public boolean isValidMass(double mass){
		if (mass >= Math.pow(this.getRadius(),3) * 4/3 * Math.PI*minDensity){
			return true;
		}
		else{
			return false;
		}
	}
	
	private double mass;
	private double minDensity = 1.42*Math.pow(10, 12);
	

	
public double getTimeCollisionBoundary(){
		
		double boundaryTime = Double.POSITIVE_INFINITY;
		if(this.getWorld() == null){
			return boundaryTime;
		}
		
			if(this.getVelocity()[0] > 0) {
				if(this.getVelocity()[1] > 0) {
					System.out.println("object = " + this);
					boundaryTime = Math.min(((this.getWorld().getDimension()[0]-this.getRadius())-this.getPosition()[0])/(this.getVelocity()[0]),
							((this.getWorld().getDimension()[1]-this.getRadius())-this.getPosition()[1])/(this.getVelocity()[1]));
				}
				else {
					boundaryTime = Math.min(((this.getWorld().getDimension()[0]-this.getRadius())-this.getPosition()[0])/(this.getVelocity()[0]),
							(this.getRadius()-this.getPosition()[1])/(this.getVelocity()[1]));
				}
			
			}
			else {
				if(this.getVelocity()[1] > 0) {
					boundaryTime = Math.min((this.getRadius()-this.getPosition()[0])/(this.getVelocity()[0]),
							((this.getWorld().getDimension()[1]-this.getRadius())-this.getPosition()[1])/(this.getVelocity()[1]));
				}
				else {
					boundaryTime = Math.min((this.getRadius()-this.getPosition()[0])/(this.getVelocity()[0]),
							(this.getRadius()-this.getPosition()[1])/(this.getVelocity()[1]));
				}	
			}
		System.out.println("BoundadryTime = " + boundaryTime);
		return boundaryTime;
	}

public double[] getPositionCollisionBoundary(){
	
	double[] boundaryColPos;
	double xPosColBound = this.getVelocity()[0]*this.getTimeCollisionBoundary() + this.getPosition()[0];
	double yPosColBound = this.getVelocity()[1]*this.getTimeCollisionBoundary() + this.getPosition()[1];
	boundaryColPos = new double[] {xPosColBound,yPosColBound};

	return boundaryColPos;

}

<<<<<<< HEAD
public double getTimeCollisionEntity(Object otherShip){
		if (((otherShip.getVelocity()[0] - this.getVelocity()[0]) * (otherShip.getPosition()[0]-this.getPosition()[0]) + 
				(otherShip.getVelocity()[1] - this.getVelocity()[1]) * (otherShip.getPosition()[1]-this.getPosition()[1])) >= 0){
			return Double.POSITIVE_INFINITY;
		}
		
		else {
			return -(((otherShip.getVelocity()[0] - this.getVelocity()[0]) * (otherShip.getPosition()[0]-this.getPosition()[0]) + 
				(otherShip.getVelocity()[1] - this.getVelocity()[1]) * (otherShip.getPosition()[1]-this.getPosition()[1]) + 
					Math.sqrt((Math.pow((otherShip.getVelocity()[0] - this.getVelocity()[0]) * (otherShip.getPosition()[0]-this.getPosition()[0]) + 
								(otherShip.getVelocity()[1] - this.getVelocity()[1]) * (otherShip.getPosition()[1]-this.getPosition()[1]),2)) 		-
								((Math.pow(otherShip.getVelocity()[0] - this.getVelocity()[0],2)) + (Math.pow(otherShip.getVelocity()[1] - this.getVelocity()[1],2))) * 
									((Math.pow((otherShip.getPosition()[0]) - this.getPosition()[0],2)) + (Math.pow((otherShip.getPosition()[1]) - this.getPosition()[1],2))-
											Math.pow((otherShip.getRadius()+this.getRadius()),2))))/((Math.pow(otherShip.getVelocity()[0] - this.getVelocity()[0],2)) + 
													(Math.pow(otherShip.getVelocity()[1] - this.getVelocity()[1],2))));
		}
=======
public double getTimeCollisionEntity(Object otherEntity){
	double timeCollisionEntities = Double.POSITIVE_INFINITY;
		   timeCollisionEntities = 
				-(((this.getVelocity()[0] - otherEntity.getVelocity()[0]) * (this.getPosition()[0]-otherEntity.getPosition()[0]) + 
				(this.getVelocity()[1] - otherEntity.getVelocity()[1]) * (this.getPosition()[1]-otherEntity.getPosition()[1]) + 
					Math.sqrt((Math.pow((this.getVelocity()[0] - otherEntity.getVelocity()[0]) * (this.getPosition()[0]-otherEntity.getPosition()[0]) + 
								(this.getVelocity()[1] - otherEntity.getVelocity()[1]) * (this.getPosition()[1]-otherEntity.getPosition()[1]),2)) 		-
								((Math.pow(this.getVelocity()[0] - otherEntity.getVelocity()[0],2)) + (Math.pow(this.getVelocity()[1] - otherEntity.getVelocity()[1],2))) * 
									((Math.pow((this.getPosition()[0]) - otherEntity.getPosition()[0],2)) + (Math.pow((this.getPosition()[1]) - otherEntity.getPosition()[1],2))-
											Math.pow((this.getRadius()+otherEntity.getRadius()),2))))/((Math.pow(this.getVelocity()[0] - otherEntity.getVelocity()[0],2)) + 
													(Math.pow(this.getVelocity()[1] - otherEntity.getVelocity()[1],2))));
	
	return timeCollisionEntities;
>>>>>>> branch 'master' of https://github.com/IgnaceBleukx/Project_OGP
}



public double[] getPositionCollisionEntity(Object otherEntity){
	
	double[] posColEntities = {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY};
<<<<<<< HEAD
	double xPosColEntities = entity1.getVelocity()[0]*(this.getTimeCollisionEntity(entity2)) + entity1.getPosition()[0];
	double yPosColEntities = entity1.getVelocity()[1]*(this.getTimeCollisionEntity(entity2)) + entity1.getPosition()[1];
=======
	double xPosColEntities = this.getVelocity()[0]*getTimeCollisionEntity(otherEntity) + this.getPosition()[0];
	double yPosColEntities = this.getVelocity()[1]*getTimeCollisionEntity(otherEntity) + this.getPosition()[1];
>>>>>>> branch 'master' of https://github.com/IgnaceBleukx/Project_OGP
	posColEntities = new double[] {xPosColEntities,yPosColEntities};			
	return posColEntities;
}







public void setWorld(World world){
	this.isPartOfWorld = world;
}

public World getWorld(){
	return this.isPartOfWorld;
}

public boolean isValidWorld(World world){
	return world != null;
}

private World isPartOfWorld;


public Object getCollisionEntity1() {
	return CollisionEntity1;
}

public void setCollisionEntity1(Object collisionEntity1) {
	CollisionEntity1 = collisionEntity1;
}



public Object getCollisionEntity2() {
	return CollisionEntity2;
}

public void setCollisionEntity2(Object collisionEntity2) {
	CollisionEntity2 = collisionEntity2;
}

private Object CollisionEntity1;
private Object CollisionEntity2;

public boolean isPartOfAShip(){
	return  !(this.getShip() == null);
}

public void setShip(Ship ship){
	this.isPartOfShip = ship;
}

public Ship getShip(){
	return this.isPartOfShip;
}

public boolean isValidShip(Ship ship){
	return this.isPartOfWorld == null;
}

public void fired(){
	this.firedFrom = this.getShip();
	this.setShip(null);
}

public Ship firedFrom(){
	return this.firedFrom;
}
private Ship isPartOfShip;
private Ship firedFrom = null;


public void loadBulletOnShip(Bullet bullet){
	if (this.getNbBulletsOnShip() < this.maxNbOfBullets){
		this.allBulletsShip.add(bullet);
		bullet.setPosition(this.getPosition()[0], this.getPosition()[1]);
		bullet.setVelocity(this.getVelocity()[0], this.getVelocity()[1]);
	}
	
}

public int getNbBulletsOnShip() {
	return this.allBulletsShip.size();
}

public Set<Bullet> getAllBulletsShip() {
	return this.allBulletsShip;
}

private Set<Bullet> allBulletsShip = new HashSet<Bullet>();
private double maxNbOfBullets = 15;

}
