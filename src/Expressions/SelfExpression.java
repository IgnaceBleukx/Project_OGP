package Expressions;

import asteroids.model.Ship;

public class SelfExpression extends EntityExpression {

	public SelfExpression(){
	}
	
	@Override
	public Ship evaluate(){
		return this.getProgram().getShip();
	}
}
