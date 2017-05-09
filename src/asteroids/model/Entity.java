package asteroids.model;

public class Entity {
	
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
			if (this instanceof Ship && this.getMinimumShipRadius() > radius){
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

	public double getMinimumShipRadius(){
		return this.minimumShipRadius;
	}
	private double minimumShipRadius = 10;

	/**
	 * 
	 * @return Returns the current radius of the ship.
	 * 			| result = this.radius
	 */
	public double getRadius(){
		return this.radius;
	}
	private double radius;
	
	public void move(double time) throws IllegalArgumentException{
		if (time < 0){
			throw new IllegalArgumentException();
			}
		else {
			this.setPosition(this.getPosition()[0] + this.getVelocity()[0]*time, this.getPosition()[1] + this.getVelocity()[1]*time);
	
		}
	}
	
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
			if(0 < yMaxBound && yMaxBound < boundaryTime){
				boundaryTime = yMaxBound;
			}
		}
		if(this.getVelocity()[0] < 0){
			if(0 < xMinBound && xMinBound < boundaryTime){
				boundaryTime = xMinBound;
			}
		}
		if(this.getVelocity()[1] < 0){
			if(0 < yMinBound && yMinBound < boundaryTime){
				boundaryTime = yMinBound;
			}
		}
		return boundaryTime;
	}

	
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
	
	public double getDistanceBetween(Entity otherEntity){
		if (this == otherEntity){
			return 0;
		}
		else {
		return  (Math.sqrt(Math.pow(this.getPosition()[0] - otherEntity.getPosition()[0],2)+Math.pow(this.getPosition()[1] - otherEntity.getPosition()[1],2))	- this.getRadius() - otherEntity.getRadius());
		}
	}
		
	public double[] getPositionCollisionBoundary(){
		if (this.getWorld() != null && this.getTimeCollisionBoundary() != Double.POSITIVE_INFINITY){
			double[] boundaryColPos;
			double xPosColBound = this.getVelocity()[0]*this.getTimeCollisionBoundary() + this.getPosition()[0];
			double yPosColBound = this.getVelocity()[1]*this.getTimeCollisionBoundary() + this.getPosition()[1];
			boundaryColPos = new double[] {xPosColBound,yPosColBound};

			return boundaryColPos;
		}
		else{
			return null;
		}
		}

	public double getTimeCollisionEntity(Entity otherEntity){
		double deltaRX = otherEntity.getPosition()[0] - this.getPosition()[0];
		double deltaRY = otherEntity.getPosition()[1] - this.getPosition()[1];
		double deltaVX = otherEntity.getVelocity()[0] - this.getVelocity()[0];
		double deltaVY = otherEntity.getVelocity()[1] - this.getVelocity()[1];
		double deltaRTotal = Math.sqrt(Math.pow(deltaRX, 2) + Math.pow(deltaRY, 2));
		double deltaVTotal = Math.sqrt(Math.pow(deltaVX, 2) + Math.pow(deltaVY, 2));
		double deltaVDeltaR = deltaVX * deltaRX + deltaVY * deltaRY;
		double sigma = this.getRadius() + otherEntity.getRadius();
		double d = Math.pow(deltaVDeltaR, 2) - Math.pow(deltaVTotal, 2)*(Math.pow(deltaRTotal, 2)-Math.pow(sigma, 2));
		if (deltaVDeltaR >= 0 || d<=0 || !this.getWorld().equals(otherEntity.getWorld())){
			return Double.POSITIVE_INFINITY;
		}
		else{
			return ((-deltaVDeltaR + Math.sqrt(d)) / Math.pow(deltaVTotal, 2));
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

	public double[] getPositionCollisionEntity(Entity otherEntity){
		if (this.getTimeCollisionEntity(otherEntity) != Double.POSITIVE_INFINITY){
			double timeToCollision = this.getTimeCollisionEntity(otherEntity);
			double xPosThisCollision = this.getVelocity()[0] * timeToCollision + this.getPosition()[0];
			double yPosThisCollision = this.getVelocity()[1] * timeToCollision + this.getPosition()[1];
			double xPosOtherEntityCollision = otherEntity.getVelocity()[0] * timeToCollision + otherEntity.getPosition()[0];
			double yPosOtherEntityCollision = otherEntity.getVelocity()[1] * timeToCollision + otherEntity.getPosition()[1];
			
			double alpha = Math.atan((yPosOtherEntityCollision - yPosThisCollision) / (xPosOtherEntityCollision - xPosThisCollision));
			
			double xCoordinate = this.getRadius() * Math.cos(alpha);
			double yCoordinate = this.getRadius() * Math.sin(alpha);
			
			double[] collisionPosition = {xCoordinate, yCoordinate};
			
			return collisionPosition;
		}
		else{
			return null;
		}
	}
	
	public void boundaryCollision(){
		if (this.getPosition()[0] >= this.getRadius() * 0.99 && this.getPosition()[0] <= this.getRadius() *1.01){
			this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);
		}
		if (this.getPosition()[1] >= this.getRadius() * 0.99 && this.getPosition()[1] <= this.getRadius() *1.01){
			this.setVelocity(this.getVelocity()[0],-this.getVelocity()[1]);
		}
		if (this.getPosition()[0] >= (this.getWorld().getDimension()[0] - this.getRadius())*0.99 && this.getPosition()[0] <= (this.getWorld().getDimension()[0] - this.getRadius())*1.01){
			this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);
		}
		if (this.getPosition()[1] >= (this.getWorld().getDimension()[1] - this.getRadius())*0.99 && this.getPosition()[1] <= (this.getWorld().getDimension()[1] - this.getRadius())*1.01){
			this.setVelocity(this.getVelocity()[0], -this.getVelocity()[1]);
		}
		else{
			//throw new IllegalStateException();
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


