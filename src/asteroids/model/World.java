package asteroids.model;

import java.util.HashSet;
import java.util.Set;

import asteroids.part2.CollisionListener;

@SuppressWarnings({ "unused", "unused", "unused" })
public class World extends Entity {
	
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
		ship.setWorld(this);
		}
		catch(NullPointerException exc){
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
		ship.setWorld(null);
		}
		catch(NullPointerException exc){
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
	
	
	public Entity getEntityAt(double x, double y){
		for (Entity object: this.getAllEntities()){
			if (object.getPosition()[0] == x && object.getPosition()[1] == y){
				return object;
				}
			}
		return null;
		}
	
	public Set<? extends Entity> getAllEntities(){

		Set<Entity> allEntities = new HashSet<Entity>();
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
		Set<? extends Entity> allEntities = this.getAllEntities();	
		for (Entity object1 : allEntities){
			if(object1.getTimeCollisionBoundary() < timeNextCollision && object1.getTimeCollisionBoundary() > 0){
				timeNextCollision = object1.getTimeCollisionBoundary();
				this.setCollisionEntity1(object1);
				this.setCollisionEntity2(null);
			}
			for (Entity object2 : allEntities){

				if(object1 == object2){
					continue;
				}
				else {
					if(object1.getTimeCollisionEntity(object2) < timeNextCollision && object1.getTimeCollisionBoundary() > 0){
						timeNextCollision = object1.getTimeCollisionEntity(object2);
						this.setCollisionEntity1(object1);
						this.setCollisionEntity2(object2);
					}	
				}		
			}
		}
		return timeNextCollision;
	}
	
	public Entity getCollisionEntity1() {
		return CollisionEntity1;
	}

	public void setCollisionEntity1(Entity collisionEntity1) {
		CollisionEntity1 = collisionEntity1;
	}



	public Entity getCollisionEntity2() {
		return CollisionEntity2;
	}

	public void setCollisionEntity2(Entity collisionEntity2) {
		CollisionEntity2 = collisionEntity2;
	}

	private Entity CollisionEntity1 = null;
	private Entity CollisionEntity2 = null;

		
	


	public double[] getPositionNextCollision() {	
		double[] posNextCollision = null;
		double timeNextCollision = Double.POSITIVE_INFINITY;
		Set<? extends Entity> allEntities = this.getAllEntities();
		timeNextCollision = this.getTimeNextCollision();
		
		for (Entity object1 : allEntities){
			if(object1.getTimeCollisionBoundary() == timeNextCollision){
				posNextCollision = object1.getPositionCollisionBoundary();
			}
			for (Entity object2 : allEntities){
				if(object1 == object2){
					continue;
				}
				else {
					if(object1.getTimeCollisionEntity(object2) == timeNextCollision){
						posNextCollision = object1.getPositionCollisionEntity(object2);

					}
				}
			}
		}
		return posNextCollision;
	}
	

	
	public void evolve(double dt,CollisionListener collisionListener){
		double nextCollisionTime = this.getTimeNextCollision();
		System.out.println(nextCollisionTime);
		if (nextCollisionTime > dt){
			for(Entity entity : this.getAllEntities()){
				entity.move(dt);
			}
		}
		else{
			for (Entity entity: this.getAllEntities()){
				entity.move(nextCollisionTime);
			}
			if (this.getCollisionEntity2() == null){
				System.out.println(this.getCollisionEntity2());
				System.out.println(this.getCollisionEntity1());
				this.collisionResolver(this.getCollisionEntity1());
			}
			else{
				this.collisionResolver(this.getCollisionEntity1(),this.getCollisionEntity2());
			}
			this.evolve(dt - nextCollisionTime, collisionListener);
			
			
		}
		
	}

	private void collisionResolver(Entity entity1, Entity entity2){
		if (entity1 instanceof Ship && entity2 instanceof Ship){
			this.shipCollision((Ship)entity1,(Ship)entity2);
		}
		if (entity1 instanceof Ship && entity2 instanceof Bullet){
			this.shipBulletCollision((Ship)entity1,(Bullet)entity2);
		}
		if (entity1 instanceof Bullet && entity2 instanceof Ship){
			this.shipBulletCollision((Ship) entity2,(Bullet)entity1);
		}
		if (entity1 instanceof Bullet && entity2 instanceof Bullet){
			this.bulletCollision((Bullet)entity1, (Bullet)entity2);
		}	
	}
	
	private void collisionResolver(Entity entity){
		if (entity instanceof Bullet){
			if (((Bullet) entity).getBoundaryCollisions() < ((Bullet) entity).getMaxBoundaryCollisions()){
				((Bullet) entity).setBoundaryCollisions(((Bullet) entity).getBoundaryCollisions() + 1);
			}
			else{
				((Bullet) entity).terminate();
				return;
			}
		}
		if (entity.getPositionCollisionBoundary()[0] == entity.getRadius() || (entity.getPositionCollisionBoundary()[0] == (this.getDimension()[0]-entity.getRadius()))){
			this.boundaryCollision(entity, "y");
		}
		if (entity.getPositionCollisionBoundary()[1] == entity.getRadius() || (entity.getPositionCollisionBoundary()[1] == (this.getDimension()[1]-entity.getRadius()))){
			this.boundaryCollision(entity ,"x");
		}
	}
	
	private void shipCollision1(Ship ship1, Ship ship2){

		double xVelocity1 = ship1.getVelocity()[0]+(2*ship2.getMass()*((ship2.getVelocity()[0]-ship1.getVelocity()[0])*
				(ship2.getPosition()[0]-ship1.getPosition()[0])+(ship2.getVelocity()[1]-ship1.getVelocity()[1])*
				(ship2.getPosition()[1]-ship1.getPosition()[1])))*(ship2.getPosition()[0]-ship1.getPosition()[0])/(Math.pow((ship1.getRadius()+ship2.getRadius()), 2)
						*(ship1.getMass()+ship2.getMass()));
		double yVelocity1 = ship1.getVelocity()[1]+(2*ship2.getMass()*((ship2.getVelocity()[0]-ship1.getVelocity()[0])*
				(ship2.getPosition()[0]-ship1.getPosition()[0])+(ship2.getVelocity()[1]-ship1.getVelocity()[1])*
				(ship2.getPosition()[1]-ship1.getPosition()[1])))*(ship2.getPosition()[1]-ship1.getPosition()[1])/(Math.pow((ship1.getRadius()+ship2.getRadius()), 2)
						*(ship1.getMass()+ship2.getMass()));
		
		System.out.println(xVelocity1);
		System.out.println(yVelocity1);
		ship1.setVelocity(xVelocity1, yVelocity1);

		
		double xVelocity2 = ship1.getVelocity()[0]-(2*ship1.getMass()*((ship2.getVelocity()[0]-ship1.getVelocity()[0])*
				(ship2.getPosition()[0]-ship1.getPosition()[0])+(ship2.getVelocity()[1]-ship1.getVelocity()[1])*
				(ship2.getPosition()[1]-ship1.getPosition()[1])))*(ship2.getPosition()[0]-ship1.getPosition()[0])/(Math.pow((ship1.getRadius()+ship2.getRadius()), 2)
						*(ship1.getMass()+ship2.getMass()));
		double yVelocity2 = ship1.getVelocity()[1]-(2*ship1.getMass()*((ship2.getVelocity()[0]-ship1.getVelocity()[0])*
				(ship2.getPosition()[0]-ship1.getPosition()[0])+(ship2.getVelocity()[1]-ship1.getVelocity()[1])*
				(ship2.getPosition()[1]-ship1.getPosition()[1])))*(ship2.getPosition()[1]-ship1.getPosition()[1])/(Math.pow((ship1.getRadius()+ship2.getRadius()), 2)
						*(ship1.getMass()+ship1.getMass()));
		
		System.out.println(xVelocity2);
		System.out.println(yVelocity2);
		ship2.setVelocity(xVelocity2,yVelocity2);
	}
	
	private void shipCollision2(Ship ship1, Ship ship2){
		double collisionAngle = Math.atan((ship1.getVelocity()[0] / ship1.getVelocity()[1])) +
									Math.atan((ship2.getVelocity()[0] / ship2.getVelocity()[1]));
		double calculatedVelocity1 = Math.sqrt(Math.pow(ship1.getVelocity()[0],2) + Math.pow(ship1.getVelocity()[1],2));
		double calculatedVelocity2 = Math.sqrt(Math.pow(ship2.getVelocity()[0],2) + Math.pow(ship2.getVelocity()[1],2));

		double xVelocity1 = calculatedVelocity1;
	}
	
	private void shipCollision(Ship ship1, Ship ship2){
		double deltaX = ship2.getPosition()[0] - ship1.getPosition()[0];
		double deltaY = ship2.getPosition()[1] - ship1.getPosition()[1];
		double deltaR = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) );
		double deltaV = Math.sqrt(Math.pow(ship2.getVelocity()[0] - ship1.getVelocity()[0],2 ) + Math.pow(ship2.getVelocity()[1] - ship1.getVelocity()[1], 2));
		double sigma = ship1.getDistanceBetween(ship2);
		
		double j = (2 * ship1.getMass()*ship2.getMass() * (deltaV * deltaR)) / (sigma * (ship1.getMass() + ship2.getMass()));
		double xJ = (j * deltaX) / sigma;
		double yJ = (j * deltaY) / sigma;
		
		ship1.setVelocity(ship1.getVelocity()[0] + xJ/ship1.getMass(), ship1.getVelocity()[1] + yJ/ship1.getMass());
		ship2.setVelocity(ship2.getVelocity()[0] - xJ/ship2.getMass(), ship2.getVelocity()[1] - yJ/ship2.getMass());
	}
	
	private void shipBulletCollision(Ship ship, Bullet bullet){
		if (bullet.getCollisionState() == true){
			if (bullet.firedFrom() == ship){
				ship.loadBulletOnShip(bullet);
			}
			else{
				bullet.terminate();
				ship.terminate();	
		}
		}
	}
	private void bulletCollision(Bullet bullet1, Bullet bullet2){
		bullet1.terminate();
		bullet2.terminate();
	}
	private void boundaryCollision(Entity object, String xOrY){
		System.out.println(object.getVelocity()[0]);
		if (xOrY == "x"){
			object.setVelocity(-object.getVelocity()[0], object.getVelocity()[1]);			
		}
		if (xOrY == "y"){
			object.setVelocity(object.getVelocity()[0], -object.getVelocity()[1]);
		}
		
	}
	


	private double width;
	private double height;
	private Set<Ship> allShips = new HashSet<Ship>();
	private Set<Bullet> allBulletsWorld = new HashSet<Bullet>();
	
}
