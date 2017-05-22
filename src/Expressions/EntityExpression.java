package Expressions;

import asteroids.model.Entity;
import asteroids.model.programs.Program;

public class EntityExpression extends Expression {

	public Entity evaluate(){
		throw new IllegalStateException("The method 'evaluate' should be overwritten by all subclasses of EntityExpression");
	}

	
}
