package Expressions;

import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class BulletExpression extends EntityExpression {

	public BulletExpression(SourceLocation location){
		setSourceLocation(location);
	}
	
	private SourceLocation sourceLocation;

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public Bullet evaluate(){
		Set<Bullet> bullets = this.getProgram().getShip().getWorld().getAllBullets();
		Ship ship = this.getProgram().getShip();
		Bullet bulletFiredByShip = null;
		for (Bullet bullet : bullets){
			if(bullet.getBulletScource() != null && bullet.getBulletScource().equals(ship) && !ship.getAllBulletsShip().contains(bullet)){
				bulletFiredByShip = bullet;
				break;
			}
		}
		return bulletFiredByShip;
	}
}
