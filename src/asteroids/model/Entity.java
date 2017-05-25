package asteroids.model;

/**
 * A class that represents a entity with a x-velocity, a y-velocity, a x-position, a y-position and a radius
 * Superclass of classes Ship, Bullet and MinorPlanet.
 * @author Ignace Bleukx and Mats Ruell
 * @Invar The velocity of an Entity must be valid.
 * 		 | isValidVelocity(getVelocity()[0],getVelocity()[1])
 * @Invar The position of an Entity must be valid.
 * 		 | isValidPosition(getPosition()[0],getPosition()[1])
 * @Invar The radius of an Entity must be valid.
 * 		 | isValidRadius(getRadius())
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
	 * This method return the maximum velocity of the entity.
	 * @return Returns the maximum velocity of the current Entity.
	 */
	public double getMaxVelocity(){
		return this.maxVelocity;
	}
	
	private double xVelocity;
	private double yVelocity;
	private final double maxVelocity = 300000;
	
	/**
	 * This method returns the current orientation of the entity, expressed in radians.
	 * @return Returns the current orientation of the entity.
	 * 			| result = this.orientation
	 */
	public double getOrientation() {
		return this.orientation;
	}

	/**
	 * Sets the orientation of the current entity to the given parameter 'orientation'.
	 * @param orientation
	 * @Pre  The value of the parameter orientation must be larger than or equal to 0 and smaller than or equal to 2 * pi.
	 * 			| this.isValidOrientation(this.getOrientation)
	 * @post Sets the current orientation of the ship to the parameter orientation.
	 * 			| new.getOrientation() = orientation 
	 */
	public void setOrientation(double orientation){
		if(isValidOrientation(orientation)){
			this.orientation = orientation;

		}
	}
	
	public boolean isValidOrientation(double orientation) {
		if(0 <= orientation && orientation <= 2*Math.PI){
			return true;
		}
		else{
			return false;
		}
	}
	
	private double orientation;

	
	/**
	 *This method returns the position of the current ship in an array. 
	 * @return Returns the current position of the ship in an array of length 2 with the xPosition on index 0 and yPosition on index 1.
	 * 			result = [xPosition, yPosition]
	 */
	public double[] getPosition(){		
		return new double[] {this.xPosition, this.yPosition};		
	}
	
	/**
	 * 
	 * @Param xPosition The position along the x-axis that must be checked if it is valid.
	 * @Raram yPosition The position along the y-axis that must be checked if it is valid.
	 * @Return returns true if both xVelocity and yVelocity are real numbers.
	 * @Return returns false if xVelocity or yVelocity is not a real number.
	 * 			| return (xPosition != NaN && yPosition != yPosition) 
	 */
	public boolean isValidPosition(double xPosition, double yPosition){
		return (!Double.isNaN(xPosition) && !Double.isNaN(yPosition));
	}
	
	/**
	 * @param xPosition The position along the x-axis to witch the current Entity must be set.
	 * @param yPosition The position along the y-axis to witch the current Entity must be set.
	 * @throws IllegalArgumentException if the given xPosition or yPosition is not valid.
	 * 			| if (xPosition == NaN || yPosition == NaN)
	 * 					throw IllegalArgumentException	  					
	 * @post Sets the current position of the ship to the parameters xPosition and yPosition.
	 * 			| new.getXPosition()[0] = xPosition
	 * 			| new.getYPosition()[1] = yPosition
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
	 * @param radius: The radius to witch the current Entity must be set.
	 * @throws IllegalArgumentException if the radius is not valid.
	 *			| if (! isValidRadius(radius)
	 *				throw IllegalArgumentException
	 * @post Sets the radius of the ship on the parameter radius.
	 * 			| new.getRadius() = radius
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
	 * @param radius: The radius to check.
	 * @return True if the radius is larger than or or equal to the minimum radius. 
	 * @return False if the radius smaller or the given radius is not a number or the current entity is a ship and the radius is below
	 * 			the minimumshipradius or the entity is a minorPlanet and the radius is smaller than the minimumradius of the minorplanet.
	 * 			| see code
	 */
	public boolean isValidRadius(double radius){
		if (!Double.isNaN(radius)){
			if (this instanceof Ship && ((Ship)this).getMinimumRadius() > radius){
					return false;
			}
			else if (this instanceof MinorPlanet && ((MinorPlanet)this).getMinimumRadius() > radius){
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
	 * This method returns the current radius of the entity.
	 * @return Returns the current radius of the entity.
	 * 			| result = this.radius
	 */
	public double getRadius(){
		return this.radius;
	}
	private double radius;
	
	/**
	 * @param time: The time over which the current entity must be advanced.
	 * @throws IllegalArgumentException throws a new exception of the type IllegalArgumentException if the give time is negative.
	 * @post Updates the position of the current entity according to the given time and its velocity.
	 * 			new.getPosition()[0] = this.getVelocity()[0] * time
	 * 			new.getPosition()[1] = this.getVelocity()[1] * time
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
	 * This method returns the time in seconds until the border of the current entity collapses with the boundary of the world it is positionned in.
	 * @return Returns the time in seconds until the current entity collides for the first time with a boundary of its world.
	 * 			| this.move(result)
	 * 			| new.getPosition()[0] = this.getWorld.getDimension()[0] - this.getRadius ||   new.getPosition()[0] = this.getRadius
	 * 			   || new.getPosition()[1] = this.getWorld.getDimension()[1] - this.getRadius ||   new.getPosition()[1] = this.getRadius
	 * 
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
	  * @param otherEntity: The other entity that might overlap with the current entity.
	  * @return Returns true if the current entity overlaps with the other entity or the current entity equals the other entity.
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
	 * @param otherEntity: The other entity to witch the distance must be measured.
	 * @return Returns the distance between the 2 outer borders of the current entity and the other entity.
	 * 			| result = sqrt((this.getPosition()[0] - otherEntity.getPosition()[0]) ** 2 + 
	 * 								(this.getPosition()[1] - otherEntity.getPosition()[1]) ** 2) - this.getRadius() - otherEntity.getRadius()
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
	 * This method returns the position where the entity hits the border of its wirld.
	 * @return Returns the position of the next boundary collision of the current entity in
	 * 			an array of 2 doubles with the xPosition on index 0 and the yPosition on index 1.
	 * 			| this.move(this.getTimeCollisionBoundary())
	 * 			| result = new.getPosition()[0] +- this.getRadius() || result = new.getPosition()[1] +- this.getRadius()
	 */
	public double[] getPositionCollisionBoundary(){
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
	 * @param otherEntity: The other entity to witch the collisiontime must be calculated
	 * @return Returns the time in seconds until the current entity collides with the other entity.
	 * 			| this.move(result)
	 * 			| otherEntity.move(result)
	 * 			| new.getDistanceBetween(otherEntity) = 0
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
	
	/**
	 * This function determines if the entity is out of bounds of the world. 
	 * @param world: The world in witch the entity must be checked if it is out of bounds.
	 * @return see code
	 */
	public boolean isOutOfBounds(World world){
		if (this.getPosition()[0] - this.getRadius() < 0 || this.getPosition()[1] - this.getRadius() < 0){
			return true;
		}
		if (this.getPosition()[0] + this.getRadius() > world.getDimension()[0] || this.getPosition()[1] + this.getRadius() > world.getDimension()[1]){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * 
	 * @param otherEntity
	 * @return
	 */
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
	
	/**
	 * This method resolves a boundary collision of an entity.
	 * @Post If the entity collides with a horizontal boundary, its y-velocity is negated, otherwise its x-velocity is negated.
	 * 			| new.getVelocity()[0] = -this.getVelocity()[0] || new.getVelocity()[1] = -this.getVelocity()[1]
	 */
	public void boundaryCollision(){
		double xPosColBound = this.getPositionCollisionBoundary()[0];
		double yPosColBound = this.getPositionCollisionBoundary()[1];
		if (xPosColBound == 0 || xPosColBound == this.getWorld().getDimension()[0]){
			this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);
		}
		if (yPosColBound == 0 || yPosColBound == this.getWorld().getDimension()[1]){
			this.setVelocity(this.getVelocity()[0], -this.getVelocity()[1]);
		}
		

	}
	
	/**
	 * This method sets the world of the current entity to the given parameter world.
	 * @param world: The world the entity must be set in.
	 * @Post The world of the current entity is set to the parameter world.
	 * 			| new.getWorld() = world
	 */
	public void setWorld(World world){
		this.world = world;
	}
	
	/**
	 * This method returs the world the current entity is set in.
	 * @return Returns the world of the current entity.
	 * 				| result = this.world
	 */
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * This method deterimines if the world given is a valid one.
	 * @param world: The world that must be checked.
	 * @return Returns false if and only if the world is null.
	 * 			| result = world != null
	 */
	public boolean isValidWorld(World world){
		return world != null;
	}
	
	/**
	 * This method is overwritten by all subclasses of Entity.
	 */
	public void terminate(){
		throw new IllegalStateException("This method should be overwritten by all subclasses of Entity");
	}

	private World world;
}


