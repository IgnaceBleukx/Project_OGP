package Expressions;

import Statements.BreakException;
import asteroids.util.ModelException;

public class BooleanExpression extends Expression {

	public boolean evaluate() throws ModelException, BreakException{
		throw new IllegalStateException("The method 'evaluate' should be overwritten by all subclasses of BooleanExpression");
	}
}
