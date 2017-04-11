package asteroids.facade;

import java.util.Collection;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part1.facade.IFacade;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

public class Facade implements asteroids.part2.facade.IFacade {
	
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
		return ship1.getTimeToCollapse(ship2);
	}

	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException{
		return ship1.getPositionOfCollapse(ship2);
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.inspectThruster();
	}

	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		ship.thruster(active);
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

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
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
		world.addShipToWorld(world, ship);
		
	}

	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		world.removeShipFromWorld(world, ship);
		
	}

	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		world.addBulletToWorld(world, bullet);
		
	}

	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeBulletToWorld(world, bullet);
		
	}

	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getAllBulletsShip();
	}

	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBulletsOnShip();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}



	
}
