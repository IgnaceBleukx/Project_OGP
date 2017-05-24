package Expressions;

import Statements.BreakException;
import asteroids.util.ModelException;

public class ValueExpression extends Expression {

	public double evaluate() throws ModelException, BreakException{
		throw new IllegalStateException("The method 'evaluate' should be overwritten by all subclasses of ValueExpression");
	}
}
