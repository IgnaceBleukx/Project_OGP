package Expressions;

import Statements.BreakException;
import asteroids.util.ModelException;

public class ValueVariable extends ValueExpression {

	public ValueVariable (Expression expression, String name){
		setName(name);
		setExpression(expression);
	}
	
	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	private Expression expression;
	private String Name;
	
	public double evaluate() throws ModelException, BreakException{
		if (getExpression() instanceof ValueExpression){
			passInformation(getExpression());
			return ((ValueExpression) getExpression()).evaluate();
		}
		else{
			throw new ModelException("The ValueVariable expression does not evaluate to a value");
		}
	}
}
