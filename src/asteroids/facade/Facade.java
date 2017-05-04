package asteroids.facade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.MinorPlanet;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.facade.IFacade;
import asteroids.model.Program;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

public class Facade implements asteroids.part2.facade.IFacade{
	
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
		return ship.getOrientation();
	}
	
	
	public void move(Ship ship, double dt) throws ModelException{
		ship.move(dt);
	}
	
	public void thrust(Ship ship, double amount) throws ModelException{
		ship.thrust(amount);
	}
	
	public void turn(Ship ship, double angle) throws ModelException{
		ship.rotate(angle);
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
		return ship1.getPositionCollisionEntity(ship2);
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
		ship.thrustOn();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return false;
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
		return bullet.firedFrom();
	}

	
	public World createWorld(double width, double height) throws ModelException {
		World world = new World(width, height);
		return world;
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		return false;
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
		world.addShipToWorld(ship);
		
	}

	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		world.removeShipFromWorld(ship);
		
	}

	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		world.addBulletToWorld(bullet);
		bullet.setWorld(world);
		
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
		bullet.setShip(ship);
		ship.loadBulletOnShip(bullet);
	}

	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		for (Bullet bullet : bullets){
			bullet.setShip(ship);
			ship.loadBulletOnShip(bullet);
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
		if (world.getEntitiesNextCollision()[1] != null){
			return world.getEntitiesNextCollision()[0].getTimeCollisionEntity(world.getEntitiesNextCollision()[1]);
		}
		else{
			return world.getEntitiesNextCollision()[0].getTimeCollisionBoundary();
		}
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
		world.evolve(dt, collisionListener);
		
	}


	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAt(x, y);
		}


	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getAllEntities();
		
	}

	
}
