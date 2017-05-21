package Expressions;

import asteroids.model.Entity;

public class NullExpression extends EntityExpression{

	public NullExpression(){
		
	}
	
	@Override
	public Entity evaluate(){
		return null;
	}
	
}
