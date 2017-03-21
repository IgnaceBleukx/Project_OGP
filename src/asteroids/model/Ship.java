package asteroids.model;
public class Ship{
	/**
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
			if (orientation <= Math.PI && orientation >= 0){
				this.setOrientation(orientation);
			}
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
	 */
	public Ship(){
		this.setVelocity(0, 0);
		this.setPosition(0,0);
		this.setOrientation(0);
		this.setRadius(10);
	}
	/**
	 * 
	 * @param xVelocity the x
	 * @param yVelocity
	 * @post The new velocity is set on the given velocities if they are valid. Otherwise, there will be no change.
	 * 			| if sqrt(xVelocity**2 + yVelocity **2) < maxVelocity:
	 * 					new.xVelocity = xVelocity
	 * 					new.yVelocity = yVelocity
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
	/**
	 * 
	 * @return Returns the total velocity calculated from the current xVelocity and yVelocity of the ship.
	 * 				| result = sqrt(xVelocity**2 + yVelocity **2)
	 */
	public double calcVelocity(){
		return Math.sqrt(Math.pow(this.getVelocity()[0], 2)+Math.pow(this.getVelocity()[1], 2));
		
	}
	
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
	 * @pre  The value of the parameter orientation must be larger than or equal to 0 and smaller than or equal to pi/.
	 * 			| 0 <= orientation <= pi/2
	 * @post Sets the current orientation of the ship to the parameter orientation.
	 * 			| new.orientation = orientation 
	 */
	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
	
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
		if (mass >= Math.pow(this.getRadius(),3) * 4/3 * Math.PI){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Enables the thruster of the current ship if not enabled yet.
	 * 			| if (thrusterstate == false)
	 * 			|		thrusterstate = true
	 * 			|		thrust(thrusterforce / mass)
	 * 
	 */
	public void thrustOn(){
		if(! this.thrusterState == true){
		this.thrusterState = true;
		this.thrust(this.thrusterForce / this.getMass());
		}
	}
	/**
	 * Disables the thruster of the ship if not disabled.
	 * 			| if (thrusterstate == false)
	 * 			|		thrusterstate = false
	 * 			|		thrust(0)
	 */
	public void thrustOff(){
		if (! this.thrusterState == false){	
		this.thrusterState = false;
		this.thrust(0);
		}
	}
	
	
	public boolean inspectThruster(){
		return this.thrusterState;
	}
	/**
	 * 
	 * @param time
	 * @throws IllegalArgumentException if the give time is smaller than 0.
	 * 			| if (time < 0)
	 * 				throw IllegalArgumentException
	 * @effect The position of the ship will be changed based on the given velocity and orientation in a given time.
	 * 			| new.xPosition = old.xPosition + old.xVelocity * time
	 * 			| new.yPosition = old.yPosition + old.yVelocity * time
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
	 * @param angle
	 * 			The angle that needs to be added to the current orientation.
	 * @pre  The sum of the current orientation and the given angle must be smaller or equal to pi/2 and must be larger than or equal to 0.
	 * 			| this.getOrientation() + angle <= pi/2 && this.getOrientation() + angle >= 0.
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
		if (isValidVelocity(this.xVelocity += a * Math.cos(orientation), this.yVelocity += a * Math.sin(orientation))){
			this.setVelocity(this.xVelocity + a * Math.cos(orientation),this.yVelocity + a * Math.sin(orientation));
		}	
		else{
			this.setVelocity(maxVelocity*Math.cos(orientation), 
								 maxVelocity*Math.sin(orientation));
							
		}
	}
	/**
	 * 
	 * @param otherShip
	 * 			| The other ship whereto the distance from the current ship must be calculated
	 * @return Returns the distance between ship1 and ship2, calculated from the edge of the ships.
	 * 			| result = sqrt((this.xPosition - otherShip.xPosition)**2 + (this.yPosition - otherShip.yPosition)**2)
	 * @return Returns 0 if the otherShip is the same as the current Ship.
	 * 			| if (this == otherShip)
	 * 				return 0
	 */
	public double getDistanceBetween(Ship otherShip){
		if (this == otherShip){
			return 0;
		}
		else {
		return  (Math.sqrt(Math.pow(this.getPosition()[0] - otherShip.getPosition()[0],2)+Math.pow(this.getPosition()[1] - otherShip.getPosition()[1],2))	- this.getRadius() - otherShip.getRadius());
		}
	}
	/**
	 * 
	 * @param otherShip
	 * 			| The other ship that might overlap with the current ship.
	 * @return Returns true if ship1 and ship2 overlap
	 * 			| if (this.getDistanceBetween(otherShip) < 0)
	 * 				result == true
	 * @return Returns true if ship1 and ship2 are the same ship
	 * 			|if (this == otherShip)
	 * 				result == true
	 * @return Returns false if ship1 and ship2 do not overlap.
	 * 			| if (getDistanceBetweend(otherShip) >= 0)
	 * 				result == false
	 */	
	public boolean overlap(Ship otherShip){
		if (this == otherShip){
			return true;
		}
		
		if (this.getDistanceBetween(otherShip) < 0){
			return true;
		}
		
		else {
			return false;
		}
	}
	/**
	 * 
	 * @param otherShip
	 * 			The other ship that might collapse with the current ship.
	 * @return Returns the time in seconds until the 2 ships collapse. Calculated from the current time.
	 * 			
	 */
	public double getTimeToCollapse(Ship otherShip){
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
	}
	
	/**
	 * 
	 * @param otherShip
	 * 			The other Ship that might collapse with the current ship.
	 * @return Double.POSITIVE_INFINITY if the ships never collapse.
	 * @return An array of length 2 with the xCoordinate of the collapseposition on index 0 and the yCoordinate of the collapseposition on index 1.
	 * 			| result = [xPositionOfCollapse, yPositionOfCollapse]
	 */
	public double[] getPositionOfCollapse(Ship otherShip){
		if (this.getTimeToCollapse(otherShip) == Double.POSITIVE_INFINITY){
			return null;
		}
		
		else {
			double[] PosCollapse;
			double xPosCollapse = (((this.getPosition()[0]+this.getTimeToCollapse(otherShip)*this.getVelocity()[0])*otherShip.getRadius())+
					((otherShip.getPosition()[0]+this.getTimeToCollapse(otherShip)*this.getVelocity()[0])*this.getRadius())) /
					(this.getRadius()+otherShip.getRadius());
			double yPosCollapse = (((this.getPosition()[1]+this.getTimeToCollapse(otherShip)*this.getVelocity()[1])*otherShip.getRadius())+
					((otherShip.getPosition()[1]+this.getTimeToCollapse(otherShip)*otherShip.getVelocity()[1])*this.getRadius())) /
					(this.getRadius()+otherShip.getRadius());
			PosCollapse = new double[] {xPosCollapse, yPosCollapse};
			return PosCollapse;	
		}		
	}

	private double xVelocity;
	private double yVelocity;
	private double xPosition;
	private double yPosition;
	private final double maxVelocity = 300000;
	private double orientation;
	private double radius;
	private double mass;
	private boolean thrusterState;
	private double thrusterForce = 1.1 * Math.pow(10, 21);

}