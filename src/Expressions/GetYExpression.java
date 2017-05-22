package Expressions;

import asteroids.part3.programs.SourceLocation;

public class GetYExpression extends ValueExpression {

	public GetYExpression(Expression expression, SourceLocation sourceLocation){
		setExpression(expression);
		setSourceLocation(sourceLocation);
	}
	
	private SourceLocation sourceLocation;
	
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	private Expression expression;
	
	@Override
	public double evaluate(){
		if (getExpression() instanceof EntityExpression){
			return ((EntityExpression) getExpression()).evaluate().getPosition()[1];
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to an entity");
		}
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
