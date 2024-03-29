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

public class Part2TestsFull {

  private static final double EPSILON = 0.0001;
  private static final double BIG_EPSILON = 1.0E14;
  private static final double VERY_BIG_EPSILON = 1.0E34;

  static int nbStudentsInTeam = 2;
  IFacade facade;
  World filledWorld;
  Ship ship1, ship2, ship3;
  Bullet bullet1;
  static int score = 0;
  static int max_score = 0;

  @AfterClass
  public static void tearDownAfterClass() {
    System.out.println("Score: " + score + "/" + max_score);
  }

  @Before
  public void setUp() throws ModelException {
	facade = new asteroids.facade.Facade();    
	filledWorld = facade.createWorld(2000, 2000);
    ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    for (int i = 1; i < 10; i++) {
      Bullet bulletToLoad = facade.createBullet(100, 120, 0, 0, 10);
      facade.loadBulletOnShip(ship1, bulletToLoad);
    }
    facade.addShipToWorld(filledWorld, ship1);
    ship2 = facade.createShip(200, 220, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(filledWorld, ship2);
    bullet1 = facade.createBullet(300, 320, 10, 5, 50);
    facade.addBulletToWorld(filledWorld, bullet1);
  }

  @Test
  public void testCreateShip() throws ModelException {
    max_score += 12;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    assertEquals(100, facade.getShipPosition(ship)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship)[1], EPSILON);
    assertEquals(10, facade.getShipVelocity(ship)[0], EPSILON);
    assertEquals(5, facade.getShipVelocity(ship)[1], EPSILON);
    assertEquals(50, facade.getShipRadius(ship), EPSILON);
    assertTrue(facade.getShipOrientation(ship) >= 0.0 - EPSILON);
    assertTrue(facade.getShipOrientation(ship) <= 2.0 * Math.PI + EPSILON);
    assertEquals(1.0E20, facade.getShipMass(ship), BIG_EPSILON);
    assertEquals(null, facade.getShipWorld(ship));
    assertFalse(facade.isShipThrusterActive(ship));
    assertEquals(0, facade.getShipAcceleration(ship), EPSILON);
    assertEquals(0, facade.getNbBulletsOnShip(ship));
    assertFalse(facade.isTerminatedShip(ship));
    score += 12;
  }

  @Test
  public void testCreateShipPositionNan() throws ModelException {
    try {
      max_score += 2;
      facade.createShip(Double.NaN, 120, 10, 5, 50, 0, 1.0E20);
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testCreateShipNegativePositionNotInWorld() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(-100, 120, 10, 5, 50, 0, 1.0E20);
    assertEquals(-100, facade.getShipPosition(ship)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship)[1], EPSILON);
    score += 2;
  }

  @Test
  public void testCreateShipInfinitePositionNotInWorld() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, 5, 50, 0, 1.0E20);
    assertTrue(Double.isInfinite(facade.getShipPosition(ship)[0]));
    assertTrue(Double.isInfinite(facade.getShipPosition(ship)[1]));
    score += 2;
  }

  @Test
  public void testCreateShipVelocityNan() throws ModelException {
    max_score += 1;
    Ship ship = facade.createShip(100, 120, Double.NaN, 5, 50, 0, 1.0E20);
    double velocity = Math.sqrt(facade.getShipVelocity(ship)[0] * facade.getShipVelocity(ship)[0]
        + facade.getShipVelocity(ship)[1] * facade.getShipVelocity(ship)[1]);
    assertTrue(0 - EPSILON <= velocity);
    assertTrue(velocity <= 300000 + EPSILON);
    score += 1;
  }

  @Test
  public void testCreateShipVelocityLargerThanLightSpeed() throws ModelException {
    max_score += 3;
    Ship ship = facade.createShip(100, 120, 500000, 0, 50, 0, 1.0E20);
    double velocity = Math.sqrt(facade.getShipVelocity(ship)[0] * facade.getShipVelocity(ship)[0]
        + facade.getShipVelocity(ship)[1] * facade.getShipVelocity(ship)[1]);
    assertTrue(0 - EPSILON <= velocity);
    assertTrue(velocity <= 300000 + EPSILON);
    score += 3;
  }

  public void testCreateShipRadiusNan() throws ModelException {
    try {
      max_score += 1;
      facade.createShip(100, 120, 10, 5, Double.NaN, 0, 1.0E20);
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testCreateShipRadiusZero() throws ModelException {
    try {
      max_score += 2;
      facade.createShip(100, 120, 10, 5, 0, 0, 1.0E20);
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testCreateShipDirectionNegative() throws ModelException {
    try {
      max_score += 1;
      facade.createShip(100, 120, 10, 5, 50, -Math.PI, 1.0E20);
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testCreateShipDirectionTooLarge() throws ModelException {
    try {
      max_score += 1;
      facade.createShip(100, 120, 10, 5, 50, 3 * Math.PI, 1.0E20);
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testCreateShipMassNan() throws ModelException {
    max_score += 1;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, Double.NaN);
    double minimalMass = Math.PI * 4 / 3. * Math.pow(50, 3) * 1.42E12;
    assertTrue(Double.isFinite(facade.getShipMass(ship)));
    assertTrue(minimalMass - BIG_EPSILON <= facade.getShipMass(ship));
    score += 1;
  }

  @Test
  public void testCreateShipMassNegative() throws ModelException {
    max_score += 1;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, -4);
    double minimalMass = Math.PI * 4 / 3. * Math.pow(50, 3) * 1.42E12;
    assertTrue(Double.isFinite(facade.getShipMass(ship)));
    System.out.println(ship.getMass());
    assertTrue(minimalMass - BIG_EPSILON <= facade.getShipMass(ship));
    score += 1;
  }

  @Test
  public void testCreateShipMassTooSmall() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0);
    double minimalMass = Math.PI * 4 / 3. * Math.pow(50, 3) * 1.42E12;
    assertTrue(Double.isFinite(facade.getShipMass(ship)));
    assertTrue(minimalMass - BIG_EPSILON <= facade.getShipMass(ship));
    score += 2;
  }

  @Test
  public void testSetShipThruster() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    facade.setThrusterActive(ship, true);
    double expectedAcceleration = 1.1E18 / 1.0E20;
    assertTrue(facade.isShipThrusterActive(ship));
    assertEquals(expectedAcceleration, facade.getShipAcceleration(ship), EPSILON);
    score += 2;
  }


  @Test
  public void testCreateBullet() throws ModelException {
    max_score += 10;
    Bullet bullet = facade.createBullet(100, 120, 10, 5, 50);
    assertEquals(100, facade.getBulletPosition(bullet)[0], EPSILON);
    assertEquals(120, facade.getBulletPosition(bullet)[1], EPSILON);
    assertEquals(10, facade.getBulletVelocity(bullet)[0], EPSILON);
    assertEquals(5, facade.getBulletVelocity(bullet)[1], EPSILON);
    assertEquals(50, facade.getBulletRadius(bullet), EPSILON);
    assertEquals(4.0 * 7.8E12 * Math.PI * Math.pow(50.0, 3) / 3.0, facade.getBulletMass(bullet), BIG_EPSILON);
    assertNull(facade.getBulletWorld(bullet));
    assertNull(facade.getBulletShip(bullet));
    assertNull(facade.getBulletSource(bullet));
    score += 10;
  }

 
  @Test
  public void testCreateWorld() throws ModelException {
    max_score += 4;
    World world = facade.createWorld(1000, 800);
    assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
    assertEquals(800, facade.getWorldSize(world)[1], EPSILON);
    assertTrue(facade.getWorldShips(world).isEmpty());
    assertTrue(facade.getWorldBullets(world).isEmpty());
    score += 4;
  }

  @Test
  public void testCreateWorldNegativeSize() throws ModelException {
    max_score += 1;
    World world = facade.createWorld(1000, -800);
    double width = facade.getWorldSize(world)[0];
    double height = facade.getWorldSize(world)[1];
    assertTrue(Double.isFinite(width) && 0.0 - EPSILON <= width);
    assertTrue(Double.isFinite(height) && 0.0 - EPSILON <= height);
    score += 1;
  }

  @Test
  public void testCreateWorldNanSize() throws ModelException {
    max_score += 1;
    World world = facade.createWorld(Double.NaN, 800);
    double width = facade.getWorldSize(world)[0];
    double height = facade.getWorldSize(world)[1];
    assertTrue(Double.isFinite(width) && 0.0 - EPSILON <= width);
    assertTrue(Double.isFinite(height) && 0.0 - EPSILON <= height);
    score += 1;
  }

  @Test
  public void testCreateWorldInfiniteSize() throws ModelException {
    max_score += 1;
    World world = facade.createWorld(Double.POSITIVE_INFINITY, 800);
    double width = facade.getWorldSize(world)[0];
    double height = facade.getWorldSize(world)[1];
    assertTrue(Double.isFinite(width) && (0.0 - EPSILON <= width));
    assertTrue(Double.isFinite(height) && (0.0 - EPSILON <= height));
    score += 1;
  }

  @Test
  public void testAddShip() throws ModelException {
    max_score += 6;
    World world = facade.createWorld(1000, 1000);
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    assertEquals(1, facade.getWorldShips(world).size());
    assertTrue(facade.getWorldShips(world).contains(ship));
    assertEquals(world, facade.getShipWorld(ship));
    score += 6;
  }

  @Test
  public void testAddShipNull() throws ModelException {
    try {
      max_score += 1;
      World world = facade.createWorld(1000, 1000);
      facade.addShipToWorld(world, null);
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testAddShipOutOfWorld() throws ModelException {
    try {
      max_score += 4;
      World world = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100000, 1200000, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship);
    } catch (ModelException exc) {
      score += 4;
    }
  }

  @Test
  public void testAddOverlappingShips() throws ModelException {
    try {
      max_score += 3;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(130, 150, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(world, ship2);
    } catch (ModelException exc) {
      score += 3;
    }
  }

  @Test
  public void testAddShipTwice() throws ModelException {
    try {
      max_score += 1;
      World world = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship);
      facade.addShipToWorld(world, ship);
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testAddShipInDifferentWorld() throws ModelException {
    try {
      max_score += 3;
      World world = facade.createWorld(1000, 1000);
      World otherWorld = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(otherWorld, ship);
      facade.addShipToWorld(world, ship);
      assertTrue(facade.getWorldShips(world).contains(ship) != facade.getWorldShips(otherWorld).contains(ship));
      assertTrue(facade.getWorldShips(world).size() + facade.getWorldShips(otherWorld).size() == 1);
      score += 3;
    } catch (ModelException exc) {
      score += 3;
    }
  }

  @Test
  public void testAddBullet() throws ModelException {
    max_score += 3;
    World world = facade.createWorld(1000, 1000);
    Bullet bullet = facade.createBullet(100, 120, 10, 5, 50);
    facade.addBulletToWorld(world, bullet);
    assertEquals(1, facade.getWorldBullets(world).size());
    assertTrue(facade.getWorldBullets(world).contains(bullet));
    assertEquals(world, facade.getBulletWorld(bullet));
    score += 3;
  }

  @Test
  public void testAddOverlappingBulletShip() throws ModelException {
    try {
      max_score += 4;
      World world = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Bullet bullet = facade.createBullet(130, 150, 10, 5, 50);
      facade.addShipToWorld(world, ship);
      facade.addBulletToWorld(world, bullet);
    } catch (ModelException exc) {
      score += 4;
    }
  }

  @Test
  public void testRemoveShip() throws ModelException {
    max_score += 6;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    Ship ship2 = facade.createShip(400, 520, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.removeShipFromWorld(world, ship1);
    assertEquals(1, facade.getWorldShips(world).size());
    assertTrue(facade.getWorldShips(world).contains(ship2));
    assertEquals(null, facade.getShipWorld(ship1));
    assertEquals(world, facade.getShipWorld(ship2));
    score += 6;
  }

  @Test
  public void testRemoveShipNull() throws ModelException {
    try {
      max_score += 1;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 520, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(world, ship2);
      facade.removeShipFromWorld(world, null);
    } catch (ModelException exc) {
      score += 1;
    }
  }

  @Test
  public void testRemoveShipNotInWorld() throws ModelException {
    try {
      max_score += 2;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 520, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.removeShipFromWorld(world, ship2);
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testRemoveShipInDifferentWorld() throws ModelException {
    try {
      max_score += 3;
      World world = facade.createWorld(1000, 1000);
      World otherWorld = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 520, 10, 5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(otherWorld, ship2);
      facade.removeShipFromWorld(world, ship2);
    } catch (ModelException exc) {
      score += 3;
    }
  }

  @Test
  public void testRemoveBullet() throws ModelException {
    max_score += 3;
    World world = facade.createWorld(1000, 1000);
    Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(400, 520, 10, 5, 50);
    facade.addBulletToWorld(world, bullet1);
    facade.addBulletToWorld(world, bullet2);
    facade.removeBulletFromWorld(world, bullet1);
    assertEquals(1, facade.getWorldBullets(world).size());
    assertTrue(facade.getWorldBullets(world).contains(bullet2));
    assertEquals(null, facade.getBulletWorld(bullet1));
    assertEquals(world, facade.getBulletWorld(bullet2));
    score += 3;
  }

  @Test
  public void testGetEntityAt() throws ModelException {
    max_score += 2;
    World world = facade.createWorld(1000, 1000);
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    Object result = facade.getEntityAt(world, 100, 120);
    assertEquals(ship, result);
    score += 2;
  }

  @Test
  public void testGetEntityAt_NoEntity() throws ModelException {
    max_score += 2;
    World world = facade.createWorld(1000, 1000);
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    Object result = facade.getEntityAt(world, 110, 120);
    assertNull(result);
    score += 2;
  }

  @Test
  public void testGetEntityAt_PositionOutOfBounds() throws ModelException {
    max_score += 1;
    World world = facade.createWorld(1000, 1000);
    Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    Object result = facade.getEntityAt(world, Double.POSITIVE_INFINITY, 120);
    assertNull(result);
    score += 1;
  }

  @Test
  public void testGetEntities() throws ModelException {
    max_score += 6;
    Set<? extends Object> entities = facade.getEntities(filledWorld);
    assertEquals(3, entities.size());
    assertTrue(entities.contains(ship1));
    assertTrue(entities.contains(ship2));
    assertTrue(entities.contains(bullet1));
    Ship otherShip = facade.createShip(400, 420, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(filledWorld, otherShip);
    assertEquals(3, entities.size());
    score += 6;
  }

  @Test
  public void testGetShips() throws ModelException {
    max_score += 2;
    Set<? extends Object> entities = facade.getWorldShips(filledWorld);
    assertEquals(2, entities.size());
    assertTrue(entities.contains(ship1));
    assertTrue(entities.contains(ship2));
    score += 2;
  }

  @Test
  public void testGetBullets() throws ModelException {
    max_score += 2;
    Bullet bullet1 = facade.createBullet(500, 520, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(600, 620, 10, 5, 50);
    facade.addBulletToWorld(filledWorld, bullet1);
    facade.addBulletToWorld(filledWorld, bullet2);
    Set<? extends Bullet> bullets = facade.getWorldBullets(filledWorld);
    assertEquals(3, bullets.size());
    assertTrue(bullets.contains(bullet1));
    assertTrue(bullets.contains(bullet2));
    score += 2;
  }

  @Test
  public void testLoadBulletOnShip() throws ModelException {
    max_score += 8;
    Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E40);
    Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(200, 10, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    assertEquals(2, facade.getNbBulletsOnShip(ship));
    if (nbStudentsInTeam > 1) {
      assertEquals(1.0E40 + facade.getBulletMass(bullet1) + facade.getBulletMass(bullet2), facade.getShipMass(ship),
          VERY_BIG_EPSILON);
      assertEquals(2, facade.getBulletsOnShip(ship).size());
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet1));
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet2));
      assertEquals(ship, facade.getBulletShip(bullet1));
      assertNull(facade.getBulletWorld(bullet1));
    } else {
      Bullet dummyBullet = facade.createBullet(0, 0, 0, 0, 3);
      assertEquals(1.0E40 + 2 * facade.getBulletMass(dummyBullet), facade.getShipMass(ship), VERY_BIG_EPSILON);
      assertTrue(facade.isTerminatedBullet(bullet1));
      assertTrue(facade.isTerminatedBullet(bullet2));
    }
    score += 8;
  }

  @Test
  public void testLoadBulletOnShipOutOfShip() throws ModelException {
    try {
      max_score += 5;
      Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
      Bullet bullet = facade.createBullet(560, 120, 10, 5, 50);
      facade.loadBulletOnShip(ship, bullet);
    } catch (ModelException exc) {
      score += 5;
    }
  }

  @Test
  public void testLoadBulletOnShipOverlappingBullets() throws ModelException {
    max_score += 4;
    Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
    Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(130, 110, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    assertEquals(2, facade.getNbBulletsOnShip(ship));
    score += 4;
  }

  @Test
  public void testLoadBulletOnShipBulletAlreadyInWorld() throws ModelException {
    try {
      max_score += 5;
      World world = facade.createWorld(1000, 1000);
      Ship ship = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Bullet bullet = facade.createBullet(100, 120, 10, 5, 50);
      facade.addBulletToWorld(world, bullet);
      facade.loadBulletOnShip(ship, bullet);
    } catch (ModelException exc) {
      score += 5;
    }
  }

  @Test
  public void testLoadMultipleBulletsOnShip() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 7;
      Ship ship = facade.createShip(600, 620, 10, 5, 500, 0, 1.0E20);
      Set<Bullet> bullets = new HashSet<>();
      Bullet bullet1 = facade.createBullet(600, 620, 10, 5, 50);
      Bullet bullet2 = facade.createBullet(700, 510, 10, 5, 30);
      Bullet bullet3 = facade.createBullet(500, 500, 0, 0, 20);
      bullets.add(bullet1);
      bullets.add(bullet2);
      bullets.add(bullet3);
      facade.loadBulletsOnShip(ship, bullets);
      assertEquals(3, facade.getNbBulletsOnShip(ship));
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet1));
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet2));
      assertEquals(ship, facade.getBulletShip(bullet1));
      assertNull(facade.getBulletWorld(bullet1));
      score += 7;
    }
  }

  @Test
  public void testLoadMultipleBulletsOnShipIllegalBullets() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 7;
      Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
      Set<Bullet> bullets = new HashSet<>();
      Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
      Bullet bullet2 = facade.createBullet(200, 10, 10, 5, 30);
      bullets.add(bullet1);
      bullets.add(bullet2);
      bullets.add(null);
      try {
        facade.loadBulletsOnShip(ship, bullets);
        fail();
      } catch (ModelException exc) {
      }
      assertEquals(0, facade.getNbBulletsOnShip(ship));
      score += 7;
    }
  }

  @Test
  public void testRemoveBulletFromShip() throws ModelException {
    max_score += 8;
    Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E40);
    Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
    Bullet bullet2 = facade.createBullet(200, 10, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    facade.removeBulletFromShip(ship, bullet1);
    assertEquals(1, facade.getNbBulletsOnShip(ship));
    if (nbStudentsInTeam > 1) {
      assertEquals(1.0E40 + facade.getBulletMass(bullet2), facade.getShipMass(ship), VERY_BIG_EPSILON);
      assertEquals(1, facade.getBulletsOnShip(ship).size());
      assertFalse(facade.getBulletsOnShip(ship).contains(bullet1));
      assertTrue(facade.getBulletsOnShip(ship).contains(bullet2));
    } else {
      Bullet dummyBullet = facade.createBullet(0, 0, 0, 0, 3);
      assertEquals(1.0E40 + facade.getBulletMass(dummyBullet), facade.getShipMass(ship), VERY_BIG_EPSILON);
    }
    score += 8;
  }

  @Test
  public void testRemoveBulletFromShipBulletOnOtherShip() throws ModelException {
    try {
      max_score += 6;
      Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
      Ship otherShip = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
      Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
      Bullet bullet2 = facade.createBullet(300, 220, 10, 5, 50);
      facade.loadBulletOnShip(ship, bullet1);
      facade.loadBulletOnShip(otherShip, bullet2);
      facade.removeBulletFromShip(ship, bullet2);
      if (nbStudentsInTeam == 1) {
        assertEquals(0, facade.getNbBulletsOnShip(ship));
        throw new ModelException("Needed for a succesfull test.");
      }
    } catch (ModelException exc) {
      score += 6;
    }
  }

  @Test
  public void testFireBullet() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(500, 200, 0, 0, 150, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    Bullet bullet1 = facade.createBullet(520, 170, 10, 5, 10);
    Bullet bullet2 = facade.createBullet(480, 300, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    facade.fireBullet(ship);
    Bullet usedBullet;
    if (nbStudentsInTeam <= 1)
      usedBullet = facade.getWorldBullets(world).iterator().next();
    else if (facade.getBulletsOnShip(ship).contains(bullet1))
      usedBullet = bullet2;
    else
      usedBullet = bullet1;
    assertEquals(1, facade.getWorldBullets(world).size());
    assertEquals(1, facade.getNbBulletsOnShip(ship));
    assertEquals(650 + facade.getBulletRadius(usedBullet), facade.getBulletPosition(usedBullet)[0], 10.0);
    assertEquals(200, facade.getBulletPosition(usedBullet)[1], 10.0);
    assertEquals(250, facade.getBulletVelocity(usedBullet)[0], EPSILON);
    assertEquals(0, facade.getBulletVelocity(usedBullet)[1], EPSILON);
    assertEquals(ship, facade.getBulletSource(usedBullet));
    score += 10;
  }

  @Test
  public void testFireBulletShipNotInWorld() throws ModelException {
    max_score += 3;
    Ship ship = facade.createShip(500, 200, 0, 0, 150, 0, 1.0E20);
    Bullet bullet1 = facade.createBullet(520, 170, 10, 5, 10);
    Bullet bullet2 = facade.createBullet(480, 300, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    facade.fireBullet(ship);
    assertEquals(2, facade.getNbBulletsOnShip(ship));
    score += 3;
  }

  @Test
  public void testFireBulletNoBullets() throws ModelException {
    max_score += 2;
    Ship ship = facade.createShip(500, 200, 0, 0, 150, 0, 1.0E20);
    facade.fireBullet(ship);
    assertEquals(0, facade.getNbBulletsOnShip(ship));
    score += 2;
  }

  @Test
  public void testFireBulletOutOfBounds() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(550, 550, 0, 0, 500, 3 * Math.PI / 2, 1.0E20);
    facade.addShipToWorld(world, ship);
    Bullet bullet1 = facade.createBullet(520, 170, 10, 5, 30);
    Bullet bullet2 = facade.createBullet(480, 300, 10, 5, 30);
    facade.loadBulletOnShip(ship, bullet1);
    facade.loadBulletOnShip(ship, bullet2);
    facade.fireBullet(ship);
    Bullet usedBullet;
    if (nbStudentsInTeam <= 1)
      usedBullet = facade.getWorldBullets(world).iterator().next();
    else if (facade.getBulletsOnShip(ship).contains(bullet1))
      usedBullet = bullet2;
    else
      usedBullet = bullet1;
    System.out.println("BulletXpos = " + usedBullet.getPosition()[0] + "  BulletYPos = " + usedBullet.getPosition()[1]);
   // assertTrue(facade.getWorldBullets(world).isEmpty());
    assertEquals(1, facade.getNbBulletsOnShip(ship));
    assertTrue(facade.isTerminatedBullet(usedBullet));
    score += 8;
  }

  @Test
  public void testFireBulletImmediateHit() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(500, 200, 0, 0, 150, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    Ship ship2 = facade.createShip(700, 200, 0, 0, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship2);
    Bullet bullet1 = facade.createBullet(520, 170, 10, 5, 10);
    facade.loadBulletOnShip(ship1, bullet1);
    facade.fireBullet(ship1);
    assertTrue(facade.getWorldBullets(world).isEmpty());
    assertTrue(facade.getWorldShips(world).contains(ship1));
    assertFalse(facade.getWorldShips(world).contains(ship2));
    assertTrue(facade.isTerminatedShip(ship2));
    assertTrue(facade.isTerminatedBullet(bullet1));
    assertEquals(ship1, facade.getBulletSource(bullet1));
    score += 8;
  }

  @Test
  public void testBoundaryCollision_FiniteTimeRightCollision() throws ModelException {
    max_score += 6;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(500, 300, 100, 0, 100, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    double timeToCollision = facade.getTimeCollisionBoundary(ship);
    double expectedTime = (5000.0 - 600.0) / 100.0;
    assertEquals(expectedTime, timeToCollision, EPSILON);
    double[] collisionPosition = facade.getPositionCollisionBoundary(ship);
    assertEquals(5000, collisionPosition[0], EPSILON);
    assertEquals(300, collisionPosition[1], EPSILON);
    score += 6;
  }

  @Test
  public void testBoundaryCollision_FiniteTimeTopCollision() throws ModelException {
    max_score += 4;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(500, 300, 0, 100, 100, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    double timeToCollision = facade.getTimeCollisionBoundary(ship);
    double expectedTime = (5000.0 - 300.0 - 100.0) / 100.0;
    assertEquals(expectedTime, timeToCollision, EPSILON);
    double[] collisionPosition = facade.getPositionCollisionBoundary(ship);
    assertEquals(500, collisionPosition[0], EPSILON);
    assertEquals(5000, collisionPosition[1], EPSILON);
    score += 4;
  }

  @Test
  public void testBoundaryCollision_NoVelocity() throws ModelException {
    max_score += 5;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(500, 300, 0, 0, 100, 0, 1.0E20);
    facade.addShipToWorld(world, ship);
    double timeToCollision = facade.getTimeCollisionBoundary(ship);
    assertEquals(Double.POSITIVE_INFINITY, timeToCollision, EPSILON);
    assertNull(facade.getPositionCollisionBoundary(ship));
    score += 5;
  }

  @Test
  public void testBoundaryCollision_NoWorld() throws ModelException {
    max_score += 3;
    Ship ship = facade.createShip(500, 100, 0, 0, 100, 0, 1.0E20);
    double timeToCollision = facade.getTimeCollisionBoundary(ship);
    assertEquals(Double.POSITIVE_INFINITY, timeToCollision, EPSILON);
    assertNull(facade.getPositionCollisionBoundary(ship));
    score += 3;
  }

  @Test
  public void testEntityCollisionHorizontalMovement() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    Ship ship2 = facade.createShip(200, 100, -10, 0, 30, 0, 0.1E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    double dt = facade.getTimeCollisionEntity(ship1, ship2);
    assertEquals(2.5, dt, 0.1);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNotNull(pos);
    assertEquals(2, pos.length);
    assertEquals(145, pos[0], EPSILON);
    assertEquals(100, pos[1], EPSILON);
    score += 12;
  }

  @Test
  public void testEntityCollisionSlopingMovement() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100.0 / Math.sqrt(2.0), 100.0 / Math.sqrt(2.0), 10.0 / Math.sqrt(2.0),
        10 / Math.sqrt(2.0), 20, 0, 1.0E20);
    Ship ship2 = facade.createShip(200.0 / Math.sqrt(2.0), 200.0 / Math.sqrt(2.0), -10 / Math.sqrt(2.0),
        -10 / Math.sqrt(2.0), 30, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    double dt = facade.getTimeCollisionEntity(ship1, ship2);
    assertEquals(2.5, dt, 0.1);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNotNull(pos);
    assertEquals(2, pos.length);
    assertEquals(145.0 / Math.sqrt(2.0), pos[0], EPSILON);
    assertEquals(145.0 / Math.sqrt(2.0), pos[1], EPSILON);
    score += 10;
  }

  @Test
  public void tesEntityCollisionSameVelocity() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    Ship ship2 = facade.createShip(200, 100, 10, 0, 30, 0, 0.1E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    double dt = facade.getTimeCollisionEntity(ship1, ship2);
    assertEquals(Double.POSITIVE_INFINITY, dt, EPSILON);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNull(pos);
    score += 8;
  }

  @Test
  public void tesEntityCollisionSameEntities() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    double dt = facade.getTimeCollisionEntity(ship1, ship2);
    assertEquals(Double.POSITIVE_INFINITY, dt, EPSILON);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNull(pos);
    score += 8;
  }

  @Test
  public void testEntityCollisionDifferentHolders() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    Bullet bullet1 = facade.createBullet(300, 100, -10, 0, 20);
    Bullet bullet2 = facade.createBullet(100, 100, -10, 0, 5);
    facade.addBulletToWorld(world, bullet1);
    facade.loadBulletOnShip(ship1, bullet2);
    double dt = facade.getTimeCollisionEntity(bullet1, bullet2);
    assertEquals(Double.POSITIVE_INFINITY, dt, EPSILON);
    double[] pos = facade.getPositionCollisionEntity(ship1, ship2);
    assertNull(pos);
    score += 10;
  }

  @Test
  public void testNextCollision() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(100, 100, 10, 0, 20, 0, 1.0E20);
    Ship ship2 = facade.createShip(200, 100, -10, 0, 30, 0, 1.0E20);
    Ship ship3 = facade.createShip(100, 300, 3, 3, 20, 0, 1.0E20);
    Ship ship4 = facade.createShip(100, 50, 0, -50, 25, 0, 1.0E22);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.addShipToWorld(world, ship3);
    facade.addShipToWorld(world, ship4);
    double timeToCollision = facade.getTimeNextCollision(world);
    double[] positionCollision = facade.getPositionNextCollision(world);
    assertEquals(0.5, timeToCollision, EPSILON);
    assertEquals(100, positionCollision[0], EPSILON);
    assertEquals(0, positionCollision[1], EPSILON);
    score += 12;
  }

  @Test
  public void testEvolveEmptyWorld() throws ModelException {
    max_score += 2;
    World world = facade.createWorld(1000, 1000);
    facade.evolve(world, 30, null);
    assertEquals(0, facade.getWorldShips(world).size());
    score += 2;
  }

  @Test
  public void testEvolveDtNan() throws ModelException {
    try {
      max_score += 2;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 120, 0, -5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(world, ship2);
      facade.evolve(world, Double.NaN, null);
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testEvolveDtNegative() throws ModelException {
    try {
      max_score += 2;
      World world = facade.createWorld(1000, 1000);
      Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
      Ship ship2 = facade.createShip(400, 120, 0, -5, 50, 0, 1.0E20);
      facade.addShipToWorld(world, ship1);
      facade.addShipToWorld(world, ship2);
      facade.evolve(world, -1, null);
    } catch (ModelException exc) {
      score += 2;
    }
  }

  @Test
  public void testEvolveVelocitiesZero() throws ModelException {
    max_score += 6;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 120, 0, 0, 50, 0, 1.0E20);
    Ship ship2 = facade.createShip(900, 120, 0, 0, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.evolve(world, 55, null);
    assertEquals(2, facade.getWorldShips(world).size());
    assertEquals(100, facade.getShipPosition(ship1)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship1)[1], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
    assertEquals(900, facade.getShipPosition(ship2)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship2)[1], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
    score += 6;
  }

  @Test
  public void testEvolveNoCollisions() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    Ship ship2 = facade.createShip(400, 120, 0, -5, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.evolve(world, 1, null);
    assertEquals(2, facade.getWorldShips(world).size());
    assertEquals(110, facade.getShipPosition(ship1)[0], EPSILON);
    assertEquals(125, facade.getShipPosition(ship1)[1], EPSILON);
    // Checking whether the information in the world's map has also been
    // changed.
    assertEquals(ship1, facade.getEntityAt(world, 110, 125));
    assertEquals(10, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(5, facade.getShipVelocity(ship1)[1], EPSILON);
    assertEquals(400, facade.getShipPosition(ship2)[0], EPSILON);
    assertEquals(115, facade.getShipPosition(ship2)[1], EPSILON);
    score += 8;
  }

  @Test
  public void testEvolveAfterShipShipCollision() throws ModelException {
    max_score += 15;
    World world = facade.createWorld(5000, 5000);
    Ship ship1 = facade.createShip(500, 120, 10, 0, 50, 0, 1.0E20);
    Ship ship2 = facade.createShip(800, 120, -10, 0, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.evolve(world, 11, null);
    // collision after 10 seconds
    assertEquals(2, facade.getWorldShips(world).size());
    assertEquals(590, facade.getShipPosition(ship1)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship1)[1], EPSILON);
    assertEquals(-10, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
    assertEquals(710, facade.getShipPosition(ship2)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship2)[1], EPSILON);
    assertEquals(10, facade.getShipVelocity(ship2)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship2)[1], EPSILON);
    score += 15;
  }

  
 
  @Test
  public void testEvolveShipBoundaryCollision() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 120, -10, 0, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    // collision after 5 seconds
    facade.evolve(world, 15, null);
    assertEquals(1, facade.getWorldShips(world).size());
    assertEquals(150, facade.getShipPosition(ship1)[0], EPSILON);
    assertEquals(120, facade.getShipPosition(ship1)[1], EPSILON);
    assertEquals(10, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
    score += 10;
  }

  @Test
  public void testEvolveShipDoubleBoundaryCollision() throws ModelException {
    max_score += 8;
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 100, -10, -10, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    // collision after 5 seconds
    facade.evolve(world, 15, null);
    assertEquals(1, facade.getWorldShips(world).size());
    assertEquals(150, facade.getShipPosition(ship1)[0], EPSILON);

    assertEquals(10, facade.getShipVelocity(ship1)[0], EPSILON);
    assertEquals(10, facade.getShipVelocity(ship1)[1], EPSILON);
    score += 8;
  }

  @Test
  public void testEvolveBulletDiesOnThirdBounce() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(1000, 1000);
    Bullet bullet = facade.createBullet(200, 200, -10, 0, 50);
    facade.addBulletToWorld(world, bullet);
    // first bounce after 15 sec
    // second bounce after 105 sec
    // third bounce after 195 sec
    facade.evolve(world, 194.0, null);
    assertEquals(1, facade.getWorldBullets(world).size());
    assertEquals(60, facade.getBulletPosition(bullet)[0], EPSILON);
    assertEquals(200, facade.getBulletPosition(bullet)[1], EPSILON);
    assertEquals(-10, facade.getBulletVelocity(bullet)[0], EPSILON);
    assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
    facade.evolve(world, 2.0, null);
    assertEquals(0, facade.getWorldBullets(world).size());
    assertTrue(facade.isTerminatedBullet(bullet));
    score += 12;
  }

  @Test
  public void testEvolveShipOwnBulletCollision() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(1090, 120, 0, 0, 50, Math.PI, 1.0E20);
    facade.addShipToWorld(world, ship);
    Bullet bullet = facade.createBullet(1080, 130, -10, 0, 20);
    facade.loadBulletOnShip(ship, bullet);
    facade.fireBullet(ship);
    // collision with own ship after 8 seconds
    facade.evolve(world, 9, null);
    assertEquals(0, facade.getWorldBullets(world).size());
    assertEquals(1, facade.getNbBulletsOnShip(ship));
    assertTrue(facade.getBulletsOnShip(ship).contains(bullet));
    assertTrue(facade.getBulletShip(bullet) == ship);
    assertEquals(1090, facade.getBulletPosition(bullet)[0], EPSILON);
    assertEquals(120, facade.getBulletPosition(bullet)[1], EPSILON);
    assertTrue(facade.getBulletVelocity(bullet)[0] <= 250.0);
    assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
    score += 12;
  }

  @Test
  public void testEvolveAfterOtherShipBulletCollision() throws ModelException {
    max_score += 12;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(1050, 120, -10, 0, 50, Math.PI, 1.0E20);
    facade.addShipToWorld(world, ship);
    Bullet bullet = facade.createBullet(80, 130, 10, 0, 20);
    facade.addBulletToWorld(world, bullet);
    // collision after 83 seconds
    facade.evolve(world, 84, null);
    assertEquals(0, facade.getWorldBullets(world).size());
    assertEquals(0, facade.getWorldShips(world).size());
    assertTrue(facade.isTerminatedBullet(bullet));
    assertTrue(facade.isTerminatedShip(ship));
    score += 12;
  }

  @Test
  public void testEvolveShipWithActiveThruster() throws ModelException {
    max_score += 10;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
    facade.addShipToWorld(world, ship);
    facade.setThrusterActive(ship, true);
    assertEquals(1.0, facade.getShipAcceleration(ship), EPSILON);
    assertTrue(facade.isShipThrusterActive(ship));
    facade.evolve(world, 1, null);
    assertEquals(9, facade.getShipVelocity(ship)[0], EPSILON);
    assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
    score += 10;
    
  }

  @Test
  public void testTerminateShipInWorld() throws ModelException {
    max_score += 5;
    World world = facade.createWorld(5000, 5000);
    Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
    facade.addShipToWorld(world, ship);
    facade.terminateShip(ship);
    assertTrue(facade.isTerminatedShip(ship));
    assertNull(facade.getShipWorld(ship));
    assertTrue(facade.getWorldShips(world).isEmpty());
    score += 5;
  }

  @Test
  public void testTerminateBulletLoadedOnShip() throws ModelException {
    max_score += 5;
    Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
    Bullet bullet = facade.createBullet(100, 120, -10, 0, 20);
    facade.loadBulletOnShip(ship, bullet);
    facade.terminateBullet(bullet);
    assertTrue(facade.isTerminatedBullet(bullet));
    assertNull(facade.getBulletShip(bullet));
    assertTrue(facade.getBulletsOnShip(ship).isEmpty());
    score += 5;
  }

  @Test
  public void testTerminateWorld() throws ModelException {
    max_score += 7;
    facade.terminateWorld(filledWorld);
    assertTrue(facade.isTerminatedWorld(filledWorld));
    assertEquals(0, facade.getEntities(filledWorld).size());
    assertNull(facade.getShipWorld(ship1));
    assertNull(facade.getBulletWorld(bullet1));
    score += 7;
  }

  // Assignment Statement

  @Test
  public void testUPPERBOUND() throws ModelException {
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(100, 200, 10, 10, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    // collision after 5 seconds
    facade.evolve(world, 80, null);
    System.out.println("upperboundtestVEL= " + ship1.getVelocity()[1]);
    System.out.println("upperboundtestPOS= " + ship1.getPosition()[1]);
  }
  
  @Test
  public void testRIGHTBOUND() throws ModelException {
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(900, 200, 10, 10, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.evolve(world, 10, null);
    System.out.println("rightboundtestVEL= " + ship1.getVelocity()[1]);
    System.out.println("rightboundtestPOS= " + ship1.getPosition()[1]);
  }
  
  @Test
  public void testBOUNDARYCOLLISIONS() throws ModelException {
    World world = facade.createWorld(1000, 1000);
    Ship ship1 = facade.createShip(900, 200, 10, 10, 50, 0, 1.0E20);
    Ship ship2 = facade.createShip(750, 200, 10, 10, 50, 0, 1.0E20);
    facade.addShipToWorld(world, ship1);
    facade.addShipToWorld(world, ship2);
    facade.evolve(world, 30, null);
    System.out.println("SHIP1XVEL= " + ship1.getVelocity()[0]);
    System.out.println("SHIP1YVEL= " + ship1.getVelocity()[1]);
    System.out.println("SHIP1XPOS= " + ship1.getPosition()[0]);
    System.out.println("SHIP1YPOS= " + ship1.getPosition()[1]);
    System.out.println("SHIP2XVEL= " + ship2.getVelocity()[0]);
    System.out.println("SHIP2YVEL= " + ship2.getVelocity()[1]);
    System.out.println("SHIP2XPOS= " + ship2.getPosition()[0]);
    System.out.println("SHIP2YPOS= " + ship2.getPosition()[1]);
  }
  

}