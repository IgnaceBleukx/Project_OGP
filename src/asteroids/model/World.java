package asteroids.model;

import java.util.HashSet;
import java.util.Set;

import apple.laf.JRSUIState.TitleBarHeightState;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException; 

public class World extends Object {
	
	/**
	 * @param width
	 * @param height
	 * This method creates a new object of type World with given parameters and sets the Dimension of the world.
	 */
	
	public World (double width, double height){
		this.setDimension(width, height);
	}
	
	/**
	 * @param width
	 * @param height
	 * This method sets the Dimension of the world.
	 * 		| new.width = width
	 * 		| new.height = height
	 * 	
	 */
	
	public void setDimension(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	/**
	 * 
	 * @return Returns the dimension of the world in an array of length 2 with this.width on index 0 and this.height on index 1.
	 * 				| return {this.width, this.height}
	 */
	
	public double[] getDimension(){
		return new double[]{this.width, this.height};
	}
	
	/**
	 * @param width
	 * @param height
	 * @return Returns true if the width and height of world lie in the range 0 to Double.MAX_VALUE.
	 */

	public boolean isValidDimension(double width, double height){
		return true;
	}
	
	/**
	 * @return Returns the set of all the ships in this world.
	 */
	
	public Set<Ship> getAllShips() {
		return allShips;
	}
	
	/**@param allShips
	 *  This method sets all ships in this world from allShips.
	 *  		| new.allShips = allShips
	 */
	
	public void setAllShips(Set<Ship> allShips){
		this.allShips = allShips;
		
	}
	
	/**@param ship
	 * @throws IllegalArgumentException
	 * 			If given parameter is invalid, a new exception of the type IllegalArgumentException will be thrown.
	 * This method adds the Ship 'ship' to the set of all ships in the world 'allShips'.
	 */
	
	public void addShipToWorld(Ship ship) throws IllegalArgumentException{
		try{
		this.allShips.add(ship);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}
	
	/**@param ship
	 * @throws IllegalArgumentException
	 * 			If given parameter is invalid, a new exception of the type IllegalArgumentException will be thrown.
	 * This method removes the Ship 'ship' from the set of all ships in the world 'allShips'.
	 */

	
	public void removeShipFromWorld(Ship ship) throws IllegalArgumentException{
		try{
		this.allShips.remove(ship);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * @return Returns the set of all the bullets in this world.
	 */
		
	public Set<Bullet> getAllBullets() {
		return allBulletsWorld;
	}
	
	/**
	 * @param allBullets
	 * @post This method sets all the bullets in this world from 'allBullets'
	 * 			| new.allBulletsWorld = allBullets
	 */

	public void setAllBullets(Set<Bullet> allBullets){
		this.allBulletsWorld = allBullets;
		
	}
	
	/**@param bullet
	 * @throws IllegalArgumentException
	 * 			If given parameter is invalid, a new exception of the type IllegalArgumentException will be thrown.
	 * This method adds the Bullet 'bullet' to the set of all bullets in the world 'allBulletsWorld'.
	 */
	
	public void addBulletToWorld(Bullet bullet) throws IllegalArgumentException{
		try{
		this.allBulletsWorld.add(bullet);
		bullet.setWorld(this);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
		
	}
	
	/**@param bullet
	 * @throws IllegalArgumentException
	 * 			If given parameter is invalid, a new exception of the type IllegalArgumentException will be thrown.
	 * This method removes the Bullet 'bullet' from the set of all bullets in the world 'allBulletsWorld'.
	 */
	
	public void removeBulletToWorld(Bullet bullet) throws IllegalArgumentException{
		try{
		this.allBulletsWorld.remove(bullet);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}
	
	
	public Object getEntityAt(double x, double y){
		for (Object object: this.getEntities()){
			if (object.getPosition()[0] == x && object.getPosition()[1] == y){
				return object;
				}
			}
		return null;
		}
	
	public Set<? extends Object> getEntities(){

		Set<Object> allEntities = new HashSet<Object>();
		for (Bullet bullet : this.getAllBullets()){
			allEntities.add(bullet);
		}
		for (Ship ship : this.getAllShips()){
			allEntities.add(ship);
		}
		return allEntities;
	}
	
	
	public double getTimeNextCollision() {
		double timeNextCollision = Double.POSITIVE_INFINITY;
		Set<? extends Object> allEntities = this.getEntities();	
		for (Object object1 : allEntities){
			if(getTimeCollisionBoundary(object1) < timeNextCollision){
				timeNextCollision = getTimeCollisionBoundary(object1);
			}
			for (Object object2 : allEntities){
				if(object1 == object2){
					continue;
				}
				else {
					if(getTimeCollisionEntity(object1,object2) < timeNextCollision){
						timeNextCollision = getTimeCollisionEntity(object1,object2);
						this.setCollisionEntity1(object1);
						this.setCollisionEntity2(object2);
					}	
				}		
			}
		}
		return timeNextCollision;
	}


	public double[] getPositionNextCollision() {	
		
		double[] posNextCollision = null;
		double timeNextCollision = Double.POSITIVE_INFINITY;
		Set<? extends Object> allEntities = this.getEntities();
		timeNextCollision = this.getTimeNextCollision();
		
		for (Object object1 : allEntities){
			if(getTimeCollisionBoundary(object1) == timeNextCollision){
				posNextCollision = getPositionCollisionBoundary(object1);
			}
			for (Object object2 : allEntities){
				if(object1 == object2){
					continue;
				}
				else {
					if(getTimeCollisionEntity(object1,object2) == timeNextCollision){
						posNextCollision = getPositionCollisionEntity(object1,object2);
					}
				}
			}
		}
		return posNextCollision;
	}
	
	public void evolve(double dt, CollisionListener collisionListener){
	double timeNextCollision = this.getTimeNextCollision();
	if(timeNextCollision > dt){
		for (Ship ship : this.getAllShips()){
			ship.setPosition(ship.getPosition()[0] + ship.getVelocity()[0]*dt, ship.getPosition()[1] + ship.getVelocity()[1]*dt);
			if(ship.inspectThruster() == true){
				ship.newThruster(ship.getShipAcceleration(),dt);			
			}
		}
		for (Bullet bullet : this.getAllBullets()){
			bullet.setPosition(bullet.getPosition()[0] + bullet.getVelocity()[0]*dt, bullet.getPosition()[1] + bullet.getVelocity()[1]);
			
			}
		}
	else {
		if(this.getCollisionEntity1() instanceof Ship && this.getCollisionEntity2() instanceof Ship){
			Object entity1 = this.getCollisionEntity1();
			Object entity2 = this.getCollisionEntity2();
			double xVelocity1 = entity1.getVelocity()[0]+(2*entity2.getMass()*((entity2.getVelocity()[0]-entity1.getVelocity()[0])*
					(entity2.getPosition()[0]-entity1.getPosition()[0])+(entity2.getVelocity()[1]-entity1.getVelocity()[1])*
					(entity2.getPosition()[1]-entity1.getPosition()[1])))*(entity2.getPosition()[0]-entity1.getPosition()[0])/(Math.pow((entity1.getRadius()+entity2.getRadius()), 2)
							*(entity1.getMass()+entity2.getMass()));
			double yVelocity1 = entity1.getVelocity()[1]+(2*entity2.getMass()*((entity2.getVelocity()[0]-entity1.getVelocity()[0])*
					(entity2.getPosition()[0]-entity1.getPosition()[0])+(entity2.getVelocity()[1]-entity1.getVelocity()[1])*
					(entity2.getPosition()[1]-entity1.getPosition()[1])))*(entity2.getPosition()[1]-entity1.getPosition()[1])/(Math.pow((entity1.getRadius()+entity2.getRadius()), 2)
							*(entity1.getMass()+entity2.getMass()));
			entity1.setVelocity(xVelocity1, yVelocity1);
			
			double xVelocity2 = entity1.getVelocity()[0]-(2*entity1.getMass()*((entity2.getVelocity()[0]-entity1.getVelocity()[0])*
					(entity2.getPosition()[0]-entity1.getPosition()[0])+(entity2.getVelocity()[1]-entity1.getVelocity()[1])*
					(entity2.getPosition()[1]-entity1.getPosition()[1])))*(entity2.getPosition()[0]-entity1.getPosition()[0])/(Math.pow((entity1.getRadius()+entity2.getRadius()), 2)
							*(entity1.getMass()+entity2.getMass()));
			double yVelocity2 = entity1.getVelocity()[1]-(2*entity1.getMass()*((entity2.getVelocity()[0]-entity1.getVelocity()[0])*
					(entity2.getPosition()[0]-entity1.getPosition()[0])+(entity2.getVelocity()[1]-entity1.getVelocity()[1])*
					(entity2.getPosition()[1]-entity1.getPosition()[1])))*(entity2.getPosition()[1]-entity1.getPosition()[1])/(Math.pow((entity1.getRadius()+entity2.getRadius()), 2)
							*(entity1.getMass()+entity2.getMass()));
			entity2.setVelocity(xVelocity2, yVelocity2);
			this.evolve(dt -= timeNextCollision, collisionListener);
			
			}
		if((this.getCollisionEntity1() instanceof Ship && this.getCollisionEntity2() instanceof Bullet)){
			if(this.getCollisionEntity2().firedFrom() == this.getCollisionEntity1()){
				this.getCollisionEntity1().loadBulletOnShip(this.getCollisionEntity2());
				}
			}
		if((this.getCollisionEntity1() instanceof Bullet && this.getCollisionEntity2() instanceof Ship)){
			if(this.getCollisionEntity1().firedFrom() == this.getCollisionEntity2()){
				this.getCollisionEntity2().loadBulletOnShip(this.getCollisionEntity1());
				}
			}
		
		}
		
	}
	
	


	private double width;
	private double height;
	private Set<Ship> allShips = new HashSet<Ship>();
	private Set<Bullet> allBulletsWorld = new HashSet<Bullet>();
	
}
