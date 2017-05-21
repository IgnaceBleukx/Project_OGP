package Expressions;

import asteroids.model.Entity;

public class AnyExpression extends EntityExpression {

	public AnyExpression(){
	}
	
	@Override
	public Entity evaluate(){
		return this.getFunction().getProgram().getShip().getWorld().getAllEntities().iterator().next();
	}
}
