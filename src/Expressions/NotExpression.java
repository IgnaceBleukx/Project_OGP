package Expressions;

import asteroids.part3.programs.SourceLocation;

public class NotExpression extends BooleanExpression {

	public NotExpression(Expression expression, SourceLocation sourceLocation){
		setExpression(expression);
		setSourceLocation(sourceLocation);
	}
	
	private Expression expression;
	private SourceLocation sourceLocation;
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	@Override
	public boolean evaluate(){
		if (getExpression() instanceof BooleanExpression){
			return !((BooleanExpression) getExpression()).evaluate();
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a boolean");
		}
	}
}
