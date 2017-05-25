package asteroids.facade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.Planetoid;
import asteroids.model.Program;
import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.model.programs.ProgramFactory;
import asteroids.part2.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

public class Facade implements asteroids.part3.facade.IFacade{
	
	public Ship createShip() throws ModelException{
		Ship newShip = new Ship();
		return newShip;
	}
	
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction, double mass)
			throws ModelException{
		try{
			Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);
			return ship;
		}
		catch(IllegalArgumentException exc){
			throw new ModelException("ModelException in createShip");
		}
	}
	
	public double[] getShipPosition(Ship ship) throws ModelException{
		try{
			return ship.getPosition();
		}catch(NullPointerException exc){
			throw new ModelException("ModelException in getShipPosition");
		}
	}
	
	public double[] getShipVelocity(Ship ship) throws ModelException{
		return ship.getVelocity();
	}
	
	public double getShipRadius(Ship ship) throws ModelException{
		return ship.getRadius();
	}

	public double getShipOrientation(Ship ship) throws ModelException{
		try{
		return ship.getOrientation();
		}catch (IllegalArgumentException exc){
			throw new ModelException("The orientation is illegal");
		}
	}
	
	
	public void move(Ship ship, double dt) throws ModelException{
		ship.move(dt);
	}
	
	@Deprecated
	public void thrust(Ship ship, double amount) throws ModelException{
		ship.thrust(amount);
	}
	
	public void turn(Ship ship, double angle) throws ModelException{
		try{
		ship.rotate(angle);
		}catch (IllegalArgumentException exc){
			throw new ModelException("Can not turn if the angle is illegal");
		}
		
	}

	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException{
		return ship1.getDistanceBetween(ship2);
	}
	
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException{
		return ship1.overlap(ship2);
	}
	
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException{
		return ship1.getTimeCollisionEntity(ship2);
	}

	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException{
		return ((Entity) ship1).getPositionCollisionEntity(ship2);
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminate();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}

	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.inspectThruster();
	}

	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if (active == true){
			ship.thrustOn();
		}
		else{
			ship.thrustOff();
		}
	}

	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getShipAcceleration();
	}

	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		Bullet bullet = new Bullet(x,y,xVelocity,yVelocity,radius);
		return bullet;
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {	
		bullet.terminate();
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}


	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getPosition();
	}

	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return bullet.getVelocity();
	}

	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}


	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}


	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getShip();
	}

	
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getBulletScource();
	}

	
	public World createWorld(double width, double height) throws ModelException {
		World world = new World(width, height);
		return world;
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminate();
		
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}

	public double[] getWorldSize(World world) throws ModelException {
		return world.getDimension();
	}

	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getAllShips();
	}

	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getAllBullets();
		
	}

	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try{
		world.addShipToWorld(ship);
		}catch (IllegalArgumentException exc){
			throw new ModelException("The ship is null or out of bounds or is already in a world");
		}
	}

	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try{
			world.removeShipFromWorld(ship);
		}catch(IllegalArgumentException exc){
			throw new ModelException("The given ship equals null or is not part of the world");
		}
		
	}

	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try{
		world.addBulletToWorld(bullet);
		bullet.setWorld(world);
		}catch (IllegalArgumentException exc){
			throw new ModelException("The bullet can't be added to the world");
		}
		
	}

	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeBulletFromWorld(bullet);
		bullet.setWorld(null);
		
	}

	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getAllBulletsShip();
	}

	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBulletsOnShip();
	}

	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try{
		ship.loadBulletOnShip(bullet);
		}catch(IllegalArgumentException exc){
			throw new ModelException("The bullet is null or the bullet is already in a World");
		}
	}

	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try{
			ship.loadBulletsOnShip(bullets);
		}catch(IllegalArgumentException exc){
			throw new ModelException("IllegalArgumentExeption");
		}
	}
	


	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try{
		ship.removeBulletFromShip(bullet);
		}	catch (IllegalArgumentException e){
			throw new ModelException("Bullet is not part of ship and can thus not be removed from it.");
		}
	}


	public void fireBullet(Ship ship) throws ModelException {
		 try{
			 ship.fire();
		 }catch(IndexOutOfBoundsException e){
			 throw new ModelException("The set of bullets of the ship is empty");
		 }
	}


	public double getTimeCollisionBoundary(Object object) throws ModelException {
		return ((Entity) object).getTimeCollisionBoundary();
	}


	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		return ((Entity) object).getPositionCollisionBoundary();
	
	}
	
	
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return (((Entity) entity1).getTimeCollisionEntity((Entity) entity2));

	}

	
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return ((Entity) entity1).getPositionCollisionEntity((Entity) entity2);

	}
	
	
	
	public double getTimeNextCollision(World world) throws ModelException {
		
		double timeNextCollision = Double.POSITIVE_INFINITY;
				
		if (world.getEntitiesNextCollision()[1] != null){
			timeNextCollision = world.getEntitiesNextCollision()[0].getTimeCollisionEntity(world.getEntitiesNextCollision()[1]);
		}
		if (world.getEntitiesNextCollision()[0].getTimeCollisionBoundary() < timeNextCollision){
			timeNextCollision = world.getEntitiesNextCollision()[0].getTimeCollisionBoundary();
		}
	return timeNextCollision;
		
	}

	public double[] getPositionNextCollision(World world) throws ModelException {	
		if (world.getEntitiesNextCollision()[1] != null){
			return world.getEntitiesNextCollision()[0].getPositionCollisionEntity(world.getEntitiesNextCollision()[1]);
		}
		else{
			return world.getEntitiesNextCollision()[0].getPositionCollisionBoundary();
		}
	}
	



	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
	try{
		world.evolve(dt, collisionListener);
	}catch (IllegalArgumentException exc){
		throw new ModelException("Negative time");
	}
	}


	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAt(x, y);
		}


	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getAllEntities();
		
	}

	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}

	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		return world.getAllAsteroids();
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		world.addAsteroid(asteroid);
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		world.removeAsteroid(asteroid);
		
	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		return world.getAllPlanetoids();
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		world.addPlanetoid(planetoid);
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		world.removePlanetoid(planetoid);
		
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		Asteroid asteroid = new Asteroid(x, y, xVelocity, yVelocity, radius);
		return asteroid;
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.terminate();
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return asteroid.isTerminated();
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		return asteroid.getPosition();
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		return asteroid.getVelocity();
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return asteroid.getWorld();
	}

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		try{
			Planetoid planetoid = new Planetoid(x,y,xVelocity, yVelocity, radius, totalTraveledDistance);
			return planetoid;
		}catch (IllegalArgumentException e){
			throw new ModelException("IllegalArgumentException in createPlanetoid");
		}
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		planetoid.terminate();
		
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		return planetoid.isTerminated();
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		return planetoid.getPosition();
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		return planetoid.getVelocity();
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		return planetoid.getRadius();
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		return planetoid.getMass();
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		return planetoid.getTotalTraveledDistance();
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		return planetoid.getWorld();
	}

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		return ship.getShipProgram();
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		ship.loadProgram(program);		
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		return ship.getShipProgram().execute(dt);
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		return new ProgramFactory();
	}

	
}
