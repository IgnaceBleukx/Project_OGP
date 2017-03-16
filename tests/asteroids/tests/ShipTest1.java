package asteroids.tests;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

	

public class ShipTest1 {
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}	
		
		
	@Test(expected = ModelException.class)
	public void testShipXVelocityGreaterThanC() throws ModelException {
		facade.createShip(100, 100, 999999999, 20, 20, 0);
	}
	@Test(expected = ModelException.class)
	public void testShipYVelocityGreaterThanC() throws ModelException {
		facade.createShip(100, 100, 20, 99999999, 20, 0);
	}
	@Test(expected = ModelException.class)
	public void testShipTotalVelocityGreaterThanC() throws ModelException {
		facade.createShip(100, 100, 299999, 299999, 20, 0);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipYIsNan() throws ModelException {
		facade.createShip(100, Double.NaN, 10, -10, 20, -Math.PI);
	}
	@Test(expected = ModelException.class)
	public void testCreateShipXVelIsNan() throws ModelException {
		facade.createShip(100, 200, Double.NaN, -10, 20, -Math.PI);
	}
	@Test(expected = ModelException.class)
	public void testCreateShipYVelIsNan() throws ModelException {
		facade.createShip(100, 200, 10, Double.NaN, 20, -Math.PI);
	}
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsNan() throws ModelException {
		facade.createShip(100, 200, 10, -10, Double.NaN, -Math.PI);
	}
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsPosInfinity() throws ModelException {
		facade.createShip(100, 200, 10, -10, Double.POSITIVE_INFINITY, -Math.PI);
	}
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsNegInfinity() throws ModelException {
		facade.createShip(100, 200, 10, -10, Double.NEGATIVE_INFINITY, -Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipOrientationIsNan() throws ModelException {
		facade.createShip(100, 200, 10, -10, 20, Double.NaN);	
	}
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsUnder10() throws ModelException {
		facade.createShip(100, 200, 10, -10, 6, -Math.PI);
	}
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsNegative() throws ModelException {
		facade.createShip(100, 200, 10, -10, -6, -Math.PI);
	}
	@Test
	public void testShipXVelocityPosInfinity() throws ModelException {
		facade.createShip(100, 200, Double.POSITIVE_INFINITY, -10, 20, 0);
	}
	@Test
	public void testShipXVelocityNegInfinity() throws ModelException {
		facade.createShip(100, 200, Double.NEGATIVE_INFINITY, -10, 20, 0);
	}
	@Test(expected = ModelException.class)
	public void testShipYVelocityPosInfinity() throws ModelException {
		facade.createShip(100, 200, 10, Double.POSITIVE_INFINITY, 20, 0);
	}
	@Test(expected = ModelException.class)
	public void testShipYVelocityNegInfinity() throws ModelException {
		facade.createShip(100, 200, 10, Double.NEGATIVE_INFINITY, 20, 0);
	}
	@Test(expected = ModelException.class)
	public void testShipYVelocityNaN() throws ModelException {
		facade.createShip(100, 200, 10,Double.NaN, 20, 0);
	}
	@Test(expected = ModelException.class)
	public void testShipXVelocityNaN() throws ModelException {
		facade.createShip(100, 200, 10, Double.NaN, 20, 0);
	}	
	@Test
	public void testCreateShipXIsPosInfinity() throws ModelException {
		facade.createShip(Double.POSITIVE_INFINITY, 200, 10, -10, 20, -Math.PI);
	}
	
	@Test
	public void testCreateShipYIsPosInfinity() throws ModelException {
		facade.createShip(100, Double.POSITIVE_INFINITY, 10, -10, 20, -Math.PI);
	}
	@Test
	public void testCreateShipXIsNegInfinity() throws ModelException {
		facade.createShip(Double.NEGATIVE_INFINITY, 200, 10, -10, 20, -Math.PI);
	}
	@Test
	public void testCreateShipYIsNegInfinity() throws ModelException {
		facade.createShip(100, Double.NEGATIVE_INFINITY, 10, -10, 20, -Math.PI);
	}	
	@Test
	public void testMove1() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, 0);
		double[] position = facade.getShipPosition(ship);
		assertEquals(100,position[0],EPSILON);
		assertEquals(100,position[1],EPSILON);
	}
		
	@Test
	public void testMove2() throws ModelException {
		Ship ship = facade.createShip(100, 100, 0, 0, 20, 0);
		facade.move(ship, 10);
		double[] position = facade.getShipPosition(ship);
		assertEquals(100,position[0],EPSILON);
		assertEquals(100,position[1],EPSILON);
	}
	@Test
	public void testThrustNegative() throws ModelException {
		Ship ship = facade.createShip(100, 100, 20, -10, 20, 0);
		facade.thrust(ship, -5);
		double[] velocity = facade.getShipVelocity(ship);
		assertEquals(20,velocity[0],EPSILON);
		assertEquals(-10,velocity[1],EPSILON);
	}
	@Test
	public void testThrustGreaterThanC() throws ModelException {
		Ship ship = facade.createShip(100, 100, 25, 25*Math.sqrt(3), 20, Math.PI/3);
		facade.thrust(ship, 999999);
		double[] velocity = facade.getShipVelocity(ship);
		assertEquals(150000,velocity[0],EPSILON);
		assertEquals(300000*Math.sin(Math.PI/3),velocity[1],EPSILON);
	
	}
	@Test
	public void testDistanceBetween() throws ModelException {
		Ship ship1 = facade.createShip(120, 120, 25, 25*Math.sqrt(3), 15, Math.PI/3);
		Ship ship2 = facade.createShip(90, 90, 25, 25*Math.sqrt(3), 10, Math.PI/3);
		assertEquals((Math.sqrt(1800)-25),facade.getDistanceBetween(ship1, ship2),EPSILON);
		
	}
	@Test
	public void testDistanceBetweenOverlap() throws ModelException {
		Ship ship1 = facade.createShip(100, 100, 25, 25*Math.sqrt(3), 20, Math.PI/3);
		Ship ship2 = facade.createShip(99, 99, 25, 25*Math.sqrt(3), 40, Math.PI/3);
		Assert.assertTrue(ship1.getDistanceBetween(ship2) < 0);
	}
	@Test
	public void testDistanceBetweenSame() throws ModelException {
		Ship ship1 = facade.createShip(100, 100, 25, 25*Math.sqrt(3), 20, Math.PI/3);
		assertEquals(0,ship1.getDistanceBetween(ship1),EPSILON);	
		
	}
	@Test
	public void testOverlap() throws ModelException {
		Ship ship1 = facade.createShip(100, 100, 25, 25*Math.sqrt(3), 20, Math.PI/3);
		Ship ship2 = facade.createShip(100, 100, 25, 25*Math.sqrt(3), 20, Math.PI/3);
		Assert.assertTrue(facade.overlap(ship1, ship2) == Boolean.TRUE);
		
	}
	@Test	
	public void testOverlapItself() throws ModelException {
		Ship ship1 = facade.createShip(100, 100, 25, 25*Math.sqrt(3), 20, Math.PI/3);
		Assert.assertTrue(facade.overlap(ship1, ship1) == Boolean.TRUE);	
	}
	
	@Test
	public void testTimeToCollisionNever() throws ModelException {
		Ship ship1 = facade.createShip(-100, -100, -25, -25*Math.sqrt(3), 20, (2*Math.PI/3));
		Ship ship2 = facade.createShip(100, 100, 25, 25*Math.sqrt(3), 20, Math.PI/3);
		assertEquals(Double.POSITIVE_INFINITY,facade.getTimeToCollision(ship1, ship2),EPSILON);		
	}
	@Test
	public void testTimeToCollision() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 0, 0, 10, 0);
		Ship ship2 = facade.createShip(21, 0, -1, 0, 10, Math.PI);
		System.out.print(facade.getTimeToCollision(ship1,ship2));
		assertEquals(1,facade.getTimeToCollision(ship1, ship2),EPSILON);
	}
	@Test
	public void testGetCollisionPosition() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 0, 0, 10, 0);
		Ship ship2 = facade.createShip(21, 0, -1, 0, 10, Math.PI);
		double[] position = facade.getCollisionPosition(ship1, ship2);
		assertEquals(10,position[0],EPSILON);
		assertEquals(0,position[1],EPSILON);	
	}
	@Test
	public void testNeverCollide() throws ModelException {
		Ship ship1 = facade.createShip(-100, -100, -25, -25*Math.sqrt(3), 20, (2*Math.PI/3));
		Ship ship2 = facade.createShip(100, 100, 25, 25*Math.sqrt(3), 20, Math.PI/3);
		assertEquals(null,facade.getCollisionPosition(ship1, ship2));
	}

	
	
	
	
}
