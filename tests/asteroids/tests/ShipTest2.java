package asteroids.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.ProgramParser;
import asteroids.util.ModelException;

public class ShipTest2 {
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}	
	
	@Test
	public void testMaxVelocityShip() throws ModelException {
		Ship ship = facade.createShip(50, 100, 500, 500*Math.sqrt(359999), 50, 0, 1.1E18);
		World world = facade.createWorld(1000000000, 1000000000);
		facade.addShipToWorld(world, ship);
		assertEquals(300000, Math.sqrt(Math.pow(ship.getVelocity()[0],2)+Math.pow(ship.getVelocity()[1], 2)), EPSILON);
		ship.thrustOn();
		ship.thrust(ship.getShipAcceleration(), 10);
		assertEquals(300000, Math.sqrt(Math.pow(ship.getVelocity()[0],2)+Math.pow(ship.getVelocity()[1], 2)), EPSILON);
		

	}
	
	
	@Test
	public void testBulletsShipVelocity() throws ModelException {
		Ship ship = facade.createShip(50, 100, 10, 0, 50, Math.PI, 1.1E18);
		World world = facade.createWorld(10000, 10000);
		facade.addShipToWorld(world, ship);
		Bullet bullet = facade.createBullet(130, 110, 10, 5, 30);
		facade.loadBulletOnShip(ship, bullet);
		assertTrue(facade.getShipVelocity(ship)[0] == facade.getBulletVelocity(bullet)[0]);
		assertTrue(facade.getShipVelocity(ship)[1] == facade.getBulletVelocity(bullet)[1]);
		
	}
	@Test
	public void testAddRemoveBulletShip() throws ModelException {
		Ship ship = facade.createShip(50, 100, 10, 0, 50, Math.PI, 1.1E18);
		World world = facade.createWorld(10000, 10000);
		facade.addShipToWorld(world, ship);
		Bullet bullet = facade.createBullet(130, 110, 10, 5, 30);
		facade.loadBulletOnShip(ship, bullet);
		facade.removeBulletFromShip(ship, bullet);
		assertFalse(facade.getBulletsOnShip(ship).contains(bullet));	
		
	}
	@Test
	public void testFireBullet() throws ModelException {
		Ship ship = facade.createShip(50, 100, 10, 0, 50, Math.PI, 1.1E18);
		World world = facade.createWorld(10000, 10000);
		facade.addShipToWorld(world, ship);
		Bullet bullet = facade.createBullet(130, 110, 10, 5, 30);
		facade.loadBulletOnShip(ship, bullet);
		facade.fireBullet(ship);
		assertFalse(facade.getBulletsOnShip(ship).contains(bullet));
	}
	@Test
	public void testAddShipWorld() throws ModelException {
		Ship ship = facade.createShip(50, 100, 10, 0, 50, Math.PI, 1.1E18);
		World world = facade.createWorld(10000, 10000);
		facade.addShipToWorld(world, ship);
		assertTrue(facade.getWorldShips(world).contains(ship));
	}
	@Test
	public void testRemoveShipWorld() throws ModelException {
		Ship ship = facade.createShip(50, 100, 10, 0, 50, Math.PI, 1.1E18);
		World world = facade.createWorld(10000, 10000);
		facade.addShipToWorld(world, ship);
		facade.removeShipFromWorld(world, ship);
		assertFalse(facade.getWorldShips(world).contains(ship));
	}
	@Test
	public void testAddBulletWorld() throws ModelException {
		World world = facade.createWorld(10000, 10000);
		Bullet bullet = facade.createBullet(130, 110, 10, 5, 30);
		facade.addBulletToWorld(world, bullet);
		assertTrue(facade.getWorldBullets(world).contains(bullet));
		
	}
	@Test
	public void testRemoveBulletWorld() throws ModelException {
		World world = facade.createWorld(10000, 10000);
		Bullet bullet = facade.createBullet(130, 110, 10, 5, 30);
		facade.addBulletToWorld(world, bullet);
		facade.removeBulletFromWorld(world, bullet);
		assertFalse(facade.getWorldBullets(world).contains(bullet));
		
	}
	@Test
	public void testGetEntityAt() throws ModelException {
		Ship ship = facade.createShip(50, 100, 10, 0, 50, Math.PI, 1.1E18);
		World world = facade.createWorld(10000, 10000);
		Bullet bullet = facade.createBullet(130, 110, 10, 5, 30);
		assertFalse(facade.getEntityAt(world, 130, 110) == bullet);
		facade.addBulletToWorld(world, bullet);
		assertTrue(facade.getEntityAt(world, 130, 110) == bullet);
		assertFalse(facade.getEntityAt(world, 50, 100) == ship);
		facade.addShipToWorld(world, ship);
		assertTrue(facade.getEntityAt(world, 50, 100) == ship);
		assertFalse(facade.getEntityAt(world, 51, 100) == ship);
		assertFalse(facade.getEntityAt(world, 130, 111) == bullet);
	}
	@Test
	public void testGetEntities() throws ModelException {
		Ship ship = facade.createShip(50, 100, 10, 0, 50, Math.PI, 1.1E18);
		World world = facade.createWorld(10000, 10000);
		Bullet bullet = facade.createBullet(130, 110, 10, 5, 30);
		facade.addBulletToWorld(world, bullet);
		assertTrue(facade.getEntities(world).contains(bullet));
		facade.addShipToWorld(world, ship);
		assertTrue(facade.getEntities(world).contains(ship));
		assertTrue(facade.getEntities(world).contains(bullet) && facade.getEntities(world).contains(ship));
	}
	@Test
	public void testTimeNextCollision() throws ModelException {
		Ship ship = facade.createShip(60, 60, 0, 100, 50, Math.PI, 1.1E18);
		World world = facade.createWorld(10000, 10000);
		facade.addShipToWorld(world, ship);
		facade.evolve(world, 95, null);
	}
	

}
