package asteroids.model;

import java.util.HashSet;
import java.util.Set;

import asteroids.part2.CollisionListener;



/**
 * A class of Worlds with a width and a height.
 * @author Ignace Bleukx  and Mats Ruell
 * @Invar The dimension of the world will always be valid.
 * 		 | isValidDimension(getDimension()[0],getDimension()[1])
 */

public class World {
	
	/**
	 * This method creates an empty world with dimensions according to the given parameters.
	 * @param width:  The width of the new World.
	 * @param height: The height of the new World.
	 * @returns An emtpy world with the given width and heigth.
	 * 			| result.getAllEntities().isEmpty()
	 * 			| result.getDimension()[0] = width
	 * 			| result.getDimension()[1] = height
	 */
	public World (double width, double height){
		this.setDimension(width, height);
	}
	
	/**
	 * This method sets the Dimension of the world.
	 * @param width
	 * @param height
	 * @post The new dimensions of the world equal the given parameters if they are valid, otherwise, the dimensions
	 * 			of the world are Double.MaxValue and Double.MaxValue.
	 * 		| new.getDimension()[0] = width || new.getDimension()[0] = Double.MaxValue
	 * 		| new.getDimension()[1] = height || new.getDimension()[1] = Double.MaxValue
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
	 * This method returns the dimensions of the current World.
	 * @return Returns the dimension of the world in an array of length 2 with this.width on index 0 and this.height on index 1.
	 * 				| result = {this.width, this.height}
	 */
	public double[] getDimension(){
		return new double[]{this.width, this.height};
	}
	
	/**
	 * @param width The height that must be checked.
	 * @param height The height that must be checked.
	 * @return Returns true if the width and height of world lie in the range 0 to Double.MAX_VALUE.
	 * 			| see code
	 */
	public boolean isValidDimension(double width, double height){
		return (width > 0 && height > 0 && !Double.isNaN(width) && !Double.isNaN(height) && Double.isFinite(width) && Double.isFinite(height));
	}
	
	private double width;
	private double height;
	
	/**
	 * This method returns a set containing all ships currently placed in this world.
	 * @return Returns the set of all the ships in this world.
	 * 			| if (ship.getWorld() == this
	 * 				result.contains(ship)
	 */	
	public Set<Ship> getAllShips() {
		return this.allShips;
	}
	
	/**
	 * This method sets all ships in this world from allShips.
	 * @param allShips
	 *  		| new.allShips = allShips
	 */
	public void setAllShips(Set<Ship> allShips){
		this.allShips = allShips;
		
	}
	
	/**
	 * This method adds the Ship 'ship' to the set of all ships in the world 'allShips'.
	 * @param ship: The ship that must be added to the world.
	 * @throws IllegalArgumentException: If the given ships equals null of the ship is already placed in a world 
	 * 				or the ship is out of bounds of this world, or the ship would overlap with another entity in the world,
	 * 					a new exception of the type IllegalArgumentException will be thrown.
	 * @Post The world contains the given ship and the world of the ship is set to this one.
	 * 			| new.getAllShips().contains(ship)
	 * 			| new.getWorld().equals(this)
	 */	
	public void addShipToWorld(Ship ship) throws IllegalArgumentException{
		if (ship == null || ship.getWorld() != null || ship.isOutOfBounds(this)){
			throw new IllegalArgumentException("The ship is null or out of bounds or is already in a World");
		}
		boolean toAddToWorld = true;
		for(Entity entity : this.getAllEntities()){
			if (ship.overlap(entity)){
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
	
	/**
	 * This method removes the Ship 'ship' from the set of all ships in the world 'allShips'.
	 * @param ship: the ship that must be removed from the world.
	 * @throws IllegalArgumentException: If the ship to remove is not placed in the world, a new IllegalArgumentExcpetion will be thrown.
	 * @Post The world does not contain the given ship and the world of the ship is set to null.
	 * 			| !new.getAllShips().contains(ship)
	 * 			| new.getWorld() = null
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
	
	private Set<Ship> allShips = new HashSet<Ship>();
	
	/**
	 * This method returns a HashSet of all the bullets currently placed in the world.
	 * @return Returns a set of all the bullets in this world.
	 * 			| if bullet.getWorld().equals(this)
	 * 				result.contains(bullet)
	 */		
	public Set<Bullet> getAllBullets() {
		return allBulletsWorld;
	}
	
	/**
	 * @param allBullets: The that contains all the bullets that must be set.
	 * @post This method sets all the bullets in this world from 'allBullets'
	 * 			| new.getAllBulletsWorld() = allBullets
	 */
	public void setAllBullets(Set<Bullet> allBullets){
		this.allBulletsWorld = allBullets;
		
	}
	
	/**
	 * This method adds the Bullet 'bullet' to the set of all bullets in the world 'allBulletsWorld'.
	 * @param bullet The bullet that must be added to this world.
	 * @throws IllegalArgumentException
	 * 			If the given bullet is already part of a world or the bullet is null or the bullet would overlap with another entity, 
	 * 				an new IllegalArgumentExcpetion is thrown.
	 * @Post The world contains the bullet and the world of the bullet is set to this world.
	 * 			| new.getAllBullets().contains(bullet)
	 * 			| bullet.getWorld().equals(this)
	 */
	public void addBulletToWorld(Bullet bullet) throws IllegalArgumentException{
		try{
			boolean toAddToWorld = true;
			for(Entity entity : this.getAllEntities()){
				if (bullet.getBulletScource() == null){	
					if (bullet.overlap(entity)){
						toAddToWorld = false;
					}
				}
			}
			if(toAddToWorld){
				this.getAllBullets().add(bullet);
				bullet.setWorld(this);
			}
			else{
				throw new IllegalArgumentException();
			}
		}catch (NullPointerException exc){
			throw new IllegalArgumentException();
		}	}
	
	/**
	 * This method removes the bullet from the current world.
	 * @param bullet: The bullet that must be removed from this world.
	 * @throws IllegalArgumentException
	 * 			If the given bullet is not part of the set of this.getAllBullets, a exception of the type IllegalArgumentException will be thrown.
	 * @Post The bullet is removed from the world and the world of the bullet is set to null.
	 * 			| !new.getAllBullets().contains(bullet)
	 * 			| bullet.getWorld() == null
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

	private Set<Bullet> allBulletsWorld = new HashSet<Bullet>();

	/**
	 * This method returns a set of all entities placed in the world.
	 * @return Returns a HashSet of all the entities in this world.
	 * 			| if (entity.getWorld().equals(this)
	 * 				result.contains(entity)
	 */
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
	
	/**
	 * Returns the entity on the given position, if there is none, it returns null.
	 * @param x The x-coordinate of the entity that must be returned.
	 * @param y The y-coordinate of the entity that must be returned.
	 * @return The entity at position (x,y) in this world.
	 * 			| result.getPosition().equals{x,y}
	 */
	public Entity getEntityAt(double x, double y){
		for (Entity object: this.getAllEntities()){
			if (object.getPosition()[0] == x && object.getPosition()[1] == y){
				return object;
				}
			}
		return null;
		}
	
	/**
	 * This method adds the asteroid to the world.
	 * @throws IllegalArgumentExcpetion: If the asteroid is null, or the asteroid is already placed in a world or
	 * 				the asteroid is out of bounds of this world or the asteroid would overlap with another entity,
	 * 					a new IllegalArgumentException is thrown.
	 * @param asteroid The asteroid to be added.
	 * @post The world contains the asteroid.
	 * 			| new.getAllAsteroids().contains(asteroid)
	 */
	public void addAsteroid(Asteroid asteroid) throws IllegalArgumentException{
		if (asteroid == null || asteroid.getWorld() != null || asteroid.isOutOfBounds(this)){
			throw new IllegalArgumentException("The ship is null or out of bounds or is already in a World");
		}
		boolean toAddToWorld = true;
		for(Entity entity : this.getAllEntities()){
			if (asteroid.overlap(entity)){
				toAddToWorld = false;
			}
		}
		if(toAddToWorld){
			this.getAllAsteroids().add(asteroid);
			asteroid.setWorld(this);
		}
		else{
			throw new IllegalArgumentException("The asteroid would overlap with another entity in the world");
		}
	}
	
	/**
	 * This method removes the given asteroid from this world.
	 * @param asteroid The asteroid that must be removed from this world.
	 * @throws IllegalArgumentException: If the asteroid is not part of this world an new IllegalArgumentException is thrown.
	 * @Post The world does not contain the asteroid given.
	 * 			| !new.getAllAsteroids().contains(asteroid)
	 */
	public void removeAsteroid(Asteroid asteroid) throws IllegalArgumentException {
		if (this.allAsteroids.contains(asteroid)){
			allAsteroids.remove(asteroid);
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * This method returns all asteroids currently placed in this world.
	 * @return Returns a HasSet containing all asteroids in this world.
	 * 			| if (asteroid.getWorld().equals(this)
	 * 				result.contains(asteroid)
	 */
	public Set<Asteroid> getAllAsteroids(){
		return this.allAsteroids;
	}
	
	private Set<Asteroid> allAsteroids = new HashSet<Asteroid>();

	/**
	 * @throws IllegalArgumentException: If the planetoid is null, or the planetoid is already placed in a world or
	 * 				the planetoid is out of bounds of this world or the planetoid would overlap with another entity,
	 * 					a new IllegalArgumentException is thrown.
	 * @param planetoid The planetoid to be added to this world.
	 * @Post The world contains the planetoid and the world of the planetoid is set to this.
	 * 			| new.getAllPlanetoids().contains(planetoid).
	 * 			| planetoid.getWorld().equals(this)
	 */
	public void addPlanetoid(Planetoid planetoid) throws IllegalArgumentException {
		if (planetoid == null || planetoid.getWorld() != null || planetoid.isOutOfBounds(this)){
			throw new IllegalArgumentException("The ship is null or out of bounds or is already in a World");
		}
		boolean toAddToWorld = true;
		for(Entity entity : this.getAllEntities()){
			if (planetoid.overlap(entity)){
				toAddToWorld = false;
			}
		}
		if(toAddToWorld){
			this.getAllPlanetoids().add(planetoid);
			planetoid.setWorld(this);
		}
		else{
			throw new IllegalArgumentException("Ship would be overlapping another Ship in the world");
		}
	}
		
	/**
	 * This method removes the planetoid from the world.
	 * @param planetoid The planetoid to be removed from this world.
	 * @throws IllegalArgumentException: If the planetoid is not part of this world, a new IllegalArgumentException is thrown.
	 * @post This world does not contain the planetoid and the world of the planetoid equals null.
	 * 			| !new.getAllPlanetoids().contains(planetoid)
	 * 			| planetoid.getWorld() = null
	 */
	public void removePlanetoid(Planetoid planetoid) throws IllegalArgumentException {
		if (this.allPlanetoids.contains(planetoid)){
			allPlanetoids.remove(planetoid);
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * This method returns all the planetoids currently in this world.
	 * @return A HashSet containing all planetoids in this world.
	 * 			| if (planetoid.getWorld().equals(this)
	 * 				result.contains(planetoid)
	 */
	public Set<Planetoid> getAllPlanetoids(){
		return this.allPlanetoids;
	}
	
	private Set<Planetoid> allPlanetoids = new HashSet<Planetoid>();
	
	/**
	 * This method returns a set of all the MinorPlanets currently placed in this world.
	 * @return A HashSet containing all the MinorPlanets placed in this world.
	 * 			| if (minorPlanet.getWorld().equals(this)
	 * 				result.contains(minorPlanet)
	 */
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
		
	/**
	 * This method returns the entities of the next collision in an array of length 2. If the collision is of the type
	 * 	'BoundaryCollision', the second Entity in the array equals null.
	 * @return  Returns the entities of the next collision in an array of length 2. If the collision is of the type
	 * 				'BoundaryCollision', the second Entity in the array equals null. 
	 */
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
						boundary = true;
						collisionEntity1 = entity1;
						collisionEntity2 = null;
					}
				}
				
				if (!entity1.equals(entity2)){
					if (entity1.getTimeCollisionEntity(entity2) < nextCollisionTime && entity1.getTimeCollisionEntity(entity2) >= 0){
						nextCollisionTime = entity1.getTimeCollisionEntity(entity2);
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
				entitiesNextCollision[0].boundaryCollision();
			}
			else{		
				if (collisionListener != null) {
					double x = entitiesNextCollision[0].getPositionCollisionEntity(entitiesNextCollision[1])[0];
					double y = entitiesNextCollision[0].getPositionCollisionEntity(entitiesNextCollision[1])[1];
				collisionListener.objectCollision(entitiesNextCollision[0], entitiesNextCollision[1], x, y);
				}
				this.collisionResolver(entitiesNextCollision[0],entitiesNextCollision[1]);
			}
			this.evolve(dt - nextCollisionTime, collisionListener);
		}
	}

	/**
	 * This method determines the right type of entitycollision and calls its according methods.
	 * @param entity1 The first Entity of the collision
	 * @param entity2 The second Entity of the collision
	 */
	public void collisionResolver(Entity entity1, Entity entity2){
		if (entity1 instanceof Ship && entity2 instanceof Ship){
			((Ship) entity1).shipCollision((Ship) entity2);
		}
		else if (entity1 instanceof Ship && entity2 instanceof Bullet){
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

	/**
	 * This method terminates all its containings and itself.
	 * @Post The world is terminated and all its containings are terminated.
	 * 			| for (entity : this.getAllEntities)
	 * 				entity.isTerminated()
	 * 			| new.getAllEntities().isEmpty()
	 * 			| new.isTerminated()
	 */
	public void terminate(){		
		while(!this.getAllEntities().isEmpty()){
			Entity entity = this.getAllEntities().iterator().next();
			entity.terminate();
		}
		this.isTerminated = true;
	}
	
	/**
	 * This method returns if the world is terminated or not.
	 * @return Return false if the world is not terminated, returs true if the world is terminated.
	 * 			| return this.isTerminated
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	private boolean isTerminated = false;
	
}
