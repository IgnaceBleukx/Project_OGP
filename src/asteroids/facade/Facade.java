package asteroids.facade;

import java.util.Collection;
import java.util.HashSet;
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

	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
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
		world.removeBulletToWorld(bullet);
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
		 ship.fire();
	}


	public double getTimeCollisionBoundary(Object object) throws ModelException {
		
		double boundaryTime;
		if(object instanceof Ship){
			Ship entity = (Ship) object;	
			if(entity.getVelocity()[0] > 0) {
				if(entity.getVelocity()[1] > 0) {
					boundaryTime = Math.min((-(entity.getVelocity()[0])+Math.sqrt(Math.pow(entity.getVelocity()[0], 2)-2*(entity.getShipAcceleration()*
					Math.cos(entity.getOrientation()))*((entity.getPosition()[0])-((entity.getRadius())-(entity.getWorld().getDimension()[0])))))/
							(entity.getShipAcceleration()*Math.cos(entity.getOrientation())),
							(-(entity.getVelocity()[1])+Math.sqrt(Math.pow(entity.getVelocity()[1], 2)-2*(entity.getShipAcceleration()*
							Math.sin(entity.getOrientation()))*((entity.getPosition()[1])-((entity.getRadius())-(entity.getWorld().getDimension()[1])))))/
							(entity.getShipAcceleration()*Math.sin(entity.getOrientation())));
				}
				else{
					boundaryTime = Math.min((-(entity.getVelocity()[0])+Math.sqrt(Math.pow(entity.getVelocity()[0], 2)-2*(entity.getShipAcceleration()*
					Math.cos(entity.getOrientation()))*((entity.getPosition()[0])-((entity.getRadius())-(entity.getWorld().getDimension()[0])))))/
							(entity.getShipAcceleration()*Math.cos(entity.getOrientation())),
							(-(entity.getVelocity()[1])-Math.sqrt(Math.pow(entity.getVelocity()[1], 2)-2*(entity.getShipAcceleration()*
									Math.sin(entity.getOrientation()))*((entity.getPosition()[1])-(entity.getRadius()))))/
									(entity.getShipAcceleration()*Math.sin(entity.getOrientation())));					
					}		
			}
			else { 
				if(entity.getVelocity()[1] > 0) {
					boundaryTime = Math.min((-(entity.getVelocity()[0])-Math.sqrt(Math.pow(entity.getVelocity()[0], 2)-2*(entity.getShipAcceleration()*
					Math.cos(entity.getOrientation()))*((entity.getPosition()[0])-(entity.getRadius()))))/
							(entity.getShipAcceleration()*Math.cos(entity.getOrientation())),
							(-(entity.getVelocity()[1])+Math.sqrt(Math.pow(entity.getVelocity()[1], 2)-2*(entity.getShipAcceleration()*
							Math.sin(entity.getOrientation()))*((entity.getPosition()[1])-((entity.getRadius())-(entity.getWorld().getDimension()[1])))))/
							(entity.getShipAcceleration()*Math.sin(entity.getOrientation())));
				}
				else { 
					boundaryTime = Math.min((-(entity.getVelocity()[0])-Math.sqrt(Math.pow(entity.getVelocity()[0], 2)-2*(entity.getShipAcceleration()*
					Math.cos(entity.getOrientation()))*((entity.getPosition()[0])-(entity.getRadius()))))/
							(entity.getShipAcceleration()*Math.cos(entity.getOrientation())),
							(-(entity.getVelocity()[1])-Math.sqrt(Math.pow(entity.getVelocity()[1], 2)-2*(entity.getShipAcceleration()*
							Math.sin(entity.getOrientation()))*((entity.getPosition()[1])-(entity.getRadius()))))/
							(entity.getShipAcceleration()*Math.sin(entity.getOrientation())));
				}
			}
		}
		else {
			Bullet entity = (Bullet) object;
			if(entity.getVelocity()[0] > 0) {
				if(entity.getVelocity()[1] > 0) {
					boundaryTime = Math.min(((entity.getWorld().getDimension()[0]-entity.getRadius())-entity.getPosition()[0])/entity.getVelocity()[0],
							((entity.getWorld().getDimension()[1]-entity.getRadius())-entity.getPosition()[1])/(entity.getVelocity()[1]));
				}
				else {
					boundaryTime = Math.min(((entity.getWorld().getDimension()[0]-entity.getRadius())-entity.getPosition()[0])/entity.getVelocity()[0],
							(entity.getRadius()-entity.getPosition()[1])/(entity.getVelocity()[1]));
				}
			
			}
			else {
				if(entity.getVelocity()[1] > 0) {
					boundaryTime = Math.min((entity.getRadius()-entity.getPosition()[0])/(entity.getVelocity()[0]),
							((entity.getWorld().getDimension()[1]-entity.getRadius())-entity.getPosition()[1])/(entity.getVelocity()[1]));
				}
				else {
					boundaryTime = Math.min((entity.getRadius()-entity.getPosition()[0])/(entity.getVelocity()[0]),
							(entity.getRadius()-entity.getPosition()[1])/(entity.getVelocity()[1]));
				}	
			}
		}
		return boundaryTime;
	}
	


	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		double[] boundaryColPos;
		if(object instanceof Ship){	
			Ship entity = (Ship) object;
			double xPosColBound = entity.getPosition()[0] + (entity.getShipAcceleration()*Math.cos(entity.getOrientation())/2)
					*Math.pow(getTimeCollisionBoundary(entity), 2) + entity.getVelocity()[0]*getTimeCollisionBoundary(entity) + entity.getPosition()[0];
			double yPosColBound = entity.getPosition()[1] + (entity.getShipAcceleration()*Math.sin(entity.getOrientation())/2)
					*Math.pow(getTimeCollisionBoundary(entity), 2) + entity.getVelocity()[1]*getTimeCollisionBoundary(entity) + entity.getPosition()[1];
			boundaryColPos = new double[] {xPosColBound,yPosColBound};
		}
		else {
			Bullet entity = (Bullet) object;
			double xPosColBound = entity.getVelocity()[0]*getTimeCollisionBoundary(entity) + entity.getPosition()[0];
			double yPosColBound = entity.getVelocity()[1]*getTimeCollisionBoundary(entity) + entity.getPosition()[1];
			boundaryColPos = new double[] {xPosColBound,yPosColBound};
		}
		
		return boundaryColPos;
	
	}
	
	
	
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		double timeCollisionEntities = Double.POSITIVE_INFINITY;
		if(entity1 instanceof Ship && entity2 instanceof Ship){
			Ship ship1 = (Ship) entity1;
			Ship ship2 = (Ship) entity2;
			timeCollisionEntities = ship1.getTimeToCollapse(ship2);
		}
		if(entity1 instanceof Ship && entity2 instanceof Bullet){
			Ship ship = (Ship) entity1;
			Bullet bullet = (Bullet) entity2;
			timeCollisionEntities = 
					-(((bullet.getVelocity()[0] - ship.getVelocity()[0]) * (bullet.getPosition()[0]-ship.getPosition()[0]) + 
					(bullet.getVelocity()[1] - ship.getVelocity()[1]) * (bullet.getPosition()[1]-ship.getPosition()[1]) + 
						Math.sqrt((Math.pow((bullet.getVelocity()[0] - ship.getVelocity()[0]) * (bullet.getPosition()[0]-ship.getPosition()[0]) + 
									(bullet.getVelocity()[1] - ship.getVelocity()[1]) * (bullet.getPosition()[1]-ship.getPosition()[1]),2)) 		-
									((Math.pow(bullet.getVelocity()[0] - ship.getVelocity()[0],2)) + (Math.pow(bullet.getVelocity()[1] - ship.getVelocity()[1],2))) * 
										((Math.pow((bullet.getPosition()[0]) - ship.getPosition()[0],2)) + (Math.pow((bullet.getPosition()[1]) - ship.getPosition()[1],2))-
												Math.pow((bullet.getRadius()+ship.getRadius()),2))))/((Math.pow(bullet.getVelocity()[0] - ship.getVelocity()[0],2)) + 
														(Math.pow(bullet.getVelocity()[1] - ship.getVelocity()[1],2))));
			
		}
		if(entity1 instanceof Bullet && entity2 instanceof Ship){
			Ship ship = (Ship) entity2;
			Bullet bullet = (Bullet) entity1;
			timeCollisionEntities = 
					-(((bullet.getVelocity()[0] - ship.getVelocity()[0]) * (bullet.getPosition()[0]-ship.getPosition()[0]) + 
					(bullet.getVelocity()[1] - ship.getVelocity()[1]) * (bullet.getPosition()[1]-ship.getPosition()[1]) + 
						Math.sqrt((Math.pow((bullet.getVelocity()[0] - ship.getVelocity()[0]) * (bullet.getPosition()[0]-ship.getPosition()[0]) + 
									(bullet.getVelocity()[1] - ship.getVelocity()[1]) * (bullet.getPosition()[1]-ship.getPosition()[1]),2)) 		-
									((Math.pow(bullet.getVelocity()[0] - ship.getVelocity()[0],2)) + (Math.pow(bullet.getVelocity()[1] - ship.getVelocity()[1],2))) * 
										((Math.pow((bullet.getPosition()[0]) - ship.getPosition()[0],2)) + (Math.pow((bullet.getPosition()[1]) - ship.getPosition()[1],2))-
												Math.pow((bullet.getRadius()+ship.getRadius()),2))))/((Math.pow(bullet.getVelocity()[0] - ship.getVelocity()[0],2)) + 
														(Math.pow(bullet.getVelocity()[1] - ship.getVelocity()[1],2))));
		}
		else {
			Bullet bullet1 = (Bullet) entity1;
			Bullet bullet2 = (Bullet) entity2;
			timeCollisionEntities = 
					-(((bullet1.getVelocity()[0] - bullet2.getVelocity()[0]) * (bullet1.getPosition()[0]-bullet2.getPosition()[0]) + 
					(bullet1.getVelocity()[1] -bullet2.getVelocity()[1]) * (bullet1.getPosition()[1]-bullet2.getPosition()[1]) + 
						Math.sqrt((Math.pow((bullet1.getVelocity()[0] - bullet2.getVelocity()[0]) * (bullet1.getPosition()[0]-bullet2.getPosition()[0]) + 
									(bullet1.getVelocity()[1] - bullet2.getVelocity()[1]) * (bullet1.getPosition()[1]-bullet2.getPosition()[1]),2)) 		-
									((Math.pow(bullet1.getVelocity()[0] - bullet2.getVelocity()[0],2)) + (Math.pow(bullet1.getVelocity()[1] - bullet2.getVelocity()[1],2))) * 
										((Math.pow((bullet1.getPosition()[0]) - bullet2.getPosition()[0],2)) + (Math.pow((bullet1.getPosition()[1]) - bullet2.getPosition()[1],2))-
												Math.pow((bullet1.getRadius()+bullet2.getRadius()),2))))/((Math.pow(bullet1.getVelocity()[0] - bullet2.getVelocity()[0],2)) + 
														(Math.pow(bullet1.getVelocity()[1] - bullet2.getVelocity()[1],2))));
		}
		
		return timeCollisionEntities;
	}

	
	
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		double[] posColEntities = {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY};
		if(entity1 instanceof Ship && entity2 instanceof Ship){
			Ship ship1 = (Ship) entity1;
			Ship ship2 = (Ship) entity2;
			posColEntities = ship1.getPositionOfCollapse(ship2);
		}
			
		if(entity1 instanceof Ship && entity2 instanceof Bullet){
			Ship ship = (Ship) entity1;
			Bullet bullet = (Bullet) entity2;
			double xPosColEntities = ship.getPosition()[0] + (ship.getShipAcceleration()*Math.cos(ship.getOrientation())/2)
					*Math.pow(getTimeCollisionEntity(ship,bullet), 2) + ship.getVelocity()[0]*getTimeCollisionEntity(ship,bullet) + ship.getPosition()[0];
			double yPosColEntities = ship.getPosition()[1] + (ship.getShipAcceleration()*Math.sin(ship.getOrientation())/2)
					*Math.pow(getTimeCollisionEntity(ship,bullet), 2) + ship.getVelocity()[1]*getTimeCollisionEntity(ship,bullet) + ship.getPosition()[1];
			posColEntities = new double[] {xPosColEntities,yPosColEntities};
		}
			
		if(entity2 instanceof Ship && entity1 instanceof Bullet){
			Ship ship = (Ship) entity2;
			Bullet bullet = (Bullet) entity1;
			double xPosColEntities = ship.getPosition()[0] + (ship.getShipAcceleration()*Math.cos(ship.getOrientation())/2)
					*Math.pow(getTimeCollisionEntity(ship,bullet), 2) + ship.getVelocity()[0]*getTimeCollisionEntity(ship,bullet) + ship.getPosition()[0];
			double yPosColEntities = ship.getPosition()[1] + (ship.getShipAcceleration()*Math.sin(ship.getOrientation())/2)
					*Math.pow(getTimeCollisionEntity(ship,bullet), 2) + ship.getVelocity()[1]*getTimeCollisionEntity(ship,bullet) + ship.getPosition()[1];
			posColEntities = new double[] {xPosColEntities,yPosColEntities};
		}
		else {
			Bullet bullet1 = (Bullet) entity1;
			Bullet bullet2 = (Bullet) entity2;
			double xPosColEntities = bullet1.getVelocity()[0]*getTimeCollisionEntity(bullet1,bullet2) + bullet1.getPosition()[0];
			double yPosColEntities = bullet2.getVelocity()[1]*getTimeCollisionEntity(bullet1,bullet2) + bullet1.getPosition()[1];
			posColEntities = new double[] {xPosColEntities,yPosColEntities};		
		}	
		return posColEntities;
	}
	
	
	
	public double getTimeNextCollision(World world) throws ModelException {
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

		Set<Object> allEntities = new HashSet<Object>();
		for (Bullet bullet : world.getAllBullets()){
			allEntities.add(bullet);
		}
		for (Ship ship : world.getAllShips()){
			allEntities.add(ship);
		}
		return allEntities;
	}



	
}
