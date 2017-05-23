package Expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class GetXExpression extends ValueExpression {

	public GetXExpression(Expression expression, SourceLocation sourceLocation){
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

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public double evaluate() throws ModelException{
		if (getExpression() instanceof EntityExpression){
			passInformation(getExpression());
			return ((EntityExpression) getExpression()).evaluate().getPosition()[0];
		}
		else{
			throw new ModelException("The expression does not evaluate to an entity");
		}
	}	
}

