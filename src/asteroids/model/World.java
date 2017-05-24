package asteroids.model;

import java.util.HashSet;
import java.util.Set;

import asteroids.part2.CollisionListener;

/**
 * A class of Worlds with a width and a height.
 * @invar The dimension of the world must be valid.
 * 		 | isValidDimension(getDimension()[0],getDimension()[1])
 */

public class World {
	
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
		if (this.isValidDimension(width, height)){
			this.width = width;
			this.height = height;
		}
		else{
			this.width = Double.MAX_VALUE;
			this.height = Double.MAX_VALUE;
		}
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
		return (width > 0 && height > 0 && !Double.isNaN(width) && !Double.isNaN(height) && Double.isFinite(width) && Double.isFinite(height));
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
		if (ship == null || !(ship.getWorld() == null) || ship.isOutOfBounds(this)){
			throw new IllegalArgumentException("The ship is null or out of bounds or is already in a World");
		}
		boolean toAddToWorld = true;
		for(Ship otherShip : this.getAllShips()){
			if (ship.overlap(otherShip)){
				toAddToWorld = false;
			}
		}
		if(toAddToWorld){
			this.getAllShips().add(ship);
			ship.setWorld(this);
		}
		else{
			throw new IllegalArgumentException("Ship would be overlapping another Ship in the world");
		}
	}
	
	/**@param ship
	 * @throws IllegalArgumentException
	 * 			If given parameter is invalid, a new exception of the type IllegalArgumentException will be thrown.
	 * This method removes the Ship 'ship' from the set of all ships in the world 'allShips'.
	 */

	
	public void removeShipFromWorld(Ship ship) throws IllegalArgumentException{
		
		if (ship != null && this.getAllShips().contains(ship)){
			this.getAllShips().remove(ship);
			ship.setWorld(null);
		}
		else{
			throw new IllegalArgumentException("The given ship is null or the world does not contain the given ship");
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
			boolean toAddToWorld = true;
			for(Ship otherShip : this.getAllShips()){
				if (bullet.getBulletScource() == null){	
					if (bullet.overlap(otherShip)){
						toAddToWorld = false;
					}
				}
			}
			if(toAddToWorld){
				this.allBulletsWorld.add(bullet);
				bullet.setWorld(this);
			}
			else{
				throw new IllegalArgumentException();
			}
		}catch (NullPointerException exc){
			throw new IllegalArgumentException();
		}	}

	
	/**@param bullet
	 * @throws IllegalArgumentException
	 * 			If the given bullet is not part of the set of this.getAllBullets, a exception of the type IllegalArgumentException will be thrown.
	 * This method removes the Bullet 'bullet' from the set of all bullets in the world 'allBulletsWorld' if it is part of the world.
	 * 			| if (this.getAllBullets().contains(bullet)
	 * 					! new.getAllbullets().contains(bullet)
	 */
	
	public void removeBulletFromWorld(Bullet bullet) throws IllegalArgumentException{
		try{
		this.allBulletsWorld.remove(bullet);
		bullet.setWorld(null);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}
	
	public Set<? extends Entity> getAllEntities(){

		Set<Entity> allEntities = new HashSet<Entity>();
		for (Bullet bullet : this.getAllBullets()){
			allEntities.add(bullet);
		}
		for (Ship ship : this.getAllShips()){
			allEntities.add(ship);
		}
		for (Asteroid asteroid : this.getAllAsteroids()){
			allEntities.add(asteroid);
		}
		for (Planetoid planetoid : this.getAllPlanetoids()){
			allEntities.add(planetoid);
		}
		return allEntities;
	}
	
	public Entity getEntityAt(double x, double y){
		for (Entity object: this.getAllEntities()){
			if (object.getPosition()[0] == x && object.getPosition()[1] == y){
				return object;
				}
			}
		return null;
		}
	
	public void addAsteroid(Asteroid asteroid){
		allAsteroids.add(asteroid);
		asteroid.setWorld(this);
	}
	
	public void removeAsteroid(Asteroid asteroid) throws IllegalArgumentException {
		if (this.allAsteroids.contains(asteroid)){
			allAsteroids.remove(asteroid);
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	public Set<Asteroid> getAllAsteroids(){
		return this.allAsteroids;
	}
	
	
	private Set<Asteroid> allAsteroids = new HashSet<Asteroid>();

	public void addPlanetoid(Planetoid planetoid){
		this.allPlanetoids.add(planetoid);
		planetoid.setWorld(this);
	}
	
	public void removePlanetoid(Planetoid planetoid) throws IllegalArgumentException {
		if (this.allPlanetoids.contains(planetoid)){
			allPlanetoids.remove(planetoid);
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	public Set<Planetoid> getAllPlanetoids(){
		return this.allPlanetoids;
	}
	
	private Set<Planetoid> allPlanetoids = new HashSet<Planetoid>();
	
	public Set<MinorPlanet> getAllMinorPlanets(){
		HashSet<MinorPlanet> minorPlanets = new HashSet<MinorPlanet>();
		for (Planetoid planetoid : this.getAllPlanetoids()){
			minorPlanets.add(planetoid);
		}
		for (Asteroid asteroid : this.getAllAsteroids()){
			minorPlanets.add(asteroid);
		}
		return minorPlanets;
	}
	
	@Deprecated
	public Entity[] getEntitiesNextCollision1(){
		Entity collisionEntity1 = null;
		Entity collisionEntity2 = null;
		double nextCollisionTime = Double.POSITIVE_INFINITY;
		for(Entity entity1:this.getAllEntities()){
			if (entity1.getTimeCollisionBoundary() < nextCollisionTime && entity1.getTimeCollisionBoundary() >= 0){
				nextCollisionTime = entity1.getTimeCollisionBoundary();
				collisionEntity1 = entity1;
				collisionEntity2 = null;
			}
			for(Entity entity2:this.getAllEntities()){
				if (entity1.equals(entity2)){
					continue;
				}
				else{
					if (entity1.getTimeCollisionEntity(entity2) < nextCollisionTime){
						nextCollisionTime = entity1.getTimeCollisionEntity(entity2);
						collisionEntity1 = entity1;
						collisionEntity2 = entity2;
					}
				}
			}
		}
		Entity[] collisionEntities = {collisionEntity1,collisionEntity2};
		return collisionEntities;
	}
	
	public Entity[] getEntitiesNextCollision(){
		double nextCollisionTime = Double.POSITIVE_INFINITY;
		Entity collisionEntity1 = null;
		Entity collisionEntity2 = null;
		boolean boundary = false;
		for (Entity entity1 : this.getAllEntities()){
			for (Entity entity2 : this.getAllEntities()){
				
				if (entity1 == entity2) {
					if (entity1.getTimeCollisionBoundary() < nextCollisionTime && entity1.getTimeCollisionBoundary() >= 0){
						nextCollisionTime = entity1.getTimeCollisionBoundary();
						System.out.println("This is getTimeCollisionBoundary =" +entity1.getTimeCollisionBoundary());
						boundary = true;
						collisionEntity1 = entity1;
						collisionEntity2 = null;
					}
				}
				
				if (!entity1.equals(entity2)){
					if (entity1.getTimeCollisionEntity(entity2) < nextCollisionTime && entity1.getTimeCollisionEntity(entity2) >= 0){
						
						nextCollisionTime = entity1.getTimeCollisionEntity(entity2);
						System.out.println("Nextcollisiontime =" + nextCollisionTime);
						boundary = false;
						collisionEntity1 = entity1;
						collisionEntity2 = entity2;
					}
					if (entity1.getTimeCollisionBoundary() < nextCollisionTime && entity1.getTimeCollisionBoundary() >= 0){
						nextCollisionTime = entity1.getTimeCollisionBoundary();
						boundary = true;
						collisionEntity1 = entity1;
						collisionEntity2 = null;
					}
				}
			}
		}
		Entity[] collisionEntities = new Entity[]{collisionEntity1, collisionEntity2};
		System.out.println("collisionEntities[0] =" + collisionEntities[0]);
		return collisionEntities;
	}
	
	public void evolve(double dt,CollisionListener collisionListener){
		System.out.println("this is dt= " + dt);
		Entity[] entitiesNextCollision = this.getEntitiesNextCollision();
		double nextCollisionTime = Double.POSITIVE_INFINITY;
		if (dt < 0 || Double.isNaN(dt)){
			throw new IllegalArgumentException();
		}
		
		else if (entitiesNextCollision[0] == null){
		}
		
		else if (entitiesNextCollision[1] != null){
			nextCollisionTime = entitiesNextCollision[0].getTimeCollisionEntity(entitiesNextCollision[1]);
		}
		
		else{
			nextCollisionTime = entitiesNextCollision[0].getTimeCollisionBoundary();
		}
		
		
		if (nextCollisionTime > dt){
			for(Entity entity : this.getAllEntities()){
				entity.move(dt);
				if (entity instanceof Ship && ((Ship) entity).inspectThruster() == true){
					((Ship) entity).thrust(((Ship) entity).getShipAcceleration(),dt);

					
				}
			}
		}
		else{
			
			for (Entity entity: this.getAllEntities()){
				entity.move(nextCollisionTime);
				System.out.println("xposentity= " + entity.getPosition()[0]);
				System.out.println("yposentity= " + entity.getPosition()[1]);
				if (entity instanceof Ship && ((Ship) entity).inspectThruster() == true){
					((Ship) entity).thrust(((Ship) entity).getShipAcceleration(),nextCollisionTime);
					
				}
			}
			if (entitiesNextCollision[1] == null){
				
				if (collisionListener != null) {
					double x = entitiesNextCollision[0].getPositionCollisionBoundary()[0];
					double y = entitiesNextCollision[0].getPositionCollisionBoundary()[1];
					collisionListener.boundaryCollision(entitiesNextCollision[0], x, y);
				}
				this.collisionResolver(entitiesNextCollision[0]);
			}
			else{

				
				if (collisionListener != null) {
					double x = entitiesNextCollision[0].getPositionCollisionEntity(entitiesNextCollision[1])[0];
					double y = entitiesNextCollision[0].getPositionCollisionEntity(entitiesNextCollision[1])[1];
				collisionListener.objectCollision(entitiesNextCollision[0], entitiesNextCollision[1], x, y);
				}
				
				this.collisionResolver(entitiesNextCollision[0],entitiesNextCollision[1]);
			}
			

			System.out.println("dt=" + dt);
			System.out.println("nextCollisionTime=" + nextCollisionTime);
			this.evolve(dt - nextCollisionTime, collisionListener);	
			
		}
		
	}

	public void collisionResolver(Entity entity1, Entity entity2){
		System.out.println("Entity 1 = " + entity1.toString());
		System.out.println("Entity 2 = " + entity2.toString());
		if (entity1 instanceof Ship && entity2 instanceof Ship){
			((Ship) entity1).shipCollision((Ship) entity2);
		}
		else if (entity1 instanceof Ship && entity2 instanceof Bullet){
			System.out.println("bulletentity2=" + (Bullet)entity2);
			((Bullet)entity2).collision(entity1);
		}
		else if (entity1 instanceof Bullet && entity2 instanceof Ship){
			((Bullet)entity1).collision(entity2);
		}
		else if (entity1 instanceof Bullet && entity2 instanceof Bullet){
			((Bullet) entity1).collision((Bullet) entity2);
		}
		else if (entity1 instanceof Bullet && entity2 instanceof MinorPlanet){
			((Bullet)entity1).collision(entity2);
		}
		else if (entity1 instanceof MinorPlanet && entity2 instanceof Bullet){
			((Bullet)entity2).collision(entity1);
		}
		else if (entity1 instanceof MinorPlanet && entity2 instanceof Ship){
			((MinorPlanet)entity1).shipCollision((Ship)entity2);
		}
		else if (entity1 instanceof Ship && entity2 instanceof MinorPlanet){
			((MinorPlanet)entity2).shipCollision((Ship)entity1);
		}
		else if (entity1 instanceof MinorPlanet && entity2 instanceof MinorPlanet){
			((MinorPlanet)entity1).minorPlanetCollision((MinorPlanet)entity2);
		}
		else {
			System.out.println("instanceof none");
			throw new IllegalStateException();
		}
	}
	
	public void collisionResolver(Entity entity){
		System.out.println("collisionresolver entity = " + entity);
		if (entity instanceof Bullet){
			System.out.println("I resolved bullet boundary collision");
			((Bullet)entity).boundaryCollision();
		}
		else if(entity instanceof Ship || entity instanceof Asteroid) {
			entity.boundaryCollision();
		}
			
	}
	
	public void terminate(){
		while (!this.getAllBullets().isEmpty()){
			Bullet bullet = this.getAllBullets().iterator().next();
			bullet.terminate();
		}
		while (!this.getAllShips().isEmpty()){
			Ship ship = this.getAllShips().iterator().next();
			ship.terminate();
		}
		this.isTerminated = true;
	}
	
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	private boolean isTerminated = false;
	private double width;
	private double height;
	private Set<Ship> allShips = new HashSet<Ship>();
	private Set<Bullet> allBulletsWorld = new HashSet<Bullet>();
	
}
