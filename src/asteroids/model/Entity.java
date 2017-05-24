package asteroids.model;

/**
 * A class of Entities. Super class of classes Ship, Bullet and MinorPlanet.
 * 
 *
 */
public class Entity {
	
	/**
	 * Sets the velocity of the current entity to the parameters xVelocity and yVelocity.
	 * @param xVelocity
	 * @param yVelocity
	 * @post The velocity of the entity is updated to the parameters xVelocity and yVelocity.
	 * 			| new.getVelocity() = [xVelocity, yVelocity]
	 */
	public void setVelocity(double xVelocity, double yVelocity){
		if (this.isValidVelocity(xVelocity, yVelocity)){
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;	
		}
	}
	
	/**
	 * @return Returns true if the calculated velocity of xVelocity and yVelociity does not exceed the maximum velocity for the ship.
	 * @return Returns false if the calculated velocity of xVelocity and yVelocity does exceed the maximum velocity for the ship.
	 * 			| result = sqrt(xVelocity**2 + yVelocity **2) < maxVelocity
	 */
	public boolean isValidVelocity(double xVelocity, double yVelocity){
		if (Double.isNaN(xVelocity) || Double.isNaN(yVelocity) || Math.sqrt(Math.pow(xVelocity,2) + Math.pow(yVelocity, 2)) > maxVelocity*1.00001 ){
			return false;
		}
		else {
			return true;
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
	
	/**
	 * 
	 * @return Returns the maximum velocity of the current Entity.
	 */
	public double getMaxVelocity(){
		return this.maxVelocity;
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
	
	/**
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @return returns true if both xVelocity and yVelocity are real numbers.
	 * @return returns false if xVelocity or yVelocity is not a real number.
	 * 			| return (xPosition != NaN && yPosition != yPosition)
	 * 
	 */
	public boolean isValidPosition(double xPosition, double yPosition){
		return (!Double.isNaN(xPosition) && !Double.isNaN(yPosition));
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
		if (!Double.isNaN(radius)){
			if (this instanceof Ship && ((Ship)this).getMinimumShipRadius() >= radius){
					return false;
			}
			else if (this instanceof MinorPlanet && ((MinorPlanet)this).getMinimumRadius() >= radius){
					return false;
			}
			
			else{
				return true;
			}
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
	 * 
	 * @param time
	 * @throws IllegalArgumentException throws a new exception of the type IllegalArgumentException if the give time is negative.
	 * @post Updates the position of the current entity according to the given time and its velocity.
	 * 			new.getVelocity()[0] = this.getVelocity()[0] * time
	 * 			new.getVelocity()[1] = this.getVelocity()[1] * time
	 */
	public void move(double time) throws IllegalArgumentException{
		if (time < 0){
			throw new IllegalArgumentException();
			}
		else {
			this.setPosition(this.getPosition()[0] + this.getVelocity()[0]*time, this.getPosition()[1] + this.getVelocity()[1]*time);
	
		}
	}
	
	/**
	 * 
	 * @return Returns the time in seconds until the current entity collides for the first time with a boundary.
	 */
	public double getTimeCollisionBoundary(){
		double boundaryTime = Double.POSITIVE_INFINITY;
		if(this.getWorld() == null){
			return boundaryTime;
		}
		
		double xMaxBound = ((this.getWorld().getDimension()[0]-this.getRadius())-this.getPosition()[0])/(this.getVelocity()[0]);
		double yMaxBound = ((this.getWorld().getDimension()[1]-this.getRadius())-this.getPosition()[1])/(this.getVelocity()[1]);
		double xMinBound = (this.getRadius()-this.getPosition()[0])/(this.getVelocity()[0]);
		double yMinBound = (this.getRadius()-this.getPosition()[1])/(this.getVelocity()[1]);
		
		
		if(this.getVelocity()[0] > 0) {
			boundaryTime = xMaxBound;
		}
		if(this.getVelocity()[1] > 0){
			if(0 <= yMaxBound && yMaxBound < boundaryTime){
				boundaryTime = yMaxBound;
			}
		}
		if(this.getVelocity()[0] < 0){
			if(0 <= xMinBound && xMinBound < boundaryTime){
				boundaryTime = xMinBound;
			}
		}
		if(this.getVelocity()[1] < 0){
			if(0 <= yMinBound && yMinBound < boundaryTime){
				boundaryTime = yMinBound;
			}
		}
		return boundaryTime;
	}

	 /**
	  * 
	  * @param otherEntity
	  * @return returns true if the current entity overlaps with the other entity or the current entity equals the other entity.
	  * 			| return (this.equals(otherEntity) || this.getDistanceBetween(otherEntity) < 0
	  */
	public boolean overlap(Entity otherEntity){
		if (this == otherEntity){
			return true;
		}
		
		if (this.getDistanceBetween(otherEntity) < 0){
			return true;
		}
		
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param otherEntity
	 * @return Returns the distance between the 2 outer borders of the current entity and the other entity.
	 */
	public double getDistanceBetween(Entity otherEntity){
		if (this == otherEntity){
			return 0;
		}
		else {
		return  (Math.sqrt(Math.pow(this.getPosition()[0] - otherEntity.getPosition()[0],2)+Math.pow(this.getPosition()[1] - otherEntity.getPosition()[1],2))	- this.getRadius() - otherEntity.getRadius());
		}
	}
	
	/**
	 * 
	 * @return Returns the position of the next boundary collision of the current entity in
	 * 			an array of 2 doubles with the xPosition on index 0 and the yPosition on index 1.
	 */
	public double[] getPositionCollisionBoundary(){
		System.out.println("timecol=" + this.getTimeCollisionBoundary());
		if (this.getWorld() != null && this.getTimeCollisionBoundary() != Double.POSITIVE_INFINITY){
			double[] boundaryColPos;
			double xPosColBound = this.getVelocity()[0]*this.getTimeCollisionBoundary() + this.getPosition()[0];
			double yPosColBound = this.getVelocity()[1]*this.getTimeCollisionBoundary() + this.getPosition()[1];
			if (xPosColBound >= (this.getWorld().getDimension()[0]-this.getRadius())*0.99 && xPosColBound <= (this.getWorld().getDimension()[0]-this.getRadius())*1.01){
				xPosColBound = this.getWorld().getDimension()[0];
			}
			if (yPosColBound >= (this.getWorld().getDimension()[1]-this.getRadius())*0.99 && yPosColBound <= (this.getWorld().getDimension()[1]-this.getRadius())*1.01){
				yPosColBound = this.getWorld().getDimension()[1];
			}
			if (xPosColBound >= this.getRadius()*0.99 && xPosColBound <= this.getRadius()*1.01){
				xPosColBound = 0;
			}
			if (yPosColBound >= this.getRadius()*0.99 && yPosColBound <= this.getRadius()*1.01){
				yPosColBound = 0;
			}
			
			boundaryColPos = new double[] {xPosColBound,yPosColBound};
			return boundaryColPos;
		}
		else{
			return null;
		}
		}

	/**
	 * 
	 * @param otherEntity
	 * @return Returns the time in seconds until the current entity collides with the other entity.
	 */
	public double getTimeCollisionEntity(Entity otherEntity){
		double deltaRX = otherEntity.getPosition()[0] - this.getPosition()[0];
		double deltaRY = otherEntity.getPosition()[1] - this.getPosition()[1];
		double deltaVX = otherEntity.getVelocity()[0] - this.getVelocity()[0];
		double deltaVY = otherEntity.getVelocity()[1] - this.getVelocity()[1];
		double deltaRTotal = Math.pow(deltaRX, 2) + Math.pow(deltaRY, 2);
		double deltaVTotal = Math.pow(deltaVX, 2) + Math.pow(deltaVY, 2);
		double deltaVDeltaR = deltaVX * deltaRX + deltaVY * deltaRY;
		double sigma = this.getRadius() + otherEntity.getRadius();
		double d = Math.pow(deltaVDeltaR, 2) - deltaVTotal*(deltaRTotal-Math.pow(sigma, 2));
		
		if (deltaVDeltaR >= 0 || d<=0 || !this.getWorld().equals(otherEntity.getWorld())){
			return Double.POSITIVE_INFINITY;
		}
		else{
			return -((deltaVDeltaR + Math.sqrt(d))/(deltaVTotal));
		}
	}
	
	@Deprecated
	public double[] getPositionCollisionEntity1(Entity otherEntity) throws IllegalArgumentException{
	try{
	if (this.getTimeCollisionEntity(otherEntity) == Double.POSITIVE_INFINITY){
		return null;
	}
	
	else {
		double[] PosCollapse;
		double xPosCollapse = (((this.getPosition()[0]+this.getTimeCollisionEntity(otherEntity)*this.getVelocity()[0])*otherEntity.getRadius())+
				((otherEntity.getPosition()[0]+this.getTimeCollisionEntity(otherEntity)*this.getVelocity()[0])*this.getRadius())) /
				(this.getRadius()+otherEntity.getRadius());
		double yPosCollapse = (((this.getPosition()[1]+this.getTimeCollisionEntity(otherEntity)*this.getVelocity()[1])*otherEntity.getRadius())+
				((otherEntity.getPosition()[1]+this.getTimeCollisionEntity(otherEntity)*otherEntity.getVelocity()[1])*this.getRadius())) /
				(this.getRadius()+otherEntity.getRadius());
		PosCollapse = new double[] {xPosCollapse, yPosCollapse};
		return PosCollapse;
	}
	}catch (IllegalArgumentException e){
		throw new IllegalArgumentException();
	}
	
}
	
	public boolean isOutOfBounds(){
		if (this.getPosition()[0] - this.getRadius() < 0 || this.getPosition()[1] - this.getRadius() < 0){
			return true;
		}
		if (this.getPosition()[0] + this.getRadius() > this.getWorld().getDimension()[0] || this.getPosition()[1] + this.getRadius() > this.getWorld().getDimension()[1]){
			return true;
		}
		else{
			return false;
		}
	}

	public double[] getPositionCollisionEntity(Entity otherEntity){
		if (this.getTimeCollisionEntity(otherEntity) != Double.POSITIVE_INFINITY){
			double timeToCollision = this.getTimeCollisionEntity(otherEntity);
			double xPosThisCollision = this.getVelocity()[0] * timeToCollision + this.getPosition()[0];
			double yPosThisCollision = this.getVelocity()[1] * timeToCollision + this.getPosition()[1];
			double xPosOtherEntityCollision = otherEntity.getVelocity()[0] * timeToCollision + otherEntity.getPosition()[0];
			double yPosOtherEntityCollision = otherEntity.getVelocity()[1] * timeToCollision + otherEntity.getPosition()[1];
			
			double alpha = Math.atan((yPosOtherEntityCollision - yPosThisCollision) / (xPosOtherEntityCollision - xPosThisCollision));
			double xCoordinate = xPosThisCollision  + this.getRadius() * Math.cos(alpha);
			double yCoordinate = yPosThisCollision  + this.getRadius() * Math.sin(alpha);
			
			double[] collisionPosition = {xCoordinate, yCoordinate};
			
			return collisionPosition;
		}
		else{
			return null;
		}
	}
	
	public void boundaryCollision(){
		System.out.println("this.getposition[0] = " + this.getPosition()[0]);
		System.out.println("this.getposition[1] = " + this.getPosition()[1]);
		System.out.println("this.getradius = " + this.getRadius());
		System.out.println("this.getworlddimensionX = " + this.getWorld().getDimension()[0]);
		System.out.println("this.getworlddimensionY = " + this.getWorld().getDimension()[1]);
		System.out.println("this.getradius*0.99=  " + this.getRadius()*0.99);
		System.out.println("test = " + this.getPositionCollisionBoundary()[0]);
		System.out.println("test = " + this.getPositionCollisionBoundary()[1]);
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
	
	public void setWorld(World world){
		this.world = world;
	}
	public World getWorld(){
		return this.world;
	}
	public boolean isValidWorld(World world){
		return world != null;
	}
	
	public void terminate(){
		//This method is overrided by all subclasses of Entity.
	}

	private World world;
}


