package Expressions;

import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Ship;

public class BulletExpression extends EntityExpression {

	public BulletExpression(){
	}
	
	public Bullet evaluate(){
		Set<Bullet> bullets = this.getProgram().getShip().getWorld().getAllBullets();
		Ship ship = this.getProgram().getShip();
		Bullet bulletFiredByShip = null;
		for (Bullet bullet : bullets){
			if(bullet.getBulletScource().equals(ship) && !ship.getAllBulletsShip().contains(bullet)){
				bulletFiredByShip = bullet;
				break;
			}
		}
		return bulletFiredByShip;
	}
}
