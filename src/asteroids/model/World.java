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
		try{
			if (ship.getWorld() == null){
				this.allShips.add(ship);
				ship.setWorld(this);
			}
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
		catch(NullPointerException exc){
			throw new IllegalArgumentException();
		}
		
	}
	
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
			System.out.println("This is entity1=" + entity1);
			for (Entity entity2 : this.getAllEntities()){
				System.out.println("This is entity2=" + entity2);
				
				if (entity1 == entity2) {
					if (entity1.getTimeCollisionBoundary() < nextCollisionTime && entity1.getTimeCollisionBoundary() >= 0){
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
						boundary = true;
						collisionEntity1 = entity1;
						collisionEntity2 = null;
					}
				}
			}
		}
		Entity[] collisionEntities = new Entity[]{collisionEntity1, collisionEntity2};
		System.out.println("collisionEntities =" + collisionEntities);
		System.out.println("collisionEntities[0] =" + collisionEntities[0]);
		return collisionEntities;
	}
	
	public void evolve(double dt,CollisionListener collisionListener){
		Entity[] entitiesNextCollision = this.getEntitiesNextCollision();
		double nextCollisionTime = Double.POSITIVE_INFINITY;
		if (dt < 0 || Double.isNaN(dt)){
			throw new IllegalArgumentException();
		}
		if (entitiesNextCollision[0] == null){
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
			}
		}
		else{
			
			for (Entity entity: this.getAllEntities()){
				entity.move(nextCollisionTime);
			}
			if (entitiesNextCollision[1] == null){
				this.collisionResolver(entitiesNextCollision[0]);
			}
			else{
				this.collisionResolver(entitiesNextCollision[0],entitiesNextCollision[1]);
			}
			System.out.println("dt=" + dt);
			System.out.println("nextCollisionTime=" + nextCollisionTime);
			this.evolve(dt - nextCollisionTime, collisionListener);
			
			
		}
		
	}

	public void collisionResolver(Entity entity1, Entity entity2){
		System.out.println("Entity 1 = " + entity1.toString());
		 // System.out.println("Entity 2 = " + entity2.toString()); deze gaf ook een nullpointer als entity2 null is
		if (entity1 instanceof Ship && entity2 instanceof Ship){
			((Ship) entity1).shipCollision((Ship) entity2);
		}
		if (entity1 instanceof Ship && entity2 instanceof Bullet){
			System.out.println("bulletentity2=" + (Bullet)entity2);
			((Bullet)entity2).collision(entity1);
		}
		if (entity1 instanceof Bullet && entity2 instanceof Ship){
			((Bullet)entity1).collision(entity2);
		}
		if (entity1 instanceof Bullet && entity2 instanceof Bullet){
			((Bullet) entity1).collision((Bullet) entity2);
		}
		if (entity1 instanceof Bullet && entity2 instanceof MinorPlanet){
			((Bullet)entity1).collision(entity2);
		}
		if (entity1 instanceof MinorPlanet && entity2 instanceof Bullet){
			((Bullet)entity2).collision(entity1);
		}
		if (entity1 instanceof MinorPlanet && entity2 instanceof Ship){
			((MinorPlanet)entity1).shipCollision((Ship)entity2);
		}
		if (entity1 instanceof Ship && entity2 instanceof MinorPlanet){
			((MinorPlanet)entity2).shipCollision((Ship)entity1);
		}
		if (entity1 instanceof MinorPlanet && entity2 instanceof MinorPlanet){
			((MinorPlanet)entity1).minorPlanetCollision((MinorPlanet)entity2);
		}
	}
	
	public void collisionResolver(Entity entity){
		System.out.println("collisionresolver entity = " + entity);
		if (entity instanceof Bullet){
			((Bullet) entity).boundaryCollision();
		}
		else if(entity instanceof Ship) {
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
