package Expressions;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.util.ModelException;

public class EntityExpression extends Expression {

	public Entity evaluate() throws ModelException{
		throw new IllegalStateException("The method 'evaluate' should be overwritten by all subclasses of EntityExpression");
	}

	
}
