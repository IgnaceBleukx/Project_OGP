package asteroids.model;

import java.util.HashSet;
import java.util.Set;

import asteroids.part2.CollisionListener;

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
		for (Entity object: this.getEntities()){
			if (object.getPosition()[0] == x && object.getPosition()[1] == y){
				return object;
				}
			}
		return null;
		}
	
	public Set<? extends Entity> getEntities(){

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
		Set<? extends Entity> allEntities = this.getEntities();	
		for (Entity object1 : allEntities){
			if(object1.getTimeCollisionBoundary() < timeNextCollision && object1.getTimeCollisionBoundary() > 0){
				timeNextCollision = object1.getTimeCollisionBoundary();
				this.setCollisionEntity1(object1);
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
		Set<? extends Entity> allEntities = this.getEntities();
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
	
	public void evolve1(double dt, CollisionListener collisionListener){
//	double timeNextCollision = this.getTimeNextCollision();
//	System.out.println(dt);
//	if(timeNextCollision > dt){
//		for (Ship ship : this.getAllShips()){
//			ship.setPosition(ship.getPosition()[0] + ship.getVelocity()[0]*dt, ship.getPosition()[1] + ship.getVelocity()[1]*dt);
//			if(ship.inspectThruster() == true){
//				ship.newThruster(ship.getShipAcceleration(),dt);			
//			}
//		}
//		for (Bullet bullet : this.getAllBullets()){
//			bullet.setPosition(bullet.getPosition()[0] + bullet.getVelocity()[0]*dt, bullet.getPosition()[1] + bullet.getVelocity()[1]*dt);
//			
//			}
//		}
//	else {
//		if(this.getCollisionEntity1() instanceof Ship && this.getCollisionEntity2() instanceof Ship){
//			Object entity1 = this.getCollisionEntity1();
//			Object entity2 = this.getCollisionEntity2();
//			double xVelocity1 = entity1.getVelocity()[0]+(2*entity2.getMass()*((entity2.getVelocity()[0]-entity1.getVelocity()[0])*
//					(entity2.getPosition()[0]-entity1.getPosition()[0])+(entity2.getVelocity()[1]-entity1.getVelocity()[1])*
//					(entity2.getPosition()[1]-entity1.getPosition()[1])))*(entity2.getPosition()[0]-entity1.getPosition()[0])/(Math.pow((entity1.getRadius()+entity2.getRadius()), 2)
//							*(entity1.getMass()+entity2.getMass()));
//			double yVelocity1 = entity1.getVelocity()[1]+(2*entity2.getMass()*((entity2.getVelocity()[0]-entity1.getVelocity()[0])*
//					(entity2.getPosition()[0]-entity1.getPosition()[0])+(entity2.getVelocity()[1]-entity1.getVelocity()[1])*
//					(entity2.getPosition()[1]-entity1.getPosition()[1])))*(entity2.getPosition()[1]-entity1.getPosition()[1])/(Math.pow((entity1.getRadius()+entity2.getRadius()), 2)
//							*(entity1.getMass()+entity2.getMass()));
//			entity1.setVelocity(xVelocity1, yVelocity1);
//			
//			double xVelocity2 = entity1.getVelocity()[0]-(2*entity1.getMass()*((entity2.getVelocity()[0]-entity1.getVelocity()[0])*
//					(entity2.getPosition()[0]-entity1.getPosition()[0])+(entity2.getVelocity()[1]-entity1.getVelocity()[1])*
//					(entity2.getPosition()[1]-entity1.getPosition()[1])))*(entity2.getPosition()[0]-entity1.getPosition()[0])/(Math.pow((entity1.getRadius()+entity2.getRadius()), 2)
//							*(entity1.getMass()+entity2.getMass()));
//			double yVelocity2 = entity1.getVelocity()[1]-(2*entity1.getMass()*((entity2.getVelocity()[0]-entity1.getVelocity()[0])*
//					(entity2.getPosition()[0]-entity1.getPosition()[0])+(entity2.getVelocity()[1]-entity1.getVelocity()[1])*
//					(entity2.getPosition()[1]-entity1.getPosition()[1])))*(entity2.getPosition()[1]-entity1.getPosition()[1])/(Math.pow((entity1.getRadius()+entity2.getRadius()), 2)
//							*(entity1.getMass()+entity2.getMass()));
//			entity2.setVelocity(xVelocity2, yVelocity2);
//			System.out.println("TimeNextCollision:" + timeNextCollision);
//			this.evolve(dt - timeNextCollision, collisionListener);
//			
//			}
//		if((this.getCollisionEntity1() instanceof Ship && this.getCollisionEntity2() instanceof Bullet)){
//			if(this.getCollisionEntity2().firedFrom() == this.getCollisionEntity1()){
//				//this.getCollisionEntity1().loadBulletOnShip(this.getCollisionEntity2());
//				}
//			}
//		if((this.getCollisionEntity1() instanceof Bullet && this.getCollisionEntity2() instanceof Ship)){
//			if(this.getCollisionEntity1().firedFrom() == this.getCollisionEntity2()){
//				 //this.getCollisionEntity2().loadBulletOnShip(this.getCollisionEntity1());
//				}
//			}
//		else{
//			this.boundaryCollision(entity1, x);
//		}
//		
//		}
//		
	}

	public void evolve(double dt,CollisionListener collisionListener){
		double nextCollisionTime = this.getTimeNextCollision();
		System.out.println(nextCollisionTime);
		if (nextCollisionTime > dt){
			for(Bullet bullet : this.getAllBullets()){
				bullet.setPosition(bullet.getPosition()[0] + bullet.getVelocity()[0]*dt, bullet.getPosition()[1] + bullet.getVelocity()[1]*dt);
				}
			for(Ship ship : this.getAllShips()){
				ship.setPosition(ship.getPosition()[0] + ship.getVelocity()[0]*dt, ship.getPosition()[1] + ship.getVelocity()[1]*dt);
				if(ship.inspectThruster() == true){
					ship.newThruster(ship.getShipAcceleration(),dt);
				}
			}
		}
		else{
			for(Bullet bullet : this.getAllBullets()){
				bullet.setPosition(bullet.getPosition()[0] + bullet.getVelocity()[0]*nextCollisionTime, bullet.getPosition()[1] + bullet.getVelocity()[1]*nextCollisionTime);
				}
			for(Ship ship : this.getAllShips()){
				ship.setPosition(ship.getPosition()[0] + ship.getVelocity()[0]*nextCollisionTime, ship.getPosition()[1] + ship.getVelocity()[1]*nextCollisionTime);
				if(ship.inspectThruster() == true){
					ship.newThruster(ship.getShipAcceleration(),dt);
				}
			}
			if (this.getCollisionEntity2() == null){
				System.out.println(this.getCollisionEntity2());
				System.out.println(this.getCollisionEntity1());
				this.collisionResolver(this.getCollisionEntity1());
			}
			else{
				this.collisionResolver(this.getCollisionEntity1(),this.getCollisionEntity2());

			}
			
			
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
		if (entity.getPositionCollisionBoundary()[0] == entity.getRadius() || entity.getPositionCollisionBoundary()[0] == (this.getDimension()[0]-entity.getRadius())){
			this.boundaryCollision(entity, "y");
		}
		if (entity.getPositionCollisionBoundary()[1] == entity.getRadius() || entity.getPositionCollisionBoundary()[1] == (this.getDimension()[1]-entity.getRadius())){
			this.boundaryCollision(entity ,"x");
		}
	}
	
	private void shipCollision(Ship ship1, Ship ship2){

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
	private void shipBulletCollision(Ship ship, Bullet bullet){
		if (bullet.firedFrom() == ship){
			ship.loadBulletOnShip(bullet);
		}
		else{
			bullet.terminate();
			ship.terminate();
			
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
			System.out.println(object.getVelocity()[0]);
			
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
